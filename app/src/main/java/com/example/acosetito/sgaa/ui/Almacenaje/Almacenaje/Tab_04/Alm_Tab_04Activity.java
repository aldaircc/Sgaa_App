package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_04;

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

    private boolean existeUbi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alm__tab_04);
        initializeControls();
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

    AdapterView.OnItemSelectedListener spnSectorOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            if (adapterSpn.getItems().size() > 0){
                SectorXAlmacen ent = adapterSpn.getItems().get(i);
                presenter.listarMasUbicacionDisponibles(2/** Global.IdWarehouse **/, 0, 1, ent.getId_Sector());
                //CargarListaUbicaciones(idAlmacen, 0, 1, idSector);
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
                    /**txtCodBarraUbicacion.Text = "";
                    txtCodBarraUbicacion.Enabled = false;
                    MostrarListaUbicaciones();**/
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
                ((TextView)row.findViewById(R.id.textFila)).setText(ent.getFila());
                ((TextView)row.findViewById(R.id.textColumna)).setText(String.valueOf(ent.getColumna()));
                ((TextView)row.findViewById(R.id.textNivel)).setText(String.valueOf(ent.getNivel()));
                ((TextView)row.findViewById(R.id.textPosicion)).setText(String.valueOf(ent.getPosicion()));
                tblUbicaciones.addView(row);
            }else if (rdbSMarca.isChecked() && ent.getMarca().equals("Sin Marca")){
                ((TextView)row.findViewById(R.id.textSector)).setText(ent.getSector());
                ((TextView)row.findViewById(R.id.textPasillo)).setText(ent.getPasillo());
                ((TextView)row.findViewById(R.id.textFila)).setText(ent.getFila());
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
                        ((TextView) row.findViewById(R.id.textFila)).setText(ent.getFila());
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

            /**
             if (string.IsNullOrEmpty(txtCodBarraUbicacion.Text.Trim()))
             {
             return;
             }

             bool existe = false;

             if (e.KeyChar == 13)
             {
             ListaUbicaciones.Items.Clear();
             listaDirigida = new List<ProxySGAAMovil.SOAPAlmacenaje.SGAA_SP_S_ListarUbicacionXCodigoBarra_Result>();
             foreach (var item in masUbi)
             {

             if (item.CodigoBarra  == txtCodBarraUbicacion.Text) //by jmc 12.02.2018
             {
             existe = true;
             ListViewItem nvo_lst = new ListViewItem(item.Sector);
             nvo_lst.SubItems.Add(item.Pasillo);
             nvo_lst.SubItems.Add(item.Pasillo.ToString().Trim().PadLeft(2, '0'));
             nvo_lst.SubItems.Add(item.Fila.ToString());
             nvo_lst.SubItems.Add(item.Columna.ToString().Trim().PadLeft(2, '0'));
             nvo_lst.SubItems.Add(item.Nivel.ToString().Trim().PadLeft(2, '0'));
             nvo_lst.SubItems.Add(item.Posicion.ToString().Trim().PadLeft(3, '0'));
             nvo_lst.SubItems.Add(item.Id_Ubicacion.ToString()); ///add by JMC 12.02.2018
             ListaUbicaciones.Items.Add(nvo_lst);

             listaDirigida.Add(
             new ProxySGAAMovil.SOAPAlmacenaje.SGAA_SP_S_ListarUbicacionXCodigoBarra_Result()
             {
             Sector = item.Sector,
             Pasillo = item.Pasillo,
             Id_Ubicacion = item.Id_Ubicacion,
             Fila=item.Fila,
             Columna=item.Columna,
             Nivel=item.Nivel,
             Posicion=item.Posicion,
             CodigoBarra= item.CodigoBarra
             });
             break;
             }
             }
             if (!existe)
             {
             masUbi.Clear();

             GestionAlmacenajeMobile ga = new GestionAlmacenajeMobile();
             //var list = ga.ListarUbicacionXCodigoBarra(txtCodBarraUbicacion.Text, control.Global.IdAlmacen);
             listaDirigida = ga.ListarUbicacionXCodigoBarra(txtCodBarraUbicacion.Text, control.Global.IdAlmacen);
             if (listaDirigida.Count() > 0)
             {
             existe = true;
             ListViewItem nvo_lst = new ListViewItem(listaDirigida[0].Sector);
             nvo_lst.SubItems.Add(listaDirigida[0].Pasillo);
             nvo_lst.SubItems.Add(listaDirigida[0].Pasillo.ToString().Trim().PadLeft(2, '0'));
             nvo_lst.SubItems.Add(listaDirigida[0].Fila.ToString());
             nvo_lst.SubItems.Add(listaDirigida[0].Columna.ToString().Trim().PadLeft(2, '0'));
             nvo_lst.SubItems.Add(listaDirigida[0].Nivel.ToString().Trim().PadLeft(2, '0'));
             nvo_lst.SubItems.Add(listaDirigida[0].Posicion.ToString().Trim().PadLeft(3, '0'));
             nvo_lst.SubItems.Add(listaDirigida[0].Id_Ubicacion.ToString()); ///add by JMC 12.02.2018
             ListaUbicaciones.Items.Add(nvo_lst);
             }
             lbltotmasubi.Text = ListaUbicaciones.Items.Count.ToString();
             }
             if (!existe)
             {

             MessageBox.Show("No se econtro la ubicación", "Aviso",
             MessageBoxButtons.OK, MessageBoxIcon.Exclamation, MessageBoxDefaultButton.Button1);
             }
             }
             **/
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    View.OnClickListener selectUbiOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View row = (View)v.getParent();
            presenter.navigateToTab03();
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
            ((TextView) row.findViewById(R.id.textFila)).setText(listDirigido.get(0).getFila());
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
    public void navigateToTab03() {
        Intent intent = new Intent(Alm_Tab_04Activity.this, Alm_Tab_03Activity.class);
        startActivity(intent);
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
}
