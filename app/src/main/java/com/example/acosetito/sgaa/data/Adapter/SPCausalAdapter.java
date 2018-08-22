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
import com.example.acosetito.sgaa.data.Model.Recepcion.Causal;

import java.util.ArrayList;

public class SPCausalAdapter extends ArrayAdapter<Causal> {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<Causal> items;
    private int mResource;

    public SPCausalAdapter(@NonNull Context context, int resource,
                           @NonNull ArrayList<Causal> objects) {
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

    public ArrayList<Causal> getItems() {
        return items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);
        TextView tvDescripcion = (TextView)view.findViewById(R.id.tvCausal);
        tvDescripcion.setTag(items.get(position).getId_Causal());
        tvDescripcion.setText(items.get(position).getCausal());
        return view;
    }
}
