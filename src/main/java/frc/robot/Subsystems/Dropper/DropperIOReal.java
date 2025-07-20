package frc.robot.Subsystems.Dropper;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.BangBangController;

public class DropperIOReal implements DropperIO{
    
    private double setSpeed;

    private TalonFX flywheel; 
    private BangBangController bbController;

    DropperIOReal() {
        flywheel = new TalonFX(DropperConstants.motorID);
        bbController = new BangBangController(DropperConstants.BB_tolerance);
    }

    @Override
    public void updateInputs(DropperIOInputs input) {
        input.flywheelSpeedSetPoint = setSpeed;

        input.flywheelSpeed = flywheel.getVelocity().getValueAsDouble();
    }

    @Override
    public void setSpeed(double speedSetPoint) {
        this.setSpeed = speedSetPoint;
        flywheel.setVoltage(bbController.calculate(flywheel.getVelocity().getValueAsDouble(), setSpeed) * DropperConstants.setInputVoltageMultiplier);
    }
}
