package com.example.vikasperaka.roundupv4;

import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Nathan on 1/11/2017.
 */

public class Month extends AppCompatActivity{
    private int endDay;
    private int year;
    private int month;
    private int day;
    private int nameDay;
    private boolean leapYear;
    private int numDays;
    private ArrayList<Dates> daysOfMonth;

    final int DAYS_31 = 31;
    final int DAYS_30 = 30;
    final int DAYS_28 = 28;
    final String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};


    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat theYear = new SimpleDateFormat("y", Locale.US);
    String strYear = theYear.format(calendar.getTime());

    SimpleDateFormat theMonth = new SimpleDateFormat("M", Locale.US);
    String strMonth = theMonth.format(calendar.getTime());

    SimpleDateFormat theDay = new SimpleDateFormat("d", Locale.US);
    String strDay = theDay.format(calendar.getTime());

    SimpleDateFormat dayName = new SimpleDateFormat("u", Locale.US); // 1=Monday, 2=Tuesday...7=Sunday
    String strDayName = dayName.format(calendar.getTime());

    public Month(){
        year = Integer.parseInt(strYear);
        month = Integer.parseInt(strMonth);
        day = Integer.parseInt(strDay);
        nameDay = Integer.parseInt(strDayName);
        numDays = getMonthDays(month);
        daysOfMonth = new ArrayList<>();
        int temp1 = findFirstDayOfMonth();
        if(temp1 != 7){
            for(int j = 0; j < temp1; j++ ){
                daysOfMonth.add(new Dates(month));
            }
        }
        for(int k = 1; k <= numDays; k++){
            daysOfMonth.add(new Dates(k, month));
        }
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

    public Month(int numAfter){
        year = Integer.parseInt(strYear);
        month = Integer.parseInt(strMonth);
        day = Integer.parseInt(strDay);
        nameDay = Integer.parseInt(strDayName);
        numDays = getMonthDays(month);
        daysOfMonth = new ArrayList<>();

        if(numAfter == 1){
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
        else if(numAfter == 2){
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
        int temp1 = nameDay;
        if(temp1 != 7){
            for(int j = 0; j < temp1; j++ ){
                daysOfMonth.add(new Dates(month));
            }
        }
        for(int k = 1; k <= numDays; k++){
            daysOfMonth.add(new Dates(k, month));
        }
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

    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public ArrayList<Dates> getDays(){
        return daysOfMonth;
    }

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

    public boolean isLeapYear(int currentYear){
        if((currentYear % 4) == 0){
            leapYear = true;
        }
        return leapYear;
    }

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
