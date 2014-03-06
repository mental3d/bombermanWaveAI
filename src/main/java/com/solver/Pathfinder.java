package com.solver;

import com.Element;
import com.utils.Point;

import java.util.ArrayList;

/**
 * @author Leon
 * @date 02.03.14
 */
public class Pathfinder {

    private int[][] boardCells;
    private Element[][] elements;
    private int boardSize = 100; // for example
    int BOMBERMAN = -4;
    int MEAT_CHOPPER = -5;
    int WALL = -2;
    int DESTROY_WALL = -3;
    int BLANK_CELL = -1;

    int[] dx = {1, 0, -1, 0};   // смещения, соответствующие соседям ячейки
    int[] dy = {0, 1, 0, -1};   // справа, снизу, слева и сверху

    // result coordinates (tmp, will be moved when algorithm will be completed)
    int resultX;
    int resultY;

    // function will writes result in resultX and resultY and returns whether it found smth
    public boolean find(Point startPos, ArrayList<ArrayList<Element>> elementsSet, Element target){



        // fill the ints arrays
        for (int i=0; i < boardSize; ++i){
            for (int j=0; j < boardSize; ++j){
                switch(elementsSet.get(i).get(j)){
                    case BOMBERMAN :
                        boardCells[i][j] = this.BOMBERMAN;
                        break;
                    case WALL :
                        boardCells[i][j] = this.WALL;
                        break;
                    case DESTROY_WALL:
                        boardCells[i][j] = this.DESTROY_WALL;
                        break;
                    case MEAT_CHOPPER:
                        boardCells[i][j] = this.MEAT_CHOPPER;
                        break;
                    default:
                        boardCells[i][j] = this.BLANK_CELL;
                }
            }
        }

        int currentStep = 0;
        int curX, curY;
        int H = elementsSet.size(); // field size
        int W = elementsSet.get(0).size();
        for(int x = 0; x < W; ++x) {
            for(int y = 0; y < H; ++y)
                for (int k = 0; k < 4; ++k ){
                    curY = y + dy[k]; curX = x + dx[k];
                    if ( boardCells[curX][curY] == BLANK_CELL ) {
                        boardCells[curX][curY] = currentStep + 1;
                    }

                    if(target != null){ // if target set
                        if(elementsSet.get(curX).get(curY) == target){  // check if this our target
                            resultX = curX; resultY = curY;
                            return true;
                        }
                    } else if(target == null){ // if target not set
                        if(elementsSet.get(curX).get(curY) == Element.MEAT_CHOPPER ||
                           elementsSet.get(curX).get(curY) == Element.BOMBERMAN)    // check for something
                        {
                            resultX = curX; resultY = curY;
                            return true;
                        }
                    }
                }
            currentStep++;
        }

        return false;
    }

    public static void main(String[] args) {
        //TODO: do a test of find here
    }
}
