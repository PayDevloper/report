package com.sumao.model;

public class Payrule implements java.io.Serializable {
//seller, sales_org, sales_orgID, member_status, margin_status, customer_status, audit_status,operuser,operdate,remark
	private static final long serialVersionUID = 2067550518674715683L;
	private String id;
	private String seller;
	private String sales_org;
	private String sales_orgID;
	private String member_status;
	private String margin_status;
	private String customer_status;
	private String audit_status;
	private String operuser;
	private String operdate;
	private String remark;
	private String codeid;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String remark5;
	
	//private Set<SyuserSyrole> syuserSyroles;

	// Constructors

	/** default constructor */
	public Payrule() {
	}

	/** full constructor */
	public Payrule(String id,String seller, String sales_org, String sales_orgID, String member_status, String margin_status, String customer_status, String audit_status,String operuser,String operdate,String remark) {
		//String seller, String sales_org, String sales_orgID, String member_status, String margin_status, String customer_status, String audit_status,String operuser,String operdate,String remark
		this.id = id;
		this.seller = seller;
		this.sales_org = sales_org;
		this.sales_orgID = sales_orgID;
		this.member_status = member_status;
		this.margin_status = margin_status;
		this.customer_status = customer_status;
		this.audit_status = audit_status;
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

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getSales_org() {
		return sales_org;
	}

	public void setSales_org(String sales_org) {
		this.sales_org = sales_org;
	}

	public String getSales_orgID() {
		return sales_orgID;
	}

	public void setSales_orgID(String sales_orgID) {
		this.sales_orgID = sales_orgID;
	}

	public String getMember_status() {
		return member_status;
	}

	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}

	public String getMargin_status() {
		return margin_status;
	}

	public void setMargin_status(String margin_status) {
		this.margin_status = margin_status;
	}

	public String getCustomer_status() {
		return customer_status;
	}

	public void setCustomer_status(String customer_status) {
		this.customer_status = customer_status;
	}

	public String getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
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