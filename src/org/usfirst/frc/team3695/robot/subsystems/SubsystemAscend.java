package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.commands.ManualCommandAscend;
import org.usfirst.frc.team3695.robot.util.Xbox;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Ascends the robot using the velcro-rope auto attachment system.
 */
public class SubsystemAscend extends Subsystem {
	CANTalon climberMotor;
	
	public SubsystemAscend(){
		climberMotor = new CANTalon(Constants.CLIMBER_MOTOR);
		climberMotor.configPeakOutputVoltage(12f, -12f);
		climberMotor.configNominalOutputVoltage(0f, -0f);
		climberMotor.EnableCurrentLimit(true);
		climberMotor.setCurrentLimit(50);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new ManualCommandAscend());
    }
	
	public void climb(Joystick joy){
		climberMotor.set((Constants.ASCENDER_MOTOR_INVERT ? -1.0 : 1.0 ) * Xbox.RT(joy) * Constants.ASCENDER_LIMIT);		
	}
}

