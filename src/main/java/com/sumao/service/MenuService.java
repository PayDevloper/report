package com.sumao.service;

import java.awt.Menu;
import java.util.List;

import com.sumao.model.Node;

/**
 * 菜单Service
 * 
 * @author 陈小俊
 * 
 */
public interface MenuService {

	public List<Node> tree(String id);

	public List<Menu> treegrid(String id);

	public Menu add(Menu menu);

	public void del(Menu menu);

	public Menu edit(Menu menu);

}
