package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

public class GlobalConstants {
    
    public enum RobotMode {
        REAL,
        SIM
    }

    public static final RobotMode ROBOT_MODE = RobotBase.isReal() ? RobotMode.REAL : RobotMode.SIM;

}
