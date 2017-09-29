
package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class GearMechMovePID extends Command {
	
	private double stopPosition;
//	private double gearP;
//	private double gearI;
//	private double gearD;
	
	
	private final static double ENCODER_COMPENSATION_VALUE = 1;

    private static final double MAX_VOLTAGE = 3;
    private static final double MIN_VOLTAGE = 0;
    
    private final static double STOP_THRESHOLD_REAL = .25; //3.0;
    private final static double STOP_THRESHOLD_ADJUSTED = Robot.DRIVE_TRAIN.convertInchesToRevs(STOP_THRESHOLD_REAL / ENCODER_COMPENSATION_VALUE);

    private int onTargetCount = 0;

    private double gearTicks = 0;
    
    private final static int TARGET_COUNT_ONE_SECOND = 50;
    
    //Half a second is being multiplied by the user input to achieve the desired "ON_TARGET_COUNT"
    private final static double ON_TARGET_MINIMUM_COUNT = TARGET_COUNT_ONE_SECOND * 0.25;

    public GearMechMovePID(double ticks) {
	    requires(Robot.GEAR_MECH);
	    
	    gearTicks = ticks;

	    this.stopPosition = ticks / ENCODER_COMPENSATION_VALUE;
	}
	
    protected void initialize() {
        
        onTargetCount = 0;
        Robot.GEAR_MECH.resetGearTransferEncoder();
    	Robot.GEAR_MECH.enablePositionControl();
    	
    	Robot.GEAR_MECH.talonGearTransfer.setPID(0.1, 0.0, 0.0);
        Robot.GEAR_MECH.talonGearTransfer.setF(0.0);
    	Robot.GEAR_MECH.setGearTransferPID(gearTicks);

        Robot.GEAR_MECH.talonGearTransfer.configNominalOutputVoltage(MIN_VOLTAGE, -MIN_VOLTAGE);
        Robot.GEAR_MECH.talonGearTransfer.configPeakOutputVoltage(MAX_VOLTAGE, -MAX_VOLTAGE);
        Robot.GEAR_MECH.talonGearTransfer.setAllowableClosedLoopErr(0);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double mechPosition = Robot.GEAR_MECH.getGearTransferPosition();
    	
    	if ((mechPosition > stopPosition - STOP_THRESHOLD_ADJUSTED ) && (mechPosition < stopPosition + STOP_THRESHOLD_ADJUSTED)) {
    	    onTargetCount++;
    	}else {
    	    onTargetCount = 0;    	        
    	}
    	
    	return (onTargetCount > ON_TARGET_MINIMUM_COUNT);
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.GEAR_MECH.stop();    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
