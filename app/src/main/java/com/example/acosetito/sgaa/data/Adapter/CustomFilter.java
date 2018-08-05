package com.example.acosetito.sgaa.data.Adapter;

import android.util.Log;
import android.widget.Filter;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    RVReciboTab01Adapter adapter;
    ArrayList<ListarRecepcionesXUsuario> filterList;

    public CustomFilter(ArrayList<ListarRecepcionesXUsuario> filterList, RVReciboTab01Adapter adapter)
    {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();

        if (charSequence != null && charSequence.length() > 0){
            charSequence = charSequence.toString().toUpperCase();

            ArrayList<ListarRecepcionesXUsuario> filteredEnt =
                    new ArrayList<>();
            for (int i = 0; i< filterList.size(); i++)
            {
                if (filterList.get(i).getNumOrden().toUpperCase().contains(charSequence))
                {
                    filteredEnt.add(filterList.get(i));
                }
            }
            results.count = filteredEnt.size();
            results.values = filteredEnt;
        }else{
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        this.adapter.list = (ArrayList<ListarRecepcionesXUsuario>) filterResults.values;
        adapter.notifyDataSetChanged();
    }
}
