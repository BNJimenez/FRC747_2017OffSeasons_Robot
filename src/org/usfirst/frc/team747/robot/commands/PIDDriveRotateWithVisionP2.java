package org.usfirst.frc.team747.robot.commands;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

@SuppressWarnings("deprecation")
public class PIDDriveRotateWithVisionP2 extends Command {
	static NetworkTable table = NetworkTable.getTable("limelight");
	static double tv;
	static double tx;
	public PIDDriveRotateWithVisionP2() {
		
	}

	public static void searchForCube() throws InterruptedException {
	while(true) {
		tv = table.getNumber("tv", 0);
	
		tx = table.getNumber("tx", 0);
		
	
		if (tv == 0) {
			//new PIDDriveRotateCommand(27);
		} else if(tv == 1) {
			new PIDDriveRotateCommand(tx);
		}
		
		TimeUnit.SECONDS.sleep(3);
	}
	}


	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
