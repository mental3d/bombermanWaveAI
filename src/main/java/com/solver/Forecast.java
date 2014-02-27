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
    private MeatChopper []choppers;
    private int countChopper;
    private ArrayList<BoardData> result;
    private BoardData lastResult;

    public Forecast()
    {
        choppers = new MeatChopper[30];
        countChopper = 0;
        for(int i = 0; i < choppers.length; i++)
        {
            choppers[i] = new MeatChopper();
        }
    }

    public void startCalc(ArrayList<BoardData> oldData, Board board, ArrayList<BoardData> result)
    {
        result.clear();
        this.result = result;
        lastResult = oldData.get(oldData.size() - 1);
        BoardData boardData;
        Vector2d vect;
        countChopper = 0;
        List<Point> points = board.getMeatChoppers();
        for(Point point : points)
        {
            boardData = oldData.get(oldData.size() - 2);
            if(boardData.getCell(point.upPoint()) == GObj.CHOPPERS)
            {
                choppers[countChopper].init(point, Vector2d.DOWN());
                countChopper++;
            }
            else if(boardData.getCell(point.downPoint()) == GObj.CHOPPERS)
            {
                choppers[countChopper].init(point, Vector2d.UP());
                countChopper++;
            }
            else if(boardData.getCell(point.leftPoint()) == GObj.CHOPPERS)
            {
                choppers[countChopper].init(point, Vector2d.RIGHT());
                countChopper++;
            }
            else if(boardData.getCell(point.rightPoint()) == GObj.CHOPPERS)
            {
                choppers[countChopper].init(point, Vector2d.LEFT());
                countChopper++;
            }
        }
        calc(); //для теста один шаг
    }

    private void calc()
    {
        lastResult = new BoardData(lastResult);
        for(int i = 0; i < countChopper; i++)
        {
            calcChopperMove(choppers[i]);
        }
        result.add(lastResult);
    }

    private boolean calcChopperMove(MeatChopper chopper)
    {
        Point oldPos = chopper.getPos();
        //пытаемся переместить чопера
        if(!runMove(chopper))
        {
            //неудача повернем на 180
            chopper.revert();
            if(!runMove(chopper))
            {
                //неудача повернем на 90
                chopper.rotate();
                if(!runMove(chopper))
                {
                    //неудача повернем на 180
                    chopper.revert();
                    if(!runMove(chopper))
                    {
                        return false;
                    }
                }
            }
        }
        lastResult.setCell(oldPos, null);
        lastResult.setCell(chopper.getPos(), GObj.CHOPPERS);
        return true;
    }

    private boolean runMove(MeatChopper chopper)
    {
        if(lastResult.getCell(chopper.nextPoint()) == null)
        {
            chopper.move();
            return true;
        }
        return false;
    }
}
