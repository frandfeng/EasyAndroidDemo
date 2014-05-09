package com.ch.chiq.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ch.chiq.R;
import com.ch.chiq.helper.HttpHelper;
import com.ch.chiq.helper.HttpHelper.ReqAPI;
import com.frand.easyandroid.FFActivity;
import com.frand.easyandroid.annotation.FFViewInject;
import com.frand.easyandroid.http.FFFileRespHandler;
import com.frand.easyandroid.http.FFHttpRequest.ReqType;
import com.frand.easyandroid.log.FFLogger;
import com.frand.easyandroid.util.FFDiskUtil;

public class DemoDownLoadActivity extends FFActivity {

	private FFFileRespHandler ffFileRespHandler;
	@FFViewInject(id=R.id.btn_download_async, click="onClick")
	private Button downLoadAsyncBtn;
	@FFViewInject(id=R.id.btn_download_pause, click="onClick")
	private Button downloadPauseBtn;
	@FFViewInject(id=R.id.tv_download_info)
	private TextView downloadInfoTv;

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_download_async:
			// 注意下载的时候要预防二次点击
			new HttpHelper(this).request(ReqAPI.DOWNLOAD, ReqType.DOWNLOAD, ffFileRespHandler);
			break;
		case R.id.btn_download_pause:
			ffFileRespHandler.PauseDownload();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState) {
		super.onAfterOnCreate(savedInstanceState);
		initRespHandler();
	}
	
	private void initRespHandler() {
		ffFileRespHandler = new FFFileRespHandler(FFDiskUtil.getExternalDownLoadDir(this).getAbsolutePath(), "apk.apk") {
				
			@Override
			protected void onStart(int reqTag, String reqUrl) {
				FFLogger.d(DemoDownLoadActivity.this, "start to download reqTag "+reqTag+" from "+reqUrl);
			}
			
			@Override
			public void onProgress(long totalSize, long currentSize, long speed, int reqTag, String reqUrl) {
				FFLogger.d(DemoDownLoadActivity.this, "progress to download"
						+" totalsize="+totalSize+"currentSize="+currentSize+"speed="+speed);
				downloadInfoTv.setText(String.format("总大小：%d, 现大小：%d, 下载速度：%d", totalSize, currentSize, speed));
			}
			
			@Override
			protected void onFailure(Throwable error, int reqTag, String reqUrl) {
				FFLogger.d(DemoDownLoadActivity.this, "fail to download reqTag "
						+reqTag+" from "+reqUrl+" cause "+error.toString());
			}
			
			@Override
			protected void onSuccess(String resp, int reqTag, String reqUrl) {
				FFLogger.d(DemoDownLoadActivity.this, "succ to download reqTag "+reqTag+" from "+reqUrl);
			}
			
			@Override
			protected void onFinish(int reqTag, String reqUrl) {
				FFLogger.d(DemoDownLoadActivity.this, "finish to download reqTag "
						+reqTag+" from "+reqUrl);
			}
		};
	}
}