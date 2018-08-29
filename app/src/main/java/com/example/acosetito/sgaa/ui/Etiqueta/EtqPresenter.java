package com.example.acosetito.sgaa.ui.Etiqueta;
import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;

import java.util.ArrayList;

public interface EtqPresenter {

    void listUMxProducto(Integer intIdProducto);
    void listSubAlmacenesXCuenta(Integer intIdCuenta, Integer intIdAlmacen);
    void listLabelsFormat(String[] list);
    void showDialogImpresora();
    void registerUAMasivo(ImpUA imp, Integer intId_Centro);
    void printListaEtq(ArrayList<ListaEtiqueta> lista, String strFormato, String strNombreImpresora, Boolean bolEsScript);
}
