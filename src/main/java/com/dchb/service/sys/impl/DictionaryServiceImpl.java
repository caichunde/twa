package com.dchb.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dchb.mapper.sys.DictionaryMapper;
import com.dchb.model.sys.Dictionary;
import com.dchb.service.sys.DictionaryService;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService{

	@Override
	public void insertDictItems(Dictionary dictionary) {
		baseMapper.insert(dictionary);
	}

	@Override
	public void updateDictItems(Dictionary dictionary) {
		QueryWrapper<Dictionary> wrapper = new QueryWrapper<Dictionary>();
		wrapper.eq("ID", dictionary);
		wrapper.setEntity(dictionary);
		baseMapper.update(dictionary, wrapper);
	}

}
