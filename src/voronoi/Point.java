package voronoi;

import java.util.Random;

public class Point {

    int x;
    int y;

    public int myRandom1()
    {
        Random number = new Random();
        return number.nextInt(500);
    }//Function which gives random number from 0 to 500

    Point()
    {
        this.x=myRandom1();
        this.y=myRandom1();

    }

    Point(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
}
