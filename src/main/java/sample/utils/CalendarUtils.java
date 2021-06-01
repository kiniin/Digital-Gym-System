package sample.utils;

import javafx.scene.control.Button;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * The class is a Util for the Calendar in {@link sample.controllerImpl.BookingController}
 * {@link sample.controllerImpl.CoachCenterController}
 *
 * @author Xiaojian Qi
 * @version 2.0
 */
public class CalendarUtils {

    /**
     * GetTodayYear can got the year information of today
     */
    //getTodayYear can got the year information of today
    public int getTodayYear() {
        Date today = new Date();
        DateFormat format = new SimpleDateFormat("yyyy");
        String year = format.format(today);
        return Integer.parseInt(year);
    }

    /**
     * GetTodayMonth can get the month information of today
     */
    // GetTodayMonth can get the month information of today
    public int getTodayMonth() {
        Date today = new Date();
        DateFormat format = new SimpleDateFormat("MM");
        String month = format.format(today);
        System.out.println(month);
        return Integer.parseInt(month);
    }
    /**
     * getTodayDate can get the date information of today
     */
    //getTodayDate can get the date information of today
    public int getTodayDate() {
        Date today = new Date();
        DateFormat format = new SimpleDateFormat("dd");
        String date = format.format(today);
        return Integer.parseInt(date);
    }

    /**
     * getTodayWeek can find out what day it is today
     */
    public int getTodayWeek(){
        Date today = new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        return c.get(Calendar.DAY_OF_WEEK)-1;
    }

    //This function returns a calendar table, which is the date distribution that
    //should be displayed when the specified year and month are selected, including
    //three pieces of information of year, month and day, expressed in the form of
    //an ArrayList
    /**
     * This function returns a calendar table, which is the date distribution that
     * should be displayed when the specified year and month are selected, including
     * three pieces of information of year, month and day, expressed in the form of
     * an ArrayList
     *
     * @param year the year you want to show
     * @param month the month you want to show
     * @param componentListSize the field size of your container
     * @return A list contain all of the date in a calendar need show
     */
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
    /**
     * Find out the different days bettween two day
     * @param date1 On the day before
     * @param date2 On the day after
     * @return The difference between them
     */
    public static int differentDays(Date date1,Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //不同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //同一年
        {
            System.out.println("judge day2 - day1 : " + (day2-day1)+ " "+ day2 + " " + day1);
            return day2-day1;
        }
    }
}

