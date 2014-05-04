package com.ch.chiq.activities;

import android.view.View;
import android.widget.Button;

import com.ch.chiq.BaseActivity;
import com.ch.chiq.R;
import com.frand.easyandroid.annotation.FFViewInject;

public class DemoMainActivity extends BaseActivity {

	@FFViewInject(id=R.id.btn_test_inject, click="onClick")
	private Button testInjectBtn;
	@FFViewInject(id=R.id.btn_test_http, click="onClick")
	private Button testHttpBtn;
	@FFViewInject(id=R.id.btn_test_db, click="onClick")
	private Button testDBBtn;
	@FFViewInject(id=R.id.btn_test_image, click="onClick")
	private Button testImageBtn;
	@FFViewInject(id=R.id.btn_test_other, click="onClick")
	private Button testOtherBtn;
	@FFViewInject(id=R.id.btn_test_download, click="onClick")
	private Button testDownLoadBtn;
	@FFViewInject(id=R.id.btn_exit_app, click="onClick")
	private Button exitAppBtn;

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_test_inject:
			doActivity(DemoInjectActivity.class.getName());
			break;
		case R.id.btn_test_http:
			doActivity(DemoHttpActivity.class.getName());
			break;
		case R.id.btn_test_db:
			doActivity(DemoDBActivity.class.getName());
			break;
		case R.id.btn_test_image:
			doActivity(DemoImageActivity.class.getName());
			break;
		case R.id.btn_test_other:
			doActivity(DemoOtherActivity.class.getName());
			break;
		case R.id.btn_test_download:
			doActivity(DemoDownLoadActivity.class.getName());
			break;
		case R.id.btn_exit_app:
			break;
		default:
			break;
		}
	}
}