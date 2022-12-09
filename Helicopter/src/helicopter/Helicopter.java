package helicopter;

public class Helicopter {

    private static int nextId = 1000;
    private int id;
    private double fuelLevel;
    private double altitude;
    private boolean engineRunning;

    //Constructor
    public Helicopter(){
        id = netxtId++;
        fuelLevel = 100;
        altitude = 0;
        engineRunning = false;
    }

    //getters
    public int getId() {
        return id;
    }
    public double getFuelLevel() {
        return fuelLevel;
    }
    public double getAltitude() {
        return altitude;
    }
    public boolean isEngineRunning() {
        return engineRunning;
    }

    @Override
    public String toString(){
        return "Helicopter " + "#" + id;
    }




}
