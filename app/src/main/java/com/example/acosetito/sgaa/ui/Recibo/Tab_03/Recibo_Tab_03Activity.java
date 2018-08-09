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
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;
import com.example.acosetito.sgaa.data.Utilitario.Global;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;

public class Recibo_Tab_03Activity extends AppCompatActivity implements ReciboTab03View{

    Button btnOption, btnBack, btnSave;
    PopupMenu ddMenu;
    TextView tvCodProd, tvUM, tvDescripcion;
    EditText edtCantPedida, edtCantRecibida, edtCantTotalRecibida, edtSaldo, edtBultos, edtFecEmi, edtFecVenci, edtLote, edtCodBar, edtAveriado, edtObserv;
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    ReciboTab03Presenter presenter;

    /** Intent Values **/
    ListarDetalleTx objReceived;
    Integer intId_TipoMovimiento;
    String strNumOrden;
    Boolean bolAutomatic;

    /** Local variables **/
    Double _cantidad = 0.0;

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
            bolAutomatic = extras.getBoolean("bolAutomatic");

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
        //edtCodBar.setOnKeyListener(onKeyListenerCodBar);
        //edtCodBar.addTextChangedListener(textWatcher_CodBar);
        edtCantTotalRecibida = (EditText)findViewById(R.id.edtCantTotalRecibida);
        edtAveriado = (EditText)findViewById(R.id.edtAveriado);
        edtObserv = (EditText)findViewById(R.id.edtObserv);


        btnOption = (Button)findViewById(R.id.btnOption);
        btnOption.setOnClickListener(optionsOnClickListener);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(btnBackOnClickListener);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(btnSaveOnClickListener);

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

