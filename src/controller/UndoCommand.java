package controller;

import controller.CommandHistory;
import view.interfaces.ICommand;

/**
 * @author lilred
 * @date 2023/07/05
 **/
public class UndoCommand implements ICommand {

    @Override
    public void run() {
        CommandHistory.undo();
    }
}

