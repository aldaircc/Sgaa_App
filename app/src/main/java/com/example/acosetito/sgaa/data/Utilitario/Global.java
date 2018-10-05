package com.example.acosetito.sgaa.data.Utilitario;

import com.example.acosetito.sgaa.data.Model.Almacenaje.UATransito;

import java.util.ArrayList;

public class Global {
    public static String userName;
    public static String ApeNom;
    public static Integer idCentro;
    public static Integer indexIdCentro;
    public static String centerName;
    public static Integer IdWarehouse;
    public static Integer indexIdWarehouse;
    public static String wareHouse;
    public static String nameImpresora;
    public static Integer intId_Impresora;
    public static Integer intPuertoImpresora;
    public static String strIpImpresora;
    public static ArrayList<UATransito> gListItemsPallet = new ArrayList<>(); //It only used for updating Item Pallet's status

    public static final String baseUrl = "http://172.16.32.15:8085/SGAA_WCF/";
    public static final String baseUrlPrint = "http://172.16.32.3/SGAA_WCF_PRINTV2/";//"http://172.16.32.15/SGAA_WCF_PRINT/";
    public static final String usuarioService = "UsuarioService.svc";
    public static final String pickingService = "PickingService.svc";
    public static final String unidadMedidaService = "UnidadMedidaService.svc";
    public static final String recepcionService = "RecepcionService.svc";
    public static final String impresoraService = "Impresiones.svc";
    public static final String produccionService = "ProduccionService.svc";
    public static final String tablasEstaticasService = "TablasEstaticasService.svc";
    public static final String almacenajeService = "AlmacenajeService.svc";
}
