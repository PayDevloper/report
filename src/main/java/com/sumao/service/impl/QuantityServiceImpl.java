package com.sumao.service.impl;

import java.rmi.server.SocketSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sumao.dao.QuantityMapper;
import com.sumao.model.ClientTop;
import com.sumao.model.CountNum;
import com.sumao.report.summary.pojo.DailySalesSummary;
import com.sumao.report.summary.pojo.SummaryRequest;
import com.sumao.service.QuantityService;

/**
 * Top10信息业务层
 * 
 * @author Liutong Version 1.0
 */
@Service("QuantityService")
public class QuantityServiceImpl implements QuantityService {

	@Resource
	private QuantityMapper quantityMapper;

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat df_today = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 获取今日产品信息 dateinfo传入的参数是“day”“week”“month”
	 */
	@Override
	public List<ClientTop> findProducts(String dateinfo, String dateend,String orgnid) {
		Map<String, Object> params = paramget(starttimeget(dateinfo), endtimeget(dateend));
		params.put("orgnid", orgnid);
		List<ClientTop> list = new ArrayList<ClientTop>();
		quantityMapper.findTops(params);
		if (params.containsKey("result")) {
			list = (List<ClientTop>) params.get("result");
		} else {
			list = new ArrayList<ClientTop>(0);
		}
		return list;
	}

	/**
	 * 获取今日客户信息 dateinfo传入的参数是“day”“week”“month”
	 */
	@Override
	public List<ClientTop> findClients(String dateinfo, String dateend,String orgnid) {
		Map<String, Object> params = paramget(starttimeget(dateinfo), endtimeget(dateend));
		params.put("orgnid", orgnid);
		List<ClientTop> list = new ArrayList<ClientTop>();
		quantityMapper.findClientTops(params);
		if (params.containsKey("resultclient")) {
			list = (List<ClientTop>) params.get("resultclient");
		} else {
			list = new ArrayList<ClientTop>(0);
		}
		return list;
	}

	/**
	 * 获取今日供应商信息 dateinfo传入的参数是“day”“week”“month”
	 */
	@Override
	public List<ClientTop> findSuplier(String dateinfo, String dateend,String orgnid) {
		Map<String, Object> params = paramget(starttimeget(dateinfo), endtimeget(dateend));
		params.put("orgnid", orgnid);
		List<ClientTop> list = new ArrayList<ClientTop>();
		quantityMapper.findSuplierTops(params);
		if (params.containsKey("resultSuplier")) {
			list = (List<ClientTop>) params.get("resultSuplier");
		} else {
			list = new ArrayList<ClientTop>(0);
		}
		return list;
	}

	/**
	 * 获取今日地区信息 dateinfo传入的参数是“day”“week”“month”
	 */
	@Override
	public List<ClientTop> findArea(String dateinfo, String dateend,String orgnid) {
		Map<String, Object> params = paramget(starttimeget(dateinfo), endtimeget(dateend));
		params.put("orgnid", orgnid);
		List<ClientTop> list = new ArrayList<ClientTop>();
		quantityMapper.findAreaTops(params);
		if (params.containsKey("resultArea")) {
			list = (List<ClientTop>) params.get("resultArea");
		} else {
			list = new ArrayList<ClientTop>(0);
		}
		return list;
	}

	/**
	 * 获取客户数量信息 dateinfo传入的参数是“day”“week”“month”
	 */
	@Override
	public CountNum CountClient(String dateinfo, String dateend,String orgnid) {
		Map<String, Object> params = paramget(starttimeget(dateinfo), endtimeget(dateend));
		params.put("orgnid", orgnid);
		CountNum clienttop = new CountNum();
		quantityMapper.CountClient(params);
		quantityMapper.findNewClient(params);
		if (params.containsKey("resultCountClient") && params.containsKey("resultCountNew")) {
			int CountClient = (Integer) params.get("resultCountClient");
			int CountNew = (Integer) params.get("resultCountNew");
			clienttop.setCol1(CountClient);
			clienttop.setCol2(CountNew);
		} else {
			clienttop = new CountNum();
		}
		return clienttop;
	}

