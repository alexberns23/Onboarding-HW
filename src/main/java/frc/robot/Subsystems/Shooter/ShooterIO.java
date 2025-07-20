package frc.robot.Subsystems.Shooter;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {
    @AutoLog
    public static class ShooterIOInputs {
        public double armPositionSetPoint;
        public double armPosition;

        public double flywheelSpeedSetPoint;
        public double flywheelSpeed;
    }

    public default void updateInputs(ShooterIOInputs input) {}

    public default void setArmPosition(double armSetPoint) {}

    public default void setFlywheelSpeed(double flywheelSetPoint) {}
}
