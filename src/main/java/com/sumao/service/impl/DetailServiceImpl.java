package com.sumao.service.impl;

import com.sumao.dao.DetailMapper;
import com.sumao.model.ComTable;
import com.sumao.report.salesummary.pojo.SaleSummeryElement;
import com.sumao.report.salesummary.pojo.SaleSummeryRequest;
import com.sumao.service.DetailService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("DetailService")
public class DetailServiceImpl implements DetailService {

	@Resource
	private DetailMapper detailMapper;

	public int findOrderNum(ComTable comtable) {
		Map params = new HashMap();
		params.put("col1", selectedType(comtable.getCol1()));
		params.put("col2", selectedState(comtable.getCol2()));
		params.put("col3", selectedCheck(comtable.getCol3()));
		params.put("col4", comtable.getCol4());
		params.put("col5", comtable.getCol5());
		params.put("col6", comtable.getCol6());
		comtable.setCol9(ortherType(comtable.getCol1()));
		params.put("col9", comtable.getCol9());
		params.put("col10", comtable.getCol10());
		params.put("col11", comtable.getCol11());
		if ((comtable.getCol7() == null) || (comtable.getCol7().equals(""))) {
			String startdatetime = "2016-01-01 00:00:00";
			params.put("enddatetime", endtimeget());
			params.put("startdatetime", startdatetime);
		} else {
			params.put("startdatetime", comtable.getCol7() + " " + "00:00:00");
			params.put("enddatetime", comtable.getCol8() + " " + "23:59:59");
		}
		this.detailMapper.getOrderCount(params);
		int OrderNum = 0;
		if (params.containsKey("resultOrderNum"))
			OrderNum = ((Integer) params.get("resultOrderNum")).intValue();
		else {
			OrderNum = 0;
		}
		return OrderNum;
	}

	public List<ComTable> showOrderByPage(int startPos, int pageSize, ComTable comtable) {
		Map params = new HashMap();
		List list = new ArrayList();
		params.put("startPos", Integer.valueOf(startPos));
		params.put("pageSize", Integer.valueOf(pageSize + startPos));
		params.put("col1", selectedType(comtable.getCol1()));
		params.put("col2", selectedState(comtable.getCol2()));
		params.put("col3", selectedCheck(comtable.getCol3()));
		params.put("col4", comtable.getCol4());
		params.put("col5", comtable.getCol5());
		params.put("col6", comtable.getCol6());
		comtable.setCol9(ortherType(comtable.getCol1()));
		params.put("col9", comtable.getCol9());
		params.put("col10", comtable.getCol10());
		params.put("col11", comtable.getCol11());
		if ((comtable.getCol7() == null) || (comtable.getCol7().equals(""))) {
			String startdatetime = "2016-01-01 00:00:00";
			params.put("enddatetime", endtimeget());
			params.put("startdatetime", startdatetime);
		} else {
			params.put("startdatetime", comtable.getCol7() + " " + "00:00:00");
			params.put("enddatetime", comtable.getCol8() + " " + "23:59:59");
		}
		this.detailMapper.showOrderByPage(params);
		if (params.containsKey("resultPage"))
			list = (List) params.get("resultPage");
		else {
			list = new ArrayList();
		}
		return list;
	}

	public String selectedState(String selectedtype) {
		String entype = null;
		if ((selectedtype != null) && (!selectedtype.equals(""))) {
			if (selectedtype.equals("待支付"))
				entype = "SUBMITTED";
			else if (selectedtype.equals("已支付"))
				entype = "QUOTED";
			else if (selectedtype.equals("已取消"))
				entype = "REMOVED";
			else if (selectedtype.equals("待业务经理审批"))
				entype = "PENDING_APPROVAL";
			else if (selectedtype.equals("业务经理审批已通过"))
				entype = "APPROVED";
			else
				entype = "FAILED_APPROVAL";
		} else {
			entype = new String();
		}
		return entype;
	}

