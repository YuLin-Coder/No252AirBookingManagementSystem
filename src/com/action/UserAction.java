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
public class UserAction {
	@Autowired
	private UserService userService;
	@Autowired
	private BumenService bumenService;
	@Autowired
	private BuyuanService buyuanService;
	@Autowired
	private BuzhiService buzhiService;
	@Autowired
	private RoleService roleService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/getUsers")
	public void getUsers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String userName = (String) request.getParameter("userName");
		String userXingming = (String) request.getParameter("userXingming");
		String userId = (String) request.getParameter("userId");
		String userMinzu = (String) request.getParameter("userMinzu");
		String userType = (String) request.getParameter("userType");
		String userType1 = (String) request.getParameter("userType1");
		String userType2 = (String) request.getParameter("userType2");
		String roleId = (String) request.getParameter("roleId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		String userSex = (String) request.getParameter("userSex");
		String sdate = (String) request.getParameter("sdate");
		String edate = (String) request.getParameter("edate");
		String sdate1 = (String) request.getParameter("sdate1");
		String edate1 = (String) request.getParameter("edate1");
		User user = new User();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {

			if (StringUtil.isNotEmpty(userXingming)) {
				user.setUserXingming(userXingming);
			}
			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			if (StringUtil.isNotEmpty(userId)) {
				user.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				user.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				user.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(userMinzu)) {
				user.setUserMinzu(userMinzu);
			}
			if (StringUtil.isNotEmpty(userType)) {
				user.setUserType(Integer.parseInt(userType));
			}
			if (StringUtil.isNotEmpty(userType1)) {
				user.setUserType1(Integer.parseInt(userType1));
			}
			if (StringUtil.isNotEmpty(userType2)) {
				user.setUserType2(Integer.parseInt(userType2));
			}
			if (StringUtil.isNotEmpty(roleId)) {
				user.setRoleId(Integer.parseInt(roleId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				user.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(userSex)) {
				user.setUserSex(Integer.parseInt(userSex));
			}
			if (StringUtil.isNotEmpty(sdate1)) {
				Date date = new Date();
				sdate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			if (StringUtil.isNotEmpty(edate1)) {
				Date date = new Date();
				edate1 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
			}
			JSONArray jsonArray = JSONArray.fromObject(userService.queryUsers(
					user,null, pageBean.getStart(), pageBean.getRows(), sdate, edate, sdate1, edate1));
			JSONObject result = new JSONObject();
			int total = userService.queryUsers(user,null, 0,0, sdate, edate, sdate1, edate1).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addUser")
	public void addUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String userName = (String) request.getParameter("userName");
			String userPassword = (String) request.getParameter("userPassword");
			String userAge = (String) request.getParameter("userAge");
			String userXingming = (String) request.getParameter("userXingming");
			String userSex = (String) request.getParameter("userSex");
			String userMinzu = (String) request.getParameter("userMinzu");
			String userPhone = (String) request.getParameter("userPhone");
			String userMark = (String) request.getParameter("userMark");
			String userMark1 = (String) request.getParameter("userMark1");
			String userMark2 = (String) request.getParameter("userMark2");
			String userMark3 = (String) request.getParameter("userMark3");
			String userMark4 = (String) request.getParameter("userMark4");
			String userDate = (String) request.getParameter("userDate");
			String userDate1 = (String) request.getParameter("userDate1");
			String userDate2 = (String) request.getParameter("userDate2");
			String userType = (String) request.getParameter("userType");
			String userType1 = (String) request.getParameter("userType1");
			String userType2 = (String) request.getParameter("userType2");
			String userZong = (String) request.getParameter("userZong");
			String userZong1 = (String) request.getParameter("userZong1");
			String userZong2 = (String) request.getParameter("userZong2");
			String userDouble = (String) request.getParameter("userDouble");
			String userDouble1 = (String) request.getParameter("userDouble1");
			String userDouble2 = (String) request.getParameter("userDouble2");
			String roleId = (String) request.getParameter("roleId");
			String bumenId = (String) request.getParameter("bumenId");
			String buyuanId = (String) request.getParameter("buyuanId");
			String buzhiId = (String) request.getParameter("buzhiId");
			String userId = (String) request.getParameter("userId");
			User user = new User();

			if (StringUtil.isNotEmpty(userId)) {
				user = userService.getUser(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			if (StringUtil.isNotEmpty(userPassword)) {
				user.setUserPassword(userPassword);
			}
			if (StringUtil.isNotEmpty(userAge)) {
				user.setUserAge(Integer.parseInt(userAge));
			}
			if (StringUtil.isNotEmpty(userXingming)) {
				user.setUserXingming(userXingming);
			}
			if (StringUtil.isNotEmpty(userSex)) {
				user.setUserSex(Integer.parseInt(userSex));
			}
			if (StringUtil.isNotEmpty(userMinzu)) {
				user.setUserMinzu(userMinzu);
			}
			if (StringUtil.isNotEmpty(userPhone)) {
				user.setUserPhone(userPhone);
			}
			if (StringUtil.isNotEmpty(userMark1)) {
				user.setUserMark1(userMark1);
			}
			if (StringUtil.isNotEmpty(userMark2)) {
				user.setUserMark2(userMark2);
			}
			if (StringUtil.isNotEmpty(userMark3)) {
				user.setUserMark3(userMark3);
			}
			if (StringUtil.isNotEmpty(userMark4)) {
				user.setUserMark4(userMark4);
			}
			if (StringUtil.isNotEmpty(userDate)) {
				user.setUserDate(DateUtil.formatString(userDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userDate1)) {
				user.setUserDate1(DateUtil.formatString(userDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userDate2)) {
				user.setUserDate2(DateUtil.formatString(userDate2,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userType)) {
				user.setUserType(Integer.parseInt(userType));
			}
			if (StringUtil.isNotEmpty(userType1)) {
				user.setUserType1(Integer.parseInt(userType1));
			}
			if (StringUtil.isNotEmpty(userType2)) {
				user.setUserType2(Integer.parseInt(userType2));
			}
			if (StringUtil.isNotEmpty(userZong)) {
				user.setUserZong(Integer.parseInt(userZong));
			}
			if (StringUtil.isNotEmpty(userZong1)) {
				user.setUserZong1(Integer.parseInt(userZong1));
			}
			if (StringUtil.isNotEmpty(userZong2)) {
				user.setUserZong2(Integer.parseInt(userZong2));
			}
			if (StringUtil.isNotEmpty(userDouble)) {
				user.setUserDouble(Double.parseDouble(userDouble));
			}
			if (StringUtil.isNotEmpty(userDouble1)) {
				user.setUserDouble1(Double.parseDouble(userDouble1));
			}
			if (StringUtil.isNotEmpty(userDouble2)) {
				user.setUserDouble2(Double.parseDouble(userDouble2));
			}
			if (StringUtil.isNotEmpty(roleId)) {
				user.setRoleId(Integer.parseInt(roleId));
				Role role = new Role();
				role = roleService.getRole(Integer.parseInt(roleId));
				user.setRoleName(role.getRoleName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				user.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				user.setBuzhiName(buzhi.getBuzhiName());
				user.setBumenId(buzhi.getBumenId());
				user.setBumenName(buzhi.getBumenName());
				user.setBuyuanId(buzhi.getBuyuanId());
				user.setBuyuanName(buzhi.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				user.setBumenId(Integer.parseInt(bumenId));
				Bumen bumen = new Bumen();
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
				user.setBumenName(bumen.getBumenName());
				user.setBuyuanId(bumen.getBuyuanId());
				user.setBuyuanName(bumen.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				user.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				user.setBuyuanName(buyuan.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(userId)) {
				userService.modifyUser(user);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				int total = userService.queryUsers(null, userName, 0, 0, null, null, null, null).size();
				if (total==0) {
					Date date = new Date();
					user.setUserDate(date);
					user.setUserType(0);
					user.setUserDouble(0.0);
					user.setUserDouble1(0.0);
					user.setUserDouble2(0.0);
					userService.save(user);
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

	@RequestMapping("/mimaUser")
	public void mimaUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userId = (String) request.getParameter("userId");
		String userPassword = (String) request.getParameter("userPassword");
		String userPassword1 = (String) request.getParameter("userPassword1");
		User user = new User();
		try {
			user = userService.getUser(Integer.parseInt(userId));
			if (!user.getUserPassword().equals(userPassword)) {
				request.setAttribute("error", "原密码错误，请重新输入！");
				request.getRequestDispatcher("user/usermima.jsp").forward(request,
						response);
			}else{
				user.setUserPassword(userPassword1);
				userService.modifyUser(user);
				request.setAttribute("error", "密码修改成功！");
				request.getRequestDispatcher("user/usermima.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/zhaohuiUser")
	public void zhaohuiUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String userName = (String) request.getParameter("userName");
			String userPhone = (String) request.getParameter("userPhone");
			User user = new User();

			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			List<User> users = userService.queryUsers(user, null, 0,0, null, null, null, null);
			if(users.size()==0){
				request.setAttribute("error", "无此用户名请重新找回！");
				request.getRequestDispatcher("user/zhaohuiuser.jsp").forward(request, response);
			}else{
				user = users.get(0);
				if(user.getUserPhone().equals(userPhone)){
					String userPassword = user.getUserPassword();
					request.setAttribute("error", "密码是："+userPassword);
					request.getRequestDispatcher("user/zhaohuiuser.jsp").forward(request, response);
				}else{
					request.setAttribute("error", "用户名和电话不匹配，请重新找回！");
					request.getRequestDispatcher("user/zhaohuiuser.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "系统错误联系管理员！");
			request.getRequestDispatcher("user/zhaohuiuser.jsp").forward(request, response);
		}
	}

	@RequestMapping("/zhuceUser")
	public void zhuceUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {

			String userName = (String) request.getParameter("userName");
			String userPassword = (String) request.getParameter("userPassword");
			String userAge = (String) request.getParameter("userAge");
			String userXingming = (String) request.getParameter("userXingming");
			String userSex = (String) request.getParameter("userSex");
			String userMinzu = (String) request.getParameter("userMinzu");
			String userPhone = (String) request.getParameter("userPhone");
			String userMark = (String) request.getParameter("userMark");
			String userMark1 = (String) request.getParameter("userMark1");
			String userMark2 = (String) request.getParameter("userMark2");
			String userMark3 = (String) request.getParameter("userMark3");
			String userMark4 = (String) request.getParameter("userMark4");
			String userDate = (String) request.getParameter("userDate");
			String userDate1 = (String) request.getParameter("userDate1");
			String userDate2 = (String) request.getParameter("userDate2");
			String userType = (String) request.getParameter("userType");
			String userType1 = (String) request.getParameter("userType1");
			String userType2 = (String) request.getParameter("userType2");
			String userZong = (String) request.getParameter("userZong");
			String userZong1 = (String) request.getParameter("userZong1");
			String userZong2 = (String) request.getParameter("userZong2");
			String userDouble = (String) request.getParameter("userDouble");
			String userDouble1 = (String) request.getParameter("userDouble1");
			String userDouble2 = (String) request.getParameter("userDouble2");
			String roleId = (String) request.getParameter("roleId");
			String bumenId = (String) request.getParameter("bumenId");
			String buyuanId = (String) request.getParameter("buyuanId");
			String buzhiId = (String) request.getParameter("buzhiId");
			String userId = (String) request.getParameter("userId");
			User user = new User();

			if (StringUtil.isNotEmpty(userId)) {
				user = userService.getUser(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			if (StringUtil.isNotEmpty(userPassword)) {
				user.setUserPassword(userPassword);
			}
			if (StringUtil.isNotEmpty(userAge)) {
				user.setUserAge(Integer.parseInt(userAge));
			}
			if (StringUtil.isNotEmpty(userMinzu)) {
				user.setUserMinzu(userMinzu);
			}
			if (StringUtil.isNotEmpty(userXingming)) {
				user.setUserXingming(userXingming);
			}
			if (StringUtil.isNotEmpty(userSex)) {
				user.setUserSex(Integer.parseInt(userSex));
			}
			if (StringUtil.isNotEmpty(userPhone)) {
				user.setUserPhone(userPhone);
			}
			if (StringUtil.isNotEmpty(userMark)) {
				user.setUserMark(userMark);
			}
			if (StringUtil.isNotEmpty(userMark1)) {
				user.setUserMark1(userMark1);
			}
			if (StringUtil.isNotEmpty(userMark2)) {
				user.setUserMark2(userMark2);
			}
			if (StringUtil.isNotEmpty(userMark3)) {
				user.setUserMark3(userMark3);
			}
			if (StringUtil.isNotEmpty(userMark4)) {
				user.setUserMark4(userMark4);
			}
			if (StringUtil.isNotEmpty(userDate)) {
				user.setUserDate(DateUtil.formatString(userDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userDate1)) {
				user.setUserDate1(DateUtil.formatString(userDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userDate2)) {
				user.setUserDate2(DateUtil.formatString(userDate2,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(userType)) {
				user.setUserType(Integer.parseInt(userType));
			}
			if (StringUtil.isNotEmpty(userType1)) {
				user.setUserType1(Integer.parseInt(userType1));
			}
			if (StringUtil.isNotEmpty(userType2)) {
				user.setUserType2(Integer.parseInt(userType2));
			}
			if (StringUtil.isNotEmpty(userZong)) {
				user.setUserZong(Integer.parseInt(userZong));
			}
			if (StringUtil.isNotEmpty(userZong1)) {
				user.setUserZong1(Integer.parseInt(userZong1));
			}
			if (StringUtil.isNotEmpty(userZong2)) {
				user.setUserZong2(Integer.parseInt(userZong2));
			}
			if (StringUtil.isNotEmpty(userDouble)) {
				user.setUserDouble(Double.parseDouble(userDouble));
			}
			if (StringUtil.isNotEmpty(userDouble1)) {
				user.setUserDouble1(Double.parseDouble(userDouble1));
			}
			if (StringUtil.isNotEmpty(userDouble2)) {
				user.setUserDouble2(Double.parseDouble(userDouble2));
			}
			if (StringUtil.isNotEmpty(roleId)) {
				user.setRoleId(Integer.parseInt(roleId));
				Role role = new Role();
				role = roleService.getRole(Integer.parseInt(roleId));
				user.setRoleName(role.getRoleName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				user.setBuzhiId(Integer.parseInt(buzhiId));
				Buzhi buzhi = new Buzhi();
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
				user.setBuzhiName(buzhi.getBuzhiName());
				user.setBumenId(buzhi.getBumenId());
				user.setBumenName(buzhi.getBumenName());
				user.setBuyuanId(buzhi.getBuyuanId());
				user.setBuyuanName(buzhi.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				user.setBumenId(Integer.parseInt(bumenId));
				Bumen bumen = new Bumen();
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
				user.setBumenName(bumen.getBumenName());
				user.setBuyuanId(bumen.getBuyuanId());
				user.setBuyuanName(bumen.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				user.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				user.setBuyuanName(buyuan.getBuyuanName());
			}
			int total = userService.queryUsers(null, userName, 0, 0, null, null, null, null).size();
			if (total==0) {
				Date date = new Date();
				user.setUserDate(date);
				user.setUserType(0);
				user.setUserDouble(0.0);
				user.setUserDouble1(0.0);
				user.setUserDouble2(0.0);
				userService.save(user);
				request.setAttribute("error", "注册成功，请登录！");
				request.getRequestDispatcher("shouye/index.jsp").forward(request,
						response);
			} else {
				request.setAttribute("error", "用户名重复，请重新输入！");
				request.getRequestDispatcher("user/zhuceuser.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteUser")
	public void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				userService.deleteUser(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/userComboList")
	public void userComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userName = (String) request.getParameter("userName");
		String userXingming = (String) request.getParameter("userXingming");
		String userId = (String) request.getParameter("userId");
		String userMinzu = (String) request.getParameter("userMinzu");
		String userType = (String) request.getParameter("userType");
		String userType1 = (String) request.getParameter("userType1");
		String userType2 = (String) request.getParameter("userType2");
		String roleId = (String) request.getParameter("roleId");
		String bumenId = (String) request.getParameter("bumenId");
		String buyuanId = (String) request.getParameter("buyuanId");
		String buzhiId = (String) request.getParameter("buzhiId");
		String userSex = (String) request.getParameter("userSex");
		User user = new User();
		try {
			if (StringUtil.isNotEmpty(userXingming)) {
				user.setUserXingming(userXingming);
			}
			if (StringUtil.isNotEmpty(userName)) {
				user.setUserName(userName);
			}
			if (StringUtil.isNotEmpty(userId)) {
				user.setUserId(Integer.parseInt(userId));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				user.setBuyuanId(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				user.setBuzhiId(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(userMinzu)) {
				user.setUserMinzu(userMinzu);
			}
			if (StringUtil.isNotEmpty(userType)) {
				user.setUserType(Integer.parseInt(userType));
			}
			if (StringUtil.isNotEmpty(userType1)) {
				user.setUserType1(Integer.parseInt(userType1));
			}
			if (StringUtil.isNotEmpty(userType2)) {
				user.setUserType2(Integer.parseInt(userType2));
			}
			if (StringUtil.isNotEmpty(roleId)) {
				user.setRoleId(Integer.parseInt(roleId));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				user.setBumenId(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(userSex)) {
				user.setUserSex(Integer.parseInt(userSex));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("userName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(userService.queryUsers(user,
					null, 0,0, null, null, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/userTongji")
	public void userTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		String tijiaoUrl = "userTongji";
		List<Integer> bumenIds = new ArrayList<Integer>();
		List<String> tongjiNames = new ArrayList<String>();
		List<Double> tongjiZongshus = new ArrayList<Double>();
		List<Bumen> bumens = new ArrayList<Bumen>();
		List<User> users = new ArrayList<User>();
		Double zongshu = 0.0;
		try {
			bumens = bumenService.queryBumens(null, 0,0);
			for(int i=0;i<bumens.size();i++){
				bumenIds.add(bumens.get(i).getBumenId());
				tongjiNames.add(bumens.get(i).getBumenName());
			}
			for(int i=0;i<bumenIds.size();i++){
				Double userZongshu = 0.0;
				User user = new User();
				user.setBumenId(bumenIds.get(i));
				users = userService.queryUsers(user, null, 0,0,sdate,edate, null, null);
				for(int j=0;j<users.size();j++){
					userZongshu = userZongshu + users.size();
				}
				zongshu = zongshu + userZongshu;
				tongjiZongshus.add(userZongshu);
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

	@RequestMapping("/shangchuanUser")
	public void shangchuanUser(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String userId = (String) request.getParameter("userId");
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
			User user = userService.getUser(Integer.parseInt(userId));
			user.setUserImg(shangchuandizhi);
			user.setUserImgName(shangchuanname);
			userService.modifyUser(user);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daoruUser")
	public void daoruUser(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
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
				String userName = row.get(0);
				String userPassword = row.get(1);
				String userXingming = row.get(2);
				String userSex = row.get(3);
				String userAge = row.get(4);
				String userMinzu = row.get(5);
				String userPhone = row.get(6);
				String buzhiId = row.get(7);
				User user = new User();
				
				if (StringUtil.isNotEmpty(userName)) {
					user.setUserName(userName);
				}
				if (StringUtil.isNotEmpty(userPassword)) {
					user.setUserPassword(userPassword);
				}
				if (StringUtil.isNotEmpty(userAge)) {
					user.setUserAge(Integer.parseInt(userAge));
				}
				if (StringUtil.isNotEmpty(userXingming)) {
					user.setUserXingming(userXingming);
				}
				if (StringUtil.isNotEmpty(userSex)) {
					if(userSex.equals("男")){
						user.setUserSex(0);
					}else if(userSex.equals("女")){
						user.setUserSex(1);
					}else{
						user.setUserSex(0);
					}
				}
				if (StringUtil.isNotEmpty(userMinzu)) {
					user.setUserMinzu(userMinzu);
				}
				if (StringUtil.isNotEmpty(userPhone)) {
					user.setUserPhone(userPhone);
				}
				if (StringUtil.isNotEmpty(buzhiId)) {
					user.setBuzhiId(Integer.parseInt(buzhiId));
					Buzhi buzhi = new Buzhi();
					buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
					user.setBuzhiName(buzhi.getBuzhiName());
					user.setBumenId(buzhi.getBumenId());
					user.setBumenName(buzhi.getBumenName());
					user.setBuyuanId(buzhi.getBuyuanId());
					user.setBuyuanName(buzhi.getBuyuanName());
				}
				Date date = new Date();
				user.setUserDate(date);
				userService.save(user);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuUser")
	public void daochuUser(HttpServletRequest request, HttpServletResponse response)
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
			User user = new User();
			for (int i = 0; i < str.length; i++) {
				List<String> row = new ArrayList<String>();
				user = userService.getUser(Integer.parseInt(str[i]));
				row.add(TypeUtil.toString(i+1));
				row.add(user.getUserXingming());
				row.add(user.getBuzhiName());
				row.add(user.getUserPhone());
				row.add(user.getUserAge().toString());
				if(user.getUserSex()==0){
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
