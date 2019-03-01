package com.dchb.model.turn;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author caichunde
 * @since 2018-11-08
 */
@TableName("A01")
public class A01 extends Model<A01> {
    /**
     * 常住人口ID
     */
    @TableId(value = "ID")
    private String id;

    /**
     * 姓名
     */
    private String xm;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 姓名
     */
    private String pwd;


    /**
     * 性别代码：0未知，1男，2女，9不明
     */
    private String xbDm;

    /**
     * 国籍代码
     */
    private String gjDm;

    /**
     * 民族代码
     */
    private String mzDm;

    private String sfzjlbDm;

    /**
     * 身份证号码
     */
    private String sfzhm;

    /**
     * 其他证件号
     */
    private String qtzjhm;

    /**
     * 1非农，2农业，9其他
     */
    private String hkxzDm;

    /**
     * 户籍地址：市+县+乡+村+门牌号名称
     */
    private String hjdz;

    /**
     * 户籍地址代码：DMA-02省+市+县代码
     */
    private String hjdzDm;

    /**
     * 户籍地址邮政编码
     */
    private String hjdzyzbm;

    /**
     * 离开户籍地时间YYYYMMDD
     */
    private Date lkhjdSj;

    /**
     * 现居住地址：市+县+乡+村+门牌号名称
     */
    private String xjzdz;

    /**
     * 现居住地址代码：DMA-02省+市+县代码
     */
    private String xjzdzDm;

    /**
     * 现居住地址邮政编码
     */
    private String xjzdzyzbm;

    /**
     * 进入现居住地时间(到现居住所在区县时间）YYYYMMDD
     */
    private Date jrxjzdSj;

    /**
     * 出生医学证明编号
     */
    private String csyxzmBh;

    /**
     * 出生日期YYYYMMDD
     */
    private Date csrq;

    /**
     * 出生地：市+县+乡+村+门牌号名称
     */
    private String csd;

    /**
     * 出生地代码：DMA-02省+市+县代码
     */
    private String csdDm;

    /**
     * 全日制学历代码
     */
    private String qrzxlDm;

    /**
     * 在职学历代码
     */
    private String zzxlDm;

    /**
     * 学位代码：1名誉博士，2博士，3硕士，4学士
     */
    private String xwDm;

    /**
     * 从业状况代码
     */
    private String cyzkDm;

    /**
     * 党派代码
     */
    private String dpDm;

    /**
     * 工作单位
     */
    private String gzdw;

    /**
     * 工作单位地址：市+县+乡+村+门牌号名称
     */
    private String gzdwdz;

    /**
     * 工作单位地址代码：省+市+县编码
     */
    private String gzdwdzDm;

    /**
     * 工作单位邮政编码
     */
    private String gzdwyzbm;

    /**
     * 职务名称
     */
    private String zwmc;

    /**
     * 专业技术职称代码：1初级2中级3副高4正高
     */
    private String zyjszcDm;

    /**
     * 干部行政级别代码
     */
    private String gbxzjbDm;

    /**
     * 个人收入水平
     */
    private String grsrsp;

    /**
     * 电话号码
     */
    private String dhhm;

    /**
     * 移动电话
     */
    private String yddh;

    /**
     * 电子邮件
     */
    private String dzyj;

    /**
     * 家庭户编号
     */
    private String jtBh;

    /**
     * 与户主关系
     */
    private String yhzgxDm;

    /**
     * 婚姻状况代码
     */
    private String hyzkDm;

    /**
     * 初婚日期
     */
    private Date chrq;

    /**
     * 婚姻变更日期
     */
    private Date hybgrq;

    /**
     * 配偶姓名
     */
    private String poxm;

    /**
     * 配偶身份证件号码
     */
    private String posfzjhm;

    /**
     * 配偶证件类型
     */
    private String pozjlxDm;

    /**
     * 配偶证件号码
     */
    private String pozjhm;

    /**
     * 父亲姓名
     */
    private String fqxm;

    /**
     * 父亲身份证件号码
     */
    private String fqsfzjhm;

    /**
     * 父亲证件类型
     */
    private String fqzjlxDm;

    /**
     * 父亲证件号码
     */
    private String fqzjhm;

    /**
     * 母亲姓名
     */
    private String mqxm;

    /**
     * 母亲身份证件号码
     */
    private String mqsfzjhm;

    private String mqzjlxDm;

    /**
     * 母亲证件号码
     */
    private String mqzjhm;

    /**
     * 监护人姓名
     */
    private String jhrxm;

    /**
     * 监护人身份证件号码
     */
    private String jhrsfzjhm;

    /**
     * 监护人证件类型
     */
    private String jhrzjlxDm;

    /**
     * 监护人证件号码
     */
    private String jhrzjhm;

