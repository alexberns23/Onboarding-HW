package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.controller.PIDController;

public class ShooterIOReal implements ShooterIO{
    
    private double setSpeed;
    private double setPosition;

    private TalonFX flywheel;
    private TalonFX arm; 
    private BangBangController bbController;
    private PIDController pController;

    ShooterIOReal() {

        bbController = new BangBangController(ShooterConstants.BB_tolerance);
        pController = new PIDController(ShooterConstants.PID_kp, ShooterConstants.PID_ki, ShooterConstants.PID_kd);

        arm = new TalonFX(ShooterConstants.armMotorID);
        flywheel = new TalonFX(ShooterConstants.flywheelMotorID);
    }

    @Override
    public void updateInputs(ShooterIOInputs input) {
        input.armPositionSetPoint = setPosition;
        input.armPosition = arm.getPosition().getValueAsDouble();

        input.flywheelSpeedSetPoint = setSpeed;
        input.flywheelSpeed = flywheel.getVelocity().getValueAsDouble();
    }

    @Override
    public void setFlywheelSpeed(double flywheelSpeedSetPoint) {
        this.setSpeed = flywheelSpeedSetPoint;

        flywheel.setVoltage(
            bbController.calculate(
                flywheel.getVelocity().getValueAsDouble(),
                setSpeed
            ) * ShooterConstants.flywheelSetInputVoltageMultiplier
        );
    }

    @Override
    public void setArmPosition(double armPositionSetPoint) {
        this.setPosition = armPositionSetPoint;

        arm.setVoltage(
            pController.calculate(
                arm.getPosition().getValueAsDouble(),
                setPosition
            ) * ShooterConstants.armSetInputVoltageMultiplier
        );
    }
}
