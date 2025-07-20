package frc.robot.Subsystems.Intake;

public final class IntakeConstants {
    
    public static final double flywheelMOI = 0.127;
    public static final int flywheelMotorNumber = 1;
    public static final double flywheelGearing = 2;
    public static final int flywheelStdDevs = 1;

    public static final double armMOI = 0.001;
    public static final int armMotorNumber = 1;
    public static final double armGearing = 2;
    public static final double armLength = 1;
    public static final double armMinAngleRAD = 0;
    public static final double armMaxAngleRAD = 1000;
    public static final boolean isGravity = true;
    public static final double armStartAngleRAD = 0;
    

    public static final double PID_kp = 1;
    public static final double PID_ki = 0;
    public static final double PID_kd = 0.1;

    public static final double BB_tolerance = 0.5;


    public static final double secInMin = 60;
    // i think i had to make this really big to get the wheel to get up to speed in sim
    public static final double armSetInputVoltageMultiplier = 1000;
    public static final double flywheelSetInputVoltageMultiplier = 12;
    public static final double updateTime = 0.02;

    public static final double positionOFF = 90;
    public static final double positionINTAKING = 0.0;
    public static final double positionFEEDING_DROPPER = 45;

    public static final double speedOFF = 0;
    public static final double speedINTAKING = 20;
    public static final double speedOUTTAKING = -10;
    public static final double speedFEEDING = 15;
    
    public static final int flywheelMotorID = 2;
    public static final int armMotorID = 3;

    public static final double RAD_TO_DEG = 57.296;

}
