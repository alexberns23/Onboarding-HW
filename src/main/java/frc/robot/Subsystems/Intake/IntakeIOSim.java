package frc.robot.Subsystems.Intake;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class IntakeIOSim implements IntakeIO{
    
    private double setSpeed;
    private double setPosition;
    
    private FlywheelSim flywheel;
    private SingleJointedArmSim arm;

    private LinearSystem<N1, N1, N1> system = LinearSystemId.createFlywheelSystem(DCMotor.getKrakenX60(IntakeConstants.flywheelMotorNumber), IntakeConstants.flywheelMOI, IntakeConstants.flywheelGearing);
    private BangBangController bbController;
    private PIDController pController;

    IntakeIOSim() {
        bbController = new BangBangController(IntakeConstants.BB_tolerance);
        pController = new PIDController(IntakeConstants.PID_kp, IntakeConstants.PID_ki, IntakeConstants.PID_kd);
        flywheel = new FlywheelSim(system, DCMotor.getKrakenX60(IntakeConstants.flywheelMotorNumber), IntakeConstants.flywheelStdDevs);
        arm = new SingleJointedArmSim(
            LinearSystemId.createDCMotorSystem(
                DCMotor.getKrakenX60(IntakeConstants.armMotorNumber), IntakeConstants.armMOI, IntakeConstants.armGearing),
                DCMotor.getKrakenX60(IntakeConstants.armMotorNumber),
                IntakeConstants.armGearing, IntakeConstants.armLength, IntakeConstants.armMinAngleRAD, IntakeConstants.armMaxAngleRAD, IntakeConstants.isGravity, IntakeConstants.armStartAngleRAD
        );
    }

    @Override
    public void updateInputs(IntakeIOInputs input) {
        flywheel.update(IntakeConstants.updateTime);
        arm.update(IntakeConstants.updateTime);

        input.armPositionSetPoint = setPosition;
        input.armPosition = arm.getAngleRads();
        input.flywheelSpeedSetPoint = setSpeed;
        input.flywheelSpeed = flywheel.getAngularVelocityRPM() / IntakeConstants.secInMin;
    }

    @Override
    public void setArmPosition(double armSetPoint) {
        this.setPosition = armSetPoint;

        arm.setInputVoltage(pController.calculate(
            arm.getAngleRads(),setPosition) * IntakeConstants.armSetInputVoltageMultiplier
        );
    }

    @Override
    public void setFlywheelSpeed(double flywheelSetPoint) {
        this.setSpeed = flywheelSetPoint;

        flywheel.setInputVoltage(
            bbController.calculate(
                flywheel.getAngularVelocityRPM() / IntakeConstants.secInMin, setSpeed
            ) * IntakeConstants.flywheelSetInputVoltageMultiplier
        );
    }

}
