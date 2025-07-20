package frc.robot.Subsystems.Shooter;

import static frc.robot.GlobalConstants.ROBOT_MODE;
import org.littletonrobotics.junction.Logger;

import org.team7525.subsystem.Subsystem;


public class Shooter extends Subsystem<ShooterState> {

    private ShooterIO io;
    private ShooterIOInputsAutoLogged inputs;
    private static Shooter instance;

    public Shooter(ShooterIO io) {
        super("Shooter", ShooterState.OFF);

        this.io = io;
        inputs = new ShooterIOInputsAutoLogged();
    }

    public void runState() {
        io.setFlywheelSpeed(getState().getSpeed());
        io.setArmPosition(getState().getPosition());

        io.updateInputs(inputs);
        Logger.processInputs("Shooter", inputs);
    }

    public static Shooter getInstance() {
        if (instance == null) {
            ShooterIO shooterIO =
                switch (ROBOT_MODE) {
                    case SIM -> new ShooterIOSim();
                    case REAL -> new ShooterIOReal();
                };
            instance = new Shooter(shooterIO);
        }   
        
        return instance;
    }

    //there's no shot this is an efficient way to do this
    public boolean shouldStopShooting() {
        if(this.getStateTime() > ShooterConstants.secStopShooting) {return true;}
        else {return false;}
    }

}