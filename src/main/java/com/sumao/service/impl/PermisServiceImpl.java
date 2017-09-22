package com.sumao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sumao.dao.PermisMapper;
import com.sumao.model.ComTable;
import com.sumao.service.PermisService;

/**
 * Top10信息业务层
 * 
 * @author Liutong Version 1.0
 */
@Service("PermisService")
public class PermisServiceImpl implements PermisService {

	@Resource
	private PermisMapper permisMapper;

	@Override
	public List<ComTable> findPermiss(String tokenin) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<ComTable> list = new ArrayList<ComTable>();
		params.put("tokenin", tokenin);
		permisMapper.findPermiss(params);
		if (params.containsKey("Permissresult")) {
			list = (List<ComTable>) params.get("Permissresult");
		} else {
			list = null;
		}
		return list;
	}

}