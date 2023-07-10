public class Rectangle
{
    public int x;
    public int y;

    public int w;
    public int h;

    public Rectangle (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean intersects (Rectangle r)
    {
        return abs((this.x + this.w / 2) - (r.x + r.w / 2)) < (this.w + r.w) / 2
        &&  abs((this.y + this.h / 2) - (r.y + r.h / 2)) < (this.h + r.h) / 2;
    }

    /*
     * inspired from java.awt.Rectangle.intersect
     */

    public Rectangle intersect (Rectangle r)
    {
        if (this.intersects(r))
        {
            int iX = max(this.x, r.x);
            int iY = max(this.y, r.y);
            int iW = min(this.x + this.w, r.x + r.w) - iX;
            int iH = min(this.y + this.h, r.y + r.h) - iY;
            return new Rectangle(iX, iY, iW, iH);
        }
        else
        {
            return new Rectangle(0, 0, 0, 0);
        }
    }

    public static int findAreaOfUnion (Rectangle r1, Rectangle r2)
    {
        if (!r1.intersects(r2))
        {
            return 0;
        }
        Rectangle intersection = r1.intersect(r2);
        int unionArea = r1.getArea() + r2.getArea() - intersection.getArea();
        return unionArea;
    }

    private static double abs (double x)
    {
        if (x >= 0)
        {
            return x;
        }
        else
        {
            return -x;
        }
    }

    private static int max (int a, int b)
    {
        if (a < b)
        {
            return b;
        }
        return a;
    }

    private static int min (int a, int b)
    {
        if (a < b)
        {
            return a;
        }
        return b;
    }

    public int getArea()
    {
        return this.w * this.h;
    }
}