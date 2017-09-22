package com.sumao.model;

import java.util.Date;
import java.util.Set;


public class Syuser implements java.io.Serializable {

	private static final long serialVersionUID = 2067550518674715683L;
	private String id;
	private String name;
	private int age;
	private String password;
	private String oldPassword;
	private Date createdatetime;
	private Date modifydatetime;
	private String ip;
	
	private Set<SyuserSyrole> syuserSyroles;

	// Constructors

	/** default constructor */
	public Syuser() {
	}

	/** minimal constructor */
	public Syuser(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public Syuser(String id, String name, String password, Date createdatetime, Date modifydatetime, Set<SyuserSyrole> syuserSyroles) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.createdatetime = createdatetime;
		this.modifydatetime = modifydatetime;
		this.syuserSyroles = syuserSyroles;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedatetime() {
		return this.createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public Date getModifydatetime() {
		return this.modifydatetime;
	}

	public void setModifydatetime(Date modifydatetime) {
		this.modifydatetime = modifydatetime;
	}

	public Set<SyuserSyrole> getSyuserSyroles() {
		return this.syuserSyroles;
	}

	public void setSyuserSyroles(Set<SyuserSyrole> syuserSyroles) {
		this.syuserSyroles = syuserSyroles;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	
}