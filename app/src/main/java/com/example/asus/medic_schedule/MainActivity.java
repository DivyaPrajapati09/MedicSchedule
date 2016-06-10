package com.example.asus.medic_schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    private static final int PROFILE_SETTING = 1;

    private AccountHeader.Result headerResult = null;
    private Drawer.Result result = null;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


  //      getActionBar().setDisplayHomeAsUpEnabled(true);
//       getActionBar().setHomeButtonEnabled(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final IProfile profile = new ProfileDrawerItem().withName("Divya Prajapati").withEmail("divya4337@gmail.com").withIcon(getResources().getDrawable(R.drawable.home));

        headerResult = new AccountHeader()
                .withActivity(this)
                        //   .withCompactStyle(true)
                .withHeaderBackground(R.drawable.download)
                .addProfiles(
                        profile
                        //  profile1,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        // new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5)).withIdentifier(PROFILE_SETTING),
                        // new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )

                .withSavedInstance(savedInstanceState)
                .build();

        result = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1).withCheckable(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_medlist).withIcon(FontAwesome.Icon.faw_medkit).withIdentifier(9).withCheckable(false),

                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_Blood_Pressure).withIcon(FontAwesome.Icon.faw_stethoscope).withIdentifier(2).withCheckable(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_Blood_Sugar).withIcon(FontAwesome.Icon.faw_stethoscope).withIdentifier(3).withCheckable(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_Search).withIcon(FontAwesome.Icon.faw_search).withIdentifier(4).withCheckable(false),

                        new SecondaryDrawerItem().withName(R.string.drawer_item_Hospital).withIcon(FontAwesome.Icon.faw_hospital_o).withIdentifier(5).withCheckable(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_Medical).withIcon(FontAwesome.Icon.faw_medkit).withIdentifier(6).withCheckable(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_Ambulance).withIcon(FontAwesome.Icon.faw_ambulance).withIdentifier(7).withCheckable(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_gear).withIdentifier(8).withCheckable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            if (drawerItem.getIdentifier() == 1) {
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                MainActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 2) {
                                Intent intent = new Intent(MainActivity.this, DataList_bp.class);
                                MainActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 3) {
                                Intent intent = new Intent(MainActivity.this, DataList_su.class);
                                MainActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 4) {
                                Intent intent = new Intent(MainActivity.this, Places.class);
                                MainActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 5) {
                                Intent intent = new Intent(MainActivity.this, ClinicList.class);
                                MainActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 6) {
                                Intent intent = new Intent(MainActivity.this, MedicalList.class);
                                MainActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 7) {
                                Intent intent = new Intent(MainActivity.this, Ambulance.class);
                                MainActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 8) {
                                Intent intent = new Intent(MainActivity.this, SystemSettings.class);
                                MainActivity.this.startActivity(intent);
                            }else if (drawerItem.getIdentifier() == 9) {
                                Intent intent = new Intent(MainActivity.this, DMedicine_List.class);
                                MainActivity.this.startActivity(intent);
                            }
                            // set the selection to the item with the identifier 5
                            result.setSelectionByIdentifier(1, false);
                        }
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }
}