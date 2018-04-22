import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class WavePanel extends JPanel {

    private Color color;
    private Timer timer;
    private Dimension d;
    private int delay, waveWidth, waveHeight, time, step, maxX, maxY;
    private ArrayList<Dot> ptList;

    public WavePanel (Color color) {
        this.color = color;
        ptList = new ArrayList<>();
        waveHeight = 72;
        waveWidth = 50;
        delay = 20;
        step = 1;
        time = 0;
        Dimension d = this.getSize();
        maxX = (int)d.getWidth();
        maxY = (int)d.getHeight();
        timer = new Timer(delay, new WaveListener());
        timer.start();

    }

    public void resume() {
        time = 0;
        timer.start();
    }

    public void clearPanel() {
        ptList.clear();
        repaint();
        time = 0;
    }

    public void changeColor (Color anotherColor) {
        color = anotherColor;
    }

    public void setWaveWidth (int newWidth) {
        waveWidth = newWidth;
    }

    public void setDelay (int delayNum) {
        delay = delayNum;
    }

    public void paintComponent (Graphics page) {
        for (int i = 0; i < ptList.size(); i++)
            ptList.get(i).draw(page);
    }

    private class WaveListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            int x = (waveWidth*time)/50;
            int y = (int)(waveHeight*Math.sin((0.0174533)*time)+85);
            Dot newDot = new Dot(x,y,color);
            ptList.add(newDot);
            repaint();
            if (y < 0 || y > maxY || x > maxX)
                timer.stop();
        }
    }
}
