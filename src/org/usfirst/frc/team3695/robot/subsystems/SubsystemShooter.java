package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.enumeration.Direction;
import org.usfirst.frc.team3695.robot.util.Util;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SubsystemShooter extends Subsystem {
	private CANTalon shooterMotor;
	private CANTalon window1;
	private CANTalon window2;
	private Servo servo;
	private boolean open = false;
	
	long lastSwitch = System.currentTimeMillis();
			
	
	public SubsystemShooter(){
		shooterMotor = new CANTalon(Constants.SHOOTER_MOTOR);
//		shooterMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		shooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		window1 = new CANTalon(Constants.WINDOW_MOTOR1);
		window2 = new CANTalon(Constants.WINDOW_MOTOR2);
		servo = new Servo(Constants.SERVO_PORT);
	}
	
    public void initDefaultCommand() {}
    
	public void spin(Direction direction) {
//		//Quick 'N Dirty PID
//		shooterMotor.setP(Util.getAndSetDouble("PID SHOOT: P", 7.0));
//		shooterMotor.setI(Util.getAndSetDouble("PID SHOOT: I", 0.0));
//		shooterMotor.setD(Util.getAndSetDouble("PID SHOOT: D", 0.0));
    	
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
		
		//BAD AJ. We Need Adjustable Speed
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
	 * Moves the servo open and closed periodically. 
	 */
	public void updateServo(Direction direction) {
	
		//servo flap
		if(direction == Direction.FORWARD) {
			if(lastSwitch + (long)Util.getAndSetDouble("SERVO: TIME", 1000.0) < System.currentTimeMillis()) {
				if(open) 
					servo.setAngle(Util.getAndSetDouble("SERVO: LOW DEG", 0.0)); 
				else 
					servo.setAngle(Util.getAndSetDouble("SERVO: HI DEG", 20.0));
				
				lastSwitch = System.currentTimeMillis();
				open = !open;
				SmartDashboard.putBoolean("SERVO", open);
			}
		} else {
			servo.setAngle(Util.getAndSetDouble("SERVO: HI DEG", 0.0));
		}
	}
}

