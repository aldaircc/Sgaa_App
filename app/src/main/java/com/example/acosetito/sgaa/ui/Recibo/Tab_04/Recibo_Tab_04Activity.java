package com.example.acosetito.sgaa.ui.Recibo.Tab_04;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVReciboTab04Adapater;
import com.example.acosetito.sgaa.data.Adapter.RVReciboTab04Adapter;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Fragments.Impresora.ImpresoraFragment;
import com.example.acosetito.sgaa.ui.Recibo.Tab_05.Recibo_Tab_05Activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Random;

public class Recibo_Tab_04Activity extends AppCompatActivity implements ReciboTab04View, IRVReciboTab04Adapater{

    TextView tvTx, tvCodArticulo, tvDescripArt;
    RecyclerView rclBulto;
    List<UAXProductoTxA> listBulto = new ArrayList<>();
    ReciboTab04Presenter presenter;
    RVReciboTab04Adapter adapter;

    /** Intent Values **/
    ListarDetalleTx objReceived;
    Integer intId_TipoMovimiento;
    String strNumOrden;
    Boolean bolAutomatic = false;
    Double currentSaldo;
    private final int CODE_TAB_04 = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_04);
        initializeComponent();
        getValueExtras();
        presenter = new ReciboTab04PresenterImpl(this);
        presenter.getBultosTx(objReceived.getId_Tx(), objReceived.getId_Producto(), objReceived.getItem());//"A20182040001", 2577, 1);
    }

    void getValueExtras(){
        Bundle extras = getIntent().getExtras();

        if (extras != null){
            objReceived = new ListarDetalleTx();
            objReceived.setId_Tx(extras.getString("Id_Tx"));
            objReceived.setCodigo(extras.getString("Codigo"));
            objReceived.setDescripcion(extras.getString("Articulo"));
            objReceived.setId_Producto(extras.getInt("Id_Articulo"));
            objReceived.setUM(extras.getString("UM"));
            objReceived.setId_UM(extras.getInt("Id_UM"));
            objReceived.setFechaEmision((Date) extras.get("Fecha_Emi"));
            objReceived.setFechaVencimiento((Date) extras.get("Fecha_Venci"));
            objReceived.setLote(extras.getString("Lote"));
            objReceived.setCantidadPedida(extras.getDouble("CantPedida"));
            objReceived.setCantidadOperacion(extras.getDouble("CantRecib"));
            objReceived.setSaldo(extras.getDouble("Saldo"));
            objReceived.setItem(extras.getInt("Item"));
            objReceived.setFactor(extras.getDouble("Factor"));
            objReceived.setFlagSeriePT(extras.getBoolean("FlagSeriePT"));
            intId_TipoMovimiento = extras.getInt("Id_TipoMovimiento");
            strNumOrden = extras.getString("NumOrden");
            bolAutomatic = extras.getBoolean("bolAutomatic");
            currentSaldo = extras.getDouble("currentSaldo");
            setDataToControls(objReceived);
        }
    }

    void initializeComponent(){
        tvTx = (TextView)findViewById(R.id.tvTx);
        tvCodArticulo = (TextView)findViewById(R.id.tvCodArticulo);
        tvDescripArt = (TextView)findViewById(R.id.tvDescripArt);
        rclBulto = (RecyclerView)findViewById(R.id.rclBultos);
        adapter = new RVReciboTab04Adapter(this, (ArrayList<UAXProductoTxA>) listBulto);
        rclBulto.setLayoutManager(new LinearLayoutManager(this));
        rclBulto.setItemAnimator(new DefaultItemAnimator());
        rclBulto.setAdapter(adapter);
    }

    void setDataToControls(ListarDetalleTx obj){
        tvTx.setText(obj.getId_Tx());
        tvCodArticulo.setText(obj.getCodigo());
        tvDescripArt.setText(obj.getDescripcion());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recibo, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Build.VERSION.SDK_INT > 11) {
            invalidateOptionsMenu();
            menu.findItem(R.id.itemPallet).setVisible(true);
            menu.findItem(R.id.itemRegInci).setVisible(false);
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
                presenter.navigateToReciboTab03();
                return true;
            case R.id.itemRefresh:
                presenter.getBultosTx(objReceived.getId_Tx(), objReceived.getId_Producto(), objReceived.getItem());
                return true;
            case R.id.itemPallet:
                evaluateSaldo();
                return true;
            case R.id.itemSelectImpr:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void updateDataSourceBulto(List<UAXProductoTxA> list) {
        listBulto = list;

        ArrayList<UAXProductoTxA> models = new ArrayList<>();

        for (UAXProductoTxA model : listBulto) {
            models.add(model.clone());
        }

        adapter.setBaseData(models);
    }

    @Override
    public void showFailureDataSourceBulto(String result) {

    }

    @Override
    public void getResultRegisterUATransferencia(Mensaje message) {

    }

    @Override
    public void showFailureRegisterUATransferencia(String result) {

    }

    @Override
    public void getResultRegisterUA(Mensaje message) {

    }

    @Override
    public void showFailureRegisterUA(String result) {

    }

    @Override
    public void navigateToReciboTab05(ListarDetalleTx ent, String strNroOrden, Integer intTipoMovimiento, Boolean bolAutomatic, Double saldo) {
        Intent intent = new Intent(this, Recibo_Tab_05Activity.class);
        intent.putExtra("Id_Tx", ent.getId_Tx());
        intent.putExtra("NumOrden", strNroOrden);
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
        intent.putExtra("Id_TipoMovimiento", intTipoMovimiento);
        intent.putExtra("bolAutomatic", bolAutomatic);
        intent.putExtra("currentSaldo", saldo);
        startActivityForResult(intent, CODE_TAB_04);
    }

    @Override
    public void navigateToReciboTab03() {
        Intent intent = new Intent();
        intent.putExtra("key", 369);
        setResult(RESULT_OK, intent);
        finish();
    }

    void evaluateSaldo(){
        if (currentSaldo > 0){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("¿Tiene un saldo pendiente, está seguro de cerrar el pallet?");
            alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //navigateToPrintEtqPallet();
                    presenter.navigateToReciboTab05(objReceived, strNumOrden, intId_TipoMovimiento, bolAutomatic, currentSaldo);
                }
            });
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else{
            //navigateToPrintEtqPallet();
            presenter.navigateToReciboTab05(objReceived, strNumOrden, intId_TipoMovimiento, bolAutomatic, currentSaldo);
        }
    }

    @Override
    public void onDeleteItem(final UAXProductoTxA ent) {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("¿Esta seguro de eliminar el registro?");
        alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(getApplicationContext(), "Entidad:" + ent.getUA(), Toast.LENGTH_SHORT).show();
                UA objUA = new UA();
                objUA.setUA_CodBarra(ent.getUA_CodBarra());
                objUA.setId_Tx(ent.getId_Tx());
                objUA.setId_Producto(ent.getId_Producto());
                objUA.setLoteLab(ent.getLoteLab());
                objUA.setFechaEmision(new Date());
                objUA.setFechaVencimiento(new Date());
                objUA.setFechaIngreso(new Date());
                objUA.setCantidad((double)ent.getCantidad());
                objUA.setSaldo((double) ent.getSaldoTotal()); //Saldo = Convert.ToDecimal(gridBulto.Rows[indice]["Cantidad"].ToString()),
                objUA.setCantidadAveriada((double)ent.getCantidadAveriada());
                objUA.setId_TerminalRF(1);//Global.Id_TerminalRF);
                objUA.setItem(objReceived.getItem());//1
                objUA.setUsuarioRegistro("ADMIN"); //Global.userName
                objUA.setId_Almacen(1);//Global.IdWarehouse);
                objUA.setFlagAnulado(true);
                //objUA.setObservacion("");
                deleteRow(objUA);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    void deleteRow(UA objUA){
        if (intId_TipoMovimiento.equals(0)){
            Toast.makeText(this, "Esta transacción no tiene tipo de movimiento", Toast.LENGTH_SHORT).show();
            return;
        }

        if (intId_TipoMovimiento == 11 || intId_TipoMovimiento == 13 || intId_TipoMovimiento == 14)
        {
            presenter.registerUATransferencia(objUA);
        }else{
            presenter.registerUA(objUA);
        }

        presenter.getBultosTx(objReceived.getId_Tx(), objReceived.getId_Producto(), objReceived.getItem());//"A20182040001", 2577, 1);
        /**

         int idArt = Convert.ToInt32(lblDesArticulo.Tag.ToString());

         if (gridBulto.SelectedCell.Selected)
         {
         int indice = gridBulto.SelectedCell.RowIndex;
         //--
         ProxySGAAMovil.SOAPRecibo.UA objUA = new ProxySGAAMovil.SOAPRecibo.UA()
         {

         };
         int index = lisRecTrans.FocusedItem.Index;
         int tipMov = (ltTransRecibo[index].Id_TipoMovimiento != null) ? Convert.ToInt16(ltTransRecibo[index].Id_TipoMovimiento) : 0;
         if (tipMov == 0)
         {
         MessageBox.Show("Esta transacción no tiene tipo de movimiento", "Aviso");
         return;
         }

         var msj = new ProxySGAAMovil.SOAPRecibo.Mensaje();
         if (tipMov == 11 || tipMov == 13 || tipMov == 14)
         {
         msj = new GestionRecibosMovil().RegistrarUATransferencia(objUA);
         }
         else
         {
         msj = new GestionRecibosMovil().RegistrarUA(objUA);
         }

         //--

         List<uaRecibida> listUARecibida = new List<uaRecibida>();
         listUARecibida = (gridBulto.DataSource as List<uaRecibida>);
         listUARecibida.RemoveAt(gridBulto.SelectedCell.RowIndex);
         gridBulto.DataSource = listUARecibida;

         txtCantTotalRecibida.Text = SUMA_UA_DETALLE(idArt).ToString();
         txtSaldo.Text = (Convert.ToDouble(txtCantPedida.Text) - SUMA_UA_DETALLE(idArt)).ToString();
         txtBultos.Text = gridBulto.Rows.Count.ToString();
         }

         **/
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
    public void onBackPressed() {
        presenter.navigateToReciboTab03();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CODE_TAB_04){
            Integer x = data.getExtras().getInt("key");
            presenter.getBultosTx(objReceived.getId_Tx(), objReceived.getId_Producto(), objReceived.getItem());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
