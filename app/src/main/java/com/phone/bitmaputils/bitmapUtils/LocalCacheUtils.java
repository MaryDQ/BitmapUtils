package com.phone.bitmaputils.bitmapUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MLXPHONE on 2017/5/29.
 */

//本地
public class LocalCacheUtils{
    private static final String CACHE_PATH= Environment.getExternalStorageState();

    public Bitmap getBitmapFromLocal(String url){
        String fileName=null;

        try {
            fileName=MD5Encoder.encode(url);
            File file=new File(CACHE_PATH,fileName);

            Bitmap bitmap= BitmapFactory.decodeStream(new FileInputStream(file));
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setBitmapToLocal(String url,Bitmap bitmap){

        try {
            String fileName=MD5Encoder.encode(url);
            File file=new File(CACHE_PATH,fileName);

            File parentFile=file.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }

            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

