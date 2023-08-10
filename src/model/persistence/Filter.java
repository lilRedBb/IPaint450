package model.persistence;

import view.interfaces.IUndoable;

import java.util.ArrayList;

/**
 * @author lilred
 * @date 2023/08/07
 **/
public class Filter {

    public static ArrayList<IUndoable> groupCmdRemove(ArrayList<IUndoable> originalArray){
        ArrayList<IUndoable> filterArray = new ArrayList<>();
        for (IUndoable cmd:originalArray){
            if (!cmd.IsGroupCommand()){
                filterArray.add(cmd);
            }
        }return filterArray;
    }

}
