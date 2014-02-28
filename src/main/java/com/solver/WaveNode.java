package com.solver;
import com.utils.Point;
/**
 * User: ${mental}
 * Date: 28.02.14
 * Time: 10:47
 */
public class WaveNode {
    public WaveNode parent;
    public Point pos;
    public int score = 0;

    public WaveNode(WaveNode parent, Point pos, int score)
    {
        this.parent = parent;
        this.pos = pos;
        this.score = score;
    }

}
