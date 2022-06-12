package voronoi;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    public static void main(String[] args)
    {
        Frame frame = new Frame();
        frame.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                System.out.println("Pressed");
            }
        });
    }
}
