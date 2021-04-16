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
        Date today = new Date();
        DateFormat format = new SimpleDateFormat("yyyy");
        String year = format.format(today);
        return Integer.parseInt(year);
    }
    public int getTodayMonth() {
        Date today = new Date();
        DateFormat format = new SimpleDateFormat("MM");
        String month = format.format(today);
        return Integer.parseInt(month);
    }
    public int getTodayDate() {
        Date today = new Date();
        DateFormat format = new SimpleDateFormat("dd");
        String date = format.format(today);
        return Integer.parseInt(date);
    }

    public ArrayList<ArrayList<Integer>> getTimeNumber(int month, int year, int componentListSize) throws ParseException {
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
        ArrayList<Integer> monthList = new ArrayList<>();
        ArrayList<Integer> yearList = new ArrayList<>();
        for (int i=0; i<indexOfThisMonth-1; i++ ){
            dateList.add(firstDate+i);
            monthList.add(calendar.get(Calendar.MONTH)+1);;
            yearList.add(calendar.get(Calendar.YEAR));
        }
        calendar.add(Calendar.MONTH,1);
        int thisMonthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i =1; i<=thisMonthLength; i++){
            dateList.add(i);
//            月份不知道为什么从0开始了
            monthList.add(calendar.get(Calendar.MONTH)+1);
            yearList.add(calendar.get(Calendar.YEAR));
        }
        calendar.add(Calendar.MONTH,1);
        for (int i = 1; i<= componentListSize+1-thisMonthLength-indexOfThisMonth; i++){
            dateList.add(i);
            monthList.add(calendar.get(Calendar.MONTH)+1);
            yearList.add(calendar.get(Calendar.YEAR));
        }
        ArrayList<ArrayList<Integer>> dateListList = new ArrayList<>();
        dateListList.add(dateList);
        dateListList.add(monthList);
        dateListList.add(yearList);
        dc = null;
        calendar = null;
        date = null;
        return dateListList;

    }
}
