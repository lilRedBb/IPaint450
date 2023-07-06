package controller;

/**
 * @author lilred
 * @date 2023/07/06
 **/
import view.interfaces.ICommand;

/**
 * @author lilred
 * @date 2023/07/05
 **/
public class RedoCommand implements ICommand {

    @Override
    public void run() {
        CommandHistory.redo();
    }
}
