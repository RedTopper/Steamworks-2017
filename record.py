import cv2
import numpy
from camera import Camera
import time
import threading

frontCamera = Camera(0)
backCamera = Camera(1)
frontFrame = None
backFrame = None
pause = False
tempPause = False


class Record(object):
    def __init__(self):

        self.frontOut = cv2.VideoWriter(time.strftime("%I-%M-%d") + "-FRONT.mp4", cv2.VideoWriter_fourcc(*'MPEG'), 30, (640,480))
        self.backOut = cv2.VideoWriter(time.strftime("%I-%M-%d") + "-BACK.mp4", cv2.VideoWriter_fourcc(*'MPEG'), 30, (640,480))

        self.thread = threading.Thread(target=self.run, args=())
        self.thread.daemon = True
        self.thread.start()

    def run(self):
        global frontCamera, backCamera, frontFrame, backFrame, pause, tempPause
        while True:
            if pause is False:
                frontFrame = frontCamera.get_frame()
                backFrame = backCamera.get_frame()
                self.frontOut.write(frontFrame)
                self.backOut.write(backFrame)

            pause = tempPause
                
   
    def end(self):
        global tempPause, frontCamera, backCamera, pause
        tempPause = True
        while pause is not tempPause:
            tempPause = tempPause
            
        self.frontOut.release()
        self.backOut.release()
        
        self.frontOut = cv2.VideoWriter(time.strftime("%I-%M-%d") + "-FRONT.mp4", cv2.VideoWriter_fourcc(*'MPEG'), 30, (640,480))
        self.backOut = cv2.VideoWriter(time.strftime("%I-%M-%d") + "-BACK.mp4", cv2.VideoWriter_fourcc(*'MPEG'), 30, (640,480))

        tempPause = False

    def get_curr_frame(self, num):
        global frontFrame, backFrame
        if num == 1:
            return frontFrame
        if num == 2:
            return backFrame
