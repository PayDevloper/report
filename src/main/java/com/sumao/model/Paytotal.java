package com.sumao.model;

public class Paytotal implements java.io.Serializable {
//totalname, totaltype, operuser,operdate,remark
	private static final long serialVersionUID = 2067550518674715683L;
	private String id;
	private String totalname;
	private String totaltype;
	
	private String operuser;
	private String operdate;
	private String remark;
	private String codeid;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String remark5;
	
	/** default constructor */
	public Paytotal() {
	}

	/** full constructor */
	public Paytotal(String id,String totalname, String totaltype,String operuser,String operdate,String remark) {
		//totalname, totaltype, operuser,operdate,remark
		this.id = id;
		this.totalname = totalname;
		this.totaltype = totaltype;

		this.operuser = operuser;
		this.operdate = operdate;
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTotalname() {
		return totalname;
	}

	public void setTotalname(String totalname) {
		this.totalname = totalname;
	}

	public String getTotaltype() {
		return totaltype;
	}

	public void setTotaltype(String totaltype) {
		this.totaltype = totaltype;
	}

	public String getOperuser() {
		return operuser;
	}

	public void setOperuser(String operuser) {
		this.operuser = operuser;
	}

	public String getOperdate() {
		return operdate;
	}

	public void setOperdate(String operdate) {
		this.operdate = operdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark5() {
		return remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	public String getCodeid() {
		return codeid;
	}

	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
	
	

	
}