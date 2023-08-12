package view.Commands;

import view.interfaces.ICommand;
import view.interfaces.IUndoable;
import model.persistence.Point;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;


/**
 * @author lilred
 * @date 2023/07/16
 **/
public class SelectCommand implements ICommand {

    Point startPoint;
    Point endPoint;


    public SelectCommand(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;

    }


    //calculate the min,max coordinates from this select movement
    public  Stack<IUndoable> SelectOtherShapes(){
        Stack<IUndoable> preSelection = new Stack<>();

        int thisMaxX = Math.max(startPoint.x, endPoint.x);
        int thisMinX =Math.min(startPoint.x, endPoint.x);
        int thisMaxY = Math.max(startPoint.y, endPoint.y);
        int thisMinY = Math.min(startPoint.y, endPoint.y);


        //iterate through main-stack, call each of the shapes' compareXY method to tell if overlap with this selection.
        // change the shape's selected status accordingly.
        // at this point, all overlap shapes are both selected & showAsSelected
        Stack<IUndoable> undostack = CommandHistory.getUndoStack();

        for (IUndoable existShape: undostack ){

            if(existShape.compareXY(thisMaxX,thisMinX,thisMaxY,thisMinY)){

                existShape.setIsSelected(true);
                existShape.setShowAsSelected(true);
                preSelection.add(existShape);


            }else {
                existShape.setIsSelected(false);
                existShape.setShowAsSelected(false);
            }
        }

        return preSelection;
    }

    @Override
    public void run() {

    }

    @Override
    public void addToHistory() {

    }
}
