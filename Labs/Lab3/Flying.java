public class Flying extends Vehicle {
    private static int no = 1;

    public Flying(String name, float speed, float position, int fuel) {
        super(name, speed, position, fuel);
        roadMultipliers = new float[] {1.00f, 1.00f, 1.00f};
        no++;
    }
    
    public static int getNo ()
    {
        return no;
    }
}
