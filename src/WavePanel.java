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
        this.setBackground(Color.WHITE);
        this.color = color;
        ptList = new ArrayList<>();
        waveHeight = 72;
        waveWidth = 50;
        delay = 20;
        step = 1;
        time = 0;
        maxX = 535;
        //maxY = (int)d.getHeight();
        timer = new Timer(delay, new WaveListener());

        timer.start();
        repaint();

    }

    public void resume() {
        repaint();
        timer.start();
    }

    public void clearPanel() {
        ptList.clear();
        repaint();
        time = 0;
        timer.stop();
    }

    public void changeColor (Color anotherColor) {
        color = anotherColor;
    }

    public void setWaveWidth (int newWidth) {
        waveWidth = newWidth;
    }

    public void setDelay (int delayNum) {
        delay = delayNum;
        timer.setDelay(delay);
    }

    public void paintComponent (Graphics page) {
        super.paintComponent(page);
        page.setColor(color);
        maxX = this.getWidth();
        for (int i = 0; i < ptList.size(); i++) {
            ptList.get(i).draw(page);
        }
    }

    private class WaveListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            time += step;
            int x = (waveWidth*time)/50;
            int y = (int)(waveHeight*Math.sin((0.0174533)*time)+85);
            Dot newDot = new Dot(x,y,color);
            ptList.add(newDot);
            repaint();
            if (x > maxX)
                timer.stop();
        }
    }
}
