package com.example.acosetito.sgaa.ui.Login;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Model.Usuario;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.ui.Main.MainActivity;
import com.example.acosetito.sgaa.ui.PreIngreso.CDistAlmacenFragment;

public class LoginActivity extends AppCompatActivity implements LoginView, CDistAlmacenFragment.EditNameDialogListener {

    private Button btnEnter, btnExit;
    private EditText edtUser, edtPassword;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Log.i("onCreate - Login", "start");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            setTitle("Inicio de sesión");
            edtUser = (EditText) findViewById(R.id.edtUser);
            edtPassword = (EditText) findViewById(R.id.edtPassword);
            btnEnter = (Button) findViewById(R.id.btnEnter);
            btnEnter.setOnClickListener(btnEnterOnClickListener);
            btnExit = (Button)findViewById(R.id.btnExit);
            btnExit.setOnClickListener(btnExitOnClickListener);
            presenter = new LoginPresenterImpl(this );
            Log.i("onCreate - Login", "finish");
        }catch (Exception e){
            Log.e("ERROR - LOGIN", e.getMessage());
        }
    }

    View.OnClickListener  btnEnterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.validateInputFields(edtUser.getText().toString(), edtPassword.getText().toString());
        }
    };

    View.OnClickListener btnExitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.exit(1);
        }
    };

    @Override
    public void navigateToMenu(Usuario user) {
        String message = "";
        if (user.getId_Perfil() != null){
            message = "Welcome: " + user.getApeNom();
            Global.userName = user.getUsuario();
            Global.ApeNom = user.getApeNom();
            showCDistDialog();
        }else{
            message = "Usuario y/o contraseñas son incorrectas";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showFailureLogin(String result) {
        Toast.makeText(LoginActivity.this,"Error: "+result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageValidation(String result) {
        if (result.trim().length() != 0){
            Toast.makeText(LoginActivity.this, "Validation:"+result, Toast.LENGTH_SHORT).show();
        }else{
            presenter.validateUser(edtUser.getText().toString(), edtPassword.getText().toString(), 2);//1);
        }
    }

    private void showCDistDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("origin", "0");
        FragmentManager fm = getSupportFragmentManager();
        CDistAlmacenFragment cDistAlmacenFragment = new CDistAlmacenFragment();
        cDistAlmacenFragment.setArguments(bundle);
        cDistAlmacenFragment.show(fm, "fragment_CDist");
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
