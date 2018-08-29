package com.example.acosetito.sgaa.ui.Etiqueta;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.SPFormatLabel;
import com.example.acosetito.sgaa.data.Adapter.SPSubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Adapter.SPUAlternativa;
import com.example.acosetito.sgaa.data.Model.Impresora.Etiqueta;
import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;
import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Fragments.Impresora.ImpresoraFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

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
    SPFormatLabel adpFormat;
    SPSubAlmacenXCuenta adpSubAlmacen;
    Double dbl_Residuo = 0.0;

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
            prepareFormatLabel();

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
        chkFE.setOnCheckedChangeListener(chkChanged);
        chkFV = (CheckBox)findViewById(R.id.chkFV);
        chkFV.setOnCheckedChangeListener(chkChanged);
        spnUAlter = (Spinner)findViewById(R.id.spnUAlter);
        spnUAlter.setOnItemSelectedListener(spnUAlterOnItemSelectedListener);
        spnFormato = (Spinner)findViewById(R.id.spnFormato);
        spnFormato.setOnItemSelectedListener(spnFormatOnItemSelectedListener);
        spnSubAlmacen = (Spinner)findViewById(R.id.spnSubAlmacen);
        btnImprimir = (Button)findViewById(R.id.btnImprimir);
        btnImprimir.setOnClickListener(imprimirOnClickListener);
        btnInventariar = (Button)findViewById(R.id.btnInventariar);
    }

    CheckBox.OnCheckedChangeListener chkChanged = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()){
                case R.id.chkFE:
                    tvFE.setTextColor( (b == false) ? getResources().getColor(R.color.disabledBlackColor) : getResources().getColor(R.color.blackColor));
                    break;
                case R.id.chkFV:
                    tvFV.setTextColor( (b == false) ? getResources().getColor(R.color.disabledBlackColor) : getResources().getColor(R.color.blackColor));
                    break;
                default:
                    break;
            }
        }
    };

    View.OnClickListener imprimirOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String message = validatePrint();

            if (message.length() != 0){
                Toast.makeText(EtqCajaLpnActivity.this, message,Toast.LENGTH_SHORT).show();
                return;
            }

            if(Global.intId_Impresora == null){
                presenter.showDialogImpresora();
                return;
            }else{
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EtqCajaLpnActivity.this);
                alertDialogBuilder.setMessage("¿Está seguro de imprimir "+ edtNumEtq.getText().toString() + " etiquetas?");
                alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (edtCantEtqSaldo.getText().toString().trim().length() == 0)
                        {
                            edtCantEtqSaldo.setText("0");
                        }

                        ImpUA objImp = new ImpUA();
                        Integer numEtq = Integer.parseInt(String.valueOf(edtNumEtq.getText()));
                        Double cantEtqSaldo = Double.parseDouble(String.valueOf(edtCantEtqSaldo.getText()));
                        Double cantXEtq = Double.parseDouble(String.valueOf(edtCantxEtq.getText()));

                        objImp.setCantidadEtiqueta(( numEtq + cantEtqSaldo  > 0 ) ? 1 : 0);
                        objImp.setCantidad(cantXEtq);
                        objImp.setId_Producto(intR_IdProducto);
                        objImp.setLoteLab( (bolR_FlagLote == true) ? edtLote.getText().toString().toUpperCase().trim() : null);
                        objImp.setFechaEmision((chkFE.isChecked()) ?  dR_FecEmi : null);
                        objImp.setFechaVencimiento((chkFV.isChecked()) ? dR_FecVen : null);
                        objImp.setItem(intR_Item);
                        objImp.setUsuarioRegistro("ADMIN");//Global.userName);
                        objImp.setId_Estado(intR_IdCondicion);
                        objImp.setSaldoEtiqueta( (edtCantEtqSaldo.getText().toString().equals("0")) ? 0 : cantEtqSaldo );
                        objImp.setId_Causal(null); //Ya no va
                        objImp.setId_SubAlmacen( (spnSubAlmacen.getSelectedItemPosition() == 0) ? 0 : ((SubAlmacenXCuenta) spnSubAlmacen.getSelectedItem()).getId_SubAlmacen());

                        /**
                         GestionRecibosMovil objRec = new GestionRecibosMovil();
                         //MessageBox.Show("Ejecutará método", "Aviso");
                         var obj = objRec.registrarUAMasivo(objImp, control.Global.IdCentro);
                         **/
                        presenter.registerUAMasivo(objImp, 1);//Global.idCentro);

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

    String str_formatSelected, str_valueSelected;

    AdapterView.OnItemSelectedListener spnFormatOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            str_formatSelected = ((TextView)view.findViewById(R.id.tvItemSpinner)).getText().toString();
            str_valueSelected = ((TextView)view.findViewById(R.id.tvItemSpinner)).getTag().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

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

            int c_year = c.get(Calendar.YEAR);
            int c_month = c.get(Calendar.MONTH);
            int c_day = c.get(Calendar.DAY_OF_MONTH);

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
                    }, c_year, c_month, c_day);
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
    public void sourceLabelsFormat(String[] list) {
        adpFormat = new SPFormatLabel(this, R.layout.spinner_item, Arrays.asList(list));
        spnFormato.setPrompt("Seleccione formato de etiqueta");
        spnFormato.setAdapter(adpFormat);
    }

    @Override
    public void showResultRegisterMasivo(ArrayList<ImpUA> list) {
        if (list.size() < 0){
            return;
        }

        ArrayList<ListaEtiqueta> lstListEtq = new ArrayList<>();
        UMxProducto selUAlter= (UMxProducto) spnUAlter.getSelectedItem();
        ListaEtiqueta objLE = new ListaEtiqueta();
        ArrayList<Etiqueta> lstEtq = new ArrayList<>();

        if (findArticulo == true){

            for (int i = 0; i< list.size(); i++){
                //ProxySGAAMovil.SOAPImpresiones.ListaEtiquetas LE = new ProxySGAAMovil.SOAPImpresiones.ListaEtiquetas();

                lstEtq.add(new Etiqueta("|MES|", new SimpleDateFormat("MM").format(new Date())));
                lstEtq.add(new Etiqueta("|ANIO|", new SimpleDateFormat("yyyy").format(new Date())));
                lstEtq.add(new Etiqueta("|LOTE|", edtLote.getText().toString()));
                lstEtq.add(new Etiqueta("|ESTADO|", strR_Condicion.toUpperCase()));
                lstEtq.add(new Etiqueta("|CODIGO|", strR_Codigo));
                lstEtq.add(new Etiqueta("|VENCIMIENTO|", formatter.format(dR_FecVen)));
                lstEtq.add(new Etiqueta("|CANTBULTO|", edtCantxEtq.getText().toString())); //Convert.ToDouble(txtCantxEtq.Text).ToString() );
                lstEtq.add(new Etiqueta("|CANTXBULTO|", String.valueOf(dbl_CantXBulto)));
                lstEtq.add(new Etiqueta("|SALDO|", "0" )); // Ya no va
                lstEtq.add(new Etiqueta("|FECHA_INGRESO|", (findArticulo.equals(true)) ? "" : formatter.format(new Date()))); //(FindArticulo==true)?"":DateTime.Now.ToString("dd/MM/yyyy"));
                lstEtq.add(new Etiqueta("|ORDEN|", (findArticulo.equals(true)) ? "": strR_NroDoc)); //(FindArticulo==true)?"":nrodoc);
                lstEtq.add(new Etiqueta("|USUARIO|", "ADMIN")); //Global.userName );
                lstEtq.add(new Etiqueta("|COMPOSICION|", strR_CondAlm)); //condicionAlmacenamiento );
                lstEtq.add(new Etiqueta("|UM|", tvUM.getText().toString().trim()));
                lstEtq.add(new Etiqueta("|TXTSALDO|", (i == 0 && Double.parseDouble(String.valueOf(edtCantEtqSaldo.getText())) > 0) ? "SALDO" : "" )); //((i==0  && Convert.ToDouble(txtCantEtqSaldo.Text)>0)?"SALDO":""));
                lstEtq.add(new Etiqueta("|COPIAS|", edtNumCopias.getText().toString()));
                lstEtq.add(new Etiqueta("|CODBARRA|", list.get(i).getUA_CodBarra()));
                lstEtq.add(new Etiqueta("|CUENTA|", "CIPSA"));//control.Global.Empresa );
                lstEtq.add(new Etiqueta("|PRODUCTO|", strR_Articulo));//lblArticulo.Text );//producto
                lstEtq.add(new Etiqueta("|EAN14|", selUAlter.getEAN14())); //listPUM[cboPresentacion.SelectedIndex].EAN14 ); //EAN14
                lstEtq.add(new Etiqueta("|EAN|", (selUAlter.getNombre().toUpperCase().equals(tvUM.getText().toString().trim())) ? "13" : "14" )); //( ((ProxySGAAMovil.SOAPUinidadMedida.SGAA_SP_S_ListarUMXProducto_Result)(cboPresentacion.Items[cboPresentacion.SelectedIndex])).Nombre.ToUpper()==lblUmed.Text.Trim().ToUpper())?"13":"14" ); //EAN
                lstEtq.add(new Etiqueta("|CANTIDAD|", (i == 0 && Double.parseDouble(String.valueOf(edtCantEtqSaldo.getText())) > 0) ? edtCantEtqSaldo.getText().toString() : edtCantxEtq.getText().toString()));//(i==0  && Convert.ToDouble(txtCantEtqSaldo.Text)>0)?((Convert.ToDouble(txtCantEtqSaldo.Text))+Convert.ToDouble(txtSaldo.Text)).ToString():(Convert.ToDouble(txtCantxEtq.Text)).ToString() );
                lstEtq.add(new Etiqueta("|SALDOTEXT|", "0" )); //Ya no va //(Convert.ToDouble(txtSaldo.Text)>0)?txtSaldo.Text:"0" );
            }
        }else{ // Desde recibo

            for (int i = 0; i < list.size(); i++){
                lstEtq.add(new Etiqueta("|MES|", new SimpleDateFormat("MMMM").format(new Date())));
                lstEtq.add(new Etiqueta("|ANIO|", new SimpleDateFormat("yyyy").format(new Date())));
                lstEtq.add(new Etiqueta("|LOTE|", edtLote.getText().toString()));
                lstEtq.add(new Etiqueta("|ESTADO|", strR_Condicion));
                lstEtq.add(new Etiqueta("|CODIGO|", strR_Codigo));
                lstEtq.add(new Etiqueta("|VENCIMIENTO|", new SimpleDateFormat("MMM-yyyy").format(dR_FecVen))); //datFechaVencimiento.Value.ToString("MMM-yyyy").ToUpper() );
                lstEtq.add(new Etiqueta("|CANTBULTO|", (i == 0 && Double.parseDouble(String.valueOf(edtCantEtqSaldo.getText())) > 0) ? String.valueOf(dbl_Residuo/dbl_CantXBulto): edtCantxEtq.getText().toString() )); //((i==0  && Convert.ToDouble(txtCantEtqSaldo.Text)>0)?Convert.ToDouble(Decimal.Truncate((residuo/CantXBulto))):Convert.ToDouble(txtCantxEtq.Text)).ToString());
                lstEtq.add(new Etiqueta("|CANTXBULTO|", String.valueOf(dbl_CantXBulto))); //((i==0  && Convert.ToDouble(txtCantEtqSaldo.Text)>0)?Decimal.Truncate(CantXBulto):Decimal.Truncate(CantXBulto)).ToString());
                lstEtq.add(new Etiqueta("|SALDO|", (i == 0 && Double.parseDouble(String.valueOf(edtCantEtqSaldo.getText())) > 0) ? String.valueOf((dbl_Residuo % dbl_CantXBulto)): "0" )); //((i==0  && Convert.ToDouble(txtCantEtqSaldo.Text)>0)?Decimal.Truncate(residuo%CantXBulto):Decimal.Truncate(Convert.ToDecimal(txtSaldo.Text))).ToString());
                lstEtq.add(new Etiqueta("|FECHA_INGRESO|", (findArticulo == true)? "" : formatter.format(new Date())));
                lstEtq.add(new Etiqueta("|ORDEN|",  (findArticulo == true)? "" : strR_NroDoc));
                lstEtq.add(new Etiqueta("|USUARIO|", "ADMIN" ));//control.Global.Usuario);
                lstEtq.add(new Etiqueta("|COMPOSICION|", strR_CondAlm)); //condicionAlmacenamiento
                lstEtq.add(new Etiqueta("|UM|", tvUM.getText().toString()));
                lstEtq.add(new Etiqueta("|TXTSALDO|", (i == 0 && Double.parseDouble(String.valueOf(edtCantEtqSaldo.getText())) > 0) ?  "SALDO" : "" )); //((i==0  && Convert.ToDouble(txtCantEtqSaldo.Text)>0)?"SALDO":"")
                lstEtq.add(new Etiqueta("|COPIAS|", edtNumCopias.getText().toString())); //numCopias.Value.ToString()
                lstEtq.add(new Etiqueta("|CODBARRA|", list.get(i).getUA_CodBarra()));//obj[i].UA_CodBarra
                lstEtq.add(new Etiqueta("|PRODUCTO|", strR_Articulo));//producto
                lstEtq.add(new Etiqueta("|EAN14|", selUAlter.getEAN14())); //EAN14
                lstEtq.add(new Etiqueta("|EAN|", (selUAlter.getNombre().toUpperCase().equals(tvUM.getText().toString().trim())) ? "13" : "14" ));
                lstEtq.add(new Etiqueta("|CANTIDAD|", (i == 0 && Double.parseDouble(String.valueOf(edtCantEtqSaldo.getText())) > 0) ? edtCantEtqSaldo.getText().toString() : edtCantxEtq.getText().toString()));

                objLE = new ListaEtiqueta();
                objLE.setEtiqueta(lstEtq);
                lstListEtq.add(objLE);
            }
        }

        //Impresora
        String format = str_valueSelected;
        presenter.printListaEtq(lstListEtq, format, Global.nameImpresora, true);
        /**
         //impresora
         string formato = (ddlFormato.SelectedValue == null) ? txtetq.Text.Trim() : ddlFormato.SelectedValue.ToString();
         var objMsj = print.imprimirListaEtiquetas(lstLisEtq, formato, impresora, true);
         if (objMsj.errNumber == -1)
         {
         MessageBox.Show(objMsj.message.ToString(), "Error");
         }
         **/
    }

    @Override
    public void showResultPrintEtq(Mensaje message) {
        if (message.errNumber == -1){
            Toast.makeText(this, "Print"+ message.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showFailureRequest(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogImpresora() {
         FragmentManager fm = getSupportFragmentManager();
         ImpresoraFragment alertDialog = ImpresoraFragment.newInstance();
         alertDialog.show(fm, "Fragment_alert");
    }

    @Override
    public void showProgressDialog() {
        ProgressDialogRequest.show(this);
    }

    @Override
    public void hideProgressDialog() {
        ProgressDialogRequest.dismiss();
    }

    void prepareFormatLabel(){
        String[] strArray;
        strArray = getResources().getStringArray(R.array.array_LabelFormats);
        presenter.listLabelsFormat(strArray);
    }

    String validatePrint(){
        String message = "";

        if (spnSubAlmacen.getSelectedItem().toString().equals("")){
            message = "Seleccione Sub Almacén";
            return message;
        }

        if (edtLote.getText().toString().equals("") && bolR_FlagLote){
            message = "Indique el lote";
            return message;
        }

        if (spnUAlter.getSelectedItem().toString().equals("")){
            message = "Seleccione una presentacion";
            return message;
        }

        if (edtCantxEtq.getText().toString().equals("")){
            message = "Indique cantidad";
            return message;
        }

        if (edtNumEtq.getText().toString().equals("") || edtNumEtq.getText().toString().equals("0")){
            message = "Indique el número de etiquetas";
            return  message;
        }

        if (Integer.parseInt(String.valueOf(edtNumCopias.getText().toString())) < 0){
            message = "El número de copias no valido";
            return message;
        }

        if (chkFE.isChecked() && chkFV.isChecked()){
            if (dR_FecEmi.after(dR_FecVen)){
                message = "La fecha de emisión debe ser menor a la de vencimiento";
                chkFE.setChecked(true);
                chkFV.setChecked(true);
                return  message;
            }
        }

        return message;
    }
}
