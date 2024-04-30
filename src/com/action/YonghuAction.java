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
public class YonghuAction {
	@Autowired
	private YonghuService yonghuService;
	@Autowired
	private UserService userService;
	@Autowired
	private BumenService bumenService;
	@Autowired
	private BuyuanService buyuanService;
	@Autowired
	private BuzhiService buzhiService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/getYonghus")
	public void getYonghus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String yonghuName = (String) request.getParameter("yonghuName");
		String yonghuXingming = (String) request.getParameter("yonghuXingming");
		String yonghuId = (String) request.getParameter("yonghuId");
		String yonghuType = (String) request.getParameter("yonghuType");
		String yonghuType1 = (String) request.getParameter("yonghuType1");
		String yonghuType2 = (String) request.getParameter("yonghuType2");
		String yhroleId = (String) request.getParameter("yhroleId");
		String byumenId = (String) request.getParameter("byumenId");
		String byuzhiId = (String) request.getParameter("byuzhiId");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String yonghuMinzu = (String) request.getParameter("yonghuMinzu");
		String yonghuSex = (String) request.getParameter("yonghuSex");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		String sdate = (String) request.getParameter("sdate");
		String edate = (String) request.getParameter("edate");
		String sdate1 = (String) request.getParameter("sdate1");
		String edate1 = (String) request.getParameter("edate1");
		Yonghu yonghu = new Yonghu();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {

			if (StringUtil.isNotEmpty(yonghuXingming)) {
				yonghu.setYonghuXingming(yonghuXingming);
			}
			if (StringUtil.isNotEmpty(yonghuName)) {
				yonghu.setYonghuName(yonghuName);
			}
			if (StringUtil.isNotEmpty(yonghuMinzu)) {
				yonghu.setYonghuMinzu(yonghuMinzu);
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yonghu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				yonghu.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				yonghu.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(yonghuType)) {
				yonghu.setYonghuType(Integer.parseInt(yonghuType));
			}
			if (StringUtil.isNotEmpty(yonghuType1)) {
				yonghu.setYonghuType1(Integer.parseInt(yonghuType1));
			}
			if (StringUtil.isNotEmpty(yonghuType2)) {
				yonghu.setYonghuType2(Integer.parseInt(yonghuType2));
			}
			if (StringUtil.isNotEmpty(yhroleId)) {
				yonghu.setYhroleId(Integer.parseInt(yhroleId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				yonghu.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(yonghuSex)) {
				yonghu.setYonghuSex(Integer.parseInt(yonghuSex));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yonghu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yonghu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yonghu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yonghu.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			JSONArray jsonArray = JSONArray.fromObject(yonghuService.queryYonghus(
					yonghu,null, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1));
			JSONObject result = new JSONObject();
			int total = yonghuService.queryYonghus(yonghu,null, 0,0, sdate, edate, sdate1, edate1).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addYonghu")
	public void addYonghu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String yonghuName = (String) request.getParameter("yonghuName");
			String yonghuPassword = (String) request.getParameter("yonghuPassword");
			String yonghuAge = (String) request.getParameter("yonghuAge");
			String yonghuXingming = (String) request.getParameter("yonghuXingming");
			String yonghuSex = (String) request.getParameter("yonghuSex");
			String yonghuPhone = (String) request.getParameter("yonghuPhone");
			String yonghuMark = (String) request.getParameter("yonghuMark");
			String yonghuMark1 = (String) request.getParameter("yonghuMark1");
			String yonghuMark2 = (String) request.getParameter("yonghuMark2");
			String yonghuMark3 = (String) request.getParameter("yonghuMark3");
			String yonghuMark4 = (String) request.getParameter("yonghuMark4");
			String yonghuDate = (String) request.getParameter("yonghuDate");
			String yonghuDate1 = (String) request.getParameter("yonghuDate1");
			String yonghuDate2 = (String) request.getParameter("yonghuDate2");
			String yonghuType = (String) request.getParameter("yonghuType");
			String yonghuType1 = (String) request.getParameter("yonghuType1");
			String yonghuType2 = (String) request.getParameter("yonghuType2");
			String yonghuZong = (String) request.getParameter("yonghuZong");
			String yonghuZong1 = (String) request.getParameter("yonghuZong1");
			String yonghuZong2 = (String) request.getParameter("yonghuZong2");
			String yonghuDouble = (String) request.getParameter("yonghuDouble");
			String yonghuDouble1 = (String) request.getParameter("yonghuDouble1");
			String yonghuDouble2 = (String) request.getParameter("yonghuDouble2");
			String yhroleId = (String) request.getParameter("yhroleId");
			String byumenId = (String) request.getParameter("byumenId");
			String byuyuanId = (String) request.getParameter("byuyuanId");
			String byuzhiId = (String) request.getParameter("byuzhiId");
			String userId = (String) request.getParameter("userId");
			String bumenId = (String) request.getParameter("bumenId");
			String buyuanId = (String) request.getParameter("buyuanId");
			String buzhiId = (String) request.getParameter("buzhiId");
			String yonghuId = (String) request.getParameter("yonghuId");
			Yonghu yonghu = new Yonghu();

			if (StringUtil.isNotEmpty(yonghuId)) {
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(yonghuName)) {
				yonghu.setYonghuName(yonghuName);
			}
			if (StringUtil.isNotEmpty(yonghuPassword)) {
				yonghu.setYonghuPassword(yonghuPassword);
			}
			if (StringUtil.isNotEmpty(yonghuAge)) {
				yonghu.setYonghuAge(Integer.parseInt(yonghuAge));
			}
			if (StringUtil.isNotEmpty(yonghuXingming)) {
				yonghu.setYonghuXingming(yonghuXingming);
			}
			if (StringUtil.isNotEmpty(yonghuSex)) {
				yonghu.setYonghuSex(Integer.parseInt(yonghuSex));
			}
			if (StringUtil.isNotEmpty(yonghuPhone)) {
				yonghu.setYonghuPhone(yonghuPhone);
			}
			if (StringUtil.isNotEmpty(yonghuMark)) {
				yonghu.setYonghuMark(yonghuMark);
			}
			if (StringUtil.isNotEmpty(yonghuMark1)) {
				yonghu.setYonghuMark1(yonghuMark1);
			}
			if (StringUtil.isNotEmpty(yonghuMark2)) {
				yonghu.setYonghuMark2(yonghuMark2);
			}
			if (StringUtil.isNotEmpty(yonghuMark3)) {
				yonghu.setYonghuMark3(yonghuMark3);
			}
			if (StringUtil.isNotEmpty(yonghuMark4)) {
				yonghu.setYonghuMark4(yonghuMark4);
			}
			if (StringUtil.isNotEmpty(yonghuDate)) {
				yonghu.setYonghuDate(DateUtil.formatString(yonghuDate,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuDate1)) {
				yonghu.setYonghuDate1(DateUtil.formatString(yonghuDate1,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuDate2)) {
				yonghu.setYonghuDate2(DateUtil.formatString(yonghuDate2,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuType)) {
				yonghu.setYonghuType(Integer.parseInt(yonghuType));
			}
			if (StringUtil.isNotEmpty(yonghuType1)) {
				yonghu.setYonghuType1(Integer.parseInt(yonghuType1));
			}
			if (StringUtil.isNotEmpty(yonghuType2)) {
				yonghu.setYonghuType2(Integer.parseInt(yonghuType2));
			}
			if (StringUtil.isNotEmpty(yonghuZong)) {
				yonghu.setYonghuZong(Integer.parseInt(yonghuZong));
			}
			if (StringUtil.isNotEmpty(yonghuZong1)) {
				yonghu.setYonghuZong1(Integer.parseInt(yonghuZong1));
			}
			if (StringUtil.isNotEmpty(yonghuZong2)) {
				yonghu.setYonghuZong2(Integer.parseInt(yonghuZong2));
			}
			if (StringUtil.isNotEmpty(yonghuDouble)) {
				yonghu.setYonghuDouble(Double.parseDouble(yonghuDouble));
			}
			if (StringUtil.isNotEmpty(yonghuDouble1)) {
				yonghu.setYonghuDouble1(Double.parseDouble(yonghuDouble1));
			}
			if (StringUtil.isNotEmpty(yonghuDouble2)) {
				yonghu.setYonghuDouble2(Double.parseDouble(yonghuDouble2));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yonghu.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				yonghu.setUserName(user.getUserName());
				yonghu.setBumenId(user.getBumenId());
				yonghu.setBumenName(user.getBumenName());
				yonghu.setBuyuanId(user.getBuyuanId());
				yonghu.setBuyuanName(user.getBuyuanName());
				yonghu.setBuzhiId(user.getBuzhiId());
				yonghu.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yonghu.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				yonghu.setBuzhiName(buzhi.getBuzhiName());
				yonghu.setBumenId(buzhi.getBumenId());
				yonghu.setBumenName(buzhi.getBumenName());
				yonghu.setBuyuanId(buzhi.getBuyuanId());
				yonghu.setBuyuanName(buzhi.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yonghu.setBumenId(Integer.parseInt(bumenId));
				Bumen bumen = new Bumen();
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
				yonghu.setBumenName(bumen.getBumenName());
				yonghu.setBuyuanId(bumen.getBuyuanId());
				yonghu.setBuyuanName(bumen.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yonghu.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				yonghu.setBuyuanName(buyuan.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yonghuService.modifyYonghu(yonghu);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				int total = yonghuService.queryYonghus(null, yonghuName, 0, 0, null, null, null, null).size();
				if (total==0) {
					Date date = new Date();
					yonghu.setYonghuDate(date);
					yonghu.setYonghuType(0);
					yonghuService.save(yonghu);
					result.put("success", "true");
					ResponseUtil.write(response, result);
				} else {
					result.put("success", "true");
					result.put("errorMsg", "用户名重复！");
					ResponseUtil.write(response, result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/mimaYonghu")
	public void mimaYonghu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String yonghuId = (String) request.getParameter("yonghuId");
		String yonghuPassword = (String) request.getParameter("yonghuPassword");
		String yonghuPassword1 = (String) request.getParameter("yonghuPassword1");
		Yonghu yonghu = new Yonghu();
		try {
			yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
			if (!yonghu.getYonghuPassword().equals(yonghuPassword)) {
				request.setAttribute("error", "原密码错误，请重新输入！");
				request.getRequestDispatcher("yonghu/yonghumima.jsp").forward(request,
						response);
			}else{
				yonghu.setYonghuPassword(yonghuPassword1);
				yonghuService.modifyYonghu(yonghu);
				request.setAttribute("error", "密码修改成功！");
				request.getRequestDispatcher("yonghu/yonghumima.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/zhaohuiYonghu")
	public void zhaohuiYonghu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String yonghuName = (String) request.getParameter("yonghuName");
			String yonghuPhone = (String) request.getParameter("yonghuPhone");
			Yonghu yonghu = new Yonghu();

			if (StringUtil.isNotEmpty(yonghuName)) {
				yonghu.setYonghuName(yonghuName);
			}
			List<Yonghu> yonghus = yonghuService.queryYonghus(yonghu, null, 0,0, null, null, null, null);
			if(yonghus.size()==0){
				request.setAttribute("error", "无此用户名请重新找回！");
				request.getRequestDispatcher("yonghu/zhaohuiyonghu.jsp").forward(request, response);
			}else{
				yonghu = yonghus.get(0);
				if(yonghu.getYonghuPhone().equals(yonghuPhone)){
					String yonghuPassword = yonghu.getYonghuPassword();
					request.setAttribute("error", "密码是："+yonghuPassword);
					request.getRequestDispatcher("yonghu/zhaohuiyonghu.jsp").forward(request, response);
				}else{
					request.setAttribute("error", "用户名和电话不匹配，请重新找回！");
					request.getRequestDispatcher("yonghu/zhaohuiyonghu.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "系统错误联系管理员！");
			request.getRequestDispatcher("yonghu/zhaohuiyonghu.jsp").forward(request, response);
		}
	}

	@RequestMapping("/zhuceYonghu")
	public void zhuceYonghu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {

			String yonghuName = (String) request.getParameter("yonghuName");
			String yonghuPassword = (String) request.getParameter("yonghuPassword");
			String yonghuAge = (String) request.getParameter("yonghuAge");
			String yonghuXingming = (String) request.getParameter("yonghuXingming");
			String yonghuSex = (String) request.getParameter("yonghuSex");
			String yonghuPhone = (String) request.getParameter("yonghuPhone");
			String yonghuMark = (String) request.getParameter("yonghuMark");
			String yonghuMark1 = (String) request.getParameter("yonghuMark1");
			String yonghuMark2 = (String) request.getParameter("yonghuMark2");
			String yonghuMark3 = (String) request.getParameter("yonghuMark3");
			String yonghuMark4 = (String) request.getParameter("yonghuMark4");
			String yonghuDate = (String) request.getParameter("yonghuDate");
			String yonghuDate1 = (String) request.getParameter("yonghuDate1");
			String yonghuDate2 = (String) request.getParameter("yonghuDate2");
			String yonghuType = (String) request.getParameter("yonghuType");
			String yonghuType1 = (String) request.getParameter("yonghuType1");
			String yonghuType2 = (String) request.getParameter("yonghuType2");
			String yonghuZong = (String) request.getParameter("yonghuZong");
			String yonghuZong1 = (String) request.getParameter("yonghuZong1");
			String yonghuZong2 = (String) request.getParameter("yonghuZong2");
			String yonghuDouble = (String) request.getParameter("yonghuDouble");
			String yonghuDouble1 = (String) request.getParameter("yonghuDouble1");
			String yonghuDouble2 = (String) request.getParameter("yonghuDouble2");
			String yhroleId = (String) request.getParameter("yhroleId");
			String byumenId = (String) request.getParameter("byumenId");
			String byuyuanId = (String) request.getParameter("byuyuanId");
			String byuzhiId = (String) request.getParameter("byuzhiId");
			String userId = (String) request.getParameter("userId");
			String bumenId = (String) request.getParameter("bumenId");
			String buyuanId = (String) request.getParameter("buyuanId");
			String buzhiId = (String) request.getParameter("buzhiId");
			String yonghuId = (String) request.getParameter("yonghuId");
			Yonghu yonghu = new Yonghu();

			if (StringUtil.isNotEmpty(yonghuId)) {
				yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(yonghuName)) {
				yonghu.setYonghuName(yonghuName);
			}
			if (StringUtil.isNotEmpty(yonghuPassword)) {
				yonghu.setYonghuPassword(yonghuPassword);
			}
			if (StringUtil.isNotEmpty(yonghuAge)) {
				yonghu.setYonghuAge(Integer.parseInt(yonghuAge));
			}
			if (StringUtil.isNotEmpty(yonghuXingming)) {
				yonghu.setYonghuXingming(yonghuXingming);
			}
			if (StringUtil.isNotEmpty(yonghuSex)) {
				yonghu.setYonghuSex(Integer.parseInt(yonghuSex));
			}
			if (StringUtil.isNotEmpty(yonghuPhone)) {
				yonghu.setYonghuPhone(yonghuPhone);
			}
			if (StringUtil.isNotEmpty(yonghuMark)) {
				yonghu.setYonghuMark(yonghuMark);
			}
			if (StringUtil.isNotEmpty(yonghuMark1)) {
				yonghu.setYonghuMark1(yonghuMark1);
			}
			if (StringUtil.isNotEmpty(yonghuMark2)) {
				yonghu.setYonghuMark2(yonghuMark2);
			}
			if (StringUtil.isNotEmpty(yonghuMark3)) {
				yonghu.setYonghuMark3(yonghuMark3);
			}
			if (StringUtil.isNotEmpty(yonghuMark4)) {
				yonghu.setYonghuMark4(yonghuMark4);
			}
			if (StringUtil.isNotEmpty(yonghuDate)) {
				yonghu.setYonghuDate(DateUtil.formatString(yonghuDate,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuDate1)) {
				yonghu.setYonghuDate1(DateUtil.formatString(yonghuDate1,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuDate2)) {
				yonghu.setYonghuDate2(DateUtil.formatString(yonghuDate2,
						"yyyy-MM-dd hh:mm:ss"));
			}
			if (StringUtil.isNotEmpty(yonghuType)) {
				yonghu.setYonghuType(Integer.parseInt(yonghuType));
			}
			if (StringUtil.isNotEmpty(yonghuType1)) {
				yonghu.setYonghuType1(Integer.parseInt(yonghuType1));
			}
			if (StringUtil.isNotEmpty(yonghuType2)) {
				yonghu.setYonghuType2(Integer.parseInt(yonghuType2));
			}
			if (StringUtil.isNotEmpty(yonghuZong)) {
				yonghu.setYonghuZong(Integer.parseInt(yonghuZong));
			}
			if (StringUtil.isNotEmpty(yonghuZong1)) {
				yonghu.setYonghuZong1(Integer.parseInt(yonghuZong1));
			}
			if (StringUtil.isNotEmpty(yonghuZong2)) {
				yonghu.setYonghuZong2(Integer.parseInt(yonghuZong2));
			}
			if (StringUtil.isNotEmpty(yonghuDouble)) {
				yonghu.setYonghuDouble(Double.parseDouble(yonghuDouble));
			}
			if (StringUtil.isNotEmpty(yonghuDouble1)) {
				yonghu.setYonghuDouble1(Double.parseDouble(yonghuDouble1));
			}
			if (StringUtil.isNotEmpty(yonghuDouble2)) {
				yonghu.setYonghuDouble2(Double.parseDouble(yonghuDouble2));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yonghu.setUserId(Integer.parseInt(userId));
				User user = new User();
				user = userService.getUser(Integer.parseInt(userId));
				yonghu.setUserName(user.getUserName());
				yonghu.setBumenId(user.getBumenId());
				yonghu.setBumenName(user.getBumenName());
				yonghu.setBuyuanId(user.getBuyuanId());
				yonghu.setBuyuanName(user.getBuyuanName());
				yonghu.setBuzhiId(user.getBuzhiId());
				yonghu.setBuzhiName(user.getBuzhiName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yonghu.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				yonghu.setBuzhiName(buzhi.getBuzhiName());
				yonghu.setBumenId(buzhi.getBumenId());
				yonghu.setBumenName(buzhi.getBumenName());
				yonghu.setBuyuanId(buzhi.getBuyuanId());
				yonghu.setBuyuanName(buzhi.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yonghu.setBumenId(Integer.parseInt(bumenId));
				Bumen bumen = new Bumen();
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
				yonghu.setBumenName(bumen.getBumenName());
				yonghu.setBuyuanId(bumen.getBuyuanId());
				yonghu.setBuyuanName(bumen.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yonghu.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				yonghu.setBuyuanName(buyuan.getBuyuanName());
			}
			int total = yonghuService.queryYonghus(null, yonghuName, 0, 0, null, null, null, null).size();
			if (total==0) {
				Date date = new Date();
				yonghu.setYonghuDate(date);
				yonghu.setYonghuZong(0);
				yonghuService.save(yonghu);
				request.setAttribute("error", "注册成功，请登录！");
				request.getRequestDispatcher("shouye/index.jsp").forward(request,
						response);
			} else {
				request.setAttribute("error", "用户名重复，请重新输入！");
				request.getRequestDispatcher("yonghu/zhuceyonghu.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteYonghu")
	public void deleteYonghu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				yonghuService.deleteYonghu(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/yonghuComboList")
	public void yonghuComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String yonghuName = (String) request.getParameter("yonghuName");
		String yonghuXingming = (String) request.getParameter("yonghuXingming");
		String yonghuId = (String) request.getParameter("yonghuId");
		String yonghuType = (String) request.getParameter("yonghuType");
		String yonghuType1 = (String) request.getParameter("yonghuType1");
		String yonghuType2 = (String) request.getParameter("yonghuType2");
		String yhroleId = (String) request.getParameter("yhroleId");
		String byumenId = (String) request.getParameter("byumenId");
		String byuzhiId = (String) request.getParameter("byuzhiId");
		String byuyuanId = (String) request.getParameter("byuyuanId");
		String yonghuMinzu = (String) request.getParameter("yonghuMinzu");
		String yonghuSex = (String) request.getParameter("yonghuSex");
		String userId = (String) request.getParameter("userId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		Yonghu yonghu = new Yonghu();
		try {
			if (StringUtil.isNotEmpty(yonghuXingming)) {
				yonghu.setYonghuXingming(yonghuXingming);
			}
			if (StringUtil.isNotEmpty(yonghuName)) {
				yonghu.setYonghuName(yonghuName);
			}
			if (StringUtil.isNotEmpty(yonghuMinzu)) {
				yonghu.setYonghuMinzu(yonghuMinzu);
			}
			if (StringUtil.isNotEmpty(yonghuId)) {
				yonghu.setYonghuId(Integer.parseInt(yonghuId));
			}
			if (StringUtil.isNotEmpty(byuzhiId)) {
				yonghu.setByuzhiId(Integer.parseInt(byuzhiId));
			}
			if (StringUtil.isNotEmpty(byuyuanId)) {
				yonghu.setByuyuanId(Integer.parseInt(byuyuanId));
			}
			if (StringUtil.isNotEmpty(yonghuType)) {
				yonghu.setYonghuType(Integer.parseInt(yonghuType));
			}
			if (StringUtil.isNotEmpty(yonghuType1)) {
				yonghu.setYonghuType1(Integer.parseInt(yonghuType1));
			}
			if (StringUtil.isNotEmpty(yonghuType2)) {
				yonghu.setYonghuType2(Integer.parseInt(yonghuType2));
			}
			if (StringUtil.isNotEmpty(yhroleId)) {
				yonghu.setYhroleId(Integer.parseInt(yhroleId));
			}
			if (StringUtil.isNotEmpty(byumenId)) {
				yonghu.setByumenId(Integer.parseInt(byumenId));
			}
			if (StringUtil.isNotEmpty(yonghuSex)) {
				yonghu.setYonghuSex(Integer.parseInt(yonghuSex));
			}
			if (StringUtil.isNotEmpty(userId)) {
				yonghu.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				yonghu.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				yonghu.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				yonghu.setBumenId(Integer.parseInt(bumenId));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("yonghuName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(yonghuService.queryYonghus(yonghu,
					null, 0,0, null, null, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/yonghuTongji")
	public void yonghuTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String tijiaoUrl = "yonghuTongji";
		List<Integer> bumenIds = new ArrayList<Integer>();
		List<String> tongjiNames = new ArrayList<String>();
		List<Double> tongjiZongshus = new ArrayList<Double>();
		List<Bumen> bumens = new ArrayList<Bumen>();
		List<Yonghu> yonghus = new ArrayList<Yonghu>();
		Double zongshu = 0.0;
		try {
			bumens = bumenService.queryBumens(null, 0,0);
			for(int i=0;i<bumens.size();i++){
				bumenIds.add(bumens.get(i).getBumenId());
				tongjiNames.add(bumens.get(i).getBumenName());
			}
			for(int i=0;i<bumenIds.size();i++){
				Double yonghuZongshu = 0.0;
				Yonghu yonghu = new Yonghu();
				yonghu.setBumenId(bumenIds.get(i));
				yonghus = yonghuService.queryYonghus(yonghu, null,0,0,sdate,edate, null, null);
				for(int j=0;j<yonghus.size();j++){
					yonghuZongshu = yonghuZongshu + yonghus.size();
				}
				zongshu = zongshu + yonghuZongshu;
				tongjiZongshus.add(yonghuZongshu);
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

	@RequestMapping("/shangchuanYonghu")
	public void shangchuanYonghu(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String yonghuId = (String) request.getParameter("yonghuId");
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
			Yonghu yonghu = yonghuService.getYonghu(Integer.parseInt(yonghuId));
			yonghu.setYonghuImg(shangchuandizhi);
			yonghu.setYonghuImgName(shangchuanname);
			yonghuService.modifyYonghu(yonghu);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruYonghu")
	public void daoruYonghu(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
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
				String yonghuName = row.get(0);
				String yonghuPassword = row.get(1);
				String yonghuXingming = row.get(2);
				String yonghuSex = row.get(3);
				String yonghuAge = row.get(4);
				String yonghuMinzu = row.get(5);
				String yonghuPhone = row.get(6);
				String byuzhiId = row.get(7);
				Yonghu yonghu = new Yonghu();
				
				if (StringUtil.isNotEmpty(yonghuName)) {
					yonghu.setYonghuName(yonghuName);
				}
				if (StringUtil.isNotEmpty(yonghuPassword)) {
					yonghu.setYonghuPassword(yonghuPassword);
				}
				if (StringUtil.isNotEmpty(yonghuAge)) {
					yonghu.setYonghuAge(Integer.parseInt(yonghuAge));
				}
				if (StringUtil.isNotEmpty(yonghuXingming)) {
					yonghu.setYonghuXingming(yonghuXingming);
				}
				if (StringUtil.isNotEmpty(yonghuSex)) {
					if(yonghuSex.equals("男")){
						yonghu.setYonghuSex(0);
					}else if(yonghuSex.equals("女")){
						yonghu.setYonghuSex(1);
					}else{
						yonghu.setYonghuSex(0);
					}
				}
				if (StringUtil.isNotEmpty(yonghuMinzu)) {
					yonghu.setYonghuMinzu(yonghuMinzu);
				}
				if (StringUtil.isNotEmpty(yonghuPhone)) {
					yonghu.setYonghuPhone(yonghuPhone);
				}
				Date date = new Date();
				yonghu.setYonghuDate(date);
				yonghuService.save(yonghu);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuYonghu")
	public void daochuYonghu(HttpServletRequest request, HttpServletResponse response)
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
			Yonghu yonghu = new Yonghu();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				yonghu = yonghuService.getYonghu(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(yonghu.getYonghuXingming());
				row.add(yonghu.getByuzhiName());
				row.add(yonghu.getYonghuPhone());
				row.add(yonghu.getYonghuAge().toString());
				if(yonghu.getYonghuSex()==0){
					row.add("男");
				}else{
					row.add("女");
				}
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
