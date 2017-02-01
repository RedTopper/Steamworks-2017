import numpy as np
import cv2
import time

# Constants
VIDEO_WIDTH = 640
VIDEO_HEIGHT = 360
VIDEO_FPS = 29
VIDEO_OUTPUT = time.strftime("%I-%M-%d") + ".mp4"  # FORMAT: HOUR-MINUTE-DAY
VIDEO_CODEC = cv2.VideoWriter_fourcc(*'MPEG')
CAMERA = cv2.VideoCapture("Oats.mp4")

# Capture Settings
CAMERA.set(3, VIDEO_WIDTH)
CAMERA.set(4, VIDEO_HEIGHT)
CAMERA.set(5, VIDEO_FPS)
CAMERA.set(6, VIDEO_CODEC)

# Define Output Location
out = cv2.VideoWriter(VIDEO_OUTPUT, VIDEO_CODEC, VIDEO_FPS, (VIDEO_WIDTH, VIDEO_HEIGHT))

while CAMERA.isOpened():
    # retrieve the frame being sent.
    # ret = true if frame is retrieved, false if not
    # frame = frame read
    ret, frame = CAMERA.read()

    if ret:
        # outputs frame to file
        frame = cv2.flip(frame, 1)
        out.write(frame)

        # Shows frame on screen
        cv2.imshow('frame', frame)

        # Press c to end code
        if cv2.waitKey(1) == ord('c'):
            break
    else:
        break

CAMERA.release()
out.release()
cv2.destroyAllWindows()
