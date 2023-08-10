package model.persistence;

import view.interfaces.IUndoable;

import java.util.List;

/**
 * @author lilred
 * @date 2023/08/09
 **/
public class LoopSetStatus {

    List<IUndoable> collection;

    public LoopSetStatus(List<IUndoable> collection){
        this.collection = collection;
    }


    public void SetSelected(boolean selected){
        for (IUndoable cmd:collection){
            cmd.setIsSelected(selected);
        }
    }

    public void SetShowAsSelected(boolean showAsSelected){
        for (IUndoable cmd:collection){
            cmd.setShowAsSelected(showAsSelected);
        }
    }

    public void SetDrawable(boolean Drawable){
        for (IUndoable cmd:collection){
            cmd.setIsDrawCommand(Drawable);
        }
    }

    public void SetBothSelection(boolean selected,boolean showAsSelected){
        for (IUndoable cmd:collection){
            cmd.setShowAsSelected(showAsSelected);
            cmd.setIsSelected(selected);
        }
    }

    public void SetSelectDrawable(boolean selected,boolean Drawable){
        for (IUndoable cmd:collection){
            cmd.setIsDrawCommand(Drawable);
            cmd.setIsSelected(selected);
        }
    }

    public void AllSetStatus(boolean selected,boolean Drawable,boolean showAsSelected){
        for (IUndoable cmd:collection){
            cmd.setIsDrawCommand(Drawable);
            cmd.setIsSelected(selected);
            cmd.setShowAsSelected(showAsSelected);
        }
    }

    public void AddOffset(int offsetX, int offsetY){
        for (IUndoable cmd:collection){
            cmd.addOffset(offsetX,offsetY);
        }
    }

}
