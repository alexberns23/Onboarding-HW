package frc.robot.Manager;

import frc.robot.Subsystems.Intake.Intake;
import frc.robot.Subsystems.Shooter.Shooter;
import frc.robot.Subsystems.Dropper.Dropper;
import frc.robot.Subsystems.Drive.Drive;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Subsystems.Drive.AutoAlignStates;


import static frc.robot.GlobalConstants.DRIVER_CONTROLLER;

import org.team7525.subsystem.Subsystem;

public class Manager extends Subsystem<ManagerStates>{

    private static Manager instance;
    private Intake intake;
    private Shooter shooter;
    private Dropper dropper;
    private AutoAlignStates autoAlign;
    private Drive drive;

    private final DigitalInput intakeSensor;
    private final DigitalInput dropperSensor;


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
            addTrigger(ManagerStates.IDLE, ManagerStates.SHOOTING, DRIVER_CONTROLLER::getAButtonPressed);
            //b -> intaking
            addTrigger(ManagerStates.IDLE, ManagerStates.INTAKING, DRIVER_CONTROLLER::getBButtonPressed);
            //x -> going to staging
            addTrigger(ManagerStates.IDLE, ManagerStates.GOING_TO_STAGING, DRIVER_CONTROLLER::getXButtonPressed);
    
            addTrigger(ManagerStates.GOING_TO_STAGING, ManagerStates.STAGING, () -> !dropperSensor.get());
            addTrigger(ManagerStates.STAGING, ManagerStates.SCORING, DRIVER_CONTROLLER::getXButtonPressed);
            addTrigger(ManagerStates.SCORING, ManagerStates.IDLE, () -> !dropperSensor.get());
            
            addTrigger(ManagerStates.SHOOTING, ManagerStates.IDLE, () -> this.getStateTime() > ManagerConstants.shootingTime);

        //from intaking
            addTrigger(ManagerStates.INTAKING, ManagerStates.GOING_TO_STAGING, DRIVER_CONTROLLER::getXButtonPressed);
            addTrigger(ManagerStates.INTAKING, ManagerStates.IDLE, DRIVER_CONTROLLER::getBButtonPressed);
            addTrigger(ManagerStates.INTAKING, ManagerStates.OUTTAKING, DRIVER_CONTROLLER::getRightBumperPressed);
            addTrigger(ManagerStates.INTAKING, ManagerStates.IDLE, () -> !intakeSensor.get());

            addTrigger(ManagerStates.OUTTAKING, ManagerStates.INTAKING, DRIVER_CONTROLLER::getRightBumperButtonReleased);

        //can't use trigger for autoalign bc i didn't use the subsystem framework with managerstates
            if(DRIVER_CONTROLLER.getLeftBumperButtonPressed()) {
                autoAlign = AutoAlignStates.DROPPER_GOAL;
                drive.autoAlign();
            }
            //buttons dont' really make sense
            if(DRIVER_CONTROLLER.getLeftBumperButtonPressed() && DRIVER_CONTROLLER.getAButtonPressed()) {
                autoAlign = AutoAlignStates.SHOOTING_GOAL;
                drive.autoAlign();
            }


            intake.periodic();
            shooter.periodic();
            dropper.periodic();
    } 

    // where does this go
    public AutoAlignStates getAutoAlignState() {
        return this.autoAlign;
    }
}