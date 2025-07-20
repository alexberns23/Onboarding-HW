package frc.robot.Subsystems.Dropper;

import org.littletonrobotics.junction.AutoLog;

public interface DropperIO {
    @AutoLog
    public static class DropperIOInputs {
        //logged vals
        public double flywheelSpeedSetPoint;
        public double flywheelSpeed;
    }

    public default void updateInputs(DropperIOInputs input) {}

    public default void setSpeed(double speedSetPoint) {}
}
