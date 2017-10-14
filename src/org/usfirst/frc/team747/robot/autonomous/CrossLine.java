package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team747.robot.commands.GearTransferHomingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLine extends CommandGroup {

    public CrossLine() {
        
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.GEAR_MECH);
        
        addParallel(new GearTransferHomingCommand());
        addSequential(new DriveDistanceCommand(95, 0.5));

    }
}
