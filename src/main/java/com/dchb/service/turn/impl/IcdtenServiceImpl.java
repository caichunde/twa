package com.dchb.service.turn.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dchb.mapper.turn.IcdtenMapper;
import com.dchb.model.turn.Icdten;
import com.dchb.service.turn.IcdtenService;
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
public class IcdtenServiceImpl extends ServiceImpl<IcdtenMapper, Icdten> implements IcdtenService {

    @Override
    public List<Icdten> getIcdtenList(String iddName) {
        QueryWrapper<Icdten> queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(iddName)) {
            queryWrapper.and(wrapper -> wrapper.like("iddname", iddName.trim())
                    .or().like("icdcode", iddName.trim())
                    .or().like("icdnamespell", iddName.trim()));
        }
        queryWrapper.orderByAsc("iddname");
        return baseMapper.selectList(queryWrapper);
    }
}
