package com.example.acosetito.sgaa.ui.Login;
import com.example.acosetito.sgaa.data.Model.Usuario;

public interface LoginView {
    void navigateToMenu(Usuario user);

    void showFailureLogin(String result);

    void showMessageValidation(String result);
}
