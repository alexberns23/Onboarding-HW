package frc.robot.Subsystems.Shooter;

public final class ShooterConstants {
    public static final double flywheelMOI = 0.127;
    public static final int flywheelMotorNumber = 1;
    public static final double flywheelGearing = 2;
    public static final int flywheelStdDevs = 1;

    public static final double armMOI = 0.001;
    public static final int armMotorNumber = 1;
    public static final double armGearing = 1;
    public static final double armLength = 0.5;
    public static final double armMinAngleRAD = 0;
    public static final double armMaxAngleRAD = 7;
    public static final boolean isGravity = true;
    public static final double armStartAngleRAD = 0;
    

    public static final double PID_kp = 10;
    public static final double PID_ki = 0;
    public static final double PID_kd = 1;

    public static final double BB_tolerance = 0.5;


    public static final double secInMin = 60;
    public static final double armSetInputVoltageMultiplier = 1000;
    public static final double flywheelSetInputVoltageMultiplier = 12;
    public static final double updateTime = 0.02;

    public static final int flywheelMotorID = 4;
    public static final int armMotorID = 5;


}
