package com.sumao.model;

import java.math.BigDecimal;
import java.util.Set;

public class Syresources implements java.io.Serializable {

	private static final long serialVersionUID = 5489239268759025766L;
	private String id;
	private Syresources syresources;
	private String text;
	private BigDecimal seq;
	private String src;
	private String descript;
	private String onoff;
	private Set<SyroleSyresources> syroleSyresourceses;
	private Set<Syresources> syresourceses;

	// Constructors

	/** default constructor */
	public Syresources() {
	}

	/** minimal constructor */
	public Syresources(String id, BigDecimal seq) {
		this.id = id;
		this.seq = seq;
	}

	/** full constructor */
	public Syresources(String id, Syresources syresources, String text, BigDecimal seq, String src, String descript, String onoff, Set<SyroleSyresources> syroleSyresourceses, Set<Syresources> syresourceses) {
		this.id = id;
		this.syresources = syresources;
		this.text = text;
		this.seq = seq;
		this.src = src;
		this.descript = descript;
		this.onoff = onoff;
		this.syroleSyresourceses = syroleSyresourceses;
		this.syresourceses = syresourceses;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Syresources getSyresources() {
		return this.syresources;
	}

	public void setSyresources(Syresources syresources) {
		this.syresources = syresources;
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

	public String getSrc() {
		return this.src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getOnoff() {
		return this.onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}

	public Set<SyroleSyresources> getSyroleSyresourceses() {
		return syroleSyresourceses;
	}

	public void setSyroleSyresourceses(Set<SyroleSyresources> syroleSyresourceses) {
		this.syroleSyresourceses = syroleSyresourceses;
	}

	public Set<Syresources> getSyresourceses() {
		return syresourceses;
	}

	public void setSyresourceses(Set<Syresources> syresourceses) {
		this.syresourceses = syresourceses;
	}



}