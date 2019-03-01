package com.dchb.service.agency.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dchb.mapper.agency.AgencyB00Mapper;
import com.dchb.model.agency.B00;
import com.dchb.service.agency.AgencyB00Service;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyB00ServiceImpl extends ServiceImpl<AgencyB00Mapper, B00> implements AgencyB00Service {
    /**
     * @param @param
     * @return List<B00>    返回类型
     * @Title: (getListB00)
     * @Description: 获取机构信息方法
     * @author hukai
     * @date 2018年10月30日
     */
    @Override
    public List<B00> selectListB00(QueryWrapper wrapper) {
        return baseMapper.selectList(wrapper);
    }

}
