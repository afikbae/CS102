import java.util.Scanner;
import java.util.Random;

/**
 * ProblemSolver
 */

 public class ProblemSolver {

    static Scanner in = new Scanner(System.in);    
    static Random rand = new Random();

    public static int integerInput(String s)
    {
        while (true)
        {
            System.out.print(s);
            if (in.hasNextInt())
            {
                return in.nextInt();
            }
            else
            {
                in.nextLine();
            }
        }
    }

    public static int integerInput()
    {
        return integerInput("");
    }

    public static void main(String[] args) {
        int option = 0;


        while (option != 5)
        {
            System.out.println("\n1. Prime Numbers\n2. Volume Filling\n3. Union Area\n4. Random Questions\n5. Exit\n");

            do
            {
                option = integerInput("Please enter your choice: ");
            }
            while (option < 1 || option > 5);

            if (option == 1)
            {
                PrimeNumbers question = new PrimeNumbers();
                question.displayQuestion();
                System.out.print("Please enter X, Y: ");
                int X = integerInput();
                int Y = integerInput();
                question.input(X, Y);
                question.displayResult();
            }

            else if (option == 2)
            {
                VolumeFilling question = new VolumeFilling();
                question.displayQuestion();
                System.out.print("Please enter X, Y, Z: ");
                int X = integerInput();
                int Y = integerInput();
                int Z = integerInput();
                question.input(X, Y, Z);
                question.displayResult();    
            }

            else if (option == 3)
            {
                UnionArea question = new UnionArea();
                question.displayQuestion();
                System.out.print("Please enter X1, Y1, W1, H1, X2, Y2, W2, H2: ");
                Rectangle r1 = new Rectangle(integerInput(), integerInput(), integerInput(), integerInput());
                Rectangle r2 = new Rectangle(integerInput(), integerInput(), integerInput(), integerInput());
                question.input(r1, r2);
                question.displayResult();
            }

            else if (option == 4)
            {
                int noOfQs = integerInput("Please enter the number of questions you want: ");
                System.out.println(noOfQs);
                for (int i = 0; i < noOfQs; i++)
                {
                    int random = rand.nextInt(3);
                    if (random == 0)
                    {
                        PrimeNumbers question = new PrimeNumbers();
                        int random1 = rand.nextInt(50);
                        int random2 = random1 + 1 + rand.nextInt(50 - random1);
                        question.input(random1, random2);
                        question.displayQuestion();
                        question.displayResult();
                    }
                    else if (random == 1)
                    {
                        VolumeFilling question = new VolumeFilling();
                        int k = rand.nextInt(10) + 1;
                        int a = rand.nextInt(10) + 1;
                        int b = rand.nextInt(10) + 1;
                        int c = rand.nextInt(10) + 1;
                        question.input(a*k, b*k, c*k);
                        question.displayQuestion();
                        question.displayResult();
                    }
                    else if (random == 2)
                    {
                        UnionArea question = new UnionArea();
                        int x1 = rand.nextInt(10);
                        int y1 = rand.nextInt(10);
                        int w1 = rand.nextInt(10);
                        int h1 = rand.nextInt(10);
                        int x2 = rand.nextInt(10);
                        int y2 = rand.nextInt(10);
                        int w2 = rand.nextInt(10);
                        int h2 = rand.nextInt(10);
                        Rectangle r1 = new Rectangle(x1, y1, w1, h1);
                        Rectangle r2 = new Rectangle(x2, y2, w2, h2);
                        question.input(r1, r2);
                        question.displayQuestion();
                        question.displayResult();
                    }
                }
            }
        }
    }
}