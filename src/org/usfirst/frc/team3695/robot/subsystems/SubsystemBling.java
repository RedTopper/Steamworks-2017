package org.usfirst.frc.team3695.robot.subsystems;

import com.ctre.CANTalon;

import java.util.Random;

import org.usfirst.frc.team3695.robot.Constants;

public class SubsystemBling {
	boolean intimidation;
	CANTalon shooterMotor;
	
	public SubsystemBling() {
		intimidation = false;
		shooterMotor = new CANTalon(Constants.SHOOTER_MOTOR);
	}
	
	/*
	 * Revs shooter motor to intimidate other teams whilst
	 * moving the robot
	 * 
	 * Replaces freshman.out.print("ROBOT!");
	 */
	public void intimidate() throws InterruptedException {
		Random interval = new Random();
		while (intimidation) {
			Thread.sleep(interval.nextInt(1000));
			int mode = interval.nextInt(3);
			if (mode == 0) {
				// short pulse
				for (int i = 1; i <= 100; i++) { shooterMotor.set(((i/10)^2)/100); Thread.sleep(7); }
				for (int i = 100; i > 0; i++) { shooterMotor.set(((i/10)^2)/100); Thread.sleep(4); }
			}
			else if (mode == 1) {
				// long pulse
				for (int i = 1; i <= 100; i++) { shooterMotor.set(((i/10)^2)/100); Thread.sleep(5); }
				Thread.sleep(500);
				for (int i = 100; i > 0; i++) { shooterMotor.set(((i/10)^2)/100); Thread.sleep(5); }
			}
			else {
				// double short pulse
				for (int i = 1; i <= 100; i++) { shooterMotor.set(((i/10)^2)/100); Thread.sleep(3); }
				for (int i = 100; i > 0; i++) { shooterMotor.set(((i/10)^2)/100); Thread.sleep(2); }
				
				for (int i = 1; i <= 100; i++) { shooterMotor.set(((i/10)^2)/100); Thread.sleep(4); }
				for (int i = 100; i > 0; i++) { shooterMotor.set(((i/10)^2)/100); Thread.sleep(2); }
			}
		}
	}
}
