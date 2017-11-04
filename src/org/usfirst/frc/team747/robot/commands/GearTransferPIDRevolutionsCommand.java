
package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class GearTransferPIDRevolutionsCommand extends Command {
	
	private double stopPosition;
//	private double gearP;
//	private double gearI;
//	private double gearD;
	
	
	private final static double ENCODER_COMPENSATION_VALUE = 1;

    private static final double MAX_VOLTAGE = 9;
    private static final double MIN_VOLTAGE = 0;
    
    private final static double STOP_THRESHOLD_REAL_REVOLUTIONS = 0.314941406 / 2;

    private int onTargetCount = 0;

    private double gearTicks = 0;
    
    private final static int TARGET_COUNT_ONE_SECOND = 50;
    
    //Half a second is being multiplied by the user input to achieve the desired "ON_TARGET_COUNT"
    private final static double ON_TARGET_MINIMUM_COUNT = TARGET_COUNT_ONE_SECOND * 0.25;

    public GearTransferPIDRevolutionsCommand(double revolutions) {
	    requires(Robot.GEAR_MECH);
	    
	    gearTicks = revolutions;

	    this.stopPosition = revolutions / ENCODER_COMPENSATION_VALUE;
	}
	
    protected void initialize() {
        
        onTargetCount = 0;
    	Robot.GEAR_MECH.enablePositionControl();
    	
    	Robot.GEAR_MECH.talonGearTransfer.setPID(0.5, 0.0, 0.0);
        Robot.GEAR_MECH.talonGearTransfer.setF(0.0);

        Robot.GEAR_MECH.talonGearTransfer.configNominalOutputVoltage(MIN_VOLTAGE, -MIN_VOLTAGE);
        Robot.GEAR_MECH.talonGearTransfer.configPeakOutputVoltage(MAX_VOLTAGE, -MAX_VOLTAGE);
        Robot.GEAR_MECH.talonGearTransfer.setAllowableClosedLoopErr(0);
        Robot.GEAR_MECH.setGearTransferPID(gearTicks);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double mechPosition = Robot.GEAR_MECH.getGearTransferPosition();
    	
    	if ((mechPosition > stopPosition - STOP_THRESHOLD_REAL_REVOLUTIONS ) && (mechPosition < stopPosition + STOP_THRESHOLD_REAL_REVOLUTIONS)) {
    	    onTargetCount++;
    	}else {
    	    onTargetCount = 0;    	        
    	}
    	
    	return (onTargetCount > ON_TARGET_MINIMUM_COUNT);
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.GEAR_MECH.stop();
        }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
