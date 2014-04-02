package com.jamie.sql;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private EditText sensorField, dateField, timeField, uvField, ambField;
    private Button mDataButton, mdisbutton;
    private TextView mOutText;

    public String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorField = (EditText)findViewById(R.id.editText1);
        dateField = (EditText)findViewById(R.id.editText2);
        timeField = (EditText)findViewById(R.id.editText);
        uvField = (EditText)findViewById(R.id.editText3);
        ambField = (EditText)findViewById(R.id.editText4);
        mDataButton = (Button)findViewById(R.id.button1);
        mOutText = (TextView)findViewById(R.id.textOut);
        mdisbutton = (Button)findViewById(R.id.disbutton);



        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void onClick(View v){
        if (v.getId()==R.id.button1){

            String sensor = sensorField.getText().toString();
            String date = dateField.getText().toString();
            String time = timeField.getText().toString();
            String uv = uvField.getText().toString();
            String amb = ambField.getText().toString();

            //new PHP_Class().execute(sensor, date, time, uv, amb);
            new PHP_query().execute(sensor);

        }
        if(v.getId()==R.id.disbutton){
            Toast.makeText(MainActivity.this, Global.result, Toast.LENGTH_LONG).show();
        }




    }




}
