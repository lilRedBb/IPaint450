package view.Commands;

import view.drawhandler.DrawHandlerShade;
import view.ovalBranch.OvalDoTwo;
import view.ovalBranch.OvalFill;
import view.ovalBranch.OvalOutline;
import view.ovalBranch.OvalSelected;
import model.interfaces.IApplicationState;
import model.persistence.Point;
import view.gui.PaintCanvas;

/**
 * @author lilred
 * @date 2023/07/13
 **/
public class DrawOvalCommand extends DrawFatherCommand {

    //declare a DrawHandler object,which will mutate according to shadeType in the run() method.
    DrawHandlerShade idrawOval;


    public DrawOvalCommand(PaintCanvas pc, Point startPoint, Point endPoint, IApplicationState appstate) {
        super(pc,startPoint,endPoint,appstate);



    }

    //run() method uses state-model-pattern to draw shadeTypes.
    @Override
    public void run() {
        if (IsSelected){
            idrawOval = new OvalSelected(graphics2d,startPoint,endPoint,color,color2);
            idrawOval.Draw();
        }

        if (this.shade.equals("OUTLINE")){
            idrawOval = new OvalOutline(graphics2d,startPoint,endPoint,color,color2);
            idrawOval.Draw();
        }
        else if (this.shade.equals("OUTLINE_AND_FILLED_IN")) {
            idrawOval = new OvalDoTwo(graphics2d,startPoint,endPoint,color,color2);
            idrawOval.Draw();
        }
        else {
            idrawOval = new OvalFill(graphics2d,startPoint,endPoint,color,color2);
            idrawOval.Draw();
        }


    }


}
