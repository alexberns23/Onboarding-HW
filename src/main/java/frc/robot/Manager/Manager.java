package frc.robot.Manager;

import frc.robot.Intake.Intake;
import frc.robot.Shooter.Shooter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DigitalInput;

import org.team7525.subsystem.Subsystem;

public class Manager extends Subsystem<ManagerStates>{


    private final Intake intake = new Intake();
    private final Shooter shooter = new Shooter();

    private final DigitalInput intakeSensor;
    private final DigitalInput dropperSensor;

    private final XboxController controller = new XboxController(0);

    public Manager() {
        super("Manager", ManagerStates.IDLE);

        intakeSensor = new DigitalInput(0);
        dropperSensor = new DigitalInput(1);
    }


    @Override
    public void runState() {

        //from idle
            //a -> shooting
            addTrigger(ManagerStates.IDLE, ManagerStates.SHOOTING, controller::getAButtonPressed);
            //b -> intaking
            addTrigger(ManagerStates.IDLE, ManagerStates.INTAKING, controller::getBButtonPressed);
            //x -> going to staging
            addTrigger(ManagerStates.IDLE, ManagerStates.GOING_TO_STAGING, controller::getXButtonPressed);
    
            addTrigger(ManagerStates.GOING_TO_STAGING, ManagerStates.STAGING, () -> !dropperSensor.get());
            addTrigger(ManagerStates.STAGING, ManagerStates.SCORING, controller::getXButtonPressed);
            addTrigger(ManagerStates.SCORING, ManagerStates.IDLE, () -> !dropperSensor.get());


        
            addTrigger(ManagerStates.SHOOTING, ManagerStates.IDLE, shooter::shouldStopShooting);

        //from intaking
            addTrigger(ManagerStates.INTAKING, ManagerStates.GOING_TO_STAGING, controller::getXButtonPressed);
            addTrigger(ManagerStates.INTAKING, ManagerStates.IDLE, controller::getBButtonPressed);
            addTrigger(ManagerStates.INTAKING, ManagerStates.OUTTAKING, controller::getRightBumperPressed);
            // not sure if ! op works like that
            addTrigger(ManagerStates.INTAKING, ManagerStates.IDLE, () -> !intakeSensor.get());

            addTrigger(ManagerStates.OUTTAKING, ManagerStates.INTAKING, controller::getRightBumperButtonReleased);


            intake.periodic();
            shooter.periodic();

    }
}