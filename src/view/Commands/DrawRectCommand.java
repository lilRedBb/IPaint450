package view.Commands;

import view.drawhandler.DrawHandlerShade;
import view.ovalBranch.OvalSelected;
import view.rectBranch.RectDoTwo;
import view.rectBranch.RectFill;
import view.rectBranch.RectOutline;
import model.interfaces.IApplicationState;
import view.gui.PaintCanvas;
import model.persistence.Point;
import view.rectBranch.RectSelected;


/**
 * @author lilred
 * @date 2023/07/05
 **/
public class DrawRectCommand extends DrawFatherCommand {

    //declare a DrawHandler object,which will mutate according to shadeType in the run() method.
    DrawHandlerShade idrawRect;


    public DrawRectCommand(Point startPoint, Point endPoint, IApplicationState appstate) {

        super(startPoint,endPoint,appstate);


    }

    //run() method uses state-model-pattern to draw shadeTypes.
    @Override
    public void run() {

        if(getIsSelected()){
            idrawRect = new RectSelected(graphics2d,startPoint,endPoint,color,color2);
            idrawRect.Draw();

        }

        if (this.shade.equals("OUTLINE")){
            idrawRect = new RectOutline(graphics2d,startPoint,endPoint,color,color2);
            idrawRect.Draw();
        }
        else if (this.shade.equals("OUTLINE_AND_FILLED_IN")) {
            idrawRect = new RectDoTwo(graphics2d,startPoint,endPoint,color,color2);
            idrawRect.Draw();
        }
        else {
            idrawRect = new RectFill(graphics2d,startPoint,endPoint,color,color2);
            idrawRect.Draw();
        }


    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

