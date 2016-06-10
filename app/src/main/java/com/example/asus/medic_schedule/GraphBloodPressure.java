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
public class GraphBloodPressure extends Activity {

    private GraphicalView mChart;

    SQLiteDatabase db = null;

    private final String DB_NAME = "MEDICDB";
    private final String TABLE_NAME = "BldPS_DB";

    int j = 0, syst, dyst;
    String date;

    ArrayList<Integer> array_sys = new ArrayList<Integer>();
    ArrayList<Integer> array_dys = new ArrayList<Integer>();
    ArrayList<String> array_date = new ArrayList<String>();

    private String[] mMonth = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_blood_pressure);
        openChart();
    }

    private void openChart() {

        XYMultipleSeriesDataset dataset = null;
        try {
            db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
            int len = c.getCount();

            if (c != null) {

                if (c.moveToFirst()) {
                    do {
                        syst = Integer.parseInt(c.getString(c.getColumnIndex("systol")));
                        array_sys.add(syst);
                        Log.e("array_sys", "array_sys :" + array_sys);

                        dyst = Integer.parseInt(c.getString(c.getColumnIndex("dystol")));
                        array_dys.add(dyst);

                        date = c.getString(c.getColumnIndex("date"));
                        array_date.add(date);

                    } while (c.moveToNext());
                }
            }
            // Creating an  XYSeries for Systol
            XYSeries SystolSeries = new XYSeries("Systol");
            // Creating an  XYSeries for Dystol
            XYSeries DystolSeries = new XYSeries("Dystol");
            // Adding data to Systol and Dystol Series

            for (int i = 0; i <len; i++)
            {

                double systol = array_sys.get(i);
                Log.e("double", "Systol : " + systol);
                double dystol = array_dys.get(i);

               // String date = array_date.get(i);

                String[] datee=array_date.toArray(new String[i]);
                Log.e("datee","datee :"+datee);


                SystolSeries.add(i, systol);
                DystolSeries.add(i, dystol);

                // Creating a dataset to hold each series
                dataset = new XYMultipleSeriesDataset();

                dataset.addSeries(SystolSeries);
                dataset.addSeries(DystolSeries);

                // Creating XYSeriesRenderer to customize visitsSeries
                XYSeriesRenderer systolRenderer = new XYSeriesRenderer();
                systolRenderer.setColor(Color.BLUE);
                systolRenderer.setPointStyle(PointStyle.CIRCLE);
                systolRenderer.setFillPoints(true);
                systolRenderer.setLineWidth(2);
                systolRenderer.setDisplayChartValues(true);

                // Creating XYSeriesRenderer to customize viewsSeries
                XYSeriesRenderer dystolRenderer = new XYSeriesRenderer();
                dystolRenderer.setColor(Color.RED);
                dystolRenderer.setPointStyle(PointStyle.CIRCLE);
                dystolRenderer.setFillPoints(true);
                dystolRenderer.setLineWidth(2);
                dystolRenderer.setDisplayChartValues(true);

                // Creating a XYMultipleSeriesRenderer to customize the whole chart
                XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
                multiRenderer.setChartTitle("Systol vs Dystol Chart");
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

               //
               // multiRenderer.setMargins(new int[]{40, 40, 40, 40});

                for (int j = 0; j <len; j++) {
                    multiRenderer.setXLabels(0);
                    multiRenderer.addXTextLabel(j, datee[j]);
                    Log.e("date", "date : " + datee);
                }

                    // Adding visitsRenderer and viewsRenderer to multipleRenderer
                    // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
                    // should be same
                    multiRenderer.addSeriesRenderer(systolRenderer);
                    multiRenderer.addSeriesRenderer(dystolRenderer);

                    // Getting a reference to LinearLayout of the MainActivity Layout
                    LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);

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

