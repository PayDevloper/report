package com.sumao.dao;

import java.util.List;
import com.sumao.model.Paysub;

/**
 * 子项管理Dao
 * @author heijj
 */
public interface NavigationMapper {

	List<Paysub> findAuthsort();

	List<Paysub> findSubauthort();

	List<Paysub> findAuthbasic(String basic);

}