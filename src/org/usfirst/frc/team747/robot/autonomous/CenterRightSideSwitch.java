package org.usfirst.frc.team747.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.maps.ValueConfig;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.PauseCommand;

public class CenterRightSideSwitch extends CommandGroup {
    
    public  CenterRightSideSwitch() {
        
        requires(Robot.DRIVE_TRAIN);

        addSequential(new PIDDriveInchesCommand(90-ValueConfig.MeasurementConstants.ROBOT_LENGTH, true));
        addSequential(new PIDDriveRotateCommand(90));
        addSequential(new PIDDriveInchesCommand(60-ValueConfig.MeasurementConstants.ROBOT_LENGTH/2, true));
        addSequential(new PIDDriveRotateCommand(-90));
        addSequential(new PIDDriveInchesCommand(50, true));
        addSequential(new EjectTimedCommand(true, 5));
    }
}
