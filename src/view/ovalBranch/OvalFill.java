package view.ovalBranch;

import view.Commands.DrawAll;
import view.drawhandler.DrawHandler;
import view.interfaces.IDrawForAll;
import model.persistence.Point;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/15
 **/
public class OvalFill extends DrawHandler {
    //draw a circle with fill in

    public OvalFill(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2){
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    @Override
    public void Draw() {

        graphics2D.fillOval(startPoint.x, startPoint.y, width, height);
        System.out.println("draw_fill_oval");

    }
}
