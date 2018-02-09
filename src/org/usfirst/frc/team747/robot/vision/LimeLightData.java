package org.usfirst.frc.team747.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLightData {
    
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    private final double horizontalAngle = tx.getDouble(0);
    private final double verticalAngle = ty.getDouble(0);
    private final double area = ta.getDouble(0);

    public double getHorizontalAngle() {
        return this.horizontalAngle;
    }
    
    public double getVerticalAngle() {
        return this.verticalAngle;
    }
    
    public double getArea() {
        return this.area;
    }
}
