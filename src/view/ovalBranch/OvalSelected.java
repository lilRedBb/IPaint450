package view.ovalBranch;

import model.persistence.Point;
import view.drawhandler.DrawHandler;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/16
 **/
public class OvalSelected extends DrawHandler {
    //give selected outline to a circle

    public OvalSelected(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2) {
        super(graphics2D, startPoint, endPoint, color, color2);
    }

    public void Draw(){

        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawOval(startPoint.x-5, startPoint.y-5, width+10, height+10);
    }
}
