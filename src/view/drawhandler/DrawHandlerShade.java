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
        this.color = color;
        this.color2 = color2;
        this.width = Math.abs(endPoint.x - startPoint.x);
        this.height = Math.abs(endPoint.y - startPoint.y);
        this.xPoints = new int[]{startPoint.x, endPoint.x, startPoint.x};
        this.yPoints = new int[]{startPoint.y, endPoint.y, endPoint.y};
        this.graphics2D.setColor(color);
        this.graphics2D.setStroke(new BasicStroke(5));

        DrawDirections.PairPoints pp = new DrawDirections().fixPoints(startPoint,endPoint);
        this.startPoint = pp.first;
        this.endPoint = pp.second;




    }

    @Override
    public void Draw() {

    }
}
