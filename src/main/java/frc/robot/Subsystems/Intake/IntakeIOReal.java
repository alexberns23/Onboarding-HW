package frc.robot.Subsystems.Intake;


import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.controller.PIDController;

public class IntakeIOReal implements IntakeIO {
    
    private double setSpeed;
    private double setPosition;

    private TalonFX flywheel;
    private TalonFX arm; 
    private BangBangController bbController;
    private PIDController pController;

    IntakeIOReal() {
        pController = new PIDController(IntakeConstants.PID_kp, IntakeConstants.PID_ki, IntakeConstants.PID_kd);
        bbController = new BangBangController(IntakeConstants.BB_tolerance);

        flywheel = new TalonFX(IntakeConstants.flywheelMotorID);
        arm = new TalonFX(IntakeConstants.armMotorID);
    }

    @Override
    public void updateInputs(IntakeIOInputs input) {
        input.armPositionSetPoint = setPosition;
        input.armPosition = arm.getPosition().getValueAsDouble();

        input.flywheelSpeedSetPoint = setSpeed;
        input.flywheelSpeed = flywheel.getVelocity().getValueAsDouble();
    }

    @Override
    public void setArmPosition(double armSetPoint) {
        this.setPosition = armSetPoint;

        arm.setVoltage(
            pController.calculate(
                arm.getPosition().getValueAsDouble() * IntakeConstants.RAD_TO_DEG,
                setPosition
            ) * IntakeConstants.armSetInputVoltageMultiplier
        );
    }

    @Override
    public void setFlywheelSpeed(double speedSetPoint) {
        this.setSpeed = speedSetPoint;

        flywheel.setVoltage(
            bbController.calculate(
                flywheel.getVelocity().getValueAsDouble(),
                setSpeed
            )
        );
    }   
}