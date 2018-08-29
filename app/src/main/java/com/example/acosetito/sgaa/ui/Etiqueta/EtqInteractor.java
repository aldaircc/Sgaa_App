package com.example.acosetito.sgaa.ui.Etiqueta;
import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;
import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;
import java.util.ArrayList;
import java.util.HashMap;

public interface EtqInteractor {
    interface OnListenerEtq{
        void OnSuccessListUMxProducto(ArrayList<UMxProducto> list);
        void OnFailureListUMxProducto(String result);
        void OnSuccessListSubAlmacenesXCuenta(ArrayList<SubAlmacenXCuenta> list);
        void OnFailureListSubAlmacenesXCuenta(String result);
        void OnSuccessRegisterUAMasivo(ArrayList<ImpUA> list);
        void OnFailureRegisterUAMasivo(String result);
        void OnSuccessPrintListaEtq(Mensaje message);
        void OnFailurePrintListaEtq(String result);
    }
    void listUMxProducto(Integer intIdProducto, OnListenerEtq listener);
    void listSubAlmacenesXCuenta(Integer intIdCuenta, Integer intIdAlmacen, OnListenerEtq listener);
    void registerUAMasivo(ImpUA imp, Integer intId_Centro, OnListenerEtq listener);
    void printListaEtq(ArrayList<ListaEtiqueta> lista, String strFormato, String strNombreImpresora, Boolean bolEsScript, OnListenerEtq listener);
}
