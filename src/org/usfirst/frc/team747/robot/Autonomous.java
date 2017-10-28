package org.usfirst.frc.team747.robot;


import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team747.robot.Autonomous.AutoMode;
import org.usfirst.frc.team747.robot.autonomous.*;
import org.usfirst.frc.team747.robot.commands.GearTransferEncoderReset;
import org.usfirst.frc.team747.robot.commands.GearTransferEncoderStartPositionSet;
import org.usfirst.frc.team747.robot.commands.GearTransferHomingCommand;
import org.usfirst.frc.team747.robot.commands.GearTransferPIDRevolutionsCommand;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

public class Autonomous{
    
    public enum AutoMode{
        AUTOMODE_NONE,
        AUTOMODE_GEAR_TRANSFER_GO_HOME,
        AUTOMODE_CROSS_LINE,
        AUTOMODE_CENTER_GEAR;
    }
    
    private SendableChooser autoChooser1;
    
    public Autonomous(){
        autoChooser1 = new SendableChooser();        
        
        autoChooser1.addDefault("Default: No autonomous", AutoMode.AUTOMODE_NONE);
        autoChooser1.addObject("Send Gear Transfer To Home", AutoMode.AUTOMODE_GEAR_TRANSFER_GO_HOME);
        autoChooser1.addObject("Cross Line", AutoMode.AUTOMODE_CROSS_LINE);
        autoChooser1.addObject("Center Gear: Agua de Coco", AutoMode.AUTOMODE_CENTER_GEAR);
        SmartDashboard.putData("Auto mode", autoChooser1);
    }
    
    public void startMode(){
        
        System.out.println("In Auto.StartMode");
        
        AutoMode selectedAutoMode = (AutoMode)(autoChooser1.getSelected());
                    
        switch (selectedAutoMode){
            case AUTOMODE_CROSS_LINE:
                new CrossLine().start();
                break;
            case AUTOMODE_CENTER_GEAR:
                new CenterGear().start();
                break;
            case AUTOMODE_GEAR_TRANSFER_GO_HOME:
                new GearTransferGoHome().start();
                break;
            case AUTOMODE_NONE:
                new GearTransferEncoderStartPositionSet().start();
            default:
                break;
            }
    }
}