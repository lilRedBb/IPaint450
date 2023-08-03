package main;

import controller.JPaintController;
import model.persistence.ApplicationState;
import view.clickhandler.ClickHandler;
import view.gui.GetPaintCanvas;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;



public class Main {
    public static void main(String[] args){

        PaintCanvas paintCanvas = PaintCanvas.getInstance();

        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);

        new JPaintController(uiModule, appState);

        ClickHandler clickHandler = new ClickHandler(paintCanvas,appState);

        paintCanvas.addMouseListener(clickHandler);


    }
}
