package view.interfaces;

import java.util.Stack;

public interface IUndoable {
    void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack);
    void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack);

    boolean compareXY(int maxX,int minX,int maxY,int minY); //to tell if two IUndoable object is overlapped
    boolean getIsDrawCommand();  //to tell if an IUndoable object is a command to draw shape

    void addOffset(int offSetX,int offSetY); //add coordinates offset to a IUndoable object
    void setIsSelectedT(); //selected an IUndoable object

    void setIsSelectedF(); //deselected an IUndoable object

    void setIsDrawCommand(boolean drawable); //set a shape to Drawable/UnDrawable

    boolean getIsSelected();   //to tell if an IUndoable object is selected by select movement
    void run();
}
