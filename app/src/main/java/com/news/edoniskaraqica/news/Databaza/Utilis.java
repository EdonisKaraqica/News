package com.news.edoniskaraqica.news.Databaza;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Edonis Karaçica on 7/6/2017.
 */

public class Utilis {
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE );
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnectedOrConnecting();
    }

    public static byte[] getPictureByteOfArray(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.PNG,0,byteArrayOutputStream );
        return byteArrayOutputStream.toByteArray();
    }
    public static Bitmap getBitmapFromByte(byte[] image){
        return BitmapFactory.decodeByteArray( image,0,image.length );
    }
}
