package com.sumao.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sumao.model.ComTable;
import com.sumao.service.DetailService;

/**
 * 明细表导出的控制层 auhtor：liutong version：1.0
 */
@Controller
@RequestMapping("/ExportTable")
public class ExportController {

	@Resource
	private DetailService detailService;
	/**
	 * 供应商明细表表头
	 */
	private static String[] suppliertable = { "销售组织","供应商", "销量（吨）", "金额（元/吨）"  };
	/**
	 * 地区明细表表头
	 */
	private static String[] areatable = { "销售组织","省", "市", "供应商", "产品牌号", "产地", "销量（吨）", "金额（元）", "平均单价（元/吨）" };
	/**
	 * 销售明细表表头
	 */
	private static String[] saletable = { "销售组织","供应商", "分类", "应用", "牌号", "产地", "销量（吨）", "金额（元）", "平均单价（元/吨）" };
	/**
	 * 客户明细表表头
	 */
	private static String[] clienttable = { "销售组织","客户编码", "客户名称", "企业类型", "销量（吨）", "金额（元）", "订单成交数" };
	/**
	 * 订单明细表表头
	 */
	private static String[] oradertable = { "销售组织", "订单编号", "订单类型", "订单状态", "订单总金额(元)", "订单改价原因", "支付方式", "支付备注", "开票状态", "买方企业编号", "买方企业名称",
			"卖方企业编号", "卖方企业名称", "下单时间", "下单人", "业务员", "审批人", "审批意见", "订单中产品ID", "产品分类", "产品名称", "产品仓库", "仓库编码",
			"产品原单价(元/吨)", "产品新单价(元/吨)", "产品数量(吨)", "产品金额(元)", "运费(元/吨)" };

	public DetailService getDetailService() {
		return detailService;
	}

