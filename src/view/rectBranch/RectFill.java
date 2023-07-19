package view.rectBranch;

import view.drawhandler.DrawHandlerShade;
import model.persistence.Point;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/15
 **/
public class RectFill extends DrawHandlerShade {
    //draw a rect with fill in

    public RectFill(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2){
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    @Override
    public void Draw() {
//        graphics2D.fillRect(startPoint.x, endPoint.y, width, height);
        graphics2D.fillRect(startPoint.x, startPoint.y, width, height);
        System.out.println("draw_fill_rect");

    }
}
