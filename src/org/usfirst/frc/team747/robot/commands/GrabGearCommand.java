
package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GrabGearCommand extends Command {
	

	private double mechRevolutions;
	private double gearP;
	private double gearI;
	private double gearD;
	
	private final static double ENCODER_COMPENSATION_VALUE = 1;

    private static final double MAX_VOLTAGE = 12;
    private static final double MIN_VOLTAGE = 1.9;
	
    public GrabGearCommand(double revolutions, double P, double I, double D) {
        requires(Robot.GEAR_MECH);
        this.gearP = P;
        this.gearI = I;
        this.gearD = D;
        

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.GEAR_MECH.GrabGear();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
