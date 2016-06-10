package com.example.asus.medic_schedule;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ASUS on 1/31/2015.
 */
public class DataList_bp extends ListActivity{

    static final ArrayList<HashMap<String , String>> list = new ArrayList<HashMap<String, String>>();
    private final String DB_NAME = "MEDICDB";
    private final String TABLE_NAME = "BldPS_DB";

    Button add,btn_graph;
    SQLiteDatabase db = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalist_bp);

        add=(Button)findViewById(R.id.add);
        btn_graph=(Button)findViewById(R.id.btn_graph);
        list.clear();

        try {

            db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
           // db.execSQL("DROP TABLE IF EXISTS BldPS_DB;");
            //Log.e("sugar","Table dropped");

            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        HashMap<String, String> map = new HashMap<String , String>();

                        String sys = c.getString(c.getColumnIndex("systol"));
                        Log.e("Blood Pressure","systol entered in database"+ c.getString(c.getColumnIndex("systol")));

                        String dys = c.getString(c.getColumnIndex("dystol"));
                        Log.e("Blood Pressure","dystol entered in database");

                        String pul = c.getString(c.getColumnIndex("pulse"));
                        Log.e("Blood Pressure","pulse entered in database");

                       String date=c.getString(c.getColumnIndex("date"));
                        //Log.e("Blood Pressure","date entered" + c.getString(c.getColumnIndex("date")));

                       String tim=c.getString(c.getColumnIndex("time"));
                       Log.e("Blood Pressure","time entered" + c.getString(c.getColumnIndex("time")));


                        map.put("sys", sys);
                        map.put("dys", dys);
                        map.put("pul", pul);
                       map.put("date",date);
                       map.put("time",tim);

                        //Log.e("blood ","time entered"+tim);

                        list.add(map);
                    } while (c.moveToNext());
                    SimpleAdapter ada = new SimpleAdapter(this, list, R.layout.rowdata_bp, new String[]{"sys", "dys", "pul","date","time"}, new int[]{R.id.Sys, R.id.Dys, R.id.Pul,R.id.date,R.id.time});
                   setListAdapter(ada);

                }
            }

            Log.e("Blood Pressure", "Total Records" + c.getCount());
        } catch (SQLiteException se) {
            Log.e("Blood Pressure", "could not create or open database"+se);

        }

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent x =new Intent(getBaseContext(),BloodPressure.class);
                startActivity(x);
            }
        });

        btn_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=new Intent(getBaseContext(),GraphBloodPressure.class);
                startActivity(g);
            }
        });
    }


}



