package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.controller.PIDController;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.RotationsPerSecond;

public class Shooter {

    private TalonFX motorDirection;
    private TalonFX motorSpeed;

    private PIDController pController;
    private BangBangController bbController;

    public Shooter() {
        motorDirection = new TalonFX(1);
        motorSpeed = new TalonFX(2);


        bbController = new BangBangController(0.5);
        pController = new PIDController(10, 0, 1);
       
    }

    public void setSpeedAndPosition(double speed, double position) {
        
        motorDirection.setVoltage(
            pController.calculate(
                motorDirection.getPosition().getValue().in(Degree),
                position
            )
        );
        motorSpeed.set(
            bbController.calculate(
                motorSpeed.getVelocity().getValue().in(RotationsPerSecond),
                speed
            )
        );

    }


}