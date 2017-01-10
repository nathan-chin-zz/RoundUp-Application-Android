package com.example.android.roundup;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

/**
 * Created by Nathan on 1/9/2017.
 */

public class RoundUpCalendar extends AppCompatActivity implements Serializable{
    private String eventName;
    private String eventDescription;
    private String uniqueCode;

    final char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','q','r','s','t','u','v','w','x','y',
            'z','1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
            'R','S','T','U','V','W','X','Y','Z'};
    final int CODE_LENGTH = 5;

    public RoundUpCalendar(){
        uniqueCode = generateCode();
    }

    public RoundUpCalendar(String eventName, String eventDescription){
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        uniqueCode = generateCode();
    }

    public String generateCode(){
        int max = ALPHABET.length;
        String code = "";
        for(int i = 0; i < CODE_LENGTH; i++){
            int random = (int)(Math.random()*max + 1);
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
}
