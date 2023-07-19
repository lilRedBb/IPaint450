package view.Commands;


import view.interfaces.IUndoable;

import java.util.Stack;


class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	public static Stack<IUndoable> getUndoStack() {
		return undoStack;
	}
	public static Stack<IUndoable> getRedoStack(){
		return redoStack;
	}


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

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			c.redo(undoStack, redoStack);
		}
		return result;
	}
}
