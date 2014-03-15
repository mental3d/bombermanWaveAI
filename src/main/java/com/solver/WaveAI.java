package com.solver;


import com.Direction;
import com.utils.Board;
import com.utils.Point;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Leon
 * @date 25.02.14
 */
public class WaveAI {
    private BoardRaw raw;
    private Wave simpleWave;
    public WaveAI(BoardRaw raw)
    {
        this.raw = raw;
        simpleWave = new Wave(raw);
    }

    public Direction getDirection(Board board)
    {
        Point point = board.getBomberman();
        return simpleWave.getDirection(point.getX(), point.getY());
    }

    /**
     *
     * @return whether we found something
     */
    public boolean findPlayer(){

        // TODO implement find of nearest player
        throw new NotImplementedException();
    }

    /**
     *
     * @return whether we found something
     */
    public boolean findChopper(){
        // TODO implement find of nearest chopper
        throw new NotImplementedException();
    }
}
