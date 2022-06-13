package voronoi;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Random;

public class Frame extends JFrame {

    Border border;
    voronoi.Point[] points;
    voronoi.Point[][] crossing_points;
    voronoi.Point[] infinity_points1;
    Line[][] lines;
    Line[] crossing_lines;
    Graphics graphics;
    voronoi.Point pom[];

    Frame()
    {
        border = BorderFactory.createLineBorder(new Color(124, 38, 2, 255),4);
        graphics = this.getGraphics();

        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(255, 234, 209, 255));
        this.getRootPane().setBorder(border);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        points_generator();
        //straights_equation();
    }

    void points_generator()
    {
        // Declaratrion of arrays, selecting points:
        points = new Point[3];
        crossing_points = new Point[2][];
        int counter=2;
        for(int i=0; i<2; i++)
        {
            crossing_points[i]=new Point[counter];
            counter--;
        }
        counter=0;
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<2-counter; j++) crossing_points[i][j]=new Point();
            counter++;
        }

        points[0] = new voronoi.Point(100,200);
        points[1] = new voronoi.Point(300,300);
        points[2] = new voronoi.Point(200,400);

        for(int i=0; i<3; i++) points[i] = new Point();

        // Lines between points:
        Line[] lines = new Line[3];
        /*lines[0] = new Line(points[0], points[1]);
        lines[1] = new Line(points[0], points[2]);
        lines[2] = new Line(points[1], points[2]);*/

        double[] length = new double[3];
        length[0]= Math.sqrt((points[0].x - points[1].x)*(points[0].x - points[1].x) + (points[0].y - points[1].y)*(points[0].y - points[1].y));
        length[1]= Math.sqrt((points[0].x - points[2].x)*(points[0].x - points[2].x) + (points[0].y - points[2].y)*(points[0].y - points[2].y));
        length[2]= Math.sqrt((points[1].x - points[2].x)*(points[1].x - points[2].x) + (points[1].y - points[2].y)*(points[1].y - points[2].y));
        Arrays.sort(length);
        System.out.println("Sides length: "+length[0]+" "+length[1]+" "+length[2]);

        while(length[2]*length[2]>=length[1]*length[1]+length[0]*length[0]) {
            // Validation of randomly picked points:
            for(int i=0; i<3; i++){
                points[i].x = myRandom1();
                points[i].y = myRandom1();
            }
            length[0]= Math.sqrt((points[0].x - points[1].x)*(points[0].x - points[1].x) + (points[0].y - points[1].y)*(points[0].y - points[1].y));
            length[1]= Math.sqrt((points[0].x - points[2].x)*(points[0].x - points[2].x) + (points[0].y - points[2].y)*(points[0].y - points[2].y));
            length[2]= Math.sqrt((points[1].x - points[2].x)*(points[1].x - points[2].x) + (points[1].y - points[2].y)*(points[1].y - points[2].y));
            Arrays.sort(length);
        }

        // Calculating crossing points:
        counter=0;
        int pom1 = 1, pom2=1;
        for(int i=0; i<2; i++)
        {
            counter = pom2-1;
            for(int j=0; j<2-counter; j++)
            {
                crossing_points[i][j] = new Point((points[i].x+points[pom1].x)/2,(points[i].y+points[pom1].y)/2);
                pom1++;
            }
            pom2++;
            pom1 = pom2;
        }

        // Output of crossing point parametres:
        counter=0;
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<2-counter; j++)
            {
                System.out.println("Crossing point of "+i+". and "+j+". parametres: x:" +crossing_points[i][j].x+ " y:" +crossing_points[i][j].y);
            }
            counter++;
        }
    }

    boolean points_validation(int i)
    {
        for(int j=0; j<i; j++)
        {
            if(points[j].x<points[i].x+30&&points[j].x>points[i].x-30||points[j].y<points[i].y+30&&points[j].y>points[i].y-30)
            {
                System.out.println("Redrawing point: prohibited distances between points: "+(j+1)+" "+(i+1));
                return false;
            }
            else if(points[i].x<50||points[i].x>450||points[i].y<50||points[i].y>450)
            {
                System.out.println("Redrawing point: point: "+(i+1)+". is too close the border");
                return false;
            }
            else
            {
                return true;
            }
        }
        return true;
    }

    void infinity_lines(Circle circle) {
        crossing_lines = new Line[3];
        crossing_lines[0] = new Line(circle.centre, crossing_points[0][0]);
        crossing_lines[1] = new Line(circle.centre, crossing_points[0][1]);
        crossing_lines[2] = new Line(circle.centre, crossing_points[1][0]);

        pom = new Point[3];
        pom[0] = crossing_points[0][0];
        pom[1] = crossing_points[0][1];
        pom[2] = crossing_points[1][0];

        // Calculating infinty points:
        infinity_points1 = new Point[3];

        double angle12 = Math.toDegrees(Math.abs((crossing_lines[0].slope-crossing_lines[1].slope)/(1+(crossing_lines[0].slope*crossing_lines[1].slope))));
        double angle13 = Math.toDegrees(Math.abs((crossing_lines[0].slope-crossing_lines[2].slope)/(1+(crossing_lines[0].slope*crossing_lines[2].slope))));
        double angle23 = Math.toDegrees(Math.abs((crossing_lines[1].slope-crossing_lines[2].slope)/(1+(crossing_lines[2].slope*crossing_lines[1].slope))));

        double[] toSort = new double[]{angle12,angle13,angle23};
        Arrays.sort(toSort);
        System.out.println("Angles: "+angle23+" "+angle13+" "+angle12);

        for(int i=0; i<3; i++){
            if (circle.centre.x >= pom[i].x) {
                System.out.println(true);
                int point_x=0;
                int point_y=(int)crossing_lines[i].intercept;
                infinity_points1[i] = new Point(point_x, point_y);
            }
            if (circle.centre.x < pom[i].x) {
                System.out.println(false);
                int point_x=500;
                int point_y=(int)(crossing_lines[i].slope*500+crossing_lines[i].intercept);
                infinity_points1[i] = new Point(point_x, point_y);
            }
        }

        if(toSort[1]==angle12&&toSort[2]==angle13||toSort[1]==angle13&&toSort[2]==angle12){
            if (circle.centre.x >= pom[0].x) {
                int point_x=500;
                int point_y=(int)(crossing_lines[0].slope*500+crossing_lines[0].intercept);
                infinity_points1[0] = new Point(point_x, point_y);
            }
            if (circle.centre.x < pom[0].x) {
                int point_x=0;
                int point_y=(int)crossing_lines[0].intercept;
                infinity_points1[0] = new Point(point_x, point_y);
            }
        }
    }


    Circle findCircle(Point p1, Point p2, Point p3){

        int x12 = p1.x - p2.x;
        int x13 = p1.x - p3.x;
        int y12 = p1.y - p2.y;
        int y13 = p1.y - p3.y;
        int y31 = p3.y - p1.y;
        int y21 = p2.y - p1.y;
        int x31 = p3.x - p1.x;
        int x21 = p2.x - p1.x;
        int sx13 = (int)(Math.pow(p1.x, 2) -
                Math.pow(p3.x, 2));
        int sy13 = (int)(Math.pow(p1.y, 2) -
                Math.pow(p3.y, 2));
        int sx21 = (int)(Math.pow(p2.x, 2) -
                Math.pow(p1.x, 2));
        int sy21 = (int)(Math.pow(p2.y, 2) -
                Math.pow(p1.y, 2));
        int f = ((sx13) * (x12)
                + (sy13) * (x12)
                + (sx21) * (x13)
                + (sy21) * (x13))
                / (2 * ((y31) * (x12) - (y21) * (x13)));
        int g = ((sx13) * (y12)
                + (sy13) * (y12)
                + (sx21) * (y13)
                + (sy21) * (y13))
                / (2 * ((x31) * (y12) - (x21) * (y13)));
        int c = -(int)Math.pow(p1.x, 2) - (int)Math.pow(p1.y, 2) -
                2 * g * p1.x - 2 * f * p1.y;
        int h = -g;
        int k = -f;
        double sqr_of_r = h * h + k * k - c;
        double r = Math.sqrt(sqr_of_r);
        return new Circle(new Point(h, k), (int)r);
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(7));
        g2D.setPaint(Color.BLACK);

        //Drawing generated points
        for(int i=0; i<3; i++)
        {
            g2D.setPaint(Color.BLACK);
            g2D.drawOval(points[i].x, points[i].y, 2, 2);
        }

        //Drawing lines
        /*g2D.setStroke(new BasicStroke(1));
        int counter=1;
        for(int i=0; i<3; i++)
        {
            for(int j=counter; j<3; j++)
            {
                g2D.drawLine(points[i].x, points[i].y, points[j].x, points[j].y);
            }
        }*/

        // Drawing crossing points:
        /*counter=0;
        int number_of_color_g=153, number_of_color_b=153;
        g2D.setStroke(new BasicStroke(5));
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<2-counter; j++)
            {
                g2D.setPaint(new Color(201, number_of_color_g, number_of_color_b, 255));
                g2D.drawOval(crossing_points[i][j].x, crossing_points[i][j].y, 2, 2);
            }
            number_of_color_g-=20;
            number_of_color_b-=20;
            counter++;
        }*/

        Circle circle = findCircle(points[0],points[1],points[2]);
        //g2D.drawOval(circle.centre.x, circle.centre.y, 3, 3);

        infinity_lines(circle);

        for(int i=0; i<3; i++) System.out.println("Infinity point: x = "+ infinity_points1[i].x+" y = "+ infinity_points1[i].y);

        for(int i=0; i<3; i++){
            g2D.setStroke(new BasicStroke(1));
            g2D.setPaint(Color.pink);
            g2D.drawLine(circle.centre.x, circle.centre.y, infinity_points1[i].x, infinity_points1[i].y);
        }

        /*g2D.setStroke(new BasicStroke(1));
        g2D.setPaint(Color.pink);
        g2D.drawLine(circle.centre.x, circle.centre.y, crossing_points[0][0].x, crossing_points[0][0].y);
        g2D.drawLine(circle.centre.x, circle.centre.y, crossing_points[0][1].x, crossing_points[0][1].y);
        g2D.drawLine(circle.centre.x, circle.centre.y, crossing_points[1][0].x, crossing_points[1][0].y);
        */



    }

    public int myRandom1()
    {
        Random number = new Random();
        return number.nextInt(500);
    }//Function which gives random number from 0 to 500


}