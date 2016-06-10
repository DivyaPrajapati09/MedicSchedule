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
 * Created by PRADEPBHAI on 11/4/2014.
 */
public class CustomListViewAdapter_amb extends ArrayAdapter<RowItem>
{
    Context context;

    public CustomListViewAdapter_amb(Context context, int resourceId, List<RowItem> items)
    {
        super(context, resourceId, items);
        this.context = context;
    }

    private class ViewHolder
    {
        ImageView imageView;
        TextView txtadd;
        TextView txtname;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.rowitems,null);
            holder = new ViewHolder();

            holder.txtname = (TextView) convertView.findViewById(R.id.name);
            holder.txtadd=(TextView)convertView.findViewById(R.id.add);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }
        else

            holder = (ViewHolder) convertView.getTag();
        holder.txtname.setText(rowItem.getName());
            holder.txtadd.setText(rowItem.getAdd());

            holder.imageView.setImageResource(rowItem.getImage());
            return convertView;

    }
}
