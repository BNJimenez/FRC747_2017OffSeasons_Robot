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

    private static final double SPEED_PERCENT_VBUS = 0.5;
    
    private static final double GEAR_TRANSFER_SLOW_POSITION_ONE = 8.8;
    private static final double GEAR_TRANSFER_SLOW_POSITION_TWO = 8.85;
    private static final double GEAR_TRANSFER_SLOW_POSITION_THREE = 8.9;
    private static final double GEAR_TRANSFER_SLOW_POSITION_FOUR = 8.925;
    private static final double GEAR_TRANSFER_SLOW_POSITION_FINAL_ONE = 8.9475;

    private static final double GEAR_TRANSFER_SLOW_SPEED_ONE = 0.3;
    private static final double GEAR_TRANSFER_SLOW_SPEED_TWO = 0.25;
    private static final double GEAR_TRANSFER_SLOW_SPEED_THREE = 0.225;
    private static final double GEAR_TRANSFER_SLOW_SPEED_FOUR = 0.2;
    private static final double GEAR_TRANSFER_SLOW_SPEED_FINAL_ONE = 0;

    
    
    private static final double GEAR_TRANSFER_SLOW_SPEED_FIVE = 0.3;
    private static final double GEAR_TRANSFER_SLOW_SPEED_SIX = 0.25;
    private static final double GEAR_TRANSFER_SLOW_SPEED_SEVEN = 0.225;
    private static final double GEAR_TRANSFER_SLOW_SPEED_EIGHT = 0.2;
    private static final double GEAR_TRANSFER_SLOW_SPEED_FINAL_TWO = 0;

    private static final double GEAR_TRANSFER_SLOW_POSITION_FIVE = 0.179;
    private static final double GEAR_TRANSFER_SLOW_POSITION_SIX = 0.129;
    private static final double GEAR_TRANSFER_SLOW_POSITION_SEVEN = 0.079;
    private static final double GEAR_TRANSFER_SLOW_POSITION_EIGHT = 0.054;
    private static final double GEAR_TRANSFER_SLOW_POSITION_FINAL_TWO = 0.0315;
    
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
        
        //logitech pull back is positive, push forward is negative
        
    	 double gearTransferDrive = OI.CONTROLLER_OPERATOR.getRawAxis(DriverStation.GamePad.AXIS_LEFT_Y.getValue());
         
    	 if (Math.abs(gearTransferDrive) < 0.1 || (Robot.GEAR_MECH.talonGearTransfer.getPosition() > GEAR_TRANSFER_SLOW_POSITION_FINAL_ONE && gearTransferDrive > 0)) {
    	     gearTransferDrive = GEAR_TRANSFER_SLOW_SPEED_FINAL_ONE;
    	 } else if (Robot.GEAR_MECH.talonGearTransfer.getPosition() > GEAR_TRANSFER_SLOW_POSITION_FOUR && gearTransferDrive > 0) {
             gearTransferDrive = GEAR_TRANSFER_SLOW_SPEED_FOUR;
    	 } else if (Robot.GEAR_MECH.talonGearTransfer.getPosition() > GEAR_TRANSFER_SLOW_POSITION_THREE && gearTransferDrive > 0) {
             gearTransferDrive = GEAR_TRANSFER_SLOW_SPEED_THREE;
    	 } else if (Robot.GEAR_MECH.talonGearTransfer.getPosition() > GEAR_TRANSFER_SLOW_POSITION_TWO && gearTransferDrive > 0) {
             gearTransferDrive = GEAR_TRANSFER_SLOW_SPEED_TWO;
    	 } else if (Robot.GEAR_MECH.talonGearTransfer.getPosition() > GEAR_TRANSFER_SLOW_POSITION_ONE && gearTransferDrive > 0) {
    	     gearTransferDrive = GEAR_TRANSFER_SLOW_SPEED_ONE;
    	 }
    	 
    	 if (Math.abs(gearTransferDrive) < 0.1 || (Robot.GEAR_MECH.talonGearTransfer.getPosition() < GEAR_TRANSFER_SLOW_POSITION_FINAL_TWO && gearTransferDrive > 0)) {
             gearTransferDrive = GEAR_TRANSFER_SLOW_SPEED_FINAL_TWO;
         } else if (Robot.GEAR_MECH.talonGearTransfer.getPosition() < GEAR_TRANSFER_SLOW_POSITION_EIGHT && gearTransferDrive < 0) {
             gearTransferDrive = GEAR_TRANSFER_SLOW_SPEED_EIGHT;
         } else if (Robot.GEAR_MECH.talonGearTransfer.getPosition() < GEAR_TRANSFER_SLOW_POSITION_SEVEN && gearTransferDrive < 0) {
             gearTransferDrive = GEAR_TRANSFER_SLOW_SPEED_SEVEN;
         } else if (Robot.GEAR_MECH.talonGearTransfer.getPosition() < GEAR_TRANSFER_SLOW_POSITION_SIX && gearTransferDrive < 0) {
             gearTransferDrive = GEAR_TRANSFER_SLOW_SPEED_SIX;
         } else if (Robot.GEAR_MECH.talonGearTransfer.getPosition() < GEAR_TRANSFER_SLOW_POSITION_FIVE && gearTransferDrive < 0) {
             gearTransferDrive = GEAR_TRANSFER_SLOW_SPEED_FIVE;
         }

    	 Robot.GEAR_MECH.talonGearTransfer.set(gearTransferDrive * SPEED_PERCENT_VBUS);         
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
