package com.example.acosetito.sgaa.ui.Login;

public interface LoginPresenter {
    void validateUser(String user, String password, Integer idTerminal);
    void validateInputFields(String user, String password);
}
