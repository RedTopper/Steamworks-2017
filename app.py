from flask import Flask, render_template, Response, redirect, url_for
from camera import Camera
import cv2
import time

# This is the main application it handles all of the paths as well as what to do with the frames

# Globals. If you need thins in different methods, use this
out = None
recording = False
currFile = None

# Do not touch this
app = Flask(__name__)

# Defines code for the /video_stream path
@app.route('/video_stream')
def index():

    # Sets the page to index.html
    return render_template('index.html')

def gen(camera):
    global out, recording, currFile
    while True:

        # Gets a frame from Camera.py
        frame = camera.get_frame()

        ##recFrame = cv2.resize(frame, (1280,720), interpolation = cv2.INTER_LINEAR)
        # Any OpenCV image editing that is on the video should go here
        ##if recording:
          ##  cv2.putText(frame,"Recording", (15,30), cv2.FONT_HERSHEY_SIMPLEX, 1, [255,255,255], 2)
          ##  cv2.putText(recFrame,time.strftime("%I-%M-%S"), (1100,30), cv2.FONT_HERSHEY_SIMPLEX, 1, [255,255,255], 1)

        cv2.putText(frame,"Score", (15,30), cv2.FONT_HERSHEY_SIMPLEX, 1, [255,255,255], 2)
        # Write the frame to the video
        ##out.write(recFrame)

        # Resize the frame so it can be streamed efficently
        frame = cv2.resize(frame, (640, 480), interpolation = cv2.INTER_LINEAR)

        # If the recording is done, release the file. This is nessisary to not have corrupt video files
        ##if recording is False:
          ##  out.release()

        # Streaming code (MJPG Stream)
        success, jpeg = cv2.imencode('.jpg', frame)
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + jpeg.tobytes() + b'\r\n\r\n')

# Defines code for the /video_feed path
@app.route('/video_feed')
def video_feed():

    # Calls the gen method and defines the camera that will be used. DO NOT TOUCH
    return Response(gen(Camera(0)),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

# Defines code for the /front_feed path
@app.route('/front_feed')
def front_feed():
    
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
    app.run(host='localhost', debug=True)
