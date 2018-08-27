package com.example.acosetito.sgaa.ui.Recibo.Tab_01;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVReciboTab01Adapter;
import com.example.acosetito.sgaa.data.Adapter.RVReciboTab01Adapter;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Fragments.Incidencia.IncidenciaFragment;
import com.example.acosetito.sgaa.ui.Main.MainActivity;
import com.example.acosetito.sgaa.ui.Recibo.Tab_02.Recibo_Tab_02Activity;
import java.util.ArrayList;
import java.util.List;

public class Recibo_Tab_01Activity extends AppCompatActivity implements ReciboTab01View, IRVReciboTab01Adapter, IncidenciaFragment.IncidenciaDialogListener {

    private final int REQUEST_CODE = 20;

    SearchView svFilterTx;
    RecyclerView rclReciboTx;
    Button btnBack, btnRefresh, btnNext;
    ReciboTab01Presenter presenter;
    RVReciboTab01Adapter adapter;
    ArrayList<ListarRecepcionesXUsuario> listRecTx = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_01);
        initializeControls();
        adapter = new RVReciboTab01Adapter(this, listRecTx);
        rclReciboTx.setAdapter(adapter);
        presenter = new ReciboTab01PresenterImpl(this);
        presenter.getListarRecepcionByUser("ADMIN",2,1);//"Admin", 1, 1);
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

    //region Show menu and options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recibo, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Build.VERSION.SDK_INT > 11) {
            invalidateOptionsMenu();
            menu.findItem(R.id.itemBack).setVisible(true);
            menu.findItem(R.id.itemRefresh).setVisible(true);
            menu.findItem(R.id.itemPallet).setVisible(false);
            menu.findItem(R.id.itemRegInci).setVisible(false);
            menu.findItem(R.id.itemEtiqImpr).setVisible(false);
            menu.findItem(R.id.itemSelectImpr).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.itemBack:
                presenter.goBackToMenu();
                return true;
            case R.id.itemRefresh:
                presenter.getListarRecepcionByUser("ADMIN",2,1);//"Admin", 1, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

    View.OnClickListener btnBackOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.goBackToMenu();
        }
    };

    View.OnClickListener btnRefreshOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.getListarRecepcionByUser("ADMIN",2,1);//"Admin", 1, 1);
        }
    };

    @Override
    public void sourceDataRecepcionByUser(ArrayList<ListarRecepcionesXUsuario> list) {
        listRecTx.clear();
        listRecTx = list;
        //adapter.clearAndAddAll(listRecTx);
        adapter.setBaseData(list);
    }

    @Override
    public void showFailureRequest(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goBackToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void showProgressDialog() {
        ProgressDialogRequest.show(this);
    }

    @Override
    public void hideProgressDialog() {
        ProgressDialogRequest.dismiss();
    }

    @Override
    public void navigateToReciboTab02(String strId_Tx, String strNumOrden, Boolean bolFlagPausa, String strCuenta, String strProveedor, Integer intId_TipoMovimiento, Integer intId_Cliente) {
        Intent intent = new Intent(this, Recibo_Tab_02Activity.class);
        intent.putExtra("Id_Tx", strId_Tx);
        intent.putExtra("NumOrden", strNumOrden);
        intent.putExtra("Cuenta", strCuenta);
        intent.putExtra("Proveedor", strProveedor);
        intent.putExtra("Id_TipoMovimiento", intId_TipoMovimiento);
        intent.putExtra("FlagPausa", bolFlagPausa);
        intent.putExtra("Id_Cliente", intId_Cliente);
        //startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE);
    }

    void initializeControls(){
        svFilterTx = (SearchView)findViewById(R.id.svFilterTx);
        rclReciboTx = (RecyclerView)findViewById(R.id.rclReciboTx);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(btnBackOnClickListener);
        btnRefresh = (Button)findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(btnRefreshOnClickListener);
        btnNext = (Button)findViewById(R.id.btnNext);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rclReciboTx.setLayoutManager(llm);
    }

    @Override
    public void onSelectItem(ListarRecepcionesXUsuario ent) {
        if (ent.getFlagPausa()){
            showDialogIncidencia(ent.getId_Tx(), ent.getNumOrden(),
                    ent.getFlagPausa(), ent.getCliente(),
                    ent.getProveedor(), ent.getId_TipoMovimiento());
        }else {
            presenter.navigateToReciboTab02(ent.getId_Tx(), ent.getNumOrden(), ent.getFlagPausa(),
                    ent.getCliente(), ent.getProveedor(), ent.getId_TipoMovimiento(), ent.getId_Cliente());
        }
    }

    @Override
    public void onItemClick(View v, int pos) {

    }

    @Override
    public void onGetItemsCount(Integer count) {

    }

    private void showDialogIncidencia(String strId_Tx, String strNumOrden,
                                      Boolean bolFlagPausa, String strCuenta,
                                      String strProveedor, Integer intId_TipoMovimiento){
        FragmentManager fm = getSupportFragmentManager();
        IncidenciaFragment frm = new IncidenciaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Id_Tx",strId_Tx);
        bundle.putString("NumOrden", strNumOrden);
        bundle.putBoolean("FlagPausa", bolFlagPausa);
        bundle.putString("Cuenta", strCuenta);
        bundle.putString("Proveedor", strProveedor);
        bundle.putInt("Id_TipoMovimiento", intId_TipoMovimiento);
        bundle.putInt("moduloTab", 11);
        frm.setArguments(bundle);
        frm.show(fm, "fragment_Incidencia");
    }

    @Override
    public void onCompleteEditDialog(String strId_Tx, String strNumOrden,
                                     Boolean bolFlagPausa, String strCuenta,
                                     String strProveedor, Integer intId_TipoMovimiento, Integer intId_Cliente)
    {
        //navigateToTab02(strId_Tx, strNumOrden, bolFlagPausa, strCuenta, strProveedor, intId_TipoMovimiento);
        presenter.navigateToReciboTab02(strId_Tx, strNumOrden, bolFlagPausa, strCuenta, strProveedor, intId_TipoMovimiento, intId_Cliente);
    }

    @Override
    public void onBackPressed() {
        // Write your code here
        super.onBackPressed();
        presenter.goBackToMenu();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            Integer x = data.getExtras().getInt("key");
            presenter.getListarRecepcionByUser("ADMIN",2,1);//"Admin", 1, 1);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
