package com.example.acosetito.sgaa.data.Adapter.Almacenaje;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Model.Almacenaje.SectorXAlmacen;

import java.util.ArrayList;

public class SPSector extends ArrayAdapter<SectorXAlmacen> {

    private Context mContext;
    private LayoutInflater mInflater;
    private Integer mResource;
    private ArrayList<SectorXAlmacen> items;

    public SPSector(@NonNull Context context, int resource,
                    @NonNull ArrayList<SectorXAlmacen> objects) {
        super(context, resource, objects);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    public ArrayList<SectorXAlmacen> getItems() {
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
        tvDescripcion.setText(items.get(position).getSector());
        tvDescripcion.setTag(items.get(position).getId_Sector());
        return view;
    }
}
