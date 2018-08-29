package com.example.acosetito.sgaa.ui.Recibo.Tab_02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Usuario;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Etiqueta.EtqCajaLpnActivity;
import com.example.acosetito.sgaa.ui.Fragments.Impresora.ImpresoraFragment;
import com.example.acosetito.sgaa.ui.Fragments.Incidencia.IncidenciaFragment;
import com.example.acosetito.sgaa.ui.Recibo.Tab_01.Recibo_Tab_01Activity;
import com.example.acosetito.sgaa.ui.Recibo.Tab_03.Recibo_Tab_03Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recibo_Tab_02Activity extends AppCompatActivity implements ReciboTab02View, IRVReciboTab02Adapter, IncidenciaFragment.IncidenciaDialogListener{
    RecyclerView rclDetailTx;
    Button btnBack, btnNext, btnCausal, btnCloseTx;
    SearchView svDetail;
    CheckBox chkAutomatic;
    TextView tvNroOrden, tvCuenta, tvProveedor, tvCountTx;

    ArrayList<ListarDetalleTx> lstDetail = new ArrayList<>();
    ReciboTab02Presenter presenter;
    RVReciboTab02Adapter adapter;
    Boolean isFirst = true;

    /** Intent values **/
    String strR_TxId, strR_NumOrden, strR_Cuenta, strR_Proveedor;
    Integer intR_IdTipoMovimiento, intR_Id_Cliente;
    Boolean bolR_FlagPausa = false;
    private final int CODE_TAB_02 = 12, CODE_ETQ = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_02);
        initializeComponent();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            strR_TxId = extras.getString("Id_Tx");
            strR_NumOrden = extras.getString("NumOrden");
            strR_Cuenta = extras.getString("Cuenta");
            strR_Proveedor = extras.getString("Proveedor");
            intR_IdTipoMovimiento = extras.getInt("Id_TipoMovimiento");
            bolR_FlagPausa = extras.getBoolean("FlagPausa");
            intR_Id_Cliente = extras.getInt("Id_Cliente");
            tvNroOrden.setText(strR_NumOrden);
            tvProveedor.setText(strR_Proveedor);
            tvCuenta.setText(strR_Cuenta);
        }

        presenter = new ReciboTab02PresenterImpl(this);
        presenter.getDataDetailTx(strR_TxId);
    }

    void initializeComponent(){
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
        adapter = new RVReciboTab02Adapter(this, lstDetail);
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
            tvCountTx.setText(String.valueOf(adapter.baseData.size()));
            return false;
        }
    };

    Integer getSaldoRecibo(ArrayList<ListarDetalleTx> list){
        Integer total = 0;

        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getSaldo().isNaN()){
                if (list.get(i).getSaldo() > 0.0){
                    total++;
                }
            }
        }
        return total;
    }

    View.OnClickListener btnCloseTxOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (intR_IdTipoMovimiento.equals(0)){
                Toast.makeText(Recibo_Tab_02Activity.this, "Esta transacción no tiene tipo de movimiento", Toast.LENGTH_SHORT).show();
                return;
            }else{
                final Integer int_SaldoCierre = getSaldoRecibo(lstDetail);
                String message = "";

                if (int_SaldoCierre > 0){
                    message = "Existen " + int_SaldoCierre + " productos con saldo pendiente ¿Está seguro de cerrar la transacción?";
                }else {
                    message = "¿Está seguro de cerrar la transacción?";
                }

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Recibo_Tab_02Activity.this);
                alertDialogBuilder.setMessage(message);
                alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //presenter.getCerrarRecepcion(strR_TxId,(int_SaldoCierre > 0 ? 6 : 5), "ADMIN");
                        presenter.getCerrarRecepcion("123456789987",99, "ADMIN");
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
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
            menu.findItem(R.id.itemRegInci).setVisible(true);
            menu.findItem(R.id.itemEtiqImpr).setVisible(false);
            menu.findItem(R.id.itemSelectImpr).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.itemBack:
                //presenter.goBackToMenu();
                presenter.navigateToReciboTab01();
                return true;
            case R.id.itemRefresh:
                presenter.getDataDetailTx(strR_TxId);
                return true;
            case R.id.itemRegInci:
                presenter.showDialogIncidencia(strR_TxId, strR_NumOrden, bolR_FlagPausa, strR_Cuenta, strR_Proveedor, intR_IdTipoMovimiento);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

    @Override
    public void showResultCerrarRecepcion(Mensaje message) {
        Toast.makeText(this, "Cerrar recepcion:"+ message.errNumber + " - " + message.message, Toast.LENGTH_SHORT).show();
        presenter.getDataDetailTx(strR_TxId);
    }

    @Override
    public void sourceDataDetailTx(ArrayList<ListarDetalleTx> list) {
        lstDetail.clear();
        lstDetail = list;
        adapter.setBaseData(lstDetail);
        tvCountTx.setText(String.valueOf(adapter.baseData.size()));
    }

    @Override
    public void showFailureRequest(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogImpresora() {
        FragmentManager fm = getSupportFragmentManager();
        ImpresoraFragment frm = new ImpresoraFragment();
        frm.show(fm, "fragment_Impresora");
    }

    @Override
    public void showDialogIncidencia(String strId_Tx, String strNumOrden, Boolean bolFlagPausa, String strCuenta, String strProveedor, Integer intId_TipoMovimiento) {
        FragmentManager fm = getSupportFragmentManager();
        IncidenciaFragment frm = new IncidenciaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Id_Tx", strId_Tx);
        bundle.putString("Nro_Orden", strNumOrden);
        bundle.putBoolean("FlagPausa", bolFlagPausa);
        bundle.putString("Cuenta", strCuenta);
        bundle.putString("Proveedor", strProveedor);
        bundle.putInt("Id_TipoMovimiento", intId_TipoMovimiento);
        bundle.putInt("moduloTab", 12);
        frm.setArguments(bundle);
        frm.show(fm, "fragment_Incidencia");
    }

    @Override
    public void navigateToReciboTab01() {
         Intent intent = new Intent();
         intent.putExtra("key", 369);
         setResult(RESULT_OK, intent);
         finish();
    }


    @Override
    public void navigateToReciboTab03(ListarDetalleTx ent, String strNumOrden, Integer intIdTipoMovimiento, Boolean bolAutomatic, Boolean bolFlagPausa) {
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
        intent.putExtra("bolAutomatic", bolAutomatic);
        intent.putExtra("FlagPausa", bolFlagPausa);
        //startActivity(intent);
        startActivityForResult(intent, CODE_TAB_02);
    }

    @Override
    public void navigateToEtqCajaLpn(ListarDetalleTx detail, String strCuenta, String strNroOrden, Integer intId_Cliente, Integer intId_TipoMovimiento, Integer intId_CuentaLPN) {
        Intent intent = new Intent(this, EtqCajaLpnActivity.class);
        intent.putExtra("LoteLab", detail.getLote());
        intent.putExtra("Id_Producto", detail.getId_Producto());
        intent.putExtra("Id_UM", detail.getId_UM());
        intent.putExtra("CantidadPedida", detail.getCantidadPedida());
        intent.putExtra("Codigo", detail.getCodigo());
        intent.putExtra("Articulo", detail.getDescripcion());
        intent.putExtra("UM", detail.getUM());
        intent.putExtra("Cliente", strCuenta);
        intent.putExtra("UM_Base", detail.getUMBase());
        intent.putExtra("TipoAlmacenaje", detail.getTipoAlmacenaje());
        intent.putExtra("Item", detail.getItem());
        intent.putExtra("Acceso", 0);
        intent.putExtra("NroDoc", strNroOrden);
        intent.putExtra("FecEmi", (detail.getFechaEmision() != null) ? detail.getFechaEmision(): new Date());
        intent.putExtra("FecVen", (detail.getFechaVencimiento() != null) ? detail.getFechaVencimiento(): new Date());
        intent.putExtra("FlagSerie", detail.getFlagSeriePT());
        intent.putExtra("FlagLote", detail.getFlagLotePT());
        intent.putExtra("CondicionAlmac", detail.getCondicionAlmacenamiento());
        intent.putExtra("Condicion", (detail.getCondicion() == null) ? "DISPONIBLE" : detail.getCondicion());
        intent.putExtra("Id_Condicion", detail.getId_Condicion());
        intent.putExtra("Id_Cliente", intId_Cliente);
        intent.putExtra("idTipoMovimiento", intId_TipoMovimiento);
        intent.putExtra("IdCuentaLPN", intId_CuentaLPN);
        intent.putExtra("Id_SubAlmacen", detail.getId_SubAlmacen());
        startActivityForResult(intent, CODE_ETQ);
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
    public void onClickbtnNext(ListarDetalleTx ent) {
        presenter.navigateToReciboTab03(ent, strR_NumOrden, intR_IdTipoMovimiento, chkAutomatic.isChecked(), bolR_FlagPausa);
    }

    @Override
    public void onClickbtnEtq(ListarDetalleTx ent) {
        presenter.navigateToEtqCajaLpn(ent, strR_Cuenta, strR_NumOrden, intR_Id_Cliente, intR_IdTipoMovimiento, intR_Id_Cliente);
    }

    @Override
    public void onCompleteEditDialog(String strId_Tx, String strNumOrden, Boolean bolFlagPausa, String strCuenta, String strProveedor, Integer intId_TipoMovimiento, Integer intId_Cliente) {
        presenter.navigateToReciboTab01();
    }

    @Override
    public void onBackPressed() {
        // Write your code here
        //super.onBackPressed();
        presenter.navigateToReciboTab01();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CODE_TAB_02){
            Integer x = data.getExtras().getInt("key");
            presenter.getDataDetailTx(strR_TxId);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
