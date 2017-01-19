package com.example.vikasperaka.roundupv4;

// Import statements

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class contains code for the screen after the calendar days have been selected.
 * @author created by Vikas Peraka but written by Nathan Chin
 * @date 1/09/2017
 */
public class CodeCreate extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    // Instance variables
    private Button join;
    private TextView codetxtView;
    private EditText number;
    private String code;
    private Spinner start_hour;
    private Spinner end_hour;
    private ArrayAdapter<CharSequence> spinner_hour1_adapter;
    private ArrayAdapter<CharSequence> spinner_hour2_adapter;

    private String duration;

    private long startHour;
    private long startTime;
    private long endHour;
    private long endTime;
    private long increment;

    private long storeStartHour;
    private String storeStartTime;
    private long storeEndHour;
    private String storeEndTime;
    private String storeIncrement;
    private ArrayList<Dates> store = new ArrayList<>();
    private ArrayList<Hour> test = new ArrayList<>();
    private ArrayList<Double> test2 = new ArrayList<>();

    final int[] hour = {12,1,2,3,4,5,6,7,8,9,10,11};
    final String[] time = {"AM", "PM"};
    final String[] timeIncrements = {"30 min", "60 min"};

    /**
     * The onCreate method describes what happens when the class is called.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_create);

        // Associate variables with Views that are defined in the activity_code_create.xml file
        join = (Button) findViewById(R.id.join);
        codetxtView = (TextView) findViewById(R.id.codeDisplay);

        // Receives data from intent and changes the TextView to display it
        code = getIntent().getExtras().get("code").toString().trim();
        codetxtView.setText(code);  //shows user the code for the created event

        // New RoundUpCalendar object created with intent data
        RoundUpCalendar r = (RoundUpCalendar) getIntent().getSerializableExtra("cal");

        // The TextView codetxtView gets an onClickListener to perform actions if clicked
        codetxtView.setOnClickListener(new View.OnClickListener() {
            /**
             * The onClick method performs actions when the TextView is clicked/selected
             * @param view the View that is being clicked/selected
             */
            @Override
            public void onClick(View view) {
                // When the View is clicked, the data is stored to the phone clipboard so it can be pasted
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Code", code);
                clipboard.setPrimaryClip(clip);
                // Makes a toast/message on the screen confirming that the code was copied
                Toast.makeText(CodeCreate.this, "Code has been copied", Toast.LENGTH_SHORT).show();
            }
        });

        Spinner start_hour = (Spinner) findViewById(R.id.spinner_hour1);
        start_hour.setOnItemSelectedListener(this);
        SpinnerIntHour1Adapter custom1 = new SpinnerIntHour1Adapter(CodeCreate.this, hour);
        start_hour.setAdapter(custom1);
        start_hour.setSelection(9);
        startHour = custom1.getItemId(1);

        Spinner start_time = (Spinner) findViewById(R.id.spinner_time1);
        start_time.setOnItemSelectedListener(this);
        SpinnerStrTime1Adapter custom2 = new SpinnerStrTime1Adapter(CodeCreate.this, time);
        start_time.setAdapter(custom2);
        start_time.setSelection(0);
        startTime = custom2.getItemId(2);

        Spinner end_hour = (Spinner) findViewById(R.id.spinner_hour2);
        end_hour.setOnItemSelectedListener(this);
        SpinnerIntHour2Adapter custom3 = new SpinnerIntHour2Adapter(CodeCreate.this, hour);
        end_hour.setAdapter(custom3);
        end_hour.setSelection(6);
        endHour = custom3.getItemId(3);

        Spinner end_time = (Spinner) findViewById(R.id.spinner_time2);
        end_time.setOnItemSelectedListener(this);
        SpinnerStrTime2Adapter custom4 = new SpinnerStrTime2Adapter(CodeCreate.this, time);
        end_time.setAdapter(custom4);
        end_time.setSelection(1);
        endTime = custom4.getItemId(4);

        Spinner pick_increments = (Spinner) findViewById(R.id.choose_increments);
        pick_increments.setOnItemSelectedListener(this);
        SpinnerIncrementAdapter custom5 = new SpinnerIncrementAdapter(CodeCreate.this, timeIncrements);
        pick_increments.setAdapter(custom5);
        pick_increments.setSelection(1);
        increment = custom5.getItemId(5);

        /*start_hour = (Spinner) findViewById(R.id.spinner_hour1);
        spinner_hour1_adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, temp);
        spinner_hour1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        start_hour.setAdapter(spinner_hour1_adapter);

        String hi = start_hour.getSelectedItem().toString();
        codetxtView.setText(hi);

        // (UNFINISHED) Displays the values selected by the Start Hour spinner number
        start_hour = (Spinner) findViewById(R.id.spinner_hour1);
        spinner_hour1_adapter = ArrayAdapter.createFromResource(this, R.array.hour_first, android.R.layout.simple_spinner_item);
        spinner_hour1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        start_hour.setAdapter(spinner_hour1_adapter);
        start_hour.setOnItemSelectedListener(this);

        // (UNFINISHED) Displays the values selected by the End Hour spinner number
        end_hour = (Spinner) findViewById(R.id.spinner_hour2);
        spinner_hour2_adapter = ArrayAdapter.createFromResource(this, R.array.hour_second, android.R.layout.simple_spinner_item);
        spinner_hour2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        end_hour.setAdapter(spinner_hour2_adapter);
        end_hour.setOnItemSelectedListener(this);*/

        // Sets an onClickListener on the join button so actions can be performed when clicked
        join.setOnClickListener(new View.OnClickListener() {
            /**
             * The method onClick performs actions when clicked
             * @param view the View that is clicked
             */
            @Override
            public void onClick(View view) {
                // Creates a popup message warning users to finalize the RoundUp details
                AlertDialog.Builder message = new AlertDialog.Builder(CodeCreate.this);
                message.setMessage("Once the RoundUp has been created, the settings CANNOT be changed")
                        .setCancelable(false) // The popup message can only be dismissed by the two buttons
                        .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                            /**
                             * When this text is clicked, it takes the user to a new screen by sending an intent to a new
                             * activity
                             * @param dialog
                             * @param which
                             */
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText inputDur = (EditText) findViewById(R.id.duration);
                                duration = inputDur.getText().toString();
                                if(duration.length() != 0){
                                    Intent i = new Intent(getApplicationContext(), JoinEvent.class);
                                    i.putParcelableArrayListExtra("sendAgain", store);
                                    startActivity(i);
                                }
                                if(duration.length() == 0){
                                    test.clear();
                                    test2.clear();
                                    store = getIntent().getParcelableArrayListExtra("list_days");
                                    for(int i = 0; i < store.size(); i++){
                                        store.get(i).setStartAndEndHours((int)storeStartHour, (int)storeEndHour);
                                        store.get(i).setStartAndEndTimes(storeStartTime, storeEndTime);
                                        store.get(i).makeHoursList((int)storeStartHour, (int)storeEndHour, storeStartTime, storeEndTime, storeIncrement);
                                        test = store.get(i).getHours();
                                    }
                                    TextView temp = (TextView) findViewById(R.id.list);
                                    for(int k = 0; k < test.size(); k++){
                                        test2.add(test.get(k).getHour());
                                    }
                                    if(test2.size() == 0){
                                        temp.setText("Invalid time range");
                                    }
                                    else{
                                        temp.setText("" + test2);
                                    }
                                    Toast.makeText(getApplicationContext(), "Enter an approximate duration so those invited " +
                                            "can plan accordingly", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Hold on", new DialogInterface.OnClickListener() {
                            /**
                             * When this text is clicked, it closes the popup message and allows the users to resume making changes
                             * @param dialog
                             * @param which
                             */
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = message.create();
                alert.setTitle("WARNING"); // The title of the popup message is set
                alert.show();
            }
        });
    }

    /**
     * (UNFINISHED) The onItemSelected method performs actions when a dropdown item from the Spinner is selected
     * @param parent
     * @param view
     * @param pos
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        String display = "";
        long check = parent.getAdapter().getItemId(1);
        if(check == startHour){
            storeStartHour = hour[pos];
            display = "Start Hour: " + storeStartHour;
        }
        else if(check == startTime){
            storeStartTime = time[pos];
            display = "Start Time: " + storeStartTime;
        }
        else if(check == endHour){
            storeEndHour = hour[pos];
            display = "End Hour: " + storeEndHour;
        }
        else if(check == endTime){
            storeEndTime = time[pos];
            display = "End Time: " + storeEndTime;
        }
        else if(check == increment){
            storeIncrement = timeIncrements[pos];
            display = "Increments: " + storeIncrement;
        }
        //Toast.makeText(getApplicationContext(), display, Toast.LENGTH_SHORT).show();
    }

    /**
     * (UNFINISHED) The onNothingSelected method performs actions with the default Spinner values
     * @param parent
     */
    public void onNothingSelected(AdapterView<?> parent){
        String store = parent.getSelectedItem().toString();
        long check = parent.getAdapter().getItemId(1);
        if(check == startHour){
            storeStartHour = Integer.parseInt(store);
        }
        else if(check == startTime){
            storeStartTime = store;
        }
        else if(check == endHour){
            storeEndHour = Integer.parseInt(store);
        }
        else if(check == endTime){
            storeEndTime = store;
        }
        else if(check == increment){
            storeIncrement = store;
        }
        //Toast.makeText(this, store, Toast.LENGTH_SHORT).show();
    }

    /**
     * (UNFINISHED)
     * @author Vikas
     */
    public void sendTextMessageReminder() {
        String phoneNumber = number.getText().toString();
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(phoneNumber, null, code, null, null);
    }
}
