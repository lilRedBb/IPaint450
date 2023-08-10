package view.Commands;

import model.persistence.Point;
import view.interfaces.ICommand;
import view.interfaces.IUndoable;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/08/08
 **/
public class UnGroupCommand implements IUndoable, ICommand {

    private ArrayList<IUndoable> toUngroupArray;
    public UnGroupCommand()
    {
        this.toUngroupArray = CommandHistory.getShowAsSelectedShapes();
    }


    //if group selected, group gone
    //if shape selected, shape leave its last group
    @Override
    public void run() throws NullPointerException{

        for (IUndoable cmd:toUngroupArray){
            if (cmd.IsGroupCommand()){

                disMissThisGroup(cmd);

            }else {
                detachThisShape(cmd);

            }
        }
        CommandHistory.unSelectAllShapes();

    }


    //resume un-group for both shape and group by manipulate their history Array
    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

            for (IUndoable cmd:toUngroupArray){
                if (cmd.IsGroupCommand()){

                   resumeThisGroup(cmd);

                }else {

                   resumeShapeToGroup(cmd);
                }
            }

            CommandHistory.unSelectAllShapes();

    }

    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {
        run();
    }


    private void disMissThisGroup(IUndoable cmd){
        ArrayList<IUndoable> cmdMembers = cmd.returnMembers();

        for (IUndoable Member:cmdMembers){
            Member.addOrPopMyGroup(this,false);
            Member.setIsSelected(false);
        }

        cmd.addOrPopMyMembers(null,false);
        cmd.setIsDrawCommand(false);

    }

    private void detachThisShape(IUndoable cmd){
        IUndoable lastGroup = cmd.addOrPopMyGroup(null,false);
        lastGroup.addOrPopMyMembers(cmd,false);
    }

    private void resumeThisGroup(IUndoable cmd){
        cmd.setIsDrawCommand(true);

        cmd.addOrPopMyMembers(null,true);

        ArrayList<IUndoable> cmdMembers = cmd.returnMembers();

        for (IUndoable Member:cmdMembers){
            Member.addOrPopMyGroup(null,true);

        }
    }


    private void resumeShapeToGroup(IUndoable cmd){
        IUndoable lastGroup = cmd.addOrPopMyGroup(null,true);
        lastGroup.addOrPopMyMembers(cmd,true);
    }

    @Override
    public void addToHistory() {
        CommandHistory.add(this);
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
    public void setIsSelected(boolean tf) {

    }

    @Override
    public void setIsDrawCommand(boolean drawable) {

    }

    @Override
    public boolean getIsSelected() {
        return false;
    }

    @Override
    public boolean IsGroupCommand() {
        return false;
    }

    @Override
    public void setShowAsSelected(boolean tf) {

    }

    @Override
    public boolean getShowAsSelected() {
        return false;
    }


    @Override
    public Point returnStartPoint() {
        return null;
    }

    @Override
    public Point returnEndPoint() {
        return null;
    }

    @Override
    public IUndoable addOrPopMyGroup(IUndoable groupCommand, boolean tf) {
        return null;
    }

    @Override
    public void addOrPopMyMembers(IUndoable drawCommand, boolean tf) {

    }

    @Override
    public ArrayList<IUndoable> returnMyGroup() {
        return null;
    }

    @Override
    public ArrayList<IUndoable> returnMembers() {
        return null;
    }





}
