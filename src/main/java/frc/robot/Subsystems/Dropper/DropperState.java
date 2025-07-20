package frc.robot.Subsystems.Dropper;

import org.team7525.subsystem.SubsystemStates;

public enum DropperState implements SubsystemStates {
    OFF("Off", DropperConstants.speedOFF),
    STAGING("Staging", 0.0),
    SCORING("Scoring", DropperConstants.speedScoring);

    private final String stateString;
    private final double speed;

    DropperState(String stateString, double speed) {
        this.stateString = stateString;
        this.speed = speed;
    }

    public String getStateString() {
        return this.stateString;
    }
    public double getSpeed() {
        return this.speed;
    }
}
