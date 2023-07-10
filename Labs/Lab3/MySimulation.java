import java.util.Scanner;

public class MySimulation {
    public static void main(String[] args) {
        Simulation sim = new Simulation();
        Scanner in = new Scanner(System.in);
        String play = "y";
        while (play.equals("y"))
        {
            System.out.print("Please enter the road length: ");
            sim.createRoad(in.nextFloat());
            System.out.print("Please enter the vehicle amount: ");
            sim.createVehicles(in.nextInt());
            sim.simulate();
            System.out.print("End of simulation. Do you wanna play again? (y/n)");
            play = in.next();
        }
        in.close();
    }
}
