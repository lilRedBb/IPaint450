package view.Commands;

import model.interfaces.IApplicationState;
import model.persistence.Point;
import view.gui.GetPaintCanvas;
import view.gui.PaintCanvas;
import view.interfaces.ICommand;
import view.interfaces.IUndoable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * @author lilred
 * @date 2023/08/01
 **/
public class PasteCommand implements ICommand,IUndoable {


    Stack<IUndoable> pasteStack;
    Stack<IUndoable> copyStack;

//    Graphics2D graphics2d;


    int offSet;

    public PasteCommand(){

//        this.graphics2d = PaintCanvas.get2D();
        this.pasteStack = new Stack<>();
        this.copyStack = CommandHistory.getCopyStack();
        this.offSet = new Random().nextInt(300)+50;

        for (IUndoable item : copyStack) {
            
            try {
                // Clone the DrawOvalCommand object and add it to the new stack of deep copies
                DrawFatherCommand cmd = (DrawFatherCommand)((DrawFatherCommand)item).clone();
                pasteStack.add(cmd);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
         
        }

        for (IUndoable pasteShape:pasteStack){
            pasteShape.addOffset(offSet,offSet);
            pasteShape.setIsSelectedF();
        }



    }

    @Override
    public void run() {
        Stack<IUndoable> undoStack = CommandHistory.getUndoStack();
        if (!pasteStack.empty()){

            undoStack.addAll(pasteStack);
            System.out.println(pasteStack.size());

        }

        CommandHistory.reDrawUndoStack();
        CommandHistory.add(this);
        System.out.println("paste");

    }

    @Override
    public void addToHistory() {
        if (!pasteStack.empty()) {
            CommandHistory.add(this);
        }
    }

    @Override
    public void undo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        int pasteNum = pasteStack.size();
        for (int i=0;i<pasteNum;i++){
            undoStack.pop();
        }

        CommandHistory.reDrawUndoStack();
    }

    @Override
    public void redo(Stack<IUndoable> undoStack, Stack<IUndoable> redoStack) {

        undoStack.pop();

        if (!pasteStack.empty()){

            undoStack.addAll(pasteStack);
            System.out.println(pasteStack.size());

        }

        addToHistory();

        CommandHistory.reDrawUndoStack();

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
    public void setIsSelectedT() {

    }

    @Override
    public void setIsSelectedF() {

    }

    @Override
    public boolean getIsSelected() {
        return false;
    }

    @Override
    public void setIsDrawCommand(boolean drawable) {

    }
}
