package org.usfirst.frc.team3695.robot.vision;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Video;
import org.usfirst.frc.team3695.robot.util.Cross;
import org.usfirst.frc.team3695.robot.util.Logger;

import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;

public class Vision extends Thread {
	
	/**
	 * Camera constant setup setting 
	 */
	public static final int 
			BOT_EXPOSURE = 0,
			BOT_WHITE = 50,
			BOT_BRIGHT = 50,
			MAN_EXPOSURE = 40,
			MAN_BRIGHT = 50,
			MAN_WHITE = 50;
	
	/**
	 * The time it takes, in MS, to switch the camera.
	 */
	public static final long SWITCH_TIME = 200;
	
	/**
	 * Color constant in OpenCV form
	 */
	public static final Scalar 
			BLACK 	= new Scalar(0.0,	0.0,	0.0),
			RED		= new Scalar(0.0,	0.0,	255.0),
			GREEN	= new Scalar(0.0,	255.0,	0.0),
			YELLOW	= new Scalar(0.0,	255.0,	255.0),
			BLUE	= new Scalar(255.0,	0.0,	0.0),
			MAGENTA	= new Scalar(255.0,	0.0,	255.0),
			CYAN	= new Scalar(255.0,	255.0,	0.0),
			WHITE 	= new Scalar(255.0,	255.0,	255.0);
	
	/**
	 * The server name for the stream. Required by FRC to be "unique"
	 */
	private static final String SERVER_NAME = "Stream"; 
	
	/**
	 * Internal list of crosses to be displayed on the video feed with a label.
	 */
	private List<Cross> targets = new CopyOnWriteArrayList<>();
	
	/**
	 * Current camera that is showing video
	 */
	private Camera camera = null;
	private Camera cameraNext = Camera.FRONT;
	
	/**
	 * View of the feed. (viewing what the robot sees or viewing the raw feed)
	 */
	private Video video = null;
	private Video videoNext = Video.RAW;
	
	/**
	 * Utility matrix for showing when there is no feed on a camera
	 */
	private final Mat warn = new Mat(Camera.HEIGHT, Camera.WIDTH, CvType.CV_8UC3);
	
	/**
	 * The time the camera started switching to another camera. Used to display the
	 * "Camera switching" screen.
	 */
	private long timeSwitchStarted = 0l;
	
	/**
	 * Creates a vision thread in the background.
	 */
	public Vision() {
		setDaemon(true);
	}
	
	public void run() {
		Logger.out("Starting camera thread...");
		Mat source = new Mat();
		Mat result;
		
		Logger.out("Starting video...");
		setCamera(Camera.FRONT, Video.RAW);
		
		Logger.out("Starting server...");
		CvSource output = CameraServer.getInstance().putVideo(SERVER_NAME, Camera.WIDTH, Camera.HEIGHT);
		
		Logger.out("Startup complete!");
		
		while(!interrupted()) {
			if(cameraNext != camera || videoNext != video) {
				
				//switch the camera
				if(cameraNext != camera) {
					warn(output, "Switching camera.", "Please wait!");
					if(camera != null) camera.getSink().setEnabled(false);
					warn(output, "Switching camera..", "Please wait!");
					cameraNext.getSink().setEnabled(true);
					warn(output, "Switching camera...", "Please wait!");
					timeSwitchStarted = System.currentTimeMillis();
				}
				
				//Start the camera
				if(videoNext == Video.RAW) initHuman(cameraNext); else initBot(cameraNext);
				
				video = videoNext;
				camera = cameraNext;
			}
			
			//Show the switching image if the camera is switching
			if(System.currentTimeMillis() < timeSwitchStarted + SWITCH_TIME) {
				warn(output, "Switching camera....", "Please wait!");
				continue;
			}
			
			//Check if the camera is actually connected
			if(!camera.getUSB().isConnected() || !camera.getSink().isValid()) {
				warn(output, camera.getUSB().getName() + " is disconnected!", "Check the connection to the camera!");
				continue;
			}
			
			//Grab the frame
			long time = camera.getSink().grabFrame(source);
			
			//If there is an error, show it
			if(time == 0l) {
				warn(output, camera.getUSB().getName() + ": ERROR ", "Code: " + camera.getSink().getError());
				continue;
			}
			
			//Display raw feed if chosen.
			if(video == Video.RAW || video == Video.LOW_EXPOSURE)  {
				drawCross(source);
				Imgproc.putText(source, camera.getUSB().getName(), new Point(0,Camera.HEIGHT - 2), Core.FONT_HERSHEY_PLAIN, 1.0, WHITE);
				output.putFrame(source);
				continue;
			}
			
			//Process with grip if we are not displaying the raw feed.
			synchronized (Robot.GRIP) {
				Robot.GRIP.process(source);
				
				//Switch to correct feed.
				switch(video) {
				case THRESHHOLD:
					result = Robot.GRIP.hslThresholdOutput();
					drawCross(result);
					break;
				default:
					warn(output, video.name(), "This method is not defined.");
					continue;
				}
				output.putFrame(result);
			}
		}
	}

