package com.solver;
/**
 * User: ${mental}
 * Date: 28.02.14
 * Time: 10:47
 */
public class WaveNode {
    public WaveNode parent;
    public int x, y;
    public double score = 0;
    public double totalScore = 0;
    public boolean bRoot = false;
    public WaveNode firstNode;
    public int direction;

    public WaveNode(WaveNode parent, int x, int y, double score, int direction)
    {
        this.parent = parent;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.score = score;
        if(parent.bRoot)
        {
            firstNode = this;
            totalScore = score;
        }
        else
        {
            firstNode = parent.firstNode;
            firstNode.totalScore += score;
        }
    }

    public WaveNode(int x, int y)
    {
        this.x = x;
        this.y = y;
        bRoot = true;
    }

}
