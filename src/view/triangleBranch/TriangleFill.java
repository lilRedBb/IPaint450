package view.triangleBranch;

import view.drawhandler.DrawHandlerShade;
import model.persistence.Point;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/15
 **/
public class TriangleFill extends DrawHandlerShade {
    //draw a triangle with fill in

    public TriangleFill(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2){
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    @Override
    public void Draw() {
        graphics2D.fillPolygon(xPoints,yPoints,3);
        System.out.println("draw_tri_fill");
    }
}
