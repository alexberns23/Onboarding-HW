package frc.robot.Shooter;

import org.team7525.subsystem.SubsystemStates;

public enum ShooterState implements SubsystemStates{
    SHOOTING("Shooting", 40, 50),
    OFF("Idle", 0, 0);

    private final String stateString;
    private final double position;
    private final double speed;


    ShooterState(String stateString, double position, double speed) {
        this.stateString = stateString;
        this.position = position;
        this.speed = speed;
    }

    public String getStateString() {
        return stateString;
    }
    public double getPosition() {
        return position;
    }
    public double getSpeed() {
        return speed;
    }
    
}
