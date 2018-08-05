package com.example.acosetito.sgaa.data.Adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVReciboTab01Adapter;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RVReciboTab01Adapter extends RecyclerView.Adapter<RVReciboTab01Adapter.RVReciboTab01AdapterViewHolder> implements Filterable{

    ArrayList<ListarRecepcionesXUsuario> list, listFilter;
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private Context context; // ??
    private IRVReciboTab01Adapter irvReciboTab01Adapter;
    private int selectedPos = -1, idTx = 0;

    CustomFilter filter;
    public RVReciboTab01Adapter(IRVReciboTab01Adapter irvReciboTab01Adapter){
        this.irvReciboTab01Adapter = irvReciboTab01Adapter;
        this.list = new ArrayList<>();
        this.listFilter = new ArrayList<>(); //New implement for filter of RecyclerView
    }

    public void clearAndAddAll(ArrayList<ListarRecepcionesXUsuario> list){
        this.list.clear();
        this.list.addAll(list);
        this.listFilter.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RVReciboTab01Adapter.RVReciboTab01AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVReciboTab01Adapter.RVReciboTab01AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recibo_tab_01, parent, false));
    }

    @Override
    public void onBindViewHolder(RVReciboTab01Adapter.RVReciboTab01AdapterViewHolder holder, int position) {
        ListarRecepcionesXUsuario ent = list.get(position);
        holder.itemView.setTag(position);
        /**
        switch (listTx.get(position).getId_Estado()){
            case 2: //Confirmado
                holder.cv.setBackgroundColor(Color.LTGRAY);
                break;
            case 3: //Proceso
                holder.cv.setBackgroundColor(Color.YELLOW);
                break;
            default:
                break;
        }**/

        holder.tvNroOrden.setText(ent.getNumOrden().toString());
        holder.tvFecDoc.setText(formatter.format(ent.getFechaDocumento()));
        holder.tvProveedor.setText(ent.getProveedor());
        holder.tvCuenta.setText(ent.getCliente());
        holder.tvEstado.setText(ent.getEstado());
        holder.tvNroTx.setText(ent.getId_Tx());
        holder.itemView.setOnClickListener(itemViewOnClickListener); //New implement for filter
        holder.btnNext.setTag(position);
        holder.btnNext.setOnClickListener(buttonOnClickListener);
    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) { //New implement for filter
            int position = (int) view.getTag();
            irvReciboTab01Adapter.onItemClick(view, position);
        }
    };

    View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                int position = (int) view.getTag();
                /**notifyItemChanged(selectedPos);
                selectedPos = position;
                idTx = -1;
                notifyItemChanged(selectedPos);**/
                irvReciboTab01Adapter.onSelectItem(list.get(position));
        }
    };

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    //New implement for filter data of RecyclerView
    @Override
    public Filter getFilter() {
        if (this.filter == null){
            filter = new CustomFilter(this.listFilter, this);
        }
        return filter;
    }

    public class RVReciboTab01AdapterViewHolder extends RecyclerView.ViewHolder{
        //CardView cv;
        TextView tvNroOrden, tvProveedor, tvFecDoc, tvCuenta, tvEstado, tvNroTx;
        Button btnNext;

        public RVReciboTab01AdapterViewHolder(View itemView){
            super(itemView);
            //cv = (CardView)itemView.findViewById(R.id.cvTxT1);
            tvNroOrden = (TextView)itemView.findViewById(R.id.tvNroOrden);
            tvProveedor = (TextView)itemView.findViewById(R.id.tvProveedor);
            tvFecDoc = (TextView)itemView.findViewById(R.id.tvFecDoc);
            tvCuenta = (TextView)itemView.findViewById(R.id.tvCuenta);
            tvEstado = (TextView)itemView.findViewById(R.id.tvEstado);
            tvNroTx = (TextView)itemView.findViewById(R.id.tvNroTx);
            btnNext = (Button)itemView.findViewById(R.id.btnNext);
        }
    }
}
