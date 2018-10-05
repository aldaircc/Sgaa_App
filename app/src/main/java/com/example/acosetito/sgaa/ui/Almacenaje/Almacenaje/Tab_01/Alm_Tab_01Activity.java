package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.Almacenaje.RVAlmTab01Adapter;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVAlmTab01Adapter;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UbicacionTransito;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_02.Alm_Tab_02Activity;

import java.util.ArrayList;

public class Alm_Tab_01Activity extends AppCompatActivity implements AlmTab01View, IRVAlmTab01Adapter{

    private RecyclerView rclTransito;
    private TextView tvAlmacen;
    private AlmTab01Presenter presenter;
    private RVAlmTab01Adapter adapter;
    private ArrayList<UbicacionTransito> listItems = new ArrayList<>();
    private int ALM_TAB_01 = 01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alm__tab_01);
        initializeControls();
        adapter = new RVAlmTab01Adapter(this, listItems);
        rclTransito.setAdapter(adapter);
        presenter = new AlmTab01PresenterImpl(this);
        presenter.sourceDataUbicacionTransito(2);
    }

    void initializeControls(){
        tvAlmacen = (TextView)findViewById(R.id.tvAlmacen);
        rclTransito = (RecyclerView)findViewById(R.id.rclTransito);
        rclTransito.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rclTransito.setLayoutManager(llm);
    }

    @Override
    public void sourceDataUbicacionTransito(ArrayList<UbicacionTransito> list) {
        adapter.setBaseData(list);
    }

    @Override
    public void showFailureRequest(String result) {

    }

    @Override
    public void navigateToTab02(String strUbicacion, Integer intIdUbicacion) {
        Intent intent = new Intent(Alm_Tab_01Activity.this, Alm_Tab_02Activity.class);
        intent.putExtra("Ubicacion", strUbicacion);
        intent.putExtra("IdUbicacion", intIdUbicacion);
        startActivityForResult(intent, ALM_TAB_01);
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
    public void onUbicarItem(UbicacionTransito ent) {
        presenter.navigateToTab02(ent.getUbicacion(), ent.getId_Ubicacion());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ALM_TAB_01){
            Integer x = data.getExtras().getInt("key");
            //Do something
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}