package frc.robot.Subsystems.Drive;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public enum AutoAlignStates {
    OFF("Off", 0,0,0), //hopefully this one never comes into play
    SHOOTING_GOAL("Shooting_Goal", 10, 10, 90),
    DROPPER_GOAL("Dropper_Goal", 5, 5, 30);

    private String stateString;
    private double xSetpoint;
    private double ySetpoint;

    private Rotation2d rotationSetpoint;

    private Pose2d setPose;


    AutoAlignStates(String stateString, double xSetpoint, double ySetpoint, double rotationSetpointValue) {
        this.stateString = stateString;
        this.xSetpoint = xSetpoint;
        this.ySetpoint = ySetpoint;
        this.rotationSetpoint = new Rotation2d(rotationSetpointValue);


    }

    public String getStateStrin(){
        return this.stateString;
    }

    public Pose2d getSetPose() {

        this.setPose = new Pose2d(this.xSetpoint, this.ySetpoint, rotationSetpoint);
        return setPose;
    }

}
