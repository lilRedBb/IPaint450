package model.persistence;

import view.interfaces.IUndoable;

import java.util.ArrayList;
import java.util.Arrays;

import static model.persistence.ConcatArrays.concatArrays;

/**
 * @author lilred
 * @date 2023/08/07
 **/
public class MakeGroupFrame {


    //return the left-upper most Point
    //return the right-downer most Point
    public PairPoint groupFramePoints(ArrayList<IUndoable> groupArray){
        Point start =  new Point();
        Point end = new Point();

        int[] startX = new int[groupArray.size()];
        int[] startY = new int[groupArray.size()];
        int[] endX = new int[groupArray.size()];
        int[] endY = new int[groupArray.size()];

        for (int i=0;i<groupArray.size();i++){
            startX[i] = groupArray.get(i).returnStartPoint().x;
            startY[i] = groupArray.get(i).returnStartPoint().y;
            endX[i] = groupArray.get(i).returnEndPoint().x;
            endY[i] = groupArray.get(i).returnEndPoint().y;
        }

        int[] concatArrayX = concatArrays(startX, endX);
        int[] concatArrayY = concatArrays(startY, endY);

        start.x = Arrays.stream(concatArrayX).min().getAsInt()-10;
        start.y = Arrays.stream(concatArrayY).min().getAsInt()-10;
        end.x = Arrays.stream(concatArrayX).max().getAsInt()+10;
        end.y = Arrays.stream(concatArrayY).max().getAsInt()+10;
        
        return new PairPoint(start,end);
    }
    
}
