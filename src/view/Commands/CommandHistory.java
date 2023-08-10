package view.Commands;


import view.interfaces.IUndoable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;


public class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	private  static final Stack<IUndoable> copyStack = new Stack<IUndoable>();



	public static Stack<IUndoable> getUndoStack() {
		return undoStack;
	}

	public static Stack<IUndoable> getRedoStack(){
		return redoStack;
	}
	public static Stack<IUndoable> getCopyStack() {return copyStack;}




	public static void add(IUndoable cmd) {

		undoStack.push(cmd);

		redoStack.clear();


	}


	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();

			redoStack.push(c);

			c.undo(undoStack, redoStack);

		}
		return result;
	}


	public static boolean redo(){
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			c.redo(undoStack, redoStack);
		}
		return result;
	}

	//redraw every drawable shapes in the main-stack
	public static void reDrawUndoStack(){
		for (IUndoable existShape: undoStack){
			if (existShape.getIsDrawCommand()){
				existShape.run();
			}

		}
	}

	//get selected shapes from the main-stack
	public static ArrayList<IUndoable> getSelectedShapes(){
		ArrayList<IUndoable> toReturn = new ArrayList<>();
		for (IUndoable existShape: undoStack){
			if (existShape.getIsSelected()){
				toReturn.add(existShape);
			}

		}
		return toReturn;
	}

	//get shapes with dash-outline from the main-stack
	public static ArrayList<IUndoable> getShowAsSelectedShapes(){
		ArrayList<IUndoable> toReturn = new ArrayList<>();
		for (IUndoable existShape: undoStack){
			if (existShape.getShowAsSelected()){
				toReturn.add(existShape);
			}
		}
		return toReturn;
	}

	//get visible shapes from the main-stack
	public static ArrayList<IUndoable> getDrawableShapes(){
		ArrayList<IUndoable> toReturn = new ArrayList<>();
		for (IUndoable existShape: undoStack){
			if (existShape.getIsDrawCommand()){
				toReturn.add(existShape);
			}

		}
		return toReturn;
	}


	public static void unSelectAllShapes(){
		ArrayList<IUndoable> selectedArray = getSelectedShapes();
		for (IUndoable shape:selectedArray){
			shape.setIsSelected(false);
			shape.setShowAsSelected(false);
		}
	}




}
