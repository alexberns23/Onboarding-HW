package frc.robot.Subsystems.Drive.AutoAlign;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import static frc.robot.GlobalConstants.*;
import static frc.robot.Subsystems.Drive.DriveConstants.*;
import edu.wpi.first.math.geometry.Pose2d;

public class AutoAlign {

    private ProfiledPIDController controller;

    private final Pose2d setpoint = new Pose2d(X_SETPOINT, Y_SETPOINT, ROTATION_SETPOINT);
	private Pose2d currentPose = getInstance().getPose();

    public AutoAlign () {
        controller = new ProfiledPIDController(
	        PROFILED_PID_P, PROFILED_PID_I, PROFILED_PID_D, new TrapezoidProfile.Constraints(PROFILED_PID_METER_CONSTRAINT, PROFILED_PID_MAX_ACCEL), UPDATE_TIME
	    );

    }

}