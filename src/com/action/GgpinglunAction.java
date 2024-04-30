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
public class GgpinglunAction {
	@Autowired
	private GonggaoService gonggaoService;
	@Autowired
	private GgpinglunService ggpinglunService;
	@Autowired
	private UserService userService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/addGgpinglun")
	public void addGgpinglun(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String ggpinglunName = (String) request.getParameter("ggpinglunName");
			String ggpinglunMark = (String) request.getParameter("ggpinglunMark");
			String ggpinglunDate = (String) request.getParameter("ggpinglunDate");
			String ggpinglunType = (String) request.getParameter("ggpinglunType");
			String ggpinglunType1 = (String) request.getParameter("ggpinglunType1");
			String gonggaoId = (String) request.getParameter("gonggaoId");
			String yonghuId = (String) request.getParameter("yonghuId");
			String userId = (String) request.getParameter("userId");
			String ggpinglunId = (String) request.getParameter("ggpinglunId");
			Ggpinglun ggpinglun = new Ggpinglun();

			if (StringUtil.isNotEmpty(ggpinglunId)) {
				ggpinglun = ggpinglunService.getGgpinglun(Integer.parseInt(ggpinglunId));
			}
			if (StringUtil.isNotEmpty(ggpinglunName)) {
				ggpinglun.setGgpinglunName(ggpinglunName);
			}
			if (StringUtil.isNotEmpty(ggpinglunMark)) {
				ggpinglun.setGgpinglunMark(ggpinglunMark);
			}
			if (StringUtil.isNotEmpty(ggpinglunDate)) {
				ggpinglun.setGgpinglunDate(DateUtil.formatString(ggpinglunDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(ggpinglunType)) {
				ggpinglun.setGgpinglunType(Integer.parseInt(ggpinglunType));
			}
			if (StringUtil.isNotEmpty(ggpinglunType1)) {
				ggpinglun.setGgpinglunType1(Integer.parseInt(ggpinglunType1));
			}
			if (StringUtil.isNotEmpty(gonggaoId)) {
				ggpinglun.setGonggaoId(Integer.parseInt(gonggaoId));
				Gonggao gonggao = new Gonggao();
				gonggao = gonggaoService.getGonggao(Integer.parseInt(gonggaoId));
				ggpinglun.setGonggaoName(gonggao.getGonggaoName());
				ggpinglun.setGgtypeId(gonggao.getGgtypeId());
				ggpinglun.setGgtypeName(gonggao.getGgtypeName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				ggpinglun.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				ggpinglun.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				ggpinglun.setUserName(user.getUserName());
				ggpinglun.setBumenId(user.getBumenId());
				ggpinglun.setBumenName(user.getBumenName());
				ggpinglun.setBuyuanId(user.getBuyuanId());
				ggpinglun.setBuyuanName(user.getBuyuanName());
				ggpinglun.setBuzhiId(user.getBuzhiId());
				ggpinglun.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(ggpinglunId)) {
				ggpinglunService.modifyGgpinglun(ggpinglun);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Date date = new Date();
				ggpinglun.setGgpinglunDate(date);
				ggpinglun.setGgpinglunType(0);
				ggpinglunService.save(ggpinglun);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getGgpingluns")
	public void getGgpingluns(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String ggpinglunName = (String) request.getParameter("ggpinglunName");
		String ggpinglunId = (String) request.getParameter("ggpinglunId");
		String ggpinglunType = (String) request.getParameter("ggpinglunType");
		String ggpinglunType1 = (String) request.getParameter("ggpinglunType1");
		String gonggaoId = (String) request.getParameter("gonggaoId");
		String ggtypeId = (String) request.getParameter("ggtypeId");
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
		Ggpinglun ggpinglun = new Ggpinglun();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(ggpinglunName)) {
				ggpinglun.setGgpinglunName(ggpinglunName);
			}
			if (StringUtil.isNotEmpty(ggpinglunId)) {
				ggpinglun.setGgpinglunId(Integer.parseInt(ggpinglunId));
			}
			if (StringUtil.isNotEmpty(ggpinglunType)) {
				ggpinglun.setGgpinglunType(Integer.parseInt(ggpinglunType));
			}
			if (StringUtil.isNotEmpty(ggpinglunType1)) {
				ggpinglun.setGgpinglunType1(Integer.parseInt(ggpinglunType1));
			}
			if (StringUtil.isNotEmpty(gonggaoId)) {
				ggpinglun.setGonggaoId(Integer.parseInt(gonggaoId));
			}
			if (StringUtil.isNotEmpty(ggtypeId)) {
				ggpinglun.setGgtypeId(Integer.parseInt(ggtypeId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				ggpinglun.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				ggpinglun.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				ggpinglun.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				ggpinglun.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				ggpinglun.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				ggpinglun.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				ggpinglun.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				ggpinglun.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			JSONArray jsonArray = JSONArray.fromObject(ggpinglunService.queryGgpingluns(
					ggpinglun, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1));
			JSONObject result = new JSONObject();
			int total = ggpinglunService.queryGgpingluns(ggpinglun, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteGgpinglun")
	public void deleteGgpinglun(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				ggpinglunService.deleteGgpinglun(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/ggpinglunComboList")
	public void ggpinglunComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String ggpinglunName = (String) request.getParameter("ggpinglunName");
		String ggpinglunId = (String) request.getParameter("ggpinglunId");
		String ggpinglunType = (String) request.getParameter("ggpinglunType");
		String ggpinglunType1 = (String) request.getParameter("ggpinglunType1");
		String gonggaoId = (String) request.getParameter("gonggaoId");
		String ggtypeId = (String) request.getParameter("ggtypeId");
		String yonghuId = (String) request.getParameter("yonghuId");
		String byumenId = (String) request.getParameter("byumenId");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String byuzhiId = (String) request.getParameter("byuzhiId");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		Ggpinglun ggpinglun = new Ggpinglun();
		try {
			if (StringUtil.isNotEmpty(ggpinglunName)) {
				ggpinglun.setGgpinglunName(ggpinglunName);
			}
			if (StringUtil.isNotEmpty(ggpinglunId)) {
				ggpinglun.setGgpinglunId(Integer.parseInt(ggpinglunId));
			}
			if (StringUtil.isNotEmpty(ggpinglunType)) {
				ggpinglun.setGgpinglunType(Integer.parseInt(ggpinglunType));
			}
			if (StringUtil.isNotEmpty(ggpinglunType1)) {
				ggpinglun.setGgpinglunType1(Integer.parseInt(ggpinglunType1));
			}
			if (StringUtil.isNotEmpty(gonggaoId)) {
				ggpinglun.setGonggaoId(Integer.parseInt(gonggaoId));
			}
			if (StringUtil.isNotEmpty(ggtypeId)) {
				ggpinglun.setGgtypeId(Integer.parseInt(ggtypeId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				ggpinglun.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				ggpinglun.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				ggpinglun.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				ggpinglun.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				ggpinglun.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				ggpinglun.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				ggpinglun.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				ggpinglun.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("ggpinglunName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(ggpinglunService.queryGgpingluns(ggpinglun,
					0,0, null, null, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/ggpinglunTongji")
	public void ggpinglunTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String userId=request.getParameter("userId");
		String tijiaoUrl = request.getParameter("tijiaoUrl");
		List<Integer> gonggaoIds = new ArrayList<Integer>();
		List<String> tongjiNames = new ArrayList<String>();
		List<Double> tongjiZongshus = new ArrayList<Double>();
		List<Gonggao> gonggaos = new ArrayList<Gonggao>();
		List<Ggpinglun> ggpingluns = new ArrayList<Ggpinglun>();
		Double zongshu = 0.0;
		Ggpinglun ggpinglun = new Ggpinglun();
		if (StringUtil.isNotEmpty(userId)) {
			ggpinglun.setUserId(Integer.parseInt(userId));
		}
		try {
			gonggaos = gonggaoService.queryGonggaos(null, 0, 0, null, null);
			for(int i=0;i<gonggaos.size();i++){
				gonggaoIds.add(gonggaos.get(i).getGonggaoId());
				tongjiNames.add(gonggaos.get(i).getGonggaoName());
			}
			for(int i=0;i<gonggaoIds.size();i++){
				Double ggpinglunZongshu = 0.0;
				ggpinglun.setGonggaoId(gonggaoIds.get(i));
				ggpingluns = ggpinglunService.queryGgpingluns(ggpinglun, 0,0,sdate,edate, null, null);
				for(int j=0;j<ggpingluns.size();j++){
					ggpinglunZongshu = ggpinglunZongshu + ggpingluns.get(j).getGgpinglunType();
				}
				zongshu = zongshu + ggpinglunZongshu;
				tongjiZongshus.add(ggpinglunZongshu);
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

	@RequestMapping("/shangchuanGgpinglun")
	public void shangchuanGgpinglun(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String ggpinglunId = (String) request.getParameter("ggpinglunId");
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
			Ggpinglun ggpinglun = ggpinglunService.getGgpinglun(Integer.parseInt(ggpinglunId));
			ggpinglun.setGgpinglunImg(shangchuandizhi);
			ggpinglun.setGgpinglunImgName(shangchuanname);
			ggpinglunService.modifyGgpinglun(ggpinglun);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruGgpinglun")
	public void daoruGgpinglun(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
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
				String ggpinglunName = row.get(0);
				String gonggaoId = row.get(1);
				String ggpinglunMark = row.get(2);
				String userId = row.get(3);
				Ggpinglun ggpinglun = new Ggpinglun();
				
				if (StringUtil.isNotEmpty(ggpinglunName)) {
					ggpinglun.setGgpinglunName(ggpinglunName);
				}
				if (StringUtil.isNotEmpty(gonggaoId)) {
					ggpinglun.setGonggaoId(Integer.parseInt(gonggaoId));
					Gonggao gonggao = new Gonggao();
					gonggao = gonggaoService.getGonggao(Integer.parseInt(gonggaoId));
					ggpinglun.setGonggaoName(gonggao.getGonggaoName());
				}
				if (StringUtil.isNotEmpty(ggpinglunMark)) {
					ggpinglun.setGgpinglunMark(ggpinglunMark);
				}
				if (StringUtil.isNotEmpty(userId)) {
					ggpinglun.setUserId(Integer.parseInt(userId));
					User user = new User();
					user = userService.getUser(Integer.parseInt(userId));
					ggpinglun.setUserName(user.getUserName());
					ggpinglun.setBumenId(user.getBumenId());
					ggpinglun.setBumenName(user.getBumenName());
					ggpinglun.setBuyuanId(user.getBuyuanId());
					ggpinglun.setBuyuanName(user.getBuyuanName());
					ggpinglun.setBuzhiId(user.getBuzhiId());
					ggpinglun.setBuzhiName(user.getBuzhiName());
				}
				Date date = new Date();
				ggpinglun.setGgpinglunDate(date);
				ggpinglunService.save(ggpinglun);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuGgpinglun")
	public void daochuGgpinglun(HttpServletRequest request, HttpServletResponse response)
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
			Ggpinglun ggpinglun = new Ggpinglun();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				ggpinglun = ggpinglunService.getGgpinglun(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(ggpinglun.getGgpinglunName());
				row.add(ggpinglun.getGonggaoName());
				row.add(ggpinglun.getGgpinglunMark());
				row.add(ggpinglun.getUserName());
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
