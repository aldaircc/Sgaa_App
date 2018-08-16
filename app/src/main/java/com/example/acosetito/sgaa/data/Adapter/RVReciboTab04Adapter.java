package com.example.acosetito.sgaa.data.Adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVReciboTab04Adapater;
import com.example.acosetito.sgaa.data.Model.DiffUtil.Tab04_DiffUtilCallBack;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RVReciboTab04Adapter extends RecyclerView.Adapter<RVReciboTab04Adapter.RVReciboTab04AdapterViewHolder>{

    private ArrayList<UAXProductoTxA> baseData;
    private IRVReciboTab04Adapater interf;
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public class RVReciboTab04AdapterViewHolder extends RecyclerView.ViewHolder{

        Button btnDelete;
        TextView tvUA, tvLote, tvContenedor, tvFechaIng, tvCantidad, tvCantAver;

        public RVReciboTab04AdapterViewHolder(View itemView) {
            super(itemView);
            tvUA = (TextView)itemView.findViewById(R.id.tvUA);
            tvLote = (TextView)itemView.findViewById(R.id.tvLote);
            tvContenedor = (TextView)itemView.findViewById(R.id.tvContenedor);
            tvFechaIng = (TextView)itemView.findViewById(R.id.tvFechaIng);
            tvCantidad = (TextView)itemView.findViewById(R.id.tvCant);
            tvCantAver = (TextView)itemView.findViewById(R.id.tvCantAver);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }

    public RVReciboTab04Adapter(IRVReciboTab04Adapater irvReciboTab04Adapater, ArrayList<UAXProductoTxA> data){
        this.interf = irvReciboTab04Adapater;
        this.baseData = data;
    }

    @Override
    public RVReciboTab04Adapter.RVReciboTab04AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return null;
        return new RVReciboTab04Adapter.RVReciboTab04AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recibo_tab_04, parent, false));
    }

    @Override
    public void onBindViewHolder(RVReciboTab04Adapter.RVReciboTab04AdapterViewHolder holder, int position) {
        UAXProductoTxA ent = this.baseData.get(position);
        holder.tvUA.setText(ent.getUA());
        holder.tvLote.setText(ent.getLoteLab());
        holder.tvContenedor.setText(ent.getContenedor());
        holder.tvFechaIng.setText(formatter.format(ent.getFechaIngreso()));
        holder.tvCantidad.setText(String.valueOf(ent.getCantidad()));
        holder.tvCantAver.setText(String.valueOf(ent.getCantidadAveriada()));
        holder.btnDelete.setTag(position);
        holder.btnDelete.setOnClickListener(btnDeleteOnClickListener);
    }

    View.OnClickListener btnDeleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            interf.onDeleteItem(baseData.get(pos));
        }
    };

    @Override
    public void onBindViewHolder(RVReciboTab04Adapter.RVReciboTab04AdapterViewHolder holder, int position, List<Object> payLoads){
        if (payLoads.isEmpty()){
            super.onBindViewHolder(holder, position, payLoads);
        }else{
            Bundle o = (Bundle) payLoads.get(0);
            for (String key: o.keySet()){
                if (key.equals("Saldo")){
                    holder.tvLote.setText(baseData.get(position).getLoteLab());
                    holder.tvCantidad.setText(String.valueOf(baseData.get(position).getSaldoTotal()));//String.valueOf(baseData.get(position).getCantidad()));
                    holder.tvCantidad.setTextColor(Color.GREEN);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        //return this.baseData.size();
        return baseData.size();
    }

    public ArrayList<UAXProductoTxA> getData(){
        return  baseData;
    }

    public void setBaseData(ArrayList<UAXProductoTxA> newData){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new Tab04_DiffUtilCallBack(newData, baseData));
        diffResult.dispatchUpdatesTo(this);
        baseData.clear();
        this.baseData.addAll(newData);
    }
}
