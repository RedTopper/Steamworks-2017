package org.usfirst.frc.team3695.robot.vision;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.enumeration.Camera;
import org.usfirst.frc.team3695.robot.enumeration.Video;
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
			MAN_EXPOSURE = 50,
			MAN_BRIGHT = 50,
			MAN_WHITE = 50,
			CAM_FPS = 30,
			CAM_WIDTH = 320,
			CAM_HEIGHT = 240;
	
	public static final long CAM_SWITCH_TIME = 2500;
	
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
	private final Mat warn = new Mat(CAM_HEIGHT, CAM_WIDTH, CvType.CV_8UC3);
	
	private long timeSwitchStarted = 0l;
	
	/**
	 * Sets a no feed message.
	 * @param output 
	 * @param name The name of the device that has no feed.
	 * @param debug A message to show to the user for debug info.
	 */
	private void warn(CvSource output, String name, String debug) {
		warn.setTo(BLACK);
		Imgproc.putText(warn, name, new Point(0,22), Core.FONT_HERSHEY_PLAIN, 1.0, WHITE);
		Imgproc.putText(warn, debug, new Point(0, 40), Core.FONT_HERSHEY_PLAIN, 1.0, RED);
		output.putFrame(warn);
	}
	
	/**
	 * Initialize a camera
	 */
	private void initBot(Camera cam) {
		cam.usb.setExposureManual(BOT_EXPOSURE);
		cam.usb.setWhiteBalanceManual(BOT_WHITE);
		cam.usb.setBrightness(BOT_BRIGHT);
		cam.usb.setResolution(CAM_WIDTH, CAM_HEIGHT);
		cam.usb.setFPS(CAM_FPS);
	}
	
	private void initHuman(Camera cam) {
		cam.usb.setExposureManual(MAN_EXPOSURE);
		cam.usb.setWhiteBalanceManual(MAN_WHITE);
		cam.usb.setBrightness(MAN_BRIGHT);
		cam.usb.setResolution(CAM_WIDTH, CAM_HEIGHT);
		cam.usb.setFPS(CAM_FPS);
	}
	
	/**
	 * Switches the camera thread to process the sent camera.
	 * @param cam the camera to switch the processing to.
	 */
	public synchronized void setCamera(Camera cam, Video video) {
		cameraNext = cam;
		videoNext = video;
	}
	

	public void run() {
		Logger.out("Starting camera thread...");
		Mat source = new Mat();
		Mat result;
		
		Logger.out("Starting video...");
		setCamera(Camera.FRONT, Video.RAW);
		
		Logger.out("Starting server...");
		CvSource output = CameraServer.getInstance().putVideo(SERVER_NAME, CAM_WIDTH, CAM_HEIGHT);
		
		Logger.out("Startup complete!");
		
		while(!interrupted()) {
			if(cameraNext != camera || videoNext != video) {
				if(camera != null) camera.sink.setEnabled(false);
				if(cameraNext != camera) timeSwitchStarted = System.currentTimeMillis();
				
				//Start the camera
				if(videoNext == Video.RAW) initHuman(cameraNext); else initBot(cameraNext);
				
				video = videoNext;
				camera = cameraNext;
				camera.sink.setEnabled(true);
			}
			
			//Show the switching image if the camera is switching
			if(System.currentTimeMillis() < timeSwitchStarted + CAM_SWITCH_TIME) {
				warn(output, "Switching camera...", "Please wait!" + camera.sink.getError());
				continue;
			}
			
			if(!camera.usb.isConnected() || !camera.sink.isValid()) {
				warn(output, camera.usb.getName() + " is disconnected!", "Check the connection to the camera!");
				continue;
			}
			
			//Grab the frame
			long time = camera.sink.grabFrame(source);
			
			//If there is an error, show it
			if(time == 0l) {
				warn(output, camera.usb.getName() + ": ERROR ", "Code: " + camera.sink.getError());
				continue;
			}
			
			Imgproc.putText(source, camera.usb.getName(), new Point(0,22), Core.FONT_HERSHEY_PLAIN, 1.0, WHITE);
			
			//Display raw feed if chosen.
			if(video == Video.RAW || video == Video.LOW_EXPOSURE)  {
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
					Imgproc.drawMarker(result, new Point(100,10), new Scalar(50,100,150));
					break;
				default:
					warn(output, video.name(), "This method is not defined.");
					continue;
				}
				output.putFrame(result);
			}
		}
	}
}
