package view.Commands;

import model.interfaces.IApplicationState;
import model.persistence.Filter;
import model.persistence.MakeGroupFrame;
import model.persistence.Point;
import view.drawhandler.DrawHandlerShade;
import view.gui.PaintCanvas;
import view.interfaces.IUndoable;
import view.rectBranch.RectOutline;
import model.persistence.PairPoint;
import view.rectBranch.RectSelected;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/08/06
 **/
public class GroupCommand extends DrawFatherCommand {

    DrawHandlerShade idrawRect;
    Graphics2D graphics2d;


    private ArrayList<IUndoable> groupMembers = new ArrayList<>();

    public ArrayList<ArrayList<IUndoable>> historyMembers = new ArrayList<>();


    //1st constructor:  activated by "Group Button"
    public GroupCommand() {


        //Filter: remove groupCommand in selectedStack, then group the shapes
        this.groupMembers = Filter.groupCmdRemove(CommandHistory.getSelectedShapes());



        //all members remember this group, and sync selected status, but not show as selected
        for (IUndoable members:groupMembers){
            members.addOrPopMyGroup(this,true);
            members.setIsSelected(true);
            members.setShowAsSelected(false);
        }


        // generate two Points to draw a groupFrame, by calculates the members Points
        PairPoint pp = new MakeGroupFrame().groupFramePoints(groupMembers);

        this.startPoint = new Point(pp.first.x, pp.first.y);
        this.endPoint = new Point(pp.second.x, pp.second.y);

        this.graphics2d = PaintCanvas.get2D();

        //group frame show as selected
        setIsSelected(true);
        setShowAsSelected(true);

    }


    //2nd constructor: used for pasteCommand
    //pasting a groupCommand will create a new groupCommand, argument=old group's members' clone, argument becomes new group's member
    public GroupCommand(ArrayList<IUndoable> newShapes){

        this.groupMembers = newShapes;


        for (IUndoable members:groupMembers){
            members.addOrPopMyGroup(this,true);
            members.setShowAsSelected(false);
        }

        PairPoint pp = new MakeGroupFrame().groupFramePoints(newShapes);

        this.startPoint = new Point(pp.first.x, pp.first.y);
        this.endPoint = new Point(pp.second.x, pp.second.y);

        this.graphics2d = PaintCanvas.get2D();
    }

    public GroupCommand(Point startPoint, Point endPoint, IApplicationState appstate) {
        super(startPoint, endPoint, appstate);
    }


    @Override
    public void run() {

        //run method draws group frame to the canvas
        if(ShowAsSelected){
            idrawRect = new RectSelected(graphics2d,startPoint,endPoint,color,color2);
            idrawRect.Draw();

        }

        idrawRect = new RectOutline(graphics2d,startPoint,endPoint,Color.CYAN,Color.CYAN);
        idrawRect.Draw();

    }



    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        //first, erase group frame from canvas
        super.undo(undoStack, redoStack);

        //second, dis-associate with all remaining members
        for (IUndoable myMember: groupMembers){
            myMember.addOrPopMyGroup(this,false);
        }

        CommandHistory.unSelectAllShapes();


    }

    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        //first, resume group frame to canvas
        super.redo(undoStack, redoStack);

        //second, resume the last dis-associate action
        for (IUndoable myMember: groupMembers){
            myMember.setIsSelected(true);
            myMember.addOrPopMyGroup(null,true);
        }

    }


    // when groupCommand being pasted, it will deep-copy the members and return them.
    public ArrayList<IUndoable> membersClone() throws CloneNotSupportedException {

        ArrayList<IUndoable> tmp = new ArrayList<>();

        for (IUndoable shapes:groupMembers){
            DrawFatherCommand cloned_shapes = (DrawFatherCommand) ((DrawFatherCommand) shapes).clone();
            tmp.add(cloned_shapes);
        }
        return tmp;
    }


    //take in a shape or group, manipulate their journal lists, to achieve group/un-group purpose.
    @Override
    public void addOrPopMyMembers(IUndoable drawCommand, boolean toAdd) throws NullPointerException,IndexOutOfBoundsException{

        if (toAdd){
            addMember(drawCommand);
        }else {
            removeMember(drawCommand);
        }

    }


    //when drawCommand==null:  it's an un-group resume action, add history members back.
    //when drawCommand !=null : it's a new group created occasion .
    private void addMember(IUndoable drawCommand) throws NullPointerException,IndexOutOfBoundsException{


        if (drawCommand == null){
            groupMembers.addAll(historyMembers.get(historyMembers.size()-1));

        }else {
            ArrayList<IUndoable> tmp = new ArrayList<>();
            groupMembers.add(drawCommand);
            tmp.add(drawCommand);
            historyMembers.remove(tmp);
        }

    }


    //when drawCommand ==null:  it's a whole group got un-group occasion.
    //when drawCommand != null:  it's a selected  member pop out occasion,
    private void removeMember(IUndoable drawCommand){


        if (drawCommand == null){
            historyMembers.add(groupMembers);
            groupMembers.clear();
        }else {
            ArrayList<IUndoable> tmp = new ArrayList<>();
            groupMembers.remove(drawCommand);
            tmp.add(drawCommand);
            historyMembers.add(tmp);

        }
    }


    @Override
    public void addToHistory() {
        super.addToHistory();
    }

    @Override
    public boolean compareXY(int maxX, int minX, int maxY, int minY) {
        return super.compareXY(maxX, minX, maxY, minY);
    }

    @Override
    public void setIsDrawCommand(boolean drawable) {
        super.setIsDrawCommand(drawable);
    }



    @Override
    public boolean getIsSelected() {
        return super.getIsSelected();
    }

    @Override
    public boolean getIsDrawCommand() {
        return super.getIsDrawCommand();
    }

    @Override
    public void addOffset(int offSetX, int offSetY) {
        super.addOffset(offSetX, offSetY);
    }



    @Override
    public boolean IsGroupCommand() {
        return true;
    }


    public ArrayList<IUndoable> returnMembers(){
        return this.groupMembers;
    }


}
