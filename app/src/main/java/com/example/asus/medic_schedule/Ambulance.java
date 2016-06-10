package com.example.asus.medic_schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 4/12/2015.
 */
public class Ambulance extends Activity implements AdapterView.OnItemClickListener{

    /**
     * Called when the activity is first created.
     */
    public static final String[] name=new String[]{
            "Sahkar Ambulance",
            "108 Ambulance",
            "Love and care hospital",
            "Preet Ambulance Service",
            "Shikha ICU Ambulance service",
            "Life line express",
            "Fortis hospital",
            "Love parents ambulance",
            "Amit Ambulance",
            "KGN Ambulance"
    };
    public static final String[] add=new String[]{
            "Valsad, Gujarat,                                              Ph.no:02632244017",
            "Emergency Services SH 67, Valsad                      Ph.no:108",
            "8,Shiv Shakti Society,Merulaxmi Road,Tariwad, Surat, Gujarat                             Ph. no.:02612763535",
            "13,Udhna-Sachin highway, Near harinagar-2,udhna,Surat,Gujarat                                     Ph. no: 09825539892",
            "14 Umiya nagar-1 navagam dindoli road, Udhna, Surat                                                 Ph.no:09879397513",
            "03,Rozy Tower, koperly Rd, Near bridge, Vapi East, Imran nagar, Vapi,Gujarat                              Ph. no:08141555557",
            "Kalyan shill road, kalyan city                                                   ",
            "Near country club, Andheri west",
            "S V P road, Prathna samaj",
            "N S road no 4, Andheri west"
    };
    //public static final int[] ph_no=new String[]{,,};
    public static final Integer[] images={
            R.drawable.amb1,
            R.drawable.amb1,
            R.drawable.amb1,
            R.drawable.amb1,
            R.drawable.amb1,
            R.drawable.amb1,
            R.drawable.amb1,
            R.drawable.amb1,
            R.drawable.amb1,
            R.drawable.amb1
    };

    ListView listview;
    List<RowItem> rowItems;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        rowItems=new ArrayList<RowItem>();
        for(int i=0;i<add.length;i++)
        {
            RowItem item=new RowItem(images[i],name[i],add[i]);
            rowItems.add(item);
        }
        listview=(ListView)findViewById(R.id.listView);
        CustomListViewAdapter_amb adapter=new CustomListViewAdapter_amb(this,R.layout.rowitems,rowItems);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id)
    {
        // Toast toast=Toast.makeText(getApplicationContext(),"add"+"phn")
    }
}


