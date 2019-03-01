package com.dchb.service.turn.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dchb.mapper.turn.A01Mapper;
import com.dchb.model.turn.A01;
import com.dchb.service.turn.A01Service;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caichunde
 * @since 2018-11-08
 */
@Service
public class A01ServiceImpl extends ServiceImpl<A01Mapper, A01> implements A01Service {
    /**
     * @param @param
     * @return List<A01>    返回类型
     * @Title: (selectListA01)
     * @Description: 获取机构信息方法
     * @author hukai
     * @date 2018年10月30日
     */
    @Override
    public A01 getA01One(QueryWrapper wrapper) {
        return baseMapper.selectOne(wrapper);
    }
}
