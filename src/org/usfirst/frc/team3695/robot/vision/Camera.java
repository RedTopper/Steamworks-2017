package org.usfirst.frc.team3695.robot.vision;

import edu.wpi.cscore.UsbCamera;

public enum Camera {
	FRONT(0, "Front Camera"), REAR(1, "Rear Camera");
	
	public final int dev;
	public final UsbCamera usb;
	private Camera(int dev, String name) {
		this.dev = dev;
		this.usb = new UsbCamera(name, dev);
	}
}
