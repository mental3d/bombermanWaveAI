package com.solver.gui;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.02.14
 * Time: 9:57
 * To change this template use File | Settings | File Templates.
 */

import com.solver.GObj;
import com.utils.Board;

import javax.swing.*;
import com.solver.BoardData;
import com.utils.Point;

import java.util.List;

public class GUIDebugger extends  Thread{
    private DrawJPanel draw;
    private GObj[][] boardGObjData;
    private BoardData boardData;
    private Board board;
    static private GUIDebugger instance;
    public GUIDebugger()
    {
        JFrame frame = new JFrame("Simple GUI");
        draw = new DrawJPanel(800,800,20);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.add(draw);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        instance = this;
    }

    public void setData(BoardData board)
    {
        boardData = board;
        this.boardGObjData = board.cloneCellBoard();
    }

    public void setData(Board board)
    {
        this.board = board;
    }

    static public void DrawObjDebug(GObj id, Point point)
    {
        instance.drawObjDebug(id, point.getX(), point.getY());
    }

    static public void DrawObjDebug(GObj id, int x, int y)
    {
        instance.drawObjDebug(id, x, y);
    }

    public void drawObjDebug(GObj id, int x, int y)
    {
        draw.drawObjectDebug(id, x, y);
        draw.updateUI();
    }

    @Override
    public void run()
    {
        try
        {
            do{
                if(boardGObjData != null)
                {
                    runDrawBoardData();
                    boardGObjData = null;
                }
                if(board != null)
                {
                    runDrawBoard();
                    board = null;
                }
                Thread.sleep(500);
            }
            while(true);
        }
        catch(InterruptedException e) {
            System.out.println("the thread  was interrupted.");
        }
    }

    private void runDrawBoardData()
    {
        System.out.println("ui boardGObjData thread");
        for(int x = 0; x < boardGObjData.length; x++)
        {
            for(int y = 0; y < boardGObjData.length; y++)
            {
                draw.drawObject(boardData.getCell(x,y), x, y);
            }
        }
        draw.updateUI();

    }

    public void runDrawBoard()
    {
        System.out.println("ui board thread");
        List<Point> points = board.getBarriers();
        for(Point point : points)
        {
            draw.drawObject(GObj.BARRIERS, point.getX(), point.getY());
        }
        points = board.getDestroyWalls();
        for(Point point : points)
        {
            draw.drawObject(GObj.WALLS, point.getX(), point.getY());
        }
        points = board.getMeatChoppers();
        for(Point point : points)
        {
            draw.drawObject(GObj.CHOPPERS, point.getX(), point.getY());
        }
        draw.updateUI();

    }

}
