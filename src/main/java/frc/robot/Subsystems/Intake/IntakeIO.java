package frc.robot.Subsystems.Intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
    @AutoLog
    public static class IntakeIOInputs {
        //things to log
        public double flywheelSpeedSetPoint;
        public double flywheelSpeed;

        public double armPositionSetPoint;
        public double armPosition;
    }

    public default void updateInputs(IntakeIOInputs input) {}

    public default void setArmPosition(double armSetPoint) {}

    public default void setFlywheelSpeed(double flywheelSetPoint) {}

}
