package com.example.acosetito.sgaa.ui.Etiqueta;
import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;
import java.util.ArrayList;

public interface EtqView {
    void sourceUMxProducto(ArrayList<UMxProducto> list);
    void sourceSubAlmacenXCuenta(ArrayList<SubAlmacenXCuenta> list);
    void showFailureRequest(String result);
    void showProgressDialog();
    void hideProgressDialog();
}
