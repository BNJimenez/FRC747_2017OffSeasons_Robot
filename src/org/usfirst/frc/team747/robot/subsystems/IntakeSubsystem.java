package org.usfirst.frc.team747.robot.subsystems;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.commands.GearTransferPIDRevolutionsCommand;
import org.usfirst.frc.team747.robot.commands.IntakeDoNothingCommand;
import org.usfirst.frc.team747.robot.commands.SpitOutGearCommand;
import org.usfirst.frc.team747.robot.commands.GearDoNothingCommand;
import org.usfirst.frc.team747.robot.commands.GearDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
	
    private static final double ENCODER_TICKS = 4096;
// 4096 for the mag encoders
    private static final double MAX_VOLTAGE = 3;
    private static final double MIN_VOLTAGE = 0;
	public double INTAKE_SPEED = 1.0;

    public  TalonSRX talonGearIntake = new TalonSRX(RobotMap.GearMech.GEAR_INTAKE.getValue());

    public IntakeSubsystem() {
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeDoNothingCommand());
    }
    
    public void SuckInGear() {
        //rollers roll to to bring in the gear
        talonGearIntake.set(ControlMode.PercentOutput, 1);
    }
    
    public void SpitOutGear() {
    	//reverse the talon output so the rollers roll the opposite direction
    	talonGearIntake.set(ControlMode.PercentOutput, -1);
    }
	public void DoNothing(){	
		//Set the intake to a speed of 0
		
		talonGearIntake.set(ControlMode.PercentOutput, 0);
	}
}


