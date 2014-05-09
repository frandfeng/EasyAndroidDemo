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

import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.ch.chiq.BaseActivity;
import com.ch.chiq.R;
import com.frand.easyandroid.annotation.FFCompareAnnotation;
import com.frand.easyandroid.annotation.FFResInject;
import com.frand.easyandroid.annotation.FFSelect;
import com.frand.easyandroid.annotation.FFViewInject;
import com.frand.easyandroid.log.FFLogger;

/**
 * @author frandfeng
 * @time 2014-3-28 上午9:14:10 class description
 */
public class DemoInjectActivity extends BaseActivity {
	
	@FFViewInject(id=R.id.btn_test_click, click="onClick")
	private Button testClickBtn;
	@FFViewInject(id=R.id.btn_test_longclick, longClick="onLongClick")
	private Button testLongClickBtn;
	@FFViewInject(id = R.id.lv_test_itemclick, itemClick = "onItemClick")
	private ListView itemClickLv;
	@FFViewInject(id = R.id.lv_test_itemlongclick, itemLongClick = "onItemLongClick")
	private ListView itemLongClickLv;
	@FFViewInject(id = R.id.sp_test_select, select = @FFSelect(selected = "onItemSelected", noSelected = "onNothingSelected"))
	private Spinner testSelectSp;
	@FFResInject(id=R.array.control)
	private String[] strings;
	@FFResInject(id=R.array.num)
	@FFCompareAnnotation()
	private int[] ints;
	@FFResInject(id=R.string.test_click)
	private String clickString = "";
	@FFResInject(id=R.drawable.ic_launcher)
	private Drawable drawable;
	@FFResInject(id=R.layout.activity_demoinject)
	private XmlResourceParser xmlResourceParser;
	@FFResInject(id=R.color.blue)
	private int color;

	@SuppressWarnings("deprecation")
	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState) {
		super.onAfterOnCreate(savedInstanceState);
		testClickBtn.setText(clickString);
		testClickBtn.setBackgroundDrawable(drawable);
		testLongClickBtn.setBackgroundColor(color);
		String isXmlNull = xmlResourceParser==null?"YES":"NO";
		FFLogger.d(this, "is xmlResourceParser null ?"+isXmlNull);
		itemClickLv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, strings));
		String intsString[] = null;
		if(ints!=null&&ints.length>0) {
			intsString = new String[ints.length];
			for(int i=0; i<intsString.length; i++) {
				intsString[i] = String.valueOf(ints[i]);
			}
			itemLongClickLv.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_expandable_list_item_1, intsString));
		}
		testSelectSp.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, strings));
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_test_click:
			FFLogger.d(this, "test onClick");
			break;
		default:
			break;
		}
	}
	
	public boolean onLongClick(View view) {
		switch (view.getId()) {
		case R.id.btn_test_longclick:
			FFLogger.d(this, "test onLongClick");
			break;
		default:
			break;
		}
		return false;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg0.getId()) {
		case R.id.lv_test_itemclick:
			FFLogger.d(this, "test item click");
			break;
		default:
			break;
		}
	}

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		switch (arg0.getId()) {
		case R.id.lv_test_itemlongclick:
			FFLogger.d(this, "test item long click");
			break;
		default:
			break;
		}
		return false;
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		FFLogger.d(this, "test onItemSelected");
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		FFLogger.d(this, "test onNothingSelected");
	}

}
