package view.clickhandler;


import model.interfaces.IApplicationState;
import model.persistence.Point;
import view.Commands.*;
import view.gui.PaintCanvas;

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
        //declare a DrawAll object(extends by different shape drawing classes) for strategy-model-design later
        DrawAll icmd;

        // based on applicationState, there are 3 functions so far:(select,draw,move);All 3 function do not co-exits
        //at the same time

        if (this.applicationState.getActiveMouseMode().toString().equals("SELECT")){
            SelectCommand.SelectOtherShapes(paintCanvas,startPoint,endPoint);

        //for draw functions, mutate the "icmd" object to different shape-drawing according to applicationState
        }else if(this.applicationState.getActiveMouseMode().toString().equals("DRAW")) {
            if (this.applicationState.getActiveShapeType().toString().equals("ELLIPSE")){
                icmd = new DrawOvalCommand(paintCanvas, startPoint, endPoint,applicationState);

            } else if (this.applicationState.getActiveShapeType().toString().equals("TRIANGLE")) {
                icmd = new DrawTriangleCommand(paintCanvas, startPoint, endPoint,applicationState);

            }else {
                icmd = new DrawRectCommand(paintCanvas, startPoint, endPoint,applicationState);

            }
        //after the mutation, draw the shape
            runCommand = new RunCommand(icmd);

            runCommand.execute();

        //if applicationState's mouse-mode is "Move", mutate the "icmd" object to move-command
        }else {
            icmd = new MoveCommand(paintCanvas, startPoint, endPoint,applicationState);

            runCommand = new RunCommand(icmd);

            runCommand.execute();


        }





    }
}
