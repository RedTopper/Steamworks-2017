package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandIntimidate extends Command {

    public CommandIntimidate() {
    	requires(Robot.subsystemBling);
    	requires(Robot.subsystemShooter);
    }

    protected void initialize() {
    }

    protected void execute() {
    	try { Robot.subsystemBling.intimidate(); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.subsystemBling.stopIntimidating();
    }

    protected void interrupted() {
    }
}
