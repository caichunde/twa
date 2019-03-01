package com.dchb.model.agency;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.List;

@TableName("B00")
public class B00 extends Model<B00> {

    @TableId(value = "id")
    @TableField("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @TableField("OrgId")
    private String OrgId;

    @TableField("OrgName")
    private String OrgName;

    @TableField("OrgType")
    private String OrgType;

    @TableField("OrgGrade")
    private String OrgGrade;

    @TableField("bEnabled")
    private String bEnabled;

    @TableField(exist = false)
    private List<B13> zrksList;

    public List<B13> getZrksList() {
        return zrksList;
    }

    public void setZrksList(List<B13> zrksList) {
        this.zrksList = zrksList;
    }

    public String getbEnabled() {
        return bEnabled;
    }

    public void setbEnabled(String bEnabled) {
        this.bEnabled = bEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgId() {
        return OrgId;
    }

    public void setOrgId(String orgId) {
        OrgId = orgId;
    }

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String orgName) {
        OrgName = orgName;
    }

    public String getOrgType() {
        return OrgType;
    }

    public void setOrgType(String orgType) {
        OrgType = orgType;
    }

    public String getOrgGrade() {
        return OrgGrade;
    }

    public void setOrgGrade(String orgGrade) {
        OrgGrade = orgGrade;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
