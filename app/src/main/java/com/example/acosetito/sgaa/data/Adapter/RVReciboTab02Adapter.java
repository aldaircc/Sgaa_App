package com.example.acosetito.sgaa.data.Adapter;
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
import com.example.acosetito.sgaa.data.Model.DiffUtil.ListarDetalleTx_DiffCallback;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import java.util.ArrayList;
import java.util.List;

public class RVReciboTab02Adapter extends RecyclerView.Adapter<RVReciboTab02Adapter.RVReciboTab02AdapterViewHolder> implements Filterable{

    public List<ListarDetalleTx> list, listFilter, listUpdate;
    private IRVReciboTab02Adapter irvReciboTab02Adapter;
    private int selectedPos = -1;
    FilterReciboTab02 filter;

    public RVReciboTab02Adapter(IRVReciboTab02Adapter irvReciboTab02Adapter){
        this.list = new ArrayList<>();
        this.listFilter = new ArrayList<>();
        this.listUpdate = new ArrayList<>();
        this.irvReciboTab02Adapter = irvReciboTab02Adapter;
    }

    public void clearAndAddAll(List<ListarDetalleTx> list){
        this.list.clear();
        this.listFilter.clear();
        this.list.addAll(list);
        this.listFilter.addAll(list);
        this.listUpdate.clear();
        this.listUpdate.addAll(list);
        notifyDataSetChanged();
    }

    public void clearAndAddAllWithOutNotify(List<ListarDetalleTx> list){
        this.list.clear();
        this.listFilter.clear();
        this.list.addAll(list);
        this.listFilter.addAll(list);
        this.listUpdate.clear();
        this.listUpdate.addAll(list);
    }

    @Override
    public RVReciboTab02Adapter.RVReciboTab02AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVReciboTab02Adapter.RVReciboTab02AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recibo_tab_02, parent, false));
    }

    @Override
    public void onBindViewHolder(RVReciboTab02Adapter.RVReciboTab02AdapterViewHolder holder, int position) {
        ListarDetalleTx ent = list.get(position);
        holder.itemView.setTag(position);
        holder.btnNext.setTag(position);
        holder.tvCodeProd.setText(ent.getCodigo());
        holder.tvLote.setText(ent.getLote());
        holder.tvDescription.setText(ent.getDescripcion());
        holder.tvSubAlmacen.setText(ent.getNombreSubAlmacen());
        holder.tvCantPedida.setText(ent.getCantidadPedida().toString());
        holder.tvCantOper.setText(ent.getCantidadOperacion().toString());
        holder.tvSaldo.setText(ent.getSaldo().toString());
        holder.btnNext.setOnClickListener(btnNextOnClickListener);
    }

    View.OnClickListener btnNextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            notifyItemChanged(selectedPos);
            selectedPos = pos;
            notifyItemChanged(selectedPos);
            irvReciboTab02Adapter.onClickbtnNext(list.get(pos));
        }
    };

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    @Override
    public Filter getFilter() {
        if (this.filter == null){
            filter = new FilterReciboTab02(this.listFilter, this);
        }
        return filter;
    }

    public class RVReciboTab02AdapterViewHolder extends RecyclerView.ViewHolder{

        TextView tvCodeProd, tvLote, tvDescription, tvSubAlmacen, tvCantPedida, tvCantOper, tvSaldo;
        Button btnNext;

        public RVReciboTab02AdapterViewHolder(View itemView) {
            super(itemView);
            tvCodeProd = (TextView)itemView.findViewById(R.id.tvCodeProd);
            tvLote = (TextView)itemView.findViewById(R.id.tvLote);
            tvDescription = (TextView)itemView.findViewById(R.id.tvDescription);
            tvSubAlmacen = (TextView)itemView.findViewById(R.id.tvSubAlmacen);
            tvCantPedida = (TextView)itemView.findViewById(R.id.tvCantPedida);
            tvCantOper = (TextView)itemView.findViewById(R.id.tvCantOper);
            tvSaldo = (TextView)itemView.findViewById(R.id.tvSaldo);
            btnNext = (Button)itemView.findViewById(R.id.btnNext);
        }
    }

    public void updateList(List<ListarDetalleTx> newList){
        final ListarDetalleTx_DiffCallback diffCallback = new ListarDetalleTx_DiffCallback(this.list, newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.list.clear();
        this.list.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }
}