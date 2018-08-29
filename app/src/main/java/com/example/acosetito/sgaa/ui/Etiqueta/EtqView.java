package com.example.acosetito.sgaa.ui.Etiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;
import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;
import java.util.ArrayList;

public interface EtqView {
    void sourceUMxProducto(ArrayList<UMxProducto> list);
    void sourceSubAlmacenXCuenta(ArrayList<SubAlmacenXCuenta> list);
    void sourceLabelsFormat(String[] list);
    void showResultRegisterMasivo(ArrayList<ImpUA> list);
    void showResultPrintEtq(Mensaje message);
    void showFailureRequest(String result);
    void showDialogImpresora();
    void showProgressDialog();
    void hideProgressDialog();
}
