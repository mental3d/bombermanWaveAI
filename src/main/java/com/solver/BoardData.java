package com.solver;

import com.utils.Board;
import com.utils.Point;

import java.util.Collection;
import java.util.List;

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
    public int counChoppers;
    public int counWalls;


    public BoardData(Board board)
    {
        initCellBoard(board.boardSize(), board.boardSize()); //fix внтури boardSize() тяжелая функция можно выполнить один раз в BoardRaw, так как размер доски const
        convert(board);
    }

    public BoardData(GObj [][]cellBoard)
    {
        this.cellBoard = cellBoard;
    }

    public BoardData(BoardData boardData)
    {
        cellBoard = boardData.cloneCellBoard();
    }



    private void initCellBoard(int width, int height)
    {
        this.width = width;
        this.height = height;
        cellBoard = new GObj[width][height];
    }


    private void convert(Board board)
    {
        List<Point> points = board.getBarriers();
        for(Point point : points)
        {
            cellBoard[point.getX()][point.getY()] = GObj.BARRIERS;
        }
        points = board.getDestroyWalls();
        counWalls = points.size();
        for(Point point : points)
        {
            cellBoard[point.getX()][point.getY()] = GObj.WALLS;
        }
        points = board.getMeatChoppers();
        counChoppers = points.size();
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

}
