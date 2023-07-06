package view;


import controller.DrawCommand;
import model.persistence.Point;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author lilred
 * @date 2023/06/26
 **/
public class ClickHandler extends MouseAdapter {
    private PaintCanvas paintCanvas;
    public ClickHandler(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
    }


    Point startPoint = new Point();
    Point endPoint = new Point();

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint.x = e.getX();
        startPoint.y = e.getY();
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint.x = e.getX();
        endPoint.y = e.getY();

        DrawCommand drawCommand = new DrawCommand(paintCanvas, startPoint, endPoint);
        drawCommand.run();
        System.out.println("released");
        drawCommand.addToHistory();


    }
}
