package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Model.Impresora.Etiqueta;
import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Fragments.Impresora.ImpresoraFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recibo_Tab_05Activity extends AppCompatActivity implements ReciboTab05View{

    EditText edtEtqPallet;
    Button btnEtqPallet, btnRegister;
    ReciboTab05Presenter presenter;
    List<UAXProductoTxA> lstBulto;

    /** Intent Values **/
    ListarDetalleTx objReceived;
    Integer intId_TipoMovimiento;
    String strNumOrden;
    Boolean bolAutomatic = false;
    Double currentSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_05);
        initializedControls();
        getValueExtras();
        presenter = new ReciboTab05PresenterImpl(this);
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
            //setDataToControls(objReceived);
        }
    }

    void initializedControls(){
        edtEtqPallet = (EditText)findViewById(R.id.edtEtqPallet);
        btnEtqPallet = (Button)findViewById(R.id.btnEtqPallet);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnEtqPallet.setOnClickListener(etqPalletOnClickListener);
        btnRegister.setOnClickListener(registerOnClickListener);
    }

    View.OnClickListener etqPalletOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (Global.intId_Impresora == null){
                showDialogImpresora();
            }else{
                presenter.insertPallet(2,"ADMIN",1);//Global.IdWarehouse, Global.userName, Global.idCentro);
            }
        }
    };

    View.OnClickListener registerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.validatePallet(edtEtqPallet.getText().toString(), 2); //Global.IdWarehouse);
            /**if(edtEtqPallet.getText().length() != 0){
                presenter.validatePallet(edtEtqPallet.getText().toString(), 2); //Global.IdWarehouse);
            }**/
        }
    };

    @Override
    public void showResultValidatePallet(Mensaje message) {

        if (message.valor1 >= -1){
            /**
             if (palletcausal)
             {
             CerrarPalletCausal();
             }
             else
             {
             CerrarPallet();
             }
             txtEtqPallet3.Focus();
             **/

            presenter.getUAsProductoTx(objReceived.getId_Tx(), objReceived.getId_Producto(), objReceived.getItem());

        }else{
            Toast.makeText(this, "El pallet escaneado no existe", Toast.LENGTH_SHORT).show();
            edtEtqPallet.requestFocus();
            edtEtqPallet.selectAll();
        }
    }

    @Override
    public void showResultPrintEtq(Mensaje message) {
        if (message.errNumber == -1)
        {
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showResultInsertPallet(String result) {
        if (result.length() != 0){
            edtEtqPallet.setText(result);

            ArrayList<ListaEtiqueta> lstListEtq = new ArrayList<>();
            ListaEtiqueta objLE = new ListaEtiqueta();
            ArrayList<Etiqueta> lstEtq = new ArrayList<>();
            lstEtq.add(new Etiqueta("|ALMACEN|", "2"));
            lstEtq.add(new Etiqueta("|CODIGO|", String.valueOf(objReceived.getId_Producto())));
            lstEtq.add(new Etiqueta("|PRODUCTO|", objReceived.getDescripcion()));
            lstEtq.add(new Etiqueta("|CUENTA|", "LOQUE369"));
            lstEtq.add(new Etiqueta("|CODBARRA|", edtEtqPallet.getText().toString()));

            objLE.setEtiqueta(lstEtq);
            lstListEtq.add(objLE);
            presenter.printListaEtq(lstListEtq, "ETQ_PALLETS_UA.txt", Global.nameImpresora, true);
        }
    }

    @Override
    public void showResultRegisterPallet(Mensaje message) {
        if (message.errNumber == 0){
            Toast.makeText(this,"UA's registradas: "+ counterImpUA + " de " + lstBulto.size(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Error al momento de registrar las UA's", Toast.LENGTH_SHORT).show();
        }
        edtEtqPallet.setText("");
        edtEtqPallet.requestFocus();
    }

    @Override
    public void getBultos(List<UAXProductoTxA> list) {
        lstBulto = list;
        TxUbicacion objTxUbi = new TxUbicacion();
        objTxUbi.setTipoUbicacion(1);
        objTxUbi.setId_Producto(objReceived.getId_Producto());
        objTxUbi.setId_Ubicacion_Origen(0);
        objTxUbi.setId_Almacen(2);//Global.IdWarehouse);
        objTxUbi.setId_Tx(objReceived.getId_Tx());
        objTxUbi.setPrioridad(10);
        objTxUbi.setObservacion("");
        objTxUbi.setUsuarioModificacion("ADMIN");//Global.userName);
        presenter.registerUATransito(objTxUbi);
    }

    Integer counterImpUA = 0;

    @Override
    public void showResultRegisterUATransito(String result) {

        ArrayList<ImpUA> lstUbi = new ArrayList<>();
        for(UAXProductoTxA o: lstBulto){
            if (o.getContenedor() != null) continue;

            ImpUA objUA = new ImpUA();
            objUA.setUA_CodBarra(o.getUA_CodBarra());
            objUA.setPalletCodBarra(edtEtqPallet.getText().toString());
            objUA.setUsuarioRegistro("ADMIN");//Global.userName);
            objUA.setId_Tx_Ubi(result);
            objUA.setId_Almacen(2);//Global.IdWarehouse);
            lstUbi.add(objUA);
            counterImpUA++;
        }

        presenter.registerPallet(lstUbi);
    }

    @Override
    public void showFailureRequest(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToReciboTab04() {
        Intent intent = new Intent();
        intent.putExtra("key", 369);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showProgressDialog() {
        ProgressDialogRequest.show(this);
    }

    @Override
    public void hideProgressDialog() {
        ProgressDialogRequest.dismiss();
    }

    private void showDialogImpresora() {
        FragmentManager fm = getSupportFragmentManager();
        ImpresoraFragment alertDialog = ImpresoraFragment.newInstance();
        alertDialog.show(fm, "Fragment_alert");
    }

    @Override
    public void onBackPressed() {
        presenter.navigateToReciboTab04();
    }
}
