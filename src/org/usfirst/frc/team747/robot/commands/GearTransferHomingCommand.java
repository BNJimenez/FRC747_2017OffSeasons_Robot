package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearTransferHomingCommand extends Command {

    public GearTransferHomingCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.GEAR_MECH);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.GEAR_MECH.enableVBusControl();
//        Robot.GEAR_MECH.talonGearTransfer.set(-0.2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //A test with the motor running with a positive input, made the gear transfer
        //move towards the back of the robot, so to move forward use a negative input
        Robot.GEAR_MECH.talonGearTransfer.set(-0.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gearHomeLimitSwitch.get();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.GEAR_MECH.stop();
        Robot.GEAR_MECH.talonGearTransfer.setPosition(2.3232421875);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
