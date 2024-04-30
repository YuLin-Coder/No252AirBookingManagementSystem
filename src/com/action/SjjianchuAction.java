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
public class SjjianchuAction {
	@Autowired
	private SjjianchuService sjjianchuService;
	@Autowired
	private SjqitaService sjqitaService;
	@Autowired
	private SjlaiyuanService sjlaiyuanService;
	@Autowired
	private SjleixingService sjleixingService;
	@Autowired
	private ShujuService shujuService;
	@Autowired
	private UserService userService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/getSjjianchus")
	public void getSjjianchus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String sjjianchuName = (String) request.getParameter("sjjianchuName");
		String sjjianchuId = (String) request.getParameter("sjjianchuId");
		String sjjianchuType = (String) request.getParameter("sjjianchuType");
		String sjjianchuType1 = (String) request.getParameter("sjjianchuType1");
		String sjjianchuType2 = (String) request.getParameter("sjjianchuType2");
		String sjlaiyuanId = (String) request.getParameter("sjlaiyuanId");
		String sjqitaId = (String) request.getParameter("sjqitaId");
		String shujuId = (String) request.getParameter("shujuId");
		String sjleixingId = (String) request.getParameter("sjleixingId");
		String sjxingtaiId = (String) request.getParameter("sjxingtaiId");
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
		Sjjianchu sjjianchu = new Sjjianchu();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(sjjianchuName)) {
				sjjianchu.setSjjianchuName(sjjianchuName);
			}
			if (StringUtil.isNotEmpty(sjjianchuId)) {
				sjjianchu.setSjjianchuId(Integer.parseInt(sjjianchuId));
			}
			if (StringUtil.isNotEmpty(sjjianchuType)) {
				sjjianchu.setSjjianchuType(Integer.parseInt(sjjianchuType));
			}
			if (StringUtil.isNotEmpty(sjjianchuType1)) {
				sjjianchu.setSjjianchuType1(Integer.parseInt(sjjianchuType1));
			}
			if (StringUtil.isNotEmpty(sjjianchuType2)) {
				sjjianchu.setSjjianchuType2(Integer.parseInt(sjjianchuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjjianchu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjjianchu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjjianchu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjjianchu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjjianchu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjjianchu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjjianchu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjjianchu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjjianchu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjjianchu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				sjjianchu.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				sjjianchu.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				sjjianchu.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			JSONArray jsonArray = JSONArray.fromObject(sjjianchuService.querySjjianchus(
					sjjianchu, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1));
			JSONObject result = new JSONObject();
			int total = sjjianchuService.querySjjianchus(sjjianchu, 0,0, sdate, edate, sdate1, edate1).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addSjjianchu")
	public void addSjjianchu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String sjjianchuName = (String) request.getParameter("sjjianchuName");
			String sjjianchuMark = (String) request.getParameter("sjjianchuMark");
			String sjjianchuMark1 = (String) request.getParameter("sjjianchuMark1");
			String sjjianchuMark2 = (String) request.getParameter("sjjianchuMark2");
			String sjjianchuMark3 = (String) request.getParameter("sjjianchuMark3");
			String sjjianchuDate = (String) request.getParameter("sjjianchuDate");
			String sjjianchuDate1 = (String) request.getParameter("sjjianchuDate1");
			String sjjianchuZong = (String) request.getParameter("sjjianchuZong");
			String sjjianchuZong1 = (String) request.getParameter("sjjianchuZong1");
			String sjjianchuZong2 = (String) request.getParameter("sjjianchuZong2");
			String sjjianchuDouble = (String) request.getParameter("sjjianchuDouble");
			String sjjianchuDouble1 = (String) request.getParameter("sjjianchuDouble1");
			String sjjianchuDouble2 = (String) request.getParameter("sjjianchuDouble2");
			String sjjianchuType = (String) request.getParameter("sjjianchuType");
			String sjjianchuType1 = (String) request.getParameter("sjjianchuType1");
			String sjjianchuType2 = (String) request.getParameter("sjjianchuType2");
			String shujuId = (String) request.getParameter("shujuId");
			String sjqitaId = (String) request.getParameter("sjqitaId");
			String sjlaiyuanId = (String) request.getParameter("sjlaiyuanId");
			String yonghuId = (String) request.getParameter("yonghuId");
			String userId = (String) request.getParameter("userId");
			String sjjianchuId = (String) request.getParameter("sjjianchuId");
			Sjjianchu sjjianchu = new Sjjianchu();

			if (StringUtil.isNotEmpty(sjjianchuId)) {
				sjjianchu = sjjianchuService.getSjjianchu(Integer.parseInt(sjjianchuId));
			}
			if (StringUtil.isNotEmpty(sjjianchuName)) {
				sjjianchu.setSjjianchuName(sjjianchuName);
			}
			if (StringUtil.isNotEmpty(sjjianchuMark)) {
				sjjianchu.setSjjianchuMark(sjjianchuMark);
			}
			if (StringUtil.isNotEmpty(sjjianchuMark1)) {
				sjjianchu.setSjjianchuMark1(sjjianchuMark1);
			}
			if (StringUtil.isNotEmpty(sjjianchuMark2)) {
				sjjianchu.setSjjianchuMark2(sjjianchuMark2);
			}
			if (StringUtil.isNotEmpty(sjjianchuMark3)) {
				sjjianchu.setSjjianchuMark3(sjjianchuMark3);
			}
			if (StringUtil.isNotEmpty(sjjianchuDate)) {
				sjjianchu.setSjjianchuDate(DateUtil.formatString(sjjianchuDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjjianchuDate1)) {
				sjjianchu.setSjjianchuDate1(DateUtil.formatString(sjjianchuDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjjianchuZong)) {
				sjjianchu.setSjjianchuZong(Integer.parseInt(sjjianchuZong));
			}
			if (StringUtil.isNotEmpty(sjjianchuZong1)) {
				sjjianchu.setSjjianchuZong1(Integer.parseInt(sjjianchuZong1));
			}
			if (StringUtil.isNotEmpty(sjjianchuZong2)) {
				sjjianchu.setSjjianchuZong2(Integer.parseInt(sjjianchuZong2));
			}
			if (StringUtil.isNotEmpty(sjjianchuDouble)) {
				sjjianchu.setSjjianchuDouble(Double.parseDouble(sjjianchuDouble));
			}
			if (StringUtil.isNotEmpty(sjjianchuDouble1)) {
				sjjianchu.setSjjianchuDouble1(Double.parseDouble(sjjianchuDouble1));
			}
			if (StringUtil.isNotEmpty(sjjianchuDouble2)) {
				sjjianchu.setSjjianchuDouble2(Double.parseDouble(sjjianchuDouble2));
			}
			if (StringUtil.isNotEmpty(sjjianchuType)) {
				sjjianchu.setSjjianchuType(Integer.parseInt(sjjianchuType));
			}
			if (StringUtil.isNotEmpty(sjjianchuType1)) {
				sjjianchu.setSjjianchuType1(Integer.parseInt(sjjianchuType1));
			}
			if (StringUtil.isNotEmpty(sjjianchuType2)) {
				sjjianchu.setSjjianchuType2(Integer.parseInt(sjjianchuType2));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjjianchu.setShujuId(Integer.parseInt(shujuId));
				Shuju shuju = new Shuju();
				shuju = shujuService.getShuju(Integer.parseInt(shujuId));
				sjjianchu.setShujuName(shuju.getShujuName());
				sjjianchu.setSjleixingId(shuju.getSjleixingId());
				sjjianchu.setSjleixingName(shuju.getSjleixingName());
				sjjianchu.setSjxingtaiId(shuju.getSjxingtaiId());
				sjjianchu.setSjxingtaiName(shuju.getSjxingtaiName());
				sjjianchu.setYonghuId(shuju.getYonghuId());
				sjjianchu.setYonghuName(shuju.getYonghuName());
				sjjianchu.setByumenId(shuju.getByumenId());
				sjjianchu.setByumenName(shuju.getByumenName());
				sjjianchu.setByuyuanId(shuju.getByuyuanId());
				sjjianchu.setByuyuanName(shuju.getByuyuanName());
				sjjianchu.setByuzhiId(shuju.getByuzhiId());
				sjjianchu.setByuzhiName(shuju.getByuzhiName());
				sjjianchu.setUserId(shuju.getUserId());
				sjjianchu.setUserName(shuju.getUserName());
				sjjianchu.setBumenId(shuju.getBumenId());
				sjjianchu.setBumenName(shuju.getBumenName());
				sjjianchu.setBuyuanId(shuju.getBuyuanId());
				sjjianchu.setBuyuanName(shuju.getBuyuanName());
				sjjianchu.setBuzhiId(shuju.getBuzhiId());
				sjjianchu.setBuzhiName(shuju.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjjianchu.setSjqitaId(Integer.parseInt(sjqitaId));
				Sjqita sjqita = new Sjqita();
				sjqita = sjqitaService.getSjqita(Integer.parseInt(sjqitaId));
				sjjianchu.setSjqitaName(sjqita.getSjqitaName());
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjjianchu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
				Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
				sjlaiyuan = sjlaiyuanService.getSjlaiyuan(Integer.parseInt(sjlaiyuanId));
				sjjianchu.setSjlaiyuanName(sjlaiyuan.getSjlaiyuanName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjjianchu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjjianchu.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				sjjianchu.setUserName(user.getUserName());
				sjjianchu.setBumenId(user.getBumenId());
				sjjianchu.setBumenName(user.getBumenName());
				sjjianchu.setBuyuanId(user.getBuyuanId());
				sjjianchu.setBuyuanName(user.getBuyuanName());
				sjjianchu.setBuzhiId(user.getBuzhiId());
				sjjianchu.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjjianchuId)) {
				sjjianchuService.modifySjjianchu(sjjianchu);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				User user = new User();
				user = userService.getUser(sjjianchu.getUserId());
				Date date = new Date();
				sjjianchu.setSjjianchuDate(date);
				sjjianchu.setSjjianchuType(0);
				sjjianchuService.save(sjjianchu);
				user.setUserDouble(user.getUserDouble()+sjjianchu.getSjjianchuDouble());
				userService.modifyUser(user);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteSjjianchu")
	public void deleteSjjianchu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjjianchuService.deleteSjjianchu(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjjianchuComboList")
	public void sjjianchuComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sjjianchuName = (String) request.getParameter("sjjianchuName");
		String sjjianchuId = (String) request.getParameter("sjjianchuId");
		String sjjianchuType = (String) request.getParameter("sjjianchuType");
		String sjjianchuType1 = (String) request.getParameter("sjjianchuType1");
		String sjjianchuType2 = (String) request.getParameter("sjjianchuType2");
		String sjlaiyuanId = (String) request.getParameter("sjlaiyuanId");
		String sjqitaId = (String) request.getParameter("sjqitaId");
		String shujuId = (String) request.getParameter("shujuId");
		String sjleixingId = (String) request.getParameter("sjleixingId");
		String sjxingtaiId = (String) request.getParameter("sjxingtaiId");
		String yonghuId = (String) request.getParameter("yonghuId");
		String byumenId = (String) request.getParameter("byumenId");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String byuzhiId = (String) request.getParameter("byuzhiId");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		Sjjianchu sjjianchu = new Sjjianchu();
		try {
			if (StringUtil.isNotEmpty(sjjianchuName)) {
				sjjianchu.setSjjianchuName(sjjianchuName);
			}
			if (StringUtil.isNotEmpty(sjjianchuId)) {
				sjjianchu.setSjjianchuId(Integer.parseInt(sjjianchuId));
			}
			if (StringUtil.isNotEmpty(sjjianchuType)) {
				sjjianchu.setSjjianchuType(Integer.parseInt(sjjianchuType));
			}
			if (StringUtil.isNotEmpty(sjjianchuType1)) {
				sjjianchu.setSjjianchuType1(Integer.parseInt(sjjianchuType1));
			}
			if (StringUtil.isNotEmpty(sjjianchuType2)) {
				sjjianchu.setSjjianchuType2(Integer.parseInt(sjjianchuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjjianchu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjjianchu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjjianchu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjjianchu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjjianchu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjjianchu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjjianchu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjjianchu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjjianchu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjjianchu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				sjjianchu.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				sjjianchu.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				sjjianchu.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("sjjianchuName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(sjjianchuService.querySjjianchus(sjjianchu,
					0,0, null, null, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjjianchuTongji")
	public void sjjianchuTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String userId=request.getParameter("userId");
		String tijiaoUrl = request.getParameter("tijiaoUrl");
		List<Integer> shujuIds = new ArrayList<Integer>();
		List<String> tongjiNames = new ArrayList<String>();
		List<Double> tongjiZongshus = new ArrayList<Double>();
		List<Shuju> shujus = new ArrayList<Shuju>();
		List<Sjjianchu> sjjianchus = new ArrayList<Sjjianchu>();
		Double zongshu = 0.0;
		Sjjianchu sjjianchu = new Sjjianchu();
		Shuju shuju = new Shuju();
		if (StringUtil.isNotEmpty(userId)) {
			shuju.setUserId(Integer.parseInt(userId));
		}
		try {
			shujus = shujuService.queryShujus(shuju, 0,0, null, null, null, null);
			for(int i=0;i<shujus.size();i++){
				shujuIds.add(shujus.get(i).getShujuId());
				tongjiNames.add(shujus.get(i).getShujuName());
			}
			for(int i=0;i<shujuIds.size();i++){
				Double sjjianchuZongshu = 0.0;
				sjjianchu.setShujuId(shujuIds.get(i));
				sjjianchus = sjjianchuService.querySjjianchus(sjjianchu, 0,0,sdate,edate, null, null);
				for(int j=0;j<sjjianchus.size();j++){
					sjjianchuZongshu = sjjianchuZongshu + sjjianchus.get(j).getSjjianchuDouble2();
				}
				zongshu = zongshu + sjjianchuZongshu;
				tongjiZongshus.add(sjjianchuZongshu);
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

	@RequestMapping("/shangchuanSjjianchu")
	public void shangchuanSjjianchu(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String sjjianchuId = (String) request.getParameter("sjjianchuId");
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
			Sjjianchu sjjianchu = sjjianchuService.getSjjianchu(Integer.parseInt(sjjianchuId));
			sjjianchu.setSjjianchuImg(shangchuandizhi);
			sjjianchu.setSjjianchuImgName(shangchuanname);
			sjjianchuService.modifySjjianchu(sjjianchu);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruSjjianchu")
	public void daoruSjjianchu(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
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
				String sjjianchuName = row.get(0);
				String sjleixingId = row.get(1);
				String sjjianchuZong = row.get(2);
				String sjjianchuMark = row.get(3);
				String userId = row.get(4);
				Sjjianchu sjjianchu = new Sjjianchu();
				
				if (StringUtil.isNotEmpty(sjjianchuName)) {
					sjjianchu.setSjjianchuName(sjjianchuName);
				}
				if (StringUtil.isNotEmpty(sjleixingId)) {
					sjjianchu.setSjleixingId(Integer.parseInt(sjleixingId));
					Sjleixing sjleixing = new Sjleixing();
					sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
					sjjianchu.setSjleixingName(sjleixing.getSjleixingName());
				}
				if (StringUtil.isNotEmpty(sjjianchuZong)) {
					sjjianchu.setSjjianchuZong(Integer.parseInt(sjjianchuZong));
				}
				if (StringUtil.isNotEmpty(sjjianchuMark)) {
					sjjianchu.setSjjianchuMark(sjjianchuMark);
				}
				if (StringUtil.isNotEmpty(userId)) {
					sjjianchu.setUserId(Integer.parseInt(userId));
					User user = new User();
					user = userService.getUser(Integer.parseInt(userId));
					sjjianchu.setUserName(user.getUserName());
					sjjianchu.setBumenId(user.getBumenId());
					sjjianchu.setBumenName(user.getBumenName());
					sjjianchu.setBuyuanId(user.getBuyuanId());
					sjjianchu.setBuyuanName(user.getBuyuanName());
					sjjianchu.setBuzhiId(user.getBuzhiId());
					sjjianchu.setBuzhiName(user.getBuzhiName());
				}
				Date date = new Date();
				sjjianchu.setSjjianchuDate(date);
				sjjianchuService.save(sjjianchu);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuSjjianchu")
	public void daochuSjjianchu(HttpServletRequest request, HttpServletResponse response)
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
			Sjjianchu sjjianchu = new Sjjianchu();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				sjjianchu = sjjianchuService.getSjjianchu(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(sjjianchu.getSjjianchuName());
				row.add(sjjianchu.getSjleixingName());
				row.add(sjjianchu.getSjjianchuZong().toString());
				row.add(sjjianchu.getSjjianchuMark());
				row.add(sjjianchu.getUserName());
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
