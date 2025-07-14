package frc.robot.Dropper;

import org.team7525.subsystem.SubsystemStates;

public enum DropperState implements SubsystemStates {
    OFF("Off", 0.0),
    STAGING("Staging", 0.0),
    SCORING("Scoring", 10);

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
