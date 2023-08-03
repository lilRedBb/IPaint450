package view.Commands;

import view.interfaces.ICommand;
import view.interfaces.IUndoable;

import java.util.Stack;

/**
 * @author lilred
 * @date 2023/08/01
 **/
public class CopyCommand implements ICommand, IUndoable {

    Stack<IUndoable> undoStack;
    Stack<IUndoable> copyStack;



    public CopyCommand() {

        undoStack = CommandHistory.getUndoStack();
        copyStack = CommandHistory.getCopyStack();

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
    public void run() {

        copyStack.clear();
        for (IUndoable drawCommand:undoStack){
            if (drawCommand.getIsSelected()){
                copyStack.push(drawCommand);
            }
        }
        System.out.println("copy");
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
    public void setIsSelectedT() {

    }

    @Override
    public void setIsSelectedF() {

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
}
