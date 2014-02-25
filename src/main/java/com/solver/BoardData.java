package com.solver;

import com.utils.Board;
import com.utils.Point;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 21.02.14
 * Time: 16:01
 */
public class BoardData {
    private GObj [][]cellBoard;
    private int height, width;
    private int id;

    public BoardData(Board board)
    {
        initCellBoard(board.boardSize(), board.boardSize()); //fix внтури boardSize() тяжелая функция можно выполнить один раз в BoardRaw, так как размер доски const
        convert(board);
    }

    public BoardData(GObj [][]cellBoard)
    {
        this.cellBoard = cellBoard;
    }


    private void initCellBoard(int width, int height)
    {
        this.width = width;
        this.height = height;
        cellBoard = new GObj[width][height];
    }


    private void convert(Board board)
    {
        System.out.print("start convert");
        List<Point> points = board.getBarriers();
        for(Point point : points)
        {
            cellBoard[point.getX()][point.getY()] = GObj.BARRIERS;
        }
        points = board.getDestroyWalls();
        for(Point point : points)
        {
            cellBoard[point.getX()][point.getY()] = GObj.WALLS;
        }
    }

    public GObj getCell(int x, int y)
    {
        return  cellBoard[x][y];
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
