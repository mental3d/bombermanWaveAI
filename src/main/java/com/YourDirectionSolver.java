package com;

import com.solver.gui.GUIDebugger;
import com.solver.*;
import com.utils.Board;

/**
 * User: your name
 */
public class YourDirectionSolver implements DirectionSolver {
    private GUIDebugger gui;
    private BoardRaw raw;
    private WaveAI ai;


    public YourDirectionSolver()
    {
        super();
        raw = new BoardRaw();
        ai = new WaveAI(raw);
        gui = new GUIDebugger();
        gui.start();
    }
    @Override
    public String get(Board board) {
        BoardData data = raw.setBoard(board);
        //System.out.print("clone boardData is "+data.testClone()+'\n');
        gui.setData(data);
       // gui.setData(board);
        System.out.print(raw.sizeHistory());
        System.out.print("\n ");

        if(raw.sizeHistory() > 3)
           return ai.getDirection(board);
        return Direction.STOP.toString();
    }
}
