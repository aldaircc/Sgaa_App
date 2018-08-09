package com.example.acosetito.sgaa.ui.Recibo.Tab_02;

import android.content.Intent;
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
import com.example.acosetito.sgaa.ui.Recibo.Tab_03.Recibo_Tab_03Activity;

import java.io.Serializable;
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
    Boolean isFirst = true;

    /** Intent values **/
    String strTxId, strNumOrden, strCuenta, strProveedor;
    Integer intIdTipoMovimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_02);
        initializeControls();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            strTxId = extras.getString("Id_Tx");
            strNumOrden = extras.getString("NumOrden");
            strCuenta = extras.getString("Cuenta");
            strProveedor = extras.getString("Proveedor");
            intIdTipoMovimiento = extras.getInt("Id_TipoMovimiento");
            tvNroOrden.setText(strNumOrden);
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
            tvCountTx.setText(String.valueOf(adapter.listUpdate.size()));
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

        if (isFirst){
            adapter.clearAndAddAllWithOutNotify(lstDetail);
            isFirst = false;
        }else{
            adapter.updateList(lstDetail);
        }

        tvCountTx.setText(String.valueOf(adapter.listUpdate.size()));
    }

    @Override
    public void showFailureDetailTx(String result) {

    }

    @Override
    public void onClickbtnNext(ListarDetalleTx ent) {
        Intent intent = new Intent(this, Recibo_Tab_03Activity.class);
        intent.putExtra("Id_Tx", ent.getId_Tx());
        intent.putExtra("NumOrden", strNumOrden);
        intent.putExtra("Codigo",ent.getCodigo());
        intent.putExtra("Articulo", ent.getDescripcion());
        intent.putExtra("Id_Articulo", ent.getId_Producto());
        intent.putExtra("UM", ent.getUM());
        intent.putExtra("Id_UM", ent.getId_UM());
        intent.putExtra("Fecha_Emi", ent.getFechaEmision());
        intent.putExtra("Fecha_Venci",ent.getFechaVencimiento());
        intent.putExtra("Lote", ent.getLote());
        intent.putExtra("CantPedida", ent.getCantidadPedida());
        intent.putExtra("CantRecib", ent.getCantidadOperacion());
        intent.putExtra("Saldo", ent.getSaldo());
        intent.putExtra("Item", ent.getItem());
        intent.putExtra("Factor", ent.getFactor());
        intent.putExtra("FlagSeriePT", ent.getFlagSeriePT());
        intent.putExtra("Id_TipoMovimiento", intIdTipoMovimiento);
        intent.putExtra("bolAutomatic", chkAutomatic.isChecked());
        startActivity(intent);
    }
}
