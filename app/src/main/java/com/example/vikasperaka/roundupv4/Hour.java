package com.example.vikasperaka.roundupv4;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

/**
 * Created by Nathan on 1/18/2017.
 */

public class Hour extends AppCompatActivity implements Serializable, Comparable<Hour> {
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

    public String numToString(double time){
        String temp = "";
        if(time % 1 == 0){
            temp += (int)time;
        }
        else{
            temp += (int)time;
            temp += ":30";
        }
        return temp;
    }

    public double getHourFrom24(double mHour, String mTimeOfDay){
        double change = 0;
        if(mTimeOfDay.equals("AM")){
            if(mHour == 12){
                change = 0;
                if(mHour % 1 == 0){
                    change += 0.5;
                }
            }
            else{
                if(mHour % 1 == 0){
                    change = mHour + 0.5;
                }
                else{
                    change = mHour;
                }
            }
        }
        else if(mTimeOfDay.equals("PM")){
            if(mHour != 12){
                switch((int)mHour)   {
                    case 1:     change = 13;
                        break;
                    case 2:     change = 14;
                        break;
                    case 3:     change = 15;
                        break;
                    case 4:     change = 16;
                        break;
                    case 5:     change = 17;
                        break;
                    case 6:     change = 18;
                        break;
                    case 7:     change = 19;
                        break;
                    case 8:     change = 20;
                        break;
                    case 9:     change = 21;
                        break;
                    case 10:    change = 22;
                        break;
                    case 11:    change = 23;
                        break;
                }
            }
            else{
                change = mHour;
            }
            if(time % 1 == 0){
                change += 0.5;
            }
        }
        return change;
    }

    public double getHour(){
        return time;
    }

    public String getTimeOfDay(){
        return timeOfDay;
    }

    public int compareTo(Hour check){
        int temp;
        double compareHour = 0;
        double currentHour = 0;
        compareHour = getHourFrom24(check.getHour(), check.getTimeOfDay());
        currentHour = getHourFrom24(time, timeOfDay);
        if(compareHour >= currentHour){
            temp = -1;
        }
        else if(compareHour <= currentHour){
            temp = 1;
        }
        else{
            temp = 0;
        }
        return temp;
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
