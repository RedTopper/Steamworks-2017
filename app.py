from flask import Flask, render_template, Response, redirect, url_for
from camera import Camera
import cv2
import time
from record import Record

recorder = Record()


app = Flask(__name__)

@app.route('/')
def index():
    
    return render_template('index.html')

def gen(num):
    global recorder
    while True:

        frame = recorder.get_curr_frame(num)

        success, jpeg = cv2.imencode('.jpg', frame)
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + jpeg.tobytes() + b'\r\n\r\n')

# Defines code for the /video_feed path
@app.route('/video_feed')
def video_feed():
    global cam1
    return Response(gen(1),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

# Defines code for the /front_feed path
@app.route('/front_feed')
def front_feed():
    global cam2
    return Response(gen(2),
            mimetype='multipart/x-mixed-replace; boundary=frame')

@app.route('/camera_recording_process_change')
def camera_recording_process_change():
    global recorder
    recorder.end()
    return redirect(url_for('index'))

# More server code, do not touch                    
if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=False)
