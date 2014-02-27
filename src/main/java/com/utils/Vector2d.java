package com.utils;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 26.02.14
 * Time: 16:04
 */
public class Vector2d {
    public double x = 0, y = 0;
    public Vector2d(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void revert()
    {
        x *= -1;
        y *= -1;
    }

    public void normal()
    {
        double t = x;
        x = y;
        y = t;
    }

    static public Vector2d UP()
    {
        return new Vector2d(0,1);
    }
    static public Vector2d DOWN()
    {
        return new Vector2d(0,-1);
    }
    static public Vector2d LEFT()
    {
        return new Vector2d(-1,0);
    }
    static public Vector2d RIGHT()
    {
        return new Vector2d(1,0);
    }

}
