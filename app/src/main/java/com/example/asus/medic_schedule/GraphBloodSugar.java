package com.example.asus.medic_schedule;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;

/**
 * Created by ASUS on 5/13/2015.
 */
public class GraphBloodSugar extends Activity {

    private final String DB_NAME = "MEDICDB";
    private final String TABLE_NAME = "BloodSugar_DB";

    SQLiteDatabase db = null;
    private GraphicalView mChart;

    int conct;
    String date;

    ArrayList<Integer> array_conc = new ArrayList<Integer>();
    ArrayList<String> array_date = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_blood_sugar);
        openChart();
    }

    private void openChart() {

        try {
            db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
            int len = c.getCount();

            if (c != null) {

                if (c.moveToFirst()) {
                    do {
                        int conct = Integer.parseInt(c.getString(c.getColumnIndex("concentration")));
                        Log.e("conct","conct : "+conct);
                        array_conc.add(conct);
                        Log.e("array_sys", "array_sys :" + array_conc);

                        date = c.getString(c.getColumnIndex("date"));
                        array_date.add(date);

                    } while (c.moveToNext());
                }
            }
            // Creating an  XYSeries for Systol
           // XYSeries concSeries = new XYSeries("Concentation");
            XYSeries concSeries=new XYSeries("Concentration");

            for (int i = 0; i <len; i++)
            {
                double conct = array_conc.get(i);
                Log.e("double", "Conct : " + conct);

                String[] datee=array_date.toArray(new String[i]);
                Log.e("datee","datee :"+datee);

                concSeries.add(i,conct);

                // Creating a dataset to hold each series
                XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

                dataset.addSeries(concSeries);

                // Creating XYSeriesRenderer to customize visitsSeries
                XYSeriesRenderer concRenderer = new XYSeriesRenderer();
                concRenderer.setColor(Color.BLUE);
                concRenderer.setPointStyle(PointStyle.CIRCLE);
                concRenderer.setFillPoints(true);
                concRenderer.setLineWidth(2);
                concRenderer.setDisplayChartValues(true);

                // Creating a XYMultipleSeriesRenderer to customize the whole chart
                XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
                multiRenderer.setChartTitle("Blood Sugar level Chart");
                multiRenderer.setXTitle("Days");
                multiRenderer.setYTitle("Concentration");
                multiRenderer.setYAxisMax(250);
                multiRenderer.setYAxisMin(80);
                multiRenderer.setXAxisMin(0);
                // multiRenderer.setXAxisMax();
                multiRenderer.setZoomButtonsVisible(true);
                multiRenderer.setBarSpacing(0.5);
                multiRenderer.setBackgroundColor(Color.TRANSPARENT);
                multiRenderer.setMarginsColor(getResources().getColor(R.color.black_clickable));
                multiRenderer.setApplyBackgroundColor(true);
                multiRenderer.setScale(2f);
                multiRenderer.setPointSize(6f);
                //multiRenderer.setLabelsTextSize(5f);
                //multiRenderer.setAxisTitleTextSize(5f);
                // multiRenderer.setMargins(new int[]{40,40,40,40});

                for (int j = 0; j <len; j++) {
                    multiRenderer.setXLabels(0);
                    multiRenderer.addXTextLabel(j, datee[j]);
                    Log.e("date", "date : " + datee);
                }

                // Adding visitsRenderer and viewsRenderer to multipleRenderer
                // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
                // should be same
                multiRenderer.addSeriesRenderer(concRenderer);

                // Getting a reference to LinearLayout of the MainActivity Layout
                LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container1);

                // Creating a Time Chart
                mChart = ChartFactory.getLineChartView(getBaseContext(), dataset, multiRenderer);

                multiRenderer.setClickEnabled(true);
                multiRenderer.setSelectableBuffer(10);

                // Adding the Line Chart to the LinearLayout
                chartContainer.addView(mChart);
            }
        } catch (SQLiteException se) {        }
    }
}