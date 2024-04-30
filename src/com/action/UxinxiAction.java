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
public class UxinxiAction {
	@Autowired
	private UxinxiService uxinxiService;
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

	@RequestMapping("/getUxinxis")
	public void getUxinxis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String uxinxiName = (String) request.getParameter("uxinxiName");
		String uxinxiId = (String) request.getParameter("uxinxiId");
		String uxtypeId = (String) request.getParameter("uxtypeId");
		String uxinxiType = (String) request.getParameter("uxinxiType");
		String uxinxiType1 = (String) request.getParameter("uxinxiType1");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		String sdate = (String) request.getParameter("sdate");
		String edate = (String) request.getParameter("edate");
		Uxinxi uxinxi = new Uxinxi();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(uxinxiName)) {
				uxinxi.setUxinxiName(uxinxiName);
			}
			if (StringUtil.isNotEmpty(uxinxiId)) {
				uxinxi.setUxinxiId(Integer.parseInt(uxinxiId));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxinxi.setUxtypeId(Integer.parseInt(uxtypeId));
			}
			if (StringUtil.isNotEmpty(uxinxiType)) {
				uxinxi.setUxinxiType(Integer.parseInt(uxinxiType));
			}
			if (StringUtil.isNotEmpty(uxinxiType1)) {
				uxinxi.setUxinxiType1(Integer.parseInt(uxinxiType1));
			}
			if (StringUtil.isNotEmpty(userId)) {
				uxinxi.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				uxinxi.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				uxinxi.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				uxinxi.setBuzhiId(Integer.parseInt(buzhiId));
			}
			JSONArray jsonArray = JSONArray.fromObject(uxinxiService.queryUxinxis(
					uxinxi, pageBean.getStart(), pageBean.getRows(), sdate, edate));
			JSONObject result = new JSONObject();
			int total = uxinxiService.queryUxinxis(uxinxi, 0,0, sdate, edate).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addUxinxi")
	public void addUxinxi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			JSONObject result = new JSONObject();

			String uxinxiName = (String) request.getParameter("uxinxiName");
			String uxinxiMark = (String) request.getParameter("uxinxiMark");
			String uxinxiMark1 = (String) request.getParameter("uxinxiMark1");
			String uxinxiMark2 = (String) request.getParameter("uxinxiMark2");
			String uxinxiDate = (String) request.getParameter("uxinxiDate");
			String uxinxiType = (String) request.getParameter("uxinxiType");
			String uxinxiType1 = (String) request.getParameter("uxinxiType1");
			String uxinxiType2 = (String) request.getParameter("uxinxiType2");
			String uxinxiZong = (String) request.getParameter("uxinxiZong");
			String uxinxiZong1 = (String) request.getParameter("uxinxiZong1");
			String uxinxiZong2 = (String) request.getParameter("uxinxiZong2");
			String uxinxiDouble = (String) request.getParameter("uxinxiDouble");
			String uxinxiDouble1 = (String) request.getParameter("uxinxiDouble1");
			String uxinxiDouble2 = (String) request.getParameter("uxinxiDouble2");
			String uxtypeId = (String) request.getParameter("uxtypeId");
			String userId = (String) request.getParameter("userId");
			String uxinxiId = (String) request.getParameter("uxinxiId");
			Uxinxi uxinxi = new Uxinxi();

			if (StringUtil.isNotEmpty(uxinxiId)) {
				uxinxi = uxinxiService.getUxinxi(Integer.parseInt(uxinxiId));
			}
			if (StringUtil.isNotEmpty(uxinxiName)) {
				uxinxi.setUxinxiName(uxinxiName);
			}
			if (StringUtil.isNotEmpty(uxinxiMark)) {
				uxinxi.setUxinxiMark(uxinxiMark);
			}
			if (StringUtil.isNotEmpty(uxinxiMark1)) {
				uxinxi.setUxinxiMark1(uxinxiMark1);
			}
			if (StringUtil.isNotEmpty(uxinxiMark2)) {
				uxinxi.setUxinxiMark2(uxinxiMark2);
			}
			if (StringUtil.isNotEmpty(uxinxiDate)) {
				uxinxi.setUxinxiDate(DateUtil.formatString(uxinxiDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(uxinxiType)) {
				uxinxi.setUxinxiType(Integer.parseInt(uxinxiType));
			}
			if (StringUtil.isNotEmpty(uxinxiType1)) {
				uxinxi.setUxinxiType1(Integer.parseInt(uxinxiType1));
			}
			if (StringUtil.isNotEmpty(uxinxiType2)) {
				uxinxi.setUxinxiType2(Integer.parseInt(uxinxiType2));
			}
			if (StringUtil.isNotEmpty(uxinxiZong)) {
				uxinxi.setUxinxiZong(Integer.parseInt(uxinxiZong));
			}
			if (StringUtil.isNotEmpty(uxinxiZong1)) {
				uxinxi.setUxinxiZong1(Integer.parseInt(uxinxiZong1));
			}
			if (StringUtil.isNotEmpty(uxinxiZong2)) {
				uxinxi.setUxinxiZong2(Integer.parseInt(uxinxiZong2));
			}
			if (StringUtil.isNotEmpty(uxinxiDouble)) {
				uxinxi.setUxinxiDouble(Double.parseDouble(uxinxiDouble));
			}
			if (StringUtil.isNotEmpty(uxinxiDouble1)) {
				uxinxi.setUxinxiDouble1(Double.parseDouble(uxinxiDouble1));
			}
			if (StringUtil.isNotEmpty(uxinxiDouble2)) {
				uxinxi.setUxinxiDouble2(Double.parseDouble(uxinxiDouble2));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxinxi.setUxtypeId(Integer.parseInt(uxtypeId));
				Uxtype uxtype = new Uxtype();
				uxtype = uxtypeService.getUxtype(Integer.parseInt(uxtypeId));
				uxinxi.setUxtypeName(uxtype.getUxtypeName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				uxinxi.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				uxinxi.setUserName(user.getUserName());
				uxinxi.setBumenId(user.getBumenId());
				uxinxi.setBumenName(user.getBumenName());
				uxinxi.setBuyuanId(user.getBuyuanId());
				uxinxi.setBuyuanName(user.getBuyuanName());
				uxinxi.setBuzhiId(user.getBuzhiId());
				uxinxi.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(uxinxiId)) {
				uxinxiService.modifyUxinxi(uxinxi);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Date date = new Date();
				uxinxi.setUxinxiDate(date);
				uxinxiService.save(uxinxi);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteUxinxi")
	public void deleteUxinxi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				uxinxiService.deleteUxinxi(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/uxinxiComboList")
	public void uxinxiComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String uxinxiName = (String) request.getParameter("uxinxiName");
		String uxinxiId = (String) request.getParameter("uxinxiId");
		String uxtypeId = (String) request.getParameter("uxtypeId");
		String uxinxiType = (String) request.getParameter("uxinxiType");
		String uxinxiType1 = (String) request.getParameter("uxinxiType1");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		Uxinxi uxinxi = new Uxinxi();
		try {
			if (StringUtil.isNotEmpty(uxinxiName)) {
				uxinxi.setUxinxiName(uxinxiName);
			}
			if (StringUtil.isNotEmpty(uxinxiId)) {
				uxinxi.setUxinxiId(Integer.parseInt(uxinxiId));
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxinxi.setUxtypeId(Integer.parseInt(uxtypeId));
			}
			if (StringUtil.isNotEmpty(uxinxiType)) {
				uxinxi.setUxinxiType(Integer.parseInt(uxinxiType));
			}
			if (StringUtil.isNotEmpty(uxinxiType1)) {
				uxinxi.setUxinxiType1(Integer.parseInt(uxinxiType1));
			}
			if (StringUtil.isNotEmpty(userId)) {
				uxinxi.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				uxinxi.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				uxinxi.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				uxinxi.setBuzhiId(Integer.parseInt(buzhiId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("uxinxiName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(uxinxiService.queryUxinxis(uxinxi,
					0,0, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/uxinxiTongji")
	public void uxinxiTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String userId=request.getParameter("userId");
		String tijiaoUrl = request.getParameter("tijiaoUrl");
		List<Integer> uxtypeIds = new ArrayList<Integer>();
		List<String> tongjiNames = new ArrayList<String>();
		List<Double> tongjiZongshus = new ArrayList<Double>();
		List<Uxtype> uxtypes = new ArrayList<Uxtype>();
		List<Uxinxi> uxinxis = new ArrayList<Uxinxi>();
		Double zongshu = 0.0;
		try {
			uxtypes = uxtypeService.queryUxtypes(null, 0,0);
			for(int i=0;i<uxtypes.size();i++){
				uxtypeIds.add(uxtypes.get(i).getUxtypeId());
				tongjiNames.add(uxtypes.get(i).getUxtypeName());
			}
			for(int i=0;i<uxtypeIds.size();i++){
				Double uxinxiZongshu = 0.0;
				Uxinxi uxinxi = new Uxinxi();
				uxinxi.setUserId(Integer.parseInt(userId));
				uxinxi.setUxtypeId(uxtypeIds.get(i));
				uxinxis = uxinxiService.queryUxinxis(uxinxi, 0,0,sdate,edate);
				for(int j=0;j<uxinxis.size();j++){
					uxinxiZongshu = uxinxiZongshu + uxinxis.size();
				}
				zongshu = zongshu + uxinxiZongshu;
				tongjiZongshus.add(uxinxiZongshu);
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

	@RequestMapping("/shangchuanUxinxi")
	public void shangchuanUxinxi(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String uxinxiId = (String) request.getParameter("uxinxiId");
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
			Uxinxi uxinxi = uxinxiService.getUxinxi(Integer.parseInt(uxinxiId));
			uxinxi.setUxinxiImg(shangchuandizhi);
			uxinxi.setUxinxiImgName(shangchuanname);
			uxinxiService.modifyUxinxi(uxinxi);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruUxinxi")
	public void daoruUxinxi(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
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
				String uxinxiName = row.get(0);
				String uxinxiMark = row.get(1);
				String uxinxiMark1 = row.get(2);
				String userId = row.get(3);
				Uxinxi uxinxi = new Uxinxi();
				
				if (StringUtil.isNotEmpty(uxinxiName)) {
					uxinxi.setUxinxiName(uxinxiName);
				}
				if (StringUtil.isNotEmpty(uxinxiMark)) {
					uxinxi.setUxinxiMark(uxinxiMark);
				}
				if (StringUtil.isNotEmpty(uxinxiMark1)) {
					uxinxi.setUxinxiMark1(uxinxiMark1);
				}
				if (StringUtil.isNotEmpty(userId)) {
					uxinxi.setUserId(Integer.parseInt(userId));
					User user = new User();
					user = userService.getUser(Integer.parseInt(userId));
					uxinxi.setUserName(user.getUserName());
					uxinxi.setBumenId(user.getBumenId());
					uxinxi.setBumenName(user.getBumenName());
					uxinxi.setBuyuanId(user.getBuyuanId());
					uxinxi.setBuyuanName(user.getBuyuanName());
				}
				Date date = new Date();
				uxinxi.setUxinxiDate(date);
				uxinxiService.save(uxinxi);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuUxinxi")
	public void daochuUxinxi(HttpServletRequest request, HttpServletResponse response)
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
			Uxinxi uxinxi = new Uxinxi();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				uxinxi = uxinxiService.getUxinxi(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(uxinxi.getUserName());
				row.add(uxinxi.getUxinxiMark1());
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
