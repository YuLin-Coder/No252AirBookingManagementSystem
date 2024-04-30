package com.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.model.*;
import com.service.*;
import com.util.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
//导入导出

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@Controller
public class ShujuAction {
	@Autowired
	private ShujuService shujuService;
	@Autowired
	private SjleixingService sjleixingService;
	@Autowired
	private SjxingtaiService sjxingtaiService;
	@Autowired
	private UserService userService;
	@Autowired
	private BuzhiService buzhiService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/getShujus")
	public void getShujus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String shujuName = (String) request.getParameter("shujuName");
		String sjleixingName = (String) request.getParameter("sjleixingName");
		String sjxingtaiName = (String) request.getParameter("sjxingtaiName");
		String shujuId = (String) request.getParameter("shujuId");
		String shujuType = (String) request.getParameter("shujuType");
		String shujuType1 = (String) request.getParameter("shujuType1");
		String sjleixingId = (String) request.getParameter("sjleixingId");
		String sjxingtaiId = (String) request.getParameter("sjxingtaiId");
		String shujuZong2 = (String) request.getParameter("shujuZong2");
		String yonghuId = (String) request.getParameter("yonghuId");
		String byumenId = (String) request.getParameter("byumenId");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String byuzhiId = (String) request.getParameter("byuzhiId");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		String sdate = (String) request.getParameter("sdate");
		String edate = (String) request.getParameter("edate");
		String sdate1 = (String) request.getParameter("sdate1");
		String edate1 = (String) request.getParameter("edate1");
		Shuju shuju = new Shuju();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(shujuName)) {
				shuju.setShujuName(shujuName);
			}
			if (StringUtil.isNotEmpty(sjleixingName)) {
				shuju.setSjleixingName(sjleixingName);
			}
			if (StringUtil.isNotEmpty(sjxingtaiName)) {
				shuju.setSjxingtaiName(sjxingtaiName);
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				shuju.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(shujuType)) {
				shuju.setShujuType(Integer.parseInt(shujuType));
			}
			if (StringUtil.isNotEmpty(shujuType1)) {
				shuju.setShujuType1(Integer.parseInt(shujuType1));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				shuju.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				shuju.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(shujuZong2)) {
				shuju.setShujuZong2(Integer.parseInt(shujuZong2));
			}
			if (StringUtil.isNotEmpty(userId)) {
				shuju.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				shuju.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				shuju.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				shuju.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				shuju.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				shuju.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				shuju.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				shuju.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			JSONArray jsonArray = JSONArray.fromObject(shujuService.queryShujus(
					shuju, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1));
			JSONObject result = new JSONObject();
			int total = shujuService.queryShujus(shuju, 0,0, sdate, edate, sdate1, edate1).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addShuju")
	public void addShuju(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String shujuName = (String) request.getParameter("shujuName");
			String shujuMark = (String) request.getParameter("shujuMark");
			String shujuMark1 = (String) request.getParameter("shujuMark1");
			String shujuMark2 = (String) request.getParameter("shujuMark2");
			String shujuMark3 = (String) request.getParameter("shujuMark3");
			String shujuDate = (String) request.getParameter("shujuDate");
			String shujuDate1 = (String) request.getParameter("shujuDate1");
			String shujuZong = (String) request.getParameter("shujuZong");
			String shujuZong1 = (String) request.getParameter("shujuZong1");
			String shujuZong2 = (String) request.getParameter("shujuZong2");
			String shujuType = (String) request.getParameter("shujuType");
			String shujuType1 = (String) request.getParameter("shujuType1");
			String shujuType2 = (String) request.getParameter("shujuType2");
			String sjleixingId = (String) request.getParameter("sjleixingId");
			String sjxingtaiId = (String) request.getParameter("sjxingtaiId");
			String shujuDouble = (String) request.getParameter("shujuDouble");
			String shujuDouble1 = (String) request.getParameter("shujuDouble1");
			String shujuDouble2 = (String) request.getParameter("shujuDouble2");
			String shujuDouble3 = (String) request.getParameter("shujuDouble3");
			String shujuDouble4 = (String) request.getParameter("shujuDouble4");
			String yonghuId = (String) request.getParameter("yonghuId");
			String userId = (String) request.getParameter("userId");
			String buzhiId = (String) request.getParameter("buzhiId");
			String shujuId = (String) request.getParameter("shujuId");
			Shuju shuju = new Shuju();

			if (StringUtil.isNotEmpty(shujuId)) {
				shuju = shujuService.getShuju(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(shujuName)) {
				shuju.setShujuName(shujuName);
			}
			if (StringUtil.isNotEmpty(shujuMark)) {
				shuju.setShujuMark(shujuMark);
			}
			if (StringUtil.isNotEmpty(shujuMark1)) {
				shuju.setShujuMark1(shujuMark1);
			}
			if (StringUtil.isNotEmpty(shujuMark2)) {
				shuju.setShujuMark2(shujuMark2);
			}
			if (StringUtil.isNotEmpty(shujuMark3)) {
				shuju.setShujuMark3(shujuMark3);
			}
			if (StringUtil.isNotEmpty(shujuDate)) {
				shuju.setShujuDate(DateUtil.formatString(shujuDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(shujuDate1)) {
				shuju.setShujuDate1(DateUtil.formatString(shujuDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(shujuZong)) {
				shuju.setShujuZong(Integer.parseInt(shujuZong));
			}
			if (StringUtil.isNotEmpty(shujuZong1)) {
				shuju.setShujuZong1(Integer.parseInt(shujuZong1));
			}
			if (StringUtil.isNotEmpty(shujuZong2)) {
				shuju.setShujuZong2(Integer.parseInt(shujuZong2));
			}
			if (StringUtil.isNotEmpty(shujuType)) {
				shuju.setShujuType(Integer.parseInt(shujuType));
			}
			if (StringUtil.isNotEmpty(shujuType1)) {
				shuju.setShujuType1(Integer.parseInt(shujuType1));
			}
			if (StringUtil.isNotEmpty(shujuType2)) {
				shuju.setShujuType2(Integer.parseInt(shujuType2));
			}
			if (StringUtil.isNotEmpty(shujuDouble)) {
				shuju.setShujuDouble(Double.parseDouble(shujuDouble));
			}
			if (StringUtil.isNotEmpty(shujuDouble1)) {
				shuju.setShujuDouble1(Double.parseDouble(shujuDouble1));
			}
			if (StringUtil.isNotEmpty(shujuDouble2)) {
				shuju.setShujuDouble2(Double.parseDouble(shujuDouble2));
			}
			if (StringUtil.isNotEmpty(shujuDouble3)) {
				shuju.setShujuDouble3(Double.parseDouble(shujuDouble3));
			}
			if (StringUtil.isNotEmpty(shujuDouble4)) {
				shuju.setShujuDouble4(Double.parseDouble(shujuDouble4));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				shuju.setSjleixingId(Integer.parseInt(sjleixingId));
				Sjleixing sjleixing = new Sjleixing();
				sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
				shuju.setSjleixingName(sjleixing.getSjleixingName());
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				shuju.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
				Sjleixing sjleixing = new Sjleixing();
				sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjxingtaiId));
				shuju.setSjxingtaiName(sjleixing.getSjleixingName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				shuju.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				shuju.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				shuju.setUserName(user.getUserName());
				shuju.setBumenId(user.getBumenId());
				shuju.setBumenName(user.getBumenName());
				shuju.setBuyuanId(user.getBuyuanId());
				shuju.setBuyuanName(user.getBuyuanName());
				shuju.setBuzhiId(user.getBuzhiId());
				shuju.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				shuju.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				shuju.setBuzhiName(buzhi.getBuzhiName());
				shuju.setBumenId(buzhi.getBumenId());
				shuju.setBumenName(buzhi.getBumenName());
				shuju.setBuyuanId(buzhi.getBuyuanId());
				shuju.setBuyuanName(buzhi.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				shujuService.modifyShuju(shuju);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Date date = new Date();
				shuju.setShujuDate(date);
				shuju.setShujuType(0);
				shujuService.save(shuju);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteShuju")
	public void deleteShuju(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				shujuService.deleteShuju(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/shujuComboList")
	public void shujuComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String shujuName = (String) request.getParameter("shujuName");
		String shujuId = (String) request.getParameter("shujuId");
		String shujuType = (String) request.getParameter("shujuType");
		String shujuType1 = (String) request.getParameter("shujuType1");
		String sjleixingId = (String) request.getParameter("sjleixingId");
		String sjxingtaiId = (String) request.getParameter("sjxingtaiId");
		String shujuZong2 = (String) request.getParameter("shujuZong2");
		String yonghuId = (String) request.getParameter("yonghuId");
		String byumenId = (String) request.getParameter("byumenId");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String byuzhiId = (String) request.getParameter("byuzhiId");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		Shuju shuju = new Shuju();
		try {
			if (StringUtil.isNotEmpty(shujuName)) {
				shuju.setShujuName(shujuName);
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				shuju.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(shujuType)) {
				shuju.setShujuType(Integer.parseInt(shujuType));
			}
			if (StringUtil.isNotEmpty(shujuType1)) {
				shuju.setShujuType1(Integer.parseInt(shujuType1));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				shuju.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				shuju.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(shujuZong2)) {
				shuju.setShujuZong2(Integer.parseInt(shujuZong2));
			}
			if (StringUtil.isNotEmpty(userId)) {
				shuju.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				shuju.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				shuju.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				shuju.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				shuju.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				shuju.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				shuju.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				shuju.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("shujuName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(shujuService.queryShujus(shuju,
					0,0, null, null, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/shujuTongji")
	public void shujuTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String userId=request.getParameter("userId");
		String tijiaoUrl = "shujuTongji";
		List<Integer> sjleixingIds = new ArrayList<Integer>();
		List<String> tongjiNames = new ArrayList<String>();
		List<Double> tongjiZongshus = new ArrayList<Double>();
		List<Sjleixing> sjleixings = new ArrayList<Sjleixing>();
		List<Shuju> shujus = new ArrayList<Shuju>();
		Double zongshu = 0.0;
		Shuju shuju = new Shuju();
		if (StringUtil.isNotEmpty(userId)) {
			shuju.setUserId(Integer.parseInt(userId));
		}
		try {
			sjleixings = sjleixingService.querySjleixings(null, 0, 0);
			for(int i=0;i<sjleixings.size();i++){
				sjleixingIds.add(sjleixings.get(i).getSjleixingId());
				tongjiNames.add(sjleixings.get(i).getSjleixingName());
			}
			for(int i=0;i<sjleixingIds.size();i++){
				Double shujuZongshu = 0.0;
				shuju.setSjleixingId(sjleixingIds.get(i));
				shujus = shujuService.queryShujus(shuju, 0, 0,sdate,edate, null, null);
				for(int j=0;j<shujus.size();j++){
					shujuZongshu = shujuZongshu + shujus.get(j).getShujuZong();
				}
				zongshu = zongshu + shujuZongshu;
				tongjiZongshus.add(shujuZongshu);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("tijiaoUrl", tijiaoUrl);
			session.setAttribute("tongjiNames", tongjiNames);
			session.setAttribute("tongjiZongshus", tongjiZongshus);
			session.setAttribute("zongshu", zongshu);
			String tongjitu = request.getParameter("tongjitu");
			response.sendRedirect("tongjitu/" + tongjitu + ".jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/shangchuanShuju")
	public void shangchuanShuju(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String shujuId = (String) request.getParameter("shujuId");
			String directory = "/file";
			String targetDirectory = request.getSession().getServletContext().getRealPath(directory);
	        String fileName = uploadFile.getOriginalFilename();  
			File dir = new File(targetDirectory,fileName);        
	        if(!dir.exists()){
	            dir.mkdirs();
	        }
	        //MultipartFile自带的解析方法
	        uploadFile.transferTo(dir);

			String shangchuandizhi = "/file" + "/" + fileName;
			String shangchuanname = fileName;
			Shuju shuju = shujuService.getShuju(Integer.parseInt(shujuId));
			shuju.setShujuImg(shangchuandizhi);
			shuju.setShujuImgName(shangchuanname);
			shujuService.modifyShuju(shuju);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruShuju")
	public void daoruShuju(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String directory = "/file";
			String targetDirectory = request.getSession().getServletContext().getRealPath(directory);
	        String fileName = uploadFile.getOriginalFilename();  
			File dir = new File(targetDirectory,fileName);        
	        if(!dir.exists()){
	            dir.mkdirs();
	        }
	        //MultipartFile自带的解析方法
	        uploadFile.transferTo(dir);
			excelFile = new FileInputStream(dir);
			List<List<String>> list = new ArrayList<List<String>>();
			list = ExcelUtil.jiexiExcel(excelFile);
			for (int i = 1; i < list.size(); i++) {
				List<String> row = list.get(i);
				String shujuName = row.get(0);
				String sjleixingId = row.get(1);
				String shujuZong = row.get(2);
				String shujuMark = row.get(3);
				String userId = row.get(4);
				Shuju shuju = new Shuju();
				
				if (StringUtil.isNotEmpty(shujuName)) {
					shuju.setShujuName(shujuName);
				}
				if (StringUtil.isNotEmpty(sjleixingId)) {
					shuju.setSjleixingId(Integer.parseInt(sjleixingId));
					Sjleixing sjleixing = new Sjleixing();
					sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
					shuju.setSjleixingName(sjleixing.getSjleixingName());
				}
				if (StringUtil.isNotEmpty(shujuZong)) {
					shuju.setShujuZong(Integer.parseInt(shujuZong));
				}
				if (StringUtil.isNotEmpty(shujuMark)) {
					shuju.setShujuMark(shujuMark);
				}
				if (StringUtil.isNotEmpty(userId)) {
					shuju.setUserId(Integer.parseInt(userId));
					User user = new User();
					user = userService.getUser(Integer.parseInt(userId));
					shuju.setUserName(user.getUserName());
					shuju.setBumenId(user.getBumenId());
					shuju.setBumenName(user.getBumenName());
					shuju.setBuyuanId(user.getBuyuanId());
					shuju.setBuyuanName(user.getBuyuanName());
					shuju.setBuzhiId(user.getBuzhiId());
					shuju.setBuzhiName(user.getBuzhiName());
				}
				Date date = new Date();
				shuju.setShujuDate(date);
				shujuService.save(shuju);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuShuju")
	public void daochuShuju(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			Date date = new Date();
			String strdate = DateUtil.formatDate(date, "yyyyMMddhhmmss");
			String excelName = strdate + ".xls";
			String mubanLujing = "";
			String daochuLujing = "" + excelName;
			String delIds = (String) request.getParameter("delIds");
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			List<List<String>> list = new ArrayList<List<String>>();
			Shuju shuju = new Shuju();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				shuju = shujuService.getShuju(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(shuju.getShujuName());
				row.add(shuju.getSjleixingName());
				row.add(shuju.getShujuZong().toString());
				row.add(shuju.getShujuMark());
				row.add(shuju.getUserName());
				list.add(row);
			}
			if(ExcelUtil.daochuExcle(list, mubanLujing, daochuLujing)){
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}else{
				result.put("success", "true");
				result.put("errorMsg", "导出Excel出错！");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
