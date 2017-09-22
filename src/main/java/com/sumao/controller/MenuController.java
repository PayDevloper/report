package com.sumao.controller;

import java.awt.Menu;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumao.model.Node;
import com.sumao.model.Json;
import com.sumao.service.MenuService;

/**
 * 菜单控制器
 * 
 * @author 陈小俊
 * 
 */
@Controller
@RequestMapping("/menuController")
public class MenuController extends BaseController {

	private MenuService menuService;

	public MenuService getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * 跳转到菜单管理页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "menu")
	public String menu() {
		return "/admin/menu";
	}

	/**
	 * 获取菜单树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "tree")
	@ResponseBody
	public List<Node> tree(String id) {
		return menuService.tree(id);
	}

	/**
	 * 获取菜单treegrid
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "treegrid")
	@ResponseBody
	public List<Menu> treegrid(String id) {
		return menuService.treegrid(id);
	}

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(Menu menu) {
		Json j = new Json();
		menuService.add(menu);
		j.setSuccess(true);
		j.setMsg("添加成功!");
		return j;
	}

	/**
	 * 删除菜单
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(Menu menu) {
		Json j = new Json();
		menuService.del(menu);
		j.setSuccess(true);
		j.setMsg("删除成功!");
		return j;
	}

	/**
	 * 编辑菜单
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(Menu menu) {
		Json j = new Json();
		menuService.edit(menu);
		j.setSuccess(true);
		j.setMsg("编辑成功!");
		return j;
	}

}
