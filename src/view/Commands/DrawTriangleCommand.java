package view.Commands;

import view.drawhandler.DrawHandlerShade;
import view.triangleBranch.TriangleDoTwo;
import view.triangleBranch.TriangleFill;
import view.triangleBranch.TriangleOutline;
import view.triangleBranch.TriangleSelect;
import model.interfaces.IApplicationState;
import model.persistence.Point;
import view.gui.PaintCanvas;

/**
 * @author lilred
 * @date 2023/07/13
 **/
public class DrawTriangleCommand extends DrawFatherCommand {

    //declare a DrawHandler object,which will mutate according to shadeType in the run() method.
    DrawHandlerShade idraw;


    public DrawTriangleCommand( Point startPoint, Point endPoint, IApplicationState appstate) {

        super(startPoint,endPoint,appstate);

    }

    //run() method uses state-model-pattern to draw shadeTypes.
    @Override
    public void run() {
        if (ShowAsSelected){
            idraw = new TriangleSelect(graphics2d,startPoint,endPoint,color,color2);
            idraw.Draw();
        }

        if (this.shade.equals("OUTLINE")){
            idraw = new TriangleOutline(graphics2d,startPoint,endPoint,color,color2);
            idraw.Draw();
        }
        else if (this.shade.equals("OUTLINE_AND_FILLED_IN")) {
            idraw = new TriangleDoTwo(graphics2d,startPoint,endPoint,color,color2);
            idraw.Draw();
        }
        else {
            idraw = new TriangleFill(graphics2d,startPoint,endPoint,color,color2);
            idraw.Draw();
        }


    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
