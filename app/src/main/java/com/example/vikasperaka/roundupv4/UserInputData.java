package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserInputData extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ArrayList<ArrayList<Hour>> masterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton forward = (FloatingActionButton) findViewById(R.id.forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
            }
        });

        FloatingActionButton backward = (FloatingActionButton) findViewById(R.id.backward);
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
            }
        });

        final ArrayList<ArrayList<Hour>> endData = new ArrayList<>();
        ArrayList<Dates> temp = getIntent().getParcelableArrayListExtra("sendAgain");
        int numDays = getIntent().getExtras().getInt("numDays");
        long startHour = getIntent().getExtras().getLong("startHour");
        long endHour = getIntent().getExtras().getLong("endHour");
        String startTime = getIntent().getExtras().getString("startTime");
        String endTime = getIntent().getExtras().getString("endTime");
        String increment = getIntent().getExtras().getString("increment");
        for(int i = 0; i < numDays; i++){
            temp.get(i).setStartAndEndHours((int)startHour, (int)endHour);
            temp.get(i).setStartAndEndTimes(startTime, endTime);
            temp.get(i).makeHoursList((int)startHour, (int)endHour, startTime, endTime, increment);
            endData.add(temp.get(i).getHours());
        }

        Button finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                for(int i = 0; i < masterList.size(); i++){
                    endData.add(new ArrayList<Hour>());
                    for(int j = 0; j < masterList.get(i).size(); j++){
                        endData.get(i).add(masterList.get(i).get(j));
                    }
                }*/
                ArrayList<ArrayList<Boolean>> master = changeToBoolean(endData);
                Toast.makeText(getApplicationContext(), "" + master, Toast.LENGTH_SHORT).show();
            }
        });

        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    public void updateSelected(ArrayList<Hour> selectedHours, int position, int size){
        //ArrayList<ArrayList<Hour>> theData = new ArrayList<>();
        if(masterList.size() == 0){
            for(int i = 0; i < size; i++){
                masterList.add(new ArrayList<Hour>());
            }
        }
        masterList.set(position, selectedHours);
    }
    public ArrayList<ArrayList<Boolean>> changeToBoolean(ArrayList<ArrayList<Hour>> input){
        ArrayList<ArrayList<Boolean>> sendToDatabase = new ArrayList<>();
        int numDays = getIntent().getExtras().getInt("numDays");
        long startHour = getIntent().getExtras().getLong("startHour");
        long endHour = getIntent().getExtras().getLong("endHour");
        for(int g = 0; g < input.size(); g++){
            sendToDatabase.add(new ArrayList<Boolean>());
            for(int h = 0; h < input.get(g).size(); h++){
                if(input.get(g).get(h).isClicked()){
                    sendToDatabase.get(g).add(h, true);
                }
                else{
                    sendToDatabase.get(g).add(h, false);
                }
            }
        }
        return sendToDatabase;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_input_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private static final String ARG_SECTION_NUMBER = "section_number";
        private ArrayList<Hour> cumulative = new ArrayList<>();
        private ArrayList<String> temp3 = new ArrayList<String>();

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public void setSize(ArrayList<Hour> change){
            ArrayList<Dates> temp = getActivity().getIntent().getParcelableArrayListExtra("sendAgain");
            /*if(change.size() == 0){
                for(int i = 0; i < temp.size(); i++){
                    change.add(new ArrayList<Hour>());
                }
            }*/
            cumulative.add(new Hour());
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int[] posClicked;
            ArrayList<Dates> temp = getActivity().getIntent().getParcelableArrayListExtra("sendAgain");
            final ArrayList<Hour> temp2 = temp.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getHours();
            int curNum = getArguments().getInt(ARG_SECTION_NUMBER) - 1;
            //setSize(cumulative);
            cumulative.clear();
            /*for(int k = 0; k < temp2.size(); k++){
                if(temp2.get(k).isClicked()){
                    cumulative.add(temp2.get(k));
                }
            }*/

            View rootView = inflater.inflate(R.layout.fragment_user_input_data, container, false);
            final HourAdapter adapter = new HourAdapter(this.getActivity(), temp2);
            adapter.notifyDataSetChanged();
            GridView gridView = (GridView) rootView.findViewById(R.id.hours_list);
            gridView.setAdapter(adapter);
            cumulative = adapter.getTheSelected();
            gridView.setVerticalScrollbarPosition(View.SCROLLBAR_POSITION_RIGHT);
            if (temp2.get(1).getHour() % 1 == 0) {
                gridView.setNumColumns(1);
            } else {
                gridView.setNumColumns(2);
            }

            UserInputData myActivity = (UserInputData)getActivity();
            myActivity.updateSelected(cumulative, curNum, temp.size());

            final TextView textView = (TextView) rootView.findViewById(R.id.num_selected_hours);
            //updateText(temp2);
            for(int f = 0; f < cumulative.size(); f++){
                temp3.add("" + cumulative.get(f).getHour());
            }
            textView.setText("Hours chosen: " + temp3);
            textView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    updateText(temp2);
                    /*for(int k = 0; k < temp2.size(); k++){
                        temp3.clear();
                            if(temp2.get(k).isClicked()){
                                temp3.add(temp2.get(k).numToString(temp2.get(k).getHour()));
                            }
                    }*/
            /*for(int j = 0; j < temp2.size(); j++){
                if(temp2.get(j).isClicked()){
                    temp3.add(temp2.get(j).numToString(temp2.get(j).getHour()));
                }
            }*/
                    textView.setText("Hours chosen: " + temp3);
                }
            });


                //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                //return rootView;
            return rootView;
        }

        public void updateText(ArrayList<Hour> temp2){
            temp3.clear();
            for(int k = 0; k < temp2.size(); k++){
                if(temp2.get(k).isClicked()){
                    temp3.add(temp2.get(k).numToString(temp2.get(k).getHour()));
                }
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            Intent i = getIntent();
            ArrayList<Dates> temp = i.getParcelableArrayListExtra("sendAgain");
            return temp.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Intent i = getIntent();
            ArrayList<Dates> temp = i.getParcelableArrayListExtra("sendAgain");
            return temp.get(position).getDayAndMonth();
        }
    }
}
