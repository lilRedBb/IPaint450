package controller;

import java.util.Stack;

public interface IUndoable {
    void undo(Stack<DrawCommand> undoStack, Stack<DrawCommand> redoStack);
    void redo(Stack<DrawCommand> undoStack, Stack<DrawCommand> redoStack);
}
