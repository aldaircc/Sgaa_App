package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Alm_Tab_02Activity extends AppCompatActivity implements AlmTab02View, IRVAlmTab02Adapter{

    TextView tvUbiOrigen, tvTotal;
    EditText edtCodBarra;
    Button btnInsert;
    RecyclerView rclItemPallet;
    TableLayout table;

            //ListView lvItemPallet;
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    RVAlmTab02Adapter adapter;
    ArrayList<UATransito> localList = new ArrayList<>();
    AlmTab02Presenter presenter;

    String strR_Ubicacion;
    Integer intR_IdUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alm__tab_02);
        initializeControls();
        getValueExtras();
        presenter = new AlmTab02PresenterImpl(this);
    }

    void initializeControls(){
        table = (TableLayout)findViewById(R.id.tableSample);
        tvUbiOrigen = (TextView)findViewById(R.id.tvUbiOrigen);
        tvTotal = (TextView)findViewById(R.id.tvTotal);
        edtCodBarra = (EditText)findViewById(R.id.edtCodBarra);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(insertOnClickListener);
        //lvItemPallet = (ListView)findViewById(R.id.lvItemPal);
        rclItemPallet = (RecyclerView)findViewById(R.id.rclItemPallet);
        rclItemPallet.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rclItemPallet.setLayoutManager(llm);
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
            }
        }
    };

    @Override
    public void resultValidateUA(ArrayList<UATransito> list) {
        if (list.size() > 0){
            localList = list;
            //adapter = new RVAlmTab02Adapter(this, list);
            //rclItemPallet.setAdapter(adapter);
            //LVAlmTab02Adapter adapter = new LVAlmTab02Adapter(this, list);
            //lvItemPallet.setAdapter(adapter);
            dataBindTable(list);
        }else{
            Toast.makeText(this, "Pallet/UA no registrada", Toast.LENGTH_SHORT).show();
        }
    }

    View.OnClickListener RemoveItemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // row is your row, the parent of the clicked button
            View row = (View) v.getParent();
            // container contains all the rows, you could keep a variable somewhere else to the container which you can refer to here
            ViewGroup container = ((ViewGroup)row.getParent());
            // delete the row and invalidate your view so it gets redrawn

            UATransito objEnt = new UATransito();
            for (UATransito ent: localList){
                if (ent.getUA_CodBarra().equals(v.getTag())){
                    objEnt = ent;
                }
            }
            localList.remove(objEnt);

            container.removeView(row);
            container.invalidate();
        }
    };

    @Override
    public void showFailureRequest(String result) {

    }

    @Override
    public void navigateToTab01() {

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
