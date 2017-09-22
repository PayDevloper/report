package com.sumao.model;

public class Paydepositbalance implements java.io.Serializable {
	private static final long serialVersionUID = 2067550518674715683L;
	private String id;
	private String seller;
	private String marketing;
	private String marketingid;
	private String ordernumber;
	private String productbrand;
	private String exchangemodel;
	private String measuredimension;
	private String productcategory;
	private String productapplication;
	private String buyerid;
	private String deductingmoney;
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
	public Paydepositbalance() {
	}

	/** full constructor */
	public Paydepositbalance(String id,String seller, String marketing, String marketingid, String ordernumber, String productbrand, String exchangemodel, String measuredimension, String productcategory, String productapplication, String buyerid, String deductingmoney,String createperson, String createtime, String remark) {
		this.id = id;
		this.seller = seller;
		this.marketing = marketing;
		this.marketingid = marketingid;
		this.ordernumber = ordernumber;
		this.productbrand = productbrand;
		this.exchangemodel = exchangemodel;
		this.measuredimension = measuredimension;
		this.productcategory = productcategory;
		this.productapplication = productapplication;
		this.buyerid = buyerid;
		this.deductingmoney = deductingmoney;
		this.createperson = createperson;
		this.createtime = createtime;
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

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getProductbrand() {
		return productbrand;
	}

	public void setProductbrand(String productbrand) {
		this.productbrand = productbrand;
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

	public String getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}

	public String getDeductingmoney() {
		return deductingmoney;
	}

	public void setDeductingmoney(String deductingmoney) {
		this.deductingmoney = deductingmoney;
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