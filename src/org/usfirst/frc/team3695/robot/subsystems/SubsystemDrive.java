package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.commands.CommandDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemDrive extends Subsystem {
	
	/**
	 * Convert internal measures to Rotations about Axle
	 * (For use with an encoder that outputs 20 Pulses/Channel with a TalonSRX (outputs 4x) Such as AM-3314(A))
	 * Multiply by 'Builtin' Velocity Units to get Rot. at encoder/second
	 * <table summary="Dimensional Analysis">
	 * 	<tr>
	 * 		<td>1 BuiltIn</td><td>1 Pulse</td><td>1 Channel</td><td>1 Rotation</td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td>0.1 Sec</td><td>4 BuiltIns</td><td>20 Pulses</td><td>2 Channels</td>
	 * 	</tr>
	 * </table>
	 * (Multiply by 10 to convert to full seconds)
	 */
	public static final double ROTATION_SCALAR = (1.0/16.0);
	/**
	 * Data for a CIMple Box Gear Box (4.67:1 ~= 14/3:)
	 * Multiply Internal Rotation to get External Rotation
	 */
	public static final double MOTOR_GEAR_BOX_RATIO = 3.0/14.0;
	
	
	private CANTalon left1;
	private CANTalon left2;
    private CANTalon right1;
    private CANTalon right2;
    private RobotDrive roboDrive;
    
    private double maxVelocity = 0.0;
    
    public void initDefaultCommand() {
    	setDefaultCommand(new CommandDrive());
    }
    
    /**
     * Initialize Needed Drivetrain Variables
     */
    public SubsystemDrive(){
    	//Master Talons
    	left1 = new CANTalon(Constants.LEFT_MOTOR);
    	right1 = new CANTalon(Constants.RIGHT_MOTOR);
    	//Slave Talons
    	left2 = new CANTalon(Constants.OTHER_LEFT_MOTOR);
    	right2 = new CANTalon(Constants.OTHER_RIGHT_MOTOR);
    	//Train the Masters
    	left1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	right1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	left1.setEncPosition(0);
    	right1.setEncPosition(0);
    	//Train the Slaves
    	left2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	left2.set(left1.getDeviceID());
    	right2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	right2.set(right1.getDeviceID());
    	//Robot Drive Train
    	roboDrive = new RobotDrive(left1,right1);
    }
    
    public void tankDrive(double left, double right) {
    	roboDrive.tankDrive(left, right);
    }
    
    public void dualStickDrive(Joystick joy){
    	double add = joy.getRawAxis(2) - joy.getRawAxis(3);//Adds in linear driving
    	double left = joy.getRawAxis(1) + add;
    	double right = joy.getRawAxis(5) + add;
    	tankDrive(left, right);
    }
    
    /**
     * @deprecated
     * @param joy
     */
    public void motorTest(Joystick joy){
    	left1.set(joy.getZ());;
    }
    
    public double getLeftVelocity(){
    	if (Math.abs(left1.getEncVelocity()) > maxVelocity)
    		maxVelocity = left1.getEncVelocity();
    	return left1.getEncVelocity();
    }
    
    public double getRightVelocity(){
    	if (Math.abs(right1.getEncVelocity()) > maxVelocity)
    		maxVelocity = right1.getEncVelocity();
    	return right1.getEncVelocity();
    }
    
    public double getMaxRPMS(){
    	return maxVelocity;
    }
}