	@Autowired
	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}

	/**
	 * 导出功能, comtable是查询条件的参数集合
	 */
	@RequestMapping(params = "exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, ComTable comtable) {
		response.setContentType("octets/stream");
		String tableinfo = request.getParameter("tableinfo");
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String CurDatetime = df1.format(c.getTime());
		String DOCID = CurDatetime + String.valueOf(Math.random()).substring(2, 6);
		String excelName = DOCID.replace(":", "").replace(" ", "").replace("-", "") + tableinfo;
		/** 转码防止乱码 */
		try {
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(excelName.getBytes("gb2312"), "ISO8859-1") + ".xls");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		OutputStream out;
		try {
			out = response.getOutputStream();
			exportExcel(excelName, tablename(tableinfo), listGet(request,tableinfo, comtable), out,request);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出功能, comtable是查询条件的参数集合 tableinfo是报表名字的信息
	 */
	private List<ComTable> listGet(HttpServletRequest request,String tableinfo, ComTable comtable) {
		List<ComTable> list = new ArrayList<ComTable>();
		if (tableinfo.equals("生产厂商")) {
			String supname=request.getParameter("supname").toString().trim();
			String start=request.getParameter("start").toString().trim();
			String end=request.getParameter("end").toString().trim();
			String orgnid=request.getParameter("orgnid").toString().trim();
			comtable.setCol1(supname);
			comtable.setCol2(start);
			comtable.setCol3(end);
			comtable.setCol10(orgnid);
			list = this.detailService.exportExcel(comtable, tableinfo);
		} else if (tableinfo.equals("销售地区")) {
			String prov=request.getParameter("prov").toString().trim();
			String city=request.getParameter("city").toString().trim();
			String prod=request.getParameter("prod").toString().trim();
			String start=request.getParameter("start").toString().trim();
			String end=request.getParameter("end").toString().trim();
			String orgnid=request.getParameter("orgnid").toString().trim();
			comtable.setCol1(prov);
			comtable.setCol2(city);
			comtable.setCol3(prod);
			comtable.setCol4(start);
			comtable.setCol5(end);
			comtable.setCol10(orgnid);
			list = this.detailService.exportExcel(comtable, tableinfo);
		} else if (tableinfo.equals("销售汇总")) {
			String orgnid=request.getParameter("orgnid").toString().trim();
			String start=request.getParameter("start").toString().trim();
			String end=request.getParameter("end").toString().trim();
			String prod=request.getParameter("prod").toString().trim();
			String supplier=request.getParameter("supplier").toString().trim();
			String types=request.getParameter("types").toString().trim();
			String apply=request.getParameter("apply").toString().trim();
			comtable.setCol1(start);
			comtable.setCol2(end);
			comtable.setCol3(prod);
			comtable.setCol4(supplier);
			comtable.setCol5(types);
			comtable.setCol6(apply);
			comtable.setCol10(orgnid);
			list = this.detailService.exportExcel(comtable, tableinfo);
		} else if (tableinfo.equals("客户销售")) {
			String start=request.getParameter("start").toString().trim();
			String end=request.getParameter("end").toString().trim();
			String clintid=request.getParameter("clintid").toString().trim();
			String clintname=request.getParameter("clintname").toString().trim();
			String enptype=request.getParameter("enptype").toString().trim();
			String orgnid=request.getParameter("orgnid").toString().trim();
			comtable.setCol1(start);
			comtable.setCol2(end);
			comtable.setCol3(clintid);
			comtable.setCol4(clintname);
			comtable.setCol5(enptype);
			comtable.setCol10(orgnid);
			list = this.detailService.exportExcel(comtable, tableinfo);
		} else {
			String orgnid=request.getParameter("orgnid").toString().trim();
			String odtype = request.getParameter("odtype").toString().trim();
			String odstate = request.getParameter("odstate").toString().trim();	
			String odcheck = request.getParameter("odcheck").toString().trim();
			String enpname = request.getParameter("enpname").toString().trim();
			String orderid = request.getParameter("orderid").toString().trim();
			String prod = request.getParameter("prod").toString().trim();
			String start = request.getParameter("start").toString().trim();
			String end = request.getParameter("end").toString().trim();
			String warehouse = request.getParameter("warehouse").toString().trim();
			comtable.setCol1(odtype);
			comtable.setCol2(odstate);
			comtable.setCol3(odcheck);
			comtable.setCol4(enpname);
			comtable.setCol5(orderid);
			comtable.setCol6(prod);
			comtable.setCol7(start);
			comtable.setCol8(end);
			comtable.setCol10(orgnid);
			comtable.setCol11(warehouse);
			list = this.detailService.exportExcel(comtable, tableinfo);
		}
		return list;
	}

	/**
	 * 导出功能, tableinfo是报表名字的信息
	 */
	private String[] tablename(String tableinfo) {
		String[] headers = null;
		if (tableinfo.equals("生产厂商")) {
			headers = suppliertable;
		} else if (tableinfo.equals("销售地区")) {
			headers = areatable;
		} else if (tableinfo.equals("销售汇总")) {
			headers = saletable;
		} else if (tableinfo.equals("客户销售")) {
			headers = clienttable;
		} else {
			headers = oradertable;
		}
		return headers;
	}

	/**
	 * 导出功能, headers是表头信息 title是报表名字的信息 mapList是返回的查询集合信息 输出流到客户端页面
	 */
	protected void exportExcel(String title, String[] headers, List<ComTable> mapList, OutputStream out,HttpServletRequest request) {
		/** 声明一个工作簿 */
		HSSFWorkbook workbook = new HSSFWorkbook();
		String orgName=request.getParameter("orgName").toString().trim();
		/** 生成一个表格 */
		HSSFSheet sheet = workbook.createSheet(title);
		/** 设置表格默认列宽度为15个字符 */
		sheet.setDefaultColumnWidth(20);
		/** 生成一个样式，用来设置标题样式 */
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFCellStyle styledate = workbook.createCellStyle();
		styledate.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
		/** 设置这些样式 */
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		/** 生成一个字体 */
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		/** 把字体应用到当前的样式 */
		style.setFont(font);
		/** 生成并设置另一个样式,用于设置内容样式 */
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		/** 生成另一个字体 */
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		/** 把字体应用到当前的样式 */
		style2.setFont(font2);
		HSSFCell cell;
		/** 产生表格标题行 */
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(style);
		}
		ComTable table;
		for (int i = 0; i < mapList.size(); i++) {
			row = sheet.createRow(i + 1);
			table = (ComTable) mapList.get(i);
			Date oderedtime=new Date();
			/** 将特定的下单时间字段转换为日期格式 */
			if(headers.length>20){
				DateFormat checktimeformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					 oderedtime=checktimeformat.parse(table.getCol13());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				/** 创建单元格，并设置值 */
				row.createCell(0).setCellValue(orgName);
				row.createCell(1).setCellValue(table.getCol1());
				row.createCell(2).setCellValue(table.getCol2());
				row.createCell(3).setCellValue(table.getCol3());
				row.createCell(4).setCellValue(table.getCol4());
				row.createCell(5).setCellValue(table.getCol5());
				row.createCell(6).setCellValue(table.getCol6());
				row.createCell(7).setCellValue(table.getCol7());
				row.createCell(8).setCellValue(table.getCol8());
				row.createCell(9).setCellValue(table.getCol9());
				row.createCell(10).setCellValue(table.getCol10());
				row.createCell(11).setCellValue(table.getCol11());
				row.createCell(12).setCellValue(table.getCol12());
				row.createCell(13).setCellValue(oderedtime);
				row.getCell(13).setCellStyle(styledate);
				row.createCell(14).setCellValue(table.getCol14());
				row.createCell(15).setCellValue(table.getCol15());
				row.createCell(16).setCellValue(table.getCol16());
				row.createCell(17).setCellValue(table.getCol17());
				row.createCell(18).setCellValue(table.getCol18());
				row.createCell(19).setCellValue(table.getCol19());
				row.createCell(20).setCellValue(table.getCol20());
				row.createCell(21).setCellValue(table.getCol21());
				row.createCell(22).setCellValue(table.getCol22());
				row.createCell(23).setCellValue(table.getCol23());
				row.createCell(24).setCellValue(table.getCol24());
				row.createCell(25).setCellValue(table.getCol25());
				row.createCell(26).setCellValue(table.getCol26());
				row.createCell(27).setCellValue(table.getCol27());
			}else{
				/** 创建单元格，并设置值 */
				row.createCell(0).setCellValue(orgName);
				row.createCell(1).setCellValue(table.getCol1());
				row.createCell(2).setCellValue(table.getCol2());
				row.createCell(3).setCellValue(table.getCol3());
				row.createCell(4).setCellValue(table.getCol4());
				row.createCell(5).setCellValue(table.getCol5());
				row.createCell(6).setCellValue(table.getCol6());
				row.createCell(7).setCellValue(table.getCol7());
				row.createCell(8).setCellValue(table.getCol8());
				row.createCell(9).setCellValue(table.getCol9());
				row.createCell(10).setCellValue(table.getCol10());
				row.createCell(11).setCellValue(table.getCol11());
				row.createCell(12).setCellValue(table.getCol12());
				row.createCell(13).setCellValue(table.getCol13());
				row.createCell(14).setCellValue(table.getCol14());
				row.createCell(15).setCellValue(table.getCol15());
				row.createCell(16).setCellValue(table.getCol16());
				row.createCell(17).setCellValue(table.getCol17());
				row.createCell(18).setCellValue(table.getCol18());
				row.createCell(19).setCellValue(table.getCol19());
				row.createCell(20).setCellValue(table.getCol20());
				row.createCell(21).setCellValue(table.getCol21());
				row.createCell(22).setCellValue(table.getCol22());
				row.createCell(23).setCellValue(table.getCol23());
				row.createCell(24).setCellValue(table.getCol24());
				row.createCell(25).setCellValue(table.getCol25());
				row.createCell(26).setCellValue(table.getCol26());
				row.createCell(27).setCellValue(table.getCol27());
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}