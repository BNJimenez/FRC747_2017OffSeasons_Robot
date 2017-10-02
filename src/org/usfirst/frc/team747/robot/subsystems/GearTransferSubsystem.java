package org.usfirst.frc.team747.robot.subsystems;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.commands.GearTransferPIDRevolutionsCommand;
import org.usfirst.frc.team747.robot.commands.SpitOutGearCommand;
import org.usfirst.frc.team747.robot.commands.GearDoNothingCommand;
import org.usfirst.frc.team747.robot.commands.GearDriveCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearTransferSubsystem extends Subsystem {
	
    private static final double ENCODER_TICKS = 4096;
// 4096 for the mag encoders
    private static final double MAX_VOLTAGE = 3;
    private static final double MIN_VOLTAGE = 0;
	public double GEAR_TRANSFER_FAST_SPEED = 0.5;
	public double GEAR_TRANSFER_MODERATE_SPEED = 0.25;
	public double GEAR_TRANSFER_SLOW_SPEED = 0.1;

    public  CANTalon talonGearTransfer = new CANTalon(RobotMap.GearMech.GEAR_TRANSFER.getValue());

    public GearTransferSubsystem() {
    	
        
		talonGearTransfer.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		talonGearTransfer.changeControlMode(TalonControlMode.MotionMagic);
		talonGearTransfer.configNominalOutputVoltage(MIN_VOLTAGE, -MIN_VOLTAGE);
		talonGearTransfer.configPeakOutputVoltage(MAX_VOLTAGE, -MAX_VOLTAGE);
		talonGearTransfer.setAllowableClosedLoopErr(0);
	}
    
    public void setGearTransferPID(double gearTicks) {
        this.talonGearTransfer.set(gearTicks);
    }
    
    public double convertRevsToTicks(double revs) {
        return revs * ENCODER_TICKS / 50;
    }
    
    public double convertTicksToRevs(double ticks) {
        return ticks / ENCODER_TICKS;
    }

    public double getGearTransferPosition() {
		return talonGearTransfer.getPosition();
    }
    
    public void resetGearTransferEncoder() {
        this.enableVBusControl();
        talonGearTransfer.setPosition(0);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GearDriveCommand());
    }
    
    public void changeControlMode(TalonControlMode mode) {
        this.talonGearTransfer.changeControlMode(mode);
    }
    
    public void stop() {
        TalonControlMode mode = this.talonGearTransfer.getControlMode();

        double gearTransferPosition = 0;
        
        switch (mode) {
        case Position:
            gearTransferPosition = this.talonGearTransfer.getPosition();
            break;
        case PercentVbus:
        case Speed:
        case Voltage:
        default:
            // Values should be 0;
            break;
        }
        
        this.setGearTransferPID(gearTransferPosition);
    }
    
    public void talonEnableControl() {
        talonGearTransfer.enableControl();
    }
    
    public void talonDisableControl() {
        talonGearTransfer.disableControl();
    }
    
    public void enablePositionControl() {
        this.changeControlMode(TalonControlMode.MotionMagic);
//        this.talonEnableControl();
    }

    public void enableVBusControl() {
//        this.talonDisableControl();
        this.changeControlMode(TalonControlMode.PercentVbus);
    }
}


