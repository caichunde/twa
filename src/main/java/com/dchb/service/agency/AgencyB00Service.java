package com.dchb.service.agency;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dchb.model.agency.B00;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AgencyB00Service extends IService<B00>{
	/**
	 * @param @param
	 * @return List<B00>    返回类型
	 * @Title: (getListB00)
	 * @Description: 获取机构信息方法
	 * @author hukai
	 * @date 2018年10月30日
	 */
	List<B00> selectListB00(QueryWrapper wrapper);
}
