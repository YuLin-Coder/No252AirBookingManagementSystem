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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
public class SjpinglunAction {
	@Autowired
	private ShujuService shujuService;
	@Autowired
	private SjpinglunService sjpinglunService;
	@Autowired
	private UserService userService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/addSjpinglun")
	public void addSjpinglun(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String sjpinglunName = (String) request.getParameter("sjpinglunName");
			String sjpinglunMark = (String) request.getParameter("sjpinglunMark");
			String sjpinglunDate = (String) request.getParameter("sjpinglunDate");
			String sjpinglunType = (String) request.getParameter("sjpinglunType");
			String sjpinglunType1 = (String) request.getParameter("sjpinglunType1");
			String shujuId = (String) request.getParameter("shujuId");
			String yonghuId = (String) request.getParameter("yonghuId");
			String userId = (String) request.getParameter("userId");
			String sjpinglunId = (String) request.getParameter("sjpinglunId");
			Sjpinglun sjpinglun = new Sjpinglun();

			if (StringUtil.isNotEmpty(sjpinglunId)) {
				sjpinglun = sjpinglunService.getSjpinglun(Integer.parseInt(sjpinglunId));
			}
			if (StringUtil.isNotEmpty(sjpinglunName)) {
				sjpinglun.setSjpinglunName(sjpinglunName);
			}
			if (StringUtil.isNotEmpty(sjpinglunMark)) {
				sjpinglun.setSjpinglunMark(sjpinglunMark);
			}
			if (StringUtil.isNotEmpty(sjpinglunDate)) {
				sjpinglun.setSjpinglunDate(DateUtil.formatString(sjpinglunDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjpinglunType)) {
				sjpinglun.setSjpinglunType(Integer.parseInt(sjpinglunType));
			}
			if (StringUtil.isNotEmpty(sjpinglunType1)) {
				sjpinglun.setSjpinglunType1(Integer.parseInt(sjpinglunType1));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjpinglun.setShujuId(Integer.parseInt(shujuId));
				Shuju shuju = new Shuju();
				shuju = shujuService.getShuju(Integer.parseInt(shujuId));
				sjpinglun.setShujuName(shuju.getShujuName());
				sjpinglun.setSjleixingId(shuju.getSjleixingId());
				sjpinglun.setSjleixingName(shuju.getSjleixingName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjpinglun.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjpinglun.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				sjpinglun.setUserName(user.getUserName());
				sjpinglun.setBumenId(user.getBumenId());
				sjpinglun.setBumenName(user.getBumenName());
				sjpinglun.setBuyuanId(user.getBuyuanId());
				sjpinglun.setBuyuanName(user.getBuyuanName());
				sjpinglun.setBuzhiId(user.getBuzhiId());
				sjpinglun.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjpinglunId)) {
				sjpinglunService.modifySjpinglun(sjpinglun);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				List<Sjpinglun> sjpingluns = new ArrayList<Sjpinglun>();
				Sjpinglun newSjpinglun = new Sjpinglun();
				newSjpinglun.setSjpinglunType1(sjpinglun.getSjpinglunType1());
				sjpingluns = sjpinglunService.querySjpingluns(newSjpinglun, 0,0, null, null, null, null);
				if(sjpingluns.size()==0){
					Date date = new Date();
					sjpinglun.setSjpinglunDate(date);
					sjpinglun.setSjpinglunType(0);
					sjpinglunService.save(sjpinglun);
					result.put("success", "true");
					ResponseUtil.write(response, result);
				}else{
					result.put("success", "true");
					result.put("errorMsg", "请勿重复评论！");
					ResponseUtil.write(response, result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getSjpingluns")
	public void getSjpingluns(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String sjpinglunName = (String) request.getParameter("sjpinglunName");
		String sjpinglunId = (String) request.getParameter("sjpinglunId");
		String sjpinglunType = (String) request.getParameter("sjpinglunType");
		String sjpinglunType1 = (String) request.getParameter("sjpinglunType1");
		String shujuId = (String) request.getParameter("shujuId");
		String sjleixingId = (String) request.getParameter("sjleixingId");
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
		Sjpinglun sjpinglun = new Sjpinglun();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(sjpinglunName)) {
				sjpinglun.setSjpinglunName(sjpinglunName);
			}
			if (StringUtil.isNotEmpty(sjpinglunId)) {
				sjpinglun.setSjpinglunId(Integer.parseInt(sjpinglunId));
			}
			if (StringUtil.isNotEmpty(sjpinglunType)) {
				sjpinglun.setSjpinglunType(Integer.parseInt(sjpinglunType));
			}
			if (StringUtil.isNotEmpty(sjpinglunType1)) {
				sjpinglun.setSjpinglunType1(Integer.parseInt(sjpinglunType1));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjpinglun.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjpinglun.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjpinglun.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjpinglun.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjpinglun.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjpinglun.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjpinglun.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				sjpinglun.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				sjpinglun.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				sjpinglun.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			JSONArray jsonArray = JSONArray.fromObject(sjpinglunService.querySjpingluns(
					sjpinglun, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1));
			JSONObject result = new JSONObject();
			int total = sjpinglunService.querySjpingluns(sjpinglun, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteSjpinglun")
	public void deleteSjpinglun(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjpinglunService.deleteSjpinglun(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjpinglunComboList")
	public void sjpinglunComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sjpinglunName = (String) request.getParameter("sjpinglunName");
		String sjpinglunId = (String) request.getParameter("sjpinglunId");
		String sjpinglunType = (String) request.getParameter("sjpinglunType");
		String sjpinglunType1 = (String) request.getParameter("sjpinglunType1");
		String shujuId = (String) request.getParameter("shujuId");
		String sjleixingId = (String) request.getParameter("sjleixingId");
		String yonghuId = (String) request.getParameter("yonghuId");
		String byumenId = (String) request.getParameter("byumenId");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String byuzhiId = (String) request.getParameter("byuzhiId");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		Sjpinglun sjpinglun = new Sjpinglun();
		try {
			if (StringUtil.isNotEmpty(sjpinglunName)) {
				sjpinglun.setSjpinglunName(sjpinglunName);
			}
			if (StringUtil.isNotEmpty(sjpinglunId)) {
				sjpinglun.setSjpinglunId(Integer.parseInt(sjpinglunId));
			}
			if (StringUtil.isNotEmpty(sjpinglunType)) {
				sjpinglun.setSjpinglunType(Integer.parseInt(sjpinglunType));
			}
			if (StringUtil.isNotEmpty(sjpinglunType1)) {
				sjpinglun.setSjpinglunType1(Integer.parseInt(sjpinglunType1));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjpinglun.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjpinglun.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjpinglun.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjpinglun.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjpinglun.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjpinglun.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjpinglun.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				sjpinglun.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				sjpinglun.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				sjpinglun.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("sjpinglunName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(sjpinglunService.querySjpingluns(sjpinglun,
					0,0, null, null, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjpinglunTongji")
	public void sjpinglunTongji(HttpServletRequest request, HttpServletResponse response)
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
		List<Sjpinglun> sjpingluns = new ArrayList<Sjpinglun>();
		Double zongshu = 0.0;
		Sjpinglun sjpinglun = new Sjpinglun();
		if (StringUtil.isNotEmpty(userId)) {
			sjpinglun.setUserId(Integer.parseInt(userId));
		}
		try {
			shujus = shujuService.queryShujus(null, 0,0, null, null, null, null);
			for(int i=0;i<shujus.size();i++){
				shujuIds.add(shujus.get(i).getShujuId());
				tongjiNames.add(shujus.get(i).getShujuName());
			}
			for(int i=0;i<shujuIds.size();i++){
				Double sjpinglunZongshu = 0.0;
				sjpinglun.setShujuId(shujuIds.get(i));
				sjpingluns = sjpinglunService.querySjpingluns(sjpinglun, 0,0,sdate,edate, null, null);
				for(int j=0;j<sjpingluns.size();j++){
					sjpinglunZongshu = sjpinglunZongshu + sjpingluns.get(j).getSjpinglunType();
				}
				zongshu = zongshu + sjpinglunZongshu;
				tongjiZongshus.add(sjpinglunZongshu);
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

	@RequestMapping("/shangchuanSjpinglun")
	public void shangchuanSjpinglun(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String sjpinglunId = (String) request.getParameter("sjpinglunId");
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
			Sjpinglun sjpinglun = sjpinglunService.getSjpinglun(Integer.parseInt(sjpinglunId));
			sjpinglun.setSjpinglunImg(shangchuandizhi);
			sjpinglun.setSjpinglunImgName(shangchuanname);
			sjpinglunService.modifySjpinglun(sjpinglun);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruSjpinglun")
	public void daoruSjpinglun(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
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
				String sjpinglunName = row.get(0);
				String shujuId = row.get(1);
				String sjpinglunMark = row.get(2);
				String userId = row.get(3);
				Sjpinglun sjpinglun = new Sjpinglun();
				
				if (StringUtil.isNotEmpty(sjpinglunName)) {
					sjpinglun.setSjpinglunName(sjpinglunName);
				}
				if (StringUtil.isNotEmpty(shujuId)) {
					sjpinglun.setShujuId(Integer.parseInt(shujuId));
					Shuju shuju = new Shuju();
					shuju = shujuService.getShuju(Integer.parseInt(shujuId));
					sjpinglun.setShujuName(shuju.getShujuName());
				}
				if (StringUtil.isNotEmpty(sjpinglunMark)) {
					sjpinglun.setSjpinglunMark(sjpinglunMark);
				}
				if (StringUtil.isNotEmpty(userId)) {
					sjpinglun.setUserId(Integer.parseInt(userId));
					User user = new User();
					user = userService.getUser(Integer.parseInt(userId));
					sjpinglun.setUserName(user.getUserName());
					sjpinglun.setBumenId(user.getBumenId());
					sjpinglun.setBumenName(user.getBumenName());
					sjpinglun.setBuyuanId(user.getBuyuanId());
					sjpinglun.setBuyuanName(user.getBuyuanName());
					sjpinglun.setBuzhiId(user.getBuzhiId());
					sjpinglun.setBuzhiName(user.getBuzhiName());
				}
				Date date = new Date();
				sjpinglun.setSjpinglunDate(date);
				sjpinglunService.save(sjpinglun);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuSjpinglun")
	public void daochuSjpinglun(HttpServletRequest request, HttpServletResponse response)
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
			Sjpinglun sjpinglun = new Sjpinglun();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				sjpinglun = sjpinglunService.getSjpinglun(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(sjpinglun.getSjpinglunName());
				row.add(sjpinglun.getShujuName());
				row.add(sjpinglun.getSjpinglunMark());
				row.add(sjpinglun.getUserName());
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
