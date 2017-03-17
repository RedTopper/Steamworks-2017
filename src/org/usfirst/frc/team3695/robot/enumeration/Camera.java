package org.usfirst.frc.team3695.robot.enumeration;

import org.usfirst.frc.team3695.robot.util.Xbox;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;

public enum Camera {
	FRONT(1, "Front Camera", Xbox.B), REAR(2, "Rear Camera", Xbox.X), GEAR(0, "Gear Camera", Xbox.Y);
	
	public static final int	WIDTH = 320;
	public static final int	HEIGHT = 240;
	public static final int	FPS = 30;
	
	public final int button;
	private UsbCamera usb;
	private CvSink sink;
	
	private Camera(int dev, String name, int button) {
		this.button = button;
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
