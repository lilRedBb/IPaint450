package view.Commands;

import model.persistence.LoopSetStatus;
import view.interfaces.IUndoable;
import model.interfaces.IApplicationState;
import model.persistence.Point;
import view.gui.PaintCanvas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/07/17
 **/
public class MoveCommand extends DrawFatherCommand {

    int offSetX; //offset of this mouse movement
    int offSetY; //offset of this mouse movement

    private ArrayList<IUndoable> mySelectedShape;



    //move-command add self's offset to other shapes; move command is invisible.
    public MoveCommand( Point startPoint, Point endPoint, IApplicationState appstate) {
        super(startPoint,endPoint,appstate);

        this.IsDrawCommand = false;
        this.offSetX = this.endPoint.x-this.startPoint.x;
        this.offSetY = this.endPoint.y-this.startPoint.y;
        this.mySelectedShape = new ArrayList<>();
    }

    //run() method will iterate the main-stack and alter the selected shapes' coordinates
    @Override
    public void run() {
        List<IUndoable> selectedArray = CommandHistory.getSelectedShapes();
        mySelectedShape.addAll(selectedArray);
        for(IUndoable preSelectShape:selectedArray){
            if (!preSelectShape.IsGroupCommand()){
                for (IUndoable group:preSelectShape.returnMyGroup()){
                    if (!group.getIsSelected()){
                        mySelectedShape.remove(preSelectShape);
                        break;
                    }
                }
            }
        }
        new LoopSetStatus(mySelectedShape).AddOffset(offSetX,offSetY);


    }

    //undo() method will iterate main-stack and resume the selected shapes' coordinates
    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {



        new LoopSetStatus(mySelectedShape).AddOffset(-offSetX,-offSetY);


    }


    //redo() functionality = run()
    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {
        this.run();
    }
}
