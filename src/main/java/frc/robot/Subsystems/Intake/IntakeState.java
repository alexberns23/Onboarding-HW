package frc.robot.Subsystems.Intake;

import org.team7525.subsystem.SubsystemStates;

public enum IntakeState implements SubsystemStates {

    //theoretically wherever the intaking position is where the piece would go -> shooter/dropper/outtaking
    //90 - shooter
    //0 - outtaking
    //45 - dropper

    OFF("Off", IntakeConstants.positionOFF, IntakeConstants.speedOFF),
    INTAKING("Intaking", IntakeConstants.positionINTAKING, IntakeConstants.speedINTAKING),
    FEEDING_SHOOTER("Feeding Shooter", IntakeConstants.positionOFF, IntakeConstants.speedFEEDING),
    FEEDING_DROPPER("Feeding Dropper", IntakeConstants.positionFEEDING_DROPPER, IntakeConstants.speedFEEDING),
    OUTTAKING("Outtaking", IntakeConstants.positionINTAKING, IntakeConstants.speedOUTTAKING);

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
