package com.sumao.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.sumao.service.PaybondService;
import com.sumao.service.PaydepositbalanceService;
import com.sumao.service.PayeditaccountingrulesService;
import com.sumao.model.Paybond;
import com.sumao.model.Paydepositbalance;
import com.sumao.model.Payeditaccountingrules;

/**
 * 结算自动运行类，扫描订单表，生成保证金结算信息
 * @author heijj
 * 
 */
@Controller
@Component
public class BalanceController{

	private static final Logger logger = Logger.getLogger(BalanceController.class);

	private PayeditaccountingrulesService payeditaccountingrulesService;

	public PayeditaccountingrulesService getPayeditaccountingrulesService() {
		return payeditaccountingrulesService;
	}

	@Autowired
	public void setPayeditaccountingrulesService(PayeditaccountingrulesService payeditaccountingrulesService) {
		this.payeditaccountingrulesService = payeditaccountingrulesService;
	}
	
	private PaydepositbalanceService paydepositbalanceService;
	public PaydepositbalanceService getPaydepositbalanceService() {
		return paydepositbalanceService;
	}

	@Autowired
	public void setPaydepositbalanceService(PaydepositbalanceService paydepositbalanceService) {
		this.paydepositbalanceService = paydepositbalanceService;
	}
	
	private PaybondService paybondService;

	public PaybondService getPaybondService() {
		return paybondService;
	}

	@Autowired
	public void setPaybondService(PaybondService paybondService) {
		this.paybondService = paybondService;
	}
	
