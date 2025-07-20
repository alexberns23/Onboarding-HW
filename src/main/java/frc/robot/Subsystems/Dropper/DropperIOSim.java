package frc.robot.Subsystems.Dropper;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class DropperIOSim implements DropperIO {

    private double setSpeed;
    
    private FlywheelSim flywheel;
    LinearSystem<N1, N1, N1> system = LinearSystemId.createFlywheelSystem(DCMotor.getKrakenX60(DropperConstants.motorNumber), DropperConstants.MOI, DropperConstants.gearing);
    private BangBangController bbController;

    DropperIOSim() {

        bbController = new BangBangController(DropperConstants.BB_tolerance);
        flywheel = new FlywheelSim(system, DCMotor.getKrakenX60(DropperConstants.motorNumber), DropperConstants.stdDevs);
    }

    @Override
    public void updateInputs(DropperIOInputs input) {
        flywheel.update(DropperConstants.updateTime);

        input.flywheelSpeedSetPoint = setSpeed;
        input.flywheelSpeed = flywheel.getAngularVelocityRPM() / DropperConstants.secInMin;

    }

    @Override
    public void setSpeed(double speedSetPoint) {
        this.setSpeed = speedSetPoint;
        flywheel.setInputVoltage(
            bbController.calculate(flywheel.getAngularVelocityRPM() / DropperConstants.secInMin, setSpeed) * DropperConstants.setInputVoltageMultiplier
        );
    }


}
