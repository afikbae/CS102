public class UnionArea {

    private Rectangle r1;
    private Rectangle r2;
    private boolean inputTaken;

    public UnionArea () 
    {
        inputTaken = false;
    }

    public void input (Rectangle r1, Rectangle r2)
    {
        this.r1 = r1;
        this.r2 = r2;
        inputTaken = true;
    }

    public void displayQuestion ()
    {
        if (inputTaken)
        {
            System.out.printf("\n- What is the area of the union of two rectangles R1 and R2, where top left corner of R1 is (%d,%d) and its size is (%d,%d),"
                 + " and top left corner of R2 is (%d,%d) and its size is (%d,%d)?\n", r1.x, r1.y, r1.w, r1.h
                                                                                     , r2.x, r2.y, r2.w, r2.h);
        }
        else
        {
            System.out.println("\n- What is the area of the union of two rectangles R1 and R2, where top left corner of R1 is (X1,Y1) and its size is (W1,H1),"
                 + " and top left corner of R2 is (X2,Y2) and its size is (W2,H2)?");
        }
    }

    public void displayResult () 
    {
        System.out.printf("\nResult: Intersection area is %d thus the total area of the union is %d.\n", r1.intersect(r2).getArea(), Rectangle.findAreaOfUnion(r1, r2));
    }
}
