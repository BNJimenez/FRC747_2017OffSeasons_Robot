
package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class GearMechMove extends Command {
	

    
	private double rotationAmount;
	private double gearP;
	private double gearI;
	private double gearD;
	
	
	private final static double ENCODER_COMPENSATION_VALUE = 1;

    private static final double MAX_VOLTAGE = 1;
    private static final double MIN_VOLTAGE = 0;
    
    private final static double STOP_THRESHOLD_REAL = .25; //3.0;
    private final static double STOP_THRESHOLD_ADJUSTED = Robot.DRIVE_TRAIN.convertInchesToRevs(STOP_THRESHOLD_REAL / ENCODER_COMPENSATION_VALUE);

	public GearMechMove(double rotations, double P, double I, double D) {
	    requires(Robot.GEAR_MECH);
	      
	    this.rotationAmount = rotations / ENCODER_COMPENSATION_VALUE;
		this.gearP = P;
		this.gearI = I;
		this.gearD = D;
	}
	
    protected void initialize() {
    	Robot.GEAR_MECH.enablePositionControl();
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double mechPosition = Robot.GEAR_MECH.getMechPosition();
    	if ((mechPosition > rotationAmount - STOP_THRESHOLD_ADJUSTED ) && (mechPosition < rotationAmount + STOP_THRESHOLD_ADJUSTED)) {
    		
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.GEAR_MECH.DoNothing();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
