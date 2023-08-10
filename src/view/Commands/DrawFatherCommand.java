package view.Commands;

import model.persistence.LoopSetStatus;
import view.interfaces.IUndoable;
import model.interfaces.IApplicationState;
import model.persistence.ColorUtil;
import model.persistence.Point;
import view.gui.PaintCanvas;
import view.interfaces.ICommand;

import java.awt.*;
import java.util.ArrayList;
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

    Color color;  //get IApplicationState's primary color
    Color color2; //get IApplicationState's secondary color

    String shade; //String object generated from IApplicationState's shading type

    boolean IsSelected;  //if this shape is selected by the select movement

    boolean ShowAsSelected; //if this shape should have dash outline

    boolean IsDrawCommand=true; //visible on canvas or not

    public ArrayList<IUndoable> belongGroups; //when shape added to a group, the group be remembered here
    public Stack<IUndoable> historyGroups;  // when shape get out from a group, the group will resettle to here




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
        
        this.belongGroups = new ArrayList<>();
        this.historyGroups = new Stack<>();


    }


    public DrawFatherCommand(){

    }



    //take in other shapes' coordinates and compare with self's coordinates; if overlapped:return ture
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


    //alter self's coordinates by offset value.
    @Override
    public void addOffset(int offSetX, int offSetY) {

        this.startPoint.x+=offSetX;
        this.startPoint.y+=offSetY;
        this.endPoint.x+=offSetX;
        this.endPoint.y+=offSetY;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        DrawFatherCommand cloned = (DrawFatherCommand) super.clone();

        // Clone the Point objects since they are mutable
        cloned.startPoint = new Point(startPoint.x, startPoint.y);
        cloned.endPoint = new Point(endPoint.x, endPoint.y);
        cloned.belongGroups = new ArrayList<>();
        cloned.historyGroups = new Stack<>();

        return cloned;
    }

    @Override
    public IUndoable addOrPopMyGroup(IUndoable drawCommand, boolean toAdd) throws NullPointerException{

        if (toAdd){
            return addGroup(drawCommand);
        }else{
            return removeGroup(drawCommand);
        }

    }

    private IUndoable addGroup(IUndoable drawCommand){
        if (drawCommand == null){
            IUndoable lastGroup = historyGroups.pop();
            belongGroups.add(lastGroup);
            return lastGroup;
        }else {
            belongGroups.add(drawCommand);
            return null;
        }
    }

    private IUndoable removeGroup(IUndoable drawCommand) throws NullPointerException{

        if (drawCommand == null){
            IUndoable lastGroup = belongGroups.get(belongGroups.size()-1);
            belongGroups.remove(lastGroup);
            historyGroups.add(lastGroup);
            return lastGroup;
        }else {
            belongGroups.remove(drawCommand);
            historyGroups.push(drawCommand);
            return null;
        }
    }



    @Override
    public Point returnEndPoint() {return this.endPoint;}

    @Override
    public Point returnStartPoint() {
        return this.startPoint;
    }


    @Override
    public boolean IsGroupCommand() {
        return false;
    }

    @Override
    public boolean getShowAsSelected() {
        return ShowAsSelected;
    }

    @Override
    public void setShowAsSelected(boolean showAsSelected) {
        ShowAsSelected = showAsSelected;
    }


    public ArrayList<IUndoable> returnMyGroup(){
        return this.belongGroups;
    }

    public ArrayList<IUndoable> returnMembers(){
        return null;
    }

    @Override
    public void addOrPopMyMembers(IUndoable drawCommand, boolean toAdd) {}

    @Override
    public boolean getIsDrawCommand() {return IsDrawCommand;}
    @Override
    public void setIsDrawCommand(boolean drawable) {
        this.IsDrawCommand = drawable;
    }


    @Override
    public boolean getIsSelected() {return IsSelected;}

    @Override
    public void setIsSelected(boolean toAdd) {IsSelected = toAdd;}

    @Override
    public void run() {}

    //add this DrawAll object to main-stack, become a history command
    public void addToHistory() {
        CommandHistory.add(this);

    }

    //undo action happen in JPaintController
    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        System.out.println("undo");
    }

    //redo action happen in JPaintController
    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        System.out.println("redo");
    }





}
