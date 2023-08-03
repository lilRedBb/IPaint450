package view.Commands;

import view.gui.PaintCanvas;
import view.interfaces.ICommand;
import view.interfaces.IUndoable;

import java.awt.*;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/08/02
 **/
public class DeleteCommand implements IUndoable, ICommand {

    Stack<IUndoable> deleteStack;



    public DeleteCommand(){
        this.deleteStack = new Stack<>();

    }

    @Override
    public void run() {
        Stack<IUndoable> undoStack = CommandHistory.getUndoStack();
        for (IUndoable existShapes:undoStack){
            if (existShapes.getIsSelected()){
                existShapes.setIsDrawCommand(false);
                deleteStack.push(existShapes);
            }
        }

        CommandHistory.reDrawUndoStack();



    }

    @Override
    public void addToHistory() {
        CommandHistory.add(this);
    }

    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        for (IUndoable deletedShapes:deleteStack){

            deletedShapes.setIsDrawCommand(true);

        }

        CommandHistory.reDrawUndoStack();
    }

    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {
        for (IUndoable deletedShapes:deleteStack){

            deletedShapes.setIsDrawCommand(false);

        }

        CommandHistory.reDrawUndoStack();


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
    public void setIsDrawCommand(boolean drawable) {

    }
}
