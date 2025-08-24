package frc.robot.Subsystems.Drive;

import frc.robot.GlobalConstants.RobotSide;

import static frc.robot.GlobalConstants.*;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class Pose {
    
    private Pose2d setpoint;

    public Pose2d correctPoseSide(Pose2d blueSidePose) {
        if(ROBOT_SIDE == RobotSide.RED) {
            this.setpoint = new Pose2d(
            175 - blueSidePose.getX(),
            80 - blueSidePose.getX(),
            blueSidePose.getRotation().rotateBy(new Rotation2d(180)) //make that a constant ... (make everything a constant but this especially))
            );
        }
        else {
            this.setpoint = blueSidePose;
        }
        return this.setpoint;
    }



}
