package model.persistence;

import view.interfaces.IUndoable;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/08/12
 **/
public class ShapeList implements IUndoable {


    public Stack<IUndoable> shapeList;


    public ShapeList(){
        this.shapeList = new Stack<>();

    }

    @Override
    public void run() {
        for (IUndoable existShape: shapeList){
            if (existShape.getIsDrawCommand()){
                existShape.run();
            }

        }
    }


    @Override
    public void undo(Stack<IUndoable> aStack, Stack<IUndoable> bStack) {

    }

    @Override
    public void redo(Stack<IUndoable> aStack, Stack<IUndoable> bStack) {

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
    public IUndoable addOrPopMyGroup(IUndoable groupCommand, boolean toAdd) {
        return null;
    }

    @Override
    public void addOrPopMyMembers(IUndoable drawCommand, boolean toAdd) {

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
