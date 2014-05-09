package com.ch.chiq.activities;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ch.chiq.BaseActivity;
import com.ch.chiq.R;
import com.frand.easyandroid.annotation.FFViewInject;

public class DemoExceptionActivity extends BaseActivity implements OnClickListener {

	@FFViewInject(id=R.id.btn_exception_catched, click="onClick")
	private Button exCatchedBtn;
	private Button exUnCatchedBtn;
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_exception_catched:
			int i = 1/0;
			break;
		case R.id.btn_exception_uncatched:
			int j = 1/0;
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onAfterSetContentView() {
		super.onAfterSetContentView();
		exUnCatchedBtn = (Button) findViewById(R.id.btn_exception_uncatched);
		exUnCatchedBtn.setOnClickListener(this);
	}
}
