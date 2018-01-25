package org.usfirst.frc.team747.robot.commands;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

@SuppressWarnings("deprecation")
public class PIDDriveRotateWithVisionP2 extends Command {
	static NetworkTable table = NetworkTable.getTable("limelight");
	static double tv;
	private static boolean bswitch = true;
	static double tx;
	public PIDDriveRotateWithVisionP2() {
		
	}

	public static double searchForCube() {
		tv = table.getNumber("tv", 0);
		if(bswitch) {
			if(tv == 1) {
				tx = table.getNumber("tx", 0);
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
