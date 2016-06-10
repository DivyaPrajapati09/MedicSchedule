package com.example.asus.medic_schedule;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ASUS on 2/2/2015.
 */
public class CustomListViewAdapter extends ArrayAdapter<RowItem_bp>
{
    Context context;

    public CustomListViewAdapter(Context context, int resourceId, List<RowItem_bp> items)
    {
        super(context, resourceId, items);
        this.context = context;
    }
        private class ViewHolder
        {
            TextView sys;
            TextView dys;
            TextView pul;
        }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        RowItem_bp rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.rowitem_bp, null);
            holder = new ViewHolder();
            holder.sys = (TextView) convertView.findViewById(R.id.Sys);
            holder.dys = (TextView) convertView.findViewById(R.id.Dys);
            holder.pul = (TextView) convertView.findViewById(R.id.Pul);
            convertView.setTag(holder);
        }
        else

            holder = (ViewHolder) convertView.getTag();

            holder.sys.setText(rowItem.getSys());
            holder.dys.setText(rowItem.getDys());
            holder.pul.setText(rowItem.getPul());
            return convertView;

    }
}