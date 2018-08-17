package com.example.acosetito.sgaa.data.Adapter.Fragment;

import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVImpresoraAdapter;
import com.example.acosetito.sgaa.data.Model.DiffUtil.ImpresoraDiffUtilCallBack;
import com.example.acosetito.sgaa.data.Model.Impresora.AccesosImpresoraXUsuario;

import java.util.ArrayList;
import java.util.List;

public class RVImpresoraAdapter extends RecyclerView.Adapter<RVImpresoraAdapter.RVImpresoraAdapterViewHolder>{

    ArrayList<AccesosImpresoraXUsuario> baseData;
    IRVImpresoraAdapter irvImpresoraAdapter;

    public RVImpresoraAdapter(IRVImpresoraAdapter irvImpresoraAdapter) {
        this.irvImpresoraAdapter = irvImpresoraAdapter;
        this.baseData = new ArrayList<>();
    }

    @Override
    public RVImpresoraAdapter.RVImpresoraAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVImpresoraAdapter.RVImpresoraAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_impresora, parent, false));
    }

    @Override
    public void onBindViewHolder(RVImpresoraAdapter.RVImpresoraAdapterViewHolder holder, int position) {
        AccesosImpresoraXUsuario ent = baseData.get(position);
        holder.tvImpresora.setText(ent.getNombre());
        holder.tvIp.setText(ent.getIpImpresora());
        holder.tvPuerto.setText(ent.getPuertoImpresora());
        holder.btnSelect.setTag(position);
        holder.btnSelect.setOnClickListener(onClickBtnSelectListener);
    }

    View.OnClickListener onClickBtnSelectListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Integer pos = (int) view.getTag();
            irvImpresoraAdapter.OnClickBtnSelect(baseData.get(pos));
        }
    };

    @Override
    public int getItemCount() {
        return this.baseData.size();
    }

    public class RVImpresoraAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView tvImpresora, tvIp, tvPuerto;
        Button btnSelect;

        public RVImpresoraAdapterViewHolder(View itemView) {
            super(itemView);
            tvImpresora = (TextView)itemView.findViewById(R.id.tvImpresora);
            tvIp = (TextView)itemView.findViewById(R.id.tvIp);
            tvPuerto = (TextView)itemView.findViewById(R.id.tvPuerto);
            btnSelect = (Button)itemView.findViewById(R.id.btnSelect);
        }
    }

    @Override
    public void onBindViewHolder(RVImpresoraAdapter.RVImpresoraAdapterViewHolder holder, int position, List<Object> payLoads){
        if (payLoads.isEmpty()){
            super.onBindViewHolder(holder, position, payLoads);
        }else{
            Bundle o = (Bundle) payLoads.get(0);
            for (String key: o.keySet()){
                if (key.equals("Nombre")){
                    holder.tvImpresora.setText(baseData.get(position).getNombre());
                }
                if (key.equals("Ip")){
                    holder.tvIp.setText(baseData.get(position).getIpImpresora());
                }
                if (key.equals("Puerto")){
                    holder.tvPuerto.setText(baseData.get(position).getPuertoImpresora());
                }
            }
        }
    }

    public void setBaseData(ArrayList<AccesosImpresoraXUsuario> newData){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ImpresoraDiffUtilCallBack(newData, baseData));
        diffResult.dispatchUpdatesTo(this);
        baseData.clear();
        this.baseData.addAll(newData);
    }
}
