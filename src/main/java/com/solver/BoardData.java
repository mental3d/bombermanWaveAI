package com.solver;


import com.solver.gui.GUIDebugger;
import com.utils.Board;
import com.utils.Point;

import java.util.Collection;
import java.util.List;
import java.util.Arrays;
/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 21.02.14
 * Time: 16:01
 */
public class BoardData {
    private GObj [][]cellBoard;
    public int height, width;
    private int id;
    public int countChoppers;
    public int countWalls;

    public BoardData(Board board)
    {
        initCellBoard(board.boardSize(), board.boardSize()); //fix внтури boardSize() тяжелая функция можно выполнить один раз в BoardRaw, так как размер доски const
        convert(board);

    }

    public BoardData(GObj [][]cellBoard, int width, int height)
    {
        this.cellBoard = cellBoard;
        this.width = width;
        this.height = height;
    }

    public BoardData(BoardData boardData)
    {
        cellBoard = boardData.cloneCellBoard();
        width = boardData.width;
        height = boardData.height;
    }

    private void initCellBoard(int width, int height)
    {
        this.width = width;
        this.height = height;
        cellBoard = new GObj[width][height];
        for(int x = 0; x < cellBoard.length; x++)
            for(int y = 0; y < cellBoard.length; y++)
            {
                cellBoard[x][y] = GObj.FREE;
            }

    }

    private void convert(Board board)
    {
        width = board.boardSize();
        height = width;
        List<Point> points = board.getBarriers();
        cellBoard[1][1] = GObj.BARRIERS;

        for(Point point : points)
        {
            cellBoard[point.getX()][point.getY()] = GObj.BARRIERS;
        }

        points = board.getDestroyWalls();
        countWalls = points.size();
        for(Point point : points)
        {
            cellBoard[point.getX()][point.getY()] = GObj.WALLS;
        }
        points = board.getMeatChoppers();
        countChoppers = points.size();
        for(Point point : points)
        {
            cellBoard[point.getX()][point.getY()] = GObj.CHOPPERS;
        }
        points = board.getBombs();
        for(Point point : points)
        {
            cellBoard[point.getX()][point.getY()] = GObj.BOMBS;
        }
        Collection<Point> points2 = board.getOtherBombermans();
        for(Point point : points2)
        {
            cellBoard[point.getX()][point.getY()] = GObj.BOMBERMAN;
        }
        points = board.getBlasts();
        for(Point point : points)
        {
            cellBoard[point.getX()][point.getY()] = GObj.BLASTS;
        }
    }

    public GObj getCell(Point point)
    {
        return  cellBoard[point.getX()][point.getY()];
    }

    public GObj getCell(int x, int y)
    {
        return  cellBoard[x][y];
    }

    public void setCell(Point point, GObj type)
    {
        cellBoard[point.getX()][point.getY()] = type;
    }

    public GObj[][] cloneCellBoard()
    {
        GObj [][]array = cellBoard.clone();
        for (int i = 0; i < cellBoard.length; i++) {
            array[i] = array[i].clone();
        }
        return  array;
    }

    public boolean testClone()
    {
        GObj [][]array = cloneCellBoard();
        for(int x = 0; x < array.length; x++)
        {
            for(int y = 0; y < array.length; y++)
            {
                if(array[x][y] != cellBoard[x][y])
                    return false;
            }
        }
        return  true;
    }

    public void testDrawCurrBoardData()
    {
        for(int x = 0; x < cellBoard.length; x++)
        {
            for(int y = 0; y < cellBoard.length; y++)
            {
                GUIDebugger.DrawObjDebug(cellBoard[x][y],x,y);
         //       System.out.print("\t"+cellBoard[x][y]);
            }
        }
    }

}
