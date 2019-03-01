package com.dchb.model.turn;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("B1A")
public class B1A extends Model<B1A> {
    private static final long serialVersionUID = 1L;

    /**
     * 功能账号表（流水号）
     */
    @TableId("ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 身份证
     */
    @TableField("IDNO")
    private String idNo;

    /**
     * 微信号
     */
    @TableField("WXID")
    private String wxId;

    /**
     * 账号
     */
    @TableField("ACC")
    private String acc;

    /**
     * 密码
     */
    @TableField("PWD")
    private String pwd;

    /**
     * 姓名
     */
    @TableField("ONAME")
    private String oname;

    /**
     * 权限
     */
    @TableField("TITLE")
    private Integer title;

    /**
     * 生成时间
     */
    @TableField("CREATETIME")
    private Date createtime;

    /**
     * 修改者
     */
    @TableField("MODIFIEDBY")
    private String modifiedby;

    /**
     * 是否有效
     */
    @TableField("BENABLED")
    private String benabled;

    /**
     * 所在机构类型:定位所在人员信息表
     */
    @TableField("ORGTYPE")
    private String orgtype;

    /**
     * 所在机构编号
     */
    @TableField("ORGID")
    private String orgid;

    @TableField("BMVHIS")
    private Integer bmvhis;

    @TableField("HISCONFIG")
    private String hisconfig;

    @TableField("ISDOCTOR")
    private Integer isDoctor;

    @TableField("ISNURSE")
    private Integer isNurse;

    public B1A() {
        super();
    }

    public B1A(Long id) {
        super();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public String getBenabled() {
        return benabled;
    }

    public void setBenabled(String benabled) {
        this.benabled = benabled;
    }

    public String getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(String orgtype) {
        this.orgtype = orgtype;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public Integer getBmvhis() {
        return bmvhis;
    }

    public void setBmvhis(Integer bmvhis) {
        this.bmvhis = bmvhis;
    }

    public String getHisconfig() {
        return hisconfig;
    }

    public void setHisconfig(String hisconfig) {
        this.hisconfig = hisconfig;
    }

    public Integer getIsDoctor() {
        return isDoctor;
    }

    public void setIsDoctor(Integer isDoctor) {
        this.isDoctor = isDoctor;
    }

    public Integer getIsNurse() {
        return isNurse;
    }

    public void setIsNurse(Integer isNurse) {
        this.isNurse = isNurse;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
