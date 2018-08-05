package com.example.acosetito.sgaa.ui.PreIngreso;

public interface AlmacenCDistPresenter {
    void getCenterByUser(String user);
    void getWarehouseByUser(String user, Integer intIdCentro);
    void navigateToMenu();
    void validateInputFields(Integer intIdCenter, Integer intIdWarehouse);
}
