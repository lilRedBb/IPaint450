package view.Commands;

import view.drawhandler.DrawHandler;
import view.ovalBranch.OvalSelected;
import view.rectBranch.RectDoTwo;
import view.rectBranch.RectFill;
import view.rectBranch.RectOutline;
import view.rectBranch.RectSelected;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawForAll;
import view.gui.PaintCanvas;
import model.persistence.Point;

import java.awt.*;


/**
 * @author lilred
 * @date 2023/07/05
 **/
public class DrawRectCommand extends DrawAll {

    //declare a DrawHandler object,which will mutate according to shadeType in the run() method.
    DrawHandler idrawRect;


    public DrawRectCommand(PaintCanvas pc, Point startPoint, Point endPoint, IApplicationState appstate) {

        super(pc,startPoint,endPoint,appstate);


    }

    //run() method uses state-model-pattern to draw shadeTypes.
    @Override
    public void run() {

        if(getIsSelected()){
            idrawRect = new OvalSelected(graphics2d,startPoint,endPoint,color,color2);
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


}

