package com.sumao.dao;

import java.util.List;
import com.sumao.model.Paysub;

/**
 * 岗位管理Dao
 * @author liutong
 */
public interface AuthManageMapper {
	/**
	 * 根据条件查询信息
	 * @author liutong
	 * @param payr
	 * @return  List<Paysub>
	 */
	public List<Paysub> findauthList(Paysub payr);
	/**
	 * 翻页查询
	 * @param Paysub payr
	 * @return int
	 */
	public int findTotal(Paysub payr);
	/**
	 * 新建保存信息
	 * @param Paysub payr
	 * @return
	 */
	public boolean insert(Paysub payr);

	/**
	 * 编辑保存信息
	 * @param Paysub payr
	 * @return
	 */
	public boolean updateByPrimaryKey(Paysub payr);
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean deleteByPrimaryKey(String id);
	/**
	 * 根据id查岗位信息
	 * @param Paysub payr
	 * @return
	 */
	public List<Paysub> findposition(Paysub basein);
}