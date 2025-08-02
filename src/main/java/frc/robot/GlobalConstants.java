package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;

public class GlobalConstants {
    
    public enum RobotMode {
        REAL,
        SIM
    }

    public static final RobotMode ROBOT_MODE = RobotBase.isReal() ? RobotMode.REAL : RobotMode.SIM;

    public static final XboxController DRIVER_CONTROLLER = new XboxController(0);

    public static final double DEADBAND = 0.01;

    public static final Field2d FIELD = new Field2d();

}
