package org.usfirst.frc.team3695.robot.enumeration;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;

public enum Camera {
	FRONT(0, "Front Camera"), REAR(1, "Rear Camera");
	
	public static final int	WIDTH = 320;
	public static final int	HEIGHT = 240;
	public static final int	FPS = 30;
	
	private UsbCamera usb;
	private CvSink sink;
	
	private Camera(int dev, String name) {
		this.usb = new UsbCamera(name, dev);
		this.usb.setResolution(WIDTH, HEIGHT);
		this.usb.setFPS(FPS);
		this.usb.setPixelFormat(PixelFormat.kMJPEG);
		this.sink = new CvSink(name + " CvSink");
		this.sink.setSource(this.usb);
		this.sink.setEnabled(false);
	}
	
	public UsbCamera getUSB() {
		return usb;
	}
	
	public CvSink getSink() {
		return sink;
	}
}
