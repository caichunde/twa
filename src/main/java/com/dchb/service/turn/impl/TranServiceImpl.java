package com.dchb.service.turn.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dchb.mapper.turn.TranMapper;
import com.dchb.model.turn.Tran;
import com.dchb.service.turn.TranService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caichunde
 * @since 2018-11-20
 */
@Service
public class TranServiceImpl extends ServiceImpl<TranMapper, Tran> implements TranService {
    @Override
    public int saveTranOutDel(String id) {
        QueryWrapper<Tran> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        return baseMapper.delete(queryWrapper);
    }


    @Override
    public List<Tran> selectList(QueryWrapper queryWrapper) {
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(QueryWrapper queryWrapper) {
        return baseMapper.selectMaps(queryWrapper);
    }

    @Override
    public IPage<Tran> selectPage(Integer pageNo, Integer pageSize, QueryWrapper queryWrapper) {
        return baseMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 修改患者转出信息
     * @author caichunde
     * @date 2018-11-21
     */
    @Override
    public int updateTran(Tran tran) {
        int count = baseMapper.updateById(tran);
        return count;
    }
}
