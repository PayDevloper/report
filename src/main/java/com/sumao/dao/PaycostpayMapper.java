package com.sumao.dao;

import java.util.List;

import com.sumao.model.Pay_rightgoldcheck;
import com.sumao.model.Paycostpay;

/**
 * 费用支付DAO
 * @author heijj
 *
 */
public interface PaycostpayMapper {
	
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	public boolean deleteByPrimaryKey(String id);
	
	/**
	 *数据批量审核
	 *@param String ids
	 */
	public boolean editaudit(String id);

	/**
	 * 新建保存信息
	 * @param Paycostpay payr
	 * @return
	 */
	public boolean insert(Paycostpay payr);

	/**
	 * 根据费用支付数据的ID，得到相应信息
	 * @param String id
	 * @author heijj
	 * @return Paycostpay 
	 */
	public Paycostpay selectByPrimaryKey(String id);

	/**
	 * 编辑保存信息
	 * @param Paycostpay payr
	 * @return
	 */
	public boolean updateByPrimaryKey(Paycostpay payr);

	/**
	 * 根据条件查询信息
	 * @author heijj
	 * @param Paycostpay payr
	 * @return  List<Paycostpay>
	 */
	public List<Paycostpay> findPayrList(Paycostpay payr);

	/**
	 * 分页查询
	 * @param Paycostpay payr
	 * @return int
	 */
	public int findPayrTotal(Paycostpay payr);
	
	/**
	 * 卖家中心--保证金缴费查看分页查询
	 * @param Paycostpay payr
	 * @return int
	 */
	public int findPayrcostTotal(Paycostpay payr);
	
	/**
	 * 卖家中心--保证金缴费查看数据列表
	 * @author heijj
	 * @param Paycostpay payr
	 * @return  List<Paycostpay>
	 */
	public List<Paycostpay> findPayrcostList(Paycostpay payr);
	
	/**
	 * 分页查询
	 * @param Paycostpay payr
	 * @return int
	 */
	public int findPayallchTotal(Paycostpay payr);
	
	/**
	 * 根据条件查询信息
	 * @author heijj
	 * @param payr
	 * @return  List<Paycostpay>
	 */
	public List<Paycostpay> findPayallchList(Paycostpay payr);
	
	/**
	 * 得到保证金子项信息
	 * @return
	 */
	public List<Paycostpay> selectgoldsub();
	
	/**
	 * 根据是否为权利金以及缴费编号，查询是否已创建
	 * @param String
	 * @return int
	 */
	public int getprovingused(String paymentcode);
}