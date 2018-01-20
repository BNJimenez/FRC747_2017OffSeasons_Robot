package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class PIDDriveRotateWithVisionP2 {
	@SuppressWarnings("deprecation")
	static NetworkTable table = NetworkTable.getTable("limelight");
	static double tv;
	static double tx;

	
	@SuppressWarnings("deprecation")
	public static void searchForCube() {
		tv = table.getNumber("tv", 0);
		tx = table.getNumber("tx", 0);
		
	
		if (tv == 0) {
			new PIDDriveRotateCommand(27);
		} else if(tv == 1) {
			if(!(tv <= 3 && tv >= -3)) {
				new PIDDriveRotateCommand(tx);
			}
			System.out.println("Done! Aligned with cube");
		}
		
		
		
	}
}
