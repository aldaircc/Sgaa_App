package com.example.acosetito.sgaa.ui.Recibo.Tab_03;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarDetalleTx;
import com.example.acosetito.sgaa.data.Model.Recepcion.TxUbicacion;
import com.example.acosetito.sgaa.data.Model.Recepcion.UA;
import com.example.acosetito.sgaa.data.Model.Recepcion.UAXProductoTxA;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Etiqueta.EtqCajaLpnActivity;
import com.example.acosetito.sgaa.ui.Fragments.Impresora.ImpresoraFragment;
import com.example.acosetito.sgaa.ui.Fragments.Incidencia.IncidenciaFragment;
import com.example.acosetito.sgaa.ui.Recibo.Tab_01.Recibo_Tab_01Activity;
import com.example.acosetito.sgaa.ui.Recibo.Tab_04.Recibo_Tab_04Activity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;

public class Recibo_Tab_03Activity extends AppCompatActivity implements ReciboTab03View, IncidenciaFragment.IncidenciaDialogListener{

    Button btnBack, btnSave, btnDetail;
    TextView tvCodProd, tvUM, tvDescripcion, tvCantPedida, tvCantTotalRecibida, tvSaldo, tvBultos, tvFecEmi, tvFecVenci, tvLote;
    EditText edtCantRecibida, edtCodBar, edtAveriado, edtObserv;
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    ReciboTab03Presenter presenter;
    LinearLayout lySection03;

    /** Intent Values **/
    ListarDetalleTx objReceived;
    Integer intId_TipoMovimiento, intId_Cliente;
    String strNumOrden, strCuenta;
    Boolean bolAutomatic = false, bolFlagPausa = false;
    private final int CODE_TAB_03 = 13;

