package com.wposs.apirickandmorty.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wposs.apirickandmorty.R;

public class Extensions {
    public static void showErrorDialog(String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle("Error")
                .setIcon(R.drawable.ic_error_drawable)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            return false;
        }
    }

    public static void convertImageService(String url, ImageView viewImage, int size){
        try {
            Picasso
                    .get()
                    .load(url)
                    .centerCrop()
                    .resize(size, size)
                    .into(viewImage);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
