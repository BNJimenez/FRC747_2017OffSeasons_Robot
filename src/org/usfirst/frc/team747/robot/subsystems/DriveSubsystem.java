package org.usfirst.frc.team747.robot.subsystems;

import java.io.IOException;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.DriveCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
	private static final double DEFAULT_DISTANCE_PRECISION = 1; // Inches
    private static final double DEFAULT_ANGLE_PRECISION = Math.toRadians(5); // Change to Radians
    private static final double DEFAULT_DISTANCE_VARIANCE = 2;
    private static final double DEFAULT_ANGLE_VARIANCE = Math.toRadians(10); // Change to Radians
    
    public CANTalon talonDriveLeftPrimary = new CANTalon(RobotMap.DriveTrain.LEFT_FRONT.getValue()),
            		talonDriveLeftMid = new CANTalon(RobotMap.DriveTrain.LEFT_MIDDLE.getValue()),
            		talonDriveLeftBack = new CANTalon(RobotMap.DriveTrain.LEFT_REAR.getValue()),
            		talonDriveRightPrimary = new CANTalon(RobotMap.DriveTrain.RIGHT_FRONT.getValue()),
            		talonDriveRightMid = new CANTalon(RobotMap.DriveTrain.RIGHT_MIDDLE.getValue()),
            		talonDriveRightBack = new CANTalon(RobotMap.DriveTrain.RIGHT_REAR.getValue());
    
    private static final double ENCODER_TICKS = 128;
//  250 for peanut, 128 for competition 
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
        
        this.talonDriveLeftPrimary.reverseSensor(true);
        this.talonDriveRightPrimary.reverseSensor(false);    
        
//        this.talonDriveLeftMid.reverseSensor(true);
//        this.talonDriveRightMid.reverseSensor(false);     
//        
//        this.talonDriveLeftBack.reverseSensor(true);
//        this.talonDriveRightBack.reverseSensor(false);     
       
        this.talonDriveLeftMid.changeControlMode(CANTalon.TalonControlMode.Follower);
        this.talonDriveLeftMid.set(this.talonDriveLeftPrimary.getDeviceID());
       
        this.talonDriveLeftBack.changeControlMode(CANTalon.TalonControlMode.Follower);
        this.talonDriveLeftBack.set(this.talonDriveLeftPrimary.getDeviceID());
        
        this.talonDriveRightMid.changeControlMode(CANTalon.TalonControlMode.Follower);
        this.talonDriveRightMid.set(this.talonDriveRightPrimary.getDeviceID());
        
        this.talonDriveRightBack.changeControlMode(CANTalon.TalonControlMode.Follower);
        this.talonDriveRightBack.set(this.talonDriveRightPrimary.getDeviceID());
        
//        talonDriveLeftPrimary.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//        talonDriveRightPrimary.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        
//        talonDriveLeftPrimary.configEncoderCodesPerRev((int) ENCODER_TICKS);
//        talonDriveRightPrimary.configEncoderCodesPerRev((int) ENCODER_TICKS);

//        talonDriveLeftMid.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//        talonDriveRightMid.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//        
//        talonDriveLeftMid.configEncoderCodesPerRev((int) ENCODER_TICKS);
//        talonDriveRightMid.configEncoderCodesPerRev((int) ENCODER_TICKS);
        
        
//        talonDriveLeftBack.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//        talonDriveRightBack.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//        
//        talonDriveLeftBack.configEncoderCodesPerRev((int) ENCODER_TICKS);
//        talonDriveRightBack.configEncoderCodesPerRev((int) ENCODER_TICKS);
        
        this.talonDriveLeftPrimary.reverseOutput(true);
        this.talonDriveRightPrimary.reverseOutput(false);
       
//        this.talonDriveLeftMid.reverseOutput(true);
//        this.talonDriveRightMid.reverseOutput(false);
       
//        this.talonDriveLeftBack.reverseOutput(true);
//        this.talonDriveRightBack.reverseOutput(false);
    }
    public void initDefaultCommand() {
        this.setDefaultCommand(new DriveCommand());
    }
    public void set(double left, double right) {
    	
    	System.out.println("Left: "+left+" Right: "+right);
    	
        this.talonDriveLeftPrimary.set(left);
        this.talonDriveRightPrimary.set(right);
        
        System.out.println("---VALUE--- Left: "+talonDriveLeftPrimary.get() + " Right: "+ talonDriveRightPrimary.get());
       
//        this.talonDriveLeftMid.set(left);
//        this.talonDriveRightMid.set(right);
       
//        this.talonDriveLeftBack.set(left);
//        this.talonDriveRightBack.set(right);
        
    }
    
    public void changeControlMode(TalonControlMode mode) {
        this.talonDriveLeftPrimary.changeControlMode(mode);
        this.talonDriveRightPrimary.changeControlMode(mode);
//        
//        this.talonDriveLeftMid.changeControlMode(mode);
//        this.talonDriveRightMid.changeControlMode(mode);
      
//        this.talonDriveLeftBack.changeControlMode(mode);
//        this.talonDriveRightBack.changeControlMode(mode);
    }
    public void stop() {
    }/*
        TalonControlMode mode = this.talonDriveLeftPrimary.getControlMode();

        double left = 0;
        double right = 0;
        
        switch (mode) {
        case Position:
            left = this.talonDriveLeftPrimary.getPosition();
            right = this.talonDriveRightPrimary.getPosition();
//            left = this.talonDriveLeftMid.getPosition();
//            right = this.talonDriveRightMid.getPosition();
//            left = this.talonDriveLeftBack.getPosition();
//            right = this.talonDriveRightBack.getPosition();
            break;
        case PercentVbus:
        case Speed:
        case Voltage:
        default:
            // Values should be 0;
            break;
        }
        
        this.set(left, right);
    }*/
}

