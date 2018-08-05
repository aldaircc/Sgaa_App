package com.example.acosetito.sgaa.data.Adapter;

import android.widget.Filter;

import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;

import java.util.ArrayList;
import java.util.List;

public class FilterReciboTab02 extends Filter{

    RVReciboTab02Adapter adapter;
    List<ListarDetalleTx> filterList;

    public FilterReciboTab02(List<ListarDetalleTx> filterList, RVReciboTab02Adapter adapter){
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        charSequence = charSequence.toString().toUpperCase();

        if (charSequence != null && charSequence.length()>0){
            List<ListarDetalleTx> filteredEnt = new ArrayList<>();

            for (int i = 0; i< this.filterList.size(); i++){

                ListarDetalleTx ent = filterList.get(i);
                ent.setEAN13( (ent.getEAN13() == null) ? "" : ent.getEAN13());

                if (ent.getCodigo().toUpperCase().contains(charSequence)
                        || ent.getDescripcion().toUpperCase().contains(charSequence)
                        || ent.getEAN13().toUpperCase().contains(charSequence)){
                    filteredEnt.add(ent);
                }
            }

            results.count = filteredEnt.size();
            results.values = filteredEnt;

        }else{
            results.count = this.filterList.size();
            results.values = this.filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        this.adapter.list = (List<ListarDetalleTx>) filterResults.values;
        this.adapter.notifyDataSetChanged();
    }
}
