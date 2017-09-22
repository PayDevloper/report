package com.sumao.service;

import java.util.List;

import com.sumao.model.Demo;

public interface DemoService {

	public Demo getUserById(int userId);
	
    // 调用带返回值的存储过程；
    public int countUsers();
    
    // 调用带游标的存储过程
    public List<Demo> findUsers(String name);
    
    
}