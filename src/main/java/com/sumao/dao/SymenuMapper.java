package com.sumao.dao;

import java.util.List;

import com.sumao.model.Symenu;

public interface SymenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Symenu record);

    int insertSelective(Symenu record);

    public Symenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Symenu record);

    int updateByPrimaryKey(Symenu record);
    
    /**
     * 查询所有菜单信息
     * 
     * @return
     */
    public List<Symenu> findMemusById(String id);
}