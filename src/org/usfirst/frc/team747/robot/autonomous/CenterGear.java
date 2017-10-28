package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.GearTransferEncoderReset;
import org.usfirst.frc.team747.robot.commands.GearTransferEncoderStartPositionSet;
import org.usfirst.frc.team747.robot.commands.GearTransferHomingCommand;
import org.usfirst.frc.team747.robot.commands.GearTransferPIDRevolutionsCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRevolutionsCommand;
import org.usfirst.frc.team747.robot.commands.PauseCommand;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterGear extends CommandGroup {

    public CenterGear() {
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.GEAR_MECH);
        
        addSequential(new GearTransferEncoderStartPositionSet());

        addParallel(new GearTransferPIDRevolutionsCommand(ValueConfig.PIDGearTransfer.HOME_POSITION));
        addSequential(new PIDDriveRevolutionsCommand(ValueConfig.PIDDriveDistances.FORWARD_TO_CENTER_GEAR_STEP_ONE, false));
        
        addSequential(new PIDDriveRevolutionsCommand((ValueConfig.PIDDriveDistances.FORWARD_TO_CENTER_GEAR_STEP_TWO - 2), false));
        
        addSequential(new GearTransferPIDRevolutionsCommand(ValueConfig.PIDGearTransfer.SCORE_POSITION));
        
        addSequential(new PIDDriveRevolutionsCommand(ValueConfig.PIDDriveDistances.BACK_OFF_FROM_CENTER_GEAR, false));
        
        addSequential(new GearTransferPIDRevolutionsCommand(ValueConfig.PIDGearTransfer.PICK_UP_POSITION));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
