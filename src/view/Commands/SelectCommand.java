package view.Commands;

import view.interfaces.IUndoable;
import model.persistence.Point;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.Stack;


/**
 * @author lilred
 * @date 2023/07/16
 **/
public class SelectCommand {

    public static void SelectOtherShapes(PaintCanvas pc, Point startPoint, Point endPoint){

        //calculate the min,max coordinates from this select movement

        int thisMaxX = Math.max(startPoint.x, endPoint.x);
        int thisMinX =Math.min(startPoint.x, endPoint.x);
        int thisMaxY = Math.max(startPoint.y, endPoint.y);
        int thisMinY = Math.min(startPoint.y, endPoint.y);


        //iterate through every history draw command, call the compareXY method to tell whether the history shape is
        // inside the scope of this select movement; change the shape's IsSelected according to compareXY's return value.
        Stack<IUndoable> undostack = CommandHistory.getUndoStack();
        for (IUndoable existShape: undostack ){

            if(existShape.compareXY(thisMaxX,thisMinX,thisMaxY,thisMinY)){

                existShape.setIsSelectedT();
            }else {
                existShape.setIsSelectedF();
            }
        }

        //after resetting the properties of selected history commands, repaint
        Graphics g = pc.getGraphics();
        Graphics2D graphics2d;
        graphics2d = (Graphics2D)g;
        pc.paint(graphics2d);

        for (IUndoable existShape: undostack){

            existShape.run();
        }
    }

}
