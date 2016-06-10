package com.example.asus.medic_schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 4/16/2015.
 */
public class MedicalList extends Activity implements AdapterView.OnItemClickListener{

    public static final String[] name=new String[]{
            "Satyam medical store",
            "Shiva medical store",
            "Soham medical",
            "Hemal medical stores",
            "Regal Chemist & drugist",
            "Shivam medical stores",
            "Shri arihant chemist",
            "Y.K Masrani medical",
            "Jalaram medical",
            "Patel medical stores"
    };

    public static final String[] add=new String[]{
            "Ashapuri road,                                              Ph.no:02632-253096",
            "Sandhkuva gate                                              Ph.no:02632-257424",
            "Sisodhra                                                         Ph.no:02632-238608",
            "Kabilpore                                                        Ph.no:02632-255719",
            "Shantadevi road                                             Ph.no:02632-256234",
            "Shantadevi road                                             Ph.no:02632-244260",
            "Kabilpore                                                        Ph.no:02632-237876",
            "Shantadevi road                                             Ph.no:02632-252218",
            "Fuvara                                                           Ph.no:02632-257275",
            "Station road                                                     Ph.no:02632-257469"
    };

    public static final Integer[] images={
            R.drawable.med,
            R.drawable.med,
            R.drawable.med,
            R.drawable.med,
            R.drawable.med,
            R.drawable.med,
            R.drawable.med,
            R.drawable.med,
            R.drawable.med,
            R.drawable.med
    };

    ListView listview_m;
    List<RowItem_medical> rowItems;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical);

        rowItems=new ArrayList<RowItem_medical>();
        for(int i=0;i<add.length;i++)
        {
            RowItem_medical item=new RowItem_medical(images[i],name[i],add[i]);
            rowItems.add(item);
        }
        listview_m=(ListView)findViewById(R.id.list_m);
       CustomListViewAdapter_medical adapter=new CustomListViewAdapter_medical(this,R.layout.rowitem_medical,rowItems);
        listview_m.setAdapter(adapter);
        listview_m.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
