package com.example.vikasperaka.roundupv4;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Nathan on 1/9/2017.
 */

public class Dates extends AppCompatActivity{
    private int startHour;
    private int endHour;
    private boolean isAM;
    private ArrayList<String> hours;
    private boolean isSelected;
    private int day;
    private int fontColor;
    private int month;

    final int colorBlack = Color.BLACK;
    final int colorClear = Color.TRANSPARENT;
    final int colorDKGray = Color.DKGRAY;

    final int[] HOURS = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
    final String HALF = ":30";
    final String AM = "AM";
    final String PM = "PM";
    final int TIME_SHIFT = 12;

    public Dates(){
        isSelected = false;
        fontColor = colorDKGray;
        day = 0;
        if(day == 0){
            makeEmptyView();
        }
        this.month = month;
    }

    public Dates(int month){
        isSelected = false;
        fontColor = colorDKGray;
        day = 0;
        if(day == 0){
            makeEmptyView();
        }
        this.month = month;
    }

    public Dates(int day, int month){
        isSelected = false;
        this.day = day;
        fontColor = colorDKGray;
        if(this.day == 0){
            makeEmptyView();
        }
        this.month = month;
    }

    public Dates(int month, int startHour, int endHour, ArrayList<String> hours){
        this.startHour = startHour;
        this.endHour = endHour;
        this.hours = hours;
        fontColor = colorDKGray;
        if(this.day == 0){
            makeEmptyView();
        }
        this.month = month;
    }

    public void setDay(int day){
        this.day = day;
    }

    public int getDay(){
        return day;
    }

    public int getFontColor(){
        return fontColor;
    }

    public int getMonth(){
        return month;
    }

    public String getDayAndMonth(){
        String temp = "";
        String nameMonth = "";
        switch(month)   {
            case 1:    nameMonth = "Jan";
                break;
            case 2:    nameMonth = "Feb";
                break;
            case 3:    nameMonth = "Mar";
                break;
            case 4:    nameMonth = "Apr";
                break;
            case 5:    nameMonth = "May";
                break;
            case 6:    nameMonth = "Jun";
                break;
            case 7:    nameMonth = "Jul";
                break;
            case 8:    nameMonth = "Aug";
                break;
            case 9:    nameMonth = "Sep";
                break;
            case 10:   nameMonth = "Oct";
                break;
            case 11:   nameMonth = "Nov";
                break;
            case 12:   nameMonth = "Dec";
                break;
        }
        temp = nameMonth + " " + day;
        return temp;
    }

    public boolean isClicked(){
        return isSelected;
    }

    public void clickTheDay(){
        isSelected = !isSelected;
    }

    public String translateHour(int hour){
        String time = "";
        int arraySpot = 0;
        switch(hour) {
            case 0:   arraySpot = TIME_SHIFT;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 1:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 2:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 3:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 4:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 5:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 6:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 7:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 8:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 9:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 10:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 11:   arraySpot = hour;
                time = time + arraySpot + AM;
                isAM = true;
                break;
            case 12:   arraySpot = hour;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 13:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 14:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 15:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 16:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 17:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 18:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 19:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 20:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 21:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 22:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
            case 23:   arraySpot = hour - TIME_SHIFT;
                time = time + arraySpot + PM;
                isAM = false;
                break;
        }
        return time;
    }

    public void makeEmptyView() {
        fontColor = colorClear;
    }

}
