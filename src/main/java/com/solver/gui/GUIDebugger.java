package com.solver.gui;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.02.14
 * Time: 9:57
 * To change this template use File | Settings | File Templates.
 */

import com.utils.Board;

import java.awt.*;
import javax.swing.*;
import com.solver.BoardData;

public class GUIDebugger extends  Thread{
    private DrawJPanel draw;
    private BoardData board = null;
    public GUIDebugger()
    {
        JFrame frame = new JFrame("Simple GUI");
        draw = new DrawJPanel(0,0,0);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.add(draw);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void update(BoardData board)
    {
        this.board = board;
    }

    @Override
    public void run()
    {
        do{
            if(board != null)
            {
                System.out.println("Привет из побочного потока!");
                board = null;
               // draw.test();

            }
        }
        while(true);
    }
}
