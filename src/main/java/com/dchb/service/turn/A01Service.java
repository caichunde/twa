package com.dchb.service.turn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dchb.model.turn.A01;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caichunde
 * @since 2018-11-08
 */
public interface A01Service extends IService<A01> {
    /**
     * @param @param
     * @return List<A01>    返回类型
     * @Title: (selectListA01)
     * @Description: 获取机构信息方法
     * @author hukai
     * @date 2018年10月30日
     */
    public A01 getA01One(QueryWrapper wrapper);

}
