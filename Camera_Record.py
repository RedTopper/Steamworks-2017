import numpy as np
import cv2

# Constants
VIDEO_WIDTH = 1280
VIDEO_HEIGHT = 720
VIDEO_FPS = 15
VIDEO_OUTPUT = time.strftime("%I-%M-%S") + ".mp4" # FORMAT: HOUR-MINUTE-TIME
VIDEO_CODEC = cv2.VideoWriter_fourcc(*'MPEG')
CAMERA = cv2.VideoCapture(1)

# Capture Settings
CAMERA.set(3, 1280)
CAMERA.set(4, 720)
CAMERA.set(5, 30)
CAMERA.set(6, fourcc)

# Define Output Location
out = cv2.VideoWriter(VIDEO_OUTPUT, VIDEO_CODEC, VIDEO_FPS, (VIDEO_WIDTH, VIDEO_HEIGHT))


while CAMERA.isOpened():
    # retrieve the frame being sent.
    # ret = true if frame is retrieved, false if not
    # frame = frame read
    ret, frame = CAMERA.read()

    if ret:
        # outputs frame to file
        out.write(frame)

        # Shows frame on screen
        cv2.imshow('frame',frame)

        # Press c to end code
        if cv2.waitKey(1) == ord('c'):
            break
    else:
        break


CAMERA.release()
out.release()
cv2.destroyAllWindows()
