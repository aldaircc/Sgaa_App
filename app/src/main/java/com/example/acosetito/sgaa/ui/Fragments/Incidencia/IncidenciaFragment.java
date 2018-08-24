package com.example.acosetito.sgaa.ui.Fragments.Incidencia;


import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.SPCausalAdapter;
import com.example.acosetito.sgaa.data.Model.Mensaje;
import com.example.acosetito.sgaa.data.Model.Recepcion.Causal;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlPendiente;
import com.example.acosetito.sgaa.data.Model.Recepcion.ControlUsuarioPendiente;
import com.example.acosetito.sgaa.data.Model.Recepcion.ListarRecepcionesXUsuario;
import com.example.acosetito.sgaa.data.Utilitario.Global;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IncidenciaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncidenciaFragment extends DialogFragment implements IncidenciaView {

    TextView tvNroOrden;
    Spinner spnCausal;
    EditText edtObser;
    Button btnParar;

    ArrayList<ControlPendiente> list = new ArrayList<>();
    SPCausalAdapter adapter;
    IncidenciaPresenter presenter;
    Integer intId_Causal = 0, intTipo = 0, intId_LineaMAQ = 0;
    String strNro_Orden, strId_Tx;
    Boolean bolFlagPausa = false;

    /** Variables received **/
     String strR_Id_Tx, strR_NumOrden, strR_Cuenta, strR_Proveedor;
     Boolean bolR_FlagPausa;
     Integer intR_Id_TipoMovimiento;

    Date dtFechaIni = null;

    Integer moduloTab = 0;
    ListarRecepcionesXUsuario objReceivedTab01;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public IncidenciaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static IncidenciaFragment newInstance(String param1, String param2) {
        IncidenciaFragment fragment = new IncidenciaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_incidencia, container, false);
        tvNroOrden = (TextView)rootView.findViewById(R.id.tvNroOrden);
        spnCausal = (Spinner)rootView.findViewById(R.id.spnCausal);
        spnCausal.setOnItemSelectedListener(spnOnItemSelected);
        edtObser = (EditText)rootView.findViewById(R.id.edtObser);
        btnParar = (Button)rootView.findViewById(R.id.btnParar);
        btnParar.setOnClickListener(btnProcessOnClickListener);

        Bundle bundle = getArguments();
        if (bundle != null){
            /**strId_Tx = bundle.getString("Id_Tx", "");
            strNro_Orden = bundle.getString("NumOrden", "");
            bolFlagPausa = bundle.getBoolean("FlagPausa", false);
            moduloTab = bundle.getInt("moduloTab", 0);**/

            strR_Id_Tx = bundle.getString("Id_Tx", "");
            strR_NumOrden = bundle.getString("NumOrden", "");
            strR_Cuenta = bundle.getString("Cuenta", "");
            strR_Proveedor = bundle.getString("Proveedor", "");
            intR_Id_TipoMovimiento = bundle.getInt("Id_TipoMovimiento", 0);
            bolR_FlagPausa= bundle.getBoolean("FlagPausa", false);
            moduloTab = bundle.getInt("moduloTab", 0);
        }

        tvNroOrden.setText(strR_Id_Tx); //strId_Tx);
        presenter = new IncidenciaPresenterImpl(this);
        presenter.listCausalXModulo(0,1);

        if (intTipo == 1){
            presenter.searchControlUsuario(strR_Id_Tx, "ADMIN"); //strId_Tx, "ADMIN");//Global.userName);
        }else {
            presenter.searchControlPendiente(strR_Id_Tx,"ADMIN"); // "ADMIN");//Global.userName);
        }

        if (bolR_FlagPausa){//bolFlagPausa){
            btnParar.setText("Continuar");
            spnCausal.setEnabled(false);
        }else {
            btnParar.setText("Detener");
            spnCausal.setEnabled(true);
        }

        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_SPLIT_TOUCH);
        return dialog;
    }

    @Override
    public void onResume() {
        // Get existing layout params for the window
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int heigth = size.y;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, (int) (heigth * 0.75));
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }

    Spinner.OnItemSelectedListener spnOnItemSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            intId_Causal = adapter.getItems().get(i).getId_Causal();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    View.OnClickListener btnProcessOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (intTipo == 1){
                presenter.registerControlOP(strR_Id_Tx, intId_LineaMAQ, intId_Causal, "ADMIN"/** Global.userName **/,
                        edtObser.getText().toString(), bolR_FlagPausa);//bolFlagPausa);
            }else{
                presenter.registerControl(strR_Id_Tx, intId_Causal, "ADMIN"/** Global.userName **/,
                        1, edtObser.getText().toString(), bolR_FlagPausa);//bolFlagPausa);
            }
        }
    };

    @Override
    public void showResultListarCausalXModulo(ArrayList<Causal> list) {
        /**Causal obj = new Causal();
        obj.setId_Causal(0);
        obj.setCausal("Ninguno");
        list.add(0, obj);**/

        adapter = new SPCausalAdapter(getContext(), R.layout.spinner_causal_item, list);
        spnCausal.setPrompt("Seleccione causal");
        spnCausal.setAdapter(adapter);
    }

    @Override
    public void showFailureRequest(String result) {

    }

    @Override
    public void showResultRegisterControlOP(Mensaje message) {
        if (message.errNumber == 0){
            String content = /**(bolFlagPausa)**/ (bolR_FlagPausa) ? "Continuar transacción":"Transacción detenida";
            Toast.makeText(this.getActivity(), content, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this.getActivity(), message.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showResultRegisterControl(Mensaje message) {
        if (message.errNumber == 0){
            String content = /**(bolFlagPausa)**/ (bolR_FlagPausa) ? "Continuar transacción":"Transacción detenida";
            Toast.makeText(this.getActivity(), content, Toast.LENGTH_SHORT).show();

            IncidenciaDialogListener listener = (IncidenciaDialogListener) getActivity();
            //Debes enviar el valor del flagPausa actualizado
            listener.onCompleteEditDialog(strR_Id_Tx, strR_NumOrden, !bolR_FlagPausa, strR_Cuenta, strR_Proveedor, intR_Id_TipoMovimiento);

            this.dismiss();
        }else{
            Toast.makeText(this.getActivity(), message.message, Toast.LENGTH_SHORT).show();
        }
    }

    Integer getPositionOfCausal(int intId_Causal){
        Integer pos = 0;
        for (int i = 0 ; i < list.size(); i++){
            if (list.get(i).getId_Causal().equals(intId_Causal)){
                pos = i;
            }
        }
        return pos;
    }

    @Override
    public void showResultSearchControlPendiente(ArrayList<ControlPendiente> list) {
        if (list.size() != 0){
            ControlPendiente obj = list.get(0);
            edtObser.setText(obj.getObservacion());
            /**bolFlagPausa**/ bolR_FlagPausa = obj.getFlagPausa();
            dtFechaIni = obj.getFechaHoraInicio();
            spnCausal.setSelection(getPositionOfCausal(obj.getId_Causal()));
            btnParar.setText("Continuar");
        }
    }

    @Override
    public void showResultSearchControlUsuario(ArrayList<ControlUsuarioPendiente> list) {
        if (list.size() != 0){
            ControlUsuarioPendiente obj = list.get(0);
            edtObser.setText(obj.getObservacion());
            /**bolFlagPausa**/ bolR_FlagPausa = obj.getFlagPausa();
            dtFechaIni = obj.getFechaHoraInicio();
            spnCausal.setSelection(getPositionOfCausal(obj.getId_Causal()));
            btnParar.setText("Continuar");
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    public interface IncidenciaDialogListener{
        void onCompleteEditDialog(String strId_Tx, String strNumOrden,
                                  Boolean bolFlagPausa, String strCuenta,
                                  String strProveedor, Integer intId_TipoMovimiento);
    }
}
