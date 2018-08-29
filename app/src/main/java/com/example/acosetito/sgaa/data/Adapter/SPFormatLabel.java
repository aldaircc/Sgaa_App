package com.example.acosetito.sgaa.data.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.acosetito.sgaa.R;

import java.util.HashMap;
import java.util.List;

public class SPFormatLabel extends ArrayAdapter<String>{

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> items;
    private int mResource;

    public SPFormatLabel(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent){
        return createItemView(position, convertView, parent);
    }

    public List<String> getItems() {
        return items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);
        TextView tvDescripcion = (TextView)view.findViewById(R.id.tvItemSpinner);
        String[] strSplit = items.get(position).split("\\|");

        tvDescripcion.setTag(strSplit[0]);
        tvDescripcion.setText(strSplit[1]);
        return view;
    }
}
