package view.gui;

import view.Commands.*;
import view.interfaces.ICommand;

/**
 * @author lilred
 * @date 2023/08/09
 **/
public class RefreshCanvas {

    private ICommand icmd;

    // after most command run, the stack should be repaint. This encapsulation reduce redundancy.
    public RefreshCanvas(ICommand cmd) throws NullPointerException{
        this.icmd = cmd;
        PaintCanvas pc = PaintCanvas.getInstance();
        pc.repaint();
        cmd.run();

        if (icmd instanceof MoveCommand|| icmd instanceof DeleteCommand|| icmd instanceof UnGroupCommand || icmd instanceof GroupCommand){
            icmd.addToHistory();
        }


        CommandHistory.reDrawUndoStack();
    }




}
