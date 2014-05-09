/*
 * Copyright (C) 2014-3-28 frandfeng
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
package com.ch.chiq.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ch.chiq.BaseActivity;
import com.ch.chiq.R;
import com.ch.chiq.helper.HttpHelper;
import com.ch.chiq.helper.HttpHelper.ReqAPI;
import com.frand.easyandroid.annotation.FFViewInject;
import com.frand.easyandroid.http.FFHttpRequest.ReqType;
import com.frand.easyandroid.http.FFHttpRespHandler;
import com.frand.easyandroid.http.FFRequestParams;
import com.frand.easyandroid.http.FFStringRespHandler;
import com.frand.easyandroid.log.FFLogger;

/** 
 * @author frandfeng
 * @time 2014-3-28 下午4:56:03 
 * class description 
 */
public class DemoHttpActivity extends BaseActivity {

	@FFViewInject(id=R.id.btn_start, click="onClick")
	private Button startBtn;
	
	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState) {
		super.onAfterOnCreate(savedInstanceState);
	}

	public void onClick(View view) {
		FFRequestParams params = new FFRequestParams();
		params.put("email", "frandfeng@gmail.com");
		params.put("password", "123456");
		// 异步发送http请求，通过get/post/put/delete/download方式
		new HttpHelper(this).request(ReqAPI.LOGIN, ReqType.POST, params, httpResponseHandler);
	}
	
	FFHttpRespHandler httpResponseHandler = new FFStringRespHandler() {
		public void onStart(int reqTag, String url) {
			FFLogger.d(this, "onstart request tag="+reqTag+" request url="+url);
		}
		public void onFailure(Throwable error, int reqTag, String url) {
			FFLogger.d(this, "onFailure request tag="+reqTag+" request url="+url);
			error.printStackTrace();
		}
		public void onSuccess(String content, int reqTag, String url) {
			FFLogger.d(this, "onSuccess request tag="+reqTag+" request url ="+url+" content="+content);
		}
		public void onFinish(int reqTag, String url) {
			FFLogger.d(this, "onfinish request tag="+reqTag+" request url="+url);
		}
	};
	
}
