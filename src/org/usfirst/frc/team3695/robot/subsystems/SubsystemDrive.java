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
	
	private double lastPosition = 0.0, distance = 0.0;
	private boolean vbusEnable = false;
	
	/**
	 * The maximum RPM that the drivers are allowed to drive at. 
	 * With an 8 in diameter wheel, and if this is set to 5, that would convert
	 * to 40.0 * Math.PI in / second, or about 10.47 feet per second.
	 */
	public static final double MAX_RPM = 400;
	public static final double MAX_RPM_AUTO = 5;
	
	/**
	 * Diameter of the wheel in inches
	 */
	public static final double WHEEL_DIAM_INCHES = 8.1;
	
	/**
	 * Allowable tolerance to be considered in range when driving a distance, in rotations
	 */
	public static final double DISTANCE_ALLOWABLE_ERROR = in2rot(2.0);
	
	/**
	 * Error variables
	 */
	public static final long START_READ_ERROR_DELAY = 500;
	public static final int TARGET = 300;
	
	private CANTalon left1;
	private CANTalon left2;
    private CANTalon right1;
    private CANTalon right2;
    private TalonPID pid;

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
    
    public static final double leftify(double left) {
		return left * (Constants.LEFT_MOTOR_INVERT ? -1.0 : 1.0);
	}

	public static final double rightify(double right) {
		return right * (Constants.RIGHT_MOTOR_INVERT ? -1.0 : 1.0);
	}

	/**
     * Initialize Needed Drive-train Variables
     */
    public SubsystemDrive(){
    	
    	//Master Talons
    	left1 = new CANTalon(Constants.LEFT_MOTOR);
    	right1 = new CANTalon(Constants.RIGHT_MOTOR);
    	
    	//Slave Talons
    	left2 = new CANTalon(Constants.OTHER_LEFT_MOTOR);
    	right2 = new CANTalon(Constants.OTHER_RIGHT_MOTOR);
    	
    	//CHANGE VOLTAGE/AMPErAGE
    	enableSafety(true);
    	enableVbus(false);

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
    	
    	pid = new TalonPID(new CANTalon[]{left1, right1}, "MOTORS");
    }
    
    /**
     * Enables and disables the amperage safety limit of all drive
     * train talons.
     * @param doSafety True to enable safety
     * 
     * This method should NOT be called in a loop.
     */
    public void enableSafety(boolean doSafety){
   		amp(left1,doSafety);   	
       	amp(left2,doSafety);   	
       	amp(right1,doSafety);   	
       	amp(right2,doSafety);
       	SmartDashboard.putBoolean("Safety Enabled", doSafety);
    }

    /**
     * Enables and disables VBus mode.
     * @param vbusEnable True to disable PID for joysticks only,
     * false to use PID
     * 
     * This method should NOT be called in a loop.
     */
	public void enableVbus(boolean vbusEnable) {
		this.vbusEnable = vbusEnable;
		SmartDashboard.putBoolean("PID Enabled", !vbusEnable);
	}

	/**
	 * Drives, but with joysticks.
	 * @param joy A joystick.
	 */
	public void driveJoy(Joystick joy){
		if(vbusEnable) {
			pid.update(TalonControlMode.PercentVbus);
		} else {
			pid.update(TalonControlMode.Speed);
		}
		
    	double adder = Xbox.RT(joy) - Xbox.LT(joy);
    	double left = adder + (Xbox.LEFT_X(joy) / 1.333333);
    	double right = adder - (Xbox.LEFT_X(joy) / 1.333333);
    	
    	//Quick Truncate
    	left = (left > 1.0 ? 1.0 : (left < -1.0 ? -1.0 : left));
    	right = (right > 1.0 ? 1.0 : (right < -1.0 ? -1.0 : right));
    	    	
    	left1.set(leftify(left * (vbusEnable ? 1.0 : MAX_RPM)));
    	right1.set(rightify(right *  (vbusEnable ? 1.0 : MAX_RPM)));
    	
    	log();
    }
    
    public void driveDirect(double left, double right) {
    	pid.update(TalonControlMode.Speed);
    	
		left1.set(leftify(left));
		right1.set(rightify(right));
		
		log();
	}

	public boolean driveDistance(double leftInches, double rightInches) {
		double leftGoal = in2rot(leftInches);
		double rightGoal = in2rot(rightInches);
		
		pid.update(TalonControlMode.MotionMagic);
		left1.set(leftify(leftGoal));
		right1.set(rightify(rightGoal));
		
		boolean leftInRange = 
				left1.getPosition() > leftify(leftGoal) - DISTANCE_ALLOWABLE_ERROR && 
				left1.getPosition() < leftify(leftGoal) + DISTANCE_ALLOWABLE_ERROR;
		boolean rightInRange = 
				right1.getPosition() > rightify(rightGoal) - DISTANCE_ALLOWABLE_ERROR && 
				right1.getPosition() < rightify(rightGoal) + DISTANCE_ALLOWABLE_ERROR;
								
		log();
		return leftInRange && rightInRange;

	}

	public void reset() {
		left1.setPosition(0);
		right1.setPosition(0);
		lastPosition = 0;
		
		log();
	}

	public double getError() {
		return  (leftify(left1.getError()) + rightify(right1.getError())) / 2.0;
	}

	/**
	 * Updates the voltage of a talon.
	 * @param talon The talon to update
	 * @param driveSafely If true, the talon will attempt to limit the
	 * passed amperage to a reasonable value.
	 * If it is false....
	 */
	private void amp(CANTalon talon, boolean driveSafely) {
		talon.configNominalOutputVoltage(0f, 0f);
		talon.configPeakOutputVoltage(12.0f, -12.0f);
		if (driveSafely){
			talon.EnableCurrentLimit(true);
			talon.setCurrentLimit(35);
		} else {
			talon.EnableCurrentLimit(false);
			talon.setCurrentLimit(50);
		}
	}

	private void log() {
    	double position = leftify(left1.getPosition()) + rightify(right1.getPosition()) / 2.0; 
    	distance += Math.abs(position - lastPosition);
    	lastPosition = position;
		SmartDashboard.putNumber("Speed", rpm2ips(Math.abs((leftify(left1.getSpeed()) + rightify(right1.getSpeed())) / 2.0)));
		SmartDashboard.putNumber("Distance", rot2in(distance));
	}
}

