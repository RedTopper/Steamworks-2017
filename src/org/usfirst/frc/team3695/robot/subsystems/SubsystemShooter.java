package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.commands.CommandShooter;
import org.usfirst.frc.team3695.robot.util.Util;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
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
	
    public void initDefaultCommand() {
        setDefaultCommand(new CommandShooter());
    }
    
    public void spin(double speed){
    	shooterMotor.set((Constants.SHOOTER_MOTOR_INVERT ? -1.0 : 1.0) * speed);
    	if (Math.abs(speed) == Constants.SHOOTER_SPEED){
    		window1.set((Constants.WINDOW1_INVERT ? -1.0 : 1.0) * Constants.AGITATOR_SPEED);
    		window2.set((Constants.WINDOW2_INVERT ? -1.0 : 1.0) * Constants.AGITATOR_SPEED);
    	} else {
    		window1.set(0.0);
    		window2.set(0.0);
    	}
    }
    
    public void spin(Joystick joy){
    	double spinSpeed = Util.getAndSetDouble("Shooter Speed", Constants.SHOOTER_SPEED);//Constants.SHOOTER_SPEED
    	if (joy.getRawButton(1))
    		spin(spinSpeed);
    	else if (joy.getRawButton(2))
    		spin(-1.0 * spinSpeed);
    	else
    		spin(0.0);
    }
}

