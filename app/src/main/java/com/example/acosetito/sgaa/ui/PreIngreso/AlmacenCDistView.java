package com.example.acosetito.sgaa.ui.PreIngreso;
import com.example.acosetito.sgaa.data.Model.Almacen;
import com.example.acosetito.sgaa.data.Model.CentroDistribucion;
import java.util.List;

public interface AlmacenCDistView {
    void sourceCentroDistribucion(List<CentroDistribucion> lstCD);
    void sourceWarehouse(List<Almacen> lstWarehouse);
    void showFailureGetCentrosDistribucion(String result);
    void showFailureGetWarehouse(String result);
    void navigateToMenu();
    void showMessageValidation(String result);
}
