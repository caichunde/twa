package com.dchb.service.turn;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dchb.model.turn.Icdninev4;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caichunde
 * @since 2018-11-23
 */
public interface Icdninev4Service extends IService<Icdninev4> {
    List<Icdninev4> getIcdninev4List(String iddName);
}
