package org.usfirst.frc.team3695.robot.enumeration;

import edu.wpi.first.wpilibj.Joystick;

public class Xbox {
	public static final int
			A = 1,
			B = 2,
			X = 3,
			Y = 4,
			LB = 5,
			RB = 6,
			BACK = 7,
			START = 8,
			LSTICK = 9,
			RSTICK = 10;

	public static double LEFT_X(Joystick joy) {return joy.getRawAxis(0);}
	public static double LEFT_Y(Joystick joy) {return joy.getRawAxis(1);}
	public static double RIGHT_X(Joystick joy) {return joy.getRawAxis(4);}
	public static double RIGHT_Y(Joystick joy) {return joy.getRawAxis(5);}
	public static double LT(Joystick joy) {return joy.getRawAxis(2);}
	public static double RT(Joystick joy) {return joy.getRawAxis(3);}
}
