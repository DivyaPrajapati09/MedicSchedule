package com.example.asus.medic_schedule;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by ASUS on 3/14/2015.
 */
public class BloodSugar extends ActionBarActivity {

    EditText sug_con,tags;
    Spinner measured_time;
    TextView time,date;
    Button ent_btn;
    String dat,sug,tim,tag;
    String ms_tm;

    SQLiteDatabase db=null;

    private final String DB_NAME="MEDICDB";
    private final String TABLE_NAME="BloodSugar_DB";


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodsugar);

        time=(TextView)findViewById(R.id.time);
        date=(TextView)findViewById(R.id.date);
        sug_con=(EditText)findViewById(R.id.sug_con);
        tags=(EditText)findViewById(R.id.tags);
        measured_time=(Spinner)findViewById(R.id.measured_time);
        ent_btn=(Button)findViewById(R.id.ent_btn);

        try{
            db=this.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
           // db.execSQL("DROP TABLE BloodSugar_DB");
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( Measured VARCHAR ,date VARCHAR, time VARCHAR,concentration INTEGER,tags VARCHAR );");
            //Log.e("Blood Pressure", "Time entered");
        }   catch (SQLiteException se){
            Log.e("Blood Sugar", "Could not create or Open the database" +se);

        }

        measured_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

                ms_tm = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment tpf=new TimePickerFragment();
                tpf.show(getFragmentManager(),"timepicker");
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment tpf=new DatePickerFragment();
                tpf.show(getFragmentManager(),"datepicker");
            }
        });

        ent_btn.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view){
                tim=time.getText().toString();
                if(TextUtils.isEmpty(tim)){
                    time.setError("Field cannot be empty");
                }

                dat=date.getText().toString();
                if(TextUtils.isEmpty(dat.toString())){
                    date.setError("Field cannot be empty");
                }

                sug=sug_con.getText().toString();
                if(TextUtils.isEmpty(sug.toString())){
                    sug_con.setError("Field cannot be empty");
                }

                tag=tags.getText().toString();
                if(TextUtils.isEmpty(tag.toString())){
                    tags.setError("Field cannot be empty");
                }

                try
                {
                    db.execSQL("INSERT INTO " + TABLE_NAME + " (Measured,date,time,concentration,tags) Values ('" + ms_tm + "','" + dat + " ','"+ tim +"',' "+ sug +"','"+tag+"');");
                    Log.e("Blood Sugar","Time entered: "+ tim);
                    Log.e("Blood Sugar","Date entered: "+ dat);
                    Log.e("Blood Sugar","Sugar level entered: "+ sug);
                    Log.e("Blood Sugar","measured time entered : "+ms_tm);
                    //Log.e("database","Time entered"+tim);
                    //Log.e("database","time entered:",datetime(%d-%M-%y));
                }
                catch (SQLiteException se)
                {
                    Log.e("database", "Error in insert query"+se);
                }

                Intent in =new Intent(getBaseContext(),DataList_su.class);
                startActivity(in);
                finish();
            }
        });
    }

    public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            final Calendar c=Calendar.getInstance();
            int hour=c.get(Calendar.HOUR_OF_DAY);
            int min=c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(),this,hour,min,false);
        }

        @Override
        public void onTimeSet(TimePicker view,int hour,int min)
        {
            populateSetTime(hour,min);
        }

        public void populateSetTime(int hour,int min)
        {
            time.setText(hour+":"+min);
        }

    }

    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            final Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),this,year,month,day);
        }

        @Override
        public void onDateSet(DatePicker view,int year,int monthOfYear,int dayOfMonth)
        {
            populateSetDate(year,monthOfYear+1,dayOfMonth);
        }

        public void populateSetDate(int year, int monthOfYear, int dayOfMonth)
        {
            date.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
        }
    }

}