	/**
	 * 获取汇总信息 dateinfo，dateend传入的参数是“day”“week”“month”
	 */
	@Override
	public CountNum CountAll(String dateinfo, String dateend,String orgnid) {
		Map<String, Object> params = paramget(starttimeget(dateinfo), endtimeget(dateend));
		params.put("orgnid", orgnid);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgnid", orgnid);
		param.put("enddatetime", beforeday(dateinfo));
		CountNum clienttop = new CountNum();
		List<ClientTop> listsecond = new ArrayList<ClientTop>();
		List<ClientTop> listthird = new ArrayList<ClientTop>();
		List<ClientTop> listdeliver = new ArrayList<ClientTop>();
		List<ClientTop> listfirst = new ArrayList<ClientTop>();
		quantityMapper.SummaryFirst(params);
		quantityMapper.SummarySecond(params);
		quantityMapper.SummaryThird(params);
		quantityMapper.SummaryDeliver(params);
		quantityMapper.SummaryClients(params);
		quantityMapper.SummaryNewClients(params);
		quantityMapper.SummaryNewClient(param);
		if (params.containsKey("resultFirst") && params.containsKey("resultSecond") && params.containsKey("resultThird")
				&& params.containsKey("resultDeliver") && params.containsKey("resultClients")
				&& params.containsKey("resultNewClients") && param.containsKey("resultNewClient")) {
			listfirst = (List<ClientTop>) params.get("resultFirst");
			listsecond = (List<ClientTop>) params.get("resultSecond");
			listthird =  (List<ClientTop>) params.get("resultThird");
			listdeliver =  (List<ClientTop>) params.get("resultDeliver");
			int TotalClients = (Integer) params.get("resultClients");
			int NewClients = (Integer) params.get("resultNewClients") - (Integer) param.get("resultNewClient");
			if(listthird.get(0)!=null&&!listthird.get(0).getSerialc().equals("")){
				clienttop.setCol11(listthird.get(0).getSerialc());
			}else{
				clienttop.setCol11("0");
			}
			if(listsecond.get(0)!=null&&!listsecond.get(0).getSerialc().equals("")){
				clienttop.setCol10(listsecond.get(0).getSerialc());
			}else{
				clienttop.setCol10("0");
			}
			if(listfirst.get(0)!=null&&!listfirst.get(0).getSerialc().equals("")){
				clienttop.setCol12(listfirst.get(0).getSerialc());
			}else{
				clienttop.setCol12("0");
			}		
			if(listdeliver.get(0)!=null&&!listdeliver.get(0).getSerialc().equals("")){
				clienttop.setCol13(listdeliver.get(0).getSerialc());
			}else{
				clienttop.setCol13("0");
			}	
			clienttop.setCol5(TotalClients);
			clienttop.setCol6(NewClients);
		} else {
			clienttop = new CountNum();
		}
		return clienttop;
	}

