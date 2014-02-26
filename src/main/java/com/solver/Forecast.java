package com.solver;

import java.util.ArrayList;
import java.util.List;

import com.utils.Board;
import com.utils.Point;
import com.utils.Vector2d;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 26.02.14
 * Time: 15:45
 */
public class Forecast {
    public ArrayList<MeatChopper> choppers;
    public ArrayList<BoardData> rezult;
    public Forecast()
    {
        choppers = new ArrayList<MeatChopper>();
    }

    public void startCalc(ArrayList<BoardData> oldData, Board board, ArrayList<BoardData> rezult)
    {
        this.rezult = rezult;
        choppers.clear();
        List<Point> points = board.getMeatChoppers();
        BoardData boardData;
        Vector2d vect;
        for(Point point : points)
        {
            //можно заранее сделать массив чоперов, чтоб избежать new MeatChopper(...
            boardData = oldData.get(oldData.size() - 2);
            if(boardData.getCell(point.upPoint()) == GObj.CHOPPERS)
            {
                choppers.add(new MeatChopper(point, Vector2d.DOWN()));
            }
            else if(boardData.getCell(point.downPoint()) == GObj.CHOPPERS)
            {
                choppers.add(new MeatChopper(point, Vector2d.UP()));
            }
            else if(boardData.getCell(point.leftPoint()) == GObj.CHOPPERS)
            {
                choppers.add(new MeatChopper(point, Vector2d.RIGHT()));
            }
            else if(boardData.getCell(point.rightPoint()) == GObj.CHOPPERS)
            {
                choppers.add(new MeatChopper(point, Vector2d.LEFT()));
            }
        }
    }

}
