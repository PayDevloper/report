package com.sumao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.PayruleMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Payrule;
import com.sumao.service.PayService;
import com.sumao.util.Encrypt;

/**
 * 缴费规则创建Service
 * @author heijj
 * 
 */
@Service("payService")
public class PayServiceImpl implements PayService {

	@Resource
	private PayruleMapper payDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#reg(com.sumao.model.Payrule)
	 */
	@Override
	public Payrule reg(Payrule user) {
		// TODO Auto-generated method stub
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#combobox(java.lang.String)
	 */
	@Override
	public List<Payrule> combobox(String codeid) {

		Payrule payr = new Payrule();
		if (codeid != null) {
			payr.setCodeid(codeid);
		}

		List<Payrule> userlist = payDao.findPayrList(payr);
		return userlist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#add(com.sumao.model.Payrule)
	 */
	@Override
	public boolean add(Payrule payr) {
		
		System.out.println("id=="+payr.getId());
		boolean stat=payDao.insert(payr);
		
		return stat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#edit(com.sumao.model.Payrule)
	 */
	@Override
	public boolean edit(Payrule payr) {
		boolean stat=payDao.updateByPrimaryKey(payr);
		return stat;
	}

	/*
	 *数据删除
	 * 
	 * @see com.sumao.service.UserService#del(java.lang.String)
	 */
	@Override
	public boolean del(String ids) {
		
		boolean stat=payDao.deleteByPrimaryKey(ids);
		return stat;
	}
	
	/*
	 *数据批量审核
	 */
	@Override
	public boolean editaudit(String ids) {
		
		boolean stat=payDao.editaudit(ids);
		return stat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#editUsersRole(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void editUsersRole(String userIds, String roleId) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc) 
	 * 
	 * @see com.sumao.service.UserService#getUserInfo(com.sumao.model.Payrule)
	 */
	@Override
	public Payrule getUserInfo(Payrule user) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#editUserInfo(com.sumao.model.Payrule)
	 */
	@Override
	public Payrule editUserInfo(Payrule user) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 数据列表
	 * @author heijj
	 * @date  20161124
	 */
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Payrule payr) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Payrule> list = payDao.findPayrList(payr);
		
		//用PageInfo对结果进行包装
		j.setRows(list);
//		PageInfo<User> page = new PageInfo<User>(list);
		return j;
	}
	
}
