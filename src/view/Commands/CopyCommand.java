package view.Commands;

import model.persistence.Point;
import view.interfaces.ICommand;
import view.interfaces.IUndoable;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/08/01
 **/
public class CopyCommand implements ICommand, IUndoable {

    ArrayList<IUndoable> showAsSelectedArray;
    Stack<IUndoable> copyStack;


    //copy command simply just put the selected shapes in the main stack to copy stack
    public CopyCommand() {

        showAsSelectedArray = CommandHistory.getShowAsSelectedShapes();
        copyStack = CommandHistory.getCopyStack();

    }


    @Override
    public void run() throws NullPointerException{

        copyStack.clear();

        copyStack.addAll(showAsSelectedArray);


        System.out.println("copy");
    }

    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {
        copyStack.clear();
    }

    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {
        run();
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
    public void setIsSelected(boolean tf) {

    }

    @Override
    public boolean getIsSelected() {
        return false;
    }

    @Override
    public void addOffset(int offSetX, int offSetY) {

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
    public IUndoable addOrPopMyGroup(IUndoable groupCommand,boolean tf) {
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
