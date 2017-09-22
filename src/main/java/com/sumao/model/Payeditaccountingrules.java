package com.sumao.model;
/**
 * 计费规则管理
 * @author heijj
 *
 */
public class Payeditaccountingrules implements java.io.Serializable {
	private static final long serialVersionUID = 2067550518674715683L;

	/*
	 * chargingparty,tradingpatterns,dimensionparameter，chargingdimensionality,ladderprice，effectivetime，invalidtime，
startthreshold，startthresholdunit，endthreshold，endthresholdunit，stepmode,stepmodenum
	 */
	private String id;
	private String seller;
	private String marketing;
	private String marketingid;
	private String tradingpatterns;
	private String chargingdimensionality;
	private String chargingdimensionalitysf;
	
	private String dimensionparameter;
	private String dimensionparametersf;
	private String chargingparty;
	
	private String productcategory;
	private String productapplication;
	private String productbrand;
	private String effective;
	private String priority;

	private String ladderprice;
	private String effectivetime;
	private String invalidtime;
	
	private String startthreshold;
	private String startthresholdunit;
	private String endthreshold;
	private String endthresholdunit;
	private String stepmode;
	private String stepmodenum;
	
	private String chargingmode;
	private String startthresholdsf;
	private String startthresholdunitsf;
	private String endthresholdsf;
	private String endthresholdunitsf;
	private String stepmodesf;
	private String stepmodenumsf;
	

    private String auditperson;

    private String audittime;
 
	private String createperson;
	private String createtime;
	private String approve;
	private String remark;
	private String codeid;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String remark5;
	private String ordersource;
	
	private String startebscategory1;
	private String ebscategory1;
	private String startebscategory2;
	private String ebscategory2;
	private String startebscategory3;
	private String ebscategory3;
	private String startgradenumber;
	private String gradenumber;
	
	/** default constructor */
	public Payeditaccountingrules() {
	}

	/** full constructor */

