package sample.utils;

import javafx.scene.control.Button;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
    //getTodayYear can got the year information of today
    public int getTodayYear() {
        Date today = new Date();
        DateFormat format = new SimpleDateFormat("yyyy");
        String year = format.format(today);
        return Integer.parseInt(year);
    }

    //getTodayMonth can get the month information of today
    public int getTodayMonth() {
        Date today = new Date();
        DateFormat format = new SimpleDateFormat("MM");
        String month = format.format(today);
        System.out.println(month);
        return Integer.parseInt(month);
    }

    //getTodayDate can get the date information of today
    public int getTodayDate() {
        Date today = new Date();
        DateFormat format = new SimpleDateFormat("dd");
        String date = format.format(today);
        return Integer.parseInt(date);
    }

    //This function returns a calendar table, which is the date distribution that
    //should be displayed when the specified year and month are selected, including
    //three pieces of information of year, month and day, expressed in the form of
    //an ArrayList
    public ArrayList<ArrayList<Integer>> getTimeNumber(int month, int year, int componentListSize) throws ParseException {
        SimpleDateFormat dc = new SimpleDateFormat();
        dc.applyPattern("yyyy-MM-dd");
        String dateString = year + "-" + month + "-01";
        Date date = dc.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //Determine the location of the first day of the current month to determine the location of the previous month
        int indexOfThisMonth = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.MONTH, -1);
        int lastMonthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDate = lastMonthLength -indexOfThisMonth + 2;
        ArrayList<Integer> dateList = new ArrayList<>();
        ArrayList<Integer> monthList = new ArrayList<>();
        ArrayList<Integer> yearList = new ArrayList<>();
        //Process the date information of the previous month
        for (int i = 0; i < indexOfThisMonth -1; i++) {
            dateList.add(firstDate + i);
            monthList.add(calendar.get(Calendar.MONTH) + 1);
            ;
            yearList.add(calendar.get(Calendar.YEAR));
        }
        calendar.add(Calendar.MONTH, 1);
        int thisMonthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //Process the current month's information
        for (int i = 1; i <= thisMonthLength; i++) {
            dateList.add(i);
            //I don't know why the month starts from 0
            monthList.add(calendar.get(Calendar.MONTH) + 1);
            yearList.add(calendar.get(Calendar.YEAR));
        }
        calendar.add(Calendar.MONTH, 1);
        //Process next month’s information
        for (int i = 1; i <= componentListSize + 1 -thisMonthLength -indexOfThisMonth; i++) {
            dateList.add(i);
            monthList.add(calendar.get(Calendar.MONTH) + 1);
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