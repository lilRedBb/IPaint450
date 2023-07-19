package view.triangleBranch;

import view.drawhandler.DrawHandler;
import view.interfaces.IDrawForAll;
import model.persistence.Point;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/15
 **/
public class TriangleOutline extends DrawHandler {
    //draw a triangle with outline

    public TriangleOutline(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2){
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    @Override
    public void Draw() {


        graphics2D.drawPolygon(xPoints,yPoints,3);
        System.out.println("draw_tri_outline");

    }
}
