package voronoi;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Frame extends JFrame {

    Border border;
    voronoi.Point[] points;
    voronoi.Point[][] crossing_points;
    Line[][] lines;
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
        // Declaratrion of arrays, selecting points:
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

        // Validation of randomly picked points:
        for(int i=0; i<6; i++)
        {
            points[i] = new voronoi.Point();
            System.out.println("Point " + (i + 1) + ". parametres: x: " + points[i].x + " ,y: " + points[i].y);
            while(points_validation(i)==false) {
                points[i] = new voronoi.Point();
                System.out.println("Point " + (i + 1) + ". parametres: x: " + points[i].x + " ,y: " + points[i].y);
            }
        }

        // Calculating crossing points:
        counter=0;
        int pom1 = 1, pom2=1;
        for(int i=0; i<5; i++)
        {
            counter = pom2-1;
            for(int j=0; j<5-counter; j++)
            {
                crossing_points[i][j] = new voronoi.Point((points[i].x+points[pom1].x)/2,(points[i].y+points[pom1].y)/2);
                pom1++;
            }
            pom2++;
            pom1 = pom2;
        }

        // Output of crossing point parametres:
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