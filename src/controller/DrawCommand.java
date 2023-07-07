package controller;

import view.gui.PaintCanvas;
import view.interfaces.ICommand;
import model.persistence.Point;
import java.awt.*;
import java.util.Stack;


/**
 * @author lilred
 * @date 2023/07/05
 **/
public class DrawCommand implements IUndoable, ICommand {

    PaintCanvas pc;
    Point startPoint;
    Point endPoint;

    public DrawCommand(PaintCanvas pc, Point startPoint, Point endPoint) {
        this.pc = pc;
        this.startPoint = new Point();
        this.endPoint = new Point();
        this.startPoint.x = startPoint.x;
        this.startPoint.y = startPoint.y;
        this.endPoint.x = endPoint.x;
        this.endPoint.y = endPoint.y;


    }
    @Override
    public void run() {
        Graphics g = pc.getGraphics();
        Graphics2D graphics2d = (Graphics2D)g;
        int width = endPoint.x - startPoint.x;
        int height = endPoint.y - startPoint.y;
        graphics2d.fillRect(startPoint.x, startPoint.y, width, height);
        System.out.println("draw");

    }

    public void addToHistory() {
        CommandHistory.add(this);

        System.out.println("add to history");
    }

    @Override
    public void undo(Stack<DrawCommand> undoStack, Stack<DrawCommand> redoStack) {
        Graphics g = pc.getGraphics();
        Graphics2D graphics2d = (Graphics2D) g;

        graphics2d.clearRect(0, 0, pc.getWidth(), pc.getHeight());

        pc.paint(graphics2d);
        for (DrawCommand drawCommand : undoStack) {

            graphics2d.fillRect(drawCommand.startPoint.x, drawCommand.startPoint.y, drawCommand.endPoint.x - drawCommand.startPoint.x, drawCommand.endPoint.y - drawCommand.startPoint.y);
        }
        System.out.println("undo");

    }

    @Override
    public void redo(Stack<DrawCommand> undoStack, Stack<DrawCommand> redoStack) {
        Graphics g = pc.getGraphics();
        Graphics2D graphics2d = (Graphics2D) g;

        graphics2d.clearRect(0, 0, pc.getWidth(), pc.getHeight());

        pc.paint(graphics2d);
        for (DrawCommand drawCommand : undoStack) {

            graphics2d.fillRect(drawCommand.startPoint.x, drawCommand.startPoint.y, drawCommand.endPoint.x - drawCommand.startPoint.x, drawCommand.endPoint.y - drawCommand.startPoint.y);
        }


        System.out.println("redo");
    }

}

