package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.Robot;
import org.usfirst.frc.team3695.robot.commands.CommandAscend;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemAscend extends Subsystem {
	CANTalon climberMotor;
	boolean isSafe = true;
	
	public SubsystemAscend(){
		climberMotor = new CANTalon(Constants.CLIMBER_MOTOR);
	}
	
	public void climb(Joystick joy){
		climberMotor.set((Constants.ASCENDER_MOTOR_INVERT ? -1.0 : 1.0 ) * (joy.getRawAxis(3) - joy.getRawAxis(2)) * Constants.ASCENDER_LIMIT);
		//if (Math.abs(joy.getRawAxis(3) - joy.getRawAxis(2)) > 0.5)
			//Robot.subsystemBallHopper.closeFlaps();
		if (Math.abs(joy.getRawAxis(3) - joy.getRawAxis(2)) > 0.5 && !isSafe)
			isSafe = true;
		if (joy.getRawButton(3) && isSafe){
			Robot.SUB_BALL_HOPPER.toggleFlaps();
			isSafe = false;
		}
			
	}

    public void initDefaultCommand() {
        setDefaultCommand(new CommandAscend());
    }
}

