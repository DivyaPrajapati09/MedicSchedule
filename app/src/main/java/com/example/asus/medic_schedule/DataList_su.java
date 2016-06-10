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
 * Created by ASUS on 3/10/2015.
 */
public class DataList_su extends ListActivity {
    static final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private final String DB_NAME = "MEDICDB";
    private final String TABLE_NAME = "BloodSugar_DB";

    Button btn_new,btn_grph;
    SQLiteDatabase db = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalist_bs);

        btn_new = (Button) findViewById(R.id.btn_new);
        btn_grph=(Button)findViewById(R.id.btn_grph);
        list.clear();

        try {
            db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
           /*db.execSQL("DROP TABLE IF EXISTS BloodSugar_DB;");
            Log.e("sugar","Table dropped");*/
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        HashMap<String, String> map = new HashMap<String, String>();

                        String meas = c.getString(c.getColumnIndex("Measured"));
                        String date = c.getString(c.getColumnIndex("date"));
                        String time = c.getString(c.getColumnIndex("time"));
                        String conc = c.getString(c.getColumnIndex("concentration"));
                       String tags=c.getString(c.getColumnIndex("tags"));

                        map.put("meas", meas);
                        map.put("date", date);
                        map.put("time", time);
                        map.put("conc", conc);
                        map.put("tags",tags);

                        list.add(map);
                    } while (c.moveToNext());
                    SimpleAdapter adapter = new SimpleAdapter(this,
                            list,
                            R.layout.rowdata_bs,
                            new String[]{"meas", "date", "time", "conc","tags"},
                            new int[]{R.id.measured, R.id.date, R.id.time, R.id.conc,R.id.tags});
                    setListAdapter(adapter);
                }
            }
            Log.e("Datalist_su", "Total Records" + c.getCount());
        } catch (SQLiteException se) {
            Log.e("Datalist_su", "could not create or open database" + se);

        }

        btn_new.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent x =new Intent(getBaseContext(),BloodSugar.class);
                startActivity(x);
            }
        });

        btn_grph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=new Intent(getBaseContext(),GraphBloodSugar.class);
                startActivity(g);
            }
        });

    }
}





