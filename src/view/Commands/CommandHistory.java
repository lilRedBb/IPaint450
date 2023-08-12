package view.Commands;


import model.persistence.ShapeList;
import view.interfaces.ICommand;
import view.interfaces.IUndoable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;


public class CommandHistory {
	private static final ShapeList undoStack = new ShapeList();  //main-stack
	private static final ShapeList redoStack = new ShapeList();

	private  static final ShapeList copyStack = new ShapeList();



	public static Stack<IUndoable> getUndoStack() {
		return undoStack.shapeList;
	}

	public static Stack<IUndoable> getRedoStack(){
		return redoStack.shapeList;
	}
	public static Stack<IUndoable> getCopyStack() {return copyStack.shapeList;}




	public static void add(IUndoable cmd) {

		undoStack.shapeList.push(cmd);

		redoStack.shapeList.clear();


	}


	public static boolean undo() {
		boolean result = !undoStack.shapeList.empty();
		if (result) {
			IUndoable c = undoStack.shapeList.pop();

			redoStack.shapeList.push(c);

			c.undo(undoStack.shapeList, redoStack.shapeList);

		}
		return result;
	}


	public static boolean redo(){
		boolean result = !redoStack.shapeList.empty();
		if (result) {
			IUndoable c = redoStack.shapeList.pop();
			undoStack.shapeList.push(c);
			c.redo(undoStack.shapeList, redoStack.shapeList);
		}
		return result;
	}

	//redraw every drawable shapes in the main-stack
	public static void reDrawUndoStack(){
//		for (IUndoable existShape: undoStack.shapeList){
//			if (existShape.getIsDrawCommand()){
//				existShape.run();
//			}
//
//		}
		undoStack.run();
	}

	//get selected shapes from the main-stack
	public static ArrayList<IUndoable> getSelectedShapes(){
		ArrayList<IUndoable> toReturn = new ArrayList<>();
		for (IUndoable existShape: undoStack.shapeList){
			if (existShape.getIsSelected()){
				toReturn.add(existShape);
			}

		}
		return toReturn;
	}

	//get shapes with dash-outline from the main-stack
	public static ArrayList<IUndoable> getShowAsSelectedShapes(){
		ArrayList<IUndoable> toReturn = new ArrayList<>();
		for (IUndoable existShape: undoStack.shapeList){
			if (existShape.getShowAsSelected()){
				toReturn.add(existShape);
			}
		}
		return toReturn;
	}

	//get visible shapes from the main-stack
	public static ArrayList<IUndoable> getDrawableShapes(){
		ArrayList<IUndoable> toReturn = new ArrayList<>();
		for (IUndoable existShape: undoStack.shapeList){
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