	public String selectedType(String selectedstate) {
		String enstate = null;
		if ((selectedstate != null) && (!selectedstate.equals(""))) {
			if (selectedstate.equals("挂牌"))
				enstate = "5001";
			else if (selectedstate.equals("代客下单"))
				enstate = "5002";
			else if (selectedstate.equals("公开竞拍"))
				enstate = "5003";
			else if (selectedstate.equals("密封竞拍"))
				enstate = "5004";
			else if (selectedstate.equals("预售"))
				enstate = "5007";
			else if (selectedstate.equals("团购"))
				enstate = "5008";
			else
				enstate = "5011";
		} else {
			enstate = new String();
		}
		return enstate;
	}

	public String ortherType(String selectedstate) {
		String enstate = null;
		if ((selectedstate != null) && (!selectedstate.equals(""))) {
			if (selectedstate.equals("代客下单"))
				enstate = "5009";
			else
				enstate = new String();
		} else {
			enstate = new String();
		}
		return enstate;
	}

	public String selectedCheck(String selectedcheck) {
		String encheck = null;
		if ((selectedcheck == null) || (selectedcheck.equals("")))
			encheck = new String();
		else if (selectedcheck.equals("已开票"))
			encheck = "1";
		else if (selectedcheck.equals("未开票"))
			encheck = "0";
		else {
			encheck = new String();
		}
		return encheck;
	}

