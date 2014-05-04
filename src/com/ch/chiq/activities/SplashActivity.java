package com.ch.chiq.activities;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;

import com.ch.chiq.BaseActivity;
import com.ch.chiq.R;
import com.frand.easyandroid.annotation.FFViewInject;

public class SplashActivity extends BaseActivity {

	private boolean isEnterDemo = false;
	@FFViewInject(id=R.id.btn_demo, click="onClick")
	private Button demoBtn;

	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_demo:
				isEnterDemo = true;
				doActivity(DemoMainActivity.class.getName());
				break;
			default:
				break;
		}
	}

	@Override
	protected void onAfterOnResume() {
		super.onAfterOnResume();
		AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
		aa.setDuration(5000);
		aa.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				if(!isEnterDemo) {
					doActivity(MainActivity.class.getName());
				}
			}
		});
		getContentView().startAnimation(aa);
	}

}
