package com.example.acosetito.sgaa.ui.PreIngreso;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Model.Almacen;
import com.example.acosetito.sgaa.data.Model.CentroDistribucion;
import com.example.acosetito.sgaa.data.Utilitario.Global;

import java.util.ArrayList;
import java.util.List;

public class CDistAlmacenFragment extends DialogFragment implements AlmacenCDistView {

    Button btnAceptar, btnCancelar;
    private Spinner spnWarehouse, spnCD;
    private List<CentroDistribucion> lstCDx;
    private List<Almacen> lstWarehousex;
    private List<String> arrCenters = new ArrayList<String>();
    private List<String> arrWarehouse = new ArrayList<String>();
    private AlmacenCDistPresenter presenter;
    private Integer intIdCenter = 0, intIdWarehouse = 0, intOrigin = 0;
    private String strCenter, strWarehouse;
    private Boolean  isFirstTime = true, isOpenedFirst = true;

    public CDistAlmacenFragment() {
        // Required empty public constructor
    }

    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cdist_almacen, container, false);
        //if (getArguments() != null)
        intOrigin = Integer.parseInt(getArguments().getString("origin").toString());
        return rootView;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // ...
        // 2. Setup a callback when the "Done" button is pressed on keyboard
        Log.i("CDListAlmacenFragment - onViewCreated", "inicio");
        btnAceptar = (Button) view.findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(btnAceptarOnClickListener);
        btnCancelar = (Button) view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(btnCancelarOnClickListener);
        spnCD = (Spinner) view.findViewById(R.id.spnCD);
        spnCD.setOnItemSelectedListener(spnCDOnItemSelectedListener);
        spnWarehouse = (Spinner) view.findViewById(R.id.spnWarehouse);
        spnWarehouse.setOnItemSelectedListener(spnWareHouseOnItemSelectedListener);
        presenter = new AlmacenCDistPresenterImpl(this);
        presenter.getCenterByUser(Global.userName);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_SPLIT_TOUCH);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public interface EditNameDialogListener{
        void onFinishEditDialog(String inputText);
    }

    View.OnClickListener btnAceptarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presenter.validateInputFields(intIdCenter, intIdWarehouse);
        }
    };

    View.OnClickListener btnCancelarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    };

    AdapterView.OnItemSelectedListener spnCDOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            try {
                intIdCenter = (i == 0) ? 0 : lstCDx.get(i-1).getId_Centro();
                strCenter = (i == 0) ? "" : lstCDx.get(i-1).getCentro();
                Global.indexIdCentro = i;
                Global.idCentro = intIdCenter;
                Global.centerName = strCenter;
                presenter.getWarehouseByUser(Global.userName, intIdCenter);
            }catch (Exception ex){

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    AdapterView.OnItemSelectedListener spnWareHouseOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            try {
                intIdWarehouse = (i == 0) ? 0 : lstWarehousex.get(i-1).getId_Almacen();
                strWarehouse = (i == 0) ? "" : lstWarehousex.get(i-1).getAlmacen();
                Global.indexIdWarehouse = i;
                Global.IdWarehouse = intIdWarehouse;
                Global.wareHouse = strWarehouse;
            }catch (Exception ex){

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    @Override
    public void sourceCentroDistribucion(List<CentroDistribucion> lstCD) {
        lstCDx = lstCD;
        for (CentroDistribucion obj : lstCDx)
            arrCenters.add(obj.getCentro());
        arrCenters.add(0, "Ninguno");
        spnCD.setPrompt("Seleccione centro");
        spnCD.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.spinner_item, arrCenters));

        if (intOrigin == 1){
            spnCD.setSelection(Global.indexIdCentro);
        }
    }

    @Override
    public void sourceWarehouse(List<Almacen> lstWarehouse) {
        lstWarehousex = lstWarehouse;
        arrWarehouse.clear();
        for (Almacen obj : lstWarehousex)
            arrWarehouse.add(obj.getAlmacen());
        arrWarehouse.add(0, "Ninguno");
        spnWarehouse.setPrompt("Seleccione almacén");
        spnWarehouse.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.spinner_item, arrWarehouse));

        if (intOrigin == 1 && isOpenedFirst == true){
            spnWarehouse.setSelection(Global.indexIdWarehouse);
            isOpenedFirst = false;
        }
    }

    @Override
    public void showFailureGetCentrosDistribucion(String result) {
        Toast.makeText(getContext(),"Error getCentrosDistribucion:" + result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailureGetWarehouse(String result) {
        Toast.makeText(getContext(),"Error getWarehouse:" + result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMenu() {
        EditNameDialogListener listener = (EditNameDialogListener) getActivity();
        listener.onFinishEditDialog(strWarehouse);
    }

    @Override
    public void showMessageValidation(String result) {
        if (result.trim().length() != 0){
            Toast.makeText(getContext(), "Validation:"+result, Toast.LENGTH_SHORT).show();
        }else{
            presenter.navigateToMenu();
            dismiss();
        }
    }
}
