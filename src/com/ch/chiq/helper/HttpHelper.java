/*
 * Copyright (C) 2014-3-27 frandfeng
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
package com.ch.chiq.helper;

import android.content.Context;
import android.widget.Toast;

import com.ch.chiq.beans.DownloadUrls;
import com.ch.chiq.utils.Configs;
import com.frand.easyandroid.FFApplication;
import com.frand.easyandroid.http.FFHttpClient;
import com.frand.easyandroid.http.FFHttpRequest.ReqType;
import com.frand.easyandroid.http.FFHttpRespHandler;
import com.frand.easyandroid.http.FFRequestParams;

/** 
 * @author frandfeng
 * @time 2014-3-27 下午5:19:50 
 * class description 
 */
public class HttpHelper {
	
	private FFHttpClient httpClient = new FFHttpClient();
	private Context context;

	public enum ReqAPI {
		LOGIN,
		DOWNLOAD,
	}
	
	private static final String HTTP_DEV_PRIX = "http://frand.java.fjjsp01.com/";
	private static final String HTTP_REL_PRIX = "";
	private static final String HTTP_PRIX = Configs.isDevMode ? HTTP_DEV_PRIX : HTTP_REL_PRIX; 
	
	public HttpHelper(Context context) {
		this.context = context;
	}
	
	public void request(ReqAPI reqAPI, FFHttpRespHandler handler) {
		request(reqAPI, ReqType.GET, new FFRequestParams(), handler);
	}
	
	public void request(ReqAPI reqAPI, ReqType reqType, FFHttpRespHandler handler) {
		request(reqAPI, reqType, new FFRequestParams(), handler);
	}
	
	public void request(ReqAPI reqAPI, FFRequestParams params, FFHttpRespHandler handler) {
		request(reqAPI, ReqType.GET, params, handler);
	}
	
	public void request(ReqAPI reqAPI, ReqType reqType, FFRequestParams params,
			FFHttpRespHandler handler) {
		if(FFApplication.getApplication().isNetworkAvailable()) {
			String url = HTTP_PRIX;
			if(reqType==ReqType.GET) {
				if(reqAPI==ReqAPI.LOGIN) {
					url += "user/getUser.do";
				}
				httpClient.get(reqAPI.ordinal(), url, params, handler);
			} else if (reqType==ReqType.POST) {
				if(reqAPI==ReqAPI.LOGIN) {
					url += "user/getUser.do";
				}
				httpClient.post(reqAPI.ordinal(), url, params, handler);
			} else if (reqType==ReqType.PUT) {
				httpClient.put(reqAPI.ordinal(), url, params, handler);
			} else if (reqType==ReqType.DELETE) {
				httpClient.delete(reqAPI.ordinal(), url, handler);
			} else if (reqType==ReqType.DOWNLOAD) {
				httpClient.download(reqAPI.ordinal(), DownloadUrls.url[0], handler);
			}
		} else {
			Toast.makeText(context, "未连接网络，请检查网络", Toast.LENGTH_SHORT).show();
		}
	}
	
}
