package org.usfirst.frc.team3695.robot.subsystems;

import org.usfirst.frc.team3695.robot.Constants;
import org.usfirst.frc.team3695.robot.commands.CommandAscend;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubsystemAscend extends Subsystem {
	CANTalon climberMotor;
	
	public SubsystemAscend(){
		climberMotor = new CANTalon(Constants.CLIMBER_MOTOR);
	}
	
	public void climb(Joystick joy){
		climberMotor.set(joy.getRawAxis(3));
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CommandAscend());
    }
}