    /**
     * 联系人姓名(特殊情况下联系人)
     */
    private String lxrxm;

    /**
     * 联系人身份证件号码
     */
    private String lxrsfzjhm;

    /**
     * 联系人证件类型
     */
    private String lxrzjlxDm;

    /**
     * 联系人证件号码
     */
    private String lxrzjhm;

    /**
     * 联系人电话号码
     */
    private String lxrdhhm;

    /**
     * 数据采集时间(初次获取人口信息时间YYYYMMDDHHMMSS)
     */
    private Date sjcjSj;

    /**
     * 数据更新时间(数据更新维护时间YYYYMMDDHHMMSS)
     */
    private Date sjgxSj;

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
    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXbDm() {
        return xbDm;
    }

    public void setXbDm(String xbDm) {
        this.xbDm = xbDm;
    }

    public String getGjDm() {
        return gjDm;
    }

    public void setGjDm(String gjDm) {
        this.gjDm = gjDm;
    }

    public String getMzDm() {
        return mzDm;
    }

    public void setMzDm(String mzDm) {
        this.mzDm = mzDm;
    }

    public String getSfzjlbDm() {
        return sfzjlbDm;
    }

    public void setSfzjlbDm(String sfzjlbDm) {
        this.sfzjlbDm = sfzjlbDm;
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getQtzjhm() {
        return qtzjhm;
    }

    public void setQtzjhm(String qtzjhm) {
        this.qtzjhm = qtzjhm;
    }

    public String getHkxzDm() {
        return hkxzDm;
    }

    public void setHkxzDm(String hkxzDm) {
        this.hkxzDm = hkxzDm;
    }

    public String getHjdz() {
        return hjdz;
    }

    public void setHjdz(String hjdz) {
        this.hjdz = hjdz;
    }

    public String getHjdzDm() {
        return hjdzDm;
    }

    public void setHjdzDm(String hjdzDm) {
        this.hjdzDm = hjdzDm;
    }

    public String getHjdzyzbm() {
        return hjdzyzbm;
    }

    public void setHjdzyzbm(String hjdzyzbm) {
        this.hjdzyzbm = hjdzyzbm;
    }

    public Date getLkhjdSj() {
        return lkhjdSj;
    }

    public void setLkhjdSj(Date lkhjdSj) {
        this.lkhjdSj = lkhjdSj;
    }

    public String getXjzdz() {
        return xjzdz;
    }

    public void setXjzdz(String xjzdz) {
        this.xjzdz = xjzdz;
    }

    public String getXjzdzDm() {
        return xjzdzDm;
    }

    public void setXjzdzDm(String xjzdzDm) {
        this.xjzdzDm = xjzdzDm;
    }

    public String getXjzdzyzbm() {
        return xjzdzyzbm;
    }

    public void setXjzdzyzbm(String xjzdzyzbm) {
        this.xjzdzyzbm = xjzdzyzbm;
    }

    public Date getJrxjzdSj() {
        return jrxjzdSj;
    }

    public void setJrxjzdSj(Date jrxjzdSj) {
        this.jrxjzdSj = jrxjzdSj;
    }

    public String getCsyxzmBh() {
        return csyxzmBh;
    }

    public void setCsyxzmBh(String csyxzmBh) {
        this.csyxzmBh = csyxzmBh;
    }

    public Date getCsrq() {
        return csrq;
    }

    public void setCsrq(Date csrq) {
        this.csrq = csrq;
    }

    public String getCsd() {
        return csd;
    }

    public void setCsd(String csd) {
        this.csd = csd;
    }

    public String getCsdDm() {
        return csdDm;
    }

    public void setCsdDm(String csdDm) {
        this.csdDm = csdDm;
    }

    public String getQrzxlDm() {
        return qrzxlDm;
    }

    public void setQrzxlDm(String qrzxlDm) {
        this.qrzxlDm = qrzxlDm;
    }

    public String getZzxlDm() {
        return zzxlDm;
    }

    public void setZzxlDm(String zzxlDm) {
        this.zzxlDm = zzxlDm;
    }

    public String getXwDm() {
        return xwDm;
    }

    public void setXwDm(String xwDm) {
        this.xwDm = xwDm;
    }

    public String getCyzkDm() {
        return cyzkDm;
    }

    public void setCyzkDm(String cyzkDm) {
        this.cyzkDm = cyzkDm;
    }

    public String getDpDm() {
        return dpDm;
    }

    public void setDpDm(String dpDm) {
        this.dpDm = dpDm;
    }

    public String getGzdw() {
        return gzdw;
    }

    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }

    public String getGzdwdz() {
        return gzdwdz;
    }

    public void setGzdwdz(String gzdwdz) {
        this.gzdwdz = gzdwdz;
    }

    public String getGzdwdzDm() {
        return gzdwdzDm;
    }

