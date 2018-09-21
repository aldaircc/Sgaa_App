package com.example.acosetito.sgaa.data.Adapter.Almacenaje;

import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVAlmTab01Adapter;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;
import com.example.acosetito.sgaa.data.Model.DiffUtil.Almacenaje.AlmTab01DiffUtilCallBack;

import java.util.ArrayList;
import java.util.List;

public class RVAlmTab01Adapter extends RecyclerView.Adapter<RVAlmTab01Adapter.RVAlmTab01AdapterViewHolder>{

    private ArrayList<UbicacionTransito> baseData;
    private IRVAlmTab01Adapter interf;

    public class RVAlmTab01AdapterViewHolder extends RecyclerView.ViewHolder{

        Button btnUbicar;
        TextView tvSector, tvPasillo, tvUbicacion, tvTotal;

        public RVAlmTab01AdapterViewHolder(View itemView) {
            super(itemView);
            btnUbicar = (Button)itemView.findViewById(R.id.btnUbicar);
            tvSector = (TextView)itemView.findViewById(R.id.tvSector);
            tvPasillo = (TextView)itemView.findViewById(R.id.tvPasillo);
            tvUbicacion = (TextView)itemView.findViewById(R.id.tvUbicacion);
            tvTotal = (TextView)itemView.findViewById(R.id.tvTotal);
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
        UbicacionTransito ent = this.baseData.get(position);
        holder.tvSector.setText(ent.getSector());
        holder.tvPasillo.setText(ent.getPasillo());
        holder.tvUbicacion.setText(ent.getUbicacion());
        holder.tvTotal.setText(String.valueOf(ent.getTotal()));
        holder.btnUbicar.setTag(position);
        holder.btnUbicar.setOnClickListener(ubicarOnClickListener);
    }

    View.OnClickListener ubicarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            interf.onUbicarItem(baseData.get(pos));
        }
    };

    @Override
    public void onBindViewHolder(RVAlmTab01Adapter.RVAlmTab01AdapterViewHolder holder, int position, List<Object> payLoads){
        if (payLoads.isEmpty()){
            super.onBindViewHolder(holder, position, payLoads);
        }else{
            Bundle o = (Bundle) payLoads.get(0);
            for (String key: o.keySet()){
                if (key.equals("Sector")){
                    holder.tvSector.setText(baseData.get(position).getSector());
                }else if (key.equals("Pasillo")){
                    holder.tvPasillo.setText(baseData.get(position).getPasillo());
                }else if (key.equals("Ubicacion")){
                    holder.tvUbicacion.setText(baseData.get(position).getUbicacion());
                }else if (key.equals("Total")){
                    holder.tvTotal.setText(String.valueOf(baseData.get(position).getTotal()));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return baseData.size();
    }

    public void setBaseData(ArrayList<UbicacionTransito> newData){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new AlmTab01DiffUtilCallBack(newData, baseData));
        diffResult.dispatchUpdatesTo(this);
        baseData.clear();
        this.baseData.addAll(newData);
    }
}
