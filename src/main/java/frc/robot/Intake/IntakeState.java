package frc.robot.Intake;

import org.team7525.subsystem.SubsystemStates;

public enum IntakeState implements SubsystemStates {

    //theoretically wherever the intaking position is where the piece would go -> shooter/dropper/outtaking
    //90 - shooter
    //0 - outtaking
    //45 - dropper

    OFF("Off", 90, 0),
    INTAKING("Intaking", 0, 10),
    FEEDING_SHOOTER("Feeding Shooter", 90, -15),
    FEEDING_DROPPER("Feeding Dropper", 45, -15),
    OUTTAKING("Outtaking", 0, -10);

    private final String stateString;
    private final double position;
    private final double speed;



    IntakeState (String stateString, double position, double speed) {
        this.stateString = stateString;
        this.position = position;
        this.speed = speed;
    }
    
    public String getStateString() {
        return stateString;
    }
    public double getPosition(){
        return position;
    }
    public double getSpeed(){
        return speed;
    }



    
}
