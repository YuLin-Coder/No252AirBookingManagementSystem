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
public class SjshaochuAction {
	@Autowired
	private SjshaochuService sjshaochuService;
	@Autowired
	private SjqitaService sjqitaService;
	@Autowired
	private SjlaiyuanService sjlaiyuanService;
	@Autowired
	private SjleixingService sjleixingService;
	@Autowired
	private ShujuService shujuService;
	@Autowired
	private SjduochuService sjduochuService;
	@Autowired
	private UserService userService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/getSjshaochus")
	public void getSjshaochus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String sjshaochuName = (String) request.getParameter("sjshaochuName");
		String sjshaochuId = (String) request.getParameter("sjshaochuId");
		String sjshaochuType = (String) request.getParameter("sjshaochuType");
		String sjshaochuType1 = (String) request.getParameter("sjshaochuType1");
		String sjshaochuType2 = (String) request.getParameter("sjshaochuType2");
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
		Sjshaochu sjshaochu = new Sjshaochu();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(sjshaochuName)) {
				sjshaochu.setSjshaochuName(sjshaochuName);
			}
			if (StringUtil.isNotEmpty(sjshaochuId)) {
				sjshaochu.setSjshaochuId(Integer.parseInt(sjshaochuId));
			}
			if (StringUtil.isNotEmpty(sjshaochuType)) {
				sjshaochu.setSjshaochuType(Integer.parseInt(sjshaochuType));
			}
			if (StringUtil.isNotEmpty(sjshaochuType1)) {
				sjshaochu.setSjshaochuType1(Integer.parseInt(sjshaochuType1));
			}
			if (StringUtil.isNotEmpty(sjshaochuType2)) {
				sjshaochu.setSjshaochuType2(Integer.parseInt(sjshaochuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjshaochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjshaochu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjshaochu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjshaochu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjshaochu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjshaochu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjshaochu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjshaochu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjshaochu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjshaochu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				sjshaochu.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				sjshaochu.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				sjshaochu.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			JSONArray jsonArray = JSONArray.fromObject(sjshaochuService.querySjshaochus(
					sjshaochu, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1));
			JSONObject result = new JSONObject();
			int total = sjshaochuService.querySjshaochus(sjshaochu, 0,0, sdate, edate, sdate1, edate1).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addSjshaochu")
	public void addSjshaochu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String sjshaochuName = (String) request.getParameter("sjshaochuName");
			String sjshaochuMark = (String) request.getParameter("sjshaochuMark");
			String sjshaochuMark1 = (String) request.getParameter("sjshaochuMark1");
			String sjshaochuMark2 = (String) request.getParameter("sjshaochuMark2");
			String sjshaochuMark3 = (String) request.getParameter("sjshaochuMark3");
			String sjshaochuDate = (String) request.getParameter("sjshaochuDate");
			String sjshaochuDate1 = (String) request.getParameter("sjshaochuDate1");
			String sjshaochuZong = (String) request.getParameter("sjshaochuZong");
			String sjshaochuZong1 = (String) request.getParameter("sjshaochuZong1");
			String sjshaochuZong2 = (String) request.getParameter("sjshaochuZong2");
			String sjshaochuDouble = (String) request.getParameter("sjshaochuDouble");
			String sjshaochuDouble1 = (String) request.getParameter("sjshaochuDouble1");
			String sjshaochuDouble2 = (String) request.getParameter("sjshaochuDouble2");
			String sjshaochuType = (String) request.getParameter("sjshaochuType");
			String sjshaochuType1 = (String) request.getParameter("sjshaochuType1");
			String sjshaochuType2 = (String) request.getParameter("sjshaochuType2");
			String shujuId = (String) request.getParameter("shujuId");
			String sjqitaId = (String) request.getParameter("sjqitaId");
			String sjlaiyuanId = (String) request.getParameter("sjlaiyuanId");
			String yonghuId = (String) request.getParameter("yonghuId");
			String userId = (String) request.getParameter("userId");
			String sjshaochuId = (String) request.getParameter("sjshaochuId");
			Sjshaochu sjshaochu = new Sjshaochu();

			if (StringUtil.isNotEmpty(sjshaochuId)) {
				sjshaochu = sjshaochuService.getSjshaochu(Integer.parseInt(sjshaochuId));
			}
			if (StringUtil.isNotEmpty(sjshaochuName)) {
				sjshaochu.setSjshaochuName(sjshaochuName);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark)) {
				sjshaochu.setSjshaochuMark(sjshaochuMark);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark1)) {
				sjshaochu.setSjshaochuMark1(sjshaochuMark1);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark2)) {
				sjshaochu.setSjshaochuMark2(sjshaochuMark2);
			}
			if (StringUtil.isNotEmpty(sjshaochuMark3)) {
				sjshaochu.setSjshaochuMark3(sjshaochuMark3);
			}
			if (StringUtil.isNotEmpty(sjshaochuDate)) {
				sjshaochu.setSjshaochuDate(DateUtil.formatString(sjshaochuDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjshaochuDate1)) {
				sjshaochu.setSjshaochuDate1(DateUtil.formatString(sjshaochuDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjshaochuZong)) {
				sjshaochu.setSjshaochuZong(Integer.parseInt(sjshaochuZong));
			}
			if (StringUtil.isNotEmpty(sjshaochuZong1)) {
				sjshaochu.setSjshaochuZong1(Integer.parseInt(sjshaochuZong1));
			}
			if (StringUtil.isNotEmpty(sjshaochuZong2)) {
				sjshaochu.setSjshaochuZong2(Integer.parseInt(sjshaochuZong2));
			}
			if (StringUtil.isNotEmpty(sjshaochuDouble)) {
				sjshaochu.setSjshaochuDouble(Double.parseDouble(sjshaochuDouble));
			}
			if (StringUtil.isNotEmpty(sjshaochuDouble1)) {
				sjshaochu.setSjshaochuDouble1(Double.parseDouble(sjshaochuDouble1));
			}
			if (StringUtil.isNotEmpty(sjshaochuDouble2)) {
				sjshaochu.setSjshaochuDouble2(Double.parseDouble(sjshaochuDouble2));
			}
			if (StringUtil.isNotEmpty(sjshaochuType)) {
				sjshaochu.setSjshaochuType(Integer.parseInt(sjshaochuType));
			}
			if (StringUtil.isNotEmpty(sjshaochuType1)) {
				sjshaochu.setSjshaochuType1(Integer.parseInt(sjshaochuType1));
			}
			if (StringUtil.isNotEmpty(sjshaochuType2)) {
				sjshaochu.setSjshaochuType2(Integer.parseInt(sjshaochuType2));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjshaochu.setShujuId(Integer.parseInt(shujuId));
				Shuju shuju = new Shuju();
				shuju = shujuService.getShuju(Integer.parseInt(shujuId));
				sjshaochu.setShujuName(shuju.getShujuName());
				sjshaochu.setSjleixingId(shuju.getSjleixingId());
				sjshaochu.setSjleixingName(shuju.getSjleixingName());
				sjshaochu.setSjxingtaiId(shuju.getSjxingtaiId());
				sjshaochu.setSjxingtaiName(shuju.getSjxingtaiName());
				sjshaochu.setYonghuId(shuju.getYonghuId());
				sjshaochu.setYonghuName(shuju.getYonghuName());
				sjshaochu.setByumenId(shuju.getByumenId());
				sjshaochu.setByumenName(shuju.getByumenName());
				sjshaochu.setByuyuanId(shuju.getByuyuanId());
				sjshaochu.setByuyuanName(shuju.getByuyuanName());
				sjshaochu.setByuzhiId(shuju.getByuzhiId());
				sjshaochu.setByuzhiName(shuju.getByuzhiName());
				sjshaochu.setUserId(shuju.getUserId());
				sjshaochu.setUserName(shuju.getUserName());
				sjshaochu.setBumenId(shuju.getBumenId());
				sjshaochu.setBumenName(shuju.getBumenName());
				sjshaochu.setBuyuanId(shuju.getBuyuanId());
				sjshaochu.setBuyuanName(shuju.getBuyuanName());
				sjshaochu.setBuzhiId(shuju.getBuzhiId());
				sjshaochu.setBuzhiName(shuju.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjshaochu.setSjqitaId(Integer.parseInt(sjqitaId));
				Sjqita sjqita = new Sjqita();
				sjqita = sjqitaService.getSjqita(Integer.parseInt(sjqitaId));
				sjshaochu.setSjqitaName(sjqita.getSjqitaName());
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjshaochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
				Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
				sjlaiyuan = sjlaiyuanService.getSjlaiyuan(Integer.parseInt(sjlaiyuanId));
				sjshaochu.setSjlaiyuanName(sjlaiyuan.getSjlaiyuanName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjshaochu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjshaochu.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				sjshaochu.setUserName(user.getUserName());
				sjshaochu.setBumenId(user.getBumenId());
				sjshaochu.setBumenName(user.getBumenName());
				sjshaochu.setBuyuanId(user.getBuyuanId());
				sjshaochu.setBuyuanName(user.getBuyuanName());
				sjshaochu.setBuzhiId(user.getBuzhiId());
				sjshaochu.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(sjshaochuId)) {
				sjshaochuService.modifySjshaochu(sjshaochu);
				if(sjshaochu.getSjshaochuType2()==1){
					Sjduochu sjduochu = new Sjduochu();
					sjduochu = sjduochuService.getSjduochu(sjshaochu.getSjshaochuType1());
					sjduochu.setSjduochuZong1(sjduochu.getSjduochuZong1()+sjshaochu.getSjshaochuZong());
					sjduochu.setSjduochuZong2(sjduochu.getSjduochuZong2()-sjshaochu.getSjshaochuZong());
					sjduochu.setSjduochuDouble2((double)sjduochu.getSjduochuZong1()/sjduochu.getSjduochuZong());
					sjduochu.setSjduochuDouble2(TypeUtil.liangweiDouble(sjduochu.getSjduochuDouble2()));
					sjduochuService.modifySjduochu(sjduochu);
				}
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Sjduochu sjduochu = new Sjduochu();
				sjduochu = sjduochuService.getSjduochu(sjshaochu.getSjshaochuType1());
				if(sjduochu.getSjduochuZong2().intValue()>=sjshaochu.getSjshaochuZong().intValue()){
					sjshaochu.setSjshaochuName(sjduochu.getSjduochuName());
					sjshaochu.setSjshaochuMark(sjduochu.getSjduochuMark());
					sjshaochu.setShujuId(sjduochu.getShujuId());
					sjshaochu.setShujuName(sjduochu.getShujuName());
					sjshaochu.setSjleixingId(sjduochu.getSjleixingId());
					sjshaochu.setSjleixingName(sjduochu.getSjleixingName());
					sjshaochu.setSjxingtaiId(sjduochu.getSjxingtaiId());
					sjshaochu.setSjxingtaiName(sjduochu.getSjxingtaiName());
					sjshaochu.setSjshaochuDouble(sjduochu.getSjduochuDouble());
					sjshaochu.setSjshaochuDouble1(sjshaochu.getSjshaochuDouble()*sjshaochu.getSjshaochuZong());
					Date date = new Date();
					sjshaochu.setSjshaochuDate(date);
					sjshaochu.setSjshaochuType(0);
					sjshaochu.setSjshaochuType2(0);
					sjshaochuService.save(sjshaochu);
					result.put("success", "true");
					ResponseUtil.write(response, result);
				}else{
					result.put("success", "true");
					result.put("errorMsg", "座位不足！");
					ResponseUtil.write(response, result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteSjshaochu")
	public void deleteSjshaochu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjshaochuService.deleteSjshaochu(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjshaochuComboList")
	public void sjshaochuComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sjshaochuName = (String) request.getParameter("sjshaochuName");
		String sjshaochuId = (String) request.getParameter("sjshaochuId");
		String sjshaochuType = (String) request.getParameter("sjshaochuType");
		String sjshaochuType1 = (String) request.getParameter("sjshaochuType1");
		String sjshaochuType2 = (String) request.getParameter("sjshaochuType2");
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
		Sjshaochu sjshaochu = new Sjshaochu();
		try {
			if (StringUtil.isNotEmpty(sjshaochuName)) {
				sjshaochu.setSjshaochuName(sjshaochuName);
			}
			if (StringUtil.isNotEmpty(sjshaochuId)) {
				sjshaochu.setSjshaochuId(Integer.parseInt(sjshaochuId));
			}
			if (StringUtil.isNotEmpty(sjshaochuType)) {
				sjshaochu.setSjshaochuType(Integer.parseInt(sjshaochuType));
			}
			if (StringUtil.isNotEmpty(sjshaochuType1)) {
				sjshaochu.setSjshaochuType1(Integer.parseInt(sjshaochuType1));
			}
			if (StringUtil.isNotEmpty(sjshaochuType2)) {
				sjshaochu.setSjshaochuType2(Integer.parseInt(sjshaochuType2));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjshaochu.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjshaochu.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(shujuId)) {
				sjshaochu.setShujuId(Integer.parseInt(shujuId));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjshaochu.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjshaochu.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(userId)) {
				sjshaochu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				sjshaochu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				sjshaochu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				sjshaochu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				sjshaochu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				sjshaochu.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				sjshaochu.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				sjshaochu.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("sjshaochuName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(sjshaochuService.querySjshaochus(sjshaochu,
					0,0, null, null, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjshaochuTongji")
	public void sjshaochuTongji(HttpServletRequest request, HttpServletResponse response)
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
		List<Sjshaochu> sjshaochus = new ArrayList<Sjshaochu>();
		Double zongshu = 0.0;
		Sjshaochu sjshaochu = new Sjshaochu();
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
				Double sjshaochuZongshu = 0.0;
				sjshaochu.setShujuId(shujuIds.get(i));
				sjshaochus = sjshaochuService.querySjshaochus(sjshaochu, 0,0,sdate,edate, null, null);
				for(int j=0;j<sjshaochus.size();j++){
					sjshaochuZongshu = sjshaochuZongshu + sjshaochus.get(j).getSjshaochuDouble2();
				}
				zongshu = zongshu + sjshaochuZongshu;
				tongjiZongshus.add(sjshaochuZongshu);
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

	@RequestMapping("/shangchuanSjshaochu")
	public void shangchuanSjshaochu(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String sjshaochuId = (String) request.getParameter("sjshaochuId");
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
			Sjshaochu sjshaochu = sjshaochuService.getSjshaochu(Integer.parseInt(sjshaochuId));
			sjshaochu.setSjshaochuImg(shangchuandizhi);
			sjshaochu.setSjshaochuImgName(shangchuanname);
			sjshaochuService.modifySjshaochu(sjshaochu);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruSjshaochu")
	public void daoruSjshaochu(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
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
				String sjshaochuName = row.get(0);
				String sjleixingId = row.get(1);
				String sjshaochuZong = row.get(2);
				String sjshaochuMark = row.get(3);
				String userId = row.get(4);
				Sjshaochu sjshaochu = new Sjshaochu();
				
				if (StringUtil.isNotEmpty(sjshaochuName)) {
					sjshaochu.setSjshaochuName(sjshaochuName);
				}
				if (StringUtil.isNotEmpty(sjleixingId)) {
					sjshaochu.setSjleixingId(Integer.parseInt(sjleixingId));
					Sjleixing sjleixing = new Sjleixing();
					sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
					sjshaochu.setSjleixingName(sjleixing.getSjleixingName());
				}
				if (StringUtil.isNotEmpty(sjshaochuZong)) {
					sjshaochu.setSjshaochuZong(Integer.parseInt(sjshaochuZong));
				}
				if (StringUtil.isNotEmpty(sjshaochuMark)) {
					sjshaochu.setSjshaochuMark(sjshaochuMark);
				}
				if (StringUtil.isNotEmpty(userId)) {
					sjshaochu.setUserId(Integer.parseInt(userId));
					User user = new User();
					user = userService.getUser(Integer.parseInt(userId));
					sjshaochu.setUserName(user.getUserName());
					sjshaochu.setBumenId(user.getBumenId());
					sjshaochu.setBumenName(user.getBumenName());
					sjshaochu.setBuyuanId(user.getBuyuanId());
					sjshaochu.setBuyuanName(user.getBuyuanName());
					sjshaochu.setBuzhiId(user.getBuzhiId());
					sjshaochu.setBuzhiName(user.getBuzhiName());
				}
				Date date = new Date();
				sjshaochu.setSjshaochuDate(date);
				sjshaochuService.save(sjshaochu);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuSjshaochu")
	public void daochuSjshaochu(HttpServletRequest request, HttpServletResponse response)
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
			Sjshaochu sjshaochu = new Sjshaochu();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				sjshaochu = sjshaochuService.getSjshaochu(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(sjshaochu.getSjshaochuName());
				row.add(sjshaochu.getSjleixingName());
				row.add(sjshaochu.getSjshaochuZong().toString());
				row.add(sjshaochu.getSjshaochuMark());
				row.add(sjshaochu.getUserName());
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
