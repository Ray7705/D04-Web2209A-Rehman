package helicopter.helicopters;

public class AttackHelicopter extends Helicopter {

    public AttackHelicopter()
    {
        super();
    }

    @Override
    public String toString()
    {
        return "AttackHelicopter #" + getId();
    }

    @Override
    public double getFuelRate()
    {
        return 3.0 / 100.0;
    }

    @Override
    public double getMaxFuelLevel()
    {
        return 500;
    }

    @Override
    public double getMaxAltitude()
    {
        return 10000;
    }


}
