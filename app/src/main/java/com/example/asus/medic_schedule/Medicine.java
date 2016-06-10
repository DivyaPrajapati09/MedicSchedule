package com.example.asus.medic_schedule;

/**
 * Created by ASUS on 1/18/2015.
 */


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ASUS on 1/17/2015.
 */
public class Medicine extends ActionBarActivity {

    EditText m_name, duration, days, dosage, qun;
    Spinner reminder, medicine_type, d_name, p_name;
    Button med_add, alarm;
    TextView start_date, reminder_time;
    String name, rem_time, dur, st, d, dos, re, mt, doc_nam, pat_nam, quantity;

    SQLiteDatabase db = null;

    final static int RQS_1 = 1;
    private final String DB_NAME = "MEDICDB";
    private final String TABLE_NAME = "Medic_DB";
    private int notificatonId = 100;

    ArrayList<String> d_results = new ArrayList<String>();
    ArrayList<String> p_results = new ArrayList<String>();

    String time = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine);


        m_name = (EditText) findViewById(R.id.m_name);
        reminder = (Spinner) findViewById(R.id.reminder);
        duration = (EditText) findViewById(R.id.duration);
        days = (EditText) findViewById(R.id.days);
        dosage = (EditText) findViewById(R.id.dosage);
        qun = (EditText) findViewById(R.id.qun);

        start_date = (TextView) findViewById(R.id.start_date);
        reminder_time = (TextView) findViewById(R.id.reminder_time);

        medicine_type = (Spinner) findViewById(R.id.medicine_type);
        d_name = (Spinner) findViewById(R.id.d_name);
        p_name = (Spinner) findViewById(R.id.p_name);
        med_add = (Button) findViewById(R.id.med_add);

        try {
            db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
            //db.execSQL("DROP TABLE Medic_DB");
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (m_id INTEGER PRIMARY KEY AUTOINCREMENT ,m_name VARCHAR NOT NULL,reminder INTEGER NOT NULL,reminder_time VARCHAR NOT NULL,quantity VARCHAR NOT NULL,duration VARCHAR NOT NULL,start_date INTEGER NOT NULL,Days INTEGER NOT NULL,medicine_type VARCHAR NOT NULL,Dosage INTEGER NOT NULL,d_name VARCHAR NOT NULL,p_name VARCHAR NOT NULL);");
        } catch (SQLiteException se) {
            Log.e("Medicine", "could not create or open database: " + se);
        }
        //  medicine_type.setOnItemSelectedListener(this);
        loadSpinnerDataDoctor();
        loadSpinnerDataPatient();

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment dpf = new DatePickerFragment();
                dpf.show(getFragmentManager(), "datepicker");
            }
        });

        reminder_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment tpf = new TimePickerFragment();
                tpf.show(getFragmentManager(), "timepicker");
            }
        });


        reminder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

                re = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        medicine_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                mt = parent.getItemAtPosition(pos).toString();
                //Toast.makeText(getBaseContext(),String.valueOf(pos),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        d_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

                doc_nam = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        p_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

                pat_nam = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        med_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = m_name.getText().toString();
                if(TextUtils.isEmpty(name)){
                    m_name.setError("Field cannot be empty");
                }

                rem_time = reminder_time.getText().toString();
                if(TextUtils.isEmpty(rem_time)){
                    reminder_time.setError("Field cannot be empty");
                }

                dur = duration.getText().toString();
                if(TextUtils.isEmpty(dur)){
                    duration.setError("Field cannot be empty");
                }

                st = start_date.getText().toString();
                if(TextUtils.isEmpty(st)){
                    start_date.setError("Field cannot be empty");
                }

                d = days.getText().toString();
                if(TextUtils.isEmpty(d)){
                    days.setError("Field cannot be empty");
                }

                dos = dosage.getText().toString();
                if(TextUtils.isEmpty(dos)){
                    dosage.setError("Field cannot be empty");
                }

                quantity = qun.getText().toString();
                if(TextUtils.isEmpty(quantity)){
                    qun.setError("Field cannot be empty");
                }

                if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(rem_time) ||!TextUtils.isEmpty(dur) ||!TextUtils.isEmpty(st)|| !TextUtils.isEmpty(d) || !TextUtils.isEmpty(dos) || !TextUtils.isEmpty(quantity)) {

                    try {
                        db.execSQL("INSERT INTO " + TABLE_NAME + "(m_name,reminder,reminder_time,quantity,duration,start_date,Days,medicine_type,Dosage,d_name,p_name) VALUES ('" + name + "','" + re + "','" + rem_time + "','" + quantity + "','" + dur + "','" + st + "','" + d + "','" + mt + "','" + dos + "','" + doc_nam + "','" + pat_nam + "');");
                        Log.e("Medicine", "m_name inserted: " + name);
                        Log.e("Medicine", "reminder inserted: " + re);
                        Log.e("Medicine", "reminder_time inserted: " + rem_time);
                        Log.e("Medicine", "duration inserted: " + dur);
                        Log.e("Medicine", "start_date inserted: " + st);
                        Log.e("Medicine", "Days inserted: " + d);
                        Log.e("Medicine", "medicine_type inserted: " + mt);
                        Log.e("Medicine", "Dosage inserted: " + dos);
                        Log.e("Medicine", "d_name inserted: " + d_name);
                        Log.e("Medicine", "p_name inserted: " + p_name);

                        // startNotification();
                        Toast.makeText(getBaseContext(), "Alarm set!!!", Toast.LENGTH_LONG).show();

                    } catch (SQLiteException se) {
                        Log.e("Medicine", "Error in insert query" + se);
                    }
                }else
                {
                    Toast.makeText(getBaseContext(),"Enter valid Fields",Toast.LENGTH_LONG).show();
                     }

                Intent in = new Intent(getBaseContext(), Datalist_m.class);
                startActivity(in);
                finish();
            }
        });

    }

    private void loadSpinnerDataPatient() {
        try {
            Cursor d = db.rawQuery("SELECT p_name FROM Patient_DB", null);
            if (d != null) {
                if (d.moveToFirst()) {
                    do {
                        String pa_name = d.getString(d.getColumnIndex("p_name"));

                        p_results.add(pa_name);
                        Log.e("SpinnerPatient", "results: " + p_results);
                    } while (d.moveToNext());
                }
            }
            ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, p_results);
            p_name.setAdapter(aa);
        } catch (SQLiteException se) {
            Log.e("Medicine", "could not fetch the value" + se);
        }
    }

    private void loadSpinnerDataDoctor() {
        try {
            Cursor e = db.rawQuery("SELECT d_name FROM Doc_DB", null);
            if (e != null) {
                if (e.moveToFirst()) {
                    do {
                        String do_name = e.getString(e.getColumnIndex("d_name"));

                        d_results.add(do_name);
                    } while (e.moveToNext());
                }
            }
            ArrayAdapter<String> bb = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, d_results);
            d_name.setAdapter(bb);
        } catch (SQLiteException se) {
            Log.e("Medicine", "could not fetch the value");
        }
    }

    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            // c.set(selDate);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);

        }


                @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            populateSetDate(year, monthOfYear + 1, dayOfMonth);
        }

        public void populateSetDate(int year, int monthOfYear, int dayOfMonth) {
            //  start_date = (TextView)findViewById(R.id.start_date);
            start_date.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
        }
    }

    public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            // c.set(selDate);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, min, false);
        }

        @Override
        public void onTimeSet(TimePicker view, int hour, int min) {
            populateSetTime(hour, min);
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hour);
            calSet.set(Calendar.MINUTE, min);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            Log.e("calset", "calSet : " + calSet);
            Log.e("calNow", "calNow : " + calNow);

            if (calSet.compareTo(calNow) <= 0) {
                //Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
            } else {
//                Integer da=Integer.parseInt("days");
  //              calSet.add(Calendar.DATE,da);
                setAlarm(calSet);


            }
        }
    }

    public void populateSetTime(int hour, int min) {

        reminder_time.setText(hour + ":" + min);
    }

    private void setAlarm(Calendar targetCal) {

//
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),24*3600*1000,pendingIntent);

    }

   /* private void setAlarmnew(Calendar targetCal) {


        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),24*3600*1000,pendingIntent);

    }*/
}