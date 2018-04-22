import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Dot {

    private Color color;
    int x,y;
    private final int RADIUS = 3;

    public Dot (int x1, int y1, Color color1) {
        color = color1;
        x = x1;
        y = y1;
    }

    public void draw (Graphics page) {
        page.fillOval(x,y,RADIUS,RADIUS);
    }
}
