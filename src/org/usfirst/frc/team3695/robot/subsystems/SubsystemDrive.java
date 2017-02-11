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
	private CANTalon left1;
	private CANTalon left2;
    private CANTalon right1;
    private CANTalon right2;
    private RobotDrive roboDrive;

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
}

