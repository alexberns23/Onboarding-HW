package frc.robot.Manager;

import frc.robot.Intake.Intake;
import frc.robot.Shooter.Shooter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Timer;

public class Manager {


    private final Intake intake = new Intake();
    private final Shooter shooter = new Shooter();
    private Timer timer = new Timer();

    private ManagerStates currentState = ManagerStates.IDLE;

    private final XboxController controller = new XboxController(0);

    public Manager() {}
    
    public void periodic () {
        switch (currentState) {
            case IDLE :
                if(controller.getAButtonPressed()) {
                    currentState = ManagerStates.SHOOTING;
                    timer.reset();
                    timer.start();
                }
                else if(controller.getBButtonPressed()) {
                    currentState = ManagerStates.INTAKING;
                }
                break;
            case SHOOTING : 
                if(timer.get() > 1) {
                    currentState = ManagerStates.IDLE;
                }
                break;
            case INTAKING :  
                if(controller.getBButtonPressed()) {
                    currentState = ManagerStates.IDLE;
                }
                else if(controller.getRightBumperPressed()) {
                    currentState = ManagerStates.OUTTAKING;
                }
                break;
            case OUTTAKING :
                if(controller.getRightBumperReleased()) {
                    currentState = ManagerStates.IDLE;
                }
                break;
            default : 
                break;
        }

        //update intake info
        intake.setSpeedAndPosition(
            currentState.getIntakeState().getPosition(),
            currentState.getIntakeState().getSpeed()
        );
        shooter.setSpeedAndPosition(
            currentState.getShooterState().getPosition(),
            currentState.getShooterState().getSpeed()
        );
        
    }
}
