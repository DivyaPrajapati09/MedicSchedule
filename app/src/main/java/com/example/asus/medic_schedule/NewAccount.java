package com.example.asus.medic_schedule;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

/**
 * Created by ASUS on 4/19/2015.
 */
public class NewAccount extends ActionBarActivity{

    EditText name,mail;

    SQLiteDatabase db=null;

    private final String DB_NAME="MEDICDB";
    private final String TABLE_NAME="Doc_DB";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccount);

        name=(EditText)findViewById(R.id.name);
        mail=(EditText)findViewById(R.id.mail);


    }
}
