package org.usfirst.frc.team747.robot.commands;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

public class PIDDriveRotateWithVisionP2 extends Command {
	static NetworkTableInstance table = NetworkTableInstance.getDefault();
	static double tv;
	private static boolean bswitch = true;
	static double tx;
	public PIDDriveRotateWithVisionP2() {
		
	}

	public static double searchForCube() {
		tv = table.getEntry("tv").getDouble(0);
		if(bswitch) {
			if(tv == 1) {
				tx = table.getEntry("tx").getDouble(0);
				bswitch = false;
			}
			
		} 
		
		if(!bswitch) {
			return tx;
		}
		
		if(!bswitch) {
			if(tv == 0) {
				bswitch = true;
			}
		}
		return 0;
	}


	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
