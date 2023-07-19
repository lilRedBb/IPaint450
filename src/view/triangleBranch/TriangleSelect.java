package view.triangleBranch;

import model.persistence.Point;
import view.drawhandler.DrawHandlerShade;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/16
 **/
public class TriangleSelect extends DrawHandlerShade {
    //draw dotted outline for triangle
    public TriangleSelect(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2) {
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    public void Draw() {
        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] -= 5;
            yPoints[i] -= 5;
        }
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawPolygon(xPoints, yPoints, 3);

    }
}
