package com.example.asus.medic_schedule;

/**
 * Created by ASUS on 1/18/2015.
 */
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ASUS on 1/18/2015.
 */
public class Datalist_p extends ListActivity {

    static final ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();

    private final String DB_NAME="MEDICDB";
    private final String TABLE_NAME="Patient_DB";

    SQLiteDatabase db=null;

    Button pat_add;

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalist_p);

        pat_add=(Button)findViewById(R.id.pat_add);
        list.clear();


        try{
            db=this.openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
           // db.execSQL("DROP TABLE IF EXISTS Patient_DB;");
            Cursor c =db.rawQuery("SELECT * FROM " + TABLE_NAME +";",null);

            if(c!=null){
                if(c.moveToFirst()){
                    do {
                        HashMap<String,String> map =new HashMap<String, String>();

                        String pat_name=c.getString(c.getColumnIndex("p_name"));


                        map.put("p_name",pat_name);

                        list.add(map);
                    }while (c.moveToNext());

                }

            }

            Log.e("Datalist_p", "Total Records" + c.getCount());
        } catch (SQLiteException se){
            Log.e("Datalist_p","could not create or open database" +se);

        }

        SimpleAdapter adapt = new SimpleAdapter(this,list,R.layout.rowdata_p,new String[]{"p_name"}, new int[]{R.id.p_name});
        setListAdapter(adapt);

pat_add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent f= new Intent(getBaseContext(),Patient.class);
        startActivity(f);
    }
});
    }
}
