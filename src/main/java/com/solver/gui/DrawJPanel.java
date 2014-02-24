package com.solver.gui;


import java.awt.*;

import java.util.ArrayList;

import com.solver.GameObject;
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
        public GameObject.Type id;
        public Point pos;
        public AddObjDraw(GameObject.Type _id, Point _pos)
        {
            id = _id;
            pos = _pos;
        }
    }
    private ArrayList<AddObjDraw> stack = new ArrayList<AddObjDraw>();
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
        int i = 0;
        while (i < stack.size()) {
            addDrawObject(stack.get(i), g2d);
            i++;
        }
        stack.clear();
    }

    private void drawTable()
    {

    }

    public void drawObject(GameObject.Type id, Point pos)
    {
        pos = new Point(pos.getX()*cellSize, pos.getY()*cellSize);
        stack.add(new AddObjDraw(id, pos));
    }

    private void addDrawObject(AddObjDraw addObjDraw, Graphics2D g)
    {

        switch (addObjDraw.id){
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
                break;
            case BLASTS:
                break;
        }
    }
}
