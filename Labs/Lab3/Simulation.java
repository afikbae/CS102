import java.util.Random;

public class Simulation {
    private int[] roadType;
    private float[] roadLength;
    private float totalLength;
    private Vehicle[] vehicles;


    public void simulate ()
    {
        int turn = 0;
        int winner = -1;
        boolean outOfFuel = false;
        while (winner == -1 && !outOfFuel)
        {
            outOfFuel = true;
            turn++;
            System.out.printf("\nTurn %d:\n%s", turn, vehiclesToString());
            System.out.print("\nMovements:\n");
            for (int i = 0; i < vehicles.length && winner == -1; i++)
            {
                Vehicle v = vehicles[i];
                System.out.printf("%s - moves from %s, for %.2f * %.2f = %.2f units\n", 
                v.getName(),
                intToRoad(getType(v.getPosition())),
                v.getSpeed(),
                v.getRoadMultiplier(getType(v.getPosition())),
                v.getSpeed() * v.getRoadMultiplier(getType(v.getPosition())));

                outOfFuel = !v.move(getType(v.getPosition()));

                if (v.getPosition() >= totalLength)
                {
                    winner = i;
                }
            }
        }
        if (outOfFuel) {
            System.out.println("\n All vehicles are out of fuel.");
        }
        else
        {
            Vehicle w = vehicles[winner];
            System.out.printf("\n%s finishes the race! Position: %.2f - Speed: %.2f - Fuel: %d\n",
            w.getName(), w.getPosition(), w.getSpeed(), w.getFuel());
        }
    }

    public void createVehicles (int amount)
    {
        vehicles = new Vehicle[amount];
        Random rand = new Random();


        for (int i = 0; i < amount; i++) {
            float r = rand.nextFloat(1);
            if (r < 0.40f)
            {
                Vehicle W = new Wheeled("W" + Wheeled.getNo(), rand.nextInt(11) + 15, 0, rand.nextInt(11) + 30);
                vehicles[i] = W;
            }
            else if (r < 0.65f)
            {
                Vehicle F = new Flying("F" + Flying.getNo(), rand.nextInt(11) + 20, 0, rand.nextInt(11) + 20);
                vehicles[i] = F;
            }
            else
            {
                Vehicle Q = new Quadruped("Q" + Quadruped.getNo(), rand.nextInt(21) + 20, 0, rand.nextInt(11) + 10);
                vehicles[i] = Q;
            }
        }

        String vString = vehiclesToString();
        for (int i = 0; i < vString.length();
         i = i == -1 ? i : i + 1) {
            vString = vString.substring(0, i + 5) + vString.substring(i + 22, vString.length());
            i = vString.indexOf("\n", i);
        }

        System.out.print("\nThe following vehicles are generated:\n" + vString);
    }

    public void createRoad (float length)
    {
        Random rand = new Random();
        totalLength = length;
        int pieces = rand.nextInt((int) (length / 10) + 1) + 1;
        
        roadType = new int[pieces];
        roadLength = new float[pieces];
        
        int sum = 0;
        for (int i = 0; i < roadLength.length - 1; i++) {
            roadType[i] = rand.nextInt(3);

            /**
             * inspried from
             * @author assylias  @date 26/03/2023 @address https://stackoverflow.com/questions/22380890/generate-n-random-numbers-whose-sum-is-m-and-all-numbers-should-be-greater-than
             */
            roadLength[i] = rand.nextFloat(totalLength - sum) / (pieces - i) + 5;
            sum += roadLength[i];
        }
        roadType[pieces - 1] = rand.nextInt(3);
        roadLength[pieces - 1] = totalLength - sum;
    
        System.out.println("\nThe following road is generated: \n" + roadToString() + "\n");
    }

    private String roadToString ()
    {
        String str = "|";

        for (int i = 0; i < roadLength.length; i++) {
            str += String.format("-%s %.2f-|", intToRoad(roadType[i]), roadLength[i]);
        }

        return str;
    }

    private String vehiclesToString ()
    {
        String str = "";

        for (Vehicle v : vehicles) {
            str += v + "\n";
        }

        return str;
    }

    private String intToRoad (int n)
    {
        switch (n) {
            case 0:
                return "Asphalt";
            case 1:
                return "Dirt";
            case 2:
                return "Stone";
            default:
                return "Invalid";
        }
    }

    public int getType (float position)
    {
        float sum = 0;
        for (int i = 0; i < roadLength.length; i++) {
            sum += roadLength[i];
            if (sum >= position)
            {
                return roadType[i];
            }
        }
        return -1;
    }
}
