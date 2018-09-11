package com.example.acosetito.sgaa.ui.Almacenaje;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.acosetito.sgaa.R;

public class OptionsAlmacenajeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_almacenaje);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recibo, menu);
        return true;
    }

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
}
