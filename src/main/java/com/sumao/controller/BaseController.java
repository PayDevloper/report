package com.sumao.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sumao.util.CustomDateEditor;

/**
 * 基础控制器，其他控制器需extends此控制器获得initBinder自动转换的功能
 * 
 * @author 陈小俊
 * 
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {

	public Logger logger;

	public BaseController() {
		logger = Logger.getLogger(this.getClass());
	}

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 * 
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		// binder.registerCustomEditor(String.class, new
		// StringTrimmerEditor(false));
	}

	public Logger getLogger() {
		return logger;
	}

	
}