    /** Local variables **/
    DecimalFormat decFormatt = new DecimalFormat("0.000");
    Double _cantidad = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_03);
        initializeComponent();

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
            bolFlagPausa = extras.getBoolean("FlagPausa");
            strCuenta = extras.getString("Cuenta");
            intId_Cliente = extras.getInt("Id_Cliente");

            tvCodProd.setText(objReceived.getCodigo());
            tvUM.setText(objReceived.getUM());
            tvDescripcion.setText(objReceived.getDescripcion());
            tvCantPedida.setText(decFormatt.format(objReceived.getCantidadPedida()));//String.format("%.3f", objReceived.getCantidadPedida()));
            //edtCantRecibida.setText(String.valueOf(objReceived.getCantidadOperacion()));
            tvSaldo.setText(decFormatt.format(objReceived.getSaldo()));//String.valueOf(objReceived.getSaldo()));
            //edtBultos.setText();
            tvFecEmi.setText(formatter.format(objReceived.getFechaEmision()));
            tvFecVenci.setText(formatter.format(objReceived.getFechaVencimiento()));
            tvLote.setText(objReceived.getLote());
        }

        presenter = new ReciboTab03PresenterImpl(this);
        presenter.getUAsProductoTx(objReceived.getId_Tx(), objReceived.getId_Producto(), objReceived.getItem());
    }

    void initializeComponent(){

        tvCodProd = (TextView)findViewById(R.id.tvCodProd);
        tvUM = (TextView)findViewById(R.id.tvUM);
        tvDescripcion = (TextView)findViewById(R.id.tvDescripcion);
        tvCantPedida = (TextView) findViewById(R.id.tvCantPedida);
        edtCantRecibida = (EditText)findViewById(R.id.edtCantRecibida);
        tvSaldo = (TextView)findViewById(R.id.tvSaldo);
        tvBultos = (TextView)findViewById(R.id.tvBultos);
        tvFecEmi = (TextView)findViewById(R.id.tvFecEmi);
        tvFecVenci = (TextView)findViewById(R.id.tvFecVenci);
        tvLote = (TextView)findViewById(R.id.tvLote);
        edtCodBar = (EditText)findViewById(R.id.edtCodBar);
        //edtCodBar.setOnKeyListener(onKeyListenerCodBar);
        edtCodBar.addTextChangedListener(textWatcher_CodBar);
        tvCantTotalRecibida = (TextView)findViewById(R.id.tvCantTotalRecibida);
        edtAveriado = (EditText)findViewById(R.id.edtAveriado);
        edtObserv = (EditText)findViewById(R.id.edtObserv);

        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(btnBackOnClickListener);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(btnSaveOnClickListener);
        btnDetail = (Button)findViewById(R.id.btnDetail);
        btnDetail.setOnClickListener(btnDetailOnClickListener);

        lySection03 = (LinearLayout)findViewById(R.id.lySection03);
    }

    //region Show menu and options

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recibo, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        if (Build.VERSION.SDK_INT > 11){
            invalidateOptionsMenu();
            menu.findItem(R.id.itemBack).setVisible(true);
            menu.findItem(R.id.itemRefresh).setVisible(true);
            menu.findItem(R.id.itemPallet).setVisible(false);
            menu.findItem(R.id.itemEtiqImpr).setVisible(true);
            menu.findItem(R.id.itemRegInci).setVisible(true);
            menu.findItem(R.id.itemSelectImpr).setEnabled(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.itemBack:
                presenter.navigateToReciboTab02();
                return true;
            case R.id.itemPallet:
                //evaluateSaldo();
                return true;
            case R.id.itemRegInci:
                showDialogIncidencia();
                return true;
            case R.id.itemEtiqImpr:
                presenter.navigateToEtqCajaLpn(objReceived, strCuenta, strNumOrden, intId_Cliente,intId_TipoMovimiento, intId_Cliente);
                return true;
            case R.id.itemSelectImpr:
                presenter.showDialogImpresora();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

    TextWatcher textWatcher_CodBar = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.i("beforeTextChanged", charSequence.toString());
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.i("onTextChanged", charSequence.toString());
        if (charSequence.length() >= 12){
            presenter.validateEmptyBarCode(edtCodBar.getText().toString());
        }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        Log.i("afterTextChanged", editable.toString());
        }
    };

    View.OnClickListener btnBackOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //presenter.validateEmptyBarCode(edtCodBar.getText().toString());
            presenter.navigateToReciboTab02();
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

    Double sumUaDetalle(List<UAXProductoTxA> list){
        Double total = 0.0;
        for (UAXProductoTxA obj: list) {
            total += obj.getCantidad();
        }
        return  total;
    }

    void evaluateResultSave(Mensaje message){

        if (message.errNumber == 0){
            Double dSaldo, dValor1, dBultos;
            dSaldo = Double.parseDouble(tvSaldo.getText().toString());
            dValor1 = Double.parseDouble(message.valor1.toString());
            dBultos = Double.parseDouble(tvBultos.getText().toString()) + 1;
            tvSaldo.setText(decFormatt.format(dSaldo-dValor1));//String.format("%.3f", dSaldo - dValor1));
            dSaldo = (dSaldo - dValor1);
            tvBultos.setText(decFormatt.format(dBultos));
            tvCantTotalRecibida.setText(decFormatt.format(dBultos * dValor1));//String.format("%.3f", (dBultos * dValor1)));
            //TabPage1.BackColor = Color.GreenYellow;
            lySection03.setBackgroundResource(R.color.greenBottomBar);

            edtCodBar.requestFocus();
            edtCodBar.selectAll();
            edtAveriado.setText(decFormatt.format(0));//"0");
            edtCantRecibida.setText("");
            edtCodBar.setTag("");

            if (dSaldo == 0){
                Toast.makeText(this, "Item completo", Toast.LENGTH_SHORT).show();
                //CargarListaDetalleTransaccion
                //manejoPaneles(2); - In this case I will go to the next activity.
            }
        }else if (message.errNumber == 1){
            //TabPage1.BackColor = Color.Red;
            lySection03.setBackgroundResource(R.color.redColor);
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }else if (message.errNumber == -1){
            //TabPage1.BackColor = Color.Red;
            lySection03.setBackgroundResource(R.color.redColor);
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }else{
            //TabPage1.BackColor = Color.Red;
            lySection03.setBackgroundResource(R.color.redColor);
            Toast.makeText(this, "Operación fallida, intente otra vez...", Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }
    }

    @Override
    public void sourceDataUAsProducto(List<UAXProductoTxA> list) {

        if (list.size() > 0){
            Double totalCantidad = sumUaDetalle(list);
            Double cantidadPedida = Double.parseDouble(tvCantPedida.getText().toString());
            tvCantTotalRecibida.setText(decFormatt.format(totalCantidad)); //String.format("%.3f", totalCantidad));
            tvSaldo.setText(decFormatt.format(cantidadPedida - totalCantidad));//String.format("%.3f", cantidadPedida - totalCantidad));
            tvBultos.setText(decFormatt.format(list.size()));
        }else{
            tvCantTotalRecibida.setText(decFormatt.format(0));//"0");
            edtAveriado.setText(decFormatt.format(0));
            tvBultos.setText(decFormatt.format(0));
            edtCodBar.setText("");
            edtCodBar.requestFocus();
        }
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
                //btnOption.setEnabled(true);
                //TabPage1.BackColor = Color.Yellow;
                lySection03.setBackgroundResource(R.color.yellowColor);

                _cantidad = 1.0;
                edtCantRecibida.setText(decFormatt.format(_cantidad));//String.format("%.2f", _cantidad));
                edtCantRecibida.requestFocus();

                if (bolAutomatic){
                    //Execute event -> btnGuardar_Click(null, null);
                    saveTransaction();
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

            edtCantRecibida.setText(decFormatt.format(message.valor1));//String.format("%.2f", message.valor1));
            edtCodBar.setTag(edtCodBar.getText());
            edtCantRecibida.requestFocus();
            edtCantRecibida.selectAll();
            //TabPage1.BackColor = Color.Yellow;
            lySection03.setBackgroundResource(R.color.yellowColor);
            if (bolAutomatic){
                //btnGuardar_Click(null, null); -- Trabajar esta parte, crear e implementar el metodo.
                saveTransaction();
            }
        }else if(message.errNumber == 1){
            //TabPage1.BackColor = Color.Red;
            lySection03.setBackgroundResource(R.color.redColor);
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }else if (message.errNumber == -1){
            //TabPage1.BackColor = Color.Red;
            lySection03.setBackgroundResource(R.color.redColor);
            edtCodBar.setTag("");
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }else{
            //TabPage1.BackColor = Color.Red;
            lySection03.setBackgroundResource(R.color.redColor);
            edtCodBar.setTag("");
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }
    }

    @Override
    public void showResultValidateUARecibo(Mensaje message) {

        _cantidad = 0.0;

        if (message.errNumber == 0){

            if (_cantidad == 0){
                _cantidad = Double.parseDouble(message.valor1.toString());
            }

            //btnGuardar.Enabled = true;
            edtCantRecibida.setText(decFormatt.format(message.valor1));//String.format("%.2f",message.valor1));
            edtCodBar.setTag(edtCodBar.getText());
            edtCantRecibida.requestFocus();
            edtCantRecibida.selectAll();
            //TabPage1.BackColor = Color.Yellow;
            lySection03.setBackgroundResource(R.color.yellowColor);

            if (bolAutomatic){
                //btnGuardar_Click(null, null); -- Trabajar esta parte, crear e implementar el metodo.
                saveTransaction();
            }

        }else if (message.errNumber == 1){
            //TabPage1.BackColor = Color.Red;
            lySection03.setBackgroundResource(R.color.redColor);
            //btnGuardar.Enabled = true;
            edtCodBar.setTag("");
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();

        }else if (message.errNumber == -1){
            lySection03.setBackgroundResource(R.color.redColor);
            //btnGuardar.Enabled = true;
            edtCodBar.setTag("");
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }else{
            lySection03.setBackgroundResource(R.color.redColor);
            //btnGuardar.Enabled = true;
            edtCodBar.setTag("");
            Toast.makeText(this, message.message, Toast.LENGTH_SHORT).show();
            edtCodBar.requestFocus();
            edtCodBar.selectAll();
        }
    }

    View.OnClickListener btnSaveOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            saveTransaction();
        }
    };

    void saveTransaction(){
        if (!validateRegistro()){
            return;
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

        String result = "";
        objReceived.setTipoAlmacenaje((objReceived.getTipoAlmacenaje() == null) ? "0" : objReceived.getTipoAlmacenaje() );
        if (Integer.parseInt(objReceived.getTipoAlmacenaje()) != 3){
            presenter.registerUATransito(objTxUbi);
        }
    }

    Boolean validateRegistro() {
        boolean result = true;

        if (!btnSave.isEnabled()){
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
            if (!edtCodBar.getText().toString().trim().equals(edtCodBar.getTag().toString().trim())){
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
            edtAveriado.setText(decFormatt.format(0));
        }
        return result;
    }

    @Override
    public void showResultRegistrarUATransito(String result) {

        Double cantRetorno = Double.parseDouble(edtCantRecibida.getText().toString());
        Double cantRecibidaUA = Double.parseDouble(tvCantTotalRecibida.getText().toString());
        Double sumCantidad = cantRetorno + cantRecibidaUA;
        Double cantPedida = Double.parseDouble(tvCantPedida.getText().toString());

        Date fEmision = null, fVencimiento = null;
        try {
            fEmision = (tvFecEmi.getText().toString().trim()=="") ? null: formatter.parse(tvFecEmi.getText().toString());
            fVencimiento = (tvFecVenci.getText().toString().trim()=="") ? null: formatter.parse(tvFecVenci.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(_cantidad != 0){
            if (!String.format("%.2f", _cantidad).equals(String.format("%.2f", cantRetorno))){
                Toast.makeText(getApplicationContext(), "Esta cantidad no corresponde", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        UA objUA = new UA();
        objUA.setUA_CodBarra(edtCodBar.getText().toString());
        objUA.setId_Tx(objReceived.getId_Tx());
        objUA.setId_Producto(objReceived.getId_Producto());
        objUA.setLoteLab(objReceived.getLote());
        objUA.setSerie( (objReceived.getFlagSeriePT()) ? edtCodBar.getText().toString(): null);
        objUA.setFechaEmision(fEmision);
        objUA.setFechaVencimiento(fVencimiento);
        objUA.setCantidad(Double.parseDouble(edtCantRecibida.getText().toString()));
        objUA.setSaldo(Double.parseDouble(edtCantRecibida.getText().toString()));
        objUA.setCantidadAveriada(Double.parseDouble(edtAveriado.getText().toString()));
        objUA.setId_TerminalRF(1); //control.ParametrosLogeo.RF_ID
        objUA.setItem(objReceived.getItem());
        objUA.setId_Ubicacion(0);
        objUA.setId_Tx_Ubi((result == "") ? null: result);
        objUA.setObservacion(edtObserv.getText().toString());
        objUA.setUsuarioRegistro("ADMIN"); //Global.userName
        objUA.setId_Almacen(1); //Global.IdWarehouse
        objUA.setId_UM(objReceived.getId_UMB());
        objUA.setFlagAnulado(false);

        if (intId_TipoMovimiento == 0){
            Toast.makeText(getApplicationContext(), "Esta transacción no tiene tipo de movimiento", Toast.LENGTH_SHORT).show();
            return;
        }

        if (intId_TipoMovimiento == 11 || intId_TipoMovimiento == 13 || intId_TipoMovimiento == 14){
            presenter.registerUATransferencia(objUA);
        }else{
            presenter.registerUA(objUA);
        }
    }

    @Override
    public void showResultRegisterUA(Mensaje message) {
        evaluateResultSave(message);
    }

    @Override
    public void showResultRegisterUATransferencia(Mensaje message) {
        evaluateResultSave(message);
    }

    @Override
    public void showFailureRequest(String result) {
        Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener btnDetailOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.navigateToReciboTab04(objReceived, strNumOrden, intId_TipoMovimiento, bolAutomatic, Double.parseDouble(tvSaldo.getText().toString()));
        }
    };

    @Override
    public void navigateToReciboTab04(ListarDetalleTx ent, String strNroOrden, Integer intTipoMovimiento, Boolean bolAuto, Double currentSaldo) {
        Intent intent = new Intent(this, Recibo_Tab_04Activity.class);
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
        intent.putExtra("bolAutomatic", bolAuto);
        intent.putExtra("currentSaldo", currentSaldo);
        //startActivity(intent);
        startActivityForResult(intent, CODE_TAB_03);
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
    public void navigateToReciboTab02() {
        Intent intent = new Intent();
        intent.putExtra("key", 369);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void navigateToEtqCajaLpn(ListarDetalleTx detail, String strCuenta, String strNroOrden, Integer intId_Cliente, Integer intId_TipoMovimiento, Integer intId_CuentaLPN) {
        Intent intent = new Intent(Recibo_Tab_03Activity.this, EtqCajaLpnActivity.class);
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
        startActivityForResult(intent, 1);
    }

    @Override
    public void showDialogImpresora(){
        FragmentManager fm = getSupportFragmentManager();
        ImpresoraFragment frm = new ImpresoraFragment();
        frm.show(fm, "fragment_Impresora");
    }

    private void showDialogIncidencia(){
        FragmentManager fm = getSupportFragmentManager();
        IncidenciaFragment frm = new IncidenciaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Id_Tx",objReceived.getId_Tx());
        bundle.putString("Nro_Orden", strNumOrden);
        bundle.putBoolean("FlagPausa", bolFlagPausa);
        frm.setArguments(bundle);
        frm.show(fm, "fragment_Incidencia");
    }

    @Override
    public void onCompleteEditDialog(String strId_Tx, String strNumOrden, Boolean bolFlagPausa, String strCuenta, String strProveedor, Integer intId_TipoMovimiento, Integer Id_Cliente) {
        Intent intent = new Intent(this, Recibo_Tab_01Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        presenter.navigateToReciboTab02();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CODE_TAB_03){
            Integer x = data.getExtras().getInt("key");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
