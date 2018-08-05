package com.example.acosetito.sgaa.ui.Recibo.Tab_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;

public class Recibo_Tab_03Activity extends AppCompatActivity {

    Button btnOption;
    PopupMenu ddMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_03);
        initializeControls();
    }

    void initializeControls(){
        btnOption = (Button)findViewById(R.id.btnOption);
        btnOption.setOnClickListener(optionsOnClickListener);

        ddMenu = new PopupMenu(getApplicationContext(), btnOption);
        ddMenu.getMenuInflater().inflate(R.menu.drop_down_menu, ddMenu.getMenu());
        ddMenu.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    View.OnClickListener optionsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ddMenu.show();
        }
    };

    PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            Toast.makeText(getApplicationContext(), "You have clicked "+ menuItem.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }
    };
}
