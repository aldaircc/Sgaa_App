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
import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import java.util.ArrayList;

public class SPSubAlmacenXCuenta extends ArrayAdapter<SubAlmacenXCuenta> {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<SubAlmacenXCuenta> items;
    private int mResource;

    public SPSubAlmacenXCuenta(@NonNull Context context, int resource,
                               @NonNull ArrayList<SubAlmacenXCuenta> objects) {
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

    public ArrayList<SubAlmacenXCuenta> getItems() {
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
        tvDescripcion.setTag(items.get(position).getId_SubAlmacen());
        tvDescripcion.setText(items.get(position).getNombreSubAlmacen());
        return view;
    }
}
