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
        return Direction.UP.toString()+","+Direction.ACT.toString();
    }
}
