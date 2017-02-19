package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.enumeration.Direction;
import org.usfirst.frc.team3695.robot.util.Util;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemShooter extends Subsystem {
	CANTalon shooterMotor;
	CANTalon window1;
	CANTalon window2;
	
	public SubsystemShooter(){
		shooterMotor = new CANTalon(Constants.SHOOTER_MOTOR);
		window1 = new CANTalon(Constants.WINDOW_MOTOR1);
		window2 = new CANTalon(Constants.WINDOW_MOTOR2);
	}
	
    public void initDefaultCommand() {}
    
	public void spin(Direction direction) {
		//set the window agitator motors
		switch (direction) {
		case FORWARD:
		case BACKWARD:
			window1.set((Constants.WINDOW1_INVERT ? -1.0 : 1.0) * Constants.AGITATOR_LIMIT);
    		window2.set((Constants.WINDOW2_INVERT ? -1.0 : 1.0) * Constants.AGITATOR_LIMIT);
			break;
		case NONE:
			window1.set(0.0);
    		window2.set(0.0);
			break;
		}
		
		//set the actual shooter
		switch(direction) {
		case FORWARD:
			shooterMotor.set((Constants.SHOOTER_MOTOR_INVERT ? -1.0 : 1.0) * 1.0);
			break;
		case BACKWARD:
			shooterMotor.set((Constants.SHOOTER_MOTOR_INVERT ? -1.0 : 1.0) * -1.0);
			break;
		case NONE:
			shooterMotor.set(0.0);
			break;
		}
	}
}