	/**
	 * 查询符合条件的订单表数据，根据计费规则生成结算信息，用于扣除卖方保证金。
	 * @author heijj
	 * 
	 */
	public void jobBond() {
		Paydepositbalance payob=null;
			
		 //下面代码即逻辑处理代码
		List<Paydepositbalance> plist=paydepositbalanceService.getOrderBalanceInfo(payob);  //得到符合结算条件的订单信息。
		if ((plist!=null)&&(plist.size()>0))
		{
			for (int k=0;k<plist.size();k++)
			{
				Paydepositbalance findinfo=(Paydepositbalance)plist.get(k);	
				String otradingpatterns=findinfo.getExchangemodel();  //交易模式
				if ((otradingpatterns==null)||(otradingpatterns.equals("null")))
				{
					otradingpatterns="现货交易";
				}
				String omarketingid=findinfo.getMarketingid();      //销售组织ID
				String buytime=findinfo.getRemark2();  //支付时间
				
				String buyerordernum=findinfo.getOrdernumber();  //订单唯一标识
				String ordernum=findinfo.getRemark1();  //订单销售总量（订单明细的产品数量和）
				
				//根据订单信息，得到配置的计费规则，如果是阶梯收费，则取出全部阶梯规则。
				List<Payeditaccountingrules> findrule=new ArrayList<Payeditaccountingrules>();
				findrule=payeditaccountingrulesService.findsellerrule(otradingpatterns,omarketingid,buytime,buyerordernum);
				
				//判断买方订单ID查询结果是否为null
				String deductingmoney="";							//定义扣除金额
				int allnum=Integer.parseInt(ordernum);				//产品总数量
				
				if(findrule!=null && findrule.size()>0){	//判断是否存在符合条件的规则
					String marketingid=findrule.get(0).getMarketingid();			//销售组织ID
					String tradingpatterns=findrule.get(0).getTradingpatterns();	//交易模式
					String effectivetime=findrule.get(0).getEffectivetime();		//生效时间
					String invalidtime=findrule.get(0).getInvalidtime();			//失效时间
					String ruleid=findrule.get(0).getId();                          //计费规则唯一标识
				
					String chargingmode=findrule.get(0).getChargingmode();  //计费模式
					
					if ((chargingmode.equals("线性收费"))||(chargingmode.equals("线性收费阶梯退费"))){
						if(findrule.get(0).getChargingdimensionality().equals("元/吨")){			
							int radix=Integer.parseInt(findrule.get(0).getDimensionparameter());
							deductingmoney=String.valueOf(allnum*radix);
						}else if(findrule.get(0).getChargingdimensionality().equals("元/单")){
							deductingmoney=findrule.get(0).getDimensionparameter();
						}else if(findrule.get(0).getChargingdimensionality().equals("元/场次")){
							deductingmoney="暂无";
						}
					}else if(findrule.get(0).getChargingmode().equals("阶梯收费")){
						if(findrule.get(0).getStepmode().equals("吨")){
							int allweight=paydepositbalanceService.sellerallweight(marketingid,tradingpatterns,effectivetime,invalidtime);
							for(int i=0;i<findrule.size();i++){
								if(!findrule.get(i).getEndthreshold().equals("#")){
									if(allweight>=Integer.parseInt(findrule.get(i).getStartthreshold())&&allweight<=Integer.parseInt(findrule.get(i).getEndthreshold())){
										deductingmoney=String.valueOf(allnum*Integer.parseInt(findrule.get(i).getStepmodenum()));
									} 
								}else if(findrule.get(i).getEndthreshold().equals("#")&&allweight>=Integer.parseInt(findrule.get(i).getStartthreshold())){
									deductingmoney=String.valueOf(allnum*Integer.parseInt(findrule.get(i).getStepmodenum()));
								}
							}
						}else if(findrule.get(0).getStepmode().equals("单")){
							int allorder=paydepositbalanceService.sellerallorder(marketingid,tradingpatterns,effectivetime,invalidtime);
							//System.out.println("allorder=="+allorder);
								for(int i=0;i<findrule.size();i++){
									
									if(!findrule.get(i).getEndthreshold().equals("#")){
										if(allorder>=Integer.parseInt(findrule.get(i).getStartthreshold())&&allorder<=Integer.parseInt(findrule.get(i).getEndthreshold())){
											deductingmoney=findrule.get(i).getStepmodenum();
										} 
									}else if(findrule.get(i).getEndthreshold().equals("#")&&allorder>=Integer.parseInt(findrule.get(i).getStartthreshold())){
										deductingmoney=findrule.get(i).getStepmodenum();
									}
								}
							}
						}
					findinfo.setSeller(findrule.get(0).getSeller());
					findinfo.setMarketing(findrule.get(0).getMarketing());
					findinfo.setRemark2(buytime);  //订单支付时间
					findinfo.setCodeid(ruleid);     //计费规则唯一标识
					
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
					String operdate = dateFormat.format(now);
					String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
					DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
					
					findinfo.setCreateperson("admin");
					findinfo.setCreatetime(operdate);
					findinfo.setId(DOCID);	
					findinfo.setOrdernumber(buyerordernum);
					findinfo.setDeductingmoney(deductingmoney);
					boolean stat=paydepositbalanceService.add(findinfo);
					//如果结算信息正常保存，则同时扣除保证金设定中“现有保证金”的值。
					if (stat==true)
					{
						Paybond payb=new Paybond();
						payb.setHavedeposit("-"+deductingmoney);
						payb.setMarketingid(marketingid);
						
						boolean bstat=paydepositbalanceService.UpdateBondByMID(payb);
						if (bstat==false)
						{
							logger.info("结算计费同时更新设定信息“现有保证金”内容失败，销售组织ID为："+marketingid+"，计费金额为："+deductingmoney);
						}
						else
						{
							//根据结算信息，形成保证金设定中的历史信息，用于保存卖家扣减保证金记录。 
							Paybond payrid=paybondService.findBondByMID(payb); //根据销售组织ID，得到保证金设定中的唯一编号。
							Paybond paybondh=paybondService.selectByPrimaryKey(payrid.getId());
							paybondh.setCodeid(buyerordernum);
							paybondh.setRemark1("-"+deductingmoney);  //发生金额
							paybondh.setRemark2("扣减服务费");       //发生类型
							paybondh.setCreatetime(operdate);    //发生日期
							paybondService.addHistory(paybondh); // 保证金设定编辑保存历史记录
						}
					}
				
					}else{
						logger.info("未找到与订单信息相匹配计费规则,订单号为："+buyerordernum+",销售组织ID为："+omarketingid);
					}
					
			}
		}

		
		}
	
	/**
	 * 5分钟自动运行扫描
	 * @author heijj
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public void run(){
		jobBond();
	}
	
	/**
	 * 每天自动运行扫描，退费规则情况"0 0 7 * * ?" 每天上午7点触发
	 * @author heijj
	 */
	@Scheduled(cron="0 0 6 * * ?")
	public void rundate(){
		jobDateBond();
	}
	
	/**
	 * 保证金设定中现有保证金小于警示金额，则改为预警状态，30分钟自动运行扫描
	 * @author heijj
	 */
	@Scheduled(cron="0 0/15 * * * ?")
	public void runWarnBond(){
		jobWarnBond();
	}