	/**
	 * 获取订单状态的信息 dateinfo，dateend传入的参数是“day”“week”“month”
	 */
	public CountNum CountOrder(String dateinfo, String dateend,String orgnid) {
		Map<String, Object> params = paramget(starttimeget(dateinfo), endtimeget(dateend));
		params.put("orgnid", orgnid);
		CountNum clienttop = new CountNum();
		List<ClientTop> countTotal = new ArrayList<ClientTop>();
		List<ClientTop> countPayed = new ArrayList<ClientTop>();
		List<ClientTop> countPaying = new ArrayList<ClientTop>();
		List<ClientTop> countremoved = new ArrayList<ClientTop>();
		quantityMapper.CountTotal(params);
		quantityMapper.CountPayed(params);
		quantityMapper.CountPaying(params);
		quantityMapper.CountRemoved(params);
		if (params.containsKey("resultPayed") && params.containsKey("resultPaying")
				&& params.containsKey("resultRemoved") && params.containsKey("resultTotal")) {
			countTotal = (List<ClientTop>) params.get("resultTotal");
			countPayed = (List<ClientTop>) params.get("resultPayed");
			countPaying =  (List<ClientTop>) params.get("resultPaying");
			countremoved =  (List<ClientTop>) params.get("resultRemoved");
			if(countTotal.get(0)!=null&&!countTotal.get(0).getSerialc().equals("")){
				clienttop.setCol10(countTotal.get(0).getSerialc());
			}else{
				clienttop.setCol10("0");
			}
			if(countPayed.get(0)!=null&&!countPayed.get(0).getSerialc().equals("")){
				clienttop.setCol11(countPayed.get(0).getSerialc());
			}else{
				clienttop.setCol11("0");
			}
			if(countPaying.get(0)!=null&&!countPaying.get(0).getSerialc().equals("")){
				clienttop.setCol12(countPaying.get(0).getSerialc());
			}else{
				clienttop.setCol12("0");
			}
			if(countremoved.get(0)!=null&&!countremoved.get(0).getSerialc().equals("")){
				clienttop.setCol13(countremoved.get(0).getSerialc());
			}else{
				clienttop.setCol13("0");
			}
		} else {
			clienttop = new CountNum();
		}
		return clienttop;
	}
	/**查询图表的日销量，金额 */
	@Override
	public List<DailySalesSummary> findChartData(String dateinfo, String dateend, String orgnid) {
		SummaryRequest params = new SummaryRequest();
		params.setStartDate(dateinfo);
		params.setEndDate(dateend);
		params.setGrade(orgnid);
		List<DailySalesSummary> chartdata= quantityMapper.findChartData(params);
		return chartdata;
	}
	/**
	 * 获取时间参数，startdatetime和enddatetime是开始时间和截至时间
	 */
	public Map<String, Object> paramget(String startdatetime, String enddatetime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enddatetime", enddatetime);
		params.put("startdatetime", startdatetime);
		return params;
	}

	/**
	 * 获取截止时间参数，dateend是截至时间的参数“day”“week”“month”
	 */
	public String endtimeget(String dateend) {
		String enddatetime;
		if (dateend.equals("day") || dateend.equals("week") || dateend.equals("month")) {
			Calendar c = Calendar.getInstance();
			enddatetime = df.format(c.getTime());
		} else {
			enddatetime = dateend;
		}
		return enddatetime;
	}

	/**
	 * 获取截止时间参数，dateend是截至时间的参数“day”“week”“month”
	 */
	public String beforeday(String dateend) {
		String enddatetime;
		if (dateend.equals("day")) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			enddatetime = df_today.format(c.getTime()) + " " + "00:00:00";
		} else if (dateend.equals("week")) {
			enddatetime = starttimeget(dateend);
		} else if (dateend.equals("month")) {
			enddatetime = starttimeget(dateend);
		} else {
			enddatetime = dateend;
		}
		return enddatetime;
	}

	/**
	 * 获取开始时间参数 startdate参数开始时间是“day”“week”“month”
	 */
	public String starttimeget(String startdate) {
		String startdatetime = new String();
		Calendar c = Calendar.getInstance();
		if (startdate.equals("day")) {
			String CurTodaytime = df_today.format(c.getTime());
			startdatetime = CurTodaytime + " " + "00:00:00";
		} else if (startdate.equals("week")) {
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			String montime = df_today.format(c.getTime());
			startdatetime = montime + " " + "00:00:00";
		} else if (startdate.equals("month")) {
			c.set(Calendar.DATE, 1);
			String montime = df_today.format(c.getTime());
			startdatetime = montime + " " + "00:00:00";
		} else {
			startdatetime = startdate;
		}
		return startdatetime;
	}
}