package view.Commands;

import model.persistence.LoopSetStatus;
import view.interfaces.IUndoable;
import model.interfaces.IApplicationState;
import model.persistence.Point;
import view.gui.PaintCanvas;

import java.util.List;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/07/17
 **/
public class MoveCommand extends DrawFatherCommand {

    int offSetX; //offset of this mouse movement
    int offSetY; //offset of this mouse movement



    //move-command is invisible
    public MoveCommand( Point startPoint, Point endPoint, IApplicationState appstate) {
        super(startPoint,endPoint,appstate);

        this.IsDrawCommand = false;
        this.offSetX = this.endPoint.x-this.startPoint.x;
        this.offSetY = this.endPoint.y-this.startPoint.y;
    }

    //run() method will iterate the undoStack and alter the selected shapes' coordinates, and draw the altered shapes
    @Override
    public void run() {
        List<IUndoable> selectedArray = CommandHistory.getSelectedShapes();

        new LoopSetStatus(selectedArray).AddOffset(offSetX,offSetY);


    }

    //undo() method will iterate undoStack and resume the selected shapes' coordinates, and draw the resumed shapes
    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        List<IUndoable> selectedArray = CommandHistory.getSelectedShapes();

        new LoopSetStatus(selectedArray).AddOffset(-offSetX,-offSetY);


    }


    //because run() methods already loop the undoStack and alter the selected shapes,
    // so , redo will use run() to alter the resumed shapes.
    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {
        this.run();
    }
}
