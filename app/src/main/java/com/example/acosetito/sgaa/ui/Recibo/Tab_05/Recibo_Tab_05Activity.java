package com.example.acosetito.sgaa.ui.Recibo.Tab_05;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;

public class Recibo_Tab_05Activity extends AppCompatActivity implements ReciboTab05View{

    EditText edtEtqPallet;
    Button btnEtqPallet, btnRegister;
    ReciboTab05Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo__tab_05);
        initializedControls();

        /**ProgressDialog progressDialog = null;
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.custom_progressdialog);
        //se ppdrá cerrar simplemente pulsando back
        progressDialog.setCancelable(true);**/

        presenter = new ReciboTab05PresenterImpl(this);
    }

    void initializedControls(){
        edtEtqPallet = (EditText)findViewById(R.id.edtEtqPallet);
        btnEtqPallet = (Button)findViewById(R.id.btnEtqPallet);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnEtqPallet.setOnClickListener(etqPalletOnClickListener);
        btnRegister.setOnClickListener(registerOnClickListener);
    }

    View.OnClickListener etqPalletOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    View.OnClickListener registerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.validatePallet(edtEtqPallet.getText().toString(), 2); //Global.IdWarehouse);
        }
    };

    @Override
    public void showResultValidatePallet(Mensaje message) {

        if (message.valor1 >= -1){
            /**
             if (palletcausal)
             {
             CerrarPalletCausal();
             }
             else
             {
             CerrarPallet();
             }
             txtEtqPallet3.Focus();
             **/
        }else{
            Toast.makeText(this, "El pallet escaneado no existe", Toast.LENGTH_SHORT).show();
            edtEtqPallet.requestFocus();
            edtEtqPallet.selectAll();
        }
        /**
         if (msjValida.valor1 >= -1)
         {
         if (palletcausal)
         {
         CerrarPalletCausal();
         }
         else
         {
         CerrarPallet();
         }

         txtEtqPallet3.Focus();
         }
         else
         {
         MessageBox.Show("El pallet escaneado no existe",
         "Error", MessageBoxButtons.OK, MessageBoxIcon.Hand, MessageBoxDefaultButton.Button1);
         txtEtqPallet3.Focus();
         txtEtqPallet3.SelectAll();
         }
         **/
    }

    @Override
    public void showFailureValidatePallet(String result) {

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
