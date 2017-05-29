package com.phone.bitmaputils.bitmapUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MLXPHONE on 2017/5/29.
 */

public class NetCacheUtils {
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public NetCacheUtils(LocalCacheUtils localCacheUtils,MemoryCacheUtils memoryCacheUtils){
        this.mLocalCacheUtils=localCacheUtils;
        this.mMemoryCacheUtils=memoryCacheUtils;
    }

    public void getBitmapFromNet(ImageView iv,String url){
        new BitmapTask().execute(iv,url);
    }

    class BitmapTask extends AsyncTask<Object,Void,Bitmap>{
        private ImageView iv;
        private String url;

        @Override
        protected Bitmap doInBackground(Object... params) {
            iv= (ImageView) params[0];
            url= (String) params[1];

            return downLoadBitmap(url);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap!=null){
                iv.setImageBitmap(bitmap);

                mLocalCacheUtils.setBitmapToLocal(url,bitmap);
                mMemoryCacheUtils.setBitmapToMemory(url,bitmap);
            }

        }

        private Bitmap downLoadBitmap(String url){
            HttpURLConnection conn=null;

            try {
                conn= (HttpURLConnection) new URL(url).openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");

                int responseCode=conn.getResponseCode();
                if (responseCode==200){
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize=2;
                    options.inPreferredConfig=Bitmap.Config.ARGB_4444;
                    Bitmap bitmap=BitmapFactory.decodeStream(conn.getInputStream());
                    return bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                conn.disconnect();
            }
            return null;
        }
    }

}
