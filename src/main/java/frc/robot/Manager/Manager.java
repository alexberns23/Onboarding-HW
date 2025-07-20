package frc.robot.Manager;

import frc.robot.Subsystems.Intake.Intake;
import frc.robot.Subsystems.Shooter.Shooter;
import frc.robot.Subsystems.Dropper.Dropper;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DigitalInput;

import org.team7525.subsystem.Subsystem;

public class Manager extends Subsystem<ManagerStates>{

    private static Manager instance;
    private Intake intake;
    private Shooter shooter;
    private Dropper dropper;

    private final DigitalInput intakeSensor;
    private final DigitalInput dropperSensor;

    private final XboxController controller = new XboxController(ManagerConstants.XBoxControllerPort);

    public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

    public Manager() {
        super("Manager", ManagerStates.IDLE);

        intake = Intake.getInstance();
        shooter = Shooter.getInstance();
        dropper = Dropper.getInstance();
        intakeSensor = new DigitalInput(ManagerConstants.intakeSensorChannel);
        dropperSensor = new DigitalInput(ManagerConstants.dropperSensorChannel);
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
            
            addTrigger(ManagerStates.SHOOTING, ManagerStates.IDLE, () -> this.getStateTime() > ManagerConstants.shootingTime);

        //from intaking
            addTrigger(ManagerStates.INTAKING, ManagerStates.GOING_TO_STAGING, controller::getXButtonPressed);
            addTrigger(ManagerStates.INTAKING, ManagerStates.IDLE, controller::getBButtonPressed);
            addTrigger(ManagerStates.INTAKING, ManagerStates.OUTTAKING, controller::getRightBumperPressed);
            addTrigger(ManagerStates.INTAKING, ManagerStates.IDLE, () -> !intakeSensor.get());

            addTrigger(ManagerStates.OUTTAKING, ManagerStates.INTAKING, controller::getRightBumperButtonReleased);


            intake.periodic();
            shooter.periodic();
            dropper.periodic();
    }
}