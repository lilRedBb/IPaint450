package view.rectBranch;

import view.drawhandler.DrawHandlerShade;
import model.persistence.Point;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/15
 **/
public class RectOutline extends DrawHandlerShade {
    //draw a rect with outline

    public RectOutline(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2){
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    @Override
    public void Draw() {

        graphics2D.drawRect(startPoint.x, startPoint.y, width, height);
        System.out.println("draw_outline_rect");
    }
}
