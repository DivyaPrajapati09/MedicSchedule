package com.example.asus.medic_schedule;

/**
 * Created by ASUS on 1/18/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.medic_schedule.R;

/**
 * Created by ASUS on 1/17/2015.
 */
public class Patient extends ActionBarActivity  {

    EditText p_name;
    Button b1,b2;

    Integer id;
    String name;

    SQLiteDatabase db=null;

    private final String DB_NAME="MEDICDB";
    private final String TABLE_NAME="Patient_DB";

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);

       // p_id=(EditText)findViewById(R.id.p_id);
        p_name=(EditText)findViewById(R.id.p_name);
        b1=(Button)findViewById(R.id.b1);
        //b2=(Button)findViewById(R.id.b2);

        try{

            db=this.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (p_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,p_name VARCHAR);");
        }   catch (SQLiteException se){
            Log.e("Patient", "Could not create or Open the database: "+se);
        }

        b1.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view){

               name= p_name.getText().toString();

                try{
                    db.execSQL("INSERT INTO " + TABLE_NAME + " (p_name) Values ('" + name + "');");
                   // Log.e("Patient","p_id"+p_id);
                    Log.e("Patient","p_name:"+name);
                } catch (SQLiteException se){
                    Log.e("Patient","Error in insert query: "+se);
                }
                Intent in =new Intent(getBaseContext(),Datalist_p.class);
                startActivity(in);
            }
        });

      /* b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent in =new Intent(getBaseContext(),Listdata_p.class);
                startActivity(in);
            }
        });*/
    }

}

