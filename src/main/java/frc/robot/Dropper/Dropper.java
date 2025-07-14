package frc.robot.Dropper;

import org.team7525.subsystem.Subsystem;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dropper extends Subsystem<DropperState> {


    private FlywheelSim flywheel;

    LinearSystem<N1, N1, N1> system = LinearSystemId.createFlywheelSystem(DCMotor.getKrakenX60(1), 0.127, 1);

    private BangBangController bbController;
    
    public Dropper() {
        super("Dropper", DropperState.OFF);

        bbController = new BangBangController(1);
        flywheel = new FlywheelSim(system, DCMotor.getKrakenX60(1), 1);
    }



    @Override
    public void runState() {
        SmartDashboard.putNumber("set dropper speed", getState().getSpeed());
        SmartDashboard.putNumber("current dropper speed", flywheel.getAngularVelocityRPM() / 60);

        flywheel.setInputVoltage(
            bbController.calculate(flywheel.getAngularVelocityRPM() / 60, getState().getSpeed()) * 12 
        );

        flywheel.update(0.02);
    }

}
