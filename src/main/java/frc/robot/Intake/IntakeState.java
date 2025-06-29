package frc.robot.Intake;

public enum IntakeState {
    OFF("Off", 90, 0),
    INTAKING("Intaking", 0, 10),
    FEEDING("Feeding", 90, -15),
    OUTTAKING("Outtaking", 0, -10);

    private final String stateString;
    private final double position;
    private final double speed;



    IntakeState (String stateString, double position, double speed) {
        this.stateString = stateString;
        this.position = position;
        this.speed = speed;
    }
    
    public String getStateString() {
        return stateString;
    }
    public double getPosition(){
        return position;
    }
    public double getSpeed(){
        return speed;
    }



    
}
