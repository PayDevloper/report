package com.sumao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.DemoMapper;
import com.sumao.model.Demo;
import com.sumao.service.DemoService;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoMapper demoDao;

    public Demo getUserById(int userId) {
        return this.demoDao.selectByPrimaryKey(userId);
    }

	@Override
	public int countUsers() {
		Map<String,Object> mm=new HashMap<String,Object>();
		mm.put("name", "zhangsan");
		demoDao.countUsers(mm);
		
		Object a = mm.get("amount");
		System.out.println(a);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Demo> findUsers(String name) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("name", name);
		demoDao.findUsers(params);
		if(params.containsKey("result")){
			return (List<Demo>) params.get("result");
		} else {
			return new ArrayList<Demo>();
		}
		
	}

    
}