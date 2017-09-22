package com.sumao.model;

/**
 * 计费规则创建
 * @author heijj
 *
 */
public class Paynewaccountingrules implements java.io.Serializable {
	private static final long serialVersionUID = 2067550518674715683L;
	private String id;
	private String seller;
	private String marketing;
	private String marketingid;
	private String exchangemodel;
	private String measuredimension;
	private String productcategory;
	private String productapplication;
	private String productbrand;
	private String collectionobject;
	private String ordersourceobject;
	private String createperson;
	private String createtime;


    private String auditperson;

    private String audittime;
    
	private String approve;
	private String billingconfiguration;
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
	public Paynewaccountingrules() {
	}

	/** full constructor */
	public Paynewaccountingrules(String id,String seller, String marketing, String marketingid, String exchangemodel, String measuredimension, String productcategory, String productapplication, String productbrand, String collectionobject,String ordersourceobject, String createperson, String createtime,String approve, String billingconfiguration, String remark,String auditperson,String audittime) {
		this.id = id;
		this.seller = seller;
		this.marketing = marketing;
		this.marketingid = marketingid;
		this.exchangemodel = exchangemodel;
		this.measuredimension = measuredimension;
		this.productcategory = productcategory;
		this.productapplication = productapplication;
		this.productbrand = productbrand;
		this.collectionobject = collectionobject;
		this.ordersourceobject=ordersourceobject;
		this.createperson = createperson;
		this.createtime = createtime;
		this.approve = approve;
		this.billingconfiguration = billingconfiguration;
		this.remark = remark;
		
		this.auditperson = auditperson;
		this.audittime = audittime;
		
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

	public String getExchangemodel() {
		return exchangemodel;
	}

	public void setExchangemodel(String exchangemodel) {
		this.exchangemodel = exchangemodel;
	}

	public String getMeasuredimension() {
		return measuredimension;
	}

	public void setMeasuredimension(String measuredimension) {
		this.measuredimension = measuredimension;
	}
	
	public String getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}
	
	public String getProductapplication() {
		return productapplication;
	}

	public void setProductapplication(String productapplication) {
		this.productapplication = productapplication;
	}
	
	public String getProductbrand() {
		return productbrand;
	}

	public void setProductbrand(String productbrand) {
		this.productbrand = productbrand;
	}
	
	public String getCollectionobject() {
		return collectionobject;
	}

	public void setCollectionobject(String collectionobject) {
		this.collectionobject = collectionobject;
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

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getBillingconfiguration() {
		return billingconfiguration;
	}

	public void setBillingconfiguration(String billingconfiguration) {
		this.billingconfiguration = billingconfiguration;
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

	public String getOrdersourceobject() {
		return ordersourceobject;
	}

	public void setOrdersourceobject(String ordersourceobject) {
		this.ordersourceobject = ordersourceobject;
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