package com.sumao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.SyuserMapper;
import com.sumao.interceptors.PageHelper;
import com.sumao.model.EasyuiDataGrid;
import com.sumao.model.EasyuiDataGridJson;
import com.sumao.model.Syuser;
import com.sumao.service.UserService;
import com.sumao.util.Encrypt;

/**
 * 用户Service
 * 
 * @author Chenxiaojun
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private SyuserMapper userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#reg(com.sumao.model.Syuser)
	 */
	@Override
	public Syuser reg(Syuser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Syuser login(Syuser user) {
		String password = Encrypt.encode(user.getPassword());
		user.setPassword(password);
		List<Syuser> userlist = userDao.findUsers(user);
		if (userlist.isEmpty()) {
			return null;
		} else if (userlist.size() > 1) {
			System.out.println("存在同名同密码的用户信息，请联系系统管理员");
			return null;
		} else {
			return userlist.get(0);
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#combobox(java.lang.String)
	 */
	@Override
	public List<Syuser> combobox(String name) {

		Syuser user = new Syuser();
		if (name != null) {
			user.setName(name);
		}

		List<Syuser> userlist = userDao.findUsers(user);
		return userlist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#add(com.sumao.model.Syuser)
	 */
	@Override
	public Syuser add(Syuser user) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#edit(com.sumao.model.Syuser)
	 */
	@Override
	public Syuser edit(Syuser user) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#del(java.lang.String)
	 */
	@Override
	public void del(String ids) {
		// TODO Auto-generated method stub

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
	 * @see com.sumao.service.UserService#getUserInfo(com.sumao.model.Syuser)
	 */
	@Override
	public Syuser getUserInfo(Syuser user) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.UserService#editUserInfo(com.sumao.model.Syuser)
	 */
	@Override
	public Syuser editUserInfo(Syuser user) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Resource
	// private BaseDao<Syuser> userDao;
	//// private BaseDao<SyuserSyrole> syuserSyroleDao;
	//// private BaseDao<Syrole> roleDao;
	//
	//
	//
	// public BaseDao<SyuserSyrole> getSyuserSyroleDao() {
	// return syuserSyroleDao;
	// }
	//
	// @Autowired
	// public void setSyuserSyroleDao(BaseDao<SyuserSyrole> syuserSyroleDao) {
	// this.syuserSyroleDao = syuserSyroleDao;
	// }
	//
	// public BaseDao<Syuser> getUserDao() {
	// return userDao;
	// }
	//
	// @Autowired
	// public void setUserDao(BaseDao<Syuser> userDao) {
	// this.userDao = userDao;
	// }
	//
	// @CacheEvict(value = "syproUserCache", allEntries = true)
	// public Syuser reg(Syuser user) {
	// user.setId(UUID.randomUUID().toString());
	// user.setPassword(Encrypt.encode(user.getPassword()));
	// user.setCreatedatetime(new Date());
	// user.setModifydatetime(new Date());
	// Syuser u = new Syuser();
	// BeanUtils.copyProperties(user, u);
	// userDao.save(u);
	// return user;
	// }
	//
	// @Cacheable(value = "syproUserCache", key =
	// "'login'+#user.name+#user.password")
	// @Transactional(readOnly = true)
	// public Syuser login(Syuser user) {
	// Object [] params= new Object[]{user.getName(),
	// Encrypt.encode(user.getPassword())};
	// Syuser u = userDao.get("from Syuser u where u.name=? and u.password=?",
	// params);
	// return u;
	// }
	//
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Syuser user) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		int pageNo = dg.getPage();
		int pageSize = dg.getRows();
		PageHelper.startPage(pageNo, pageSize);
		List<Syuser> list = userDao.findUsers(user);
		
		//用PageInfo对结果进行包装
		j.setRows(list);
//		PageInfo<User> page = new PageInfo<User>(list);
		
		if (user != null) {// 添加查询条件
			// if (user.getName() != null && !user.getName().trim().equals(""))
			// {
			// hql += " and t.name like '%%" + user.getName().trim() + "%%' ";
			// }
			// if (user.getCreatedatetimeStart() != null) {
			// hql += " and t.createdatetime>=? ";
			// values.add(user.getCreatedatetimeStart());
			// }
			// if (user.getCreatedatetimeEnd() != null) {
			// hql += " and t.createdatetime<=? ";
			// values.add(user.getCreatedatetimeEnd());
			// }
			// if (user.getModifydatetimeStart() != null) {
			// hql += " and t.modifydatetime>=? ";
			// values.add(user.getModifydatetimeStart());
			// }
			// if (user.getModifydatetimeEnd() != null) {
			// hql += " and t.modifydatetime<=? ";
			// values.add(user.getModifydatetimeEnd());
			// }
		}
		
		return j;
	}
	//
	// @Cacheable(value = "syproUserCache", key = "'combobox'+#q")
	// @Transactional(readOnly = true)
	// public List<Syuser> combobox(String q) {
	// List<Syuser> userSearchList=null;
	//// if (q == null || "".equals(q)) {
	//// String hql = " from Syuser";
	//// userSearchList = userDao.pageFind(hql, 1, 10);
	//// } else {
	//// String hql = " from Syuser t where t.name like '?%'";
	//// userSearchList = userDao.pageFind(hql, 1, 10, new Object[]{q});
	//// }
	////
	//// List<User> ul = new ArrayList<User>();
	//// if (userSearchList != null && userSearchList.size() > 0) {
	//// for (Syuser syuser : userSearchList) {
	//// User u = new User();
	//// BeanUtils.copyProperties(syuser, u);
	//// ul.add(u);
	//// }
	//// }
	//// return ul;
	// return null;
	// }
	//
	// @CacheEvict(value = "syproUserCache", allEntries = true)
	// public Syuser add(Syuser user) {
	// userDao.save(user);
	// return null;
	////
	//// if (user.getRoleId() != null && !user.getRoleId().equals("")) {
	//// for (String id : user.getRoleId().split(",")) {
	//// SyuserSyrole syuserSyrole = new SyuserSyrole();
	//// syuserSyrole.setId(UUID.randomUUID().toString());
	//// syuserSyrole.setSyrole(roleDao.get(Syrole.class, id));
	//// syuserSyrole.setSyuser(syuser);
	//// syuserSyroleDao.save(syuserSyrole);
	//// }
	//// }
	////
	//// return user;
	// }
	//
	// @CacheEvict(value = { "syproAuthCache", "syproUserCache" }, allEntries =
	// true)
	// public void editUsersRole(String userIds, String roleId) {
	// for (String userId : userIds.split(",")) {
	// Syuser syuser = userDao.get(Syuser.class, userId);
	// if (syuser != null) {
	// List<SyuserSyrole> syuserSyroleList = syuserSyroleDao.find("from
	// SyuserSyrole t where t.syuser=?", new Syuser []{syuser});
	// if (syuserSyroleList != null && syuserSyroleList.size() > 0) {
	// for (SyuserSyrole syuserSyrole : syuserSyroleList) {
	// syuserSyroleDao.delete(syuserSyrole);
	// }
	// }
	// if (roleId != null && !roleId.equals("")) {
	// for (String id : roleId.split(",")) {
	// Syrole syrole = roleDao.get(Syrole.class, id);
	// if (syrole != null) {
	// SyuserSyrole syuserSyrole = new SyuserSyrole();
	// syuserSyrole.setId(UUID.randomUUID().toString());
	// syuserSyrole.setSyrole(syrole);
	// syuserSyrole.setSyuser(syuser);
	// syuserSyroleDao.save(syuserSyrole);
	// }
	// }
	// }
	//
	// }
	// }
	// }
	//
	// @CacheEvict(value = { "syproAuthCache", "syproUserCache" }, allEntries =
	// true)
	// public Syuser edit(Syuser user) {
	// Syuser syuser = userDao.get(Syuser.class, user.getId());
	// if (syuser != null) {
	// if (user.getPassword() != null && !user.getPassword().trim().equals(""))
	// {
	// syuser.setPassword(Encrypt.encode(user.getPassword()));
	// }
	// if (user.getCreatedatetime() == null) {
	// syuser.setCreatedatetime(new Date());
	// }
	// if (user.getModifydatetime() == null) {
	// syuser.setModifydatetime(new Date());
	// }
	// BeanUtils.copyProperties(syuser, user);
	//
	//// List<SyuserSyrole> syuserSyroleList = syuserSyroleDao.find("from
	// SyuserSyrole t where t.syuser=?", new Syuser []{syuser});
	//// if (syuserSyroleList != null && syuserSyroleList.size() > 0) {
	//// for (SyuserSyrole syuserSyrole : syuserSyroleList) {
	//// syuserSyroleDao.delete(syuserSyrole);
	//// }
	//// }
	//// if (user.getRoleId() != null && !user.getRoleId().equals("")) {
	//// for (String id : user.getRoleId().split(",")) {
	//// Syrole syrole = roleDao.get(Syrole.class, id);
	//// if (syrole != null) {
	//// SyuserSyrole syuserSyrole = new SyuserSyrole();
	//// syuserSyrole.setId(UUID.randomUUID().toString());
	//// syuserSyrole.setSyrole(syrole);
	//// syuserSyrole.setSyuser(syuser);
	//// syuserSyroleDao.save(syuserSyrole);
	//// }
	//// }
	//// }
	// }
	// return user;
	// }
	//
	// @CacheEvict(value = "syproUserCache", allEntries = true)
	// public void del(String ids) {
	// for (String id : ids.split(",")) {
	// Syuser syuser = userDao.get(Syuser.class, id);
	// if (syuser != null) {
	// Set<SyuserSyrole> syuserSyroleSet = syuser.getSyuserSyroles();
	// if (syuserSyroleSet != null && syuserSyroleSet.size() > 0) {
	// List<SyuserSyrole> syuserSyroleList = syuserSyroleDao.find("from
	// SyuserSyrole t where t.syuser=?", new Syuser []{syuser});
	// if (syuserSyroleList != null && syuserSyroleList.size() > 0) {
	// for (SyuserSyrole syuserSyrole : syuserSyroleList) {
	// syuserSyroleDao.delete(syuserSyrole);
	// }
	// }
	// }
	// userDao.delete(syuser);
	// }
	// }
	// }
	//
	// @Cacheable(value = "syproUserCache", key = "'getUserInfo'+#user.id")
	// public Syuser getUserInfo(Syuser user) {
	// Syuser syuser = userDao.get(Syuser.class, user.getId());
	// BeanUtils.copyProperties(syuser, user);
	// String roleText = "";
	// String resourcesText = "";
	// Set<SyuserSyrole> syuserSyroleSet = syuser.getSyuserSyroles();
	// if (syuserSyroleSet != null && syuserSyroleSet.size() > 0) {
	// for (SyuserSyrole syuserSyrole : syuserSyroleSet) {
	// if (!roleText.equals("")) {
	// roleText += ",";
	// }
	// Syrole syrole = syuserSyrole.getSyrole();
	// roleText += syrole.getText();
	//
	// Set<SyroleSyresources> syroleSyresourcesSet =
	// syrole.getSyroleSyresourceses();
	// if (syroleSyresourcesSet != null && syroleSyresourcesSet.size() > 0) {
	// for (SyroleSyresources syroleSyresources : syroleSyresourcesSet) {
	// if (!resourcesText.equals("")) {
	// resourcesText += ",";
	// }
	// Syresources syresources = syroleSyresources.getSyresources();
	// resourcesText += syresources.getText();
	// }
	// }
	// }
	// }
	//// user.setRoleText(roleText);
	//// user.setResourcesText(resourcesText);
	// return user;
	// }
	//
	// @CacheEvict(value = "syproUserCache", allEntries = true)
	// public Syuser editUserInfo(Syuser user) {
	// if (user.getOldPassword() != null &&
	// !user.getOldPassword().trim().equals("") && user.getPassword() != null &&
	// !user.getPassword().trim().equals("")) {
	// Object [] params= new Object[]{user.getId(),
	// Encrypt.encode(user.getOldPassword())};
	// Syuser syuser = userDao.get("from Syuser t where t.id=? and
	// t.password=?", params);
	// if (syuser != null) {
	// syuser.setPassword(Encrypt.encode(user.getPassword()));
	// return user;
	// }
	// }
	// return null;
	// }
}
