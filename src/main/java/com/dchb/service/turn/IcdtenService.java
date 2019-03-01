package com.dchb.service.turn;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dchb.model.turn.Icdten;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caichunde
 * @since 2018-11-23
 */
public interface IcdtenService extends IService<Icdten> {
    List<Icdten> getIcdtenList(String iddName);
}