	/**
	 * Switches the camera thread to process the sent camera.
	 * This method is thread-safe.
	 * @param cam the camera to switch the processing to.
	 */
	public synchronized void setCamera(Camera cam, Video video) {
		cameraNext = cam;
		videoNext = video;
	}

	/**
	 * Method used to put crosses on the robot feed.
	 * Do not continuously use the "new" keyword for this method!
	 * Instead, initialize a cross, then add that pointer. Simply modify
	 * that cross to update it on the screen.
	 * This method is thread-safe.
	 * @param cross A pointer to a cross (don't use new here!)
	 */
	public synchronized void putCrosshair(Cross cross) {
		targets.add(cross);
	}

	/**
	 * Sets a no feed message.
	 * WARNING: This method blocks the main thread for about 1 frame (to simulate a 
	 * camera). 
	 * While this may not be wanted, it is needed for DriverStation to "think"
	 * that a camera is still connected.
	 * @param output 
	 * @param name The name of the device that has no feed.
	 * @param debug A message to show to the user for debug info.
	 */
	private void warn(CvSource output, String name, String debug) {
		try {Thread.sleep((long) (1000.0/30.0));} catch(InterruptedException e) {};
		warn.setTo(BLACK);
		Imgproc.putText(warn, name, new Point(0,22), Core.FONT_HERSHEY_PLAIN, 1.0, WHITE);
		Imgproc.putText(warn, debug, new Point(0, 40), Core.FONT_HERSHEY_PLAIN, 1.0, RED);
		output.putFrame(warn);
	}
	
	/**
	 * Initialize a camera
	 */
	private void initBot(Camera cam) {
		cam.getUSB().setExposureManual(BOT_EXPOSURE);
		cam.getUSB().setWhiteBalanceManual(BOT_WHITE);
		cam.getUSB().setBrightness(BOT_BRIGHT);
	}
	
	/**
	 * Initialize the camera, but for a human instead of the robot.
	 * @param cam The camera.
	 */
	private void initHuman(Camera cam) {
		cam.getUSB().setExposureManual(MAN_EXPOSURE);
		cam.getUSB().setWhiteBalanceManual(MAN_WHITE);
		cam.getUSB().setBrightness(MAN_BRIGHT);
	}
	
	/**
	 * Draws all of the crosses in the cross array list to the screen.
	 * @param source The screen matrix to draw to.
	 */
	private void drawCross(Mat source) {
		for(Cross cross : targets) {
			if(cross.enabled()) {
				Point point = cross.getPoint();
				Imgproc.drawMarker(source, point , new Scalar(50,100,150));
				Imgproc.putText(source, cross.name, point, Core.FONT_HERSHEY_PLAIN, 0.5, WHITE);
			}
		}
	}
}
