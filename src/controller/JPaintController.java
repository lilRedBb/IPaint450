package controller;

import controller.interfaces.IJPaintController;
import model.interfaces.IApplicationState;
import view.Commands.*;
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.ICommand;
import view.interfaces.IUiModule;
import view.interfaces.IUndoable;

import java.util.Stack;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;

    private PaintCanvas pc;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.pc = PaintCanvas.getInstance();
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, applicationState::setActiveShape);
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, applicationState::setActivePrimaryColor);
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, applicationState::setActiveSecondaryColor);
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, applicationState::setActiveShadingType);
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, applicationState::setActiveStartAndEndPointMode);
        uiModule.addEvent(EventName.UNDO, this::undo);
        uiModule.addEvent(EventName.REDO, this::redo);
        uiModule.addEvent(EventName.COPY, this::copy);
        uiModule.addEvent(EventName.PASTE, this::paste);
        uiModule.addEvent(EventName.DELETE, this::delete);
        uiModule.addEvent(EventName.GROUP, this::group);
        uiModule.addEvent(EventName.UNGROUP, this::ungroup);
    }

    private void undo() {
        ICommand undoCommand = new UndoCommand();
        pc.repaint();
        undoCommand.run();
    }

    private void redo() {
        ICommand redoCommand = new RedoCommand();
        pc.repaint();
        redoCommand.run();
    }

    private void copy() {
        ICommand copyCommand = new CopyCommand();
        copyCommand.run();
        copyCommand.addToHistory();
    }

    private void paste() {

        ICommand pasteCommand = new PasteCommand();
        pc.repaint();
        pasteCommand.run();

    }

    private void delete() {
        ICommand deleteCommand = new DeleteCommand();
        pc.repaint();
        deleteCommand.run();
        deleteCommand.addToHistory();
    }

    private void group() {
    }

    private void ungroup() {
    }
}
