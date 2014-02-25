package com.solver;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 21.02.14
 * Time: 16:00
 */
import com.utils.Board;

import java.util.ArrayList;

public class BoardRaw {
    private ArrayList<BoardData> oldData = new ArrayList<BoardData>();
    public BoardRaw()
    {

    }

    public BoardData setBoard(Board board)
    {
        BoardData data = new BoardData(board);
        oldData.add(data);
        return  data;
    }

    public int sizeHistory()
    {
        return oldData.size();
    }
}
