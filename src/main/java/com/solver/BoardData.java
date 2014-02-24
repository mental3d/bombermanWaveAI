package com.solver;

import java.util.ArrayList;
import com.utils.Board;
/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 21.02.14
 * Time: 16:01
 */
public class BoardData {
    private ArrayList<ArrayList<Integer>> cellBoard = new ArrayList<ArrayList<Integer>>();
    private int height, width;
    private int id;

    public BoardData(int id, Board board)
    {
        this.id = id;
        initCellBoard(board.boardSize(), board.boardSize()); //fix внтури boardSize() тяжелая функция можно выполнить один раз в BoardRaw, так как размер доски const
    }

    // Copy constructor
    public BoardData(BoardData bd)
    {

    }


    private void initCellBoard(int width, int height)
    {
        this.width = width;
        this.height = height;
        for(int i = 0; i < width; i++)
        {
            cellBoard.add(new ArrayList<Integer>());
        }
    }


    private void convert(Board board)
    {

    }

    public int getCell(int x, int y)
    {
        return  cellBoard.get(x).get(y);
    }

    public  ArrayList<ArrayList<Integer>> getCloneCellBoard()
    {
        ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < width; i++)
        {
            array.add(new ArrayList<Integer>(cellBoard.get(i)));
        }
        return  array;
    }

}
