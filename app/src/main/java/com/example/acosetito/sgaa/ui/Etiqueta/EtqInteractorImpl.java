package com.example.acosetito.sgaa.ui.Etiqueta;

import com.example.acosetito.sgaa.data.Model.Impresora.ListaEtiqueta;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.ImpUA;
import com.example.acosetito.sgaa.data.Model.Recepcion.SubAlmacenXCuenta;
import com.example.acosetito.sgaa.data.Model.Recepcion.UMxProducto;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.WebService.ApiClient;
import com.example.acosetito.sgaa.data.WebService.ImpresionClient;
import com.example.acosetito.sgaa.data.WebService.ReciboClient;
import com.example.acosetito.sgaa.data.WebService.TablasEstaticasClient;
import com.example.acosetito.sgaa.data.WebService.UnidadMedidaClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EtqInteractorImpl implements EtqInteractor{

    @Override
    public void listUMxProducto(Integer intIdProducto, final OnListenerEtq listener) {
        UnidadMedidaClient umClient = (ApiClient.getApiClient(Global.baseUrl, Global.unidadMedidaService).create(UnidadMedidaClient.class));
        Call<ArrayList<UMxProducto>> call = umClient.listarUMxProducto(intIdProducto);
        call.enqueue(new Callback<ArrayList<UMxProducto>>() {
            @Override
            public void onResponse(Call<ArrayList<UMxProducto>> call, Response<ArrayList<UMxProducto>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessListUMxProducto(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UMxProducto>> call, Throwable t) {
                listener.OnFailureListUMxProducto(t.getMessage());
            }
        });
    }

    @Override
    public void listSubAlmacenesXCuenta(Integer intIdCuenta, Integer intIdAlmacen, final OnListenerEtq listener) {
        TablasEstaticasClient estaticasClient = (ApiClient.getApiClient(Global.baseUrl, Global.tablasEstaticasService).create(TablasEstaticasClient.class));
        Call<ArrayList<SubAlmacenXCuenta>> call = estaticasClient.listarSubAlmacenesXCuenta(intIdCuenta, intIdAlmacen);
        call.enqueue(new Callback<ArrayList<SubAlmacenXCuenta>>() {
            @Override
            public void onResponse(Call<ArrayList<SubAlmacenXCuenta>> call, Response<ArrayList<SubAlmacenXCuenta>> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessListSubAlmacenesXCuenta(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SubAlmacenXCuenta>> call, Throwable t) {
                listener.OnFailureListSubAlmacenesXCuenta(t.getMessage());
            }
        });
    }

    @Override
    public void registerUAMasivo(ImpUA imp, Integer intId_Centro, final OnListenerEtq listener) {
        ReciboClient reciboClient = (ApiClient.getApiClient(Global.baseUrl, Global.recepcionService).create(ReciboClient.class));
        HashMap<String, Object> obj = new HashMap<>();

        JsonObject json = new JsonObject();
        json.addProperty("Cantidad",  imp.getCantidad());
        json.addProperty("CantidadEtiqueta",  imp.getCantidadEtiqueta());
        json.addProperty("Codigo",  imp.getCodigo());
        json.addProperty("FechaEmision",  "/Date("+ imp.getFechaEmision().getTime() +")/");///Date(1335205592410)/);
        //json.addProperty("FechaRegistro",  "/Date("+imp.getFechaRegistro().getTime() + ")/");
        json.addProperty("FechaVencimiento", "/Date("+imp.getFechaVencimiento().getTime()+")/");//  /Date(1335205592410)/);
        json.addProperty("Id_Almacen",  imp.getId_Almacen());
        json.addProperty("Id_Causal",  imp.getId_Causal());
        json.addProperty("Id_Estado",  imp.getId_Estado());
        json.addProperty("Id_Producto",  imp.getId_Producto());
        json.addProperty("Id_SubAlmacen",  imp.getId_SubAlmacen());
        json.addProperty("Id_TerminalRF",  imp.getId_TerminalRF());
        json.addProperty("Id_Tx_Ubi",  imp.getId_Tx_Ubi());
        json.addProperty("Item",  imp.getItem());
        json.addProperty("LoteLab",  imp.getLoteLab());
        json.addProperty("LotePT",  imp.getLotePT());
        json.addProperty("PalletCodBarra",  imp.getPalletCodBarra());
        json.addProperty("Producto",  imp.getProducto());
        json.addProperty("Saldo",  imp.getSaldo());
        json.addProperty("SaldoEtiqueta",  imp.getSaldoEtiqueta());
        json.addProperty("UA_CodBarra",  imp.getUA_CodBarra());
        json.addProperty("UsuarioRegistro",  imp.getUsuarioRegistro());
        obj.put("imp", json);
        obj.put("idCentro", intId_Centro);
        Call<ArrayList<ImpUA>> call = reciboClient.registrarUAMasivo(obj);

        call.enqueue(new Callback<ArrayList<ImpUA>>() {
            @Override
            public void onResponse(Call<ArrayList<ImpUA>> call, Response<ArrayList<ImpUA>> response) {
                switch (response.code()){
                    case 200:
                        ArrayList<ImpUA> list = new ArrayList<>();
                        list = response.body();
                        listener.OnSuccessRegisterUAMasivo(list);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ImpUA>> call, Throwable t) {
                listener.OnFailureRegisterUAMasivo(t.getMessage());
            }
        });
    }

    @Override
    public void printListaEtq(ArrayList<ListaEtiqueta> lista, String strFormato, String strNombreImpresora, Boolean bolEsScript, final OnListenerEtq listener) {
        ImpresionClient impresionClient = (ApiClient.getApiClient(Global.baseUrlPrint, Global.impresoraService)).create(ImpresionClient.class);
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("lista", lista);
        obj.put("formato", strFormato);
        obj.put("nombreImpresora", strNombreImpresora);
        obj.put("esScript", bolEsScript);

        Call<Mensaje> call = impresionClient.imprimirListaEtiquetas(obj);
        call.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                switch (response.code()){
                    case 200:
                        listener.OnSuccessPrintListaEtq(response.body());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                listener.OnFailurePrintListaEtq(t.getMessage());
            }
        });
    }
}
