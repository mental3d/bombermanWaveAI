package com.solver;

import com.Direction;

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
        if(width == 0  || height == 0)
        {
            height = raw.height;
            width = raw.width;
            boardCells = new int[width][height];
        }
        if(boardCells == null)
        {
            System.out.print("\nWave boardCells == null\n");
        }

        idWave++;
        currIter = 0;
        endNods.clear();
        firstNods.clear();
        boardCells[x][y] = idWave;
        System.out.print("getDirection add rootNode\n");
        lastNods.add(new WaveNode(x, y));
        int i = 0;
        while (lastNods.size() > 0 && i < 20)
        {
            nextStep();
            i++;
        }
        System.out.print("getDirection end nextStep "+firstNods.size()+'\n');
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
      //  System.out.print("nextStep\n");
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
                int score = checkStep(node.x + dx[i], node.y + dy[i]);
                if(score > 0)
                {
                    int nx =  node.x + dx[i];
                    int ny =  node.y + dy[i];
                    tempNode = new WaveNode(node, nx, ny, score/(currIter*0.97), i);
                    lastNods.add(tempNode);
                    bEnd = false;
                    boardCells[nx][ny] = idWave;
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

    private int checkStep(int x, int y){
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


    private int evaluateCell(int x, int y)
    {
        GObj cell = raw.getCell(currIter, x, y);
        switch (cell)
        {
            case BOMBS:
            case WALLS:
            case BARRIERS:
            case BLASTS:
                return 0;

            case BOMBERMAN:
                break;
            case CHOPPERS:
                return 200;

            case FREE:

                break;
        }
        return 1;
    }
}
