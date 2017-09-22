package com.sumao.model;

public class Paysub implements java.io.Serializable {
//totalname, totaltype, operuser,operdate,remark
	private static final long serialVersionUID = 2067550518674715683L;
	private String id;
	private String subname;
	private String totalname;
	private String totalid;
	private String financeacc;
	
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
	public Paysub() {
	}

	/** full constructor */
	public Paysub(String id, String subname,String totalname,String totalid, String financeacc,String operuser,String operdate,String remark) {
		//subname, totalname, totalid, financeacc, audit_status
		this.id = id;
		this.subname = subname;
		this.totalid = totalid;
		this.totalname = totalname;
		this.financeacc = financeacc;

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
	
	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public String getTotalid() {
		return totalid;
	}

	public void setTotalid(String totalid) {
		this.totalid = totalid;
	}

	public String getFinanceacc() {
		return financeacc;
	}

	public void setFinanceacc(String financeacc) {
		this.financeacc = financeacc;
	}

	public String getTotalname() {
		return totalname;
	}

	public void setTotalname(String totalname) {
		this.totalname = totalname;
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

	@Override
	public String toString() {
		return "Paysub [id=" + id + ", subname=" + subname + ", totalname=" + totalname + ", totalid=" + totalid
				+ ", financeacc=" + financeacc + ", operuser=" + operuser + ", operdate=" + operdate + ", remark="
				+ remark + ", codeid=" + codeid + ", remark1=" + remark1 + ", remark2=" + remark2 + ", remark3="
				+ remark3 + ", remark4=" + remark4 + ", remark5=" + remark5 + "]";
	}
	
	

	
}