package com.solver;
import com.utils.Point;
/**
 * User: ${mental}
 * Date: 28.02.14
 * Time: 10:47
 */
public class WaveNode {
    public WaveNode parent;
    public int x, y;
    public int score = 0;

    public WaveNode(WaveNode parent, int x, int y, int score)
    {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.score = score;
    }

    public WaveNode(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
