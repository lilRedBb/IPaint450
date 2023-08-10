package view.interfaces;

import model.persistence.Point;

import java.util.ArrayList;
import java.util.Stack;

public interface IUndoable {
    void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack);
    void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack);

    boolean compareXY(int maxX,int minX,int maxY,int minY); //to tell if two IUndoable object is overlapped
    boolean getIsDrawCommand();  //to tell if an IUndoable object is a command to draw shape

    void addOffset(int offSetX,int offSetY); //add coordinates offset to a IUndoable object

    void setIsSelected(boolean tf);

    void setIsDrawCommand(boolean drawable); //set a shape to Drawable/UnDrawable

    boolean getIsSelected();   //to tell if an IUndoable object is selected by select movement

    boolean IsGroupCommand();//to tell if an IUndoable object is a group command

    void setShowAsSelected(boolean tf); //

    boolean getShowAsSelected();


    Point returnStartPoint();

    Point returnEndPoint();

    IUndoable addOrPopMyGroup(IUndoable groupCommand,boolean toAdd);

    void addOrPopMyMembers(IUndoable drawCommand, boolean toAdd);

    public ArrayList<IUndoable> returnMyGroup();

    public ArrayList<IUndoable> returnMembers();


    void run();
}
