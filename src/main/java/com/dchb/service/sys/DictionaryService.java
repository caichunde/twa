package com.dchb.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dchb.model.sys.Dictionary;

public interface DictionaryService extends IService<Dictionary>{
	/**
	 * 
		 * 
		 * @Title: (insertDictItems)   
		 * @Description: TODO(新增数据字典)    
		 * @param @param 
		 * @return ReturnMsg    返回类型 
		 * @author hukai
		 * @date 2018年10月17日上午11:12:34
	 */
	public void insertDictItems(Dictionary dictionary);
	/**
	 * 
		 * 
		 * @Title: (updateDictItems)   
		 * @Description: TODO(修改数据字典)    
		 * @param @param 
		 * @return ReturnMsg    返回类型 
		 * @author hukai
		 * @date 2018年10月17日上午11:14:39
	 */
	public void updateDictItems(Dictionary dictionary);
}
