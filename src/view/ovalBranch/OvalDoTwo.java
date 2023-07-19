package view.ovalBranch;

import view.drawhandler.DrawHandler;
import view.interfaces.IDrawForAll;
import model.persistence.Point;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/15
 **/
public class OvalDoTwo extends DrawHandler {
    //draw a circle with fill in and outline

    public OvalDoTwo(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2){
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    @Override
    public void Draw() {


        graphics2D.fillOval(startPoint.x, startPoint.y, width, height);

        graphics2D.setColor(color2);
        graphics2D.drawOval(startPoint.x, startPoint.y, width, height);
        System.out.println("draw_2_oval");

    }
}
