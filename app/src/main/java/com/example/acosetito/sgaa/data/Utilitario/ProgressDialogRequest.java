package com.example.acosetito.sgaa.data.Utilitario;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.acosetito.sgaa.R;

public class ProgressDialogRequest {

    private static ProgressDialog progressDialog;

    public static void show(Context context){//, int messageResourceId) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait.");
        progressDialog.setCancelable(false);
        progressDialog.show();

        /**
         //Old Version
         if (progressDialog != null) {
            progressDialog.dismiss();
        }

        int style;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            style = android.R.style.Theme_Material_Light_Dialog;
        } else {
            //noinspection deprecation
            style = ProgressDialog.THEME_HOLO_LIGHT;
        }

        progressDialog = new ProgressDialog(context);//, style);
        //progressDialog.setMessage(context.getResources().getString(messageResourceId));
        //progressDialog.setMessage("XXX");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.setContentView(R.layout.custom_progressdialog);**/
    }

    public static void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
