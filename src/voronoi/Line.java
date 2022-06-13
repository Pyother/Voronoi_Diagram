package voronoi;

public class Line {
    Point head;
    Point tail;
    double slope;
    double intercept;
    Boolean if_reverse;

    Line(Point point1, Point point2)
    {
        this.head = point1;
        this.tail = point2;
        if(tail.x-head.x!=0) this.slope = ((double)tail.y-head.y)/(double)(tail.x-head.x);
        else if(tail.x-head.x==0f) this.slope = 0f;
        this.intercept = tail.y-slope*tail.x;
        this.if_reverse = false;
    }
}