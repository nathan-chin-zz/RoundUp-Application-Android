package com.example.vikasperaka.roundupv4;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

/**
 * Created by Nathan on 1/18/2017.
 */

public class Hour extends AppCompatActivity implements Serializable {
    private double time;
    private String timeOfDay;
    private boolean isSelected;
    private boolean isHalf;

    public Hour(){
        time = 0;
        timeOfDay = "";
        isSelected = false;
        isHalf = false;
    }

    public Hour(double time, String timeOfDay){
        this.time = time;
        this.timeOfDay = timeOfDay;
        isSelected = false;
        isHalf = false;
    }

    /**
     * The isClicked method tells whether or not the Dates object was clicked on
     * @return isSelected a boolean variable telling if the object was clicked
     */
    public boolean isClicked(){
        return isSelected;
    }

    public void clickTheHour(){
        isSelected = !isSelected;
    }

    public String intToString(double time){
        String temp = "";
        temp += time;
        if(isHalf){
            temp += ":30";
        }
        return temp;
    }

    public double getHour(){
        return time;
    }

    /**
     * The translateHour method returns the hour given in a String format along with time of day (AM or PM)
     * @param hour the hour of the day to translate into a String
     * @return time the String holding the hour and time of day
     */
    /*public String translateHour(int hour){
        String time = "";
        int arraySpot = 0;
        switch(hour) {
            case 1:   arraySpot = hour;
                time = time + arraySpot;
                if()
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
        return time;
    }*/
}
