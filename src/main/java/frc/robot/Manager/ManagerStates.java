package frc.robot.Manager;

import frc.robot.Intake.IntakeState;
import frc.robot.Shooter.ShooterState;
import frc.robot.Dropper.DropperState;

import org.team7525.subsystem.SubsystemStates;

public enum ManagerStates implements SubsystemStates{

    IDLE(IntakeState.OFF, ShooterState.OFF, DropperState.OFF, "Idle"),
    GOING_TO_STAGING(IntakeState.FEEDING_DROPPER, ShooterState.OFF, DropperState.OFF, "Going To Staging"),
    STAGING(IntakeState.OFF, ShooterState.OFF, DropperState.STAGING, "Staging"),
    SCORING(IntakeState.OFF, ShooterState.OFF, DropperState.SCORING, "Scoring"),
    INTAKING(IntakeState.INTAKING, ShooterState.OFF, DropperState.OFF, "Intaking"),
    SHOOTING(IntakeState.FEEDING_SHOOTER, ShooterState.SHOOTING, DropperState.OFF,"Shooting"),
    OUTTAKING(IntakeState.OUTTAKING, ShooterState.OFF, DropperState.OFF,"Outtaking");

    private IntakeState intakeState;
    private ShooterState shooterState;
    private DropperState dropperState;
    private String stateString;

    private ManagerStates(IntakeState intakeState, ShooterState shooterState, DropperState dropperState, String stateString) {
        this.intakeState = intakeState;
        this.stateString = stateString;
        this.dropperState = dropperState;
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
    public DropperState getDropperState() {
        return dropperState;
    }
    
}
