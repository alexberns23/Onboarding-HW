package frc.robot.Shooter;


import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.team7525.subsystem.Subsystem;


public class Shooter extends Subsystem<ShooterState> {
    
    //This totally isn't just the intake code :)


    FlywheelSim flywheel;
    SingleJointedArmSim arm;

    LinearSystem<N1, N1, N1> system = LinearSystemId.createFlywheelSystem(DCMotor.getKrakenX60(1), 0.127, 2);


    private PIDController pController;
    private BangBangController bbController;

    public Shooter() {
        super("Shooter", ShooterState.OFF);

        flywheel = new FlywheelSim(system, DCMotor.getKrakenX60(1), 1);

        arm = new SingleJointedArmSim(
            LinearSystemId.createDCMotorSystem(
                DCMotor.getKrakenX60(1), 0.001, 1),
                DCMotor.getKrakenX60(1),
                2, 0.5, 0, 7, true, 0
        );


        bbController = new BangBangController(0.5);
        pController = new PIDController(10, 0, 1);

       
    }

    public void runState() {
        SmartDashboard.putNumber("set shooter position", getState().getPosition());
        SmartDashboard.putNumber("set shooter speed", getState().getSpeed());
        SmartDashboard.putNumber("current shooter position", arm.getAngleRads());
        SmartDashboard.putNumber("current shooter speed", flywheel.getAngularVelocityRPM() / 60);

        arm.setInputVoltage(
            pController.calculate(arm.getAngleRads(), getState().getPosition()) * 1000
        );

        flywheel.setInputVoltage(
            bbController.calculate(flywheel.getAngularVelocityRPM() / 60, getState().getSpeed()) * 12
        );

        arm.update(0.02);
        flywheel.update(0.02);

    }

    //there's no sho this is an efficient way to do this
    public boolean shouldStopShooting() {
        if(this.getStateTime() > 1) {return true;}
        else {return false;}
    }

}