package com.example.acosetito.sgaa.ui.Recibo.Tab_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVReciboTab02Adapter;
import com.example.acosetito.sgaa.data.Adapter.RVReciboTab02Adapter;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;

import java.util.ArrayList;
import java.util.List;

public class Recibo_Tab_02Activity extends AppCompatActivity implements ReciboTab02View, IRVReciboTab02Adapter{
    RecyclerView rclDetailTx;
    Button btnBack, btnNext, btnCausal, btnCloseTx;
    SearchView svDetail;
    CheckBox chkAutomatic;
    TextView tvNroOrden, tvCuenta, tvProveedor, tvCountTx;

    List<ListarDetalleTx> lstDetail = new ArrayList<>();
    ReciboTab02Presenter presenter;
    RVReciboTab02Adapter adapter;
    String strTxId, strNroOrden, strCuenta, strProveedor;
    Boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_02);
        initializeControls();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            strTxId = extras.getString("Id_Tx");
            strNroOrden = extras.getString("NroOrden");
            strCuenta = extras.getString("Cuenta");
            strProveedor = extras.getString("Proveedor");
            tvNroOrden.setText(strNroOrden);
            tvProveedor.setText(strProveedor);
            tvCuenta.setText(strCuenta);
        }

        presenter = new ReciboTab02PresenterImpl(this);
        presenter.getDataDetailTx(strTxId);
    }

    void initializeControls(){
        rclDetailTx = (RecyclerView)findViewById(R.id.rclDetailTx);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnNext = (Button)findViewById(R.id.btnNext);
        btnCausal = (Button)findViewById(R.id.btnCausal);
        btnCloseTx = (Button)findViewById(R.id.btnCloseTx);
        btnCloseTx.setOnClickListener(btnCloseTxOnClickListener);
        svDetail = (SearchView)findViewById(R.id.svDetail);
        svDetail.setOnQueryTextListener(onQueryTextListener);
        chkAutomatic = (CheckBox)findViewById(R.id.chkAutomatic);
        tvNroOrden = (TextView)findViewById(R.id.tvNroOrden);
        tvCuenta = (TextView)findViewById(R.id.tvCuenta);
        tvProveedor = (TextView)findViewById(R.id.tvProveedor);
        tvCountTx = (TextView)findViewById(R.id.tvCountTx);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rclDetailTx.setLayoutManager(llm);
        adapter = new RVReciboTab02Adapter(this);
        rclDetailTx.setAdapter(adapter);
    }

    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            adapter.getFilter().filter(s);
            tvCountTx.setText(String.valueOf(adapter.getItemCount()));
            return false;
        }
    };

    View.OnClickListener btnCloseTxOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.getCerrarRecepcion("123456789987",99, "ADMIN");
        }
    };

    @Override
    public void showResultCerrarRecepcion(Mensaje message) {
        Toast.makeText(this, "Cerrar recepcion:"+ message.errNumber + " - " + message.message, Toast.LENGTH_SHORT).show();
        presenter.getDataDetailTx(strTxId);

    }

    @Override
    public void showFailureCerrarRecepcion(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sourceDataDetailTx(List<ListarDetalleTx> list) {
        lstDetail.clear();
        lstDetail = list;
        if(isFirst){
            adapter.clearAndAddAllWithOutNotify(lstDetail);
            isFirst = false;
        }else{
            adapter.clearAndAddAllWithOutNotify(lstDetail);
            adapter.updateList(lstDetail);
        }
        tvCountTx.setText(String.valueOf(list.size()));
    }

    @Override
    public void showFailureDetailTx(String result) {

    }

    @Override
    public void onSelectItem(ListarDetalleTx ent) {
        Toast.makeText(this,"Entidad:"+ ent.getDescripcion(), Toast.LENGTH_SHORT).show();
    }
}
