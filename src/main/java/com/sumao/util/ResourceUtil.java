package com.sumao.util;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 * @author 陈小俊
 * 
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("conf/config");

	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		String value= bundle.getString("sessionInfoName");
		return value;
	}

}