    public void setGzdwdzDm(String gzdwdzDm) {
        this.gzdwdzDm = gzdwdzDm;
    }

    public String getGzdwyzbm() {
        return gzdwyzbm;
    }

    public void setGzdwyzbm(String gzdwyzbm) {
        this.gzdwyzbm = gzdwyzbm;
    }

    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }

    public String getZyjszcDm() {
        return zyjszcDm;
    }

    public void setZyjszcDm(String zyjszcDm) {
        this.zyjszcDm = zyjszcDm;
    }

    public String getGbxzjbDm() {
        return gbxzjbDm;
    }

    public void setGbxzjbDm(String gbxzjbDm) {
        this.gbxzjbDm = gbxzjbDm;
    }

    public String getGrsrsp() {
        return grsrsp;
    }

    public void setGrsrsp(String grsrsp) {
        this.grsrsp = grsrsp;
    }

    public String getDhhm() {
        return dhhm;
    }

    public void setDhhm(String dhhm) {
        this.dhhm = dhhm;
    }

    public String getYddh() {
        return yddh;
    }

    public void setYddh(String yddh) {
        this.yddh = yddh;
    }

    public String getDzyj() {
        return dzyj;
    }

    public void setDzyj(String dzyj) {
        this.dzyj = dzyj;
    }

    public String getJtBh() {
        return jtBh;
    }

    public void setJtBh(String jtBh) {
        this.jtBh = jtBh;
    }

    public String getYhzgxDm() {
        return yhzgxDm;
    }

    public void setYhzgxDm(String yhzgxDm) {
        this.yhzgxDm = yhzgxDm;
    }

    public String getHyzkDm() {
        return hyzkDm;
    }

    public void setHyzkDm(String hyzkDm) {
        this.hyzkDm = hyzkDm;
    }

    public Date getChrq() {
        return chrq;
    }

    public void setChrq(Date chrq) {
        this.chrq = chrq;
    }

    public Date getHybgrq() {
        return hybgrq;
    }

    public void setHybgrq(Date hybgrq) {
        this.hybgrq = hybgrq;
    }

    public String getPoxm() {
        return poxm;
    }

    public void setPoxm(String poxm) {
        this.poxm = poxm;
    }

    public String getPosfzjhm() {
        return posfzjhm;
    }

    public void setPosfzjhm(String posfzjhm) {
        this.posfzjhm = posfzjhm;
    }

    public String getPozjlxDm() {
        return pozjlxDm;
    }

    public void setPozjlxDm(String pozjlxDm) {
        this.pozjlxDm = pozjlxDm;
    }

    public String getPozjhm() {
        return pozjhm;
    }

    public void setPozjhm(String pozjhm) {
        this.pozjhm = pozjhm;
    }

    public String getFqxm() {
        return fqxm;
    }

    public void setFqxm(String fqxm) {
        this.fqxm = fqxm;
    }

    public String getFqsfzjhm() {
        return fqsfzjhm;
    }

    public void setFqsfzjhm(String fqsfzjhm) {
        this.fqsfzjhm = fqsfzjhm;
    }

    public String getFqzjlxDm() {
        return fqzjlxDm;
    }

    public void setFqzjlxDm(String fqzjlxDm) {
        this.fqzjlxDm = fqzjlxDm;
    }

    public String getFqzjhm() {
        return fqzjhm;
    }

    public void setFqzjhm(String fqzjhm) {
        this.fqzjhm = fqzjhm;
    }

    public String getMqxm() {
        return mqxm;
    }

    public void setMqxm(String mqxm) {
        this.mqxm = mqxm;
    }

    public String getMqsfzjhm() {
        return mqsfzjhm;
    }

    public void setMqsfzjhm(String mqsfzjhm) {
        this.mqsfzjhm = mqsfzjhm;
    }

    public String getMqzjlxDm() {
        return mqzjlxDm;
    }

    public void setMqzjlxDm(String mqzjlxDm) {
        this.mqzjlxDm = mqzjlxDm;
    }

    public String getMqzjhm() {
        return mqzjhm;
    }

    public void setMqzjhm(String mqzjhm) {
        this.mqzjhm = mqzjhm;
    }

    public String getJhrxm() {
        return jhrxm;
    }

    public void setJhrxm(String jhrxm) {
        this.jhrxm = jhrxm;
    }

    public String getJhrsfzjhm() {
        return jhrsfzjhm;
    }

    public void setJhrsfzjhm(String jhrsfzjhm) {
        this.jhrsfzjhm = jhrsfzjhm;
    }

    public String getJhrzjlxDm() {
        return jhrzjlxDm;
    }

    public void setJhrzjlxDm(String jhrzjlxDm) {
        this.jhrzjlxDm = jhrzjlxDm;
    }

    public String getJhrzjhm() {
        return jhrzjhm;
    }

    public void setJhrzjhm(String jhrzjhm) {
        this.jhrzjhm = jhrzjhm;
    }

    public String getLxrxm() {
        return lxrxm;
    }

    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    public String getLxrsfzjhm() {
        return lxrsfzjhm;
    }

    public void setLxrsfzjhm(String lxrsfzjhm) {
        this.lxrsfzjhm = lxrsfzjhm;
    }

    public String getLxrzjlxDm() {
        return lxrzjlxDm;
    }

    public void setLxrzjlxDm(String lxrzjlxDm) {
        this.lxrzjlxDm = lxrzjlxDm;
    }

    public String getLxrzjhm() {
        return lxrzjhm;
    }

    public void setLxrzjhm(String lxrzjhm) {
        this.lxrzjhm = lxrzjhm;
    }

    public String getLxrdhhm() {
        return lxrdhhm;
    }

    public void setLxrdhhm(String lxrdhhm) {
        this.lxrdhhm = lxrdhhm;
    }

    public Date getSjcjSj() {
        return sjcjSj;
    }

    public void setSjcjSj(Date sjcjSj) {
        this.sjcjSj = sjcjSj;
    }

    public Date getSjgxSj() {
        return sjgxSj;
    }

    public void setSjgxSj(Date sjgxSj) {
        this.sjgxSj = sjgxSj;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "A01{" +
        ", id=" + id +
        ", xm=" + xm +
        ", xbDm=" + xbDm +
        ", gjDm=" + gjDm +
        ", mzDm=" + mzDm +
        ", sfzjlbDm=" + sfzjlbDm +
        ", sfzhm=" + sfzhm +
        ", qtzjhm=" + qtzjhm +
        ", hkxzDm=" + hkxzDm +
        ", hjdz=" + hjdz +
        ", hjdzDm=" + hjdzDm +
        ", hjdzyzbm=" + hjdzyzbm +
        ", lkhjdSj=" + lkhjdSj +
        ", xjzdz=" + xjzdz +
        ", xjzdzDm=" + xjzdzDm +
        ", xjzdzyzbm=" + xjzdzyzbm +
        ", jrxjzdSj=" + jrxjzdSj +
        ", csyxzmBh=" + csyxzmBh +
        ", csrq=" + csrq +
        ", csd=" + csd +
        ", csdDm=" + csdDm +
        ", qrzxlDm=" + qrzxlDm +
        ", zzxlDm=" + zzxlDm +
        ", xwDm=" + xwDm +
        ", cyzkDm=" + cyzkDm +
        ", dpDm=" + dpDm +
        ", gzdw=" + gzdw +
        ", gzdwdz=" + gzdwdz +
        ", gzdwdzDm=" + gzdwdzDm +
        ", gzdwyzbm=" + gzdwyzbm +
        ", zwmc=" + zwmc +
        ", zyjszcDm=" + zyjszcDm +
        ", gbxzjbDm=" + gbxzjbDm +
        ", grsrsp=" + grsrsp +
        ", dhhm=" + dhhm +
        ", yddh=" + yddh +
        ", dzyj=" + dzyj +
        ", jtBh=" + jtBh +
        ", yhzgxDm=" + yhzgxDm +
        ", hyzkDm=" + hyzkDm +
        ", chrq=" + chrq +
        ", hybgrq=" + hybgrq +
        ", poxm=" + poxm +
        ", posfzjhm=" + posfzjhm +
        ", pozjlxDm=" + pozjlxDm +
        ", pozjhm=" + pozjhm +
        ", fqxm=" + fqxm +
        ", fqsfzjhm=" + fqsfzjhm +
        ", fqzjlxDm=" + fqzjlxDm +
        ", fqzjhm=" + fqzjhm +
        ", mqxm=" + mqxm +
        ", mqsfzjhm=" + mqsfzjhm +
        ", mqzjlxDm=" + mqzjlxDm +
        ", mqzjhm=" + mqzjhm +
        ", jhrxm=" + jhrxm +
        ", jhrsfzjhm=" + jhrsfzjhm +
        ", jhrzjlxDm=" + jhrzjlxDm +
        ", jhrzjhm=" + jhrzjhm +
        ", lxrxm=" + lxrxm +
        ", lxrsfzjhm=" + lxrsfzjhm +
        ", lxrzjlxDm=" + lxrzjlxDm +
        ", lxrzjhm=" + lxrzjhm +
        ", lxrdhhm=" + lxrdhhm +
        ", sjcjSj=" + sjcjSj +
        ", sjgxSj=" + sjgxSj +
        ", sjcjjgBm=" + sjcjjgBm +
        ", sjcjjgmc=" + sjcjjgmc +
        ", sjcjryBm=" + sjcjryBm +
        ", status=" + status +
        "}";
    }
}
