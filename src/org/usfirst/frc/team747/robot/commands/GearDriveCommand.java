package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.DriverStation;

import com.ctre.CANTalon;


import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class GearDriveCommand extends Command {

    public GearDriveCommand() {
        requires(Robot.GEAR_MECH);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	  Robot.GEAR_MECH.enableVBusControl();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //with a negative sign it pulling down on the left joystick would make the gear transfer move towards
        //the front and pushing up would make the gear transfer move towards the back of the robot
        //to counteract this, I removed the negative sign in front of OI
    	 double gearTransferDrive = OI.CONTROLLER_OPERATOR.getRawAxis(DriverStation.GamePad.AXIS_LEFT_Y.getValue());
         
         if (Math.abs(gearTransferDrive) < 0.1) {
             gearTransferDrive = 0;
         } else if (Robot.gearPickUpLimitSwitch.get() && gearTransferDrive <= 0) {
             
         }
         
         double speed = 0.5;
         
         Robot.GEAR_MECH.talonGearTransfer.set(gearTransferDrive * speed);         
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gearScoreLimitSwitch.get() || Robot.gearPickUpLimitSwitch.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.DRIVE_TRAIN.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
