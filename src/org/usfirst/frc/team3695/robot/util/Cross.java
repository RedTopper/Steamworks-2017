package org.usfirst.frc.team3695.robot.util;

import org.opencv.core.Point;

public class Cross {
	private double x,y;
	private boolean enabled = false;
	public final String name;

	public Cross(String name, double x, double y) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point getPoint() {
		return new Point(x, y);
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean enabled() {
		return enabled;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
