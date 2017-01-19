package com.example.vikasperaka.roundupv4;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vikasperaka on 1/9/17 and edited by Nathan Chin on 1/14/17.
 */

public class RoundUpCalendar extends AppCompatActivity implements Serializable{

    private String eventName;
    private String eventDescription;
    private String uniqueCode;
    private ArrayList<ArrayList<Integer>> test;
    private int numClicked;

    final char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
            'r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0','A',
            'B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W',
            'X','Y','Z'};
    final int CODE_LENGTH = 5;

    public RoundUpCalendar(){
        uniqueCode = generateCode();
    }

    public RoundUpCalendar(String eventName, String eventDescription, ArrayList<ArrayList<Integer>> test){
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        uniqueCode = generateCode();
        this.test = test;
        numClicked = 0;
    }

    public String generateCode(){
        int max = ALPHABET.length - 1;
        String code = "";
        for (int i = 0; i < CODE_LENGTH; i++) {
            int random = (int) (Math.random() * max) + 1;
            code += ALPHABET[random];
        }
        return code;
    }

    public String getEventName(){
        return eventName;
    }

    public String getEventDescription(){
        return eventDescription;
    }

    public String getUniqueCode(){
        return uniqueCode;
    }

    public ArrayList<ArrayList<Integer>> getCal(){ return test; }

    public int getNumClicked(){
        return numClicked;
    }

    public void increment(){
        numClicked++;
    }
}

