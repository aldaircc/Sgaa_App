package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_04;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Almacenaje.SPSector;
import com.example.acosetito.sgaa.data.Model.Almacenaje.SectorXAlmacen;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionDisponible;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionXCodigoBarra;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_03.Alm_Tab_03Activity;

import java.util.ArrayList;

public class Alm_Tab_04Activity extends AppCompatActivity implements AlmTab04View{

    private Spinner spnSector;
    private EditText edtUbicacion;
    private TextView tvRows;
    private RadioGroup rgpOptions;
    private RadioButton rdbCMarca, rdbSMarca, rdbDirig;
    private TableLayout tblUbicaciones;
    private AlmTab04Presenter presenter;
    private SPSector adapterSpn;
    private ArrayList<UbicacionDisponible> baseListUbi, baseListUbiAux;
    private ArrayList<UbicacionXCodigoBarra> listDirigido;
    private ArrayList<UATransito> lstR_ItemPallets = new ArrayList<>();

    private boolean existeUbi = false;
    private String strR_Producto, strR_CodProducto;
    private Integer intR_IdCondicion, intR_Id_Marca, intR_TotalRowsUbi, intOrigen;
    private int ALM_TAB_04 = 04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alm__tab_04);
        initializeControls();
        getValueExtras();
        presenter = new AlmTab04PresenterImpl(this);
        presenter.listarSectoresXAlmacen(2);//Global.IdWarehouse);
    }

    void initializeControls(){
        tblUbicaciones = (TableLayout)findViewById(R.id.tblUbicaciones);
        spnSector = (Spinner)findViewById(R.id.spnSector);
        spnSector.setOnItemSelectedListener(spnSectorOnItemSelectedListener);
        edtUbicacion = (EditText)findViewById(R.id.edtUbicacion);
        edtUbicacion.addTextChangedListener(textWatcherUbi);
        tvRows = (TextView)findViewById(R.id.tvRows);
        rgpOptions = (RadioGroup)findViewById(R.id.rgpOptions);
        rgpOptions.setOnCheckedChangeListener(checkedChangeOptions);
        rdbCMarca = (RadioButton)findViewById(R.id.rdbCMarca);
        rdbSMarca = (RadioButton)findViewById(R.id.rdbSMarca);
        rdbDirig = (RadioButton)findViewById(R.id.rdbDirig);
    }

    void getValueExtras(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            intR_Id_Marca = extras.getInt("Id_Marca");
            intR_TotalRowsUbi = extras.getInt("Total_RowsUbi");
            intR_IdCondicion = extras.getInt("Id_Condicion");
            strR_CodProducto = extras.getString("Cod_Producto");
            strR_Producto = extras.getString("Producto");
            intR_TotalRowsUbi = extras.getInt("Total_RowsUbi");

            //Evaluar origen
            intOrigen = extras.getInt("Origen");
            lstR_ItemPallets =  extras.getParcelableArrayList("lstItemsPallet");
        }
    }

    AdapterView.OnItemSelectedListener spnSectorOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            if (adapterSpn.getItems().size() > 0){
                SectorXAlmacen ent = adapterSpn.getItems().get(i);
                presenter.listarMasUbicacionDisponibles(2/** Global.IdWarehouse **/, 0, 1, ent.getId_Sector());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    RadioGroup.OnCheckedChangeListener checkedChangeOptions = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

            switch (checkedId){
                case R.id.rdbCMarca:
                    showUbicaciones();
                    break;
                case R.id.rdbSMarca:
                    edtUbicacion.setText("");
                    edtUbicacion.setEnabled(false);
                    showUbicaciones();
                    break;
                case R.id.rdbDirig:
                    edtUbicacion.setEnabled(true);
                    edtUbicacion.requestFocus();
                    break;
                default:
                    break;
            }
        }
    };

    void showUbicaciones(){

        clearTblUbicacion();

        for (UbicacionDisponible ent: baseListUbiAux){

            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.table_row_alm_tab04, null);

            if (rdbCMarca.isChecked() && !ent.getMarca().equals("Sin Marca")){
                ((TextView)row.findViewById(R.id.textSector)).setText(ent.getSector());
                ((TextView)row.findViewById(R.id.textPasillo)).setText(ent.getPasillo());
                ((TextView)row.findViewById(R.id.textPasillo)).setTag(ent.getCodigoBarra());
                ((TextView)row.findViewById(R.id.textFila)).setText(ent.getFila());
                ((TextView) row.findViewById(R.id.textFila)).setTag(ent.getId_Ubicacion());
                ((TextView)row.findViewById(R.id.textColumna)).setText(String.valueOf(ent.getColumna()));
                ((TextView)row.findViewById(R.id.textNivel)).setText(String.valueOf(ent.getNivel()));
                ((TextView)row.findViewById(R.id.textPosicion)).setText(String.valueOf(ent.getPosicion()));
                tblUbicaciones.addView(row);
            }else if (rdbSMarca.isChecked() && ent.getMarca().equals("Sin Marca")){
                ((TextView)row.findViewById(R.id.textSector)).setText(ent.getSector());
                ((TextView)row.findViewById(R.id.textPasillo)).setText(ent.getPasillo());
                ((TextView)row.findViewById(R.id.textPasillo)).setTag(ent.getCodigoBarra());
                ((TextView)row.findViewById(R.id.textFila)).setText(ent.getFila());
                ((TextView)row.findViewById(R.id.textFila)).setTag(ent.getId_Ubicacion());
                ((TextView)row.findViewById(R.id.textColumna)).setText(String.valueOf(ent.getColumna()));
                ((TextView)row.findViewById(R.id.textNivel)).setText(String.valueOf(ent.getNivel()));
                ((TextView)row.findViewById(R.id.textPosicion)).setText(String.valueOf(ent.getPosicion()));
                ((Button)row.findViewById(R.id.btnSelectUbi)).setTag(ent.getId_Ubicacion());
                ((Button)row.findViewById(R.id.btnSelectUbi)).setOnClickListener(selectUbiOnClick);
                tblUbicaciones.addView(row);
            }
        }
        tblUbicaciones.requestLayout();
    }

    void clearTblUbicacion(){
        while(tblUbicaciones.getChildCount() > 1){
            TableRow row = (TableRow)tblUbicaciones.getChildAt(1);
            tblUbicaciones.removeView(row);
            tblUbicaciones.invalidate();
        }
    }

    TextWatcher textWatcherUbi = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String  x = charSequence.toString();
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (edtUbicacion.getText().toString().contains("\n")){

                String codeUbicacion = edtUbicacion.getText().toString();
                codeUbicacion = codeUbicacion.replace("\n","");
                edtUbicacion.setText("");

                existeUbi = false;
                clearTblUbicacion();
                listDirigido = new ArrayList<>();

                for(UbicacionDisponible ent : baseListUbi){

                    if (ent.getCodigoBarra().trim().equals(codeUbicacion.trim())) {
                        existeUbi = true;
                        TableRow row = (TableRow) LayoutInflater.from(Alm_Tab_04Activity.this).inflate(R.layout.table_row_alm_tab04, null);
                        ((TextView) row.findViewById(R.id.textSector)).setText(ent.getSector());
                        ((TextView) row.findViewById(R.id.textPasillo)).setText(ent.getPasillo());
                        ((TextView) row.findViewById(R.id.textPasillo)).setTag(ent.getCodigoBarra());
                        ((TextView) row.findViewById(R.id.textFila)).setText(ent.getFila());
                        ((TextView) row.findViewById(R.id.textFila)).setTag(ent.getId_Ubicacion());
                        ((TextView) row.findViewById(R.id.textColumna)).setText(String.valueOf(ent.getColumna()));
                        ((TextView) row.findViewById(R.id.textNivel)).setText(String.valueOf(ent.getNivel()));
                        ((TextView) row.findViewById(R.id.textPosicion)).setText(String.valueOf(ent.getPosicion()));
                        ((Button) row.findViewById(R.id.btnSelectUbi)).setTag(ent.getId_Ubicacion());
                        ((Button) row.findViewById(R.id.btnSelectUbi)).setOnClickListener(selectUbiOnClick);
                        tblUbicaciones.addView(row);

                        UbicacionXCodigoBarra newItem = new UbicacionXCodigoBarra();
                        newItem.setSector(ent.getSector());
                        newItem.setPasillo(ent.getPasillo());
                        newItem.setId_Ubicacion(ent.getId_Ubicacion());
                        newItem.setFila(ent.getFila());
                        newItem.setColumna(ent.getColumna());
                        newItem.setNivel(ent.getNivel());
                        newItem.setPosicion(ent.getPosicion());
                        newItem.setCodigoBarra(ent.getCodigoBarra());
                        listDirigido.add(newItem);
                        break;
                    }
                }

                if (!existeUbi){
                    baseListUbi.clear();
                    presenter.listarUbicacionXCodigoBarra(codeUbicacion, 2);//Global.IdWarehouse);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    View.OnClickListener selectUbiOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TableRow row = (TableRow)v.getParent();

            String strSector, strPasillo, strFila, strCodigoBarra;
            Integer intIdUbicacion, intColumna, intNivel, intPosicion;

            if (rdbDirig.isChecked()){
                strSector = listDirigido.get(0).getSector();
                strPasillo = listDirigido.get(0).getPasillo();
                strCodigoBarra = listDirigido.get(0).getCodigoBarra();
                strFila = listDirigido.get(0).getFila();
                intIdUbicacion = listDirigido.get(0).getId_Ubicacion();
                intColumna = listDirigido.get(0).getColumna();
                intNivel = listDirigido.get(0).getNivel();
                intPosicion = listDirigido.get(0).getPosicion();
            }else{

                if (baseListUbi.size() > 0){
                    strSector = ((TextView) row.getChildAt(0)).getText().toString();
                    strPasillo = ((TextView) row.getChildAt(1)).getText().toString();
                    strCodigoBarra = ((TextView) row.getChildAt(1)).getTag().toString();
                    strFila = ((TextView) row.getChildAt(2)).getText().toString();
                    intIdUbicacion = Integer.parseInt(String.valueOf(((TextView) row.getChildAt(2)).getTag()));
                    intColumna = Integer.parseInt(String.valueOf(((TextView) row.getChildAt(3)).getText()));
                    intNivel = Integer.parseInt(String.valueOf(((TextView) row.getChildAt(4)).getText()));
                    intPosicion = Integer.parseInt(String.valueOf(((TextView) row.getChildAt(5)).getText()));
                }else{
                    strSector = ((TextView) row.getChildAt(0)).getText().toString();
                    strPasillo = ((TextView) row.getChildAt(1)).getText().toString();
                    strCodigoBarra = ((TextView) row.getChildAt(1)).getTag().toString();
                    strFila = ((TextView) row.getChildAt(2)).getText().toString();
                    intIdUbicacion = Integer.parseInt(String.valueOf(((TextView) row.getChildAt(2)).getTag()));
                    intColumna = Integer.parseInt(String.valueOf(((TextView) row.getChildAt(3)).getText()));
                    intNivel = Integer.parseInt(String.valueOf(((TextView) row.getChildAt(4)).getText()));
                    intPosicion = Integer.parseInt(String.valueOf(((TextView) row.getChildAt(5)).getText()));
                }
            }

            presenter.navigateToTab03(intR_IdCondicion, strR_CodProducto, strR_Producto, strCodigoBarra, strCodigoBarra, strSector, strPasillo, strFila, intIdUbicacion, intColumna, intNivel, intPosicion,1,intR_TotalRowsUbi, lstR_ItemPallets);
        }
    };


    @Override
    public void resultListarSector(ArrayList<SectorXAlmacen> list) {
        adapterSpn = new SPSector(this, R.layout.spinner_item, list);
        spnSector.setPrompt("Seleccione sector");
        spnSector.setAdapter(adapterSpn);
    }

    @Override
    public void resultListarMasUbicacionesDisponibles(ArrayList<UbicacionDisponible> list) {
        baseListUbi = list;
        baseListUbiAux = list;
        tvRows.setText(String.valueOf(baseListUbi.size()));
    }

    @Override
    public void resultListarUbicacionXCodigoBarra(ArrayList<UbicacionXCodigoBarra> list) {
        listDirigido = list;
        if (listDirigido.size() > 0){
            existeUbi = true;
            TableRow row = (TableRow) LayoutInflater.from(Alm_Tab_04Activity.this).inflate(R.layout.table_row_alm_tab04, null);
            ((TextView) row.findViewById(R.id.textSector)).setText(listDirigido.get(0).getSector());
            ((TextView) row.findViewById(R.id.textPasillo)).setText(listDirigido.get(0).getPasillo());
            ((TextView) row.findViewById(R.id.textPasillo)).setTag(listDirigido.get(0).getCodigoBarra());
            ((TextView) row.findViewById(R.id.textFila)).setText(listDirigido.get(0).getFila());
            ((TextView) row.findViewById(R.id.textFila)).setTag(listDirigido.get(0).getId_Ubicacion());
            ((TextView) row.findViewById(R.id.textColumna)).setText(String.valueOf(listDirigido.get(0).getColumna()));
            ((TextView) row.findViewById(R.id.textNivel)).setText(String.valueOf(listDirigido.get(0).getNivel()));
            ((TextView) row.findViewById(R.id.textPosicion)).setText(String.valueOf(listDirigido.get(0).getPosicion()));
            ((Button) row.findViewById(R.id.btnSelectUbi)).setTag(listDirigido.get(0).getId_Ubicacion());
            ((Button) row.findViewById(R.id.btnSelectUbi)).setOnClickListener(selectUbiOnClick);
            tblUbicaciones.addView(row);

        }
        tvRows.setText(String.valueOf(tblUbicaciones.getChildCount()));

        if (!existeUbi){
            Toast.makeText(this, "No se econtro la ubicación", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void navigateToTab02() {
        Global.gListItemsPallet.clear();
        Global.gListItemsPallet = lstR_ItemPallets;
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("listItemPallets", lstR_ItemPallets);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void navigateToTab03(Integer intId_Condicion, String strCod_Prod, String strProducto,
                                String strCod_Barra, String strCod_UAPallet, String strSector, String strPasillo,
                                String strFila, Integer intId_Ubicacion, Integer intColumna, Integer intNivel,
                                Integer intPosicion, Integer intCountPallets, Integer total, ArrayList<UATransito> lstItemPallets) {
        Intent intent = new Intent(Alm_Tab_04Activity.this, Alm_Tab_03Activity.class);
        intent.putExtra("Id_Condicion",intId_Condicion);
        intent.putExtra("Cod_Producto", strCod_Prod);
        intent.putExtra("Producto", strProducto);
        intent.putExtra("Cod_Barra",strCod_Barra);
        intent.putExtra("Cod_UAPallet",strCod_UAPallet);
        intent.putExtra("Sector",strSector);
        intent.putExtra("Pasillo",strPasillo);
        intent.putExtra("Fila",strFila);
        intent.putExtra("Id_Ubicacion",intId_Ubicacion);
        intent.putExtra("Columna",intColumna);
        intent.putExtra("Nivel",intNivel);
        intent.putExtra("Posicion",intPosicion);
        intent.putExtra("CountPallets", intCountPallets);
        intent.putExtra("Total_RowsUbi", total);
        intent.putExtra("Origen", ALM_TAB_04);
        intent.putParcelableArrayListExtra("lstItemPallets", lstItemPallets);
        startActivity(intent);
        finish();
    }

    @Override
    public void showFailureRequest(String result) {

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
        if (intOrigen.equals(2)){
            presenter.navigateToTab02();
        }else{
            //presenter.navigateToTab03();
        }
    }
}
