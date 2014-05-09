package com.ch.chiq;
/*
 * Copyright (C) 2014-4-1 frandfeng
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

import java.io.File;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Handler;

import com.frand.easyandroid.FFApplication;
import com.frand.easyandroid.db.FFDBHelper.FFDBListener;
import com.frand.easyandroid.log.FFLogger;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

/** 
 * @author frandfeng
 * @time 2014-4-1 下午3:09:40 
 * class description 
 */
public class DemoApplication extends FFApplication implements FFDBListener {
	
	@Override
	protected void onAfterCreateApplication() {
		super.onAfterCreateApplication();
		initImageLoader();
		sendCrashReport();
	}

	/**
	 * 初始化imageloader
	 */
	private void initImageLoader() {
		// image loader detail please refer https://github.com/nostra13/Android-Universal-Image-Loader
		// DisplayImageOptions对每一个显示的任务有效
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		        .showStubImage(R.drawable.ic_launcher) /*设置预加载的loading图片*/
		        .showImageForEmptyUri(R.drawable.ic_launcher) /*设置图片地址为空时的默认图片*/
		        .showImageOnFail(R.drawable.ic_launcher) /*设置图片加载失败时的图片*/
		        .resetViewBeforeLoading()
		        .delayBeforeLoading(1000)
		        .cacheInMemory()
		        .cacheOnDisc() /*设置存储在外部存储设备中，如果外部存储设备不能用，则保存在设备文件系统中，一般2.3以前不能用外部存储设备，即SD卡*/
		        .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
		        .bitmapConfig(Bitmap.Config.ARGB_8888) // default
		        .displayer(new SimpleBitmapDisplayer()) // default
		        .handler(new Handler()) // default
		        .build();
		File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
		// ImageLoaderConfiguration是一个全局的设置
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
		        .memoryCacheExtraOptions(display_width, display_height) // default = device screen dimensions
		        .discCacheExtraOptions(display_width, display_height, CompressFormat.JPEG, 75)
		        /*.taskExecutor(AsyncTask.THREAD_POOL_EXECUTOR)*/
		        /*.taskExecutorForCachedImages(AsyncTask.THREAD_POOL_EXECUTOR)*/
		        .threadPoolSize(3) // default
		        .threadPriority(Thread.NORM_PRIORITY - 1) // default
		        .tasksProcessingOrder(QueueProcessingType.FIFO) // default
		        .denyCacheImageMultipleSizesInMemory()
		        .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
		        /*.memoryCacheSize(2 * 1024 * 1024)*/
		        .discCache(new UnlimitedDiscCache(cacheDir)) // default
		        /*.discCacheSize(50 * 1024 * 1024)
		        .discCacheFileCount(100)*/
		        .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
		        .imageDownloader(new BaseImageDownloader(getApplicationContext())) // default
		        .imageDecoder(new BaseImageDecoder()) // default
		        .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
		        .enableLogging()
		        .defaultDisplayImageOptions(options)
		        .build();
		/*如果遇到out of memory错误， 可采取的措施有：
		 * 1,在配置中减少下载的线程池的大小 .threadPoolSize, 建议值在1-5
		 * 2,用.bitmapConfig(BitmapConfig.RGB_565)取代.bitmapConfig(BitmapConfig.ARGB_8888)，这样大小可以缩小一倍
		 * 3,.cacheInMemory最好去掉，不让保存在内存中
		 * 4,用.imageScaleType(ImageScaleType.IN_SAMPLE_INT)将图片成倍缩小后再做处理，或.imageScaleType(ImageScaleType.EXACTLY)不做处理直接放上去，相当于scale fitXY
		 * 5,避免用RoundedBitmapDisplayer，因为他生成了新的bitmap,建议用SimpleBitmapDisplayer、FadeInBitmapDisplayer，FakeBitmapDisplayer*/
		/*.memoryCache cache configuration
		 * 1,强引用 LruMemoryCache，当内存存储满后，根据时间，最先下载的将会被删除
		 * 2，强弱引用结合，UsingFreqLimitedMemoryCache使用频率最小的将会被删除
		 *   LRULimitedMemoryCache，最先下载的将会被先删除
		 *   FIFOLimitedMemoryCache，根据FIFO的规则来删除资源
		 *   LargestLimitedMemoryCache，占用空间较大的将会被删除
		 *   LimitedAgeMemoryCache，当时间超越的规定的时间，将会被删除
		 * 3,弱引用WeakMemoryCache，此时的memory将会全部分配给此应用的图片模块*/
		/*.diskCache cache configuration
		 * 1,UnlimitedDiscCache,最快的缓存方式，不限制cache文件夹的大小
		 * 2,TotalSizeLimitedDiscCache根据文件夹的大小来删除资源
		 * 3,FileCountLimitedDiscCache,通过文件夹中的数量来删除资源
		 * 4,LimitedAgeDiscCache,通过设定的时间来删除资源*/
		/*String imageUri = "http://site.com/image.png"; // from Web
		String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
		String imageUri = "content://media/external/audio/albumart/13"; // from content provider
		String imageUri = "assets://image.png"; // from assets
		String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
		*/
		ImageLoader.getInstance().init(config);
	}
	
	private void sendCrashReport() {
		FFLogger.d(this, "send crash report");
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		FFLogger.i(this, "database onCreate path="+db.getPath());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		FFLogger.i(this, "database onUpgrade version="+newVersion+"db path="+db.getPath());
	}

}
