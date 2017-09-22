package com.sumao.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sumao.dao.AuthDistributeMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Paysub;
import com.sumao.service.AuthDistributeService;

/**
 * 权限分配创建Service
 * @author liutong
 * 
 */
@Service("AuthDistributeService")
public class AuthDistributeServiceImpl implements AuthDistributeService {
	@Resource
	private AuthDistributeMapper authDistributeDao;

	/**
	 * 权限数据列表总数+列表
	 * @author liutong
	 * @date  20170116
	 */
	@Override
	public int disauthTotal(Paysub payr) {	
		int total=authDistributeDao.disauthTotal(payr);
		return total;
	}
	public EasyuiDataGridJson disauthdatagrid(EasyuiDataGrid dg, Paysub payr,int total) {
		
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = authDistributeDao.finddisauthList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	/**
	 * 岗位数据列表总数+列表
	 * @author liutong
	 * @date  20170116
	 */
	@Override
	public int findposTotal(Paysub payr) {
		int total=authDistributeDao.findposTotal(payr);
		return total;
	}
	@Override
	public EasyuiDataGridJson datagridpos(EasyuiDataGrid dg, Paysub payr, int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = authDistributeDao.findpositionList(payr);
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(list);
		
		return j;
	}
	/**
	 * 查询出权限的父类栏以及子类栏
	 * @param dg
	 * @param payr
	 * @param int
	 * @return
	 */
	@Override
	public List<Paysub> findAuthsort() {
		List<Paysub> findSort=new ArrayList<Paysub>();
		findSort=this.authDistributeDao.findAuthsort();
		return findSort;
	}
	@Override
	public List<Paysub> findSubauthort() {
		List<Paysub> findsubsort=new ArrayList<Paysub>();
		findsubsort=this.authDistributeDao.findSubauthort();
		return findsubsort;
	}
	/**
	 * 新建保存信息
	 * @param Paysub payr
	 * @return
	 */
	@Override
	public boolean add(Paysub payr) {
		
		boolean stat=authDistributeDao.insert(payr);
		
		return stat;
	}
	/**
	 * 编辑保存信息
	 * @param Paysub payr
	 * @return
	 */
	@Override
	public boolean edit(Paysub payr) {
		boolean stat=authDistributeDao.updateByPrimaryKey(payr);
		return stat;
	}
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	@Override
	public boolean del(String ids) {
		
		boolean stat=authDistributeDao.deleteByPrimaryKey(ids);
		return stat;
	}
	/**
	 * 根据Id查询出该权限信息
	 * 
	 * @param authid
	 * @return
	 */
	@Override
	public Paysub findinfoByid(String authid) {
		Paysub findinfo=authDistributeDao.findinfoByid(authid);
		return findinfo;
	}
	@Override
	public EasyuiDataGridJson disbasicauthTotal(EasyuiDataGrid dg) {
		int total=authDistributeDao.disbasicauthTotal();
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = authDistributeDao.disbasicauthList();
		List<Paysub> listbasic=new ArrayList<Paysub>();
		Paysub payr=new Paysub();
		if(list!=null&&!list.isEmpty()){
			payr.setCodeid("基本授权");
			payr.setSubname("所有岗位");
			if(list.size()==1){
			payr.setFinanceacc(list.get(0).getFinanceacc());
			}else{
				String auth=list.get(0).getFinanceacc();
				for(int i=1;i<list.size();i++){
				auth=auth+","+list.get(i).getFinanceacc();
				payr.setFinanceacc(auth);
				}	
			}
			listbasic.add(payr);
		}else{
			payr.setCodeid("基本授权");
			payr.setSubname("所有岗位");
			payr.setFinanceacc("");
			listbasic.add(payr);
		}
		PageHelper.endPage();
		long listss=(long)total;
		j.setTotal(listss);
		j.setRows(listbasic);
		
		return j;
	}
	@Override
	public boolean editbasic(String sql) {
		boolean stat=authDistributeDao.editbasic(sql);
		return stat;
	}
	
}
