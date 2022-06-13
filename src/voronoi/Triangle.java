package voronoi;

import java.awt.geom.Point2D;
import java.util.Arrays;

public class Triangle {

    Point[] vertex;
    double angle12, angle13, angle23;
    double largest_angle;
    Boolean if_obtuse;

    Triangle(Line line1, Line line2, Line line3) {
        vertex = new Point[3];
        this.vertex[0] = line1.tail;
        this.vertex[1] = line2.tail;
        this.vertex[2] = line3.tail;
        angle12 = Math.toDegrees(Math.abs((line1.slope-line2.slope)/(1+(line1.slope*line2.slope))));
        angle13 = Math.toDegrees(Math.abs((line1.slope-line3.slope)/(1+(line1.slope*line3.slope))));
        angle23 = Math.toDegrees(Math.abs((line2.slope-line3.slope)/(1+(line3.slope*line2.slope))));

        double[] toSort = new double[]{angle12,angle13,angle23};
        Arrays.sort(toSort);
        largest_angle = (toSort[0]);
        System.out.println("Angles: "+angle23+" "+angle13+" "+angle12);
    }

    Triangle(Point point1, Point point2, Point point3){
        vertex = new Point[3];
        this.vertex[0] = point1;
        this.vertex[1] = point2;
        this.vertex[2] = point3;

        // Sides length:
        double[] length = new double[3];
        length[0] = Point2D.distance(vertex[0].x,vertex[0].y,vertex[1].x,vertex[1].y);
        length[0] = Point2D.distance(vertex[0].x,vertex[0].y,vertex[2].x,vertex[2].y);
        length[0] = Point2D.distance(vertex[1].x,vertex[1].y,vertex[2].x,vertex[2].y);
        Arrays.sort(length);

        if(Math.pow(length[0],2)>Math.pow(length[1]+length[2],2)){
            this.if_obtuse = true;
        }
        else this.if_obtuse = false;
        System.out.println("Sides length: "+length[0]+" "+length[1]+" "+length[2]);
    }
}
