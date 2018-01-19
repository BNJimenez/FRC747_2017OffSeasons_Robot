package org.usfirst.frc.team747.robot.subsystems;

import java.io.IOException;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.DriveCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
    public TalonSRX talonDriveLeftPrimary = new TalonSRX(RobotMap.DriveTrain.LEFT_FRONT.getValue()),
            		talonDriveLeftMid = new TalonSRX(RobotMap.DriveTrain.LEFT_MIDDLE.getValue()),
            		talonDriveLeftBack = new TalonSRX(RobotMap.DriveTrain.LEFT_REAR.getValue()),
            		talonDriveRightPrimary = new TalonSRX(RobotMap.DriveTrain.RIGHT_FRONT.getValue()),
            		talonDriveRightMid = new TalonSRX(RobotMap.DriveTrain.RIGHT_MIDDLE.getValue()),
            		talonDriveRightBack = new TalonSRX(RobotMap.DriveTrain.RIGHT_REAR.getValue());

    private static final int pidIdx = 0;
    private static final int timeoutMs = 10;
    
    private static final double ENCODER_TICKS = 4096;
// 4096 for the mag encoders
    private static final double WHEEL_CIRCUMFERNCE = 18.85; //18.875 then was 18.85
    
    private static final double MAX_VOLTAGE = 12;
    private static final double MIN_VOLTAGE = 0;
  
    //Gear Distance IN REVOLUTIONS 3.7125 (needed like another inch or so; trying 3.725
    
    private static final double TICKS_PER_INCH = ENCODER_TICKS / WHEEL_CIRCUMFERNCE;
    
	StringBuilder sb = new StringBuilder();
	int loops = 0;
    
    public DriveSubsystem() {
        super();
        
        this.talonDriveLeftPrimary.setInverted(true);
        this.talonDriveLeftMid.setInverted(true);
        this.talonDriveLeftBack.setInverted(true);
        
        this.talonDriveRightPrimary.setInverted(false);
        this.talonDriveRightMid.setInverted(false);
        this.talonDriveRightBack.setInverted(false);
       
        this.talonDriveLeftMid.set(ControlMode.Follower, talonDriveLeftPrimary.getDeviceID());
        this.talonDriveLeftBack.set(ControlMode.Follower, talonDriveLeftPrimary.getDeviceID());
        this.talonDriveRightMid.set(ControlMode.Follower, talonDriveLeftPrimary.getDeviceID());
        this.talonDriveRightBack.set(ControlMode.Follower, talonDriveLeftPrimary.getDeviceID());
        
        this.talonDriveLeftPrimary.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, pidIdx, timeoutMs);

        this.talonDriveRightPrimary.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, pidIdx, timeoutMs);
        
        this.talonDriveLeftPrimary.config_kF(pidIdx, 0.1489, timeoutMs);
        this.talonDriveRightPrimary.config_kF(pidIdx, 0.1489, timeoutMs);
        
        this.talonDriveLeftPrimary.configMotionCruiseVelocity(269, timeoutMs); //706
        this.talonDriveLeftPrimary.configMotionAcceleration(269, timeoutMs); //706
        this.talonDriveRightPrimary.configMotionCruiseVelocity(269, timeoutMs); //706
        this.talonDriveRightPrimary.configMotionAcceleration(269, timeoutMs); //706
       
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new DriveCommand());
    }
    
    public void set(double left, double right) {
    	
        this.talonDriveLeftPrimary.set(ControlMode.PercentOutput, left);
        this.talonDriveRightPrimary.set(ControlMode.PercentOutput, right);
    }

    public void setPID(double leftRevolutions, double rightRevolutions) {
        this.talonDriveLeftPrimary.set(ControlMode.MotionMagic, leftRevolutions);
        this.talonDriveRightPrimary.set(ControlMode.MotionMagic, rightRevolutions);
    }
    
    public double convertRevsToInches(double revs) {
        return revs * WHEEL_CIRCUMFERNCE;
    }
    
    public double convertInchesToRevs(double inches) {
        return inches / WHEEL_CIRCUMFERNCE;
    }
    
    public double convertRevsToTicks(double revs) {
        return revs * ENCODER_TICKS;
    }
    
    public double convertTicksToRevs(double ticks) {
        return ticks / ENCODER_TICKS;
    }
    
    public double convertInchesToTicks(double inches) {
        return convertRevsToTicks(convertInchesToRevs(inches));
    }
    
    public double convertTicksToInches(double ticks) {
        return convertRevsToInches(convertTicksToRevs(ticks));
    }
    
    public void changeControlMode(ControlMode mode) {
    	this.talonDriveLeftPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	this.talonDriveRightPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
        this.talonDriveLeftPrimary.set(mode, 0);
        this.talonDriveRightPrimary.set(mode, 0);
    }
    
    public void stop() {
        ControlMode mode = this.talonDriveLeftPrimary.getControlMode();

        double left = 0;
        double right = 0;
        
        switch (mode) {
        case MotionMagic:
            left = this.talonDriveLeftPrimary.getSelectedSensorPosition(pidIdx);
            right = this.talonDriveRightPrimary.getSelectedSensorPosition(pidIdx);
            break;
        case PercentOutput:
        case Velocity:
        case Follower:
        default:
            // Values should be 0;
            break;
        }
        
        this.set(left, right);
    }
    
//    public void talonEnableControl() {
//        talonDriveLeftPrimary.enableControl();
//        talonDriveRightPrimary.enableControl();
//    }
//    
//    public void talonDisableControl() {
//        talonDriveLeftPrimary.disableControl();
//        talonDriveRightPrimary.disableControl();
//    }
    
    public void enablePositionControl() {
        this.changeControlMode(ControlMode.MotionMagic);
//        this.talonEnableControl();
    }

    public void enableVBusControl() {
//        this.talonDisableControl();
        this.changeControlMode(ControlMode.PercentOutput);
    }
    
    public void resetLeftEncoder() {
        this.enableVBusControl();
        talonDriveLeftPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void resetRightEncoder() {
        this.enableVBusControl();
        talonDriveRightPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void resetBothEncoders(){
        this.enableVBusControl();
    	this.talonDriveRightPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	this.talonDriveLeftPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
        //get the current encoder position regardless of whether it is the current feedback device
    public double getLeftEncoderPosition() {
        return talonDriveLeftPrimary.getSelectedSensorPosition(pidIdx);
    }
    
    public double getRightEncoderPosition() {
        return talonDriveRightPrimary.getSelectedSensorPosition(pidIdx);
    }

    public double getLeftPosition() {
        return talonDriveLeftPrimary.getSelectedSensorPosition(pidIdx);
    }
        
    public double getRightPosition() {
        return talonDriveRightPrimary.getSelectedSensorPosition(pidIdx);
    }
    
    public double getCombindedEncoderPosition() {
        return (getLeftEncoderPosition() + getRightEncoderPosition()) / 2;
    }
}

