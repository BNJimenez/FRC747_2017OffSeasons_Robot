package org.usfirst.frc.team747.robot;


import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team747.robot.Autonomous.AutoMode;
import org.usfirst.frc.team747.robot.autonomous.*;
import org.usfirst.frc.team747.robot.commands.GearTransferHomingCommand;

public class Autonomous{
    
    public enum AutoMode{
        AUTOMODE_NONE,
        AUTOMODE_HOME_GEAR_TRANSFER,
        AUTOMODE_CROSS_LINE,
        AUTOMODE_CENTER_GEAR;
    }
    
    private SendableChooser autoChooser1;
    
    public Autonomous(){
        autoChooser1 = new SendableChooser();        
        
        autoChooser1.addDefault("Default: No autonomous", AutoMode.AUTOMODE_NONE);
        autoChooser1.addObject("Home Gear Transfer", AutoMode.AUTOMODE_HOME_GEAR_TRANSFER);
        autoChooser1.addObject("Cross Line", AutoMode.AUTOMODE_CROSS_LINE);
        autoChooser1.addObject("Center Gear", AutoMode.AUTOMODE_CENTER_GEAR);
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
            case AUTOMODE_HOME_GEAR_TRANSFER:
                new GearTransferHomingCommand().start();
                break;
            case AUTOMODE_NONE:
            default:
                break;
            }
    }
}