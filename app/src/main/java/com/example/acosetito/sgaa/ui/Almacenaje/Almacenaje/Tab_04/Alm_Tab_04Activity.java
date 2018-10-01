package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;

import java.util.ArrayList;

public class Alm_Tab_04Activity extends AppCompatActivity implements AlmTab04View{

    private Spinner spnSector;
    private EditText edtUbicacion;
    private RadioGroup rgpOptions;
    private RadioButton rdbCMarca, rdbSMarca, rdbDirig;
    private TableLayout tblUbicaciones;
    private AlmTab04Presenter presenter;
    private SPSector adapterSpn;
    ArrayList<UbicacionDisponible> baseListUbi, baseListUbiAux;

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
                    Toast.makeText(Alm_Tab_04Activity.this, "Dirigido", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    void showUbicaciones(){

        while(tblUbicaciones.getChildCount() > 1){
            TableRow row = (TableRow)tblUbicaciones.getChildAt(1);
            tblUbicaciones.removeView(row);
            tblUbicaciones.invalidate();
        }

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
                tblUbicaciones.addView(row);
            }
        }
        tblUbicaciones.requestLayout();
    }

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
    }

    @Override
    public void navigateToTab03() {

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
