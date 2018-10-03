package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;

public class Alm_Tab_03Activity extends AppCompatActivity {

    private TextView tvCodProduct, tvProducto, tvSector, tvPasillo, tvFila, tvColumna, tvNivel, tvPosicion, tvUArestant;
    private EditText edtUbicacion;
    private Button btnPallets, btnRegistrar;

    private String strR_CodProduct, strR_Producto, strR_Sector, strR_Pasillo, strR_Fila, strR_CodBarra;
    private Integer intR_Id_Condicion, intR_Id_Ubicacion, intR_Columna, intR_Nivel, intR_Posicion, intR_TotalRowsUbis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alm__tab_03);
        initializeControls();
        getValueExtras();
        setDataToControls();
    }

    void initializeControls(){
        tvCodProduct = (TextView)findViewById(R.id.tvCodProduct);
        tvProducto = (TextView)findViewById(R.id.tvProducto);
        tvSector = (TextView)findViewById(R.id.tvSector);
        tvPasillo = (TextView)findViewById(R.id.tvPasillo);
        tvFila = (TextView)findViewById(R.id.tvFila);
        tvColumna = (TextView)findViewById(R.id.tvColumna);
        tvNivel = (TextView)findViewById(R.id.tvNivel);
        tvPosicion = (TextView)findViewById(R.id.tvPosicion);
        tvUArestant = (TextView)findViewById(R.id.tvUArestant);
        edtUbicacion = (EditText)findViewById(R.id.edtUbicacion);
        btnPallets = (Button)findViewById(R.id.btnPallets);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(btnRegistrarOnClickListener);
    }

    void getValueExtras(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            intR_Id_Condicion = extras.getInt("Id_Condicion");
            strR_CodProduct = extras.getString("Cod_Producto");
            strR_Producto = extras.getString("Producto");
            strR_CodBarra = extras.getString("Cod_Barra");
            strR_Sector = extras.getString("Sector");
            strR_Pasillo = extras.getString("Pasillo");
            strR_Fila = extras.getString("Fila");
            intR_Id_Ubicacion = extras.getInt("Id_Ubicacion");
            intR_Columna = extras.getInt("Columna");
            intR_Nivel = extras.getInt("Nivel");
            intR_Posicion = extras.getInt("Posicion");
            /**
             intent.putExtra("Cod_UAPallet",strCod_UAPallet);
             intent.putExtra("CountPallets", intCountPallets);
             **/
            intR_TotalRowsUbis = extras.getInt("Total_RowsUbi");
        }
    }

    void setDataToControls(){
        tvCodProduct.setText(strR_CodProduct);
        tvProducto.setText(strR_Producto);
        tvSector.setText(strR_Sector);
        tvPasillo.setText(String.format("%02d", Integer.parseInt(strR_Pasillo)));
        tvPasillo.setTag(strR_CodBarra);
        tvFila.setText(strR_Fila);
        tvColumna.setText(String.format("%02d", intR_Columna));
        tvNivel.setText(String.format("%02d", intR_Nivel));
        tvPosicion.setText(String.format("%02d", intR_Posicion));

    }

    View.OnClickListener btnRegistrarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (tvPasillo.getTag().toString().trim().equals(edtUbicacion.getText().toString().trim())){

            }else{
                Toast.makeText(Alm_Tab_03Activity.this, "La ubicación no es correcta", Toast.LENGTH_SHORT).show();
                edtUbicacion.setText("");
                edtUbicacion.requestFocus();
                return;
            }

            /**
             if (lblpasillo.Tag.ToString().Trim() == txtUbicacion.Text.Trim())
             {
             txtcodpalletua.Enabled = true;
             txtcodpalletua.Focus();
             //txtUbicacion.Enabled = false;
             }
             else
             {
             MessageBox.Show("La ubicación no es correcta", "Aviso", MessageBoxButtons.OK, MessageBoxIcon.Exclamation, MessageBoxDefaultButton.Button1);
             txtUbicacion.Text = string.Empty;
             txtUbicacion.Focus();
             return;
             }
             **/
        }
    };
}
