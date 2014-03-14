package com.solver;

import java.util.ArrayList;

/**
 * User: ${mental}
 * Date: 28.02.14
 * Time: 11:27
 */
public class Wave {
    private int[][] boardCells;
    private BoardRaw boardRaw;
    private ArrayList<WaveNode> endNods = new ArrayList<WaveNode>();
    private ArrayList<WaveNode> lastNods = new ArrayList<WaveNode>();
    private ArrayList<WaveNode> oldLastNods;
    private int idWave = 1;
    private int height, width;
    private int[] dx = {1, 0, -1, 0};   // смещения, соответствующие соседям ячейки
    private int[] dy = {0, 1, 0, -1};   // справа, снизу, слева и сверху

    public Wave(BoardRaw boardRaw, int width, int height)
    {
        this.boardRaw = boardRaw;
        this.height = height;
        this.width = width;
        boardCells = new int[width][height];
    }

    public void startWave(int x, int y)
    {
        endNods.clear();
        lastNods.add(new WaveNode(x, y));
        while (lastNods.size() > 0)
        {
            nextStep();
        }
    }

    private void nextStep()
    {
        oldLastNods = lastNods;
        lastNods = new ArrayList<WaveNode>();

        for(WaveNode node:oldLastNods)
        {
            boolean bEnd = true;
            for(int i = 0; i < 4; i++)
            {
                int score = checkStep(node.x + dx[i], node.y + dy[i]);
                if(score > 0)
                {
                    lastNods.add(new WaveNode(node, node.x + dx[i], node.y + dy[i], score));
                    bEnd = false;
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

        return сellEvaluate();

    }


    private int сellEvaluate()
    {
        return 1;
    }


}
