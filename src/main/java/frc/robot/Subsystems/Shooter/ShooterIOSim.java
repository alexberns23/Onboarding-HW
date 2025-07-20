package frc.robot.Subsystems.Shooter;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class ShooterIOSim implements ShooterIO{
    private double setSpeed;
    private double setPosition;
    
    private FlywheelSim flywheel;
    private SingleJointedArmSim arm;

    private LinearSystem<N1, N1, N1> system = LinearSystemId.createFlywheelSystem(DCMotor.getKrakenX60(ShooterConstants.flywheelMotorNumber), ShooterConstants.flywheelMOI, ShooterConstants.flywheelGearing);
    private BangBangController bbController;
    private PIDController pController;

    ShooterIOSim() {
        bbController = new BangBangController(ShooterConstants.BB_tolerance);
        pController = new PIDController(ShooterConstants.PID_kp, ShooterConstants.PID_ki, ShooterConstants.PID_kd);
        flywheel = new FlywheelSim(system, DCMotor.getKrakenX60(ShooterConstants.flywheelMotorNumber), ShooterConstants.flywheelStdDevs);
        arm = new SingleJointedArmSim(
            LinearSystemId.createDCMotorSystem(
                DCMotor.getKrakenX60(ShooterConstants.armMotorNumber), ShooterConstants.armMOI, ShooterConstants.armGearing),
                DCMotor.getKrakenX60(ShooterConstants.armMotorNumber),
                ShooterConstants.armGearing, ShooterConstants.armLength, ShooterConstants.armMinAngleRAD, ShooterConstants.armMaxAngleRAD, ShooterConstants.isGravity, ShooterConstants.armStartAngleRAD
        );
    }

    @Override
    public void updateInputs(ShooterIOInputs input) {
        flywheel.update(ShooterConstants.updateTime);
        arm.update(ShooterConstants.updateTime);
        
        input.armPositionSetPoint = setPosition;
        input.armPosition = arm.getAngleRads();
        input.flywheelSpeedSetPoint = setSpeed;
        input.flywheelSpeed = flywheel.getAngularVelocityRPM() / ShooterConstants.secInMin;
    }

    @Override
    public void setArmPosition(double armPositionSetPoint) {
        this.setPosition = armPositionSetPoint;
        
        arm.setInputVoltage(
            pController.calculate(
                arm.getAngleRads(),
                setPosition
            ) * ShooterConstants.armSetInputVoltageMultiplier
        );
    }

    @Override
    public void setFlywheelSpeed(double flywheelSpeedSetPoint) {
        this.setSpeed = flywheelSpeedSetPoint;
        
        flywheel.setInputVoltage(
            bbController.calculate(
                flywheel.getAngularVelocityRPM(),
                setSpeed
            ) * ShooterConstants.flywheelSetInputVoltageMultiplier
        );
    }
}
