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
import com.sumao.service.NavigationService;
/**
 * 导航管理Service1.0
 * @author liutong
 * 
 */
@Service("NavigationService")
public class NavigationServiceImpl implements NavigationService {
	@Resource
	private AuthDistributeMapper authDistributeDao;

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
	public List<Paysub> findSubauthort(String[] userids) {
		List<Paysub> findsource=new ArrayList<Paysub>();
		List<Paysub> findurl=new ArrayList<Paysub>();
		String sql_resource="";
		sql_resource="select ID, ROLE_ID, ROLE_NAME,RESOURCES_ID,RESOUCES_NAME,CREATETIME,"
				+ "MODIFIEDTIME,operuser,remark,AUTHNAME from UAT_PROD0109.SYROLE_SYRESOURCES  where ";
		if(userids.length==1){
			sql_resource=sql_resource+"ROLE_ID LIKE"+" "+"'%"+userids[0]+"%'"; 
			}else{
				sql_resource=sql_resource+"ROLE_ID LIKE"+" "+"'%"+userids[0]+"%'"; 
				for(int i=1;i<userids.length;i++){
					sql_resource=sql_resource+" "+"or"+" "+"ROLE_ID LIKE"+" "+"'%"+userids[i]+"%'"; 
			}
			}
		findsource=this.authDistributeDao.findsourceid(sql_resource);	
		String sourceid="";
		String[] sourceids = null;   
		if(findsource!=null){
		if (findsource.size() == 1) {
			sourceid = findsource.get(0).getTotalid();
		} else if (findsource.size() > 1) {
		    sourceid = findsource.get(0).getTotalid();
			for (int i = 1; i < findsource.size(); i++) {
				sourceid = sourceid + "," + findsource.get(i).getTotalid();
			}}
		if(sourceid.contains(",")){
			sourceids = sourceid.split(",");
		}else{
			sourceids=new String[1];     
			sourceids[0]=sourceid;
		}
		/**查询出导航的url*/
		String sql_url="";
		sql_url="select ID as id, PID as subname, TEXT as financeacc,SRC as totalid,SYSCODE as totalname,MLEVEL as operdate,REMARK1 as remark1,ISBASIC as codeid from UAT_PROD0109.SYMENU where";
		if(sourceids.length==1){
			sql_url=sql_url+" "+"ID="+" "+"'"+sourceids[0]+"'"; 
			sql_url=sql_url+" "+"or"+" "+"isbasic="+"'"+"basic"+"'";
			}else if(sourceids.length>1){
				sql_url=sql_url+" "+"ID="+" "+"'"+sourceids[0]+"'"; 
				for(int i=1;i<sourceids.length;i++){
					sql_url=sql_url+" "+"or"+" "+"ID="+" "+"'"+sourceids[i]+"'"; 
			}
				sql_url=sql_url+" "+"or"+" "+"isbasic="+"'"+"basic"+"'";
			}else{
				sql_url=sql_url+" "+"isbasic="+"'"+"basic"+"'";
			}
		findurl=this.authDistributeDao.findurl(sql_url);	
		}else{
			System.out.println("没有赋权的导航");
		}
		return findurl;
	}
	/**
	 * 显示一级导航的数据表格+总数
	 * 
	 * @param dg
	 * @param payr
	 * @return
	 */
	@Override
	public int findnaviTotal(Paysub payr) {
		int total = authDistributeDao.findnaviTotal(payr);
		return total;
	}
	@Override
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Paysub payr, int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = authDistributeDao.findnaviList(payr);
		PageHelper.endPage();
		long listss = (long) total;
		j.setTotal(listss);
		j.setRows(list);

		return j;
	}
	/**
	 * 新建一级导航信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	@Override
	public boolean addnavi(Paysub payr) {
		boolean stat = authDistributeDao.insertnavi(payr);
		return stat;
	}
	/**
	 * 编辑一级导航信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	@Override
	public boolean editnavi(Paysub payr) {
		boolean stat = authDistributeDao.editnaviByKey(payr);
		return stat;
	}
	/**
	 * 删除一级导航信息
	 * 
	 * @param Paysub
	 *            payr
	 * @return
	 */
	@Override
	public boolean delnavi(String ids) {
		boolean stat = authDistributeDao.delnaviByKey(ids);
		return stat;
	}
	@Override
	public int findsubnaviTotal(Paysub payr) {
		int total = authDistributeDao.findsubnaviTotal(payr);
		return total;
	}
	@Override
	public EasyuiDataGridJson subnavidatagrid(EasyuiDataGrid dg, Paysub payr, int total) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Paysub> list = authDistributeDao.subnavidatagrid(payr);
		PageHelper.endPage();
		long listss = (long) total;
		j.setTotal(listss);
		j.setRows(list);

		return j;
	}
	@Override
	public boolean addsubnavi(Paysub payr) {
		boolean stat = authDistributeDao.insertsubnavi(payr);
		return stat;
	}
	@Override
	public boolean editsubnavi(Paysub payr) {
		boolean stat = authDistributeDao.editsubnaviByKey(payr);
		return stat;
	}
	@Override
	public boolean delsubnavi(String ids) {
		boolean stat = authDistributeDao.delsubnaviByKey(ids);
		return stat;
	}
	
}
