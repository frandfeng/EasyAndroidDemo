package com.ch.chiq.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.ch.chiq.beans.ImagesUrls;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageAdapter extends BaseAdapter {

	private final Context mContext;

	public ImageAdapter(Context context) {
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return ImagesUrls.imageThumbUrls.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(100, 100));// 设置ImageView宽高
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		} else {
			imageView = (ImageView) convertView;
		}
		ImageLoader.getInstance().displayImage(ImagesUrls.imageThumbUrls[position], imageView);
		// 如果listview滑动过后不需要再在后台加载图片，可调用imageloader.cancel(imageview)
		// ImageLoader.getInstance().cancelDisplayTask(imageView);
		// imageLoader.loadImage(imageUri, new SimpleImageLoadingListener());设置简单监听
		// Bitmap bmp = imageLoader.loadImageSync(imageUri);同步下载图片
		// imageLoader.displayImage(imageUri, imageView, displayOptions, new ImageLoadingListener();加载图片前，重新设置一个displayOption
		// ImageSize targetSize = new ImageSize(120, 80); // result Bitmap will be fit to this size
		// imageLoader.loadImage(imageUri, targetSize, displayOptions, new SimpleImageLoadingListener())
		// ImageSize targetSize = new ImageSize(120, 80); // result Bitmap will be fit to this size
		// Bitmap bmp = imageLoader.loadImageSync(imageUri, targetSize, displayOptions); // 同步获取一个长宽规定的bitmap
		return imageView;
	}
}