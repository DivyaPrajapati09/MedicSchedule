package com.example.asus.medic_schedule;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


/**
 * Created by ASUS on 1/31/2015.
 */
public class BloodPressure extends ActionBarActivity{

    EditText systol,dystol,pulse;
    Button btn_ent;

    Integer sys,dys,pul;

    SQLiteDatabase db=null;

    private Toolbar toolbar;

    private final String DB_NAME="MEDICDB";
    private final String TABLE_NAME="BldPS_DB";


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodpressure);

        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);


        systol=(EditText)findViewById(R.id.et_add_systol);
        dystol=(EditText)findViewById(R.id.et_add_dystol);
        pulse=(EditText)findViewById(R.id.et_add_pulse);

        btn_ent=(Button)findViewById(R.id.btn_ent);

        try
        {
            db=this.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
           // db.execSQL("DROP TABLE BldPS_DB");
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( systol INTEGER  ,dystol INTEGER, pulse INTEGER, date VARCHAR, time VARCHAR);");
        }
        catch (SQLiteException se)
        {
            Log.e("database", "Could not create or Open the database" +se);
        }

       btn_ent.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view){

                sys=Integer.parseInt(systol.getText().toString());
                if(TextUtils.isEmpty(sys.toString()))
                {
                    systol.setError("Field cannot be empty");
                }

                dys=Integer.parseInt(dystol.getText().toString());
                if(TextUtils.isEmpty(dys.toString()))
                {
                    dystol.setError("Field cannot be empty");
                }

                pul=Integer.parseInt(pulse.getText().toString());
                if(TextUtils.isEmpty(pul.toString()))
                {
                    pulse.setError("Field cannot be empty");
                }

                String date=java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

                String time=java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());

                if(!TextUtils.isEmpty(sys.toString())|| !TextUtils.isEmpty(dys.toString())|| !TextUtils.isEmpty(pul.toString()))
                {
                    try
                    {
                        db.execSQL("INSERT INTO " + TABLE_NAME + " (systol,dystol,pulse,date,time) Values (" + sys + "," + dys + " ," + pul + ",'" + date + "','" + time + "' );");
                        Log.e("database", "systol entered: " + sys);
                        Log.e("database", "dystol entered: " + dys);
                        Log.e("database", "pulse entered: " + pul);
                        Log.e("database", "Date entered : " + date);
                        Log.e("database", "Time entered : " + time);
                    }
                    catch (SQLiteException se)
                    {
                        Log.e("database", "Error in insert query" + se);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter valid text",Toast.LENGTH_LONG);
                }
                Intent in =new Intent(getBaseContext(),DataList_bp.class);
                startActivity(in);
                finish();
            }
        });

    }
}
