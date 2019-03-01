package com.dchb.model.sys;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@TableName("DICT01")
public class Dictionary extends Model<Dictionary>{

	private static final long serialVersionUID = 1L;
	
	@TableId( type = IdType.UUID)
	private String id;
	
	@TableField("ITEM_NAME")
	private String itemName;
	
	@TableField("ITEM_TYPE")
	private String itemType;
	
	@TableField("GRADE1")
	private String grade1;
	
	@TableField("GRADE2")
	private String grade2;
	
	@TableField("GRADE3")
	private String grade3;
	
	@TableField("ITEMCODE")
	private String itemCode;
	
	@TableField("ITEMCONTENT")
	private String itemContent;
	
	@TableField("DESCRIBE")
	private String describe;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getGrade1() {
		return grade1;
	}

	public void setGrade1(String grade1) {
		this.grade1 = grade1;
	}

	public String getGrade2() {
		return grade2;
	}

	public void setGrade2(String grade2) {
		this.grade2 = grade2;
	}

	public String getGrade3() {
		return grade3;
	}

	public void setGrade3(String grade3) {
		this.grade3 = grade3;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


}
