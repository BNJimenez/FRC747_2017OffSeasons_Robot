package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearTransferEncoderStartPositionSet extends Command {

    public GearTransferEncoderStartPositionSet() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.GEAR_MECH);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.GEAR_MECH.setGearTransferEncoderStartPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.GEAR_MECH.talonGearTransfer.getPosition() == 4.0185546875);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
