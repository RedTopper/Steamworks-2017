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

    
    # Calls the gen method and defines the camera that will be used. DO NOT TOUCH
    return Response(gen(Camera(1)),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

# Defines code for the / path
@app.route('/')
def camera_record():

    # When page is first loaded, redirect to the /camera_recording_process_change path
    return redirect(url_for('camera_recording_process_change'))

# Defines code for the /camera_recording_process_change path
@app.route('/camera_recording_process_change')
def camera_recording_process_change():
    global out, recording, currFile

    # Tag the change in the console
    print ('This function is turned off...')

    ##if recording:

        # Disable recording
      ##  recording = False
      ##  print ('recording turned off')
        
    ##else:

        # Enable recording
      ##  recording = True

        # Set current file global, defines where the video will be saved
      ##  currFile = time.strftime("%I-%M-%d") + ".mp4"

        # Set out global, defines what video to save to
      ##  out = cv2.VideoWriter(currFile, cv2.VideoWriter_fourcc(*'MPEG'), 10, (1280,720))
      ##  print ('recording turned on')

    return redirect(url_for('index'))

# More server code, do not touch                    
if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=False)