	public String endtimeget() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		String enddatetime = df.format(c.getTime());
		return enddatetime;
	}

	public int findSupplierNum(ComTable comtable) {
		Map params = new HashMap();
		params.put("col1", comtable.getCol1());
		params.put("col10", comtable.getCol10());
		if ((comtable.getCol2() == null) || (comtable.getCol2().equals(""))) {
			String startdatetime = "1999-10-23 00:00:00";
			params.put("enddatetime", endtimeget());
			params.put("startdatetime", startdatetime);
		} else {
			params.put("startdatetime", comtable.getCol2() + " " + "00:00:00");
			params.put("enddatetime", comtable.getCol3() + " " + "23:59:59");
		}
		this.detailMapper.getSupplierCount(params);
		int OrderNum = 0;
		if (params.containsKey("resultSupplierNum"))
			OrderNum = ((Integer) params.get("resultSupplierNum")).intValue();
		else {
			OrderNum = 0;
		}
		return OrderNum;
	}

	public List<ComTable> showSupplierByPage(int startPos, int pageSize, ComTable comtable) {
		Map params = new HashMap();
		List list = new ArrayList();
		params.put("startPos", Integer.valueOf(startPos));
		params.put("pageSize", Integer.valueOf(pageSize + startPos));
		params.put("col1", comtable.getCol1());
		params.put("col10", comtable.getCol10());
		if ((comtable.getCol2() == null) || (comtable.getCol2().equals(""))) {
			String startdatetime = "1999-10-23 00:00:00";
			params.put("enddatetime", endtimeget());
			params.put("startdatetime", startdatetime);
		} else {
			params.put("startdatetime", comtable.getCol2() + " " + "00:00:00");
			params.put("enddatetime", comtable.getCol3() + " " + "23:59:59");
		}
		this.detailMapper.showSupplierByPage(params);
		if (params.containsKey("resultSupplier"))
			list = (List) params.get("resultSupplier");
		else {
			list = new ArrayList();
		}
		return list;
	}

	public int findAreaNum(ComTable comtable) {
		Map params = new HashMap();
		params.put("col1", comtable.getCol1());
		params.put("col2", comtable.getCol2());
		params.put("col3", comtable.getCol3());
		params.put("col10", comtable.getCol10());
		if ((comtable.getCol4() == null) || (comtable.getCol4().equals(""))) {
			String startdatetime = "1999-10-23 00:00:00";
			params.put("enddatetime", endtimeget());
			params.put("startdatetime", startdatetime);
		} else {
			params.put("startdatetime", comtable.getCol4() + " " + "00:00:00");
			params.put("enddatetime", comtable.getCol5() + " " + "23:59:59");
		}
		this.detailMapper.getAreaCount(params);
		int OrderNum = 0;
		if (params.containsKey("resultAreaNum"))
			OrderNum = ((Integer) params.get("resultAreaNum")).intValue();
		else {
			OrderNum = 0;
		}
		return OrderNum;
	}

	public List<ComTable> showAreaByPage(int startPos, int pageSize, ComTable comtable) {
		Map params = new HashMap();
		List list = new ArrayList();
		params.put("startPos", Integer.valueOf(startPos));
		params.put("pageSize", Integer.valueOf(pageSize + startPos));
		params.put("col1", comtable.getCol1());
		params.put("col2", comtable.getCol2());
		params.put("col3", comtable.getCol3());
		params.put("col10", comtable.getCol10());
		if ((comtable.getCol4() == null) || (comtable.getCol4().equals(""))) {
			String startdatetime = "1999-10-23 00:00:00";
			params.put("enddatetime", endtimeget());
			params.put("startdatetime", startdatetime);
		} else {
			params.put("startdatetime", comtable.getCol4() + " " + "00:00:00");
			params.put("enddatetime", comtable.getCol5() + " " + "23:59:59");
		}
		this.detailMapper.showAreaByPage(params);
		if (params.containsKey("resultArea"))
			list = (List) params.get("resultArea");
		else {
			list = new ArrayList();
		}
		return list;
	}

	public int findSaleNum(ComTable comtable) {
		Map params = new HashMap();
		params.put("col3", comtable.getCol3());
		params.put("col4", comtable.getCol4());
		params.put("col5", comtable.getCol5());
		params.put("col6", comtable.getCol6());
		params.put("col10", comtable.getCol10());
		if ((comtable.getCol1() == null) || (comtable.getCol1().equals(""))) {
			String startdatetime = "2000-11-23 00:00:00";
			params.put("enddatetime", endtimeget());
			params.put("startdatetime", startdatetime);
		} else {
			params.put("startdatetime", comtable.getCol1() + " " + "00:00:00");
			params.put("enddatetime", comtable.getCol2() + " " + "23:59:59");
		}
		this.detailMapper.getSaleCount(params);
		int OrderNum = 0;
		if (params.containsKey("resultSaleNum"))
			OrderNum = ((Integer) params.get("resultSaleNum")).intValue();
		else {
			OrderNum = 0;
		}
		return OrderNum;
	}

	public List<ComTable> showSaleByPage(int startPos, int pageSize, ComTable comtable) {
		Map params = new HashMap();
		List list = new ArrayList();
		params.put("startPos", Integer.valueOf(startPos));
		params.put("pageSize", Integer.valueOf(pageSize + startPos));
		params.put("col3", comtable.getCol3());
		params.put("col4", comtable.getCol4());
		params.put("col5", comtable.getCol5());
		params.put("col6", comtable.getCol6());
		params.put("col10", comtable.getCol10());
		if ((comtable.getCol1() == null) || (comtable.getCol1().equals(""))) {
			String startdatetime = "2000-11-23 00:00:00";
			params.put("enddatetime", endtimeget());
			params.put("startdatetime", startdatetime);
		} else {
			params.put("startdatetime", comtable.getCol1() + " " + "00:00:00");
			params.put("enddatetime", comtable.getCol2() + " " + "23:59:59");
		}
		this.detailMapper.showSaleByPage(params);
		if (params.containsKey("resultSale"))
			list = (List) params.get("resultSale");
		else {
			list = new ArrayList();
		}
		return list;
	}

	public int findClientNum(ComTable comtable) {
		Map params = new HashMap();
		params.put("col3", comtable.getCol3());
		params.put("col4", comtable.getCol4());
		params.put("col5", comtable.getCol5());
		params.put("col10", comtable.getCol10());
		if ((comtable.getCol1() == null) || (comtable.getCol1().equals(""))) {
			String startdatetime = "1999-10-23 00:00:00";
			params.put("enddatetime", endtimeget());
			params.put("startdatetime", startdatetime);
		} else {
			params.put("startdatetime", comtable.getCol1() + " " + "00:00:00");
			params.put("enddatetime", comtable.getCol2() + " " + "23:59:59");
		}
		this.detailMapper.getClientCount(params);
		int OrderNum = 0;
		if (params.containsKey("resultClientNum"))
			OrderNum = ((Integer) params.get("resultClientNum")).intValue();
		else {
			OrderNum = 0;
		}
		return OrderNum;
	}

	public List<ComTable> showClientByPage(int startPos, int pageSize, ComTable comtable) {
		Map params = new HashMap();
		List list = new ArrayList();
		params.put("startPos", Integer.valueOf(startPos));
		params.put("pageSize", Integer.valueOf(pageSize + startPos));
		params.put("col3", comtable.getCol3());
		params.put("col4", comtable.getCol4());
		params.put("col5", comtable.getCol5());
		params.put("col10", comtable.getCol10());
		if ((comtable.getCol1() == null) || (comtable.getCol1().equals(""))) {
			String startdatetime = "1999-10-23 00:00:00";
			params.put("enddatetime", endtimeget());
			params.put("startdatetime", startdatetime);
		} else {
			params.put("startdatetime", comtable.getCol1() + " " + "00:00:00");
			params.put("enddatetime", comtable.getCol2() + " " + "23:59:59");
		}
		this.detailMapper.showClientByPage(params);
		if (params.containsKey("resultClient"))
			list = (List) params.get("resultClient");
		else {
			list = new ArrayList();
		}
		return list;
	}

	public List<ComTable> exportExcel(ComTable comtable, String tableinfo) {
		List list = new ArrayList();
		if (tableinfo.equals("生产厂商")) {
			int num = findSupplierNum(comtable);
			list = showSupplierByPage(0, num, comtable);
		} else if (tableinfo.equals("销售地区")) {
			int num = findAreaNum(comtable);
			list = showAreaByPage(0, num, comtable);
		} else if (tableinfo.equals("销售汇总")) {
			int num = findSaleNum(comtable);
			list = showSaleByPage(0, num, comtable);
		} else if (tableinfo.equals("客户销售")) {
			int num = findClientNum(comtable);
			list = showClientByPage(0, num, comtable);
		} else {
			int num = findOrderNum(comtable);
			list = showOrderByPage(0, num, comtable);
		}
		return list;
	}

	public List<ComTable> selecttype(String typeIn) {
		List list = new ArrayList();
		list = this.detailMapper.selecttype(typeIn);
		return list;
	}

	// 显示销售概览的数据
	@Override
	public List<SaleSummeryElement> findSummaryList(SaleSummeryRequest cts) {
		if ((cts.getStartDate() != null) && !cts.getStartDate().equals("")) {
			cts.setStartDate(cts.getStartDate() + " 00:00:00");
			cts.setEndDate(cts.getEndDate() + " 23:59:59");
		} else {
			String startdatetime = "2010-01-01 00:00:00";
			cts.setStartDate(startdatetime);
			cts.setEndDate(endtimeget());
		}
		if ((cts.getOrderstate() != null) && !cts.getOrderstate().equals("")) {
		} else {
			cts.setOrderstate("PENDING_APPROVAL,INITIAL,SUBMITTED,REMOVED,CHANGED,APPROVED,FAILED_APPROVAL");
		}
		if ((cts.getOrdertype() != null) && !cts.getOrdertype().equals("")) {
		} else {
			cts.setOrdertype("5001,5003,5004,5007,5008,5009,5011");
		}
		if ((cts.getBusinessType() != null) && !cts.getBusinessType().equals("")) {
		} else {
			cts.setBusinessType("1,2,0");
		}
		List<SaleSummeryElement> salesummary = this.detailMapper.findSummaryList(cts);
		if (salesummary != null && salesummary.get(0) != null) {
		} else {
			salesummary.clear();
			SaleSummeryElement adddata = new SaleSummeryElement();
			adddata.setQuantity("0");
			adddata.setTotalprice("0");
			salesummary.add(adddata);
		}
		return salesummary;
	}
}