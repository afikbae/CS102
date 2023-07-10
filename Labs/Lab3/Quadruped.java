public class Quadruped extends Vehicle {
    private static int no = 1;

    public Quadruped(String name, float speed, float position, int fuel) {
        super(name, speed, position, fuel);
        roadMultipliers = new float[] {0.50f, 1.00f, 0.75f};
        no++;
    }
    
    public static int getNo ()
    {
        return no;
    }
}
