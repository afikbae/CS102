public abstract class Vehicle 
{
    private String name;
    private float speed;
    private float position;
    private int fuel;
    protected float[] roadMultipliers;
    
    public Vehicle(String name, float speed, float position, int fuel) {
        this.name = name;
        this.speed = speed;
        this.position = position;
        this.fuel = fuel;
    }

    public String getName() {
        return name;
    }

    public float getSpeed() {
        return speed;
    }

    public float getPosition() {
        return position;
    }

    public int getFuel() {
        return fuel;
    }

    public float getRoadMultiplier(int n) {
        return roadMultipliers[n];
    }

    private boolean decFuel ()
    {
        if (fuel < 1)
        {
            return false;
        }
        fuel--;
        return true;
    }

    public boolean move(int roadType)
    {
        if (decFuel())
        {
            position += speed * roadMultipliers[roadType];
            return true;
        }
        return false;
    }

    public String toString ()
    {
        return String.format("%s - Position: %.2f - Speed %.2f - Fuel: %d", name, position, speed, fuel);
    }
}