package com.example.acosetito.sgaa.ui.Recibo.Tab_03;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.DialogFragment;
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
import com.example.acosetito.sgaa.ui.Fragments.Impresora.ImpresoraFragment;
import com.example.acosetito.sgaa.ui.Fragments.Incidencia.IncidenciaFragment;
import com.example.acosetito.sgaa.ui.Recibo.Tab_04.Recibo_Tab_04Activity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;

public class Recibo_Tab_03Activity extends AppCompatActivity implements ReciboTab03View{

    Button btnBack, btnSave, btnDetail;
    TextView tvCodProd, tvUM, tvDescripcion, tvCantPedida, tvCantTotalRecibida, tvSaldo, tvBultos, tvFecEmi, tvFecVenci, tvLote;
    EditText edtCantRecibida, edtCodBar, edtAveriado, edtObserv;
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    ReciboTab03Presenter presenter;
    LinearLayout lySection03;

    /** Intent Values **/
    ListarDetalleTx objReceived;
    Integer intId_TipoMovimiento;
    String strNumOrden;
    Boolean bolAutomatic = false;

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

            tvCodProd.setText(objReceived.getCodigo());
            tvUM.setText(objReceived.getUM());
            tvDescripcion.setText(objReceived.getDescripcion());
            tvCantPedida.setText(String.format("%.3f", objReceived.getCantidadPedida()));
            //edtCantRecibida.setText(String.valueOf(objReceived.getCantidadOperacion()));
            tvSaldo.setText(String.valueOf(objReceived.getSaldo()));
            //edtBultos.setText();
            tvFecEmi.setText(formatter.format(objReceived.getFechaEmision()));
            tvFecVenci.setText(formatter.format(objReceived.getFechaVencimiento()));
            tvLote.setText(objReceived.getLote());
        }

        presenter = new ReciboTab03PresenterImpl(this);
        presenter.getUAsProductoTx(objReceived.getId_Tx(), objReceived.getId_Producto(), objReceived.getItem());
    }

    void initializeControls(){

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
        //edtCodBar.addTextChangedListener(textWatcher_CodBar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recibo, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        if (Build.VERSION.SDK_INT > 11){
            invalidateOptionsMenu();
            //menu.findItem(R.id.itemBulto).setVisible(true);
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
            case R.id.itemPallet:
                //evaluateSaldo();
                return true;
            case R.id.itemRegInci:
                showDialogIncidencia();
                return true;
            case R.id.itemSelectImpr:
                showDialogImpresora();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            tvSaldo.setText(String.format("%.3f", dSaldo - dValor1));
            dSaldo = (dSaldo - dValor1);
            tvBultos.setText(dBultos.toString());
            tvCantTotalRecibida.setText(String.format("%.3f", (dBultos * dValor1)));
            //TabPage1.BackColor = Color.GreenYellow;
            lySection03.setBackgroundResource(R.color.greenBottomBar);

            edtCodBar.requestFocus();
            edtCodBar.selectAll();
            edtAveriado.setText("0");
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
            tvCantTotalRecibida.setText(String.format("%.3f", totalCantidad));
            tvSaldo.setText(String.format("%.3f", cantidadPedida - totalCantidad));
            tvBultos.setText(String.valueOf(list.size()));
        }else{
            tvCantTotalRecibida.setText("0");
            edtAveriado.setText("0");
            tvBultos.setText("0");
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
                //btnOption.setEnabled(true);
                //TabPage1.BackColor = Color.Yellow;
                lySection03.setBackgroundResource(R.color.yellowColor);

                _cantidad = 1.0;
                edtCantRecibida.setText(String.format("%.2f", _cantidad));
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

            edtCantRecibida.setText(String.format("%.2f", message.valor1));
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

    @Override
    public void showFailureValidateUARecibo(String result) {

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
            edtAveriado.setText("0");
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
    public void showFailureRegistrarUATransito(String result) {

    }

    @Override
    public void showResultRegisterUA(Mensaje message) {
        evaluateResultSave(message);
    }

    @Override
    public void showFailureRegisterUA(String result) {

    }

    @Override
    public void showResultRegisterUATransferencia(Mensaje message) {
        evaluateResultSave(message);
    }

    @Override
    public void showFailureRegisterUATransferencia(String result) {

    }

    View.OnClickListener btnDetailOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            navigatoToBulto();
        }
    };

    @Override
    public void navigatoToBulto() {
        Intent intent = new Intent(this, Recibo_Tab_04Activity.class);
        intent.putExtra("Id_Tx", objReceived.getId_Tx());
        intent.putExtra("NumOrden", strNumOrden);
        intent.putExtra("Codigo",objReceived.getCodigo());
        intent.putExtra("Articulo", objReceived.getDescripcion());
        intent.putExtra("Id_Articulo", objReceived.getId_Producto());
        intent.putExtra("UM", objReceived.getUM());
        intent.putExtra("Id_UM", objReceived.getId_UM());
        intent.putExtra("Fecha_Emi", objReceived.getFechaEmision());
        intent.putExtra("Fecha_Venci",objReceived.getFechaVencimiento());
        intent.putExtra("Lote", objReceived.getLote());
        intent.putExtra("CantPedida", objReceived.getCantidadPedida());
        intent.putExtra("CantRecib", objReceived.getCantidadOperacion());
        intent.putExtra("Saldo", objReceived.getSaldo());
        intent.putExtra("Item", objReceived.getItem());
        intent.putExtra("Factor", objReceived.getFactor());
        intent.putExtra("FlagSeriePT", objReceived.getFlagSeriePT());
        intent.putExtra("Id_TipoMovimiento", intId_TipoMovimiento);
        intent.putExtra("bolAutomatic", bolAutomatic);
        intent.putExtra("currentSaldo", Double.parseDouble(tvSaldo.getText().toString()));
        startActivity(intent);
    }

    private void showDialogImpresora() {
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
        bundle.putBoolean("Pausa", true);
        frm.setArguments(bundle);
        frm.show(fm, "fragment_Incidencia");
    }
}
