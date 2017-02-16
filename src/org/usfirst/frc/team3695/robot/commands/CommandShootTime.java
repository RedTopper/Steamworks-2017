package org.usfirst.frc.team3695.robot.commands;

import org.usfirst.frc.team3695.robot.Robot;

public class CommandShootTime {
	private long startTime = 0;
	private long driveTime = 0;
	private double power = 0.0;
	
	/**
	 * Shoots for a certain amount of time for a certain power.
	 * @param timeToDrive Time in ms to shoot.
	 * @param power The percent power that should shoot.
	 */
	public CommandShootTime(long timeToDrive, double power) {
		driveTime = timeToDrive;
		this.power = power;
	}
	
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	protected void execute() {
		Robot.SUB_SHOOTER.spin(power);
	}

	protected boolean isFinished() {
		long now = System.currentTimeMillis();
		return (now - startTime) > driveTime;
	}

	protected void end() {
		Robot.SUB_SHOOTER.spin(0);
	}

	protected void interrupted() {
		end();
	}
}
