package com.ch.chiq.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ch.chiq.R;
import com.ch.chiq.beans.DemoDataEntity;
import com.ch.chiq.helper.PreferHelper;
import com.frand.easyandroid.FFActivity;
import com.frand.easyandroid.FFApplication;
import com.frand.easyandroid.annotation.FFViewInject;
import com.frand.easyandroid.config.FFIConfig;
import com.frand.easyandroid.log.FFLogger;
import com.frand.easyandroid.util.FFComparator;

public class DemoOtherActivity extends FFActivity {
	@FFViewInject(id = R.id.btn_test_preference, click = "onClick")
	private Button testPreferenceBtn;
	@FFViewInject(id = R.id.btn_test_propertities, click = "onClick")
	private Button testPropertitiesBtn;
	@FFViewInject(id = R.id.btn_test_comparator, click = "onClick")
	private Button testComparatorButton;
	@FFViewInject(id = R.id.show_view)
	private TextView showViewTextView;

	@Override
	protected void onAfterOnCreate(Bundle savedInstanceState) {
		super.onAfterOnCreate(savedInstanceState);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_test_preference:
			testPreference();
			break;
		case R.id.btn_test_propertities:
			testPropertities();
			break;
		case R.id.btn_test_comparator:
			testComparator();
			break;
		default:
			break;
		}
	}
	private void testPreference() {
		FFIConfig config = getFFApplication().getConfig(FFApplication.PREFERENCECONFIG);
		DemoDataEntity testDataEntity = new DemoDataEntity();
		testDataEntity.setId(0);
		testDataEntity.setName("frand");
		testDataEntity.setBooleanExp(true);
		testDataEntity.setDoubleExp(10);
		testDataEntity.setDate(new Date(System.currentTimeMillis()));
		testDataEntity.setFloatExp(12);
		testDataEntity.setIndexInt(0);
		// 保存配置
		config.setConfig(testDataEntity);
		// 读取配置
		DemoDataEntity dataEntity = config.getConfig(DemoDataEntity.class);
		showViewTextView.setText(dataEntity.toString());
		// 保存用户的信息
		getFFApplication().getConfig(FFApplication.PREFERENCECONFIG).setString(PreferHelper.STR_ACCOUNT, "frand");
		showViewTextView.setText(getFFApplication().getConfig(FFApplication.PREFERENCECONFIG).getString(PreferHelper.STR_ACCOUNT, ""));
	}
	
	private void testPropertities() {
		FFIConfig config = getFFApplication().getConfig(FFApplication.PROPERTIESCONFIG);
		DemoDataEntity testDataEntity = new DemoDataEntity();
		testDataEntity.setId(0);
		testDataEntity.setName("frand");
		testDataEntity.setBooleanExp(true);
		testDataEntity.setDoubleExp(10);
		testDataEntity.setDate(new Date(System.currentTimeMillis()));
		testDataEntity.setFloatExp(12);
		testDataEntity.setIndexInt(0);
		// 保存配置,用对象来存储，放到config中去也是键值对的形式
		config.setConfig(testDataEntity);
		// 读取配置
		DemoDataEntity dataEntity = config.getConfig(DemoDataEntity.class);
		showViewTextView.setText(dataEntity.toString());
		// 保存用户的信息，直接用键值对的形式存取
//		getFFApplication().getConfig(FFApplication.PROPERTIESCONFIG).setString(PreferHelper.ACCOUNT_STR, "frand");
//		showViewTextView.setText(getFFApplication().getConfig(FFApplication.PROPERTIESCONFIG).getString(PreferHelper.ACCOUNT_STR, ""));
	}

	private void testComparator() {
		// @TACompareAnnotation(sortFlag = 0) 根据实体中这个注解的字段进行排序
		// 如果要对集合对象或数组对象进行排
		List<DemoDataEntity> listTestDataEntities = new ArrayList<DemoDataEntity>();
		for(int i=0; i<10; i++) {
			DemoDataEntity demoDataEntity = new DemoDataEntity();
			int index = (int) (Math.random() * 10);
			demoDataEntity.setIndexInt(index);
			int id = (int) (Math.random() * 10);
			demoDataEntity.setId(id);
			listTestDataEntities.add(demoDataEntity);
		}
		// 初始化数组之后打印输出
		for(int i=0; i<listTestDataEntities.size(); i++) {
			FFLogger.d(this, "demodataentity index="+listTestDataEntities.get(i).getIndexInt()
					+", id="+listTestDataEntities.get(i).getId());
		}
		// 构造比较器，对对象列表的sortFlag为0的字段进行升序排列
		FFComparator<DemoDataEntity> comparator = new FFComparator<DemoDataEntity>(
				FFComparator.ASC_SORT_TYPE, 0);
		Collections.sort(listTestDataEntities, comparator);
		// 排列之后打印列表
		for(int i=0; i<listTestDataEntities.size(); i++) {
			FFLogger.d(this, "demodataentity index="+listTestDataEntities.get(i).getIndexInt()
					+", id="+listTestDataEntities.get(i).getId());
		}
		// 重新构造比较器，对对象列表的sortFlag为1的字段进行降序排列
		comparator = new FFComparator<DemoDataEntity>(FFComparator.DES_SORT_TYPE, 1);
		Collections.sort(listTestDataEntities, comparator);
		// 排列之后打印列表
		for(int i=0; i<listTestDataEntities.size(); i++) {
			FFLogger.d(this, "demodataentity index="+listTestDataEntities.get(i).getIndexInt()
					+", id="+listTestDataEntities.get(i).getId());
		}
		Toast.makeText(this, "请查看log信息", Toast.LENGTH_SHORT).show();
	}

}