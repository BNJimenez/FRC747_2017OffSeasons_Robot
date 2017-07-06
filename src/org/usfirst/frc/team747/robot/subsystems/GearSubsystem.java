package org.usfirst.frc.team747.robot.subsystems;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.commands.GrabGearCommand;
import org.usfirst.frc.team747.robot.commands.SpitOutGearCommand;
import org.usfirst.frc.team747.robot.commands.GearDoNothing;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearSubsystem extends Subsystem {
	
    private static final double ENCODER_TICKS = 1028;

    private static final double MAX_VOLTAGE = 12;
    private static final double MIN_VOLTAGE = 0;

	private double speed = 1.0;
	
    public  CANTalon talonGear1 = new CANTalon(RobotMap.GearMech.GEARMECH_1.getValue()),
    				 talonGear2 = new CANTalon(RobotMap.GearMech.GEARMECH_2.getValue());

    public GearSubsystem() {
		talonGear1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GearDoNothing());
    }

    public void GrabGear() {
    	talonGear1.set(1*speed);
    	
    	
    }
    public void SpitOutGear() {
    	talonGear1.set(-1*speed);
    	
    	
    }
	public void DoNothing(){
		talonGear1.set(0);
		talonGear2.set(0);
	}
}


