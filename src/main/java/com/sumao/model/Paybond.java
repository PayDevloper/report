package com.sumao.model;

public class Paybond implements java.io.Serializable {
	private static final long serialVersionUID = 2067550518674715683L;
	private String id;
	private String seller;
	private String marketing;
	private String marketingid;
	private String subitemcode;
	private String subitemname;
	private String totalitemname;
	private String financeacc;
	private String minpayamount;
	private String havedeposit;
	private String whetheralerted;
	private String warningsum;
	private String approve;
	private String payment;
	private String createperson;
	private String createtime;
	

    private String auditperson;

    private String audittime;
 
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
	public Paybond() {
	}

	/** full constructor */
	public Paybond(String id,String seller, String marketing, String marketingid, String subitemcode, String subitemname, String totalitemname, String financeacc, String minpayamount, String havedeposit, String whetheralerted, String warningsum,String approve,String payment,String createperson, String createtime, String remark,String auditperson,String audittime,String codeid,String remark1,String remark2) {
		this.id = id;
		this.seller = seller;
		this.marketing = marketing;
		this.marketingid = marketingid;
		this.subitemcode = subitemcode;
		this.subitemname = subitemname;
		this.totalitemname = totalitemname;
		this.financeacc = financeacc;
		this.minpayamount = minpayamount;
		this.havedeposit = havedeposit;
		this.whetheralerted = whetheralerted;
		this.warningsum = warningsum;
		this.approve = approve;
		this.payment = payment;
		this.createperson = createperson;
		this.createtime = createtime;
		this.remark = remark;
		
		this.auditperson = auditperson;
		this.audittime = audittime;
		
		this.codeid = codeid;    //保证金设定历史表中“订单号”
		this.remark1 = remark1;  //保证金设定历史表中“发生金额”
		this.remark2 = remark2;  //保证金设定历史表中“发生类型”
		
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

	public String getMarketing() {
		return marketing;
	}

	public void setMarketing(String marketing) {
		this.marketing = marketing;
	}

	public String getMarketingid() {
		return marketingid;
	}

	public void setMarketingid(String marketingid) {
		this.marketingid = marketingid;
	}

	public String getSubitemcode() {
		return subitemcode;
	}

	public void setSubitemcode(String subitemcode) {
		this.subitemcode = subitemcode;
	}

	public String getSubitemname() {
		return subitemname;
	}

	public void setSubitemname(String subitemname) {
		this.subitemname = subitemname;
	}
	
	public String getTotalitemname() {
		return totalitemname;
	}

	public void setTotalitemname(String totalitemname) {
		this.totalitemname = totalitemname;
	}
	
	public String getFinanceacc() {
		return financeacc;
	}

	public void setFinanceacc(String financeacc) {
		this.financeacc = financeacc;
	}
	
	public String getMinpayamount() {
		return minpayamount;
	}

	public void setMinpayamount(String minpayamount) {
		this.minpayamount = minpayamount;
	}
	
	public String getHavedeposit() {
		return havedeposit;
	}

	public void setHavedeposit(String havedeposit) {
		this.havedeposit = havedeposit;
	}

	public String getWhetheralerted() {
		return whetheralerted;
	}

	public void setWhetheralerted(String whetheralerted) {
		this.whetheralerted = whetheralerted;
	}

	public String getWarningsum() {
		return warningsum;
	}

	public void setWarningsum(String warningsum) {
		this.warningsum = warningsum;
	}
	
	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}
	
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getCreateperson() {
		return createperson;
	}

	public void setCreateperson(String createperson) {
		this.createperson = createperson;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public String getAuditperson() {
		return auditperson;
	}

	public void setAuditperson(String auditperson) {
		this.auditperson = auditperson;
	}

	public String getAudittime() {
		return audittime;
	}

	public void setAudittime(String audittime) {
		this.audittime = audittime;
	}
}