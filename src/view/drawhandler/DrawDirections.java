package view.drawhandler;

import model.persistence.Point;

/**
 * @author lilred
 * @date 2023/08/05
 **/
public class DrawDirections {


    class PairPoints {
        Point first, second;

        PairPoints(Point fixedStart, Point fixedEnd) {
            this.first = fixedStart;
            this.second = fixedEnd;
        }
    }


    public  PairPoints fixPoints(Point startPoint, Point endPoint){

        Point first = new Point();
        Point second = new Point();

        if (startPoint.x<endPoint.x&&startPoint.y< endPoint.y){

            first = startPoint;
            second= endPoint;
        } else if (startPoint.x>endPoint.x&&startPoint.y> endPoint.y) {

            first = endPoint;
            second = endPoint;

        }else if (startPoint.x>endPoint.x&&startPoint.y< endPoint.y){

            second = endPoint;
            first.y = startPoint.y;;
            first.x = endPoint.x;
        }else {

            second = endPoint;
            first.y = endPoint.y;;
            first.x = startPoint.x;
        }


        return new PairPoints(first,second);

    }




}
