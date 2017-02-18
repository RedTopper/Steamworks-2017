from flask import Flask, render_template, Response, redirect, url_for
from camera import Camera
import cv2
import time

cam1 = Camera(0)
cam2 = Camera(1)

app = Flask(__name__)

@app.route('/')
def index():
    
    return render_template('index.html')

def gen(camera):
    while True:

        frame = camera.get_frame()

        success, jpeg = cv2.imencode('.jpg', frame)
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + jpeg.tobytes() + b'\r\n\r\n')

# Defines code for the /video_feed path
@app.route('/video_feed')
def video_feed():
    global cam1
    return Response(gen(cam1),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

# Defines code for the /front_feed path
@app.route('/front_feed')
def front_feed():
    global cam2
    return Response(gen(cam2),
            mimetype='multipart/x-mixed-replace; boundary=frame')

# More server code, do not touch                    
if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=False)