    View.OnClickListener btnBackOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.validateEmptyBarCode(edtCodBar.getText().toString());
        }
    };

    View.OnKeyListener onKeyListenerCodBar = new View.OnKeyListener() {

        @Override
        public boolean onKey(View view, int keyCode, KeyEvent event) {

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                // perform search
                Toast.makeText(getApplicationContext(), "MENSAJE", Toast.LENGTH_SHORT).show();
                //EditText edtContent = (EditText) view;
                //presenter.validateEmptyBarCode(edtContent.getText().toString());
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
            edtCodBar.requestFocus();
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
            edtCodBar.requestFocus();
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
            edtCodBar.requestFocus();
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

                edtCodBar.setTag(edtCodBar.getText());
                edtCodBar.setText(edtCodBar.getTag().toString());
                btnOption.setEnabled(true);
                //TabPage1.BackColor = Color.Yellow;

                _cantidad = 1.0;
                edtCantRecibida.setText(String.format("%.2f", _cantidad));
                edtCantRecibida.requestFocus();

                if (bolAutomatic){
                    //Execute event -> btnGuardar_Click(null, null);
                }

            }else{
                // Atender esto -> CantidadporEscaneo();

                if (intId_TipoMovimiento == 0){
                    Toast.makeText(this, "Esta transacción no tiene tipo de movimiento", Toast.LENGTH_SHORT).show();
                    return;
                }

                UA objUA;

                if(intId_TipoMovimiento == 11 || intId_TipoMovimiento == 13 || intId_TipoMovimiento == 14){
                    /** Recepción por transferencia **/
                    objUA = new UA();
                    objUA.setId_Tx(objReceived.getId_Tx());
                    objUA.setUA_CodBarra(edtCodBar.getText().toString());
                    objUA.setId_Producto(objReceived.getId_Producto());
                    objUA.setItem(objReceived.getItem());
                    objUA.setId_Almacen(1);//Global.IdWarehouse

                    presenter.validateUAReciboTransferencia(objUA);
                }else{
                    /** Recepcion por compra **/
                    objUA = new UA();
                    objUA.setUA_CodBarra(edtCodBar.getText().toString());
                    objUA.setId_Producto(objReceived.getId_Producto());
                    objUA.setItem(objReceived.getItem());
                    presenter.validateUARecibo(objUA);
                }
            }
        }
    }

    @Override
    public void showResultValidarUAReciboTransferencia(Mensaje message) {
        _cantidad = 0.0;

        if (message.errNumber == 0){

            if (_cantidad == 0){
                _cantidad = Double.parseDouble(message.valor1.toString());
            }

            edtCantRecibida.setText(String.format("%.2f", message.valor1));
            edtCodBar.setTag(edtCodBar.getText());
            edtCantRecibida.requestFocus();
            edtCantRecibida.selectAll();
            //TabPage1.BackColor = Color.Yellow;
            if (bolAutomatic){
                //btnGuardar_Click(null, null); -- Trabajar esta parte, crear e implementar el metodo.
            }
        }else if(message.errNumber == 1){
            //TabPage1.BackColor = Color.Red;
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }else if (message.errNumber == -1){
            //TabPage1.BackColor = Color.Red;
            edtCodBar.setTag("");
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }else{
            //TabPage1.BackColor = Color.Red;
            edtCodBar.setTag("");
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }
    }

    @Override
    public void showFailureValidateUAReciboTransferencia(String result) {

    }

    @Override
    public void showResultValidateUARecibo(Mensaje message) {

        _cantidad = 0.0;

        if (message.errNumber == 0){

            if (_cantidad == 0){
             _cantidad = Double.parseDouble(message.valor1.toString());
            }

            //btnGuardar.Enabled = true;
            edtCantRecibida.setText(String.format("%.2f",message.valor1));
            edtCodBar.setTag(edtCodBar.getText());
            edtCantRecibida.requestFocus();
            edtCantRecibida.selectAll();
            //TabPage1.BackColor = Color.Yellow;

            if (bolAutomatic){
                //btnGuardar_Click(null, null); -- Trabajar esta parte, crear e implementar el metodo.
            }

        }else if (message.errNumber == 1){
            //TabPage1.BackColor = Color.Red;
            //btnGuardar.Enabled = true;
            edtCodBar.setTag("");
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();

        }else if (message.errNumber == -1){
            //TabPage1.BackColor = Color.Red;
            //btnGuardar.Enabled = true;
            edtCodBar.setTag("");
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }else{
            //TabPage1.BackColor = Color.Red;
            //btnGuardar.Enabled = true;
            edtCodBar.setTag("");
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }
    }

    @Override
    public void showFailureValidateUARecibo(String result) {

    }

    View.OnClickListener btnSaveOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (!validateRegistro()){
                return;
            }


            Double cantRetorno = Double.parseDouble(edtCantRecibida.getText().toString());
            Double cantRecibidaUA = Double.parseDouble(edtCantTotalRecibida.getText().toString());
            Double sumCantidad = cantRetorno + cantRecibidaUA;
            Double cantPedida = Double.parseDouble(edtCantPedida.getText().toString());

            Date fEmision, fVencimiento;
            try {
                fEmision = (edtFecEmi.getText().toString().trim()=="") ? null: formatter.parse(edtFecEmi.getText().toString());
                fVencimiento = (edtFecVenci.getText().toString().trim()=="") ? null: formatter.parse(edtFecVenci.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            TxUbicacion objTxUbi = new TxUbicacion();
            objTxUbi.setTipoUbicacion(1);
            objTxUbi.setId_Producto(objReceived.getId_Producto());
            objTxUbi.setId_Ubicacion_Origen(0);
            objTxUbi.setId_Almacen(1); //(Global.IdWarehouse);
            objTxUbi.setId_Tx(objReceived.getId_Tx());
            objTxUbi.setPrioridad(10);
            objTxUbi.setObservacion(edtObserv.getText().toString());
            objTxUbi.setUsuarioModificacion("ADMIN"); //(Global.userName);

            if (Integer.parseInt(objReceived.getTipoAlmacenaje()) != 3){
                presenter.registerUATransito(objTxUbi);
            }

            /**
             * string result = string.Empty;

             if (tipoAlmacenaje != 3)
             {
             result = new GestionRecibosMovil().RegistrarUATransito(objTxUbi);
             }
             **/

        }
    };

    Boolean validateRegistro() {
        boolean result = true;

        if (btnSave.isEnabled()){
            result = false;
            return result;
        }

        if (edtCodBar.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Ingrese el código de barras", Toast.LENGTH_SHORT).show();
            edtCodBar.selectAll();
            edtCodBar.requestFocus();
            result = false;
            return  result;
        }

        if (edtCantRecibida.getText().toString().trim().isEmpty() || edtCantRecibida.getText().equals("0")){
            Toast.makeText(this, "Ingrese el cantidad", Toast.LENGTH_SHORT).show();
            edtCantRecibida.requestFocus();
            edtCantRecibida.selectAll();
            result = false;
            return  result;
        }

        if (!objReceived.getFlagSeriePT()){
            if (edtCodBar.getText().toString().trim() != edtCodBar.getTag().toString().trim()){
                Toast.makeText(this, "La UA no es correcta.", Toast.LENGTH_SHORT).show();
                edtCantRecibida.requestFocus();
                edtCantRecibida.selectAll();
                result = false;
                return result;
            }
        }

        if (intId_TipoMovimiento != 3){
            if (edtCodBar.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Ingrese código de barras UA.", Toast.LENGTH_SHORT).show();
                edtCodBar.requestFocus();
                edtCodBar.selectAll();
                result = false;
                return result;
            }
        }

        if (edtAveriado.getText().toString().trim().isEmpty()){
            edtAveriado.setText("0");
        }
        return result;
    }

    @Override
    public void showResultRegistrarUATransito(String result) {

    }

    @Override
    public void showFailureRegistrarUATransito(String result) {

    }
}
