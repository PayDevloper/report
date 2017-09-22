package com.sumao.model;

/**
 * session信息模型
 * 
 * @author 陈小俊
 * 
 */
public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = -5527056729949031889L;
	private Syuser user;

	public Syuser getUser() {
		return user;
	}

	public void setUser(Syuser user) {
		this.user = user;
	}

}
