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
public class SjduochuAction {
	@Autowired
	private SjduochuService sjduochuService;
	@Autowired
	private SjqitaService sjqitaService;
	@Autowired
	private SjlaiyuanService sjlaiyuanService;
	@Autowired
	private SjleixingService sjleixingService;
	@Autowired
	private ShujuService shujuService;
	@Autowired
	private SjshaochuService sjshaochuService;
	@Autowired
	private UserService userService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/getSjduochus")
	public void getSjduochus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String sjduochuName = (String) request.getParameter("sjduochuName");
		String sjduochuId = (String) request.getParameter("sjduochuId");
		String sjduochuType = (String) request.getParameter("sjduochuType");
		String sjduochuType1 = (String) request.getParameter("sjduochuType1");
		String sjduochuType2 = (String) request.getParameter("sjduochuType2");
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
		Sjduochu sjduochu = new Sjduochu();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(sjduochuName)) {
				sjduochu.setSjduochuName(sjduochuName);
			}
			if (StringUtil.isNotEmpty(sjduochuId)) {
				sjduochu.setSjduochuId(Integer.parseInt(sjduochuId));
			}
			if (StringUtil.isNotEmpty(sjduochuType)) {
				sjduochu.setSjduochuType(Integer.parseInt(sjduochuType));
			}
			if (StringUtil.isNotEmpty(sjduochuType1)) {
				sjduochu.setSjduochuType1(Integer.parseInt(sjduochuType1));
			}
			if (StringUtil.isNotEmpty(sjduochuType2)) {
				sjduochu.setSjduochuType2(Integer.parseInt(sjduochuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjduochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjduochu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjduochu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjduochu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjduochu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjduochu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjduochu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjduochu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjduochu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjduochu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				sjduochu.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				sjduochu.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				sjduochu.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			JSONArray jsonArray = JSONArray.fromObject(sjduochuService.querySjduochus(
					sjduochu, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1));
			JSONObject result = new JSONObject();
			int total = sjduochuService.querySjduochus(sjduochu, 0,0, sdate, edate, sdate1, edate1).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addSjduochu")
	public void addSjduochu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String sjduochuName = (String) request.getParameter("sjduochuName");
			String sjduochuMark = (String) request.getParameter("sjduochuMark");
			String sjduochuMark1 = (String) request.getParameter("sjduochuMark1");
			String sjduochuMark2 = (String) request.getParameter("sjduochuMark2");
			String sjduochuMark3 = (String) request.getParameter("sjduochuMark3");
			String sjduochuDate = (String) request.getParameter("sjduochuDate");
			String sjduochuDate1 = (String) request.getParameter("sjduochuDate1");
			String sjduochuZong = (String) request.getParameter("sjduochuZong");
			String sjduochuZong1 = (String) request.getParameter("sjduochuZong1");
			String sjduochuZong2 = (String) request.getParameter("sjduochuZong2");
			String sjduochuDouble = (String) request.getParameter("sjduochuDouble");
			String sjduochuDouble1 = (String) request.getParameter("sjduochuDouble1");
			String sjduochuDouble2 = (String) request.getParameter("sjduochuDouble2");
			String sjduochuType = (String) request.getParameter("sjduochuType");
			String sjduochuType1 = (String) request.getParameter("sjduochuType1");
			String sjduochuType2 = (String) request.getParameter("sjduochuType2");
			String shujuId = (String) request.getParameter("shujuId");
			String sjqitaId = (String) request.getParameter("sjqitaId");
			String sjlaiyuanId = (String) request.getParameter("sjlaiyuanId");
			String yonghuId = (String) request.getParameter("yonghuId");
			String userId = (String) request.getParameter("userId");
			String sjduochuId = (String) request.getParameter("sjduochuId");
			Sjduochu sjduochu = new Sjduochu();

			if (StringUtil.isNotEmpty(sjduochuId)) {
				sjduochu = sjduochuService.getSjduochu(Integer.parseInt(sjduochuId));
			}
			if (StringUtil.isNotEmpty(sjduochuName)) {
				sjduochu.setSjduochuName(sjduochuName);
			}
			if (StringUtil.isNotEmpty(sjduochuMark)) {
				sjduochu.setSjduochuMark(sjduochuMark);
			}
			if (StringUtil.isNotEmpty(sjduochuMark1)) {
				sjduochu.setSjduochuMark1(sjduochuMark1);
			}
			if (StringUtil.isNotEmpty(sjduochuMark2)) {
				sjduochu.setSjduochuMark2(sjduochuMark2);
			}
			if (StringUtil.isNotEmpty(sjduochuMark3)) {
				sjduochu.setSjduochuMark3(sjduochuMark3);
			}
			if (StringUtil.isNotEmpty(sjduochuDate)) {
				sjduochu.setSjduochuDate(DateUtil.formatString(sjduochuDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjduochuDate1)) {
				sjduochu.setSjduochuDate1(DateUtil.formatString(sjduochuDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjduochuZong)) {
				sjduochu.setSjduochuZong(Integer.parseInt(sjduochuZong));
			}
			if (StringUtil.isNotEmpty(sjduochuZong1)) {
				sjduochu.setSjduochuZong1(Integer.parseInt(sjduochuZong1));
			}
			if (StringUtil.isNotEmpty(sjduochuZong2)) {
				sjduochu.setSjduochuZong2(Integer.parseInt(sjduochuZong2));
			}
			if (StringUtil.isNotEmpty(sjduochuDouble)) {
				sjduochu.setSjduochuDouble(Double.parseDouble(sjduochuDouble));
			}
			if (StringUtil.isNotEmpty(sjduochuDouble1)) {
				sjduochu.setSjduochuDouble1(Double.parseDouble(sjduochuDouble1));
			}
			if (StringUtil.isNotEmpty(sjduochuDouble2)) {
				sjduochu.setSjduochuDouble2(Double.parseDouble(sjduochuDouble2));
			}
			if (StringUtil.isNotEmpty(sjduochuType)) {
				sjduochu.setSjduochuType(Integer.parseInt(sjduochuType));
			}
			if (StringUtil.isNotEmpty(sjduochuType1)) {
				sjduochu.setSjduochuType1(Integer.parseInt(sjduochuType1));
			}
			if (StringUtil.isNotEmpty(sjduochuType2)) {
				sjduochu.setSjduochuType2(Integer.parseInt(sjduochuType2));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjduochu.setShujuId(Integer.parseInt(shujuId));
				Shuju shuju = new Shuju();
				shuju = shujuService.getShuju(Integer.parseInt(shujuId));
				sjduochu.setShujuName(shuju.getShujuName());
				sjduochu.setSjleixingId(shuju.getSjleixingId());
				sjduochu.setSjleixingName(shuju.getSjleixingName());
				sjduochu.setSjxingtaiId(shuju.getSjxingtaiId());
				sjduochu.setSjxingtaiName(shuju.getSjxingtaiName());
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjduochu.setSjqitaId(Integer.parseInt(sjqitaId));
				Sjqita sjqita = new Sjqita();
				sjqita = sjqitaService.getSjqita(Integer.parseInt(sjqitaId));
				sjduochu.setSjqitaName(sjqita.getSjqitaName());
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjduochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
				Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
				sjlaiyuan = sjlaiyuanService.getSjlaiyuan(Integer.parseInt(sjlaiyuanId));
				sjduochu.setSjlaiyuanName(sjlaiyuan.getSjlaiyuanName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjduochu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjduochu.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				sjduochu.setUserName(user.getUserName());
				sjduochu.setBumenId(user.getBumenId());
				sjduochu.setBumenName(user.getBumenName());
				sjduochu.setBuyuanId(user.getBuyuanId());
				sjduochu.setBuyuanName(user.getBuyuanName());
				sjduochu.setBuzhiId(user.getBuzhiId());
				sjduochu.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjduochuId)) {
				if(sjduochu.getSjduochuType()==1){
					sjduochu.setSjduochuMark1(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
					List<Sjshaochu> sjshaochus = new ArrayList<Sjshaochu>();
					Sjshaochu sjshaochu = new Sjshaochu();
					sjshaochu.setSjshaochuType1(sjduochu.getSjduochuId());
					sjshaochus = sjshaochuService.querySjshaochus(sjshaochu, 0, 0, null, null, null, null);
					for (int i = 0; i < sjshaochus.size(); i++) {
						sjshaochu = sjshaochus.get(i);
						sjshaochu.setSjshaochuType(1);
						sjshaochu.setSjshaochuMark1(sjduochu.getSjduochuMark1());
						sjshaochuService.modifySjshaochu(sjshaochu);
					}
				}
				if(sjduochu.getSjduochuType()==2){
					sjduochu.setSjduochuMark2(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
					List<Sjshaochu> sjshaochus = new ArrayList<Sjshaochu>();
					Sjshaochu sjshaochu = new Sjshaochu();
					sjshaochu.setSjshaochuType1(sjduochu.getSjduochuId());
					sjshaochus = sjshaochuService.querySjshaochus(sjshaochu, 0, 0, null, null, null, null);
					for (int i = 0; i < sjshaochus.size(); i++) {
						sjshaochu = sjshaochus.get(i);
						sjshaochu.setSjshaochuType(2);
						sjshaochu.setSjshaochuMark2(sjduochu.getSjduochuMark2());
						sjshaochuService.modifySjshaochu(sjshaochu);
					}
				}
				sjduochuService.modifySjduochu(sjduochu);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Shuju shuju = new Shuju();
				shuju = shujuService.getShuju(sjduochu.getShujuId());
				sjduochu.setSjduochuImg(shuju.getShujuImg());
				sjduochu.setSjduochuImgName(shuju.getShujuImgName());
				sjduochu.setSjduochuMark(shuju.getShujuMark());
				sjduochu.setSjduochuZong(shuju.getShujuZong());
				sjduochu.setSjduochuZong1(0);
				sjduochu.setSjduochuZong2(sjduochu.getSjduochuZong());
				sjduochu.setSjduochuDouble(shuju.getShujuDouble());
				Date date = new Date();
				sjduochu.setSjduochuDate(date);
				sjduochu.setSjduochuType(0);
				for (int i = 0; i < Integer.parseInt(sjduochuZong); i++) {
					Date hangbanriqiDate = DateUtil.jiatian(date, i);
					sjduochu.setSjduochuName(DateUtil.formatDate(hangbanriqiDate, "yyyy-MM-dd"));
					List<Sjduochu> sjduochus = new ArrayList<Sjduochu>();
					Sjduochu newSjduochu = new Sjduochu();
					newSjduochu.setShujuId(sjduochu.getShujuId());
					newSjduochu.setSjduochuName(sjduochu.getSjduochuName());
					sjduochus = sjduochuService.querySjduochus(newSjduochu, 0, 0, null, null, null, null);
					if(sjduochus.size()==0){
						sjduochuService.save(sjduochu);
					}
				}
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteSjduochu")
	public void deleteSjduochu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjduochuService.deleteSjduochu(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjduochuComboList")
	public void sjduochuComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sjduochuName = (String) request.getParameter("sjduochuName");
		String sjduochuId = (String) request.getParameter("sjduochuId");
		String sjduochuType = (String) request.getParameter("sjduochuType");
		String sjduochuType1 = (String) request.getParameter("sjduochuType1");
		String sjduochuType2 = (String) request.getParameter("sjduochuType2");
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
		Sjduochu sjduochu = new Sjduochu();
		try {
			if (StringUtil.isNotEmpty(sjduochuName)) {
				sjduochu.setSjduochuName(sjduochuName);
			}
			if (StringUtil.isNotEmpty(sjduochuId)) {
				sjduochu.setSjduochuId(Integer.parseInt(sjduochuId));
			}
			if (StringUtil.isNotEmpty(sjduochuType)) {
				sjduochu.setSjduochuType(Integer.parseInt(sjduochuType));
			}
			if (StringUtil.isNotEmpty(sjduochuType1)) {
				sjduochu.setSjduochuType1(Integer.parseInt(sjduochuType1));
			}
			if (StringUtil.isNotEmpty(sjduochuType2)) {
				sjduochu.setSjduochuType2(Integer.parseInt(sjduochuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjduochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjduochu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjduochu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjduochu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjduochu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjduochu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjduochu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjduochu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjduochu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjduochu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				sjduochu.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				sjduochu.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				sjduochu.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("sjduochuName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(sjduochuService.querySjduochus(sjduochu,
					0,0, null, null, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjduochuTongji")
	public void sjduochuTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String userId=request.getParameter("userId");
		String tijiaoUrl = "shujuTongji";
		List<Integer> shujuIds = new ArrayList<Integer>();
		List<String> tongjiNames = new ArrayList<String>();
		List<Double> tongjiZongshus = new ArrayList<Double>();
		List<Shuju> shujus = new ArrayList<Shuju>();
		List<Sjduochu> sjduochus = new ArrayList<Sjduochu>();
		Double zongshu = 0.0;
		Sjduochu sjduochu = new Sjduochu();
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
				Double sjduochuZongshu = 0.0;
				sjduochu.setShujuId(shujuIds.get(i));
				sjduochus = sjduochuService.querySjduochus(sjduochu, 0,0,sdate,edate, null, null);
				for(int j=0;j<sjduochus.size();j++){
					sjduochuZongshu = sjduochuZongshu + sjduochus.get(j).getSjduochuDouble2();
				}
				zongshu = zongshu + sjduochuZongshu;
				tongjiZongshus.add(sjduochuZongshu);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("tijiaoUrl", tijiaoUrl);
			session.setAttribute("tongjiNames", tongjiNames);
			session.setAttribute("tongjiZongshus", tongjiZongshus);
			session.setAttribute("zongshu", zongshu);
			response.sendRedirect("tongjitu/bingzhuangtu.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/shangchuanSjduochu")
	public void shangchuanSjduochu(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String sjduochuId = (String) request.getParameter("sjduochuId");
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
			Sjduochu sjduochu = sjduochuService.getSjduochu(Integer.parseInt(sjduochuId));
			sjduochu.setSjduochuImg(shangchuandizhi);
			sjduochu.setSjduochuImgName(shangchuanname);
			sjduochuService.modifySjduochu(sjduochu);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruSjduochu")
	public void daoruSjduochu(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
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
				String sjduochuName = row.get(0);
				String sjleixingId = row.get(1);
				String sjduochuZong = row.get(2);
				String sjduochuMark = row.get(3);
				String userId = row.get(4);
				Sjduochu sjduochu = new Sjduochu();
				
				if (StringUtil.isNotEmpty(sjduochuName)) {
					sjduochu.setSjduochuName(sjduochuName);
				}
				if (StringUtil.isNotEmpty(sjleixingId)) {
					sjduochu.setSjleixingId(Integer.parseInt(sjleixingId));
					Sjleixing sjleixing = new Sjleixing();
					sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
					sjduochu.setSjleixingName(sjleixing.getSjleixingName());
				}
				if (StringUtil.isNotEmpty(sjduochuZong)) {
					sjduochu.setSjduochuZong(Integer.parseInt(sjduochuZong));
				}
				if (StringUtil.isNotEmpty(sjduochuMark)) {
					sjduochu.setSjduochuMark(sjduochuMark);
				}
				if (StringUtil.isNotEmpty(userId)) {
					sjduochu.setUserId(Integer.parseInt(userId));
					User user = new User();
					user = userService.getUser(Integer.parseInt(userId));
					sjduochu.setUserName(user.getUserName());
					sjduochu.setBumenId(user.getBumenId());
					sjduochu.setBumenName(user.getBumenName());
					sjduochu.setBuyuanId(user.getBuyuanId());
					sjduochu.setBuyuanName(user.getBuyuanName());
					sjduochu.setBuzhiId(user.getBuzhiId());
					sjduochu.setBuzhiName(user.getBuzhiName());
				}
				Date date = new Date();
				sjduochu.setSjduochuDate(date);
				sjduochuService.save(sjduochu);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuSjduochu")
	public void daochuSjduochu(HttpServletRequest request, HttpServletResponse response)
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
			Sjduochu sjduochu = new Sjduochu();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				sjduochu = sjduochuService.getSjduochu(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(sjduochu.getSjduochuName());
				row.add(sjduochu.getSjleixingName());
				row.add(sjduochu.getSjduochuZong().toString());
				row.add(sjduochu.getSjduochuMark());
				row.add(sjduochu.getUserName());
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
