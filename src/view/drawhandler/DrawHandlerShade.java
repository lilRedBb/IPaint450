package view.drawhandler;

import model.persistence.Point;
import view.interfaces.IDrawForAllShade;

import java.awt.*;

/**
 * @author lilred
 * @date 2023/07/18
 **/


public class DrawHandlerShade implements IDrawForAllShade {
    //inherited by classes in the *Branch package, so this Class would mutate according to shadeType

    //all subclasses have these shared fields
    public Graphics2D graphics2D;
    public Point startPoint;
    public Point endPoint;
    public Color color;
    public Color color2;

    public int width;
    public int height;
    public int[] xPoints;
    public int[] yPoints;


    //init all the shared fields
    public DrawHandlerShade(Graphics2D graphics2D, Point startPoint, Point endPoint, Color color, Color color2) {
        this.graphics2D =graphics2D;
        this.startPoint = new Point();
        this.endPoint = new Point();

        //handle 4 different drawing directions
        if (startPoint.x<endPoint.x&&startPoint.y< endPoint.y){

            this.startPoint = startPoint;
            this.endPoint=endPoint;
        } else if (startPoint.x>endPoint.x&&startPoint.y> endPoint.y) {

            this.startPoint = endPoint;
            this.endPoint = endPoint;

        }else if (startPoint.x>endPoint.x&&startPoint.y< endPoint.y){

            this.endPoint = endPoint;
            this.startPoint.y = startPoint.y;;
            this.startPoint.x = endPoint.x;
        }else {

            this.endPoint = endPoint;
            this.startPoint.y = endPoint.y;;
            this.startPoint.x = startPoint.x;
        }

        this.color = color;
        this.color2 = color2;
        this.width = Math.abs(endPoint.x - startPoint.x);
        this.height = Math.abs(endPoint.y - startPoint.y);
        this.xPoints = new int[]{startPoint.x, endPoint.x, startPoint.x};
        this.yPoints = new int[]{startPoint.y, endPoint.y, endPoint.y};
        this.graphics2D.setColor(color);
        this.graphics2D.setStroke(new BasicStroke(5));


    }

    @Override
    public void Draw() {

    }
}
