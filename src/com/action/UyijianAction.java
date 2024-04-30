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
public class UyijianAction {
	@Autowired
	private UyijianService uyijianService;
	@Autowired
	private UxtypeService uxtypeService;
	@Autowired
	private UserService userService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/getUyijians")
	public void getUyijians(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String uyijianName = (String) request.getParameter("uyijianName");
		String uyijianId = (String) request.getParameter("uyijianId");
		String uxtypeId = (String) request.getParameter("uxtypeId");
		String uyijianType = (String) request.getParameter("uyijianType");
		String uyijianType1 = (String) request.getParameter("uyijianType1");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		String sdate = (String) request.getParameter("sdate");
		String edate = (String) request.getParameter("edate");
		Uyijian uyijian = new Uyijian();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(uyijianName)) {
				uyijian.setUyijianName(uyijianName);
			}
			if (StringUtil.isNotEmpty(uyijianId)) {
				uyijian.setUyijianId(Integer.parseInt(uyijianId));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uyijian.setUxtypeId(Integer.parseInt(uxtypeId));
			}
			if (StringUtil.isNotEmpty(uyijianType)) {
				uyijian.setUyijianType(Integer.parseInt(uyijianType));
			}
			if (StringUtil.isNotEmpty(uyijianType1)) {
				uyijian.setUyijianType1(Integer.parseInt(uyijianType1));
			}
			if (StringUtil.isNotEmpty(userId)) {
				uyijian.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				uyijian.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				uyijian.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				uyijian.setBuzhiId(Integer.parseInt(buzhiId));
			}
			JSONArray jsonArray = JSONArray.fromObject(uyijianService.queryUyijians(
					uyijian, pageBean.getStart(), pageBean.getRows(), sdate, edate));
			JSONObject result = new JSONObject();
			int total = uyijianService.queryUyijians(uyijian, 0,0, sdate, edate).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addUyijian")
	public void addUyijian(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			JSONObject result = new JSONObject();

			String uyijianName = (String) request.getParameter("uyijianName");
			String uyijianMark = (String) request.getParameter("uyijianMark");
			String uyijianMark1 = (String) request.getParameter("uyijianMark1");
			String uyijianMark2 = (String) request.getParameter("uyijianMark2");
			String uyijianDate = (String) request.getParameter("uyijianDate");
			String uyijianType = (String) request.getParameter("uyijianType");
			String uyijianType1 = (String) request.getParameter("uyijianType1");
			String uyijianType2 = (String) request.getParameter("uyijianType2");
			String uyijianZong = (String) request.getParameter("uyijianZong");
			String uyijianZong1 = (String) request.getParameter("uyijianZong1");
			String uyijianZong2 = (String) request.getParameter("uyijianZong2");
			String uyijianDouble = (String) request.getParameter("uyijianDouble");
			String uyijianDouble1 = (String) request.getParameter("uyijianDouble1");
			String uyijianDouble2 = (String) request.getParameter("uyijianDouble2");
			String uxtypeId = (String) request.getParameter("uxtypeId");
			String userId = (String) request.getParameter("userId");
			String uyijianId = (String) request.getParameter("uyijianId");
			Uyijian uyijian = new Uyijian();

			if (StringUtil.isNotEmpty(uyijianId)) {
				uyijian = uyijianService.getUyijian(Integer.parseInt(uyijianId));
			}
			if (StringUtil.isNotEmpty(uyijianName)) {
				uyijian.setUyijianName(uyijianName);
			}
			if (StringUtil.isNotEmpty(uyijianMark)) {
				uyijian.setUyijianMark(uyijianMark);
			}
			if (StringUtil.isNotEmpty(uyijianMark1)) {
				uyijian.setUyijianMark1(uyijianMark1);
			}
			if (StringUtil.isNotEmpty(uyijianMark2)) {
				uyijian.setUyijianMark2(uyijianMark2);
			}
			if (StringUtil.isNotEmpty(uyijianDate)) {
				uyijian.setUyijianDate(DateUtil.formatString(uyijianDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(uyijianType)) {
				uyijian.setUyijianType(Integer.parseInt(uyijianType));
			}
			if (StringUtil.isNotEmpty(uyijianType1)) {
				uyijian.setUyijianType1(Integer.parseInt(uyijianType1));
			}
			if (StringUtil.isNotEmpty(uyijianType2)) {
				uyijian.setUyijianType2(Integer.parseInt(uyijianType2));
			}
			if (StringUtil.isNotEmpty(uyijianZong)) {
				uyijian.setUyijianZong(Integer.parseInt(uyijianZong));
			}
			if (StringUtil.isNotEmpty(uyijianZong1)) {
				uyijian.setUyijianZong1(Integer.parseInt(uyijianZong1));
			}
			if (StringUtil.isNotEmpty(uyijianZong2)) {
				uyijian.setUyijianZong2(Integer.parseInt(uyijianZong2));
			}
			if (StringUtil.isNotEmpty(uyijianDouble)) {
				uyijian.setUyijianDouble(Double.parseDouble(uyijianDouble));
			}
			if (StringUtil.isNotEmpty(uyijianDouble1)) {
				uyijian.setUyijianDouble1(Double.parseDouble(uyijianDouble1));
			}
			if (StringUtil.isNotEmpty(uyijianDouble2)) {
				uyijian.setUyijianDouble2(Double.parseDouble(uyijianDouble2));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uyijian.setUxtypeId(Integer.parseInt(uxtypeId));
				Uxtype uxtype = new Uxtype();
				uxtype = uxtypeService.getUxtype(Integer.parseInt(uxtypeId));
				uyijian.setUxtypeName(uxtype.getUxtypeName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				uyijian.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				uyijian.setUserName(user.getUserName());
				uyijian.setBumenId(user.getBumenId());
				uyijian.setBumenName(user.getBumenName());
				uyijian.setBuyuanId(user.getBuyuanId());
				uyijian.setBuyuanName(user.getBuyuanName());
				uyijian.setBuzhiId(user.getBuzhiId());
				uyijian.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(uyijianId)) {
				uyijianService.modifyUyijian(uyijian);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Date date = new Date();
				uyijian.setUyijianDate(date);
				uyijianService.save(uyijian);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteUyijian")
	public void deleteUyijian(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				uyijianService.deleteUyijian(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/uyijianComboList")
	public void uyijianComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String uyijianName = (String) request.getParameter("uyijianName");
		String uyijianId = (String) request.getParameter("uyijianId");
		String uxtypeId = (String) request.getParameter("uxtypeId");
		String uyijianType = (String) request.getParameter("uyijianType");
		String uyijianType1 = (String) request.getParameter("uyijianType1");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		Uyijian uyijian = new Uyijian();
		try {
			if (StringUtil.isNotEmpty(uyijianName)) {
				uyijian.setUyijianName(uyijianName);
			}
			if (StringUtil.isNotEmpty(uyijianId)) {
				uyijian.setUyijianId(Integer.parseInt(uyijianId));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uyijian.setUxtypeId(Integer.parseInt(uxtypeId));
			}
			if (StringUtil.isNotEmpty(uyijianType)) {
				uyijian.setUyijianType(Integer.parseInt(uyijianType));
			}
			if (StringUtil.isNotEmpty(uyijianType1)) {
				uyijian.setUyijianType1(Integer.parseInt(uyijianType1));
			}
			if (StringUtil.isNotEmpty(userId)) {
				uyijian.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				uyijian.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				uyijian.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				uyijian.setBuzhiId(Integer.parseInt(buzhiId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("uyijianName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(uyijianService.queryUyijians(uyijian,
					0,0, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/uyijianTongji")
	public void uyijianTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String userId=request.getParameter("userId");
		String tijiaoUrl = "uyijianTongji";
		List<Integer> uxtypeIds = new ArrayList<Integer>();
		List<String> tongjiNames = new ArrayList<String>();
		List<Double> tongjiZongshus = new ArrayList<Double>();
		List<Uxtype> uxtypes = new ArrayList<Uxtype>();
		List<Uyijian> uyijians = new ArrayList<Uyijian>();
		Double zongshu = 0.0;
		try {
			uxtypes = uxtypeService.queryUxtypes(null, 0,0);
			for(int i=0;i<uxtypes.size();i++){
				uxtypeIds.add(uxtypes.get(i).getUxtypeId());
				tongjiNames.add(uxtypes.get(i).getUxtypeName());
			}
			for(int i=0;i<uxtypeIds.size();i++){
				Double uyijianZongshu = 0.0;
				Uyijian uyijian = new Uyijian();
				uyijian.setUserId(Integer.parseInt(userId));
				uyijian.setUxtypeId(uxtypeIds.get(i));
				uyijians = uyijianService.queryUyijians(uyijian, 0,0,sdate,edate);
				for(int j=0;j<uyijians.size();j++){
					uyijianZongshu = uyijianZongshu + uyijians.size();
				}
				zongshu = zongshu + uyijianZongshu;
				tongjiZongshus.add(uyijianZongshu);
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

	@RequestMapping("/shangchuanUyijian")
	public void shangchuanUyijian(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String uyijianId = (String) request.getParameter("uyijianId");
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
			Uyijian uyijian = uyijianService.getUyijian(Integer.parseInt(uyijianId));
			uyijian.setUyijianImg(shangchuandizhi);
			uyijian.setUyijianImgName(shangchuanname);
			uyijianService.modifyUyijian(uyijian);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruUyijian")
	public void daoruUyijian(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
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
				String uyijianName = row.get(0);
				String uyijianMark = row.get(1);
				String uyijianMark1 = row.get(2);
				String userId = row.get(3);
				Uyijian uyijian = new Uyijian();
				
				if (StringUtil.isNotEmpty(uyijianName)) {
					uyijian.setUyijianName(uyijianName);
				}
				if (StringUtil.isNotEmpty(uyijianMark)) {
					uyijian.setUyijianMark(uyijianMark);
				}
				if (StringUtil.isNotEmpty(uyijianMark1)) {
					uyijian.setUyijianMark1(uyijianMark1);
				}
				if (StringUtil.isNotEmpty(userId)) {
					uyijian.setUserId(Integer.parseInt(userId));
					User user = new User();
					user = userService.getUser(Integer.parseInt(userId));
					uyijian.setUserName(user.getUserName());
					uyijian.setBumenId(user.getBumenId());
					uyijian.setBumenName(user.getBumenName());
					uyijian.setBuyuanId(user.getBuyuanId());
					uyijian.setBuyuanName(user.getBuyuanName());
				}
				Date date = new Date();
				uyijian.setUyijianDate(date);
				uyijianService.save(uyijian);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuUyijian")
	public void daochuUyijian(HttpServletRequest request, HttpServletResponse response)
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
			Uyijian uyijian = new Uyijian();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				uyijian = uyijianService.getUyijian(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(uyijian.getUserName());
				row.add(uyijian.getUyijianMark1());
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
