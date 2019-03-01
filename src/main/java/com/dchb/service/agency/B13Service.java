package com.dchb.service.agency;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dchb.model.agency.B13;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caichunde
 * @since 2019-01-04
 */
public interface B13Service extends IService<B13> {
    List<B13> selectListB13(QueryWrapper wrapper);
}
