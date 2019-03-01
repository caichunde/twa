package com.dchb.service.agency.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dchb.model.agency.B13;
import com.dchb.mapper.agency.B13Mapper;
import com.dchb.service.agency.B13Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caichunde
 * @since 2019-01-04
 */
@Service
public class B13ServiceImpl extends ServiceImpl<B13Mapper, B13> implements B13Service {

    @Override
    public List<B13> selectListB13(QueryWrapper wrapper) {
        return baseMapper.selectList(wrapper);
    }
}
