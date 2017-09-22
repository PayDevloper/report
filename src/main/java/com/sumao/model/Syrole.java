package com.sumao.model;

import java.math.BigDecimal;
import java.util.Set;

public class Syrole implements java.io.Serializable {

	private static final long serialVersionUID = -6463615818043829001L;
	private String id;
	private Syrole syrole;
	private String text;
	private BigDecimal seq;
	private String descript;
	private Set<SyuserSyrole> syuserSyroles;
	private Set<Syrole> syroles;
	private Set<SyroleSyresources> syroleSyresourceses;

	// Constructors

	/** default constructor */
	public Syrole() {
	}

	/** minimal constructor */
	public Syrole(String id, BigDecimal seq) {
		this.id = id;
		this.seq = seq;
	}

	/** full constructor */
	public Syrole(String id, Syrole syrole, String text, BigDecimal seq, String descript, Set<SyuserSyrole> syuserSyroles, Set<Syrole> syroles, Set<SyroleSyresources> syroleSyresourceses) {
		this.id = id;
		this.syrole = syrole;
		this.text = text;
		this.seq = seq;
		this.descript = descript;
		this.syuserSyroles = syuserSyroles;
		this.syroles = syroles;
		this.syroleSyresourceses = syroleSyresourceses;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Syrole getSyrole() {
		return this.syrole;
	}

	public void setSyrole(Syrole syrole) {
		this.syrole = syrole;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Set<SyuserSyrole> getSyuserSyroles() {
		return syuserSyroles;
	}

	public void setSyuserSyroles(Set<SyuserSyrole> syuserSyroles) {
		this.syuserSyroles = syuserSyroles;
	}

	public Set<Syrole> getSyroles() {
		return syroles;
	}

	public void setSyroles(Set<Syrole> syroles) {
		this.syroles = syroles;
	}

	public Set<SyroleSyresources> getSyroleSyresourceses() {
		return syroleSyresourceses;
	}

	public void setSyroleSyresourceses(Set<SyroleSyresources> syroleSyresourceses) {
		this.syroleSyresourceses = syroleSyresourceses;
	}

	

}