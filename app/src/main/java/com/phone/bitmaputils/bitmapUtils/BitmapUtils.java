package com.phone.bitmaputils.bitmapUtils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.phone.bitmaputils.R;

/**
 * Created by MLXPHONE on 2017/5/29.
 */

public class BitmapUtils {
    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public BitmapUtils(){
        mMemoryCacheUtils=new MemoryCacheUtils();
        mLocalCacheUtils=new LocalCacheUtils();
        mNetCacheUtils=new NetCacheUtils(mLocalCacheUtils,mMemoryCacheUtils);
    }

    public void disPlay(ImageView iv,String url){
        iv.setImageResource(R.mipmap.ic_launcher);
        Bitmap bitmap;

        bitmap=mMemoryCacheUtils.getBitmapFromMemory(url);

        if (bitmap!=null) {
            iv.setImageBitmap(bitmap);
            return;
        }

        bitmap=mLocalCacheUtils.getBitmapFromLocal(url);

        if (bitmap!=null){
            iv.setImageBitmap(bitmap);
            return;
        }

        mNetCacheUtils.getBitmapFromNet(iv,url);

    }

}

