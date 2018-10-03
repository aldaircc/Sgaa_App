package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Almacenaje.LVAlmTab02Adapter;
import com.example.acosetito.sgaa.data.Adapter.Almacenaje.RVAlmTab02Adapter;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVAlmTab02Adapter;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionLibreXMarca;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_03.Alm_Tab_03Activity;
import com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_04.Alm_Tab_04Activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Alm_Tab_02Activity extends AppCompatActivity implements AlmTab02View, IRVAlmTab02Adapter{

    TextView tvUbiOrigen, tvTotal;
    EditText edtCodBarra;
    Button btnInsert;
    //RecyclerView rclItemPallet;
    TableLayout table;

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    //RVAlmTab02Adapter adapter;
    ArrayList<UATransito> localList = new ArrayList<>();
    AlmTab02Presenter presenter;

    String strR_Ubicacion;
    Integer intR_IdUbicacion, intTotalRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alm__tab_02);
        initializeControls();
        getValueExtras();
        presenter = new AlmTab02PresenterImpl(this);
    }

    //region Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_almacen, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (Build.VERSION.SDK_INT > 11){
            invalidateOptionsMenu();
            menu.findItem(R.id.itemBack).setVisible(true);
            menu.findItem(R.id.itemRefresh).setVisible(false);
            menu.findItem(R.id.itemNext).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    Integer intId_Marca, intId_Condicion;
    String strUA_CodBarra, strCod_Producto, strProducto, strUAPallet;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemBack:
                return true;
            case R.id.itemRefresh:
                return true;
            case R.id.itemNext:

                if (localList.size() > 0){

                    strCod_Producto = localList.get(0).getCodigoProducto();
                    strProducto = localList.get(0).getNombreProducto();
                    strUAPallet = localList.get(0).getUA_CodBarra();
                    intId_Marca = localList.get(0).getId_Marca();
                    intId_Condicion = localList.get(0).getId_Condicion();
                    strUA_CodBarra = localList.get(0).getUA_CodBarra();

                    presenter.listarUbicacionLibrexMarcaSugerida(
                            intId_Marca,
                            2, //Global.IdWarehouse,
                            intId_Condicion,
                            strUA_CodBarra);
                }else{
                    Toast.makeText(this, "Debe de ingresar Pallet/UA", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

    void initializeControls(){
        table = (TableLayout)findViewById(R.id.tableSample);
        tvUbiOrigen = (TextView)findViewById(R.id.tvUbiOrigen);
        tvTotal = (TextView)findViewById(R.id.tvTotal);
        edtCodBarra = (EditText)findViewById(R.id.edtCodBarra);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(insertOnClickListener);
        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //rclItemPallet.setLayoutManager(llm);
    }

    void getValueExtras(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            strR_Ubicacion = extras.getString("Ubicacion");
            intR_IdUbicacion = extras.getInt("IdUbicacion");
            tvUbiOrigen.setText(strR_Ubicacion);
            tvUbiOrigen.setTag(intR_IdUbicacion);
        }
    }

    void dataBindTable(ArrayList<UATransito> list){
        for (UATransito ent : list){
            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.table_row_alm_tab02, null);
            ((TextView)row.findViewById(R.id.textItem1)).setText(ent.getUA_CodBarra());
            ((TextView)row.findViewById(R.id.textItem2)).setText(ent.getCodigoProducto());
            ((TextView)row.findViewById(R.id.textItem3)).setText(ent.getNombreProducto());
            ((TextView)row.findViewById(R.id.textItem4)).setText(ent.getUM());
            ((TextView)row.findViewById(R.id.textCantidad)).setText(String.valueOf(ent.getCantidad()));
            ((TextView)row.findViewById(R.id.textLoteLab)).setText(ent.getLoteLab());
            ((TextView)row.findViewById(R.id.textFEmision)).setText(formatter.format(ent.FechaEmision));
            ((TextView)row.findViewById(R.id.textFVencimiento)).setText(formatter.format(ent.getFechaVencimiento()));
            ((Button)row.findViewById(R.id.btnRemove)).setTag(ent.getUA_CodBarra());
            ((Button)row.findViewById(R.id.btnRemove)).setOnClickListener(RemoveItemOnClick);
            table.addView(row);
            localList.add(ent);
        }
        table.requestLayout();

    }

    Boolean existInList(String strCodBar){
        Boolean value = false;
        for (UATransito ent: localList){
            if (ent.getUA_CodBarra().equals(strCodBar)){
                value = true;
            }
        }
        return value;
    }

    View.OnClickListener insertOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (existInList(edtCodBarra.getText().toString())){
                Toast.makeText(Alm_Tab_02Activity.this, "Pallet/UA se encuentra en lista", Toast.LENGTH_SHORT).show();
            }else {
                presenter.validateUATransito(edtCodBarra.getText().toString(), intR_IdUbicacion);
                edtCodBarra.setText("");
            }
        }
    };

    @Override
    public void resultValidateUA(ArrayList<UATransito> list) {
        if (list.size() > 0){
            //localList = list;
            dataBindTable(list);
            intTotalRows = localList.size();
            tvTotal.setText(String.valueOf(intTotalRows));
        }else{
            Toast.makeText(this, "Pallet/UA no registrada", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void resultListarUbicacionLibrexMarcaSugerida(ArrayList<UbicacionLibreXMarca> list) {

        if (list.size() > 0){
            UbicacionLibreXMarca ent = list.get(0);
            presenter.navigateToTab03(
                    ent.getId_Marca(),
                    intId_Condicion,
                    strCod_Producto,
                    strProducto,
                    ent.getCodigoBarra(),
                    strUAPallet,
                    ent.getSector(),
                    ent.getPasillo(),
                    ent.getFila(),
                    ent.getId_Ubicacion(),
                    ent.getColumna(),
                    ent.getNivel(),
                    String.valueOf(ent.getPosicion()),
                    list.size(),
                    localList.size()
            );

        }else{

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("No existe ubicación sugerida,¿Desea seleccionar una ubicación?");
            alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    presenter.navigateToTab04(intId_Marca, intTotalRows, intId_Condicion, strCod_Producto, strProducto);
                    //strCod_Barra =  -> ListViewItem nvo_lstua = new ListViewItem((item.Serie == null) ? item.UA_CodBarra : item.Serie);//0
                    //strCodUA_Pallet = UA_CodBarra; //15
                    //total = > total de filas de la grilla

                }
            });

            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    View.OnClickListener RemoveItemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View row = (View) v.getParent();
            ViewGroup container = ((ViewGroup)row.getParent());
            UATransito objEnt = new UATransito();
            for (UATransito ent: localList){
                if (ent.getUA_CodBarra().equals(v.getTag())){
                    objEnt = ent;
                }
            }
            localList.remove(objEnt);
            container.removeView(row);
            container.invalidate();
            intTotalRows = localList.size();
            tvTotal.setText(String.valueOf(intTotalRows));
        }
    };

    @Override
    public void showFailureRequest(String result) {

    }

    @Override
    public void navigateToTab01() {

    }

    @Override
    public void navigateToTab03(Integer intId_Marca, Integer intId_Condicion, String strCod_Prod, String strProducto,
                                String strCod_Barra, String strCod_UAPallet, String strSector, String strPasillo,
                                String strFila, Integer intId_Ubicacion, Integer intColumna, Integer intNivel,
                                String strPosicion, Integer intCountPallets, Integer total) {
        Intent intent = new Intent(Alm_Tab_02Activity.this, Alm_Tab_03Activity.class);
        intent.putExtra("Id_Marca", intId_Marca);
        intent.putExtra("Id_Condicion", intId_Condicion);
        intent.putExtra("Cod_Prod", strCod_Prod);
        intent.putExtra("Producto", strProducto);
        intent.putExtra("Cod_Barra", strCod_Barra);
        intent.putExtra("Cod_UAPallet", strCod_UAPallet);
        intent.putExtra("Sector", strSector);
        intent.putExtra("Pasillo", strPasillo);
        intent.putExtra("Fila", strFila);
        intent.putExtra("Ubicacion", intId_Ubicacion);
        intent.putExtra("Columna", intColumna);
        intent.putExtra("Nivel", intNivel);
        intent.putExtra("Posicion", strPosicion);
        intent.putExtra("CountPallet", intCountPallets);
        intent.putExtra("Total", total);
        startActivityForResult(intent, 3);
    }

    @Override
    public void navigateToTab04(Integer intId_Marca, Integer intTotal_RowsUbi, Integer intId_Condicion, String strCod_Producto, String strProducto){
        Intent intent = new Intent(Alm_Tab_02Activity.this, Alm_Tab_04Activity.class);
        intent.putExtra("Id_Marca", intId_Marca);
        intent.putExtra("Total_RowsUbi", intTotal_RowsUbi);
        intent.putExtra("Id_Condicion",intId_Condicion);
        intent.putExtra("Cod_Producto",strCod_Producto);
        intent.putExtra("Producto",strProducto);
        startActivityForResult(intent, 4);
    }

    @Override
    public void showProgressDialog() {
        ProgressDialogRequest.show(Alm_Tab_02Activity.this);
    }

    @Override
    public void hideProgressDialog() {
        ProgressDialogRequest.dismiss();
    }

    @Override
    public void OnRemoveItem(UATransito ent, Integer position) {
        //adapter.addItem();
    }

    @Override
    public void OnAddItem(UATransito ent, Integer position) {
        //adapter.removeItem();
    }
}
