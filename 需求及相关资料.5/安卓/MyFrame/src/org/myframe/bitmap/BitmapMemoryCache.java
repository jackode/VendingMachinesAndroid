/*
 * Copyright (c) 2014,KJFrameForAndroid Open Source Project,张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.myframe.bitmap;

import org.myframe.bitmap.ImageDisplayer.ImageCache;
import org.myframe.utils.SystemTool;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;

/**
 * 使用lru算法的Bitmap内存缓存池<br>
 * 
 * <b>创建时间</b> 2014-7-11
 * 
 * @author kymjs (https://github.com/kymjs)
 * @version 1.0
 */
public final class BitmapMemoryCache implements ImageCache {

    private MemoryLruCache<String, Bitmap> cache;

    public BitmapMemoryCache() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory());
        init(maxMemory / 8);
    }

    /**
     * @param maxSize
     *            使用内存缓存的内存大小，单位：kb
     */
    public BitmapMemoryCache(int maxSize) {
        init(maxSize);
    }

    /**
     * @param maxSize
     *            使用内存缓存的内存大小，单位：kb
     */
    @SuppressLint("NewApi")
    private void init(int maxSize) {
        cache = new MemoryLruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                super.sizeOf(key, value);
                if (SystemTool.getSDKVersion() >= 12) {
                    return value.getByteCount();
                } else {
                    return value.getRowBytes() * value.getHeight();
                }
            }
        };
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void removeAll() {
        cache.removeAll();
    }

    /**
     * 已过期，请使用putBitmap(String key, Bitmap bitmap)
     * 
     * @param key
     *            图片的地址
     * @param bitmap
     *            要缓存的bitmap
     */
    @Deprecated
    public void put(String key, Bitmap bitmap) {
        if (this.get(key) == null) {
            cache.put(key, bitmap);
        }
    }

    /**
     * 已过期，请使用gutBitmap(String key)
     * 
     * @param key
     *            图片的地址
     * @return
     */
    @Deprecated
    public Bitmap get(String key) {
        return cache.get(key);
    }

    /**
     * @param url
     *            图片的地址
     * @return
     */
    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    /**
     * @param url
     *            图片的地址
     * @param bitmap
     *            要缓存的bitmap
     */
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        if (this.getBitmap(url) == null) {
            cache.put(url, bitmap);
        }
    }
}
