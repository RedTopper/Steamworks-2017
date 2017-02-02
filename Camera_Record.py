import numpy as np
import cv2
import time
import os
import shutil

# Constants
VIDEO_WIDTH = 1280
VIDEO_HEIGHT = 720
VIDEO_FPS = 30
VIDEO_OUTPUT = time.strftime("%I-%M-%S") + ".mp4"  # FORMAT: HOUR-MINUTE-SECOND
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
        # Rewrites the frame to be correctly displayed
        frame = cv2.flip(frame, 1)

        # outputs frame to file
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

# Moves the video to a Videos folder for organization
dir_path = os.path.dirname(os.path.realpath(__file__))
shutil.move(dir_path + "\\\\" + VIDEO_OUTPUT, dir_path + "\\\\Videos\\\\")