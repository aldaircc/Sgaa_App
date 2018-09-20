package com.example.acosetito.sgaa.ui.Almacenaje;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;
import com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_01.Alm_Tab_01Activity;

public class OptionsAlmacenajeActivity extends AppCompatActivity implements OptionsView{

    OptionsPresenter presenter;
    Button btnAlm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_almacenaje);
        initializeControls();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recibo, menu);
        return true;
    }

    void initializeControls(){
        btnAlm = (Button)findViewById(R.id.btnAlm);
        btnAlm.setOnClickListener(almOnClickListener);
        presenter = new OptionsPresenterImpl(this);
    }

    View.OnClickListener almOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.navigateToTab01();
        }
    };

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Build.VERSION.SDK_INT > 11) {
            invalidateOptionsMenu();
            menu.findItem(R.id.itemBack).setVisible(true);
            menu.findItem(R.id.itemRefresh).setVisible(true);
            menu.findItem(R.id.itemPallet).setVisible(false);
            menu.findItem(R.id.itemRegInci).setVisible(false);
            menu.findItem(R.id.itemEtiqImpr).setVisible(false);
            menu.findItem(R.id.itemSelectImpr).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.itemBack:
                return true;
            case R.id.itemRefresh:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void navigateToTab01() {
        Intent intent = new Intent(OptionsAlmacenajeActivity.this, Alm_Tab_01Activity.class);
        startActivity(intent);
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
