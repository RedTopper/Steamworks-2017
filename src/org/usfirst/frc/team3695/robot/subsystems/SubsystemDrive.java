package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.commands.ManualCommandDrive;
import org.usfirst.frc.team3695.robot.util.TalonPID;
import org.usfirst.frc.team3695.robot.util.Xbox;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive the robot
 */
public class SubsystemDrive extends Subsystem {
	
	double lastPosition = 0.0,
		   distance = 0.0;
	
	/**
	 * The maximum RPM that the drivers are allowed to drive at. 
	 * With an 8 in diameter wheel, and if this is set to 5, that would convert
	 * to 40.0 * Math.PI in / second, or about 10.47 feet per second.
	 */
	public static final double MAX_RPM = 600;
	
	/**
	 * Diameter of the wheel in inches
	 */
	public static final double WHEEL_DIAM_INCHES = 8;
	
	private CANTalon left1;
	private CANTalon left2;
    private CANTalon right1;
    private CANTalon right2;
    private TalonPID leftPID;
    private TalonPID rightPID;

    public void initDefaultCommand() {
    	setDefaultCommand(new ManualCommandDrive());
    }
    
    /** Converts RPM from the magnetic encoder to inches per second **/
    public static final double rpm2ips(double rpm) {
    	return rpm / 60.0 * WHEEL_DIAM_INCHES * Math.PI;
    }
    
    /** Converts an inches per second number to RPM */
    public static final double ips2rpm(double ips) {
    	return ips * 60.0 / WHEEL_DIAM_INCHES / Math.PI;
    }
    
    public static final double rot2in(double rot) {
    	return rot * WHEEL_DIAM_INCHES * Math.PI;
    }
    
    public static final double in2rot(double in) {
    	return in / WHEEL_DIAM_INCHES / Math.PI;
    }
    
    /**
     * Initialize Needed Drive-train Variables
     */
    public SubsystemDrive(){
    	//Master Talons
    	left1 = new CANTalon(Constants.LEFT_MOTOR);
    	right1 = new CANTalon(Constants.RIGHT_MOTOR);
    	leftPID = new TalonPID(left1, "LEFT");
    	rightPID = new TalonPID(right1, "RIGHT");
    	left1.changeControlMode(TalonControlMode.Speed);
    	right1.changeControlMode(TalonControlMode.Speed);
    	
    	//Slave Talons
    	left2 = new CANTalon(Constants.OTHER_LEFT_MOTOR);
    	right2 = new CANTalon(Constants.OTHER_RIGHT_MOTOR);
    	right2.reverseOutput(Constants.COLTON_RIGHT_MOTOR_IS_BACKWARD);
    	
    	//Train the Masters
    	left1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
    	right1.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
    	left1.setEncPosition(0);
    	right1.setEncPosition(0);
    	left1.reverseSensor(false);
    	right1.reverseSensor(false);
    	
    	//Train the Slaves
    	left2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	right2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	left2.set(left1.getDeviceID());
    	right2.set(right1.getDeviceID());
    }

	public void dualStickDrive(Joystick joy){
		leftPID.update();
		rightPID.update();
		
    	double adder = Xbox.LT(joy) - Xbox.RT(joy);
    	double left = adder - (Xbox.LEFT_X(joy) / 2);
    	double right = adder + (Xbox.LEFT_X(joy) / 2);
    	
    	left1.set(left * MAX_RPM * (Constants.LEFT_MOTOR_INVERT ? -1.0 : 1.0));
    	right1.set(right * MAX_RPM  * (Constants.RIGHT_MOTOR_INVERT ? -1.0 : 1.0));
    	
    	vel();
    }
    
    private void vel() {
    	double position = (left1.getPosition() * (Constants.LEFT_MOTOR_INVERT ? -1.0 : 1.0)
    				   +  right1.getPosition() * (Constants.RIGHT_MOTOR_INVERT ? -1.0 : 1.0)) / 2.0; 
    	distance += Math.abs(position - lastPosition);
    	lastPosition = position;
		SmartDashboard.putNumber("Speed", rpm2ips(Math.abs((left1.getSpeed() + right1.getSpeed()) / 2.0)));
		SmartDashboard.putNumber("Distance", rot2in(distance));
	}

	public void directDrive(double left, double right) {
    	left1.set(left * (Constants.LEFT_MOTOR_INVERT ? -1.0 : 1.0));
    	right1.set(right  * (Constants.RIGHT_MOTOR_INVERT ? -1.0 : 1.0));
    	vel();
    }
}

