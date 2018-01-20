package org.usfirst.frc.team747.robot;

import org.usfirst.frc.team747.robot.commands.*;
//import org.usfirst.frc.team747.robot.maps.AutonomousConfig;
import org.usfirst.frc.team747.robot.maps.DriverStation;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

	
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	 public static final Joystick 
	 //Joysticks control both climb and drive
		JOYSTICK_DRIVER_LEFT = new Joystick(DriverStation.Controller.DRIVER_LEFT.getValue()),
		JOYSTICK_DRIVER_RIGHT = new Joystick(DriverStation.Controller.DRIVER_RIGHT.getValue()),
		CONTROLLER_OPERATOR = new Joystick(DriverStation.Controller.OPERATOR.getValue());
	 
	 public static final JoystickButton
	 	BUTTON_GEAR_INTAKE 
	 		= new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_LB.getValue()),
	 	BUTTON_GEAR_PICK_UP_POSITION
	 	    = new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_A.getValue()),
	 	BUTTON_GEAR_HOME_POSITION
	 	    = new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_Y.getValue()),
	 	BUTTON_GEAR_SCORE_POSITION
	 	    = new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_B.getValue()),
	 	BUTTON_GEAR_TRANSFER_ENCODER_RESET
	 	    = new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_START.getValue()),
	 	BUTTON_GEAR_HOMING_BUTTON
	 	    = new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_BACK.getValue());
	 
	 public static final JoystickButton 
	     BUTTON_PID_TEST_BUTTON_ONE
	         = new JoystickButton(JOYSTICK_DRIVER_LEFT, DriverStation.Joystick.BUTTON_3.getValue()),
	     BUTTON_PID_TEST_BUTTON_TWO
	         = new JoystickButton(JOYSTICK_DRIVER_LEFT, DriverStation.Joystick.BUTTON_4.getValue()),
	     BUTTON_PID_TEST_BUTTON_THREE
	         = new JoystickButton(JOYSTICK_DRIVER_LEFT, DriverStation.Joystick.BUTTON_5.getValue()),
	     BUTTON_PID_TEST_BUTTON_FOUR
	         = new JoystickButton(JOYSTICK_DRIVER_LEFT, DriverStation.Joystick.BUTTON_6.getValue()),
	     BUTTON_PID_TEST_REVERSE_BUTTON_ONE
	         = new JoystickButton(JOYSTICK_DRIVER_RIGHT, DriverStation.Joystick.BUTTON_3.getValue()),
	     BUTTON_PID_TEST_REVERSE_BUTTON_TWO
	         = new JoystickButton(JOYSTICK_DRIVER_RIGHT, DriverStation.Joystick.BUTTON_4.getValue()),
	     BUTTON_PID_TEST_REVERSE_BUTTON_THREE
	         = new JoystickButton(JOYSTICK_DRIVER_RIGHT, DriverStation.Joystick.BUTTON_5.getValue()),
	     BUTTON_PID_TEST_REVERSE_BUTTON_FOUR
	         = new JoystickButton(JOYSTICK_DRIVER_RIGHT, DriverStation.Joystick.BUTTON_6.getValue());
	 
    static Preferences prefs;
    
	public OI() {
//		BUTTON_GEAR_INTAKE.whileHeld(new SuckInGearCommand());
//		BUTTON_GEAR_DEPLOY.whileHeld(new SpitOutGearCommand());
//        BUTTON_GEAR_PICK_UP_POSITION.whenPressed(new GearTransferPIDRevolutionsCommand(ValueConfig.PIDGearTransfer.PICK_UP_POSITION)); //8.910400380625
//        BUTTON_GEAR_HOME_POSITION.whenPressed(new GearTransferPIDRevolutionsCommand(ValueConfig.PIDGearTransfer.HOME_POSITION));
//        BUTTON_GEAR_SCORE_POSITION.whenPressed(new GearTransferPIDRevolutionsCommand(ValueConfig.PIDGearTransfer.SCORE_POSITION));
//        BUTTON_GEAR_TRANSFER_ENCODER_RESET.whileHeld(new GearTransferEncoderReset());
//        BUTTON_GEAR_HOMING_BUTTON.whileHeld(new GearTransferHomingCommand());
        
      BUTTON_PID_TEST_BUTTON_ONE.toggleWhenPressed(new PIDDriveRotateCommand(5));
      BUTTON_PID_TEST_BUTTON_TWO.toggleWhenPressed(new PIDDriveRotateCommand(5));
//      BUTTON_PID_TEST_REVERSE_BUTTON_ONE.toggleWhenPressed();
//        BUTTON_PID_TEST_BUTTON_ONE.toggleWhenPressed(new PIDDriveRevolutionsCommand(10, false));
//        BUTTON_PID_TEST_BUTTON_TWO.toggleWhenPressed(new PIDDriveRevolutionsCommand(20, false));
//        BUTTON_PID_TEST_BUTTON_THREE.toggleWhenPressed(new PIDDriveRevolutionsCommand(30, false));
//        BUTTON_PID_TEST_BUTTON_FOUR.toggleWhenPressed(new PIDDriveRevolutionsCommand(40, false));
//
//        BUTTON_PID_TEST_REVERSE_BUTTON_ONE.toggleWhenPressed(new PIDDriveRevolutionsCommand(10, true));
//        BUTTON_PID_TEST_REVERSE_BUTTON_TWO.toggleWhenPressed(new PIDDriveRevolutionsCommand(20, true));
//        BUTTON_PID_TEST_REVERSE_BUTTON_THREE.toggleWhenPressed(new PIDDriveRevolutionsCommand(30, true));
//        BUTTON_PID_TEST_REVERSE_BUTTON_FOUR.toggleWhenPressed(new PIDDriveRevolutionsCommand(40, true));
      
	new Notifier(() -> updateOI()).startPeriodic(0.100); //value in seconds
	}
	
	public static boolean getGearDeployButton() {
	    return CONTROLLER_OPERATOR.getRawAxis(DriverStation.GamePad.TRIGGER_LEFT.getValue())
	                >= 0.5;
	}
	
	public void updateOI() {
	    SmartDashboard.putBoolean("Gear Pick Up Limit:", Robot.gearPickUpLimitSwitch.get());
	    SmartDashboard.putBoolean("Gear Home Limit:", Robot.gearHomeLimitSwitch.get());
	    SmartDashboard.putBoolean("Gear Score Limit:", Robot.gearScoreLimitSwitch.get());
		SmartDashboard.putNumber("Left Encoder Position:", Robot.DRIVE_TRAIN.getLeftEncoderPosition());
		SmartDashboard.putNumber("Right Encoder Position:", Robot.DRIVE_TRAIN.getRightEncoderPosition());
		//SmartDashboard.putNumber("Left Encoder Position:", Robot.DRIVE_TRAIN.getLeftEncoderPosition() * 4);
		//SmartDashboard.putNumber("Right Encoder Position:", Robot.DRIVE_TRAIN.getRightEncoderPosition() * 4);
		//SmartDashboard.putNumber("Left Position (Revolutions):", Robot.DRIVE_TRAIN.getLeftPosition() * 4);
		//SmartDashboard.putNumber("Right Position (Revolutions):", Robot.DRIVE_TRAIN.getRightPosition() * 4);
		SmartDashboard.putNumber("Left Position (Inches):", Robot.DRIVE_TRAIN.convertRevsToInches(Robot.DRIVE_TRAIN.getLeftPosition()));
		SmartDashboard.putNumber("Right Position (Inches):", Robot.DRIVE_TRAIN.convertRevsToInches(Robot.DRIVE_TRAIN.getRightPosition()));
//		SmartDashboard.putNumber("NavX Angle:", Robot.getNavXAngle());
//		SmartDashboard.putNumber("Gear Transfer Position:", Robot.GEAR_MECH.getGearTransferPosition());
		//SmartDashboard.putNumber("Distance to Boiler Target:", Robot.getCVDistance(Robot.VISION_TRACKING_REAR, "BOILER"));
		//SmartDashboard.putNumber("Degrees to Boiler Target:", Robot.getCVAngle(Robot.VISION_TRACKING_REAR, "BOILER"));
//		SmartDashboard.putNumber("Distance to Target:", Robot.getCVDistance(Robot.VISION_TRACKING_REAR, "GEAR"));
//		SmartDashboard.putNumber("Degrees to Target:", (Robot.getCVAngle(Robot.VISION_TRACKING_REAR, "GEAR")));
		
	}
}


