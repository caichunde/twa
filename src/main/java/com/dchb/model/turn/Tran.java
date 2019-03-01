package com.dchb.model.turn;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dchb.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author caichunde
 * @since 2018-11-20
 */
@TableName("tab_tran")
@KeySequence(value = "tab_tran_id", clazz = String.class)
public class Tran extends Model<Tran> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId(value = "ID")
    private String id;

    /**
     * 转出机构Id
     */
    private String outOrgId;

    /**
     * 转出机构名称
     */
    private String outOrgName;

    /**
     * 转出科室
     */
    private String outDepartment;

    /**
     * 转出医生Id
     */
    private String outDoctorId;

    /**
     * 转出医生姓名
     */
    private String outDoctorName;

    /**
     * 转出医生电话
     */
    private String outDoctorPhonenum;

    /**
     * 转出方向，上-1,下-0
     */
    private String tranDirect;

    /**
     * 患者身份证号
     */
    private String skerIdcardno;

    /**
     * 患者姓名
     */
    private String skerName;

    /**
     * 患者性别
     */
    private String skerSex;

    /**
     * 患者电话
     */
    private String skerPhonenum;

    /**
     * 患者诊疗情况 下转：住院摘要，出院医嘱
     */
    private String skerContent;

    /**
     * 患者初步诊断码
     */
    private String skerFirstdiaCode;

    /**
     * 患者初步诊
     */
    private String skerFirstdiaName;

    /**
     * 患者诊疗附件 (患者有无报告单)
     */
    private String skerReport;

    /**
     * 转诊原因
     */
    private String skerTranreasion;

    /**
     * 转入机构Id
     */
    private String inOrgId;

    /**
     * 转入机构名称
     */
    private String inOrgName;

    /**
     * 转入科室代码
     */
    @TableField(exist = false)
    private String inKsdm;

    /**
     * 转入科室
     */
    private String inDepartment;

    /**
     * 转出日期
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date tranDate;

    /**
     * 转入日期
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date inDate;

    /**
     * 转出状态(保存0/转出生效2/转入5)
     */
    private String tranState;

    /**
     * 医生意见
     */
    private String doctorOpinion;

    public String getInKsdm() {
        return inKsdm;
    }

    public void setInKsdm(String inKsdm) {
        this.inKsdm = inKsdm;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getDoctorOpinion() {
        return doctorOpinion;
    }

    public void setDoctorOpinion(String doctorOpinion) {
        this.doctorOpinion = doctorOpinion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutOrgId() {
        return outOrgId;
    }

    public void setOutOrgId(String outOrgId) {
        this.outOrgId = outOrgId;
    }

    public String getOutOrgName() {
        return outOrgName;
    }

    public void setOutOrgName(String outOrgName) {
        this.outOrgName = outOrgName;
    }

    public String getOutDepartment() {
        return outDepartment;
    }

    public void setOutDepartment(String outDepartment) {
        this.outDepartment = outDepartment;
    }

    public String getOutDoctorId() {
        return outDoctorId;
    }

    public void setOutDoctorId(String outDoctorId) {
        this.outDoctorId = outDoctorId;
    }

    public String getOutDoctorName() {
        return outDoctorName;
    }

    public void setOutDoctorName(String outDoctorName) {
        this.outDoctorName = outDoctorName;
    }

    public String getOutDoctorPhonenum() {
        return outDoctorPhonenum;
    }

    public void setOutDoctorPhonenum(String outDoctorPhonenum) {
        this.outDoctorPhonenum = outDoctorPhonenum;
    }

    public String getTranDirect() {
        return tranDirect;
    }

    public void setTranDirect(String tranDirect) {
        this.tranDirect = tranDirect;
    }

    public String getSkerIdcardno() {
        return skerIdcardno;
    }

    public void setSkerIdcardno(String skerIdcardno) {
        this.skerIdcardno = skerIdcardno;
    }

    public String getSkerName() {
        return skerName;
    }

    public void setSkerName(String skerName) {
        this.skerName = skerName;
    }

    public String getSkerSex() {
        return skerSex;
    }

    public void setSkerSex(String skerSex) {
        this.skerSex = skerSex;
    }

    public String getSkerPhonenum() {
        return skerPhonenum;
    }

    public void setSkerPhonenum(String skerPhonenum) {
        this.skerPhonenum = skerPhonenum;
    }

    public String getSkerContent() {
        return skerContent;
    }

    public void setSkerContent(String skerContent) {
        this.skerContent = skerContent;
    }

    public String getSkerFirstdiaCode() {
        return skerFirstdiaCode;
    }

    public void setSkerFirstdiaCode(String skerFirstdiaCode) {
        this.skerFirstdiaCode = skerFirstdiaCode;
    }

    public String getSkerFirstdiaName() {
        return skerFirstdiaName;
    }

    public void setSkerFirstdiaName(String skerFirstdiaName) {
        this.skerFirstdiaName = skerFirstdiaName;
    }

    public String getSkerReport() {
        return skerReport;
    }

    public void setSkerReport(String skerReport) {
        this.skerReport = skerReport;
    }

    public String getSkerTranreasion() {
        return skerTranreasion;
    }

    public void setSkerTranreasion(String skerTranreasion) {
        this.skerTranreasion = skerTranreasion;
    }

    public String getInOrgId() {
        return inOrgId;
    }

    public void setInOrgId(String inOrgId) {
        this.inOrgId = inOrgId;
    }

    public String getInOrgName() {
        return inOrgName;
    }

    public void setInOrgName(String inOrgName) {
        this.inOrgName = inOrgName;
    }

    public String getInDepartment() {
        return inDepartment;
    }

    public void setInDepartment(String inDepartment) {
        this.inDepartment = inDepartment;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date date) {
        this.tranDate = date;
    }

    public String getTranState() {
        return tranState;
    }

    public void setTranState(String tranState) {
        this.tranState = tranState;
    }

    @Override
    protected Serializable pkVal() {
        return id;
    }

    @Override
    public String toString() {
        return "Tran{" +
                ", id=" + id +
                ", outOrgId=" + outOrgId +
                ", outOrgName=" + outOrgName +
                ", outDepartment=" + outDepartment +
                ", outDoctorId=" + outDoctorId +
                ", outDoctorName=" + outDoctorName +
                ", outDoctorPhonenum=" + outDoctorPhonenum +
                ", tranDirect=" + tranDirect +
                ", skerIdcardno=" + skerIdcardno +
                ", skerName=" + skerName +
                ", skerSex=" + skerSex +
                ", skerPhonenum=" + skerPhonenum +
                ", skerContent=" + skerContent +
                ", skerFirstdiaCode=" + skerFirstdiaCode +
                ", skerFirstdiaName=" + skerFirstdiaName +
                ", skerReport=" + skerReport +
                ", skerTranreasion=" + skerTranreasion +
                ", inOrgId=" + inOrgId +
                ", inOrgName=" + inOrgName +
                ", inDepartment=" + inDepartment +
                ", tranDate=" + tranDate +
                ", tranState=" + tranState +
                "}";
    }
}
