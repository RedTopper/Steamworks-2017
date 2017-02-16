package org.usfirst.frc.team3695.robot.util;

import edu.wpi.first.wpilibj.Preferences;

public class Util {

	static Preferences pref = Preferences.getInstance();
	
	public static double getAndSetDouble(String key, double backup) {
		if(!pref.containsKey(key)) pref.putDouble(key, backup);
		return pref.getDouble(key, backup);
	}
}
