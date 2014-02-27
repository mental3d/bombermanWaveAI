package com.utils;

/**
 * User: mental
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point pt) {
        this(pt.x, pt.y);
    }

    public static Point pt(int x, int y) {
        return new Point(x, y);
    }

    @Override
    public boolean equals(Object o) {
        Point pt = (Point)o;
        return pt.x == x && pt.y == y;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", x, y);
    }

    public boolean isBad(int boardSize) {
        return x >= boardSize || y >= boardSize || x < 0 || y < 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point upPoint()
    {
        return new Point(x, y + 1);
    }

    public Point downPoint()
    {
        return new Point(x, y - 1);
    }

    public Point leftPoint()
    {
        return new Point(x - 1, y);
    }

    public Point rightPoint()
    {
        return new Point(x + 1, y);
    }

    public void move(Vector2d vect)
    {
        x += vect.x;
        y += vect.y;
    }

    public Point movePoint(Vector2d vect)
    {
        return new Point((int)(x + vect.x), (int)(y + vect.y));
    }
}