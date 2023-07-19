package view.rectBranch;

import view.drawhandler.DrawHandlerShade;
import model.persistence.Point;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/15
 **/
public class RectDoTwo extends DrawHandlerShade {
    //draw a rect with fill in and outline

    public RectDoTwo(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2){
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    @Override
    public void Draw() {
        graphics2D.fillRect(startPoint.x, startPoint.y, width, height);

        graphics2D.setColor(color2);
        graphics2D.drawRect(startPoint.x, startPoint.y, width, height);
        System.out.println("draw_2_rect");




    }
}
