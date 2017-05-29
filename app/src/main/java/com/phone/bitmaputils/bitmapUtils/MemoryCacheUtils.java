package com.phone.bitmaputils.bitmapUtils;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.lang.ref.SoftReference;

/**
 * Created by MLXPHONE on 2017/5/29.
 */

public class MemoryCacheUtils {
    private LruCache<String, Bitmap> mLruCache;

    public MemoryCacheUtils() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 8;

        mLruCache = new LruCache<String, Bitmap>((int) maxMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteCount = value.getByteCount();
                return byteCount;
            }
        };
    }
    public Bitmap getBitmapFromMemory(String url) {
        SoftReference<Bitmap> bitmapSoftReference = mLruCache.get(url);

        if (bitmapSoftReference != null) {
            Bitmap bitmap=bitmapSoftReference.get();
            return bitmap;
        }

        Bitmap bitmap=mLruCache.get(url);
        return bitmap;
    }

    public void setBitmapToMemory(String url,Bitmap bitmap){
        mLruCache.put(url,bitmap);
    }
}

