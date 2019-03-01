package com.dchb.service.turn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dchb.model.turn.Tran;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 * @author caichunde
 * @since 2018-11-20
 */
public interface TranService extends IService<Tran> {
    int saveTranOutDel(String id);

    List<Tran> selectList(QueryWrapper queryWrapper);

    List<Map<String, Object>> selectMaps(QueryWrapper queryWrapper);

    IPage<Tran> selectPage(Integer pageNo, Integer pageSize, QueryWrapper queryWrapper);

    /**
     * @param
     * @return ReturnMsg
     * @Description: 修改患者转出信息
     * @author caichunde
     * @date 2018-11-21
     */
    int updateTran(Tran tran);

}
