package com.sumao.dao;

import java.util.List;
import java.util.Map;

import com.sumao.model.Demo;

public interface DemoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Demo record);

    int insertSelective(Demo record);

    Demo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Demo record);

    int updateByPrimaryKey(Demo record);
    
    // 调用带返回值的存储过程；
    public int countUsers(Map<String, Object> params);
    
    // 调用带游标的存储过程
    public List<Demo> findUsers(Map<String, Object> params);
}