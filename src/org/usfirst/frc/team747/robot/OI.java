package org.usfirst.frc.team747.robot;

import org.usfirst.frc.team747.robot.commands.*;
//import org.usfirst.frc.team747.robot.maps.AutonomousConfig;
import org.usfirst.frc.team747.robot.maps.DriverStation;
import org.usfirst.frc.team747.robot.maps.RobotMap;


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
		JOYSTICK_DRIVER_LEFT= new Joystick(DriverStation.Controller.DRIVER_LEFT.getValue()),
		JOYSTICK_DRIVER_RIGHT = new Joystick(DriverStation.Controller.DRIVER_RIGHT.getValue()),
		CONTROLLER_OPERATOR = new Joystick(DriverStation.Controller.OPERATOR.getValue());
	 
	 public static final JoystickButton
	 	BUTTON_GEAR_INTAKE 
	 		= new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_LB.getValue()),
	 	BUTTON_GEAR_DEPLOY
	 		= new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_RB.getValue());

		    	
    static Preferences prefs;
    
	public OI() {
		BUTTON_GEAR_INTAKE.whileHeld(new SuckInGearCommand());
//		BUTTON_GEAR_DEPLOY.whileHeld(new SpitOutGearCommand());
			
	new Notifier(() -> updateOI()).startPeriodic(0.100); //value in seconds
	}
	
	public static boolean getGearDeployButton() {
	    return CONTROLLER_OPERATOR.getRawAxis(DriverStation.GamePad.TRIGGER_LEFT.getValue())
	                >= 0.5;
	}
	
	public void updateOI() {
		SmartDashboard.putNumber("Left Encoder Position:", Robot.DRIVE_TRAIN.getLeftEncoderPosition());
		SmartDashboard.putNumber("Right Encoder Position:", Robot.DRIVE_TRAIN.getRightEncoderPosition());
		//SmartDashboard.putNumber("Left Encoder Position:", Robot.DRIVE_TRAIN.getLeftEncoderPosition() * 4);
		//SmartDashboard.putNumber("Right Encoder Position:", Robot.DRIVE_TRAIN.getRightEncoderPosition() * 4);
		//SmartDashboard.putNumber("Left Position (Revolutions):", Robot.DRIVE_TRAIN.getLeftPosition() * 4);
		//SmartDashboard.putNumber("Right Position (Revolutions):", Robot.DRIVE_TRAIN.getRightPosition() * 4);
		SmartDashboard.putNumber("Left Position (Inches):", Robot.DRIVE_TRAIN.convertRevsToInches(Robot.DRIVE_TRAIN.getLeftPosition()));
		SmartDashboard.putNumber("Right Position (Inches):", Robot.DRIVE_TRAIN.convertRevsToInches(Robot.DRIVE_TRAIN.getRightPosition()));
		SmartDashboard.putNumber("NavX Angle:", Robot.getNavXAngle());
		//SmartDashboard.putNumber("Distance to Boiler Target:", Robot.getCVDistance(Robot.VISION_TRACKING_REAR, "BOILER"));
		//SmartDashboard.putNumber("Degrees to Boiler Target:", Robot.getCVAngle(Robot.VISION_TRACKING_REAR, "BOILER"));
//		SmartDashboard.putNumber("Distance to Target:", Robot.getCVDistance(Robot.VISION_TRACKING_REAR, "GEAR"));
//		SmartDashboard.putNumber("Degrees to Target:", (Robot.getCVAngle(Robot.VISION_TRACKING_REAR, "GEAR")));
	}
}


