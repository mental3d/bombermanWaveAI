package com.solver;

/**
 * User: ${mental}
 * Date: 19.02.14
 * Time: 14:27
*/
import com.utils.Point;
import com.utils.Vector2d;


public class MeatChopper {
    private Point pos;
    private Vector2d vMove;

    public void init(Point pos, Vector2d vMove)
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

    public void rotate()
    {
        vMove.normal();
    }

    public void move()
    {
        pos.move(vMove);
    }

    public Point getPos()
    {
        return new Point(pos);
    }
}
