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

    private int test = 0;
    public YourDirectionSolver()
    {
        super();
        raw = new BoardRaw();
      // gui = new GUIDebugger();
      //  gui.start();
    }
    @Override
    public String get(Board board) {
        BoardData data = raw.setBoard(board);
        // gui.update(data);
        System.out.print(raw.sizeHistory());
        System.out.print("\n ");
        if(test%2 == 0)
            return Direction.ACT.toString();
        return Direction.UP.toString();
    }
}
