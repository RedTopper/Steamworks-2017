import cv2
import numpy as np
import time
import shutil
import os

# This class handles all of the camera capture, and returns frames from the capture using the get_frame method

class Camera(object):
    def __init__(self, num):
        # Define video capture device
        self.video = cv2.VideoCapture(num)

        # Define video capture settings

        self.video.set(3, 640)
        self.video.set(4, 480)
        self.video.set(5, 30)
        self.video.set(6, cv2.VideoWriter_fourcc(*'MPEG'))
        
    def __del__(self):
        # So it does not complain
        self.video.release()

    def get_frame(self):
        #Read image and flip
        success, image = self.video.read()
        image = cv2.flip(image, 1)
        
        return image

