package com.dchb.model.agency;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author caichunde
 * @since 2019-01-04
 */
@TableName("B13")
public class B13 extends Model<B13> {

    private static final long serialVersionUID = 1L;

    /**
     * 科室信息ID
     */
    @TableId(value = "ID")
    private String id;

    /**
     * 机构名称
     */
    private String jgmc;

    /**
     * 机构代码
     */
    private String jgDm;

    /**
     * 科室类别码(用于双向转诊，2018.10.15增):0.非医疗科室（默认）1.门诊2.住院病区3.门诊护士站4.住院护士站5.门诊药房6.住院药房7.门住合一药房8.中药房9.化验10.检查11.病理室12.处置室
     */
    private String kslb;

    /**
     * 科室名称
     */
    @TableField("ksmc")
    private String inDepartment;

    /**
     * 科室级别代码:0院领导，1一级科室，2二级科室，3三级科室，9其他
     */
    private String ksjbDm;

    /**
     * 科室代码:DMA-01
     */
    private String ksDm;

    /**
     * 上级科室名称
     */
    private String sjksmc;

    /**
     * 上级科室代码
     */
    private String sjksDm;

    /**
     * 编制床位数
     */
    private Integer bzcws;

    /**
     * 开放床位数
     */
    private Integer kfcws;

    /**
     * 最大床位数:含加床等临时床位
     */
    private Integer zdcws;

    /**
     * 数据维护时间:YYYYMMDDHHMMSS
     */
    private LocalDateTime sjwhSj;

    /**
     * 数据采集机构编码
     */
    private String sjcjjgBm;

    /**
     * 数据采集机构名称
     */
    private String sjcjjgmc;

    /**
     * 数据采集人员编码
     */
    private String sjcjryBm;

    /**
     * 状态：0删除，1可用，2不可用
     */
    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getJgDm() {
        return jgDm;
    }

    public void setJgDm(String jgDm) {
        this.jgDm = jgDm;
    }

    public String getKslb() {
        return kslb;
    }

    public void setKslb(String kslb) {
        this.kslb = kslb;
    }

    public String getInDepartment() {
        return inDepartment;
    }

    public void setInDepartment(String inDepartment) {
        this.inDepartment = inDepartment;
    }

    public String getKsjbDm() {
        return ksjbDm;
    }

    public void setKsjbDm(String ksjbDm) {
        this.ksjbDm = ksjbDm;
    }

    public String getKsDm() {
        return ksDm;
    }

    public void setKsDm(String ksDm) {
        this.ksDm = ksDm;
    }

    public String getSjksmc() {
        return sjksmc;
    }

    public void setSjksmc(String sjksmc) {
        this.sjksmc = sjksmc;
    }

    public String getSjksDm() {
        return sjksDm;
    }

    public void setSjksDm(String sjksDm) {
        this.sjksDm = sjksDm;
    }

    public Integer getBzcws() {
        return bzcws;
    }

    public void setBzcws(Integer bzcws) {
        this.bzcws = bzcws;
    }

    public Integer getKfcws() {
        return kfcws;
    }

    public void setKfcws(Integer kfcws) {
        this.kfcws = kfcws;
    }

    public Integer getZdcws() {
        return zdcws;
    }

    public void setZdcws(Integer zdcws) {
        this.zdcws = zdcws;
    }

    public LocalDateTime getSjwhSj() {
        return sjwhSj;
    }

    public void setSjwhSj(LocalDateTime sjwhSj) {
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "B13{" +
        ", id=" + id +
        ", jgmc=" + jgmc +
        ", jgDm=" + jgDm +
        ", kslb=" + kslb +
        ", inDepartment=" + inDepartment +
        ", ksjbDm=" + ksjbDm +
        ", ksDm=" + ksDm +
        ", sjksmc=" + sjksmc +
        ", sjksDm=" + sjksDm +
        ", bzcws=" + bzcws +
        ", kfcws=" + kfcws +
        ", zdcws=" + zdcws +
        ", sjwhSj=" + sjwhSj +
        ", sjcjjgBm=" + sjcjjgBm +
        ", sjcjjgmc=" + sjcjjgmc +
        ", sjcjryBm=" + sjcjryBm +
        ", status=" + status +
        "}";
    }
}
