package com;

import com.solver.gui.GUIDebugger;
import com.utils.Board;

/**
 * User: your name
 */
public class YourDirectionSolver implements DirectionSolver {
    private GUIDebugger gui;
    public YourDirectionSolver()
    {
        super();
        gui = new GUIDebugger();
        gui.start();
    }
    @Override
    public String get(Board board) {
        gui.update(board);

        return Direction.DOWN.toString()+","+Direction.ACT.toString();
    }
}
