package view.gui;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PaintCanvas extends JComponent {

    private static PaintCanvas pc;

    private PaintCanvas(){};

    public static PaintCanvas getInstance() {
        if (pc == null) {
            // Create the instance only if it doesn't exist yet
            pc = new PaintCanvas();
        }
        return pc;
    }

    public static Graphics2D get2D() {
        if (pc == null) {
            // Create the instance only if it doesn't exist yet
            pc = new PaintCanvas();
        }
        Graphics g =  pc.getGraphics();
        Graphics2D graphics2D = (Graphics2D)g;
        return graphics2D;
    }


    @Override
    public void paint(Graphics g) {

        Graphics2D graphics2d = (Graphics2D)g;

        // Draw all shapes here
//        graphics2d.setBackground(Color.WHITE);
        graphics2d.setColor(Color.WHITE);
        graphics2d.fillRect(0, 0, getWidth(), getHeight());


        // For example purposes only; remove all lines below from your final project.
        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(-120, -120, 200, 400);

        // Outlined rectangle
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(12, 13, 200, 400);

        // Selected Shape
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(7, 8, 210, 410);



    }

    public void repaint(){
        Graphics g = pc.getGraphics();
        Graphics2D graphics2d = (Graphics2D)g;
        paint(graphics2d);
    }
}
