package frc.robot.Subsystems.Intake;

import static frc.robot.GlobalConstants.ROBOT_MODE;

import org.team7525.subsystem.Subsystem;

import org.littletonrobotics.junction.Logger;

public class Intake extends Subsystem<IntakeState> {

    private IntakeIO io;
    private IntakeIOInputsAutoLogged inputs;
    private static Intake instance;

    private Intake (IntakeIO io) {
        super("Intake", IntakeState.OFF);

        this.io = io;
        inputs = new IntakeIOInputsAutoLogged();
    }

    @Override
    public void runState() {
        io.setFlywheelSpeed(getState().getSpeed());
        io.setArmPosition(getState().getPosition());

        io.updateInputs(inputs);
        Logger.processInputs("Intake", inputs);
        
    }

    public static Intake getInstance() {
        if (instance == null) {
            IntakeIO intakeIO =
                switch (ROBOT_MODE) {
                    case SIM -> new IntakeIOSim();
                    case REAL -> new IntakeIOReal();
                };
            instance = new Intake(intakeIO);
        }   
        
        return instance;
    }
    
}
