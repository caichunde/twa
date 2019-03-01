package com.dchb.service.turn.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dchb.mapper.turn.Icdninev4Mapper;
import com.dchb.model.turn.Icdninev4;
import com.dchb.service.turn.Icdninev4Service;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caichunde
 * @since 2018-11-23
 */
@Service
public class Icdninev4ServiceImpl extends ServiceImpl<Icdninev4Mapper, Icdninev4> implements Icdninev4Service {

    @Override
    public List<Icdninev4> getIcdninev4List(String iddName) {
        QueryWrapper<Icdninev4> queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(iddName)) {
            queryWrapper.and(wrapper -> wrapper.like("iddname", iddName.trim())
                    .or().like("icdcode", iddName.trim())
                    .or().like("icdnamespell", iddName.trim()));
        }
        queryWrapper.orderByAsc("iddname");
        return baseMapper.selectList(queryWrapper);
    }
}
