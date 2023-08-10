package view.Commands;

import model.persistence.LoopSetStatus;
import view.interfaces.ICommand;
import view.interfaces.IUndoable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/08/06
 **/
public class SelectGroupCommand implements ICommand {

    private SelectCommand preSelection;



    public SelectGroupCommand(SelectCommand preSelection){
        this.preSelection = preSelection;

    }


    //as decorator for SelectCommand, re-fix the selected objects
    // selection's priority: shape > group
    //so, when shape & its groups all selected, shape will set its groups un-selected, (exclude)
    //now, remaining groups set its member-shape selected, so they be handled as a group.
    public void run(){

        List<IUndoable> preSelectedStack = preSelection.SelectOtherShapes();

        shapeExcludeGroup(preSelectedStack);

        groupSelection(preSelectedStack);


    }






    private void shapeExcludeGroup(List<IUndoable> preSelectedStack){

        for (IUndoable shape:preSelectedStack) {
            if (!shape.IsGroupCommand()) {

                new LoopSetStatus(shape.returnMyGroup()).SetBothSelection(false, false);


            }
        }
    }


    private void groupSelection(List<IUndoable> preSelectedStack){
        for (IUndoable shape:preSelectedStack){
            if (shape.IsGroupCommand()&&shape.getIsSelected()){

                new LoopSetStatus(shape.returnMembers()).SetBothSelection(true,false);


            }
        }
    }

    @Override
    public void addToHistory() {

    }
}
