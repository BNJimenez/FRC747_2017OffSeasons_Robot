
package org.usfirst.frc.team747.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateWithVisionP2;
import org.usfirst.frc.team747.robot.maps.DriverStation;
//import org.usfirst.frc.team747.robot.commands.DriveForwardAutoCommandGroup;
//import org.usfirst.frc.team747.robot.commands.GearTransferHomingCommand;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;
//import org.usfirst.frc.team747.robot.subsystems.GearTransferSubsystem;
//import org.usfirst.frc.team747.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team747.robot.vision.Target;
import org.usfirst.frc.team747.robot.vision.VisionTracking;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.DigitalInput;

//import java.util.HashMap;

//import com.ctre.CANTalon.TalonControlMode;
//import com.kauailabs.navx.frc.AHRS;

//import java.io.File;
//import java.time.Instant;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.networktables.NetworkTableInstance;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static final DriveSubsystem DRIVE_TRAIN = new DriveSubsystem();
//    public static final GearTransferSubsystem GEAR_MECH = new GearTransferSubsystem();
//    public static final IntakeSubsystem INTAKE = new IntakeSubsystem();
    public static File logs, driveLog;
	public static BufferedWriter bw, bwDrive;
	public static FileWriter fw, fwDrive;
	public static Joystick joystick = new Joystick(0);
    public static OI oi = null;

	public static NetworkTableInstance table = NetworkTableInstance.getDefault();
	public static double tx;
    
    private Command     autonomousCommand;
//    private Autonomous  autonomous;
    
    public static DigitalInput gearPickUpLimitSwitch = new DigitalInput(1), gearHomeLimitSwitch = new DigitalInput(2), gearScoreLimitSwitch = new DigitalInput(0);

//  SendableChooser<Command> chooser = new SendableChooser<>();
    
    
    
    private static final AHRS NAV_X = new AHRS (SPI.Port.kMXP);
    
    public static double getNavXAngle() {
    	return NAV_X.getYaw();
    }
    
    public static double getNavXAngleRadians() {
    	return Math.toRadians(getNavXAngle());
    }
    
    public static void resetNavXAngle() {
    	NAV_X.zeroYaw();
    }

	
    
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
//	    CameraServer.getInstance().startAutomaticCapture();
//        resetNavXAngle();
        DRIVE_TRAIN.changeControlMode(ControlMode.PercentOutput);
        UsbCamera ucamera = CameraServer.getInstance().startAutomaticCapture("cam1", 0);
        ucamera.setResolution(180, 240);
        autonomousCommand = new PIDDriveRotateWithVisionP2();
//        this.autonomous = new Autonomous();
        
        if (oi == null) {
            oi = new OI();
        }
	}
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		new PIDDriveRotateCommand(90);
//		while(true) {
//		try {
//			PIDDriveRotateWithVisionP2.searchForCube();
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		}
	    //basic autonomous code, without the selector
	    
//	    autonomousCommand = new DriveForwardAutoCommandGroup();
//
        if (autonomousCommand != null) autonomousCommand.start();

//        autonomous.startMode();
       // if (autonomousCommand != null) {
      //      autonomousCommand.start();
	    //}
        
        //if (oi == null) {
       //    oi = new OI();
       // }
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
//		resetNavXAngle();
		Robot.DRIVE_TRAIN.resetBothEncoders();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		tx = table.getEntry("tx").getDouble(0);
		
		if(joystick.getRawButton(3)) {
			new PIDDriveRotateCommand(tx);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
