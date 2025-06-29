package frc.robot.Manager;

import frc.robot.Intake.IntakeState;
import frc.robot.Shooter.ShooterState;

public enum ManagerStates {
    IDLE(IntakeState.OFF, ShooterState.OFF, "Idle"),
    INTAKING(IntakeState.INTAKING, ShooterState.OFF, "Intaking"),
    SHOOTING(IntakeState.FEEDING, ShooterState.SHOOTING, "Shooting"),
    OUTTAKING(IntakeState.OUTTAKING, ShooterState.OFF, "Outtaking"),;

    private IntakeState intakeState;
    private ShooterState shooterState;
    private String stateString;

    private ManagerStates(IntakeState intakeState, ShooterState shooterState, String stateString) {
        this.intakeState = intakeState;
        this.stateString = stateString;
        this.shooterState = shooterState;
    }

    public String getIntakeString() {
        return stateString;
    }
    public IntakeState getIntakeState() {
        return intakeState;
    }
    public ShooterState getShooterState() {
        return shooterState;
    }
    
    
}
