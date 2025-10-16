// Camera Management Module
let videoStream = null;
let currentColor = { r: 0, g: 0, b: 0 };
let autoModeInterval = null;

const videoElement = document.getElementById('videoPreview');
const cameraSelector = document.getElementById('cameraSelector');
const colorPreview = document.getElementById('colorPreview');
const sendButton = document.getElementById('sendButton');
const autoModeToggle = document.getElementById('autoModeToggle');
const errorMessage = document.getElementById('errorMessage');
const hiddenCanvas = document.getElementById('hiddenCanvas');
const canvasContext = hiddenCanvas.getContext('2d');

async function initializeCameraSelector() {
    try {
        const devices = await navigator.mediaDevices.enumerateDevices();
        const videoDevices = devices.filter(device => device.kind === 'videoinput');

        cameraSelector.innerHTML = '';
        videoDevices.forEach((device, index) => {
            const option = document.createElement('option');
            option.value = device.deviceId;
            option.text = device.label || `Camera ${index + 1}`;
            cameraSelector.appendChild(option);
        });

        if (videoDevices.length > 0) {
            await startCamera(videoDevices[0].deviceId);
        }
    } catch (error) {
        handleCameraError('Failed to enumerate cameras: ' + error.message);
    }
}

async function startCamera(deviceId) {
    try {
        if (videoStream) {
            videoStream.getTracks().forEach(track => track.stop());
        }

        const constraints = {
            video: {
                deviceId: deviceId ? { exact: deviceId } : undefined
            }
        };

        videoStream = await navigator.mediaDevices.getUserMedia(constraints);
        videoElement.srcObject = videoStream;

        videoElement.onloadedmetadata = () => {
            startColorDetection();
        };

        clearError();
    } catch (error) {
        handleCameraError('Camera access denied or unavailable: ' + error.message);
    }
}

function handleCameraError(message) {
    displayError(message);
    cameraSelector.disabled = false;
}

// Initialize on page load
document.addEventListener('DOMContentLoaded', async () => {
    await initializeCameraSelector();

    cameraSelector.addEventListener('change', async (e) => {
        await startCamera(e.target.value);
    });

    sendButton.addEventListener('click', () => {
        sendColorToBackend(currentColor);
    });

    autoModeToggle.addEventListener('change', toggleAutoMode);
});


// Color Detection Module
function detectDominantColor() {
    if (!videoElement.videoWidth || !videoElement.videoHeight) {
        return { r: 0, g: 0, b: 0 };
    }

    hiddenCanvas.width = videoElement.videoWidth;
    hiddenCanvas.height = videoElement.videoHeight;

    canvasContext.drawImage(videoElement, 0, 0, hiddenCanvas.width, hiddenCanvas.height);

    const imageData = canvasContext.getImageData(0, 0, hiddenCanvas.width, hiddenCanvas.height);
    const pixels = imageData.data;

    let totalR = 0, totalG = 0, totalB = 0;
    let sampleCount = 0;

    // Sample every 10th pixel for performance
    for (let i = 0; i < pixels.length; i += 40) {
        totalR += pixels[i];
        totalG += pixels[i + 1];
        totalB += pixels[i + 2];
        sampleCount++;
    }

    return {
        r: Math.round(totalR / sampleCount),
        g: Math.round(totalG / sampleCount),
        b: Math.round(totalB / sampleCount)
    };
}

function updateColorPreview(color) {
    colorPreview.style.backgroundColor = `rgb(${color.r}, ${color.g}, ${color.b})`;
}

function startColorDetection() {
    setInterval(() => {
        currentColor = detectDominantColor();
        updateColorPreview(currentColor);
    }, 100);
}


// Mode Control Module
async function sendColorToBackend(color) {
    const startTime = Date.now();

    try {
        const response = await fetch('/api/color', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                red: color.r,
                green: color.g,
                blue: color.b
            })
        });

        const result = await response.json();
        const responseTime = Date.now() - startTime;

        if (!result.success) {
            displayError(result.message);
        } else {
            clearError();
            if (responseTime > 2000) {
                console.warn(`Color change took ${responseTime}ms (exceeds 2s requirement)`);
            }
        }
    } catch (error) {
        displayError('Cannot connect to server: ' + error.message);
    }
}

function toggleAutoMode() {
    if (autoModeToggle.checked) {
        autoModeInterval = setInterval(() => {
            sendColorToBackend(currentColor);
        }, 3000);
        sendColorToBackend(currentColor);
    } else {
        if (autoModeInterval) {
            clearInterval(autoModeInterval);
            autoModeInterval = null;
        }
    }
}

function displayError(message) {
    errorMessage.textContent = message;
    errorMessage.style.display = 'block';
}

function clearError() {
    errorMessage.textContent = '';
    errorMessage.style.display = 'none';
}
