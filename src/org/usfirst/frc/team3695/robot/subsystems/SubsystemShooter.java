package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.enumeration.Direction;
import org.usfirst.frc.team3695.robot.enumeration.Feeder;
import org.usfirst.frc.team3695.robot.util.Util;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemShooter extends Subsystem {
	private CANTalon shooterMotor;
	private CANTalon conveyor_L;
	private CANTalon conveyor_R;
	private CANTalon spinner_L;
	private CANTalon spinner_R;
	
	
	long lastSwitch = System.currentTimeMillis();
			
	
	public SubsystemShooter(){
		shooterMotor = new CANTalon(Constants.SHOOTER_MOTOR);
//		shooterMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		shooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		conveyor_L = new CANTalon(Constants.FEEDER_CONVEYOR_LEFT);
		conveyor_R = new CANTalon(Constants.FEEDER_CONVEYOR_RIGHT);
		spinner_L = new CANTalon(Constants.FEEDER_ROTOR_LEFT);
		spinner_R = new CANTalon(Constants.FEEDER_ROTOR_RIGHT);
	}
	
    public void initDefaultCommand() {}
    
	public void spin(Direction direction) {	
		double spinSpeed = Util.getAndSetDouble("Shooter Voltage", Constants.SHOOTER_LIMIT);
		//set the actual shooter
		switch(direction) {
		case FORWARD:
			shooterMotor.set((Constants.SHOOTER_MOTOR_INVERT ? -1.0 : 1.0) * spinSpeed);
			break;
		case BACKWARD:
			shooterMotor.set((Constants.SHOOTER_MOTOR_INVERT ? -1.0 : 1.0) * -spinSpeed);
			break;
		case NONE:
			shooterMotor.set(0.0);
			break;
		}
	}
	
	/**
	 * Simplest implementation of spinner code
	 * @param f Feeder Direction (ENUM)
	 */
	public void feed(Feeder f){
		conveyor_L.set((Constants.FEEDER_CONVEYOR_LEFT_INVERT ? -1.0 : 1.0) * Constants.FEEDER_CONVEYOR_LEFT_LIMIT * (f.equals(Feeder.LEFT) ? 1.0 : 0));
		conveyor_R.set((Constants.FEEDER_CONVEYOR_RIGHT_INVERT ? -1.0 : 1.0) * Constants.FEEDER_CONVEYOR_RIGHT_LIMIT * (f.equals(Feeder.RIGHT) ? 1.0 : 0));
		spinner_L.set((Constants.FEEDER_CONVEYOR_LEFT_INVERT ? -1.0 : 1.0) * Constants.FEEDER_ROTOR_LIMIT * (f.equals(Feeder.LEFT) ? 1.0 : 0));
		spinner_R.set((Constants.FEEDER_ROTOR_RIGHT_INVERT ? -1.0 : 1.0) * Constants.FEEDER_ROTOR_LIMIT * (f.equals(Feeder.RIGHT) ? 1.0 : 0));
	}
}