	/**
	 * 查询结算表数据，根据计费规则中退费规则，生成退费结算信息，用于返还卖方保证金。
	 * @author heijj
	 * 
	 */
	public void jobDateBond() {
		
		Date now1 = new Date();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");// 阶梯退费生效日期截止时间
		String opertime = dateFormat1.format(now1);
		//得到配置的退费计费规则。
		List<Payeditaccountingrules> findrule=new ArrayList<Payeditaccountingrules>();
		findrule=payeditaccountingrulesService.findRefundrule(opertime);	
		
				if(findrule!=null && findrule.size()>0){	//判断是否存在符合条件的规则
					for (int k=0;k<findrule.size();k++)
					{
					String marketingid=findrule.get(k).getMarketingid();			//销售组织ID
					String tradingpatterns=findrule.get(k).getTradingpatterns();	//交易模式
					String effectivetime=findrule.get(k).getEffectivetime();		//生效时间
					String invalidtime=findrule.get(k).getInvalidtime();			//失效时间
					String ruleid=findrule.get(k).getId();			//计费规则唯一标识
					String deductingmoney="0";
					String ordernum="0";
					
						if(findrule.get(k).getStepmode().equals("吨")){
							//生效日期之内结算信息中总吨数，用于判断退费区间及计算退费金额
							int allweight=paydepositbalanceService.sellerallweight(marketingid,tradingpatterns,effectivetime,invalidtime);
							
								if(!findrule.get(k).getEndthreshold().equals("#")){
									if(allweight>=Integer.parseInt(findrule.get(k).getStartthreshold())&&allweight<=Integer.parseInt(findrule.get(k).getEndthreshold())){
										deductingmoney=String.valueOf(allweight*Integer.parseInt(findrule.get(k).getStepmodenum()));
									}
								}else if(findrule.get(k).getEndthreshold().equals("#")&&allweight>=Integer.parseInt(findrule.get(k).getStartthreshold())){
									deductingmoney=String.valueOf(allweight*Integer.parseInt(findrule.get(k).getStepmodenum()));
								}
								ordernum=String.valueOf(allweight);
						}else if(findrule.get(k).getStepmode().equals("单")){
							int allorder=paydepositbalanceService.sellerallorder(marketingid,tradingpatterns,effectivetime,invalidtime);
									if(!findrule.get(k).getEndthreshold().equals("#")){
										if(allorder>=Integer.parseInt(findrule.get(k).getStartthreshold())&&allorder<=Integer.parseInt(findrule.get(k).getEndthreshold())){
											deductingmoney=String.valueOf(allorder*Integer.parseInt(findrule.get(k).getStepmodenum()));
										} 
									}else if(findrule.get(k).getEndthreshold().equals("#")&&allorder>=Integer.parseInt(findrule.get(k).getStartthreshold())){
										deductingmoney=String.valueOf(allorder*Integer.parseInt(findrule.get(k).getStepmodenum()));
									}
									ordernum=String.valueOf(allorder);
							}
						
					if (!deductingmoney.equals("0"))
					{
						Paydepositbalance findinfo=new Paydepositbalance();

						findinfo.setSeller(findrule.get(k).getSeller());
						findinfo.setMarketing(findrule.get(k).getMarketing());
						findinfo.setMarketingid(marketingid);
						findinfo.setExchangemodel(tradingpatterns);	//交易模式
						findinfo.setRemark2(invalidtime);  //订单支付时间
						findinfo.setRemark1(ordernum);  //退费数量
						findinfo.setCodeid(ruleid);     //计费规则唯一标识
						
						Date now = new Date();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
						String operdate = dateFormat.format(now);
						String DOCID = operdate + String.valueOf(Math.random()).substring(2, 6);
						DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
						
						findinfo.setCreateperson("admin");
						findinfo.setCreatetime(operdate);
						findinfo.setId(DOCID);	
						findinfo.setOrdernumber("退费");
						findinfo.setDeductingmoney(deductingmoney);
						boolean stat=paydepositbalanceService.add(findinfo);
						
						if (stat==true)
						{
							Paybond payb=new Paybond();
							payb.setHavedeposit(deductingmoney);
							payb.setMarketingid(marketingid);
							boolean bstat=paydepositbalanceService.UpdateBondByMID(payb);
							if (bstat==false)
							{
								logger.info("结算退费同时更新设定信息“现有保证金”内容失败，销售组织ID为："+marketingid+"，退费金额为："+deductingmoney);
							}
						}
					}
					
					}
				}
			
		
		}
	
	/**
	 * 保证金设定中现有保证金小于警示金额，则改为预警状态
	 * @author heijj
	 */
	public void jobWarnBond() {
		
		paydepositbalanceService.UpdateWarnBond();
		
	}

}
