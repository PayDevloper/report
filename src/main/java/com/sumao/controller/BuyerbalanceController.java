package com.sumao.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumao.service.PayeditaccountingrulesService;
import org.apache.log4j.Logger;
import com.sumao.model.Payeditaccountingrules;
import com.sumao.model.Paydepositbalance;
import com.sumao.service.PaydepositbalanceService;
/**
 * 买方结算接口
 * 
 * @author heijj
 * 
 */
@Controller
@RequestMapping("/buyerbalanceController")
public class BuyerbalanceController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(BuyerbalanceController.class);

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

	/*根据参数返回json格式数据*/
	@ResponseBody
	@RequestMapping(value="/buyersettle",produces="text/html;charset=UTF-8")
	public String buyersettle(HttpServletRequest request,HttpServletResponse response){
		String json="";
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	//可以方便地修改日期格式
		String buytime = dateFormat.format(now);
		String buyerordernum=request.getParameter("buyerordernum");

		if ((buyerordernum==null) || (buyerordernum.equals("null"))){				//无参数		
			json="{\"start\":true,\"result\":[{\"information\":\"不可没有参数\"}]}";
		}else{																		//有参数时
			List<Payeditaccountingrules> findrule=new ArrayList<Payeditaccountingrules>();
			findrule=payeditaccountingrulesService.findrule(buyerordernum,buytime);
			
			Paydepositbalance findinfo=new Paydepositbalance();	
			findinfo=paydepositbalanceService.findinfoma(buyerordernum);
			if(findinfo!=null){				//判断买方订单ID查询结果是否为null
				String deductingmoney="";										//定义扣除金额
				int allnum=Integer.parseInt(findinfo.getRemark1());				//产品总数量
				String buyerid=findinfo.getBuyerid();							//买方唯一编号
				
				if(findrule!=null && findrule.size()>0){	//判断是否存在符合条件的规则
					String marketingid=findrule.get(0).getMarketingid();			//销售组织ID
					String tradingpatterns=findrule.get(0).getTradingpatterns();	//交易模式
					String effectivetime=findrule.get(0).getEffectivetime();		//生效时间
					String invalidtime=findrule.get(0).getInvalidtime();			//失效时间
				
					if(findrule.get(0).getChargingmode().equals("线性收费")){
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
							int allweight=paydepositbalanceService.allweight(marketingid,tradingpatterns,buyerid,effectivetime,invalidtime);
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
							int allorder=paydepositbalanceService.allorder(marketingid,tradingpatterns,buyerid,effectivetime,invalidtime);
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
					findinfo.setRemark2(buytime);
					}else{
						deductingmoney="0";
						findinfo.setRemark("未找到与参数订单编号相匹配计费规则");
					}
					json="{\"start\":true,\"result\":[{\"information\":\""+deductingmoney+"\"}]}";
				
					String DOCID = buytime + String.valueOf(Math.random()).substring(2, 6);
					DOCID = DOCID.replace(":", "").replace(" ", "").replace("-", "");
					findinfo.setCreateperson("admin");
					findinfo.setCreatetime(buytime);
					findinfo.setId(DOCID);	
					findinfo.setOrdernumber(buyerordernum);
					findinfo.setDeductingmoney(deductingmoney);
					boolean stat=paydepositbalanceService.add(findinfo);
					if (stat==false)
					{
						logger.info("买方结算计费信息生成失败，订单号为："+buyerordernum+"，计费金额为："+deductingmoney);
					}
				
				}else{
					json="{\"start\":true,\"result\":[{\"information\":\"参数错误或不存在\"}]}";
			}
		}
			return json;
			
	}
}
