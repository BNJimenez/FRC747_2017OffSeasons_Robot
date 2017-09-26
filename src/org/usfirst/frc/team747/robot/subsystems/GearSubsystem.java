package org.usfirst.frc.team747.robot.subsystems;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.commands.GrabGearCommand;
import org.usfirst.frc.team747.robot.commands.SpitOutGearCommand;
import org.usfirst.frc.team747.robot.commands.GearDoNothing;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearSubsystem extends Subsystem {
	
    private static final double ENCODER_TICKS = 1028;

    private static final double MAX_VOLTAGE = 12;
    private static final double MIN_VOLTAGE = 0;
	private double speed = 1.0;
	private int loops = 0;
    

	
    public  CANTalon /*talonGear1 = new CANTalon(RobotMap.GearMech.GEARMECH_1.getValue()), */
    				 talonGear2 = new CANTalon(RobotMap.GearMech.GEARMECH_2.getValue());

    public GearSubsystem() {
    	
//		talonGear1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		talonGear2.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
    	int abspositon = talonGear2.getPulseWidthPosition() & 0xFFF;

		talonGear2.changeControlMode(TalonControlMode.Position);
		talonGear2.configNominalOutputVoltage(MIN_VOLTAGE, -1*MIN_VOLTAGE);
		talonGear2.configPeakOutputVoltage(MAX_VOLTAGE, -1*MAX_VOLTAGE);
		talonGear2.setAllowableClosedLoopErr(0);
		talonGear2.setEncPosition(abspositon);
		

		
		
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GearDoNothing());
    }
    
    public void GrabGear() {
    	//activate the rollers
//    	talonGear1.set(1*speed);
    	//activate the PID controlled mechanism

    	talonGear2.set(loops);
    	
    }
    public void SpitOutGear() {
    	//reverse the talon output so the rollers roll the opposite direction
//    	talonGear1.set(-1*speed);
    	
    	
    }
	public void DoNothing(){
		loops = 0;
//		talonGear1.set(0);
		
		talonGear2.set(loops);
	}
}


