package sample.utils;

import javafx.scene.control.Button;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
    public int getTodayYear() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy");
        String year = format.format(date);
        return Integer.parseInt(year);
    }
    public int getTodayMonth() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("MM");
        String month = format.format(date);
        return Integer.parseInt(month);
    }

    public ArrayList<Integer> getTimeNumber(int month, int year, int componentListSize) throws ParseException {
        SimpleDateFormat dc = new SimpleDateFormat();
        dc.applyPattern("yyyy-MM-dd");
        String dateString = year +"-"+ month +"-01";
        Date date = dc.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int indexOfThisMonth = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.MONTH, -1);
        int lastMonthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDate = lastMonthLength-indexOfThisMonth+2;
        ArrayList<Integer> dateList = new ArrayList<>();
        for (int i=0; i<indexOfThisMonth-1; i++ ){
            dateList.add(firstDate+i);
        }
        calendar.add(Calendar.MONTH,1);
        int thisMonthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(thisMonthLength);
        for (int i =1; i<=thisMonthLength; i++){
            dateList.add(i);
        }
        for (int i = 1; i<= componentListSize+1-thisMonthLength-indexOfThisMonth; i++){
            dateList.add(i);
        }
        dc = null;
        calendar = null;
        date = null;
        return dateList;

    }
}
