package com.example.acosetito.sgaa.ui.Login;

import com.example.acosetito.sgaa.data.Model.Usuario;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnValidateUserListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public  LoginPresenterImpl(LoginView view)
    {
        this.loginView = view;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void validateUser(String user, String password, Integer idTerminal) {
        loginInteractor.validateUser(user, password, idTerminal, this);
    }

    @Override
    public void validateInputFields(String user, String password) {
        loginInteractor.validateInputFields(user, password, this);
    }

    @Override
    public void OnSuccessValidateUser(Usuario user) {
        loginView.navigateToMenu(user);
    }

    @Override
    public void OnFailureValidateUser(String result) {
        loginView.showFailureLogin(result);
    }

    @Override
    public void OnSuccessValidateInputFields(String result) {
        loginView.showMessageValidation(result);
    }

    @Override
    public void OnFailureValidateInputFields(String result) {
        loginView.showMessageValidation(result);
    }
}