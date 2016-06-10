package com.example.asus.medic_schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ASUS on 3/28/2015.
 */
public class SystemSettings extends ActionBarActivity {

    Button btn_pat,btn_doc,btn_med;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.systemsettings);

        btn_pat=(Button)findViewById(R.id.btn_patn);
        btn_doc=(Button)findViewById(R.id.btn_doc);
        btn_med=(Button) findViewById(R.id.btn_med);


        btn_pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getBaseContext(), Datalist_p.class);
                startActivity(a);
            }
        });


        btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getBaseContext(), Datalist_d.class);
                startActivity(b);

            }
        });

        btn_med.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent c=new Intent(getBaseContext(),Datalist_m.class);
                startActivity(c);

            }
        });

    }


}
