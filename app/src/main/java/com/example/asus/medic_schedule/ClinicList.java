package com.example.asus.medic_schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 4/12/2015.
 */
public class ClinicList extends Activity implements AdapterView.OnItemClickListener {
public static final String[] titles = new String[] { "Blimora Clinic",
        "Manvant Dental Clinic", "Panchal Orthopaedic Hospital", "Baba Ramdev Patanjali Clinic","Government Ayurveda Centre","Snehal Hospital","Gupta Hospital" };

public static final String[] descriptions = new String[] {
        "Bilimora Clinic, Station Road, Gandevi, Bilimora" +
        "  Navsari-396321" ,

        "Manvant Dental Clinic, 3316, Gohar Baugh," +
        " Bilimora" +
        " Navsari-396321" ,

        "Panchal Orthopaedic Hospital, Gabba Estate" +
        " Tower Road, Biimora" +
        " Navsari-396321" ,

        "Baba Ramdev Patanjali Chikitsalaya," +
        "  Devi Bhuvan," +
        " 1st Floor, Triveni Society" +
        " Talodh, Bilimora-396380" ,

        "Government Ayurveda Centre, Bilimora Main Road," +
        " Gandevi Navsari," +
        " Bilimora-396321",

        "Snehal Hospital, Gohar Baugh, Morarji Desai Marg," +
        " Bilimora-396321"

        ,"Gupta Hospital, Station Road," +
        " Bilimora-396321" ,

        };
    public static final String[] phno={ "Ph:02634-285237",
                                        "Ph:02634-289505",
                                        "Ph:02634-284269",
                                        "Ph:02634-284048",
                                        "Ph:02634-285724",
                                        "Ph:02634-285549",
                                        "Ph:02634-255896"};


public static final Integer[] images = { R.drawable.clinic2,
        R.drawable.clinic2, R.drawable.clinic2, R.drawable.clinic2 ,R.drawable.clinic2,R.drawable.clinic2,R.drawable.clinic2};

        ListView listView;
        List<RowItem_Clinic> rowItems;

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clinic);

        rowItems = new ArrayList<RowItem_Clinic>();
        for (int i = 0; i < titles.length; i++) {
        RowItem_Clinic item = new RowItem_Clinic(images[i], titles[i], descriptions[i],phno[i]);
        rowItems.add(item);
        }


        listView = (ListView) findViewById(R.id.list);
        CustomListViewAdapter_Clinic adapter = new CustomListViewAdapter_Clinic(this,
        R.layout.rowitem_clinic, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        }

@Override
public void onItemClick(AdapterView<?> parent, View view, int position,
        long id) {
      /*  Toast toast = Toast.makeText(getApplicationContext(),
        "Item " + (position + 1) + ": " + rowItems.get(position),
        Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();*/
        }
        }

