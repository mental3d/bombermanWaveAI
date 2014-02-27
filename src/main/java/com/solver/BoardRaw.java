package com.solver;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 21.02.14
 * Time: 16:00
 */
import com.utils.Board;
import org.apache.log4j.LogManager;

import java.util.ArrayList;

public class BoardRaw {
    private ArrayList<BoardData> oldData = new ArrayList<BoardData>();
    private ArrayList<BoardData> forecastData = new ArrayList<BoardData>();
    private Forecast forecast;
    private double countError = 0;
    private double countForecast = 0;
    public BoardRaw()
    {
        forecast = new Forecast();
    }

    public BoardData setBoard(Board board)
    {
        BoardData data = new BoardData(board);
        oldData.add(data);

        //debug//
        if(forecastData.size()>0)
        {
            logStatisticsTrueForecast(data, forecastData.get(0), 0, 0, data.width, data.height);
            //logStatisticsTrueForecast(data, forecastData.get(0), 30, 30, 9, 15);
        }
        //debug//

        if(oldData.size()>2)
        {
            forecast.startCalc(oldData, board, forecastData);
        }
        return  data;
    }

    public int sizeHistory()
    {
        return oldData.size();
    }

    public void logStatisticsTrueForecast(BoardData rb, BoardData fb, int x, int y, int w, int h)
    {
        String dstr ="";
        dstr += "****StatisticsTrueForecast*****\n";
        int countError = 0;
        for(int i = 0; i < w; i++)
        {
            for(int j = 0; j < h; j++)
            {
                if(rb.getCell(i, j) != fb.getCell(i,j))
                {
                    if(rb.getCell(i, j) == GObj.CHOPPERS || fb.getCell(i, j) == GObj.CHOPPERS)
                    {
                        dstr += "\terror: pos("+i+", "+j+")\t"+ "real("+rb.getCell(i, j)+")\tforecast("+ fb.getCell(i,j)+")\n" ;
                        countError++;
                    }
                }
            }
        }
        dstr +=  "count chopper: " + rb.counChoppers+'\n';
        this.countError += countError;
        this.countForecast +=  rb.counChoppers;
        dstr +=  "count error: " + countError+ " ("+ (this.countError/this.countForecast*100) + "%)\n";
        LogManager.getRootLogger().debug(dstr);
    }


}
