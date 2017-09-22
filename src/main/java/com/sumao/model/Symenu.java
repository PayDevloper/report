package com.sumao.model;

import java.math.BigDecimal;
import java.util.Set;

public class Symenu implements java.io.Serializable {

	private static final long serialVersionUID = 4083399768984487181L;
	// 菜单ID
	private String id;

	// 菜单父级节点ID
	private String pid;

	// 节点名称
	private String text;

	// 节点图片
	private String iconcls;

	// 资源路径
	private String src;

	private BigDecimal seq;

	// 子菜单
	private Set<Symenu> symenus;

	// 菜单文件夹状态
	private String state;

	// 菜单编码
	private String code;

	// 菜单级别
	private BigDecimal mlevel;

	/** default constructor */
	public Symenu() {
	}

	/** minimal constructor */
	public Symenu(String id, String text, BigDecimal seq) {
		this.id = id;
		this.text = text;
		this.seq = seq;
	}

	/** full constructor */
	public Symenu(String id, String pid, String text, String iconcls, String src, BigDecimal seq, Set<Symenu> symenus) {
		this.id = id;
		this.pid = pid;
		this.text = text;
		this.iconcls = iconcls;
		this.src = src;
		this.seq = seq;
		this.symenus = symenus;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFid() {
		return pid;
	}

	public void setFid(String fid) {
		this.pid = fid;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconcls() {
		return this.iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public String getSrc() {
		return this.src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public Set<Symenu> getSymenus() {
		return this.symenus;
	}

	public void setSymenus(Set<Symenu> symenus) {
		this.symenus = symenus;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getMlevel() {
		return mlevel;
	}

	public void setMlevel(BigDecimal mlevel) {
		this.mlevel = mlevel;
	}

}