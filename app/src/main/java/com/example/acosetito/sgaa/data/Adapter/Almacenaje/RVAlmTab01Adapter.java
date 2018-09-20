package com.example.acosetito.sgaa.data.Adapter.Almacenaje;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVAlmTab01Adapter;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;

import java.util.ArrayList;

public class RVAlmTab01Adapter extends RecyclerView.Adapter<RVAlmTab01Adapter.RVAlmTab01AdapterViewHolder>{

    private ArrayList<UbicacionTransito> baseData;
    private IRVAlmTab01Adapter interf;

    public class RVAlmTab01AdapterViewHolder extends RecyclerView.ViewHolder{

        public RVAlmTab01AdapterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public RVAlmTab01Adapter(IRVAlmTab01Adapter irvAlmTab01Adapter, ArrayList<UbicacionTransito> data) {
        this.interf = irvAlmTab01Adapter;
        this.baseData = data;
    }

    @Override
    public RVAlmTab01Adapter.RVAlmTab01AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVAlmTab01Adapter.RVAlmTab01AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alm_tab_01, parent, false));
    }

    @Override
    public void onBindViewHolder(RVAlmTab01Adapter.RVAlmTab01AdapterViewHolder holder, int position) {
        /**
         UAXProductoTxA ent = this.baseData.get(position);
         holder.tvUA.setText(ent.getUA());
         holder.tvLote.setText(ent.getLoteLab());
         holder.tvContenedor.setText(ent.getContenedor());
         holder.tvFechaIng.setText(formatter.format(ent.getFechaIngreso()));
         holder.tvCantidad.setText(String.valueOf(ent.getCantidad()));
         holder.tvCantAver.setText(String.valueOf(ent.getCantidadAveriada()));
         holder.btnDelete.setTag(position);
         holder.btnDelete.setOnClickListener(btnDeleteOnClickListener);
         **/
    }

    @Override
    public int getItemCount() {
        return baseData.size();
    }
}
