package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import android.app.ProgressDialog;
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
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Fragments.ImpresoraFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recibo_Tab_05Activity extends AppCompatActivity implements ReciboTab05View{

    EditText edtEtqPallet;
    Button btnEtqPallet, btnRegister;
    ReciboTab05Presenter presenter;

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
                /**
                 codBarPallet = new GestionRecibosMovil().CrearEtqContenedor(Convert.ToInt32(lblAlmacen.Tag), lblUser.Tag.ToString(), control.Global.IdCentro);
                 txtEtqPallet3.Text = codBarPallet;

                 //---- imprimir------
                 GestionImpresionesMovil print = new GestionImpresionesMovil();
                 List<ProxySGAAMovil.SOAPImpresiones.ListaEtiquetas> lstLisEtq = new List<ProxySGAAMovil.SOAPImpresiones.ListaEtiquetas>();

                 ProxySGAAMovil.SOAPImpresiones.ListaEtiquetas LE = new ProxySGAAMovil.SOAPImpresiones.ListaEtiquetas();
                 LE.etiqueta = new ProxySGAAMovil.SOAPImpresiones.Etiqueta[]{
                 new ProxySGAAMovil.SOAPImpresiones.Etiqueta() { campo = "|ALMACEN|", valor = control.Global.Almacen },
                 new ProxySGAAMovil.SOAPImpresiones.Etiqueta() { campo = "|CODIGO|", valor = lblCodArticulo2.Text  },
                 new ProxySGAAMovil.SOAPImpresiones.Etiqueta() { campo = "|PRODUCTO|", valor = lblDesArticulo.Text.Trim()   },
                 new ProxySGAAMovil.SOAPImpresiones.Etiqueta() { campo = "|CUENTA|", valor = lblCliente.Tag.ToString() },
                 new ProxySGAAMovil.SOAPImpresiones.Etiqueta() { campo = "|CODBARRA|", valor = codBarPallet }
                 };
                 lstLisEtq.Add(LE);
                 var objMsj = print.imprimirListaEtiquetas(lstLisEtq, "ETQ_PALLETS_UA.txt", nomImp, true);
                 if (objMsj.errNumber == -1)
                 {
                 MessageBox.Show(objMsj.message.ToString(), "Error");
                 }
                 **/
            }
        }
    };

    View.OnClickListener registerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.validatePallet(edtEtqPallet.getText().toString(), 2); //Global.IdWarehouse);
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
        }else{
            Toast.makeText(this, "El pallet escaneado no existe", Toast.LENGTH_SHORT).show();
            edtEtqPallet.requestFocus();
            edtEtqPallet.selectAll();
        }
        /**
         if (msjValida.valor1 >= -1)
         {
         if (palletcausal)
         {
         CerrarPalletCausal();
         }
         else
         {
         CerrarPallet();
         }

         txtEtqPallet3.Focus();
         }
         else
         {
         MessageBox.Show("El pallet escaneado no existe",
         "Error", MessageBoxButtons.OK, MessageBoxIcon.Hand, MessageBoxDefaultButton.Button1);
         txtEtqPallet3.Focus();
         txtEtqPallet3.SelectAll();
         }
         **/
    }

    @Override
    public void showResultPrintEtq(Mensaje message) {
        Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailurePrintEtq(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailureValidatePallet(String result) {

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
    public void showFailureInsertPallet(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
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
}
