package com.solver.gui;


import java.awt.*;

import java.util.ArrayList;

import com.solver.GObj;
import com.utils.Point;
import javax.swing.JPanel;

/**
 * Created with IntellijIDEA.
 * User: user
 * Date: 20.02.14
 * Time: 10:15
 *

 */
public class DrawJPanel extends JPanel {
    //Ellipse2D.Double ddd;

    class AddObjDraw{
        public GObj id;
        public Point pos;
        public AddObjDraw(GObj _id, Point _pos)
        {
            id = _id;
            pos = _pos;
        }
    }
    private ArrayList<AddObjDraw> stack = new ArrayList<AddObjDraw>();
    private ArrayList<AddObjDraw> stackUpLayer = new ArrayList<AddObjDraw>();
    private ArrayList<AddObjDraw> stackDebugObj = new ArrayList<AddObjDraw>();
    private int width, height, cellSize;

    public  DrawJPanel(int width, int height, int cellSize)
    {
        super();
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawTable(g);
        int i = 0;
        while (i < stack.size()) {
            addDrawObject(stack.get(i), g2d);
            i++;
        }
        stack.clear();
        i = 0;
        while (i < stackDebugObj.size()) {
            addDrawObject(stackDebugObj.get(i), g2d);
            i++;
        }
        i = 0;
        while (i < stackUpLayer.size()) {
            addDrawObject(stackUpLayer.get(i), g2d);
            i++;
        }
        stackUpLayer.clear();
    }

    //нарисовать сетку
    private void drawTable(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, width, height);
        g.drawRect(0,0, width, height);
    }

    public void drawObjectDebug(GObj id, int x, int y)
    {
        drawObjectDebug(id, new Point(x, y));
    }

    public void drawObjectDebug(GObj id, Point pos)
    {
        pos = new Point(pos.getX()*cellSize, pos.getY()*cellSize);
        stackDebugObj.add(new AddObjDraw(id, pos));
    }

    public void drawObject(GObj id, int x, int y)
    {
        drawObject(id, new Point(x, y));
    }

    public void clearDebugDraw()
    {
        stackDebugObj = new ArrayList<AddObjDraw>();
    }


    public void drawObject(GObj id, Point pos)
    {
        pos = new Point(pos.getX()*cellSize, pos.getY()*cellSize);
        switch (id)
        {
            case CHOPPERS:
                stackUpLayer.add(new AddObjDraw(id, pos));
                break;
            default:
            stack.add(new AddObjDraw(id, pos));
        }

    }

    private void addDrawObject(AddObjDraw addObjDraw, Graphics2D g)
    {
        switch (addObjDraw.id){
            case FREE:
                g.setColor(Color.WHITE);
                g.fillRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                g.drawRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                break;
            case BLUE:
                g.setColor(Color.BLUE);
                g.fillRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                g.drawRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                break;
            case GREEN:
                g.setColor(Color.GREEN);
                g.fillRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                g.drawRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                break;
            case BARRIERS:
                g.setColor(Color.BLACK);
                g.fillRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                g.drawRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                break;
            case WALLS:
                g.setColor(Color.GRAY);
                g.fillRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                g.drawRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                break;
            case BOMBS:
                g.setColor(Color.DARK_GRAY);
                g.fillRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                g.drawOval(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                break;
            case BOMBERMAN:
                break;
            case CHOPPERS:
                g.setColor(Color.RED);
                g.fillRect(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                g.drawOval(addObjDraw.pos.getX(), addObjDraw.pos.getY(), cellSize, cellSize);
                break;
            case BLASTS:
                break;

        }
    }
}
