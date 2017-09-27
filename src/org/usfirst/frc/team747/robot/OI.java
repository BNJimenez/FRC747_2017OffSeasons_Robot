package org.usfirst.frc.team747.robot;

import org.usfirst.frc.team747.robot.commands.*;
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
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	public OI() {
//		BUTTON_GEAR_INTAKE.whileHeld(new GrabGearCommand());
		BUTTON_GEAR_DEPLOY.whileHeld(new SpitOutGearCommand());
		
		
	}

	
}
