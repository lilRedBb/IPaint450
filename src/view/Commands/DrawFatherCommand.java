package view.Commands;

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
public class DrawFatherCommand implements IUndoable, ICommand {
    //inherited by DrawOval,DrawRect,DrawTriangle,MoveCommand
    PaintCanvas pc;  //take in the shared PaintCanvas
    IApplicationState appstate; //take in the shared IApplicationState
    Point startPoint;   //take in Point object from ClickHandler
    Point endPoint;     //take in Point object from ClickHandler

    Graphics2D graphics2d; //take in shared Graphics2D

    Graphics g;

    Color color;  //Color object generated from IApplicationState's primary color
    Color color2; //Color object generated from IApplicationState's secondary color

    String shade; //String object generated from IApplicationState's shading type

    boolean IsSelected;  //if this shape is selected by the select movement

    boolean IsDrawCommand=true; //if this command's function is to draw a shape


    public DrawFatherCommand(PaintCanvas pc, Point startPoint, Point endPoint, IApplicationState appstate) {
        this.pc = pc;
        this.appstate = appstate;
        this.g = pc.getGraphics();
        this.graphics2d = (Graphics2D)g;

        this.startPoint = new Point();
        this.endPoint = new Point();
        this.startPoint.x = startPoint.x;
        this.endPoint.x = endPoint.x;
        this.startPoint.y = startPoint.y;
        this.endPoint.y = endPoint.y;

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

    }

    //CommandHistory's undo functionality is to pop a command from the undoStack, and run its undo
    // a DrawAll object's undo functionality is to run the commands inside the CommandHistory's undoStack.
    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        pc.paint(graphics2d);

        for (IUndoable drawCommand : undoStack) {

            drawCommand.run();
        }
        System.out.println("undo");

    }

    //CommandHistory's redo functionality is to pop a command from to redoStack and push it to undoStack, then run its redo
    //a DrawAll object's redo functionality is to run the commands inside the CommandHistory's undoStack.
    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        pc.paint(graphics2d);
        for (IUndoable drawCommand : undoStack) {
            drawCommand.run();
        }
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
}
