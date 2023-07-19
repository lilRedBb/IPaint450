package view.ovalBranch;

import view.drawhandler.DrawHandlerShade;
import model.persistence.Point;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/15
 **/
public class OvalOutline extends DrawHandlerShade {
    //draw a circle with outline

    public OvalOutline(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2){
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    @Override
    public void Draw() {

        graphics2D.drawOval(startPoint.x, startPoint.y, width, height);
        System.out.println("draw_outline_oval");

    }
}
