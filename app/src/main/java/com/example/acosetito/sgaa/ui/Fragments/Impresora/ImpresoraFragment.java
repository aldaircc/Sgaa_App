package com.example.acosetito.sgaa.ui.Fragments.Impresora;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.acosetito.sgaa.R;
import com.example.acosetito.sgaa.data.Adapter.DividerItemDecoration;
import com.example.acosetito.sgaa.data.Adapter.Fragment.RVImpresoraAdapter;
import com.example.acosetito.sgaa.data.Adapter.Interfaces.IRVImpresoraAdapter;
import com.example.acosetito.sgaa.data.Model.Impresora.AccesosImpresoraXUsuario;
import com.example.acosetito.sgaa.data.Utilitario.Global;
import com.example.acosetito.sgaa.data.Utilitario.ProgressDialogRequest;

import java.util.ArrayList;

public class ImpresoraFragment extends DialogFragment implements IRVImpresoraAdapter, ImpresoraView{

    RecyclerView rclImpresora;
    RVImpresoraAdapter adapter;
    ImpresoraPresenter presenter;

    public ImpresoraFragment() {
        // Required empty public constructor
    }

    public static ImpresoraFragment newInstance() {
        ImpresoraFragment fragment = new ImpresoraFragment();
        return  fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_impresora, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        rclImpresora = (RecyclerView) view.findViewById(R.id.rclImpresora);
        adapter = new RVImpresoraAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        rclImpresora.setLayoutManager(mLayoutManager);
        rclImpresora.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.item_separator)));

        rclImpresora.setAdapter(adapter);
        presenter = new ImpresoraPresenterImpl(this);
        presenter.getListImpresoraXUsuario("ADMIN");//Global.userName);

        /**getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);**/
    }


    @Override
    public void OnClickBtnSelect(final AccesosImpresoraXUsuario ent) {
        Toast.makeText(getActivity().getApplicationContext(),
                "Impresora: "+ent.getNombre(), Toast.LENGTH_SHORT).show();

        final AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(this.getActivity());
        alertDialogBuilder.setMessage("Ha seleccionado la impresora " + ent.getNombre() + ". ¿Desea continuar?\"");
        alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Global.intId_Impresora = ent.getId_Impresora();
                Global.strIpImpresora = ent.getIpImpresora();
                Global.nameImpresora = ent.getNombre();
                Global.intPuertoImpresora = Integer.parseInt(ent.getPuertoImpresora());
                endFragment();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public void sourceImpresora(ArrayList<AccesosImpresoraXUsuario> list) {
        adapter.setBaseData(list);
    }

    @Override
    public void showFailureImpresora(String result) {
        Toast.makeText(this.getActivity(), "Error:" + result, Toast.LENGTH_SHORT).show();
    }

    void endFragment(){
        this.dismiss();
    }

    @Override
    public void showProgressDialog() {
        ProgressDialogRequest.show(this.getContext());
    }

    @Override
    public void hideProgressDialog() {
        ProgressDialogRequest.dismiss();
    }
}
