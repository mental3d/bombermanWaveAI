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
    private enum Strategy{ACT, CHASE, RUN_OFF};
    private BoardRaw raw;
    private Wave chaseChopperWave;
    private Wave chaseBombermanWave;
    private Wave runOffWave;
    private int[] dx = {0,1, 0, -1, 0};   // смещения, соответствующие соседям ячейки
    private int[] dy = {0,0, 1, 0, -1};   // справа, снизу, слева и сверху
    private int x, y;
    private int timerRunOff = 0;
    public WaveAI(BoardRaw raw)
    {
        this.raw = raw;
        chaseChopperWave = new Wave(raw);
        chaseChopperWave.evaluateChopper = 2000;
        chaseChopperWave.findObj = GObj.CHOPPERS;
        runOffWave = new Wave(raw);
        runOffWave.evaluateChopper = 0;
        runOffWave.evaluateFree = 30;
        chaseBombermanWave = new Wave(raw);
        chaseBombermanWave.evaluateBomberman = 6000;
        chaseBombermanWave.findObj = GObj.BOMBERMAN;
    }

    public String getDirection(Board board)
    {
        Point point = board.getBomberman();
        x =  point.getX();
        y =  point.getY();
        Strategy strategy = chooseStrategy();
        System.out.print("\n "+strategy + '\n');
        switch (strategy)
        {
            case ACT:
                return Direction.ACT.toString()+","+runOffWave.getDirection(point.getX(), point.getY()).toString();
            case CHASE:
                return chase();
            case RUN_OFF:
                return runOffWave.getDirection(point.getX(), point.getY()).toString();
        }
        return Direction.STOP.toString();
    }

    private Strategy chooseStrategy()
    {
        System.out.print("choose strtegy\n");
        if(timerRunOff != 0)
        {
            timerRunOff--;
            return Strategy.RUN_OFF;
        }

        for(int k=1; k<4;k++)
        for(int iter = 0; iter < 3; iter++)
        {
            for(int i =0; i < 5; i++)
            {
                System.out.print("choose strtegy, cell check iter"+iter+"\t "+(x+dx[i])+" "+ (y+dy[i])+'\t');
                try
                {
                    GObj cell = raw.getCell(iter, x+dx[i]*k, y+dy[i]*k);


                    System.out.print(cell+ "\n ");
                    if(cell == GObj.CHOPPERS || cell == GObj.BOMBERMAN)
                    {
                        timerRunOff = 7;
                        return Strategy.ACT;
                    }
                }
                catch(Exception e)
                {
                    continue;
                }
            }
        }
        return Strategy.CHASE;
    }

    private String chase()
    {
        Direction chopper = chaseChopperWave.getDirection(x, y);
        Direction bomberman = chaseBombermanWave.getDirection(x, y);
        if(chaseChopperWave.score > chaseBombermanWave.score)
        {
            if(chaseChopperWave.score < 20)
            {
                timerRunOff = 6;
                System.out.print("ass fire");
                return Direction.ACT.toString();
            }
            return chopper.toString();
        }
        else
        {
            if(chaseBombermanWave.score < 20)
            {
                timerRunOff = 6;
                return Direction.ACT.toString();
            }
            return bomberman.toString();
        }
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
