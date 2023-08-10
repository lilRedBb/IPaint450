package view.clickhandler;


import model.interfaces.IApplicationState;
import model.persistence.Point;
import view.Commands.*;
import view.gui.PaintCanvas;
import view.gui.RefreshCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author lilred
 * @date 2023/06/26
 **/
public class ClickHandler extends MouseAdapter {
    private PaintCanvas paintCanvas;
    private IApplicationState applicationState;

    private RunCommand runCommand;

    private DrawFatherCommand drawCmd;
    public ClickHandler(PaintCanvas paintCanvas, IApplicationState applicationState)
    {
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;
    }


    Point startPoint = new Point();
    Point endPoint = new Point();

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint.x = e.getX();
        startPoint.y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        endPoint.x = e.getX();
        endPoint.y = e.getY();


        // based on applicationState, there are 3 branches:(select,draw,move).
        if (this.applicationState.InSelectMode()){

            new RefreshCanvas(new SelectGroupCommand(new SelectCommand(startPoint,endPoint)));


        //draw: mutate the "drawCmd" object to draw different shape according to applicationState
        }else if(this.applicationState.InDrawMode()) {

            if (this.applicationState.drawCircle()){
                drawCmd = new DrawOvalCommand( startPoint, endPoint,applicationState);

            } else if (this.applicationState.drawTriangle()) {
                drawCmd = new DrawTriangleCommand(startPoint, endPoint,applicationState);

            }else {
                drawCmd = new DrawRectCommand( startPoint, endPoint,applicationState);

            }

            //draw action start
            runCommand = new RunCommand(drawCmd);

            runCommand.execute();


        //move:  mutate the "drawCmd" object to move-command
        }else {

            drawCmd = new MoveCommand(startPoint, endPoint,applicationState);
            new RefreshCanvas(drawCmd);

        }
    }
}
