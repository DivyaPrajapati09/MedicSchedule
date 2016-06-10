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
 * Created by ASUS on 1/18/2015.
 */
public class Datalist_m extends ListActivity {

    static final ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();
    private final String DB_NAME="MEDICDB";
    private final String TABLE_NAME="Medic_DB";

    SQLiteDatabase db=null;

    Button add;


    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalist_m);

        add=(Button)findViewById(R.id.add);

        list.clear();

        try{
            db=this.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
           //db.execSQL("DROP TABLE IF EXISTS Medic_DB;");
            Cursor c =db.rawQuery("SELECT * FROM " + TABLE_NAME +";",null);

            if(c!=null){
                if(c.moveToFirst()){
                    do {
                        HashMap<String,String> map =new HashMap<String, String>();
                       // String id = c.getString(c.getColumnIndex("m_id"));
                        String name = c.getString(c.getColumnIndex("m_name"));
                        String re=c.getString(c.getColumnIndex("reminder"));
                        String reminder_time = c.getString(c.getColumnIndex("reminder_time"));
                        String duration = c.getString(c.getColumnIndex("duration"));
                        String start_date = c.getString(c.getColumnIndex("start_date"));
                        String days = c.getString(c.getColumnIndex("Days"));
                        String mt=c.getString(c.getColumnIndex("medicine_type"));
                        String dosage = c.getString(c.getColumnIndex("Dosage"));
                        //String remark= c.getString(c.getColumnIndex("remark"));
                        String d_name = c.getString(c.getColumnIndex("d_name"));
                        String p_name = c.getString(c.getColumnIndex("p_name"));

                       // map.put("m_id",id);
                        map.put("m_name",name);
                        map.put("reminder",re);
                        map.put("reminder_time",reminder_time);
                        map.put("duration",duration);
                        map.put("start_date",start_date);
                        map.put("Days",days);
                        map.put("medicine_type",mt);
                        map.put("Dosage",dosage);
                       // map.put("remark",remark);
                        map.put("d_name",d_name);
                        map.put("p_name",p_name);



                        list.add(map);
                    }while (c.moveToNext());

                }
            }

            Log.e("Datalist_m", "Total Records" + c.getCount());
        } catch (SQLiteException se){
            Log.e("Datalist_m","could not create or open database"+se);

        }

        SimpleAdapter adap = new SimpleAdapter(this,
                                list,
                                R.layout.rowdata_m,
                                new String[]{"m_name","reminder","reminder_time","duration","start_date","Days","medicine_type","Dosage","p_name","d_name"},
                                new int[]{R.id.m_name,R.id.reminder,R.id.reminder_time,R.id.duration,R.id.start_date,R.id.Days,R.id.medicine_type,R.id.Dosage,R.id.p_name,R.id.d_name});
        setListAdapter(adap);

    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent c=new Intent(getBaseContext(),Medicine.class);
                    startActivity(c);
        }
    });
    }
}
