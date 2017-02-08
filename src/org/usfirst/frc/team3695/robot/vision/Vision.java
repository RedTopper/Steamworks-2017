package org.usfirst.frc.team3695.robot.vision;

import org.opencv.core.Mat;
import org.usfirst.frc.team3695.robot.Grip;
import org.usfirst.frc.team3695.robot.util.Logger;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision extends Thread {
	
	private static final String SERVER_NAME = "Team3695 CvSink"; 
	private final UsbCamera cam0 = new UsbCamera("Front Camera", Camera.FRONT.dev);
	private final UsbCamera cam1 = new UsbCamera("Rear Camera", Camera.REAR.dev);
	private final CvSink sink = new CvSink(SERVER_NAME);
	private final Grip grip = new Grip();
	
	public static final int 
			EXPOSURE = 0,
			WHITE = 50,
			BRIGHT = 50,
			FPS = 30,
			WIDTH = 640,
			HEIGHT = 480;
	
	/**
	 * Initialize the camera stuff
	 */
	private void init(UsbCamera cam) {
		if(cam.isConnected()) {
			Logger.out("> " + cam.getName() + " has come online!");
		} else {
			Logger.err("> " + cam.getName() + " is offline! Reason: disconnected");
			return;
		}
		
		Logger.debug("BRIGHTNESS " + cam.getBrightness());
		cam.setExposureManual(EXPOSURE);
		cam.setWhiteBalanceManual(WHITE);
		cam.setBrightness(BRIGHT);
		cam.setResolution(WIDTH, HEIGHT);
		cam.setFPS(FPS);
	}
	
	/**
	 * Switches the camera thread to process the sent camera.
	 * @param cam the camera to switch the processing to.
	 */
	private void camera(UsbCamera cam) {
		if(cam.isConnected()) {
			Logger.out("> Switched the stream to " + cam.getName() + "!");
		} else {
			Logger.err("> Cannot switch stream to " + cam.getName() + "! Reason: disconnected");
			return;
		}
		sink.setSource(cam);
	}

	public void run() {
		Logger.out("Setting camera settings...");
		init(cam0);
		init(cam1);
		
		Logger.out("Setting video...");
		camera(cam0);
		
		Logger.out("Setting server settings...");
		CvSource output = CameraServer.getInstance().putVideo(SERVER_NAME, WIDTH, HEIGHT);
		
		Logger.out("Setup complete!");
		Mat source = new Mat();
		
		while(!interrupted()) {
			sink.grabFrame(source);
			grip.process(source);
			output.putFrame(grip.blurOutput());
		}
	}
}
