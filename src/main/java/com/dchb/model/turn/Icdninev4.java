package com.dchb.model.turn;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author caichunde
 * @since 2018-11-23
 */
@TableName("dict_icdninev4")
public class Icdninev4 extends Model<Icdninev4> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ICDSID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Double icdsid;

    /**
     * 全拼首字母
     */
    private String icdnamespell;

    /**
     * 名称
     */
    private String iddname;

    /**
     * 编码
     */
    private String icdcode;

    private String icdattr;


    public Double getIcdsid() {
        return icdsid;
    }

    public void setIcdsid(Double icdsid) {
        this.icdsid = icdsid;
    }

    public String getIcdnamespell() {
        return icdnamespell;
    }

    public void setIcdnamespell(String icdnamespell) {
        this.icdnamespell = icdnamespell;
    }

    public String getIddname() {
        return iddname;
    }

    public void setIddname(String iddname) {
        this.iddname = iddname;
    }

    public String getIcdcode() {
        return icdcode;
    }

    public void setIcdcode(String icdcode) {
        this.icdcode = icdcode;
    }

    public String getIcdattr() {
        return icdattr;
    }

    public void setIcdattr(String icdattr) {
        this.icdattr = icdattr;
    }

    @Override
    protected Serializable pkVal() {
        return this.icdsid;
    }

    @Override
    public String toString() {
        return "Icdninev4{" +
        ", icdsid=" + icdsid +
        ", icdnamespell=" + icdnamespell +
        ", iddname=" + iddname +
        ", icdcode=" + icdcode +
        ", icdattr=" + icdattr +
        "}";
    }
}
