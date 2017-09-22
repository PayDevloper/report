package com.sumao.model;

/**
 * JSON模型
 * 
 * @author 陈小俊
 * 
 */
public class Json implements java.io.Serializable {

	private static final long serialVersionUID = -3718317820880569603L;
	private boolean success = false;// 是否成功
	private String msg = "";// 提示信息
	private Object obj = null;// 其他信息

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
