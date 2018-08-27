package com.example.acosetito.sgaa.ui.Etiqueta;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.SPSubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Adapter.SPUAlternativa;
import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EtqCajaLpnActivity extends AppCompatActivity implements EtqView{

    TextView tvCodigo, tvArticulo, tvUM, tvTotalSuma, tvFE, tvFV;
    EditText edtLote, edtCantxEtq, edtNumEtq, edtCantEtqSaldo, edtNumCopias;
    CheckBox chkFE, chkFV;
    Spinner spnUAlter, spnFormato, spnSubAlmacen;
    Button btnImprimir, btnInventariar;
    DatePickerDialog datePickerDialog;

    Boolean findArticulo = false;
    String strEtq = "";
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


    /** Intent variables **/
    String strR_LoteLab, strR_Codigo, strR_Articulo, strR_UM,
            strR_Cliente, strR_UMBase, strR_TipoAlmacenaje, strR_NroDoc,
            strR_CondAlm, strR_Condicion;
    Integer intR_IdProducto, intR_IdUM, intR_Item, intR_Acceso, intR_IdCondicion,
            intR_IdCliente, intR_IdTipoMovimiento, intR_IdCuentaLPN, intR_IdSubAlmacen;
    Double dblR_CantPedida;
    Date dR_FecEmi, dR_FecVen;
    Boolean bolR_FlagSerie, bolR_FlagLote;

    EtqPresenter presenter;
    SPUAlternativa adpUAlterntiva;
    SPSubAlmacenXCuenta adpSubAlmacen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etq_caja_lpn);
        initializeComponent();
        getValueExtras();

        if (findArticulo == false){
            tvCodigo.setText(strR_Codigo);
            tvArticulo.setText(strR_Articulo);
            tvArticulo.setTag(intR_IdProducto);
            tvUM.setText(strR_UM);
            tvUM.setTag(intR_IdUM);
            edtLote.setText(strR_LoteLab);
            btnInventariar.setEnabled(false);
            btnInventariar.setVisibility(View.INVISIBLE);
            tvFE.setText(formatter.format(dR_FecEmi));
            tvFV.setText(formatter.format(dR_FecVen));

            presenter = new EtqPresenterImpl(this);
            presenter.listUMxProducto(intR_IdProducto);

            if (strR_TipoAlmacenaje == "1"){
                strEtq = "ETQ_UA.txt";
            }else if (strR_TipoAlmacenaje == "2"){
                strEtq = "ETQ_Pallet.txt";
            }else if (strR_TipoAlmacenaje == "3"){
                strEtq = "ETQ_UA.txt";
            }else{
                strEtq = "";
            }
            spnSubAlmacen.setEnabled(false);

        }else{

        }


        /**
         loadImpresora();
         ListarFormato();
         if (idTipoMovimiento == 4 || idTipoMovimiento == 3)
         ListarCausales();
         loadSubAlmacenes();
         **/

        if (intR_IdTipoMovimiento == 4 || intR_IdTipoMovimiento == 3) {
            //listarCausales();
        }

        presenter.listSubAlmacenesXCuenta(intR_IdCuentaLPN, 2);//Global.IdWarehouse);


        if (bolR_FlagLote)
        {
            edtLote.setEnabled(true);
            tvFE.setEnabled(true);
            tvFV.setEnabled(true);
            chkFE.setChecked(true);
            chkFV.setChecked(true);
            //btnGenerarLote.Enabled = true;
        }
        else
        {
            edtLote.setEnabled(false);
            tvFE.setEnabled(false);
            tvFV.setEnabled(false);
            edtLote.setText("");
            chkFE.setChecked(false);
            chkFV.setChecked(false);
            //btnGenerarLote.Enabled = false;
        }
    }

    void getValueExtras(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            strR_LoteLab = extras.getString("LoteLab");
            intR_IdProducto = extras.getInt("Id_Producto");
            intR_IdUM = extras.getInt("Id_UM");
            dblR_CantPedida = extras.getDouble("CantidadPedida");
            strR_Codigo = extras.getString("Codigo");
            strR_Articulo = extras.getString("Articulo");
            strR_UM = extras.getString("UM");
            strR_Cliente = extras.getString("Cliente");
            strR_UMBase = extras.getString("UM_Base");
            strR_TipoAlmacenaje = extras.getString("TipoAlmacenaje");
            intR_Item = extras.getInt("Item");
            intR_Acceso = extras.getInt("Acceso");
            strR_NroDoc = extras.getString("NroDoc");
            dR_FecEmi = (Date)extras.get("FecEmi");
            dR_FecVen = (Date)extras.get("FecVen");
            bolR_FlagSerie = extras.getBoolean("FlagSerie");
            bolR_FlagLote = extras.getBoolean("FlagLote");
            strR_CondAlm = extras.getString("CondicionAlmac");
            strR_Condicion = extras.getString("Condicion");
            intR_IdCondicion = extras.getInt("Id_Condicion");
            intR_IdCliente = extras.getInt("Id_Cliente");
            intR_IdTipoMovimiento = extras.getInt("idTipoMovimiento");
            intR_IdCuentaLPN = extras.getInt("IdCuentaLPN");
            intR_IdSubAlmacen = extras.getInt("Id_SubAlmacen");
        }
    }

    void initializeComponent(){
        tvCodigo = (TextView)findViewById(R.id.tvCodigo);
        tvArticulo = (TextView)findViewById(R.id.tvArticulo);
        tvUM = (TextView)findViewById(R.id.tvUM);
        tvTotalSuma = (TextView)findViewById(R.id.tvTotalSuma);
        edtLote = (EditText)findViewById(R.id.edtLote);
        tvFE = (TextView)findViewById(R.id.tvFE);
        tvFE.setOnClickListener(tvDateOnClickListener);
        tvFV = (TextView) findViewById(R.id.tvFV);
        tvFV.setOnClickListener(tvDateOnClickListener);
        edtCantxEtq = (EditText)findViewById(R.id.edtCantxEtq);
        edtCantxEtq.addTextChangedListener(textWatcherNumEtq);
        edtNumEtq = (EditText)findViewById(R.id.edtNumEtq);
        edtNumEtq.addTextChangedListener(textWatcherNumEtq);
        edtCantEtqSaldo = (EditText)findViewById(R.id.edtCantEtqSaldo);
        edtCantEtqSaldo.addTextChangedListener(textWatcherNumEtq);
        edtNumCopias = (EditText)findViewById(R.id.edtNumCopias);
        chkFE = (CheckBox)findViewById(R.id.chkFE);
        chkFV = (CheckBox)findViewById(R.id.chkFV);
        spnUAlter = (Spinner)findViewById(R.id.spnUAlter);
        spnUAlter.setOnItemSelectedListener(spnUAlterOnItemSelectedListener);
        spnFormato = (Spinner)findViewById(R.id.spnFormato);
        spnSubAlmacen = (Spinner)findViewById(R.id.spnSubAlmacen);
        btnImprimir = (Button)findViewById(R.id.btnImprimir);
        btnInventariar = (Button)findViewById(R.id.btnInventariar);
    }

    Double dbl_CantXBulto, fac;

    AdapterView.OnItemSelectedListener spnUAlterOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            try {

                ArrayList<UMxProducto> list = adpUAlterntiva.getItems();

                if (list.size() > 0){
                    UMxProducto obj = (UMxProducto) spnUAlter.getSelectedItem();
                    dbl_CantXBulto = obj.getCantXBulto();

                    if (!findArticulo){
                        fac = obj.getFactor();
                        Integer numEtqPrint = (int) (dblR_CantPedida / fac);
                        Double saldoEtq = dblR_CantPedida - (numEtqPrint * fac);
                        edtCantxEtq.setText(fac.toString());
                        edtNumEtq.setText(numEtqPrint.toString());
                        edtCantEtqSaldo.setText(String.format("%.2f",saldoEtq));
                    }else{
                        dbl_CantXBulto = obj.getCantXBulto();
                        edtCantxEtq.setText(dbl_CantXBulto.toString());
                        edtNumEtq.setText("0");
                        edtCantEtqSaldo.setText("0");
                    }

                }
            }catch (Exception e){

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    /**
     AdapterView.OnItemSelectedListener spnWareHouseOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    try {
    intIdWarehouse = (i == 0) ? 0 : lstWarehousex.get(i-1).getId_Almacen();
    strWarehouse = (i == 0) ? "" : lstWarehousex.get(i-1).getAlmacen();
    Global.indexIdWarehouse = i;
    Global.IdWarehouse = intIdWarehouse;
    Global.wareHouse = strWarehouse;
    }catch (Exception ex){

    }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    };
     **/

    TextWatcher textWatcherNumEtq = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(!charSequence.toString().equals("") ) {
                //do your work here
                Double cantXCaja, numEtqTem, etqSaldoCant;
                cantXCaja = Double.parseDouble((edtCantxEtq.getText().toString().equals("")) ? "0": edtCantxEtq.getText().toString());
                numEtqTem = Double.parseDouble((edtNumEtq.getText().toString().equals("")) ? "0": edtNumEtq.getText().toString());
                etqSaldoCant = Double.parseDouble(edtCantEtqSaldo.getText().toString().equals("") ? "0": edtCantEtqSaldo.getText().toString());
                tvTotalSuma.setText( String.format("%.2f", ((cantXCaja * numEtqTem) + etqSaldoCant)));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    View.OnClickListener tvDateOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View control) {
            final Calendar c = Calendar.getInstance();

            switch (control.getId()){
                case R.id.tvFE:

                    if (!chkFE.isChecked()){
                        return;
                    }

                    c.setTime(dR_FecEmi);
                    break;
                case R.id.tvFV:

                    if (!chkFV.isChecked()){
                        return;
                    }

                    c.setTime(dR_FecVen);
                    break;
                default:
                    break;
            }

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // date picker dialog
            datePickerDialog = new DatePickerDialog(EtqCajaLpnActivity.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            String dateSelect = ((dayOfMonth < 10) ? "0"+dayOfMonth : dayOfMonth) + "/" +
                                                (((monthOfYear + 1) < 10) ? "0"+ (monthOfYear + 1): monthOfYear) + "/"
                                                + year;
                            try {
                                if (control.getId() == R.id.tvFE){
                                    tvFE.setText(dateSelect);
                                    dR_FecEmi = formatter.parse(tvFE.getText().toString());
                                }else if (control.getId() == R.id.tvFV){
                                    tvFV.setText(dateSelect);
                                    dR_FecVen = formatter.parse(tvFV.getText().toString());
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }, year, month, day);
            datePickerDialog.show();
        }
    };

    @Override
    public void sourceUMxProducto(ArrayList<UMxProducto> list) {
        adpUAlterntiva = new SPUAlternativa(this, R.layout.spinner_ualternativa_item, list);
        spnUAlter.setPrompt("Seleccione presentación");
        spnUAlter.setAdapter(adpUAlterntiva);
    }

    @Override
    public void sourceSubAlmacenXCuenta(ArrayList<SubAlmacenXCuenta> list) {
        adpSubAlmacen = new SPSubAlmacenXCuenta(this, R.layout.spinner_item, list);
        spnSubAlmacen.setPrompt("Seleccione SubAlmacen");
        spnSubAlmacen.setAdapter(adpSubAlmacen);

        if (intR_IdSubAlmacen == 0){
            spnSubAlmacen.setSelection(0);
        }else{
            for(int i = 0; i < list.size(); i++){
                if (intR_IdSubAlmacen.equals(list.get(i).getId_SubAlmacen())){
                    spnSubAlmacen.setSelection(i);
                    break;
                }
            }
        }
    }

    @Override
    public void showFailureRequest(String result) {
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
}
