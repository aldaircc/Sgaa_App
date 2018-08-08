package com.example.acosetito.sgaa.ui.Recibo.Tab_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class Recibo_Tab_03Activity extends AppCompatActivity implements ReciboTab03View{

    Button btnOption;
    PopupMenu ddMenu;
    TextView tvCodProd, tvUM, tvDescripcion;
    EditText edtCantPedida, edtCantRecibida, edtSaldo, edtBultos, edtFecEmi, edtFecVenci, edtLote, edtCodBar, edtCantidad, edtAveriado, edtObserv;
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    ReciboTab03Presenter presenter;

    /** Intent Values **/
    ListarDetalleTx objReceived;
    Integer intId_TipoMovimiento;
    String strNumOrden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_03);
        initializeControls();

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
            objReceived.setFechaVencimiento((Date) extras.get("Fecha_Emi"));
            objReceived.setLote(extras.getString("Lote"));
            objReceived.setCantidadPedida(extras.getDouble("CantPedida"));
            objReceived.setCantidadOperacion(extras.getDouble("CantRecib"));
            objReceived.setSaldo(extras.getDouble("Saldo"));
            objReceived.setItem(extras.getInt("Item"));
            objReceived.setFactor(extras.getDouble("Factor"));
            objReceived.setFlagSeriePT(extras.getBoolean("FlagSeriePT"));
            intId_TipoMovimiento = extras.getInt("Id_TipoMovimiento");
            strNumOrden = extras.getString("NumOrden");

            tvCodProd.setText(objReceived.getCodigo());
            tvUM.setText(objReceived.getUM());
            tvDescripcion.setText(objReceived.getDescripcion());
            edtCantPedida.setText(String.valueOf(objReceived.getCantidadPedida()));
            edtCantRecibida.setText(String.valueOf(objReceived.getCantidadOperacion()));
            edtSaldo.setText(String.valueOf(objReceived.getSaldo()));
            //edtBultos.setText();
            edtFecEmi.setText(formatter.format(objReceived.getFechaEmision()));
            edtFecVenci.setText(formatter.format(objReceived.getFechaVencimiento()));
            edtLote.setText(objReceived.getLote());
        }

        presenter = new ReciboTab03PresenterImpl(this);
        presenter.getUAsProductoTx(objReceived.getId_Tx(), objReceived.getId_Producto(), objReceived.getItem());
    }

    void initializeControls(){

        tvCodProd = (TextView)findViewById(R.id.tvCodProd);
        tvUM = (TextView)findViewById(R.id.tvUM);
        tvDescripcion = (TextView)findViewById(R.id.tvDescripcion);
        edtCantPedida = (EditText)findViewById(R.id.edtCantPedida);
        edtCantRecibida = (EditText)findViewById(R.id.edtCantRecibida);
        edtSaldo = (EditText)findViewById(R.id.edtSaldo);
        edtBultos = (EditText)findViewById(R.id.edtBultos);
        edtFecEmi = (EditText)findViewById(R.id.edtFecEmi);
        edtFecVenci = (EditText)findViewById(R.id.edtFecVenci);
        edtLote = (EditText)findViewById(R.id.edtLote);
        edtCodBar = (EditText)findViewById(R.id.edtCodBar);
        edtCodBar.setOnKeyListener(onKeyListenerCodBar);
        //edtCodBar.addTextChangedListener(textWatcher_CodBar);

        edtCantidad = (EditText)findViewById(R.id.edtCantidad);
        edtAveriado = (EditText)findViewById(R.id.edtAveriado);
        edtObserv = (EditText)findViewById(R.id.edtObserv);


        btnOption = (Button)findViewById(R.id.btnOption);
        btnOption.setOnClickListener(optionsOnClickListener);

        ddMenu = new PopupMenu(getApplicationContext(), btnOption);
        ddMenu.getMenuInflater().inflate(R.menu.drop_down_menu, ddMenu.getMenu());
        ddMenu.setOnMenuItemClickListener(onMenuItemClickListener);
    }


    /**TextWatcher textWatcher_CodBar = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.i("beforeTextChanged", charSequence.toString());
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.i("onTextChanged", charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.i("afterTextChanged", editable.toString());
        }
    };**/

    View.OnKeyListener onKeyListenerCodBar = new View.OnKeyListener() {

        @Override
        public boolean onKey(View view, int keyCode, KeyEvent event) {
            EditText edtContent = (EditText) view;
            if (event.getAction()==KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                //do something here
                presenter.validateEmptyBarCode(edtContent.getText().toString());
                return true;
            }
            return false;
        }
    };

    View.OnClickListener optionsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ddMenu.show();
        }
    };

    PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            Toast.makeText(getApplicationContext(), "You have clicked "+ menuItem.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    Double sumUaDetalle(List<UAXProductoTxA> list){
        Double total = 0.0;
        for (UAXProductoTxA obj: list) {
            total += obj.getCantidad();
        }
        return  total;
    }

    @Override
    public void sourceDataUAsProducto(List<UAXProductoTxA> list) {

        if (list.size() > 0){
            Double totalCantidad = sumUaDetalle(list);
            Double cantidadPedida = Double.parseDouble(edtCantPedida.getText().toString());
            edtCantRecibida.setText(String.format("%.3f", totalCantidad));
            edtSaldo.setText(String.format("%.3f", cantidadPedida - totalCantidad));
            edtBultos.setText(list.size());
        }else{
            edtCantRecibida.setText("0");
            edtAveriado.setText("0");
            edtBultos.setText("0");
            edtCodBar.setText("");
            edtCodBar.hasFocus();
        }
    }

    @Override
    public void showFailureUAXProduco(String result) {

    }

    @Override
    public void showResultValidateReciboTransferSerie(Mensaje message) {

        if (message.errNumber != 0){
            //Background de color Rojo
            //Deshabilitar el boton de guardar
            edtCantRecibida.setText("");
            edtCodBar.setText("");
            edtCodBar.setFocusable(true);
            Toast.makeText(getApplicationContext(), message.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showFailureValidateReciboTransferSerie(String result) {

    }

    @Override
    public void showResultValidateReciboSerie(Mensaje message) {
        if (message.errNumber != 0){
            //BackGroundColor red
            //Deshabilitar boton guardar.
            edtCodBar.setText("");
            edtCantRecibida.setText("");
            edtCodBar.setFocusable(true);
            Toast.makeText(getApplicationContext(), message.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showFailureValidateReciboSerie(String result) {

    }

    @Override
    public void showMessageValidationBarCode(String result) {
        if (result.trim().isEmpty()){
            if (objReceived.getFlagSeriePT() == true){

                if(intId_TipoMovimiento == 0){
                    Toast.makeText(getApplicationContext(), "Esta transaccióm no tiene tipo de movimeinto", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(intId_TipoMovimiento == 11 || intId_TipoMovimiento == 13 || intId_TipoMovimiento == 14){
                    presenter.validateReciboTransferSerie(strNumOrden, edtCodBar.getText().toString(), objReceived.getItem());
                }else{
                    presenter.validateReciboSerie(edtCodBar.getText().toString(), objReceived.getId_Tx(), objReceived.getId_Producto());
                }

                /**
                 Debe suceder aqui lo siguiente:

                 txtBarrasCaja.Tag = txtBarrasCaja.Text;
                 txtBarrasCaja.Text = txtBarrasCaja.Tag.ToString();
                 btnGuardar.Enabled = true;
                 TabPage1.BackColor = Color.Yellow;

                 _cantidad = 1;
                 txtCantidadRecibida.Text = _cantidad.ToString();

                 txtCantidadRecibida.Focus();
                 txtCantidadRecibida.SelectAll();
                 if (chkAuto.Checked)
                 {
                 btnGuardar_Click(null, null);
                 }

                 **/

            }else{
                // Atender esto -> CantidadporEscaneo();
            }
        }
    }
}
