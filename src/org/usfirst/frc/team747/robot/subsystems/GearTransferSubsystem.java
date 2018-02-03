package org.usfirst.frc.team747.robot.subsystems;

import java.util.Map;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.maps.ValueConfig;
import org.usfirst.frc.team747.robot.commands.GearTransferPIDRevolutionsCommand;
import org.usfirst.frc.team747.robot.commands.SpitOutGearCommand;
import org.usfirst.frc.team747.robot.commands.GearDoNothingCommand;
import org.usfirst.frc.team747.robot.commands.GearDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearTransferSubsystem extends Subsystem {
	
    private static final int pidIdx = 0;
    private static final int timeoutMs = 10;
    private static final int slotIdx = 0;
    
    private static final double ENCODER_TICKS = 4096;

    private static final double MAX_PERCENT_VOLTAGE = 0.25;
    private static final double MIN_PERCENT_VOLTAGE = 0;

    public  TalonSRX talonGearTransfer = new TalonSRX(RobotMap.GearMech.GEAR_TRANSFER.getValue());

    public GearTransferSubsystem() {
    	
        
		talonGearTransfer.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, pidIdx, timeoutMs);
		talonGearTransfer.set(ControlMode.MotionMagic, 0);
		talonGearTransfer.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        talonGearTransfer.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        talonGearTransfer.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        talonGearTransfer.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
		talonGearTransfer.configAllowableClosedloopError(slotIdx, 0, timeoutMs);
	}
    
    public void setGearTransferPID(double gearTicks) {
        this.talonGearTransfer.set(ControlMode.MotionMagic, gearTicks); //multiplying by 4096 to account for Phoenix changes
    }
    
    public double convertRevsToTicks(double revs) {
        return revs * ENCODER_TICKS / 50;
    }
    
    public double convertTicksToRevs(double ticks) {
        return ticks / ENCODER_TICKS;
    }

    public double getGearTransferPosition() {
		return talonGearTransfer.getSelectedSensorPosition(0);
    }
    
    public void resetGearTransferEncoder() {
        this.enableVBusControl();
        talonGearTransfer.setSelectedSensorPosition(0, pidIdx, timeoutMs);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void setGearTransferEncoderStartPosition() {
        this.enableVBusControl();
        talonGearTransfer.setSelectedSensorPosition(ValueConfig.PIDGearTransfer.START_POSITION, pidIdx, timeoutMs);
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
    
    public void changeControlMode(ControlMode mode) {
        if (mode == ControlMode.MotionMagic) {
            this.talonGearTransfer.set(mode, talonGearTransfer.getSelectedSensorPosition(0));
        }   else {
            this.talonGearTransfer.set(mode, 0);
            
        }
    }
    
    public void stop() {
        ControlMode mode = this.talonGearTransfer.getControlMode();

        double gearTransferPosition = 0;
        
        switch (mode) {
        case MotionMagic:
            gearTransferPosition = this.talonGearTransfer.getSelectedSensorPosition(0);
            break;
        case PercentOutput:
        case Velocity:
        case Current:
        default:
            // Values should be 0;
            break;
        }
        
        this.setGearTransferPID(gearTransferPosition);
    }
    
    public void enablePositionControl() {
        this.changeControlMode(ControlMode.MotionMagic);
//        this.talonEnableControl();
    }

    public void enableVBusControl() {
//        this.talonDisableControl();
        this.changeControlMode(ControlMode.PercentOutput);
    }
}


