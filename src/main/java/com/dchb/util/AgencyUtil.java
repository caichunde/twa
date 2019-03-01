package com.dchb.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dchb.model.agency.B00;
import com.dchb.model.agency.B13;
import com.dchb.service.agency.AgencyB00Service;
import com.dchb.service.agency.B13Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @param @param
 * @author caichunde
 * @Title: (AgencyUtil)
 * @Description: 获取机构信息工具类
 * @date 2018年11月8日
 */
@Component
public class AgencyUtil {

    @Autowired
    private AgencyB00Service agencyB00Service;
    @Autowired
    private B13Service b13Service;

    private static AgencyUtil agencyUtil;

    public void setAgencyB00Service(AgencyB00Service agencyB00Service) {

        this.agencyB00Service = agencyB00Service;
    }

    public void setDepartmentB13Service(B13Service departmentB13Service) {
        this.b13Service = departmentB13Service;
    }

    @PostConstruct
    public void init() {
        agencyUtil = this;
        setAgencyB00Service(this.agencyB00Service);
        setDepartmentB13Service(this.b13Service);
    }

    /**
     * @param @param
     * @return List<B00>    返回类型
     * @Title: (getListB00)
     * @Description: 获取机构信息方法参数为空则获取所有可用机构
     * @author caichunde
     * @date 2018年11月8日
     */
    public static List<B00> getListB00(String orgId) {
        QueryWrapper<B00> wrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(orgId)) {
            wrapper.eq("orgId", orgId);
        }
        wrapper.eq("bEnabled", "1");
        List<B00> b00List = agencyUtil.agencyB00Service.selectListB00(wrapper);
        return b00List;
    }

    /**
     * @param @param
     * @return List<B00>    返回类型
     * @Title: (getListB00)
     * @Description: 获取机构信息方法
     * @author caichunde
     * @date 2018年11月8日
     */
    public static List<B13> getListDepartmentB13(String orgId) {
        QueryWrapper<B13> wrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(orgId)) {
            wrapper.eq("jg_dm", orgId);
        }
        wrapper.eq("status", "1");
        List<B13> b00List = agencyUtil.b13Service.selectListB13(wrapper);
        return b00List;
    }
}
