package voronoi;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Point;

public class Frame extends JFrame {

    Border border;
    voronoi.Point[] points;
    voronoi.Point[][] crossing_points;
    Line[][] lines;
    Boolean[][] crossing_lines;
    Graphics graphics;

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
        straights_equation();
    }

    void points_generator()
    {
        points = new voronoi.Point[6];
        crossing_points = new voronoi.Point[5][];
        int counter=5;
        for(int i=0; i<5; i++)
        {
            crossing_points[i]=new voronoi.Point[counter];
            counter--;
        }
        counter=0;

        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5-counter; j++) crossing_points[i][j]=new voronoi.Point();
            counter++;
        }

        for(int i=0; i<6; i++)
        {
            points[i] = new voronoi.Point();
            System.out.println("Point " + (i + 1) + ". parametres: x: " + points[i].x + " ,y: " + points[i].y);
            while(points_validation(i)==false) {
                points[i] = new voronoi.Point();
                System.out.println("Point " + (i + 1) + ". parametres: x: " + points[i].x + " ,y: " + points[i].y);
            }
        }

        /*counter=0;
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5-counter; j++)
            {
                crossing_points[i][j]= new Point();
                crossing_points[i][j].x=(points[i].x+points[j].x)/2;
                crossing_points[i][j].x=(points[i].y+points[j].y)/2;;
                counter++;
            }
        }*/

        crossing_points[0][0].x=(points[0].x+points[1].x)/2;
        crossing_points[0][0].y=(points[0].y+points[1].y)/2;

        crossing_points[0][1].x=(points[0].x+points[2].x)/2;
        crossing_points[0][1].y=(points[0].y+points[2].y)/2;

        crossing_points[0][2].x=(points[0].x+points[3].x)/2;
        crossing_points[0][2].y=(points[0].y+points[3].y)/2;

        crossing_points[0][3].x=(points[0].x+points[4].x)/2;
        crossing_points[0][3].y=(points[0].y+points[4].y)/2;

        crossing_points[0][4].x=(points[0].x+points[5].x)/2;
        crossing_points[0][4].y=(points[0].y+points[5].y)/2;

        crossing_points[1][0].x=(points[1].x+points[2].x)/2;
        crossing_points[1][0].y=(points[1].y+points[2].y)/2;

        crossing_points[1][1].x=(points[1].x+points[3].x)/2;
        crossing_points[1][1].y=(points[1].y+points[3].y)/2;

        crossing_points[1][2].x=(points[1].x+points[4].x)/2;
        crossing_points[1][2].y=(points[1].y+points[4].y)/2;

        crossing_points[1][3].x=(points[1].x+points[5].x)/2;
        crossing_points[1][3].y=(points[1].y+points[5].y)/2;

        crossing_points[2][0].x=(points[2].x+points[3].x)/2;
        crossing_points[2][0].y=(points[2].y+points[3].y)/2;

        crossing_points[2][1].x=(points[2].x+points[4].x)/2;
        crossing_points[2][1].y=(points[2].y+points[4].y)/2;

        crossing_points[2][2].x=(points[2].x+points[5].x)/2;
        crossing_points[2][2].y=(points[2].y+points[5].y)/2;

        crossing_points[3][0].x=(points[3].x+points[4].x)/2;
        crossing_points[3][0].y=(points[3].y+points[4].y)/2;

        crossing_points[3][1].x=(points[3].x+points[5].x)/2;
        crossing_points[3][1].y=(points[3].y+points[5].y)/2;

        crossing_points[4][0].x=(points[4].x+points[5].x)/2;
        crossing_points[4][0].y=(points[4].y+points[5].y)/2;

        counter=0;
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5-counter; j++)
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

    void straights_equation()
    {
        lines = new Line[5][];
        int counter=5;
        for(int i=0; i<5; i++)
        {
            lines[i]=new Line[counter];
            counter--;
        }

        counter=0;
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5-counter; j++) lines[i][j] = new voronoi.Line(points[i],points[j]);
            counter++;
        }

        counter=0;
        for(int i=0; i<15; i++) {
            for (int j = 0; j < 5 - counter; j++)
                if(i!=j)System.out.println("Line " + (i+1) + " -> " + (j+1) + " : y = " + (double)lines[i][j].slope + "x + " + (double)lines[i][j].intercept);
            counter++;
        }
    }

    /*Boolean[][] crossing_lines(Line[][] lines)
    {
        //Array initialization
        crossing_lines=new Boolean[5][];
        int counter=5;
        for(int i=0; i<5; i++)
        {
            crossing_lines[i]=new Boolean[counter];
            counter--;
        }

        //Checking crossings
        counter=1;
        for(int i=0; i<6; i++)
        {
            for(int j=counter; j<6; j++)
            {

            }
        }
    }*/

    public void paint(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(7));
        g2D.setPaint(Color.BLACK);

        //Drawing generated points
        for(int i=0; i<6; i++)
        {
            g2D.setPaint(Color.BLACK);
            g2D.drawOval(points[i].x, points[i].y, 2, 2);
        }

        //Drawing lines
        g2D.setStroke(new BasicStroke(1));
        int counter=1;
        for(int i=0; i<6; i++)
        {
            for(int j=counter; j<6; j++)
            {
                g2D.drawLine(points[i].x, points[i].y, points[j].x, points[j].y);
            }
        }

        //Drawing crossing points
        counter=0;
        int number_of_color_g=153, number_of_color_b=153;
        g2D.setStroke(new BasicStroke(5));
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5-counter; j++)
            {
                g2D.setPaint(new Color(201, number_of_color_g, number_of_color_b, 255));
                g2D.drawOval(crossing_points[i][j].x, crossing_points[i][j].y, 2, 2);
            }
            number_of_color_g-=20;
            number_of_color_b-=20;
            counter++;
        }
    }
}