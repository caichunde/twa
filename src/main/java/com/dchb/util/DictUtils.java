package com.dchb.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dchb.model.sys.Dictionary;
import com.dchb.redis.RedisUtil;

@Repository
public class DictUtils {

	public static final String CACHE_DICT_MAP = "dictMap"; // 字典缓存key
	@Autowired
	private  RedisUtil redisUtil ;
	
	public  List<Dictionary> getDictAllList(){
//		redisUtil.remove(CACHE_DICT_MAP);
		String result;
		List<Dictionary> dictList = new ArrayList<>();
		if(redisUtil.exists(CACHE_DICT_MAP)) {
			 result = redisUtil.get(CACHE_DICT_MAP).toString();
			 result = StringUtils.substring(result, result.indexOf("["));//TODO
			 dictList = JSON.parseArray(result, Dictionary.class);  
		}
		if(dictList == null || dictList.isEmpty()){
			dictList = new Dictionary().selectList(new QueryWrapper<Dictionary>().eq("BENABLED", 1).orderByAsc("ITEMCODE"));
			redisUtil.set(CACHE_DICT_MAP, JSON.toJSON(dictList).toString());
		}
		return dictList;
		
	}
	
	
	/**
	 * 获取某个类型的字典
	 * @param itemType
	 * @return List<Dictionary>
	 */
	public  List<Dictionary> getDictListByType(String itemType){
		List<Dictionary> dictList = getDictAllList().stream().filter(d->{return d.getItemType().equals(itemType);}).collect(Collectors.toList());
		return dictList;
	}

	/**
	 * @Description 根据字典类型、字典code获取具体content
	 * @param itemType、itemCode
	 * @return Dictionary
	 * @author caichunde
	 * @date 2018-11-8
	 */
	public  Dictionary getDictListByType(String itemType, String itemCode){
		List<Dictionary> dictList = getDictListByType(itemType);
		Dictionary dictionary = new Dictionary();
		for(Dictionary d : dictList) {
			if(itemCode.equals(d.getItemCode()) ) {
				dictionary = d;
			}
		}
		return dictionary;
	}
}
