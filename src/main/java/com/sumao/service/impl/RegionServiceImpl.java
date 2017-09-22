package com.sumao.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sumao.dao.RegionMapper;
import com.sumao.model.ComTable;
import com.sumao.service.RegionService;

/**
 * 省市联动的service实现类 auhtor：liutong version：1.0
 */
@Service("RegionService")
public class RegionServiceImpl implements RegionService {

	@Resource
	private RegionMapper regionMapper;

	/**
	 * 用于返回页面的json,返回值是省信息的json
	 */
	@Override
	public List<ComTable> getProvince() {
		List<ComTable> list = regionMapper.getProvince();
		return list;
	}

	/**
	 * 用于返回页面的json,返回值是市信息的json，provincename是传来的省名字
	 */
	@Override
	public List<ComTable> getCityByProvince(String provincename) {
		List<ComTable> list = regionMapper.getCityByProvince(provincename);
		return list;
	}
}