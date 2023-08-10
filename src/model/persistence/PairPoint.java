package model.persistence;

/**
 * @author lilred
 * @date 2023/08/07
 **/
public class PairPoint {

    public Point first, second;

    PairPoint(Point fixedStart, Point fixedEnd) {
        this.first = fixedStart;
        this.second = fixedEnd;
    }
}
