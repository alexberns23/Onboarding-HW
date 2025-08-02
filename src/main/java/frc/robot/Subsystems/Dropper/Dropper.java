package frc.robot.Subsystems.Dropper;

import org.team7525.subsystem.Subsystem;

import static frc.robot.GlobalConstants.ROBOT_MODE;

import org.littletonrobotics.junction.Logger;

public class Dropper extends Subsystem<DropperState> {

    private static Dropper instance;

    private DropperIO io;
    private DropperIOInputsAutoLogged inputs;


    private Dropper(DropperIO io) {
        super("Dropper", DropperState.OFF);
        this.io = io;
        inputs = new DropperIOInputsAutoLogged();
    }



    @Override   
    protected void runState() {
        io.setSpeed(getState().getSpeed());

        io.updateInputs(inputs);
        Logger.processInputs("Dropper", inputs);

    }

    public static Dropper getInstance() {
        if(instance == null) {
            DropperIO dropperIO = 
                switch(ROBOT_MODE) {
                    case SIM -> new DropperIOSim();
                    case REAL -> new DropperIOReal();
                };
            instance = new Dropper(dropperIO);   
        }
        return instance;
    }

}
