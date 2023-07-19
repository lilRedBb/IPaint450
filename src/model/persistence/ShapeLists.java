package model.persistence;

import view.interfaces.IUndoable;

import java.util.ArrayList;

/**
 * @author lilred
 * @date 2023/07/16
 **/
public class ShapeLists {
    public static final ArrayList<IUndoable> selectedArray = new ArrayList<>();

    public static final ArrayList<IUndoable> movedArray = new ArrayList<>();
}
