package com.ch.chiq.beans;

import java.io.Serializable;
import java.util.Date;

import com.frand.easyandroid.annotation.FFCompareAnnotation;
import com.frand.easyandroid.db.annotation.FFColumn;
import com.frand.easyandroid.db.annotation.FFPrimaryKey;
import com.frand.easyandroid.db.annotation.FFTableName;
import com.frand.easyandroid.db.annotation.FFTransient;

/*@FFTableName注解用来生成表的名称，如果没有，则用包名+类名来生成*/
@FFTableName(name="demoTable")
public class DemoDataEntity implements Serializable {

	/*@FFTransient注解表示隐藏此字段，在生成表的时候不生成字段*/
	@FFTransient
	private static final long serialVersionUID = -7995717179024306707L;
	/*@FFPrimaryKey用来控制表的主键，属性autoIncrement用来表示主键是否自增*/
	@FFPrimaryKey(autoIncrement = true)
	/*@FFCompareAnnotation表示对list比较，重新排序的参考字段，sortFlag是对此属性作为一个标记*/
	@FFCompareAnnotation(sortFlag = 1)
	private int id;
	@FFColumn(name = "username")
	private String name;
	private Date date;
	private char charExp;
	@FFCompareAnnotation(sortFlag = 0)
	private int indexInt;
	private boolean booleanExp;
	private float floatExp;
	private double doubleExp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public char getCharExp() {
		return charExp;
	}

	public void setCharExp(char charExp) {
		this.charExp = charExp;
	}

	public int getIndexInt() {
		return indexInt;
	}

	public void setIndexInt(int indexInt) {
		this.indexInt = indexInt;
	}

	public Boolean getBooleanExp() {
		return booleanExp;
	}

	public void setBooleanExp(Boolean booleanExp) {
		this.booleanExp = booleanExp;
	}

	public float getFloatExp() {
		return floatExp;
	}

	public void setFloatExp(float floatExp) {
		this.floatExp = floatExp;
	}

	public double getDoubleExp() {
		return doubleExp;
	}

	public void setDoubleExp(double doubleExp) {
		this.doubleExp = doubleExp;
	}

	@Override
	public String toString() {
		return "id="+getId()+",name="+getName()+",date="+date+",charExp="+charExp
				+",index="+indexInt+",booleanExp="+booleanExp+",floatExp="+floatExp+",doubleExp="+doubleExp;
	}
}
