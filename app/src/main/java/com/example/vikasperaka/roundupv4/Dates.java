package com.example.vikasperaka.roundupv4;

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

    final int[] HOURS = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
    final String HALF = ":30";
    final String AM = "AM";
    final String PM = "PM";
    final int TIME_SHIFT = 12;

    public Dates(){
        isSelected = false;
    }

    public Dates(int day){
        isSelected = false;
        this.day = day;
    }

    public Dates(int startHour, int endHour, ArrayList<String> hours){
        this.startHour = startHour;
        this.endHour = endHour;
        this.hours = hours;
    }

    public void setDay(int day){
        this.day = day;
    }

    public int getDay(){
        return day;
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

}
