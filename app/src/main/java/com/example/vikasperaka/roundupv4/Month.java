package com.example.vikasperaka.roundupv4;

// Import statements
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Nathan on 1/11/2017.
 */

/**
 * The Month class was created to be input into the RoundUp app to keep track of each Month
 * @author Nathan Chin
 * @date 1/11/2017
 */
public class Month extends AppCompatActivity{
    // Instance variables
    private int endDay;
    private int year;
    private int month;
    private int day;
    private int nameDay;
    private boolean leapYear;
    private int numDays;
    private ArrayList<Dates> daysOfMonth;

    // Constants
    final int DAYS_31 = 31;
    final int DAYS_30 = 30;
    final int DAYS_28 = 28;
    final String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};

    // Created a new Calendar object to get the current time and date of the phone
    Calendar calendar = Calendar.getInstance();

    // Store the current year into an String variable strYear so that the current month can be assigned the value
    SimpleDateFormat theYear = new SimpleDateFormat("y", Locale.US);
    String strYear = theYear.format(calendar.getTime());

    // Store the current month into an String variable strMonth so that the current month can be assigned the value
    SimpleDateFormat theMonth = new SimpleDateFormat("M", Locale.US);
    String strMonth = theMonth.format(calendar.getTime());

    // Store the current day into an String variable strDay so that the current month can have the value
    SimpleDateFormat theDay = new SimpleDateFormat("d", Locale.US);
    String strDay = theDay.format(calendar.getTime());

    // Store the current day name into an String variable strDayName so that the current month have the value
    SimpleDateFormat dayName = new SimpleDateFormat("u", Locale.US); // 1=Monday, 2=Tuesday...7=Sunday
    String strDayName = dayName.format(calendar.getTime());

    /**
     * The Month constructor assigns all the instance variables and populates an ArrayList based on the values of that month
     */
    public Month(){
        year = Integer.parseInt(strYear);
        month = Integer.parseInt(strMonth);
        day = Integer.parseInt(strDay);
        nameDay = Integer.parseInt(strDayName);
        numDays = getMonthDays(month);
        daysOfMonth = new ArrayList<>();

        // Add Dates objects to the List that are empty to account for the starting day name of the month
        int temp1 = findFirstDayOfMonth();
        if(temp1 != 7){
            for(int j = 0; j < temp1; j++ ){
                daysOfMonth.add(new Dates(month));
            }
        }

        // Add Dates objects according to the number of days in the given month
        for(int k = 1; k <= numDays; k++){
            daysOfMonth.add(new Dates(k, month));
        }

        // Add Dates objects to the List that are empty to account for the ending day name of the month
        int temp2 = findLastDayOfMonth();
        temp2 = 6 - temp2;
        if(temp2 < 0){
            daysOfMonth.add(new Dates(month));
            temp2 = 5;
        }
        for(int l = 0; l < temp2; l++){
            daysOfMonth.add(new Dates(month));
        }
    }

    /**
     * The Month constructor takes an int parameter to create a Month object numAfter months after the current month
     * @param numAfter the number of months after the current month
     */
    public Month(int numAfter){
        year = Integer.parseInt(strYear);
        month = Integer.parseInt(strMonth);
        day = Integer.parseInt(strDay);
        nameDay = Integer.parseInt(strDayName);
        numDays = getMonthDays(month);
        daysOfMonth = new ArrayList<>();

        // If the month is the next month after the current one
        if(numAfter == 1){
            // Must reevaluate month to make sure starting day name, ending day name, day, month, and year are correct
            int tempNameDay = findLastDayOfMonth() + 1;
            if(tempNameDay == 8){
                tempNameDay = 1;
            }
            nameDay = tempNameDay;
            month = month + 1;
            if(month > 12){
                month = 1;
                year = year + 1;
            }
            day = 1;
            numDays = getMonthDays(month);
        }
        // If the month is two months after the current one
        else if(numAfter == 2){
            // Must reevaluate month to make sure starting day name, ending day name, day, month, and year are correct
            int temp = numAfter;
            while(numAfter > 0){
                int tempNameDay = findLastDayOfMonth() + 1;
                if(tempNameDay == 8){
                    tempNameDay = 1;
                }
                nameDay = tempNameDay;
                int tempMonth = month + 1;
                if(tempMonth > 12){
                    month = tempMonth - month;
                    year = year + 1;
                }
                else{
                    month = tempMonth;
                }
                day = 1;
                numAfter = numAfter - 1;
                numDays = getMonthDays(month);
            }
        }

        daysOfMonth = new ArrayList<>();
        // Add Dates objects to the List that are empty to account for the starting day name of the month
        int temp1 = nameDay;
        if(temp1 != 7){
            for(int j = 0; j < temp1; j++ ){
                daysOfMonth.add(new Dates(month));
            }
        }

        // Add Dates objects according to the number of days in the given month
        for(int k = 1; k <= numDays; k++){
            daysOfMonth.add(new Dates(k, month));
        }

        // Add Dates objects to the List that are empty to account for the ending day name of the month
        int temp2 = findLastDayOfMonth();
        temp2 = 6 - temp2;
        if(temp2 < 0){
            daysOfMonth.add(new Dates(month));
            temp2 = 5;
        }
        for(int l = 0; l < temp2; l++){
            daysOfMonth.add(new Dates(month));
        }
    }

    /**
     * The getYear method returns the year the month is in
     * @return year the year the month is in
     */
    public int getYear(){
        return year;
    }

    /**
     * The getMonth method returns the number month of the Month object
     * @return month the number month
     */
    public int getMonth(){
        return month;
    }

    /**
     * The getMonthName method returns the name of the month in a String based on each month's number (1-12)
     * @return nameMonth the name of the month in a String
     */
    public String getMonthName(){
        String nameMonth = "";
        switch(month) {
            case 1:
                nameMonth = "Jan";
                break;
            case 2:
                nameMonth = "Feb";
                break;
            case 3:
                nameMonth = "Mar";
                break;
            case 4:
                nameMonth = "Apr";
                break;
            case 5:
                nameMonth = "May";
                break;
            case 6:
                nameMonth = "Jun";
                break;
            case 7:
                nameMonth = "Jul";
                break;
            case 8:
                nameMonth = "Aug";
                break;
            case 9:
                nameMonth = "Sep";
                break;
            case 10:
                nameMonth = "Oct";
                break;
            case 11:
                nameMonth = "Nov";
                break;
            case 12:
                nameMonth = "Dec";
                break;
        }
        return nameMonth;
    }

    /**
     * The getDay method returns the day of the month as an integer
     * @return day the day of the month
     */
    public int getDay(){
        return day;
    }

    /**
     * The getDays method returns an ArrayList of Dates objects based on the Month's properties
     * @return daysOfMonth the ArrayList of Dates of the Month object
     */
    public ArrayList<Dates> getDays(){
        return daysOfMonth;
    }

    /**
     * The getMonthDays method returns the number of days a month should have based on its year and month
     * @param monthNum the number of the month
     * @return monthDays the number of days the month should have
     */
    public int getMonthDays(int monthNum){
        int monthDays = 0;
        switch (monthNum){
            case 1:    monthDays = DAYS_31;
                break;
            case 2:    monthDays = DAYS_28;
                leapYear = isLeapYear(year);
                if(leapYear){
                    monthDays = monthDays + 1;
                }
                break;
            case 3:    monthDays = DAYS_31;
                break;
            case 4:    monthDays = DAYS_30;
                break;
            case 5:    monthDays = DAYS_31;
                break;
            case 6:    monthDays = DAYS_30;
                break;
            case 7:    monthDays = DAYS_31;
                break;
            case 8:    monthDays = DAYS_31;
                break;
            case 9:    monthDays = DAYS_30;
                break;
            case 10:    monthDays = DAYS_31;
                break;
            case 11:    monthDays = DAYS_30;
                break;
            case 12:    monthDays = DAYS_31;
                break;
        }
        return monthDays;
    }

    /**
     * The isLeapYear method tells whether or not the year is a leap year
     * @param currentYear the year to check
     * @return leapYear a boolean variable telling if it is a leap year
     */
    public boolean isLeapYear(int currentYear){
        if((currentYear % 4) == 0){
            leapYear = true;
        }
        return leapYear;
    }

    /**
     * The findFirstDayOfMonth method finds the name of the first day of the month
     * @return currentNameOfDay the name of the day
     */
    public int findFirstDayOfMonth(){
        int currentDay = day;
        int currentNameOfDay = nameDay;
        while(currentDay != 1){
            currentDay = currentDay - 1;
            if((currentNameOfDay - 1) == 0){
                currentNameOfDay = 7;
            }
            else{
                currentNameOfDay = currentNameOfDay - 1;
            }
        }
        return currentNameOfDay;
    }

    /**
     * The findLastDayOfMonth method finds the name of the last day of the month
     * @return currentNameOfDay the name of the day
     */
    public int findLastDayOfMonth(){
        int currentDay = day;
        int currentNameOfDay = nameDay;
        int currentMonth = month;
        while(currentDay != getMonthDays(currentMonth)){
            currentDay = currentDay + 1;
            if((currentNameOfDay + 1) == 8){
                currentNameOfDay = 1;
            }
            else{
                currentNameOfDay = currentNameOfDay + 1;
            }
        }
        return currentNameOfDay;
    }


}
