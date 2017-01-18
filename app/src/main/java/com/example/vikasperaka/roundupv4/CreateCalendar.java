package com.example.vikasperaka.roundupv4;

// Import statements
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The CreateCalendar is the code for the screen displaying a calendar of months for the user to select days for his/her RoundUp
 * event
 * @author created by Vikas Peraka and written by Nathan Chin
 * @author Vikas Peraka for all Firebase code
 * @date 1/09/2017
 */
public class CreateCalendar extends AppCompatActivity {

    // Instance variables
    private Button next;
    private DatabaseReference root;
    private String tempKey;
    private ArrayList<ArrayList<Integer>> test;

    int numDays = 0;
    ArrayList<Dates> list;
    boolean hasUpdated = false;

    /**
     * The onCreate method describes what happens when the class is called.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_calendar);

        /*
         *
         * COMMENT YOUR OWN CODE VIKAS
         *
         *
         */
        next = (Button)findViewById(R.id.nextButton4);
        root = FirebaseDatabase.getInstance().getReference();

        //Add calendar data here
        ArrayList<Integer> inner = new ArrayList<>();
        inner.add(0);
        inner.add(0);
        inner.add(0);
        test = new ArrayList<>();
        test.add(inner);


        final String name = getIntent().getExtras().get("name").toString();
        final String description = getIntent().getExtras().get("description").toString();
        final RoundUpCalendar roundUpCalendar =  new RoundUpCalendar(name, description, test);
        final String CODE = roundUpCalendar.getUniqueCode();

        //Adds unique event code to database
        Map<String, Object> map = new HashMap<String, Object>();
        tempKey = CODE;
        root.updateChildren(map);

        //figure out how to clear database to prevent data overload...
        //Adds event name, description, and original calendar to event base on given code
        DatabaseReference subRoot = root.child(tempKey);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", name);
        map2.put("description", description);
        map2.put("orgCal", test);
        subRoot.updateChildren(map2);

        setTitle("Event: " + name);

        // Retrieving more data from the intent
        Intent intent = getIntent();
        String description_edit = intent.getStringExtra("description");
        TextView message = (TextView) findViewById(R.id.write);
        message.setText(description_edit);

        // Creating the Month objects to be displayed
        Month first = new Month();
        ArrayList<Dates> dates = first.getDays();
        Month second = new Month(1);
        ArrayList<Dates> dates2 = second.getDays();
        Month third = new Month(2);
        ArrayList<Dates> dates3 = third.getDays();

        // Set the TextView to display which 3 months are shown on the screen
        TextView months = (TextView) findViewById(R.id.display_months);
        months.setText(first.getMonthName() + " - " + second.getMonthName() + " - " + third.getMonthName());

        // Puts all of the months' days into one ArrayList<>
        for(int k = 0; k < dates2.size(); k++){
            dates.add(dates2.get(k));
        }
        for(int j = 0; j < dates3.size(); j++){
            dates.add(dates3.get(j));
        }

        // Copy made because a final variable was required
        final ArrayList<Dates> copy = dates;

        // CalendarAdapter is set to the ArrayList of dates to be displayed in a Grid format
        final CalendarAdapter adapter = new CalendarAdapter(this, dates);
        final GridView gridView = (GridView) findViewById(R.id.calendar);
        gridView.setAdapter(adapter);

        // The update button is assigned a variable in code
        Button update = (Button) findViewById(R.id.update);
        // An onClickListener is put on the update button so actions can be performed when clicked
        update.setOnClickListener(new View.OnClickListener(){
            /**
             * The onClick method performs actions when clicked
             * @param view the View that is clicked
             */
            @Override
            public void onClick(View view) {
                ArrayList<String> selected = new ArrayList<>();
                numDays = 0;
                selected.clear();
                TextView num = (TextView) findViewById(R.id.num_selected_days);
                TextView sel = (TextView) findViewById(R.id.selected_days);
                // Traverse the ArrayList of Dates objects, and if a Dates object is selected, the number of selected days is
                // updated and the Dates objects selected are added to another ArrayList to be displayed on screen
                for(int k = 0; k < copy.size(); k++){
                    if(copy.get(k).isClicked() == true){
                        numDays = numDays + 1;
                        selected.add(copy.get(k).getDayAndMonth());
                    }
                }
                num.setText("" + numDays);
                if(selected.size() == 0){
                    sel.setText("none");
                }
                else{
                    sel.setText("" + selected);
                }
                hasUpdated = true;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasUpdated){
                    Intent i = new Intent(getApplicationContext(), CodeCreate.class);
                    i.putExtra("number_days", numDays);
                    i.putExtra("code", CODE);

                    i.putExtra("cal", roundUpCalendar);
                    //i.putExtra("name", name);
                    //i.putExtra("description", description);
                    startActivity(i);
                }
            }
        });
    }
}
