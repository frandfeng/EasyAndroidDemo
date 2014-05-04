package com.ch.chiq.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.ch.chiq.BaseActivity;
import com.ch.chiq.R;
import com.ch.chiq.adapters.ImageAdapter;
import com.frand.easyandroid.annotation.FFViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;

public class DemoImageActivity extends BaseActivity {
	@FFViewInject(id = R.id.gridView)
	private GridView gridView;
	@FFViewInject(id=R.id.btn_cache_memory_clear, click="onClick")
	private Button clearMemoryCacheBtn;
	@FFViewInject(id=R.id.btn_cache_disk_clear, click="onClick")
	private Button clearDiskCacheBtn;

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState) {
		super.onAfterOnCreate(savedInstanceState);
		gridView.setAdapter(new ImageAdapter(this));
		// 为了避免listview等滑动比较慢，可以用PauseOnScrollListener
		boolean pauseOnScroll = true; // or true
		boolean pauseOnFling = true; // or false
		PauseOnScrollListener listener = new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling);
		gridView.setOnScrollListener(listener);
	}
	
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_cache_memory_clear:
			ImageLoader.getInstance().clearMemoryCache();
			break;
		case R.id.btn_cache_disk_clear:
			ImageLoader.getInstance().clearDiscCache();
			break;
		default:
			break;
		}
	}
}