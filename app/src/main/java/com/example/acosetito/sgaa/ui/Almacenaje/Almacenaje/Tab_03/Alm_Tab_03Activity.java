package com.example.acosetito.sgaa.ui.Almacenaje.Almacenaje.Tab_03;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.ui.Main.MainActivity;

import java.util.ArrayList;

public class Alm_Tab_03Activity extends AppCompatActivity implements  AlmTab03View{

    private TextView tvCodProduct, tvProducto, tvSector, tvPasillo, tvFila, tvColumna, tvNivel, tvPosicion, tvUArestant;
    private EditText edtUbicacion;
    private Button btnPallets, btnRegistrar;

    private String strR_CodProduct, strR_Producto, strR_Sector, strR_Pasillo, strR_Fila, strR_CodBarra;
    private Integer intR_Id_Condicion, intR_Id_Ubicacion, intR_Columna, intR_Nivel, intR_Posicion, intR_TotalRowsUbis, intR_Origen;
    private ArrayList<UATransito> lstR_ItemPallets = new ArrayList<>();

    private AlmTab03Presenter presenter;
    private int ALM_TAB_03 = 03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alm__tab_03);
        initializeControls();
        getValueExtras();
        setDataToControls();
        presenter = new AlmTab03PresenterImpl(this);
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
            intR_Origen = extras.getInt("Origen");
            intR_TotalRowsUbis = extras.getInt("Total_RowsUbi");
            lstR_ItemPallets = extras.getParcelableArrayList("lstItemPallets");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemBack:
                return true;
            case R.id.itemRefresh:
                return true;
            case R.id.itemNext:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //endregion

    int counterPallets;

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

            counterPallets = 0;

            for (UATransito ent: lstR_ItemPallets){
                if (ent.isComplete.equals(false)){
                    strR_CodBarra = ent.UA_CodBarra;
                    presenter.registrarUAUbicacion(strR_CodBarra, intR_Id_Ubicacion, "ADMIN#369"); //Global.userName);
                }
            }

            /**
             int contadorPallets = 0;
             foreach (ListViewItem items in lstItemsPallets.Items)
             {
             if (items.BackColor != Color.GreenYellow)
             {
             codUAPallet = items.Text;

             var msjRegistraUbicacion = new GestionAlmacenajeMobile().RegistrarUAUbicacion(  codUAPallet
             , int.Parse(lblfila.Tag.ToString())//JMC 10.02.18
             , true
             , control.Global.Usuario);


             }
             }
             */
        }
    };

    void changeStateItemPallet(String strUA){
        for (UATransito ent: lstR_ItemPallets){
            if (ent.getUA_CodBarra().equals(strUA)){
                ent.isComplete = true;
            }
        }
    }

    @Override
    public void resultRegistrarUAUbicacion(Mensaje message, String strUA) {
        if (message.errNumber == 0)
        {
            counterPallets++;
            Toast.makeText(this, "Pallet/UA ubicada correctamente", Toast.LENGTH_SHORT).show(); //lblMensaje.Text = codUAPallet + " Pallet/UA ubicada correctamente...";
            changeStateItemPallet(strUA);
            tvUArestant.setText(String.format("{0} - {1}", intR_TotalRowsUbis, counterPallets)); //lbluprestantes.Text = string.Format("{0}-{1}", total.ToString(), contadorPallets);

            if (lstR_ItemPallets.size() == counterPallets){
                /**
                 RunOnUiThread (() => {
                 AlertDialog.Builder a = new AlertDialog.Builder(MainActivity);
                 a.SetTitle("Select");
                 a.SetAdapter(_Adapter, new EventHandler<DialogClickEventArgs>(ClosedDialog));
                 a.Create();
                 a.Show();
                 });
                 **/
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!isFinishing()){
                            Global.gListItemsPallet.clear();
                            Global.gListItemsPallet = Alm_Tab_03Activity.this.lstR_ItemPallets;
                            finish();
                        }
                    }
                });
            }
        }
        else
        {
            Toast.makeText(this, "Pallet/UA No ubicada", Toast.LENGTH_SHORT).show(); //lblMensaje.Text = codUAPallet + " Pallet/UA NO ubicada ...";
            //items.BackColor = Color.Red;
        }
    }

    @Override
    public void showFailureRequest(String result) {

    }

    @Override
    public void navigateToTab02(ArrayList<UATransito> listItemPallets) {
        Global.gListItemsPallet.clear();
        Global.gListItemsPallet = listItemPallets;
        /**Intent intent = null;
        intent = new Intent();
        intent.putParcelableArrayListExtra("listItemPallets", listItemPallets);
        setResult(RESULT_OK, intent);
        finish();**/

        /**switch (intR_Origen){
            case 2:
                intent = new Intent();
                intent.putParcelableArrayListExtra("listItemPallets", listItemPallets);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case 4:
                intent = new Intent(this, Alm_Tab_02Activity.class);
                intent.putParcelableArrayListExtra("listItemPallets", listItemPallets);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }**/
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onBackPressed() {
        presenter.navigateToTab02(lstR_ItemPallets);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ALM_TAB_03){
            Integer x = data.getExtras().getInt("key");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
