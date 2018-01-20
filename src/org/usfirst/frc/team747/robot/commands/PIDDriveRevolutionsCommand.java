package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDDriveRevolutionsCommand extends Command {
	
    //execute is called every 20ms and isFinished is called right after execute
    //add a button to Ryan's joystick that will default the drive train back to DriveWithJoystickCommand
    
	private double driveRevolutions;
    private double driveP;
    private double driveI;
    private double driveD;
    
	
	private final static double ENCODER_COMPENSATION_VALUE = 1;

    private static final double MAX_VOLTAGE = 12;
    private static final double MIN_VOLTAGE = 1.9;

    //STOP_THRESHOLD_REAL was 3 inches and is now 8 inches in an attempt to cut back on time
    private final static double STOP_THRESHOLD_REAL = .5; //3.0;
    private final static double STOP_THRESHOLD_ADJUSTED = Robot.DRIVE_TRAIN.convertInchesToRevs(STOP_THRESHOLD_REAL / ENCODER_COMPENSATION_VALUE);
    
    private final static int I_ZONE_IN_REVOLUTIONS = 50; //100;
    
    private int onTargetCount = 0;
    
    private final static int TARGET_COUNT_ONE_SECOND = 50;
    
    //Half a second is being multiplied by the user input to achieve the desired "ON_TARGET_COUNT"
    private final static double ON_TARGET_MINIMUM_COUNT = TARGET_COUNT_ONE_SECOND * 0.25;

	private double specificDistanceInches;
	
	private double specificDistanceP = 1.5;
	
	private double specificDistanceI = 0.08;
	
	private double specificDistanceD = 60;
	
    //the values used for motion magic (universal PID values for driving forward and back
    
//    private final static double FORWARD_TO_SHOOT_DISTANCE = 81.25;
//    private final static double FORWARD_TO_SHOOT_P = 1.5;
//    private final static double FORWARD_TO_SHOOT_I = 0.01;
//    private final static double FORWARD_TO_SHOOT_D = 60;
    

	
	public PIDDriveRevolutionsCommand(double inches, boolean reverse) {
	    requires(Robot.DRIVE_TRAIN);
	      
//	    this.driveRevolutions = inches / ENCODER_COMPENSATION_VALUE;
	
	    if (reverse) {
	        this.driveRevolutions = -Robot.DRIVE_TRAIN.convertInchesToRevs(inches / ENCODER_COMPENSATION_VALUE);
	    } else {
	        this.driveRevolutions = Robot.DRIVE_TRAIN.convertInchesToRevs(inches / ENCODER_COMPENSATION_VALUE);
	    }
	    this.driveP = specificDistanceP;
	    this.driveI = specificDistanceI;
	    this.driveD = specificDistanceD;
	}
	
		
	protected void initialize() {
	    
//	    SmartDashboard.putString("specificDistanceName:", specificDistanceName);
	    
	    onTargetCount = 0;
	    
	    Robot.DRIVE_TRAIN.resetBothEncoders();
//	    Robot.resetNavXAngle();
        Robot.DRIVE_TRAIN.enablePositionControl();
        
        
        /*
         * April 20th: Brian - Comfortable PID values that we found are P = 3, I = 0, and D = 950.
         * Testing done with George and Corey, we dropped using I. Still need to test the use of
         * different drive distances. April 20th (end of the night): Brian - after testing shorter
         * distances, particularly 25 inches, we found that the drive train does not arrive at the
         * desired location.
         */
        
        Robot.DRIVE_TRAIN.talonDriveLeftPrimary.setPID(driveP, driveI, driveD);
        Robot.DRIVE_TRAIN.talonDriveRightPrimary.setPID(driveP, driveI, driveD);
        Robot.DRIVE_TRAIN.talonDriveLeftPrimary.setF(0.1);
        Robot.DRIVE_TRAIN.talonDriveRightPrimary.setF(0.1);

//        Robot.DRIVE_TRAIN.talonDriveLeftPrimary.ClearIaccum();
//        Robot.DRIVE_TRAIN.talonDriveRightPrimary.ClearIaccum();
        
        Robot.DRIVE_TRAIN.talonDriveLeftPrimary.configNominalOutputVoltage(+MIN_VOLTAGE,-MIN_VOLTAGE);
        Robot.DRIVE_TRAIN.talonDriveLeftPrimary.configPeakOutputVoltage(+MAX_VOLTAGE, -MAX_VOLTAGE);
        Robot.DRIVE_TRAIN.talonDriveRightPrimary.configNominalOutputVoltage(+MIN_VOLTAGE,-MIN_VOLTAGE);
        Robot.DRIVE_TRAIN.talonDriveRightPrimary.configPeakOutputVoltage(+MAX_VOLTAGE, -MAX_VOLTAGE);
        
//        Robot.DRIVE_TRAIN.talonDriveLeftPrimary.setCloseLoopRampRate(rampRate);
//        Robot.DRIVE_TRAIN.talonDriveRightPrimary.setCloseLoopRampRate(rampRate);
        
        Robot.DRIVE_TRAIN.talonDriveLeftPrimary.setAllowableClosedLoopErr(1); //was 6
        Robot.DRIVE_TRAIN.talonDriveRightPrimary.setAllowableClosedLoopErr(1); //was 6
        
        Robot.DRIVE_TRAIN.talonDriveLeftPrimary.setIZone(I_ZONE_IN_REVOLUTIONS);
        Robot.DRIVE_TRAIN.talonDriveRightPrimary.setIZone(I_ZONE_IN_REVOLUTIONS);

        Robot.DRIVE_TRAIN.setPID(driveRevolutions, -driveRevolutions);
	}
	
	protected void execute() {
//	    SmartDashboard.putNumber("STOP THRESHOLD:", Robot.DRIVE_TRAIN.convertRevsToInches(STOP_THRESHOLD_ADJUSTED));
//	    SmartDashboard.putNumber("Closed-Loop Error Left:", Robot.DRIVE_TRAIN.talonDriveLeftPrimary.getClosedLoopError());
//      SmartDashboard.putNumber("Closed-Loop Error Right:", Robot.DRIVE_TRAIN.talonDriveRightPrimary.getClosedLoopError());
//	    SmartDashboard.putNumber("I Accum Left:", Robot.DRIVE_TRAIN.talonDriveLeftPrimary.GetIaccum());
//      SmartDashboard.putNumber("I Accum Right:", Robot.DRIVE_TRAIN.talonDriveRightPrimary.GetIaccum());
        
//        IAccumDistanceTraveled = Robot.DRIVE_TRAIN.convertRevsToInches((Robot.DRIVE_TRAIN.getRightPosition() + Robot.DRIVE_TRAIN.getLeftPosition()) * 4);
//	    
//	    if ((Math.abs(IAccumDistanceTraveled) - Math.abs(IAccumDistanceCounter)) >= Math.abs(I_ACCUM_RESET_BENCHMARK_IN_INCHES)) {
//	        IAccumDistanceCounter = IAccumDistanceTraveled;
//	        Robot.DRIVE_TRAIN.talonDriveLeftPrimary.ClearIaccum();
//	        Robot.DRIVE_TRAIN.talonDriveRightPrimary.ClearIaccum();
//	    }
//	    
//	    if ((Math.abs(IAccumDistanceTraveled) >= Math.abs(driveRevolutions) && !firstPass)) {
//	        firstPass = true;
//            Robot.DRIVE_TRAIN.talonDriveLeftPrimary.ClearIaccum();
//            Robot.DRIVE_TRAIN.talonDriveRightPrimary.ClearIaccum();
//	    }
	    
	}
	
	@Override
	protected boolean isFinished() {
		double leftPosition = Robot.DRIVE_TRAIN.getLeftPosition();
		double rightPosition = -Robot.DRIVE_TRAIN.getRightPosition();
		
		if (leftPosition > (driveRevolutions - STOP_THRESHOLD_ADJUSTED) && leftPosition < (driveRevolutions + STOP_THRESHOLD_ADJUSTED) &&
		    rightPosition > (driveRevolutions - STOP_THRESHOLD_ADJUSTED) && rightPosition < (driveRevolutions + STOP_THRESHOLD_ADJUSTED)) {
		    onTargetCount++;
		} else {
		    onTargetCount = 0;
		}
		
		return (onTargetCount > ON_TARGET_MINIMUM_COUNT);
	}
	
	protected void end() {
        System.out.println("LEFT Drive Distance: Inches" + Robot.DRIVE_TRAIN.convertRevsToInches(Robot.DRIVE_TRAIN.getLeftPosition()));
        System.out.println("RIGHT Drive Distance: Inches" + Robot.DRIVE_TRAIN.convertRevsToInches(Robot.DRIVE_TRAIN.getRightPosition()));
		Robot.DRIVE_TRAIN.enableVBusControl();
		Robot.DRIVE_TRAIN.resetBothEncoders();
//		Robot.resetNavXAngle();
		Robot.DRIVE_TRAIN.stop();
	}
	
	protected void interrupted() {
		end();
	}

}
