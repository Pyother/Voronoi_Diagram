package voronoi;

public class Circle {

    Point centre;
    double radius;
    Boolean location;

    public Circle(Point point, int r) {
        this.centre = point;
        this.radius = r;
        //this.check_centre();
    }

    /*void check_centre(){
        if(this.centre.x<500||this.centre.x>0
                && this.centre.y<500||this.centre.y>0)
            this.location = true;
        else location = false;
    }*/

}
