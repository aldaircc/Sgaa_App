package com.example.acosetito.sgaa.ui.Recibo.Tab_01;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVReciboTab01Adapter;
import com.example.acosetito.sgaa.data.Adapter.RVReciboTab01Adapter;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;
import com.example.acosetito.sgaa.ui.Recibo.Tab_02.Recibo_Tab_02Activity;
import java.util.ArrayList;
import java.util.List;

public class Recibo_Tab_01Activity extends AppCompatActivity implements ReciboTab01View, IRVReciboTab01Adapter {

    SearchView svFilterTx;
    RecyclerView rclReciboTx;
    Button btnBack, btnRefresh, btnNext;
    ReciboTab01Presenter presenter;
    RVReciboTab01Adapter adapter;
    List<ListarRecepcionesXUsuario> listRecTx = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_01);
        initializeControls();
        adapter = new RVReciboTab01Adapter(this);
        rclReciboTx.setAdapter(adapter);
        presenter = new ReciboTab01PresenterImpl(this);
        presenter.getListarRecepcionByUser("Admin", 1, 1);
        svFilterTx.setOnQueryTextListener(onQueryTextListener);
    }

    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            adapter.getFilter().filter(s);
            return false;
        }
    };

    @Override
    public void sourceDataRecepcionByUser(List<ListarRecepcionesXUsuario> list) {
        listRecTx.clear();
        listRecTx = list;
        adapter.clearAndAddAll((ArrayList<ListarRecepcionesXUsuario>) listRecTx);
    }

    @Override
    public void showFailureRecepcionByUser(String result) {

    }

    void initializeControls(){
        svFilterTx = (SearchView)findViewById(R.id.svFilterTx);
        rclReciboTx = (RecyclerView)findViewById(R.id.rclReciboTx);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnRefresh = (Button)findViewById(R.id.btnRefresh);
        btnNext = (Button)findViewById(R.id.btnNext);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rclReciboTx.setLayoutManager(llm);
    }

    @Override
    public void onSelectItem(ListarRecepcionesXUsuario ent) {
        Intent intent = new Intent(this, Recibo_Tab_02Activity.class);
        intent.putExtra("Id_Tx", ent.getId_Tx());
        intent.putExtra("NumOrden", ent.getNumOrden());
        intent.putExtra("Cuenta", ent.getCliente());
        intent.putExtra("Proveedor", ent.getProveedor());
        intent.putExtra("Id_TipoMovimiento", ent.getId_TipoMovimiento());
        startActivity(intent);
    }

    @Override
    public void onItemClick(View v, int pos) {

    }

    @Override
    public void onGetItemsCount(Integer count) {

    }
}
