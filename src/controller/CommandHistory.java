package controller;


import java.util.Stack;

class CommandHistory {
	private static final Stack<DrawCommand> undoStack = new Stack<DrawCommand>();
	private static final Stack<DrawCommand> redoStack = new Stack<DrawCommand>();

	public static void add(DrawCommand cmd) {

		undoStack.push(cmd);
		System.out.println(cmd.endPoint.x + " add ");
		redoStack.clear();



	}
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			DrawCommand c = undoStack.pop();

			redoStack.push(c);
			c.undo(undoStack, redoStack);
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			DrawCommand c = redoStack.pop();
			undoStack.push(c);
			c.redo(undoStack, redoStack);
		}
		return result;
	}
}
