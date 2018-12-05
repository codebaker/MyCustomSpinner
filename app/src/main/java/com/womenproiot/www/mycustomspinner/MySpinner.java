package com.womenproiot.www.mycustomspinner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MySpinner extends ArrayAdapter<MeetupDto> {

    LayoutInflater inflater;
    ArrayList<MeetupDto> objects;
    ViewHolder holder = null;

    public MySpinner(Context context, int resource, List<MeetupDto> objects) {
        super(context, resource, objects);
        inflater = ((Activity) context).getLayoutInflater();
        this.objects = (ArrayList<MeetupDto>) objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        MeetupDto listItem = objects.get(position);
        View row = convertView;

        if (null == row) {
            holder = new ViewHolder();
            row = inflater.inflate(R.layout.row, parent, false);
            holder.seq = (TextView) row.findViewById(R.id.textViewSeq);
            holder.title = (TextView) row.findViewById(R.id.textViewTitle);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.seq.setText(listItem.seq);
        holder.title.setText(listItem.title);

        return row;
    }

    public class ViewHolder {
        TextView seq, title;
    }
}
