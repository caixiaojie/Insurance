package com.clkj.insurance.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lf.tempcore.tempApplication.TempApplication;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by chenlingkeji on 2016/11/6.
 * for chenlingkeji Company
 * link for more detail www.lingkj.com
 */
public class App extends TempApplication {

    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
     //   LeakCanary.install(this);
      // MultiDex.install(this);
        //Android 7.0 FileUriExposedException 解决
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

    }

    @Override
    public void initImageLoader() {
        defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(com.lf.tempcore.R.drawable.temp_image_default)
                .showImageOnFail(com.lf.tempcore.R.drawable.temp_image_load_fail)
                .showImageOnLoading(com.lf.tempcore.R.drawable.temp_image_default)
                .considerExifParams(true) //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(800))
                .resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .build();

        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2).threadPoolSize(8)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new LRULimitedMemoryCache(2 * 1024 * 1024))
                .memoryCacheExtraOptions(1080, 1920)
                .memoryCacheSize(2 * 1024 * 1024)
                .defaultDisplayImageOptions(defaultOptions);
        ImageLoader.getInstance().init(builder.build());
    }

    /**
     * 分割 Dex 支持
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
