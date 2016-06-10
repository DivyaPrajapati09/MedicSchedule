package com.example.asus.medic_schedule;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ASUS on 4/19/2015.
 */
public class DMedicine_List extends ListActivity {

    private final String DB_NAME = "MEDICDB";
    private final String TABLE_NAME = "Medic_DB";

    static final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    ListView listmed;

    SQLiteDatabase db = null;
    String qun;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dmedicine_list);


        listmed = (ListView) findViewById(R.id.list);
        list.clear();

        try {
            db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
            Cursor c = db.rawQuery("SELECT m_name, quantity ,start_date, Days FROM " + TABLE_NAME + ";", null);

            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        HashMap<String, String> map = new HashMap<String, String>();

                        String name = c.getString(c.getColumnIndex("m_name"));

                        String quantity = c.getString(c.getColumnIndex("quantity"));
                        Log.e("medicine list","medicine quantity: "+quantity);
                        String start_date = c.getString(c.getColumnIndex("start_date"));
                        Log.e("medicine list","start date : "+start_date);
                        String days = c.getString(c.getColumnIndex("Days"));

                        map.put("m_name", name);
                        map.put("quantity", quantity);
                        map.put("start_date",start_date);
                        map.put("Days",days);

                        list.add(map);

                    } while (c.moveToNext());
                }
            }
        } catch (SQLiteException se) {
            Log.e("Datalist_m", "could not create or open database" + se);
        }

        SimpleAdapter adap = new SimpleAdapter(this,
                list,
                R.layout.rowdata_med,
                new String[]{"m_name","quantity","start_date","days"},
                new int[]{R.id.med,R.id.qun,R.id.s_date,R.id.days});
        setListAdapter(adap);
    }


    }

