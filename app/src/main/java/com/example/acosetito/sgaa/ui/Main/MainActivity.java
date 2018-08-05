package com.example.acosetito.sgaa.ui.Main;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.ui.PreIngreso.CDistAlmacenFragment;
import com.example.acosetito.sgaa.ui.Recibo.Tab_01.Recibo_Tab_01Activity;

public class MainActivity extends AppCompatActivity implements MainView, CDistAlmacenFragment.EditNameDialogListener{

    Button btnRecibo, btnAlmacenaje, btnPicking, btnEmbalaje, btnDespacho, btnTransferencia, btnEtiquetado, btnInventario, btnSalir, btnCDist;
    TextView tvPerfil;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPerfil = (TextView)findViewById(R.id.tvPerfil);
        tvPerfil.setText(Global.ApeNom);
        btnRecibo = (Button)findViewById(R.id.btnRecibo);
        btnAlmacenaje = (Button)findViewById(R.id.btnAlmacenaje);
        btnPicking = (Button)findViewById(R.id.btnPicking);
        btnEmbalaje = (Button)findViewById(R.id.btnEmbalaje);
        btnDespacho = (Button)findViewById(R.id.btnDespacho);
        btnTransferencia = (Button)findViewById(R.id.btnTransferencia);
        btnEtiquetado = (Button)findViewById(R.id.btnEtiquetado);
        btnInventario = (Button)findViewById(R.id.btnInventario);
        btnSalir = (Button)findViewById(R.id.btnSalir);
        btnCDist = (Button)findViewById(R.id.btnCDist);
        btnCDist.setText(Global.wareHouse);
        btnCDist.setPaintFlags(btnCDist.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnCDist.setOnClickListener(modalCDistOnClickListener);
        btnRecibo.setOnClickListener(menuOptionsOnClickListener);
        btnAlmacenaje.setOnClickListener(menuOptionsOnClickListener);
        btnPicking.setOnClickListener(menuOptionsOnClickListener);
        btnEmbalaje.setOnClickListener(menuOptionsOnClickListener);
        btnDespacho.setOnClickListener(menuOptionsOnClickListener);
        btnTransferencia.setOnClickListener(menuOptionsOnClickListener);
        btnEtiquetado.setOnClickListener(menuOptionsOnClickListener);
        btnInventario.setOnClickListener(menuOptionsOnClickListener);
        btnSalir.setOnClickListener(menuOptionsOnClickListener);
        presenter = new MainPresenterImpl(this);

        /**mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        Log.i("onCreate", "fin")**/
    }

    View.OnClickListener modalCDistOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showCDistDialog();
            /**android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
             CDistAlmacenFragment newFragment = new CDistAlmacenFragment();
             newFragment.setArguments(bundle);
             FragmentTransaction transaction = fragmentManager.beginTransaction();
             transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
             transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();**/
        }
    };

    private void showCDistDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("origin", "1");
        FragmentManager fm = getSupportFragmentManager();
        CDistAlmacenFragment cDistAlmacenFragment = new CDistAlmacenFragment();
        cDistAlmacenFragment.setArguments(bundle);
        cDistAlmacenFragment.show(fm, "fragment_CDist");
        /**CDistAlmacenFragment.newInstance();**/
    }

    View.OnClickListener menuOptionsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.navigateToOption(view);
        }
    };

    @Override
    public void navigateToOption(View view) {
        String message = "";
        Intent intent;

        switch (view.getId()){
            case R.id.btnRecibo:
                intent = new Intent(this, Recibo_Tab_01Activity.class);
                //intent = new Intent(this, Main3Activity.class);
                startActivity(intent);
                break;
            case R.id.btnAlmacenaje:
                break;
            case R.id.btnPicking:
                break;
            case R.id.btnEmbalaje:
                break;
            case R.id.btnDespacho:
                message = "Go to despacho module";
                break;
            case R.id.btnTransferencia:
                message = "Go to Transferencia module";
                //intent = new Intent(this, TransferenciaActivity.class);
                //startActivity(intent);
                break;
            case R.id.btnEtiquetado:
                break;
            case R.id.btnInventario:
                break;
            case R.id.btnSalir:
                break;
            default:
                break;
        }

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        Log.i("onFinishEditDialog", inputText);
        btnCDist.setText(inputText);
        //Toast.makeText(this, "Hi, "+ inputText, Toast.LENGTH_SHORT ).show();
    }
}
