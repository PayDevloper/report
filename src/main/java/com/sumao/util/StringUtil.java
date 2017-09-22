package com.sumao.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtil {

	/**
	 * 生成随机数:当前精确到秒的时间再加6位的数字随机序列
	 * 
	 * @return 流水号
	 */
	public static String getThirdLogNo() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String rdNum = df.format(new Date());
		Random random = new Random();
		int ird = random.nextInt(999999);
		String srd = String.format("%06d", ird);
		String thirdLogNo = rdNum + srd;

		return thirdLogNo;
	}

	/**
	 * 获取字符串的编码
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		if (isEmpty(str)) {
			return "";
		}

		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "未知的编码格式";
	}

	/**
	 * 判断字符串是否为NULL和空串。
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return !notEmpty(str);
	}

	/**
	 * 判断字符串是否不为NULL和空串。
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean notEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}

	/**
	 * 去掉字符串左右空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trimBlank(String str) {
		if (isEmpty(str) == true) {
			return str;
		} else {
			return str.replaceAll("^[　 ]+|[　 ]+$", "");
		}

	}
}
