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
	
    public CANTalon talonDriveLeftPrimary = new CANTalon(RobotMap.DriveTrain.LEFT_FRONT.getValue()),
            		talonDriveLeftMid = new CANTalon(RobotMap.DriveTrain.LEFT_MIDDLE.getValue()),
            		talonDriveLeftBack = new CANTalon(RobotMap.DriveTrain.LEFT_REAR.getValue()),
            		talonDriveRightPrimary = new CANTalon(RobotMap.DriveTrain.RIGHT_FRONT.getValue()),
            		talonDriveRightMid = new CANTalon(RobotMap.DriveTrain.RIGHT_MIDDLE.getValue()),
            		talonDriveRightBack = new CANTalon(RobotMap.DriveTrain.RIGHT_REAR.getValue());
    
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
        
        this.talonDriveLeftPrimary.reverseSensor(true);
        this.talonDriveRightPrimary.reverseSensor(false);       
       
        this.talonDriveLeftMid.changeControlMode(CANTalon.TalonControlMode.Follower);
        this.talonDriveLeftMid.set(this.talonDriveLeftPrimary.getDeviceID());
       
        this.talonDriveLeftBack.changeControlMode(CANTalon.TalonControlMode.Follower);
        this.talonDriveLeftBack.set(this.talonDriveLeftPrimary.getDeviceID());
        
        this.talonDriveRightMid.changeControlMode(CANTalon.TalonControlMode.Follower);
        this.talonDriveRightMid.set(this.talonDriveRightPrimary.getDeviceID());
        
        this.talonDriveRightBack.changeControlMode(CANTalon.TalonControlMode.Follower);
        this.talonDriveRightBack.set(this.talonDriveRightPrimary.getDeviceID());
        
        this.talonDriveLeftPrimary.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        this.talonDriveRightPrimary.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        
        this.talonDriveLeftPrimary.reverseOutput(true);
        this.talonDriveRightPrimary.reverseOutput(false);
        
        this.talonDriveLeftPrimary.setF(0.1489);
        this.talonDriveRightPrimary.setF(0.1489);
        
        this.talonDriveLeftPrimary.setMotionMagicCruiseVelocity(269); //706
        this.talonDriveLeftPrimary.setMotionMagicAcceleration(269); //706
        this.talonDriveRightPrimary.setMotionMagicCruiseVelocity(269); //706
        this.talonDriveRightPrimary.setMotionMagicAcceleration(269); //706
       
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new DriveCommand());
    }
    
    public void set(double left, double right) {
    	
    	System.out.println("Left: "+left+" Right: "+right);
    	
        this.talonDriveLeftPrimary.set(left);
        this.talonDriveRightPrimary.set(right);
        
        System.out.println("---VALUE--- Left: "+talonDriveLeftPrimary.get() + " Right: "+ talonDriveRightPrimary.get());
    }

    public void setPID(double leftRevolutions, double rightRevolutions) {
        this.talonDriveLeftPrimary.set(leftRevolutions);
        this.talonDriveRightPrimary.set(rightRevolutions);
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
    
    public void changeControlMode(TalonControlMode mode) {
        this.talonDriveLeftPrimary.changeControlMode(mode);
        this.talonDriveRightPrimary.changeControlMode(mode);
    }
    
    public void stop() {
        TalonControlMode mode = this.talonDriveLeftPrimary.getControlMode();

        double left = 0;
        double right = 0;
        
        switch (mode) {
        case Position:
            left = this.talonDriveLeftPrimary.getPosition();
            right = this.talonDriveRightPrimary.getPosition();
            break;
        case PercentVbus:
        case Speed:
        case Voltage:
        default:
            // Values should be 0;
            break;
        }
        
        this.set(left, right);
    }
    
    public void talonEnableControl() {
        talonDriveLeftPrimary.enableControl();
        talonDriveRightPrimary.enableControl();
    }
    
    public void talonDisableControl() {
        talonDriveLeftPrimary.disableControl();
        talonDriveRightPrimary.disableControl();
    }
    
    public void enablePositionControl() {
        this.changeControlMode(TalonControlMode.MotionMagic);
//        this.talonEnableControl();
    }

    public void enableVBusControl() {
//        this.talonDisableControl();
        this.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    public void resetLeftEncoder() {
        this.enableVBusControl();
        talonDriveLeftPrimary.setPosition(0);
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void resetRightEncoder() {
        this.enableVBusControl();
        talonDriveRightPrimary.setPosition(0);
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void resetBothEncoders(){
        this.enableVBusControl();
    	this.talonDriveRightPrimary.setEncPosition(0);
    	this.talonDriveLeftPrimary.setEncPosition(0);
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
        //get the current encoder position regardless of whether it is the current feedback device
    public double getLeftEncoderPosition() {
        return talonDriveLeftPrimary.getEncPosition();
    }
    
    public double getRightEncoderPosition() {
        return talonDriveRightPrimary.getEncPosition();
    }
    //	
    public double getLeftPosition() {
        return talonDriveLeftPrimary.getPosition();
    }
        
    public double getRightPosition() {
        return talonDriveRightPrimary.getPosition();
    }
    
    public double getCombindedEncoderPosition() {
        return (getLeftEncoderPosition() + getRightEncoderPosition()) / 2;
    }
}

