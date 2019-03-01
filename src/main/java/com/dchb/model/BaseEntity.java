package com.dchb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dchb.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title:  B02~B18 基类
 * @Description:  
 * @Author: LiMing
 * @Date: 2018年11月2日
 */
public abstract class BaseEntity<T extends Model<T>> extends Model<T>{

	private static final long serialVersionUID = 1L;
	
    @TableId( type = IdType.UUID)
    protected String id;
    /**
     * 数据维护时间：YYYYMMDDHHMMSS
     */
    @JsonSerialize(using=JsonDateSerializer.class)
    protected  Date sjwhSj;

    /**
     * 数据采集机构编码
     */
    protected String sjcjjgBm;

    /**
     * 数据采集机构名称(具体机构名称)
     */
    protected String sjcjjgmc;

    /**
     * 数据采集人员编码
     */
    protected String sjcjryBm;
    /**
     * 状态：0删除，1可用，2不可用
     */
    protected Integer status;
    

	public BaseEntity() {
		super();
	}

	public BaseEntity(String id) {
		super();
		this.id = id;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Date getSjwhSj() {
		return sjwhSj;
	}

	public void setSjwhSj(Date sjwhSj) {
		this.sjwhSj = sjwhSj;
	}

	public String getSjcjjgBm() {
		return sjcjjgBm;
	}

	public void setSjcjjgBm(String sjcjjgBm) {
		this.sjcjjgBm = sjcjjgBm;
	}

	public String getSjcjjgmc() {
		return sjcjjgmc;
	}

	public void setSjcjjgmc(String sjcjjgmc) {
		this.sjcjjgmc = sjcjjgmc;
	}

	public String getSjcjryBm() {
		return sjcjryBm;
	}

	public void setSjcjryBm(String sjcjryBm) {
		this.sjcjryBm = sjcjryBm;
	}
    
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
