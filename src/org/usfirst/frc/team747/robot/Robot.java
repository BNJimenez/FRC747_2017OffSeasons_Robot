
package org.usfirst.frc.team747.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.usfirst.frc.team747.robot.commands.DriveForwardAutoCommandGroup;
import org.usfirst.frc.team747.robot.commands.GearTransferHomingCommand;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team747.robot.subsystems.GearTransferSubsystem;
import org.usfirst.frc.team747.robot.subsystems.IntakeSubsystem;
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
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import com.ctre.CANTalon.TalonControlMode;
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
    public static final GearTransferSubsystem GEAR_MECH = new GearTransferSubsystem();
    public static final IntakeSubsystem INTAKE = new IntakeSubsystem();
    public static File logs, driveLog;
	public static BufferedWriter bw, bwDrive;
	public static FileWriter fw, fwDrive;
	
    public static OI oi = null;
    
    private Command     autonomousCommand;
    private Autonomous  autonomous;
    
    public static DigitalInput gearPickUpLimitSwitch = new DigitalInput(1), gearHomeLimitSwitch = new DigitalInput(2), gearScoreLimitSwitch = new DigitalInput(0);
    
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
        DRIVE_TRAIN.changeControlMode(TalonControlMode.PercentVbus);
        UsbCamera ucamera = CameraServer.getInstance().startAutomaticCapture("cam1", 0);
        ucamera.setResolution(180, 240);

        this.autonomous = new Autonomous();
        
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
        autonomous.startMode();
        if (autonomousCommand != null) {
            autonomousCommand.start();
	    }
        
        if (oi == null) {
            oi = new OI();
        }
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
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
