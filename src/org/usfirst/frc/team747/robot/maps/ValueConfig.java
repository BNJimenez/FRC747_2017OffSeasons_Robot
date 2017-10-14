package org.usfirst.frc.team747.robot.maps;

/**
 * Distances are in inches
 * Rotations are in degrees
 * Times are in seconds
 * Speed values are in motor %
 */
public final class ValueConfig {
    
    private ValueConfig() {
    } 
    
    //TODO Determine Values for these classes
    
    public final class PIDDriveDistances{
       
        private PIDDriveDistances(){
        }
        
        public static final double FORWARD_TO_CENTER_GEAR = 0.0,
                                   REVERSE_AWAY_FROM_CENTER_GEAR = 0.0,
                                   FORWARD_TO_FRONT_OF_KEY = 0.0;
    }
    public final class PIDGearTransfer{
    	
    	private PIDGearTransfer() {
    		
    	}
    	public static final double 	PICK_UP_POSITION = 9.012568279,
    								HOME_POSITION = 2.3232421875,
    								SCORE_POSITION = 0.0;
    }
   
}