package com.solver;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 19.02.14
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */

import com.utils.Point;
import com.utils.Vector2d;

public class MeatChopper {
    private Point pos;
    private Vector2d vMove;
    public MeatChopper(Point pos, Vector2d vMove)
    {
        this.pos = pos;
        this.vMove = vMove;
    }

    public Point nextPoint()
    {
        return pos.movePoint(vMove);
    }

    public void revert()
    {
        vMove.revert();
    }

    public void move()
    {
        pos.move(vMove);
    }
}
