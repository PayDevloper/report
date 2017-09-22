package com.sumao.dao;

import java.util.List;
import com.sumao.model.ComTable;

/**
 * 省市联动的mapper auhtor：liutong version：1.0
 */
public interface RegionMapper {
	/**
	 * 用于返回页面的json,返回值是省信息的json
	 */
	public List<ComTable> getProvince();

	/**
	 * 用于返回页面的json,返回值是市信息的json，provincename是传来的省名字
	 */
	public List<ComTable> getCityByProvince(String provincename);
}