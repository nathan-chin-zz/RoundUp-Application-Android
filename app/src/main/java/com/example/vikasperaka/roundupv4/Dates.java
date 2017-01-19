package com.example.vikasperaka.roundupv4;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Nathan on 1/9/2017.
 */

/**
 * The Dates class was created to be input into the RoundUp app to keep track of days
 * @author Nathan Chin
 * @date 1/9/2017
 */
public class Dates extends AppCompatActivity implements Parcelable {
    // Instance variables
    private String increment;
    private int startHour;
    private String startTime;
    private int endHour;
    private String endTime;
    private boolean isAM;
    private ArrayList<Hour> hours = new ArrayList<>();
    private boolean isSelected;
    private int day;
    private int fontColor;
    private int month;

    // Constants
    final int colorBlack = Color.BLACK;
    final int colorClear = Color.TRANSPARENT;
    final int colorDKGray = Color.DKGRAY;

    final int[] HOURS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    final String HALF = ":30";
    final String AM = "AM";
    final String PM = "PM";
    final int TIME_SHIFT = 12;

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(increment);
        dest.writeInt(startHour);
        dest.writeString(startTime);
        dest.writeInt(endHour);
        dest.writeString(endTime);
        dest.writeByte((byte) (isAM ? 1 : 0)); // if isAM == true, byte = 1
        dest.writeList(hours);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeInt(day);
        dest.writeInt(fontColor);
        dest.writeInt(month);
    }

    public static final Parcelable.Creator<Dates> CREATOR = new Parcelable.Creator<Dates>() {
        @Override
        public Dates createFromParcel(Parcel in) {
            return new Dates(in);
        }

        @Override
        public Dates[] newArray(int size) {
            return new Dates[size];
        }
    };

    public Dates(Parcel in){
        this.increment = in.readString();
        this.startHour = in.readInt();
        this.startTime = in.readString();
        this.endHour = in.readInt();
        this.endTime = in.readString();
        this.isAM = (in.readByte() != 0); // if isAM == true, byte = 1
        in.readList(hours, null);
        this.isSelected = (in.readByte() != 0);
        this.day = in.readInt();
        this.fontColor = in.readInt();
        this.month = in.readInt();
    }

    /**
     * The Dates constructor takes no parameters and assigns values to the instance variables. The View is made empty
     * automatically
     */
    public Dates(){
        increment = "60 min";
        isSelected = false;
        fontColor = colorDKGray;
        day = 0;
        makeEmptyView();
        this.month = month;
    }

    /**
     * The Dates constructor takes a month parameter so that a month can be assigned to a created Dates object. The View is made
     * empty automatically.
     * @param month the month to be assigned to the day (must be an integer with 1 = January,... 12 = December)
     */
    public Dates(int month){
        increment = "60 min";
        isSelected = false;
        fontColor = colorDKGray;
        day = 0;
        makeEmptyView();
        this.month = month;
    }

    /**
     * The Dates constructor takes a day and month parameter so a specific day can be created
     * @param day the number day of the month the date is given. If the day given is 0, the View is made empty
     * @param month the number month the date is given (must be an integer with 1 = January,... 12 = December)
     */
    public Dates(int day, int month){
        increment = "60 min";
        isSelected = false;
        this.day = day;
        fontColor = colorDKGray;
        if(this.day == 0){
            makeEmptyView();
        }
        this.month = month;
    }

    /**
     * The Dates constructor has four parameters so a really specific day can be created
     * @param month the month the day is to be placed in
     * @param startHour the starting hour of the date for the ArrayList of hours
     * @param endHour the ending hour of the date for the ArrayList of hours
     */
    public Dates(String increment, int month, int startHour, String startTime, int endHour, String endTime){
        this.increment = increment;
        this.startHour = startHour;
        this.startTime = startTime;
        this.endHour = endHour;
        this.endTime = endTime;
        fontColor = colorDKGray;
        if(this.day == 0){
            makeEmptyView();
        }
        this.month = month;
    }

    public void change60to30(){
        increment = "30 min";
    }

    public void change30to60(){
        increment = "60 min";
    }

    /**
     * The setDay method sets the day of the Dates object to a specific day
     * @param day
     */
    public void setDay(int day){
        this.day = day;
    }

    /**
     * The getDay method retrieves the day of the Dates object
     * @return day the day of the Dates object
     */
    public int getDay(){
        return day;
    }

    /**
     * The getFontColor method retrieves the color of the font assigned to the Dates object
     * @return fontColor the color variable assigned
     */
    public int getFontColor(){
        return fontColor;
    }

    /**
     * The getMonth method gets the month of the Dates object
     * @return month the month of the object
     */
    public int getMonth(){
        return month;
    }

    /**
     * The getDayAndMonth method returns the day and month of a Dates object in a String based on its values
     * @return temp the day and month of the Dates object in a String
     */
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

    /**
     * The isClicked method tells whether or not the Dates object was clicked on
     * @return isSelected a boolean variable telling if the object was clicked
     */
    public boolean isClicked(){
        return isSelected;
    }

    /**
     * The clickTheDay method is called when the View is actually clicked. It takes the value of the isSelected variable and
     * flips it
     */
    public void clickTheDay(){
        isSelected = !isSelected;
    }

    /**
     * The makeEmptyView method gives the appearance that the View is empty by changing the font color to transparent
     */
    public void makeEmptyView() {
        fontColor = colorClear;
    }

    public void setStartAndEndHours(int startHour, int endHour){
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public void setStartAndEndTimes(String startTime, String endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartHour(){
        return startHour;
    }

    public String getStartTime(){
        return startTime;
    }

    public int getEndHour(){
        return endHour;
    }

    public String getEndTime(){
        return endTime;
    }

    public void setStartHour(int startHour){
        this.startHour = startHour;
    }

    public void getStartTime(String startTime){
        this.startTime = startTime;
    }

    public void setEndHour(int endHour){
        this.endHour = endHour;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public boolean checkIfAM(){
        return isAM;
    }

    public boolean isValidSelection(){
        if(startTime.equals("PM") && endTime.equals("AM")){
            return false;
        }
        int temp_start = 0;
        int temp_end = 0;
        if(startTime.equals("AM")){
            if(startHour == 12){
                temp_start = 0;
            }
            else{
                temp_start = startHour;
            }
        }
        else if(startTime.equals("PM")){
            if(startHour != 12){
                switch(startHour)   {
                    case 1:     temp_start = 13;
                        break;
                    case 2:     temp_start = 14;
                        break;
                    case 3:     temp_start = 15;
                        break;
                    case 4:     temp_start = 16;
                        break;
                    case 5:     temp_start = 17;
                        break;
                    case 6:     temp_start = 18;
                        break;
                    case 7:     temp_start = 19;
                        break;
                    case 8:     temp_start = 20;
                        break;
                    case 9:     temp_start = 21;
                        break;
                    case 10:    temp_start = 22;
                        break;
                    case 11:    temp_start = 23;
                        break;
                }
            }
            else{
                temp_start = startHour;
            }
        }

        // Repeat for second set of data
        if(endTime.equals("AM")){
            if(endHour == 12){
                temp_end = 0;
            }
            else{
                temp_end = endHour;
            }
        }
        if(endTime.equals("PM")){
            if(endHour != 12){
                switch(endHour)   {
                    case 1:     temp_end = 13;
                        break;
                    case 2:     temp_end = 14;
                        break;
                    case 3:     temp_end = 15;
                        break;
                    case 4:     temp_end = 16;
                        break;
                    case 5:     temp_end = 17;
                        break;
                    case 6:     temp_end = 18;
                        break;
                    case 7:     temp_end = 19;
                        break;
                    case 8:     temp_end = 20;
                        break;
                    case 9:     temp_end = 21;
                        break;
                    case 10:    temp_end = 22;
                        break;
                    case 11:    temp_end = 23;
                        break;
                }
            }
            else{
                temp_end = endHour;
            }
        }

        if(temp_start >= temp_end){
            return false;
        }
        return true;
    }

    public void makeHoursList(int startHour, int endHour, String startTime, String endTime, String increment){
        // Check AM PM
        if(startTime.equals("AM") && endTime.equals("PM") && increment.equals("60 min")){
            if(startHour == 12){
                hours.add(new Hour(12, startTime));
                startHour = 1;
            }
            if(endHour == 12){
                for(double k = startHour; k < 12; k++){
                    hours.add(new Hour(k, startTime));
                }
                hours.add(new Hour(12, endTime));
            }
            else{
                for(double k = startHour; k < 12; k++){
                    hours.add(new Hour(k, startTime));
                }
                hours.add(new Hour(12, endTime));
                for(int l = 1; l <= endHour; l++){
                    hours.add(new Hour(l, endTime));
                }
            }

        }
        else if(startTime.equals("AM") && endTime.equals("PM") && increment.equals("30 min")){
            if(!isValidSelection()){
                hours.clear();
            }
            else{
                if(startHour == 12) {
                    hours.add(new Hour(12, startTime));
                    hours.add(new Hour(12.5, startTime));
                    startHour = 1;
                }
                for (double j = startHour; j <= 12; j += 0.5) {
                    hours.add(new Hour(j, startTime));
                }
                if(endHour != 12) {
                    hours.add(new Hour(12.5, startTime));
                    for(double j = 1; j <= endHour; j += 0.5){
                        hours.add(new Hour(j, startTime));
                    }
                }
            }
        }
            /*if(startHour == 12){
                hours.add(new Hour(12, startTime));
                hours.add(new Hour(12.5, startTime));
                startHour = 1;
            }
            if(endHour == 12){
                for(double k = startHour; k < 12; k += 0.5){
                    hours.add(new Hour(k, startTime));
                }
                hours.add(new Hour(12, endTime));
            }
            else{
                for(double k = startHour; k < 12; k += 0.5){
                    hours.add(new Hour(k, startTime));
                }
                hours.add(new Hour(12, endTime));
                for(int l = 1; l <= endHour; l += 0.5){
                    hours.add(new Hour(l, endTime));
                }
            }*/
            /*if(startHour == 12) {
                hours.add(new Hour(12, startTime));
                hours.add(new Hour(12.5, startTime));
                startHour = 1;
            }
            for(int k = startHour; k < 12; k += 0.5){
                hours.add(new Hour(k, startTime));
            }
            hours.add(new Hour(12, endTime));
            hours.add(new Hour(12.5, endTime));
            for(int l = 1; l <= endHour; l += 0.5){
                hours.add(new Hour(l, endTime));
            }
        }*/

        // Check AM AM
        else if(startTime.equals("AM") && endTime.equals("AM") && increment.equals("60 min")){
            if(!isValidSelection()){
                hours.clear();
            }
            else{
                if(startHour == 12){
                    hours.add(new Hour(12, startTime));
                    startHour = 1;
                }
                for(double k = startHour; k <= endHour; k++){
                    hours.add(new Hour(k, startTime));
                }
            }
        }
        else if(startTime.equals("AM") && endTime.equals("AM") && increment.equals("30 min")){
            if(!isValidSelection()){
                hours.clear();
            }
            else{
                if(startHour == 12){
                    hours.add(new Hour(12, startTime));
                    hours.add(new Hour(12.5, startTime));
                    for(double j = 1; j <= endHour; j += 0.5){
                        hours.add(new Hour(j, startTime));
                    }
                }
                else{
                    for(double j = startHour; j <= endHour; j += 0.5){
                        hours.add(new Hour(j, startTime));
                    }
                }
            }
        }
        // Check PM PM
        else if(startTime.equals("PM") && endTime.equals("PM") && increment.equals("60 min")){
            if(!isValidSelection()){
                hours.clear();
            }
            else{
                if(startHour == 12){
                    hours.add(new Hour(12, startTime));
                    startHour = 1;
                }
                for(double k = startHour; k <= endHour; k++){
                    hours.add(new Hour(k, startTime));
                }
            }
        }
        else if(startTime.equals("PM") && endTime.equals("PM") && increment.equals("30 min")){
            if(!isValidSelection()){
                hours.clear();
            }
            else{
                if(startHour == 12){
                    hours.add(new Hour(12, startTime));
                    hours.add(new Hour(12.5, startTime));
                    for(double j = 1; j <= endHour; j += 0.5){
                        hours.add(new Hour(j, startTime));
                    }
                }
                else{
                    for(double j = startHour; j <= endHour; j += 0.5){
                        hours.add(new Hour(j, startTime));
                    }
                }
            }
        }
    }

    public ArrayList<Hour> getHours(){
        return hours;
    }

}