	/*
	 * chargingparty,tradingpatterns,dimensionparameter，chargingdimensionality,ladderprice，effectivetime，invalidtime，
startthreshold,startthresholdunit,endthreshold,endthresholdunit,stepmode,stepmodenum
	 */
	public Payeditaccountingrules(String id,String seller, String marketing, String marketingid,String tradingpatterns,String chargingdimensionality,String dimensionparameter,String chargingparty,String productcategory,String productapplication,String productbrand,String effective,String priority,
			String ladderprice,String effectivetime,String invalidtime,String startthreshold,String startthresholdunit,String endthreshold,String endthresholdunit,String stepmode,String stepmodenum, String createperson, String createtime,String approve, String remark,String ordersource,String auditperson,String audittime,
			String chargingdimensionalitysf,String dimensionparametersf,String startthresholdsf,String startthresholdunitsf,String endthresholdsf,String endthresholdunitsf,String stepmodesf,String stepmodenumsf,String chargingmode,
			String startebscategory1,String ebscategory1,String startebscategory2,String ebscategory2,String startebscategory3,String ebscategory3,String startgradenumber,String gradenumber) {
		this.id = id;
		this.seller = seller;
		this.marketing = marketing;
		this.marketingid = marketingid;
		this.tradingpatterns = tradingpatterns;
		this.chargingdimensionality = chargingdimensionality;
		this.dimensionparameter = dimensionparameter;
		this.chargingparty = chargingparty;
		this.productcategory = productcategory;
		this.productapplication = productapplication;
		this.productbrand = productbrand;
		this.effective = effective;
		this.priority = priority;
		this.ladderprice = ladderprice;
		this.effectivetime = effectivetime;
		this.invalidtime = invalidtime;
		
		this.startthreshold = startthreshold;
		this.startthresholdunit = startthresholdunit;
		this.endthreshold = endthreshold;
		this.endthresholdunit = endthresholdunit;
		this.stepmode = stepmode;
		this.stepmodenum = stepmodenum;
		this.chargingmode = chargingmode;
		
		this.chargingdimensionalitysf = chargingdimensionalitysf;
		this.dimensionparametersf = dimensionparametersf;
		this.startthresholdsf = startthresholdsf;
		this.startthresholdunitsf = startthresholdunitsf;
		this.endthresholdsf = endthresholdsf;
		this.endthresholdunitsf = endthresholdunitsf;
		this.stepmodesf = stepmodesf;
		this.stepmodenumsf = stepmodenumsf;
		
		this.createperson = createperson;
		this.createtime = createtime;
		this.approve = approve;
		this.remark = remark;
		this.ordersource=ordersource;
		
		this.auditperson = auditperson;
		this.audittime = audittime;
		
		this.startebscategory1 = startebscategory1;
		this.ebscategory1 = ebscategory1;
		this.startebscategory2 = startebscategory2;
		this.ebscategory2 = ebscategory2;
		this.startebscategory3 = startebscategory3;
		this.ebscategory3 = ebscategory3;
		this.startgradenumber = startgradenumber;
		this.gradenumber = gradenumber;
		
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
	
	
	public String getChargingmode() {
		return chargingmode;
	}

	public void setChargingmode(String chargingmode) {
		this.chargingmode = chargingmode;
	}

	public String getTradingpatterns() {
		return tradingpatterns;
	}

	public void setTradingpatterns(String tradingpatterns) {
		this.tradingpatterns = tradingpatterns;
	}
	
	public String getChargingdimensionality() {
		return chargingdimensionality;
	}

	public void setChargingdimensionality(String chargingdimensionality) {
		this.chargingdimensionality = chargingdimensionality;
	}
	
	public String getDimensionparameter() {
		return dimensionparameter;
	}

	public void setDimensionparameter(String dimensionparameter) {
		this.dimensionparameter = dimensionparameter;
	}
	
	public String getChargingparty() {
		return chargingparty;
	}

	public void setChargingparty(String chargingparty) {
		this.chargingparty = chargingparty;
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
	
	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}
	
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String getEffectivetime() {
		return effectivetime;
	}

	public void setEffectivetime(String effectivetime) {
		this.effectivetime = effectivetime;
	}
	
	public String getInvalidtime() {
		return invalidtime;
	}

	public void setInvalidtime(String invalidtime) {
		this.invalidtime = invalidtime;
	}
	

	public String getLadderprice() {
		return ladderprice;
	}

	public void setLadderprice(String ladderprice) {
		this.ladderprice = ladderprice;
	}

	public String getStartthreshold() {
		return startthreshold;
	}

	public void setStartthreshold(String startthreshold) {
		this.startthreshold = startthreshold;
	}

	public String getStartthresholdunit() {
		return startthresholdunit;
	}

	public void setStartthresholdunit(String startthresholdunit) {
		this.startthresholdunit = startthresholdunit;
	}

	public String getEndthreshold() {
		return endthreshold;
	}

	public void setEndthreshold(String endthreshold) {
		this.endthreshold = endthreshold;
	}

	public String getEndthresholdunit() {
		return endthresholdunit;
	}

	public void setEndthresholdunit(String endthresholdunit) {
		this.endthresholdunit = endthresholdunit;
	}

	public String getStepmode() {
		return stepmode;
	}

	public void setStepmode(String stepmode) {
		this.stepmode = stepmode;
	}

	public String getStepmodenum() {
		return stepmodenum;
	}

	public void setStepmodenum(String stepmodenum) {
		this.stepmodenum = stepmodenum;
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

	public String getOrdersource() {
		return ordersource;
	}

	public void setOrdersource(String ordersource) {
		this.ordersource = ordersource;
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

	public String getChargingdimensionalitysf() {
		return chargingdimensionalitysf;
	}

	public void setChargingdimensionalitysf(String chargingdimensionalitysf) {
		this.chargingdimensionalitysf = chargingdimensionalitysf;
	}

	public String getDimensionparametersf() {
		return dimensionparametersf;
	}

	public void setDimensionparametersf(String dimensionparametersf) {
		this.dimensionparametersf = dimensionparametersf;
	}

	public String getStartthresholdsf() {
		return startthresholdsf;
	}

	public void setStartthresholdsf(String startthresholdsf) {
		this.startthresholdsf = startthresholdsf;
	}

	public String getStartthresholdunitsf() {
		return startthresholdunitsf;
	}

	public void setStartthresholdunitsf(String startthresholdunitsf) {
		this.startthresholdunitsf = startthresholdunitsf;
	}

	public String getEndthresholdsf() {
		return endthresholdsf;
	}

	public void setEndthresholdsf(String endthresholdsf) {
		this.endthresholdsf = endthresholdsf;
	}

	public String getEndthresholdunitsf() {
		return endthresholdunitsf;
	}

	public void setEndthresholdunitsf(String endthresholdunitsf) {
		this.endthresholdunitsf = endthresholdunitsf;
	}

	public String getStepmodesf() {
		return stepmodesf;
	}

	public void setStepmodesf(String stepmodesf) {
		this.stepmodesf = stepmodesf;
	}

	public String getStepmodenumsf() {
		return stepmodenumsf;
	}

	public void setStepmodenumsf(String stepmodenumsf) {
		this.stepmodenumsf = stepmodenumsf;
	}
	
	public String getStartebscategory1() {
		return startebscategory1;
	}

	public void setStartebscategory1(String startebscategory1) {
		this.startebscategory1 = startebscategory1;
	}
	public String getEbscategory1() {
		return ebscategory1;
	}

	public void setEbscategory1(String ebscategory1) {
		this.ebscategory1 = ebscategory1;
	}
	public String getStartebscategory2() {
		return startebscategory2;
	}

	public void setStartebscategory2(String startebscategory2) {
		this.startebscategory2 = startebscategory2;
	}
	public String getEbscategory2() {
		return ebscategory2;
	}

	public void setEbscategory2(String ebscategory2) {
		this.ebscategory2 = ebscategory2;
	}
	public String getStartebscategory3() {
		return startebscategory3;
	}

	public void setStartebscategory3(String startebscategory3) {
		this.startebscategory3 = startebscategory3;
	}
	public String getEbscategory3() {
		return ebscategory3;
	}

	public void setEbscategory3(String ebscategory3) {
		this.ebscategory3 = ebscategory3;
	}
	public String getStartgradenumber() {
		return startgradenumber;
	}

	public void setStartgradenumber(String startgradenumber) {
		this.startgradenumber = startgradenumber;
	}
	public String getGradenumber() {
		return gradenumber;
	}

	public void setGradenumber(String gradenumber) {
		this.gradenumber = gradenumber;
	}
	
	
}