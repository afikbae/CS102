public class VolumeFilling {
    private static int gcd (int a, int b)
    {
        while (a != b)
        {
            if (a > b)
            {
                a -= b;
            }
            else 
            {
                b -= a;
            }
        }
        return a;
    }

    private static int findEdgeOfLargestCube(int a, int b, int c)
    {
        int edgeLength = gcd(a, gcd(b, c));
        return edgeLength;
    }

    private int X;
    private int Y;
    private int Z;
    private boolean inputTaken;

    public VolumeFilling()
    {
        inputTaken = false;
    }

    public void input (int X, int Y, int Z)
    {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        inputTaken = true;
    }

    public void displayQuestion ()
    {
        if (inputTaken)
        {
            System.out.printf("\n- A rectangular prism volume of dimensions %d, %d and %d is to be filled using cube blocks."
            + "What is the minimum number of cubes required?\n", this.X, this.Y, this.Z);
        }
        else
        {
            System.out.println("\n- A rectangular prism volume of dimensions X, Y and Z is to be filled using cube blocks."
            + "What is the minimum number of cubes required?");
        }
    }

    public void displayResult ()
    {
        int edgeLength = findEdgeOfLargestCube(X, Y, Z);
        System.out.printf("\nResult: Using cubes of edge length %d you need %d blocks minimum.\n"
        , edgeLength
        , (X / edgeLength) * (Y / edgeLength) * (Z / edgeLength));
    }
}
