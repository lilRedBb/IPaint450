package view.Commands;

import model.persistence.LoopSetStatus;
import model.persistence.Point;
import view.gui.PaintCanvas;
import view.interfaces.ICommand;
import view.interfaces.IUndoable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/08/02
 **/
public class DeleteCommand implements IUndoable, ICommand {

    Stack<IUndoable> deleteStack;


    //each delete command has its local delete-stack for its own deleted shapes
    public DeleteCommand(){
        this.deleteStack = new Stack<>();

    }


    //set the selected shapes invisible
    @Override
    public void run() {

        ArrayList<IUndoable> selectedArray = CommandHistory.getSelectedShapes();

        new LoopSetStatus(selectedArray).SetDrawable(false);

        try {
            deleteStack.addAll(selectedArray);
        }catch (NullPointerException e){
            System.out.println();
        }


    }

    // resume visibility
    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        new LoopSetStatus(deleteStack).SetDrawable(true);

    }


    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        new LoopSetStatus(deleteStack).SetDrawable(false);
    }

    @Override
    public void addToHistory() {
        CommandHistory.add(this);
    }



    @Override
    public boolean compareXY(int maxX, int minX, int maxY, int minY) {
        return false;
    }

    @Override
    public boolean getIsDrawCommand() {
        return false;
    }

    @Override
    public void addOffset(int offSetX, int offSetY) {

    }

    @Override
    public void setIsSelected(boolean tf) {

    }

    @Override
    public boolean getIsSelected() {
        return false;
    }

    @Override
    public void setIsDrawCommand(boolean drawable) {

    }

    @Override
    public Point returnStartPoint() {
        return null;
    }

    @Override
    public Point returnEndPoint() {
        return null;
    }

    @Override
    public boolean IsGroupCommand() {
        return false;
    }

    @Override
    public void setShowAsSelected(boolean tf) {

    }


    @Override
    public boolean getShowAsSelected() {
        return false;
    }

    @Override
    public IUndoable addOrPopMyGroup(IUndoable groupCommand, boolean tf) {
        return null;
    }

    @Override
    public void addOrPopMyMembers(IUndoable drawCommand, boolean tf) {

    }

    @Override
    public ArrayList<IUndoable> returnMyGroup() {
        return null;
    }

    @Override
    public ArrayList<IUndoable> returnMembers() {
        return null;
    }


}
