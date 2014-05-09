package com.ch.chiq.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.view.View;
import android.widget.Button;

import com.ch.chiq.BaseActivity;
import com.ch.chiq.DemoApplication;
import com.ch.chiq.R;
import com.ch.chiq.beans.DemoDataEntity;
import com.frand.easyandroid.annotation.FFViewInject;
import com.frand.easyandroid.log.FFLogger;

public class DemoDBActivity extends BaseActivity {
	@FFViewInject(id=R.id.btn_show_tables, click="onClick")
	private Button showTableBtn;
	@FFViewInject(id=R.id.btn_create_table, click="onClick")
	private Button createTableBtn;
	@FFViewInject(id=R.id.btn_drop_table, click="onClick")
	private Button dropTableBtn;
	@FFViewInject(id=R.id.btn_has_table, click="onClick")
	private Button hasTableBtn;
	@FFViewInject(id=R.id.btn_show_data, click="onClick")
	private Button showDataBtn;
	@FFViewInject(id=R.id.btn_insert_data, click="onClick")
	private Button insertDataButton;
	@FFViewInject(id=R.id.btn_update_data, click="onClick")
	private Button updateDataButton;
	@FFViewInject(id=R.id.btn_delete_data, click="onClick")
	private Button deleteDataButton;

	private DemoDataEntity testDataEntity = new DemoDataEntity();

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_show_tables:
			showTables();
			break;
		case R.id.btn_create_table:
			createTable();
			break;
		case R.id.btn_drop_table:
			dropTable();
			break;
		case R.id.btn_has_table:
			hasTable();
			break;
		case R.id.btn_show_data:
			showDatas();
			break;
		case R.id.btn_insert_data:
			insertData();
			break;
		case R.id.btn_update_data:
			updateData();
			break;
		case R.id.btn_delete_data:
			deleteData();
			break;
		default:
			break;
		}
	}
	
	private void showTables() {
		ArrayList<? extends Object> tabEntities = DemoApplication.ffdb.getTables();
		for(int i=0; i<tabEntities.size(); i++) {
			Object object = tabEntities.get(i);
			FFLogger.d(this, "element "+i+" "+object.toString()+"\n");
		}
	}
	
	private void createTable() {
		boolean isSucc = DemoApplication.ffdb.createTable(DemoDataEntity.class);
		if(isSucc) {
			FFLogger.d(this, "表创建成功");
		} else {
			FFLogger.d(this, "表创建失败");
		}
	}
	
	private void dropTable() {
		boolean isSucc = DemoApplication.ffdb.dropTable(DemoDataEntity.class);
		if(isSucc) {
			FFLogger.d(this, "表删除成功");
		} else {
			FFLogger.d(this, "表删除失败");
		}
	}
	
	private void hasTable() {
		boolean hasTable = DemoApplication.ffdb.hasTable(DemoDataEntity.class);
		if(hasTable) {
			FFLogger.d(this, "有此数据表");
		} else {
			FFLogger.d(this, "无此数据表");
		}
	}
	
	private void showDatas() {
		List<DemoDataEntity> demoDataEntities = DemoApplication.ffdb
				.query(DemoDataEntity.class, false, null, null, null, null, null);
		if(demoDataEntities!=null&&demoDataEntities.size()>0) {
			printDemoDataEntities(demoDataEntities);
		} else {
			FFLogger.d(this, "demoDataEntities is null or size is 0");
		}
	}

	private void insertData() {
		testDataEntity.setBooleanExp(true);
		testDataEntity.setCharExp('c');
		testDataEntity.setDate(new Date());
		testDataEntity.setDoubleExp(0.0);
		testDataEntity.setFloatExp(0.0f);
		testDataEntity.setIndexInt(2);
		testDataEntity.setName("sdd");
		boolean isSucc = DemoApplication.ffdb.insert(testDataEntity);
		if(isSucc) {
			FFLogger.d(this, "插入数据成功");
		} else {
			FFLogger.d(this, "插入数据失败");
		}
	}

	private void updateData() {
		testDataEntity.setBooleanExp(true);
		testDataEntity.setCharExp('d');
		testDataEntity.setDate(new Date());
		testDataEntity.setDoubleExp(1.0);
		testDataEntity.setFloatExp(2.0f);
		testDataEntity.setIndexInt(4);
		testDataEntity.setName("sdd");
		testDataEntity.setId(1);
		boolean isSucc = DemoApplication.ffdb.update(testDataEntity);
		if(isSucc) {
			FFLogger.d(this, "更新数据成功");
		} else {
			FFLogger.d(this, "更新数据失败");
		}
	}

	private void deleteData() {
		// DELETE FROM demoTable WHERE username = 'sdd' AND date = 'Fri Apr 18 11:55:05 格林尼治标准时间+0800 2014' AND doubleExp = '0.0' AND charExp = 'c' AND floatExp = '0.0' AND indexInt = 2 AND booleanExp = 'true'
//		boolean isSucc = DemoApplication.ffdb.delete(DemoDataEntity.class, "id=" + "1");
		boolean isSucc = DemoApplication.ffdb.delete(testDataEntity);
		if(isSucc) {
			FFLogger.d(this, "删除数据成功");
		} else {
			FFLogger.d(this, "删除数据失败");
		}
	}
	
	private void printDemoDataEntities(List<DemoDataEntity> demoDataEntities) {
		for (int i = 0; i < demoDataEntities.size(); i++) {
			 printDemoDataEntity(demoDataEntities.get(i));
		}
	}
	
	private void printDemoDataEntity(DemoDataEntity demoDataEntity) {
		FFLogger.d(this, demoDataEntity.toString());
	}

}