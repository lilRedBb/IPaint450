package view.Commands;

import view.drawhandler.DrawHandlerShade;
import view.interfaces.IUndoable;
import model.interfaces.IApplicationState;
import model.persistence.ColorUtil;
import model.persistence.Point;
import view.gui.PaintCanvas;
import view.interfaces.ICommand;

import java.awt.*;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/07/13
 **/
public class DrawFatherCommand implements IUndoable, ICommand,Cloneable {
    //inherited by DrawOval,DrawRect,DrawTriangle,MoveCommand

    IApplicationState appstate; //take in the shared IApplicationState
    Point startPoint;   //take in Point object from ClickHandler
    Point endPoint;     //take in Point object from ClickHandler

    Graphics2D graphics2d; //take in shared Graphics2D

    Color color;  //Color object generated from IApplicationState's primary color
    Color color2; //Color object generated from IApplicationState's secondary color

    String shade; //String object generated from IApplicationState's shading type

    boolean IsSelected;  //if this shape is selected by the select movement

    boolean IsDrawCommand=true; //if this command's function is to draw a shape


    public DrawFatherCommand( Point startPoint, Point endPoint, IApplicationState appstate) {

        this.appstate = appstate;

        this.graphics2d = PaintCanvas.get2D();

        this.startPoint = new Point(startPoint.x, startPoint.y);
        this.endPoint = new Point(endPoint.x, endPoint.y);

        String colorString = appstate.getActivePrimaryColor().toString();
        this.color = ColorUtil.getColor(colorString);
        String SecondColor = appstate.getActiveSecondaryColor().toString();
        this.color2 = ColorUtil.getColor(SecondColor);

        this.shade = appstate.getActiveShapeShadingType().toString();



    }


    public DrawFatherCommand(){

    }

    //add this DrawAll object to CommandHistory's undoStack, become a history command
    public void addToHistory() {
        CommandHistory.add(this);
        System.out.println("undostack"+CommandHistory.getUndoStack().size());

    }

    //CommandHistory's undo functionality is to pop a command from the undoStack, and run its undo
    // a DrawAll object's undo functionality is to run the commands inside the CommandHistory's undoStack.
    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        CommandHistory.reDrawUndoStack();
        System.out.println("undo");

    }

    //CommandHistory's redo functionality is to pop a command from to redoStack and push it to undoStack, then run its redo
    //a DrawAll object's redo functionality is to run the commands inside the CommandHistory's undoStack.
    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        CommandHistory.reDrawUndoStack();
        System.out.println("redo");
    }

    //take in other Objects' coordinates and compare with self's coordinates; if overlapped:return ture
    @Override
    public boolean compareXY(int maxX, int minX, int maxY, int minY) {
        int thisMaxX = Math.max(startPoint.x, endPoint.x);
        int thisMinX =Math.min(startPoint.x, endPoint.x);
        int thisMaxY = Math.max(startPoint.y, endPoint.y);
        int thisMinY = Math.min(startPoint.y, endPoint.y);
        if (maxX<thisMinX||minX>thisMaxX){
            return false;
        }
        if (maxY<thisMinY||minY>thisMaxY){
            return false;
        }
        return true;
    }

    @Override
    public void setIsDrawCommand(boolean drawable) {
        this.IsDrawCommand = drawable;
    }

    @Override
    public void setIsSelectedT() {
        IsSelected = true;
    }

    @Override
    public void setIsSelectedF() {
        IsSelected = false;
    }

    @Override
    public boolean getIsSelected() {
        return IsSelected;
    }

    @Override
    public boolean getIsDrawCommand() {
        return IsDrawCommand;
    }

    //alter self's coordinates by offset value.
    @Override
    public void addOffset(int offSetX, int offSetY) {
        this.startPoint.x+=offSetX;
        this.startPoint.y+=offSetY;
        this.endPoint.x+=offSetX;
        this.endPoint.y+=offSetY;
    }

    @Override
    public void run() {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        DrawFatherCommand cloned = (DrawFatherCommand) super.clone();

        // Clone the Point objects since they are mutable
        cloned.startPoint = new Point(startPoint.x, startPoint.y);
        cloned.endPoint = new Point(endPoint.x, endPoint.y);


        return cloned;
    }


}
