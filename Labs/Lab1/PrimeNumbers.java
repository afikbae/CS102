import java.util.ArrayList;

public class PrimeNumbers {
    private static ArrayList<Integer> findPrimeNumbersBetween(int start, int end)
    {
        ArrayList<Integer> primes = new ArrayList<>(); 
        for (int i = start; i <= end; i++)
        {
            if (isPrime(i))
            {
                primes.add(i);
            }
        }
        return primes;
    }

    private static boolean isPrime (int x)
    {
        if (x <= 1)
        {
            return false;
        }
        for (int i = 2; i * i <= x; i++)
        {
            if (x % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    private int X;
    private int Y;
    private boolean inputTaken;

    public PrimeNumbers ()
    {
        inputTaken = false;
    }

    public void input (int X, int Y)
    {
        this.X = X;
        this.Y = Y;
        inputTaken = true;
    }

    public void displayQuestion ()
    {
        if (inputTaken)
        {
            System.out.printf("\n- Find the prime numbers in the range between %d and %d.\n", this.X, this.Y);
        }
        else
        {
            System.out.println("\n- Find the prime numbers in the range between X and Y.");
        }
    }

    public void displayResult ()
    {
        ArrayList<Integer> primes = findPrimeNumbersBetween(X, Y);
        
        if (primes.size() > 0)
        {
            System.out.printf("\nResult: Prime numbers in range [%d, %d] are ", X, Y);
            for (int i = 0; i < primes.size() - 1; i++)
            {
                System.out.print(primes.get(i) + ", ");
            }
            System.out.printf("%d.\n", primes.get(primes.size() - 1));
        }
        else
        {
            System.out.printf("\nResult: There are no prime numbers in range [%d, %d].", X, Y);
        }
    }
}
