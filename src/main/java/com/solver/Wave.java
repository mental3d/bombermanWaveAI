package com.solver;

import com.Direction;
import com.solver.gui.GUIDebugger;

import java.util.ArrayList;

/**
 * User: ${mental}
 * Date: 28.02.14
 * Time: 11:27
 */
public class Wave {
    private int[][] boardCells;
    private BoardRaw raw;
    private ArrayList<WaveNode> firstNods = new ArrayList<WaveNode>();
    private ArrayList<WaveNode> endNods = new ArrayList<WaveNode>();
    private ArrayList<WaveNode> lastNods = new ArrayList<WaveNode>();
    private ArrayList<WaveNode> oldLastNods;
    private int idWave = 1;
    private int height=0, width=0;
    private int[] dx = {1, 0, -1, 0};   // смещения, соответствующие соседям ячейки
    private int[] dy = {0, 1, 0, -1};   // справа, снизу, слева и сверху
    private int currIter = 0;

    public int evaluateChopper = 0;
    public int evaluateFree = 10;
    public int evaluateBomberman = 0;
    private double evaluateBlast = 0.01;
    public GObj findObj;
    private boolean bFind = false;
    public double score=0;

    public Wave(BoardRaw boardRaw)
    {
        this.raw = boardRaw;
        this.height = raw.height;
        this.width = raw.width;
        if(width != 0  && height != 0)
        {
            boardCells = new int[width][height];
        }
    }

    public Direction getDirection(int x, int y)
    {
        //System.out.print(raw.getCell(0, 0, 0)+" "+raw.getCell(0, 1, 1)+" "+raw.getCell(0, 2, 2)+" "+raw.getCell(0, 3, 3)+" "+raw.getCell(0, 4, 4)+" "+raw.getCell(0, 5, 5)+" \n");
        //System.out.print(raw.getCell(1, 0, 0)+" "+raw.getCell(1, 1, 1)+" "+raw.getCell(1, 2, 2)+" "+raw.getCell(1, 3, 3)+" "+raw.getCell(1, 4, 4)+" "+raw.getCell(1, 5, 5)+" ");
        GUIDebugger.clearDebugDraw();
        GUIDebugger.DrawObjDebug(GObj.GREEN, x, y);
        if(width == 0  || height == 0)
        {
            height = raw.height;
            width = raw.width;
            boardCells = new int[width][height];
        }

        bFind = false;
        idWave++;
        currIter = 0;
        endNods = new ArrayList<WaveNode>();
        firstNods = new ArrayList<WaveNode>();
        boardCells[x][y] = idWave;
        lastNods = new ArrayList<WaveNode>();
        lastNods.add(new WaveNode(x, y));
        if(raw.getCell(0,x,y) == GObj.FREE)
        {
            evaluateBlast = 0;
        }
        else
        {
            evaluateBlast = 0.01;
        }
        int i = 0;
        while (lastNods.size() > 0 && i < Forecast.FORECAST_ITER - 5 && !bFind)
        {
            nextStep();
            i++;
        }
        if(firstNods.size()>0)
        {
            WaveNode max = firstNods.get(0);
            for(WaveNode node:firstNods)
            {
                if(node.totalScore > max.totalScore)
                {
                    max = node;
                }
            }
            score = max.totalScore;
            switch (max.direction)
            {
                case 0:
                    return Direction.RIGHT;
                case 1:
                    return Direction.DOWN;
                case 2:
                    return Direction.LEFT;
                case 3:
                    return Direction.UP;
            }
        }
        return Direction.ACT;
    }

    private void nextStep()
    {
        currIter++;
        oldLastNods = lastNods;
        lastNods = new ArrayList<WaveNode>();

        for(WaveNode node:oldLastNods)
        {
            if(raw.getCell(currIter, node.x, node.y) == GObj.CHOPPERS)
            {
                endNods.add(node);
                continue;
            }
            boolean bEnd = true;
            WaveNode tempNode;
            for(int i = 0; i < 4; i++)
            {
                double score = checkStep(node.x + dx[i], node.y + dy[i]);
                if(score > 0)
                {
                    int nx =  node.x + dx[i];
                    int ny =  node.y + dy[i];
                    tempNode = new WaveNode(node, nx, ny, (score), i);
                    lastNods.add(tempNode);
                    bEnd = false;
                    boardCells[nx][ny] = idWave;
                    GUIDebugger.DrawObjDebug(GObj.BLUE, nx, ny);
                    if(currIter == 1)
                    {
                        firstNods.add(tempNode);
                    }
                }
            }
            if(bEnd)
            {
                endNods.add(node);
            }
        }
    }

    private double checkStep(int x, int y){
        if(x > width || y > height)
        {
            return 0;
        }

        if(boardCells[x][y] == idWave)
        {
            return 0;
        }

        return evaluateCell(x, y);

    }


    private double evaluateCell(int x, int y)
    {
        GObj cell = raw.getCell(currIter, x, y);
        if(findObj != null)
            if(cell == findObj)
                bFind = true;
        switch (cell)
        {
            case BOMBS:
            case WALLS:
            case BARRIERS:
                return 0;
            case BLASTS:
                return 0.000001;

            case BOMBERMAN:
                return evaluateBomberman/currIter;

            case CHOPPERS:
                return evaluateChopper/currIter;

            case FREE:
                return evaluateFree/(currIter*0.5);
        }
        return 1;
    }
}
