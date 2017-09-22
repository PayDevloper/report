package com.sumao.service.impl;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sumao.dao.SymenuMapper;
import com.sumao.model.Node;
import com.sumao.model.Symenu;
import com.sumao.service.MenuService;
import com.sumao.util.MenuComparator;

/**
 * 菜单Service实现类
 * 
 * @author 陈小俊
 * 
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Resource
	private SymenuMapper menuDao;


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.MenuService#add(java.awt.Menu)
	 */
	@Override
	public Menu add(Menu menu) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.MenuService#del(java.awt.Menu)
	 */
	@Override
	public void del(Menu menu) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sumao.service.MenuService#edit(java.awt.Menu)
	 */
	@Override
	public Menu edit(Menu menu) {
		// TODO Auto-generated method stub
		return null;
	}


	
	public List<Node> tree(String id) {
		List<Symenu> menus = menuDao.findMemusById(id);

		List<Node> tree = new ArrayList<Node>();
		for (Symenu symenu : menus) {
			tree.add(tree(symenu, false));
		}
		return tree;
	}
	
	

	private Node tree(Symenu symenu, boolean recursive) {
		Node node = new Node();
		node.setId(symenu.getId());
		node.setText(symenu.getText());
		node.setIconCls(symenu.getIconcls());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("src", symenu.getSrc());
		node.setAttributes(attributes);
		if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Symenu> symenuList = new ArrayList<Symenu>(symenu.getSymenus());
				Collections.sort(symenuList, new MenuComparator());// 排序
				List<Node> children = new ArrayList<Node>();
				for (Symenu m : symenuList) {
					Node t = tree(m, true);
					children.add(t);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	/* (non-Javadoc)
	 * @see com.sumao.service.MenuService#treegrid(java.lang.String)
	 */
	@Override
	public List<Menu> treegrid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

//	public List<Menu> treegrid(String id) {
//		List<Menu> treegrid = new ArrayList<Menu>();
//		String hql = "from Symenu t where t.symenu is null order by t.seq";
//		if (id != null && !id.trim().equals("")) {
//			hql = "from Symenu t where t.symenu.id = '" + id + "' order by t.seq";
//		}
//		List<Symenu> symenus = menuDao.find(hql);
//		for (Symenu symenu : symenus) {
//			
//			if (symenu.getSymenu() != null) {
//				m.setParentId(symenu.getSymenu().getId());
//				m.setParentText(symenu.getSymenu().getText());
//			}
//			m.setIconCls(symenu.getIconcls());
//			if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
//				m.setState("closed");
//			}
//			treegrid.add(m);
//		}
//		return treegrid;
//	}
	//
	// @CacheEvict(value = "syproMenuCache", allEntries = true)
	// public Menu add(Menu menu) {
	// Symenu symenu = new Symenu();
	// BeanUtils.copyProperties(menu, symenu);
	// symenu.setIconcls(menu.getIconCls());
	// if (menu.getParentId() != null && !menu.getParentId().trim().equals(""))
	// {
	// symenu.setSymenu(menuDao.load(Symenu.class, menu.getParentId()));
	// }
	// menuDao.save(symenu);
	// return menu;
	// }
	//
	// @CacheEvict(value = "syproMenuCache", allEntries = true)
	// public void del(Menu menu) {
	// Symenu symenu = menuDao.get(Symenu.class, menu.getId());
	// if (symenu != null) {
	// recursiveDelete(symenu);
	// }
	// }
	//
	// private void recursiveDelete(Symenu symenu) {
	// if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
	// Set<Symenu> symenus = symenu.getSymenus();
	// for (Symenu t : symenus) {
	// recursiveDelete(t);
	// }
	// }
	// menuDao.delete(symenu);
	// }
	//
	// @CacheEvict(value = "syproMenuCache", allEntries = true)
	// public Menu edit(Menu menu) {
	// Symenu symenu = menuDao.get(Symenu.class, menu.getId());
	// if (symenu != null) {
	// BeanUtils.copyProperties(menu, symenu);
	// symenu.setIconcls(menu.getIconcls());
	// if (!symenu.getId().equals("0")) {// 根节点不可以修改上级节点
	// symenu.setSymenu(menuDao.get(Symenu.class, menu.getParentId()));
	// }
	// }
	// return menu;
	// }

}
