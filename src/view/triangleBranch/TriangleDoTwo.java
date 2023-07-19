package view.triangleBranch;

import view.drawhandler.DrawHandlerShade;
import model.persistence.Point;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/15
 **/
public class TriangleDoTwo extends DrawHandlerShade {
    //draw a triangle with fill in and outline
    public TriangleDoTwo(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2){
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    @Override
    public void Draw() {

        graphics2D.fillPolygon(xPoints,yPoints,3);

        graphics2D.setColor(color2);
        graphics2D.drawPolygon(xPoints,yPoints,3);
        System.out.println("draw_tri_2");
    }
}
