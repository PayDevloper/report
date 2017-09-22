package com.sumao.model;

public class Paycostpay implements java.io.Serializable {
	private static final long serialVersionUID = 2067550518674715683L;
	private String id;
	private String seller;
	private String marketing;
	private String marketingid;
	private String paymentcode;
	private String subitemname;
	private String totalitemname;
	private String financeacc;
	private String shouldpay;
	private String actualpay;
	private String paymentmethod;
	private String payorderid;
	private String paytime;
	private String createperson;
	private String createtime;
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
	public Paycostpay() {
	}

	/** full constructor */
	public Paycostpay(String id,String seller, String marketing, String marketingid, String paymentcode, String subitemname, String totalitemname, String financeacc, String shouldpay, String actualpay, String paymentmethod, String payorderid,String paytime,String createperson, String createtime, String remark,String codeid) {
		this.id = id;
		this.seller = seller;
		this.marketing = marketing;
		this.marketingid = marketingid;
		this.paymentcode = paymentcode;
		this.subitemname = subitemname;
		this.totalitemname = totalitemname;
		this.financeacc = financeacc;
		this.shouldpay = shouldpay;
		this.actualpay = actualpay;
		this.paymentmethod = paymentmethod;
		this.payorderid = payorderid;
		this.paytime = paytime;
		this.createperson = createperson;
		this.createtime = createtime;
		this.remark = remark;
		this.codeid = codeid;
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

	public String getPaymentcode() {
		return paymentcode;
	}

	public void setPaymentcode(String paymentcode) {
		this.paymentcode = paymentcode;
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
	
	public String getShouldpay() {
		return shouldpay;
	}

	public void setShouldpay(String shouldpay) {
		this.shouldpay = shouldpay;
	}
	
	public String getActualpay() {
		return actualpay;
	}

	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getPayorderid() {
		return payorderid;
	}

	public void setPayorderid(String payorderid) {
		this.payorderid = payorderid;
	}
	
	public String getPaytime() {
		return paytime;
	}

	public void setPaytime(String paytime) {
		this.paytime = paytime;
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

	
}