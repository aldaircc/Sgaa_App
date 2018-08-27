package com.example.acosetito.sgaa.data.Adapter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVReciboTab02Adapter;
import com.example.acosetito.sgaa.data.Model.DiffUtil.ReciboTab02_DiffUtilCallback;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import java.util.ArrayList;
import java.util.List;

public class RVReciboTab02Adapter extends RecyclerView.Adapter<RVReciboTab02Adapter.RVReciboTab02AdapterViewHolder> implements Filterable{

    public ArrayList<ListarDetalleTx> listFilter, baseData;
    private IRVReciboTab02Adapter irvReciboTab02Adapter;
    private int selectedPos = -1;
    FilterReciboTab02 filter;

    public RVReciboTab02Adapter(IRVReciboTab02Adapter irvReciboTab02Adapter, ArrayList<ListarDetalleTx> list){
        this.baseData = list;
        this.listFilter = list;
        this.irvReciboTab02Adapter = irvReciboTab02Adapter;
    }

    @Override
    public RVReciboTab02Adapter.RVReciboTab02AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVReciboTab02Adapter.RVReciboTab02AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recibo_tab_02, parent, false));
    }

    @Override
    public void onBindViewHolder(RVReciboTab02Adapter.RVReciboTab02AdapterViewHolder holder, int position) {
        ListarDetalleTx ent = baseData.get(position);

        if (ent.getSaldo() == 0.0) {
            holder.viewEstado.setBackgroundColor(Color.GREEN);
        }else if(ent.getSaldo() < ent.getCantidadPedida()){
            holder.viewEstado.setBackgroundColor(Color.YELLOW);
        }

        holder.itemView.setTag(position);
        holder.btnNext.setTag(position);
        holder.btnEtq.setTag(position);
        holder.tvCodeProd.setText(ent.getCodigo());
        holder.tvLote.setText(ent.getLote());
        holder.tvDescription.setText(ent.getDescripcion());
        holder.tvSubAlmacen.setText(ent.getNombreSubAlmacen());
        holder.tvCantPedida.setText(String.format("%.3f", ent.getCantidadPedida()));
        holder.tvCantOper.setText(String.format("%.3f", ent.getCantidadOperacion()));
        holder.tvSaldo.setText(String.format("%.3f", ent.getSaldo()));
        holder.btnNext.setOnClickListener(btnNextOnClickListener);
        holder.btnEtq.setOnClickListener(btnEtqOnClickListener);
    }

    @Override
    public void onBindViewHolder(RVReciboTab02Adapter.RVReciboTab02AdapterViewHolder holder, int position, List<Object> payLoads){
        if (payLoads.isEmpty()){
            super.onBindViewHolder(holder, position, payLoads);
        }else{
            Bundle o = (Bundle) payLoads.get(0);
            for (String key: o.keySet()){
                if (key.equals("Codigo")){
                    holder.tvCodeProd.setText(baseData.get(position).getCodigo());
                }

                if (key.equals("Lote")){
                    holder.tvLote.setText(baseData.get(position).getLote());
                }

                if (key.equals("Descripcion")){
                    holder.tvCodeProd.setText(baseData.get(position).getDescripcion());
                }

                if (key.equals("SubAlmacen")){
                    holder.tvSubAlmacen.setText(baseData.get(position).getNombreSubAlmacen());
                }

                if (key.equals("CantidadPedida")){
                    holder.tvCantPedida.setText(String.format("%.3f",baseData.get(position).getCantidadPedida()));
                }

                if (key.equals("CantidadOperada")){
                    holder.tvCantOper.setText(String.format("%.3f",baseData.get(position).getCantidadOperacion()));
                }

                if (key.equals("Saldo")){
                    holder.tvSaldo.setText(String.format("%.3f",baseData.get(position).getSaldo()));
                }
            }
        }
    }

    View.OnClickListener btnNextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            //notifyItemChanged(selectedPos);
            //selectedPos = pos;
            //notifyItemChanged(selectedPos);
            irvReciboTab02Adapter.onClickbtnNext(baseData.get(pos));
        }
    };

    View.OnClickListener btnEtqOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int)view.getTag();
            irvReciboTab02Adapter.onClickbtnEtq(baseData.get(pos));
        }
    };

    @Override
    public int getItemCount() {
        return this.baseData.size();
    }

    @Override
    public Filter getFilter() {
        if (this.filter == null){
            filter = new FilterReciboTab02(this.baseData, this);
        }
        return filter;
    }

    public class RVReciboTab02AdapterViewHolder extends RecyclerView.ViewHolder{

        TextView tvCodeProd, tvLote, tvDescription, tvSubAlmacen, tvCantPedida, tvCantOper, tvSaldo;
        Button btnNext, btnEtq;
        View viewEstado;

        public RVReciboTab02AdapterViewHolder(View itemView) {
            super(itemView);
            viewEstado = (View)itemView.findViewById(R.id.viewEstado);
            tvCodeProd = (TextView)itemView.findViewById(R.id.tvCodeProd);
            tvLote = (TextView)itemView.findViewById(R.id.tvLote);
            tvDescription = (TextView)itemView.findViewById(R.id.tvDescription);
            tvSubAlmacen = (TextView)itemView.findViewById(R.id.tvSubAlmacen);
            tvCantPedida = (TextView)itemView.findViewById(R.id.tvCantPedida);
            tvCantOper = (TextView)itemView.findViewById(R.id.tvCantOper);
            tvSaldo = (TextView)itemView.findViewById(R.id.tvSaldo);
            btnNext = (Button)itemView.findViewById(R.id.btnNext);
            btnEtq = (Button)itemView.findViewById(R.id.btnEtq);
        }
    }

    public void setBaseData(ArrayList<ListarDetalleTx> newData){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ReciboTab02_DiffUtilCallback( baseData, newData));
        diffResult.dispatchUpdatesTo(this);
        baseData.clear();
        this.baseData.addAll(newData);
    }
}