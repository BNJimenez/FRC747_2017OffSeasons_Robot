package org.usfirst.frc.team747.robot.maps;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public final class RobotMap {
    /**
     * The drive train will use six talons, and will have the climber integrated into all six 
     */
 
	public enum DriveTrain {
		
        LEFT_FRONT(0), //for off season robot
        //LEFT_MIDDLE(1),
        LEFT_REAR(1),
        RIGHT_FRONT(2),
       // RIGHT_MIDDLE(4),
        RIGHT_REAR(3);
		
        private int value;

        private DriveTrain(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
	/**
	 * The gear mech will use 2 talons to operate the mechanism, one for the intake, and another for the PID joint
	 */
    public enum GearMech {

    	GEAR_INTAKE(6), //INTAKE
    	GEAR_TRANSFER(7); //GEAR TRANSFER
 
    	private int value;
    	
    	private GearMech(int value) {
        	this.value = value;
    	}
    	public int getValue() {
    		return value;
    	}
    }
}
