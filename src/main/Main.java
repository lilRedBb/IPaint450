package main;

import java.util.concurrent.TimeUnit;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.ClickHandler;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;



public class Main {
    public static void main(String[] args){
        PaintCanvas paintCanvas = new PaintCanvas();

        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);

        new JPaintController(uiModule, appState);

        ClickHandler clickHandler = new ClickHandler(paintCanvas);
        paintCanvas.addMouseListener(clickHandler);


    }
}
