package com.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.*;
import com.service.*;
import com.util.*;

@Controller
public class LoginAction {
	@Autowired
	private RizhiService rizhiService;
	@Autowired
	private UserService userService;
	@Autowired
	private YonghuService yonghuService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private JcpeizhiService jcpeizhiService;
	@Autowired
	private JcdaohangService jcdaohangService;
	@Autowired
	private JcbiaotiService jcbiaotiService;
	
	public Jcpeizhi jiazaiPeizhi() {
		List<Jcpeizhi> jcpeizhis = new ArrayList<Jcpeizhi>();
		jcpeizhis = jcpeizhiService.queryJcpeizhis(null, 0, 0);
		if(jcpeizhis.size()>0){
			return jcpeizhis.get(0);
		}else{
			return null;
		}
	}
	
	public List<Jcbiaoti> jiazaiBiaoti(int jcbiaotiType) {
		Jcbiaoti jcbiaoti = new Jcbiaoti();
		jcbiaoti.setJcbiaotiType(jcbiaotiType);
		jcbiaoti.setJcbiaotiType1(0);
		List<Jcbiaoti> jcbiaotis = new ArrayList<Jcbiaoti>();
		jcbiaotis = jcbiaotiService.queryJcbiaotis(jcbiaoti, 0, 0);
		return jcbiaotis;
	}
	
	public List<List<Jcdaohang>> jiazaiDaohang(List<Jcbiaoti> jcbiaotis) {
		List<List<Jcdaohang>> jcdaohangslist = new ArrayList<List<Jcdaohang>>();
		for (int i = 0; i < jcbiaotis.size(); i++) {
			List<Jcdaohang> jcdaohangs = new ArrayList<Jcdaohang>();
			Jcdaohang jcdaohang = new Jcdaohang();
			jcdaohang.setJcdaohangType1(0);
			jcdaohang.setJcbiaotiId(jcbiaotis.get(i).getJcbiaotiId());
			jcdaohangs = jcdaohangService.queryJcdaohangs(jcdaohang,0 ,0);
			jcdaohangslist.add(jcdaohangs);
		}
		return jcdaohangslist;
	}

	@RequestMapping("/login")
	public void loginUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		String loginType = (String) request.getParameter("loginType");
		
		String ip = request.getRemoteAddr();
		Date date = new Date();
		Rizhi rizhi = new Rizhi();						
		rizhi.setRizhiName(userName);
		rizhi.setDate(date);
		rizhi.setDengluIp(ip);
		rizhiService.save(rizhi);
		if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
			request.setAttribute("error", "用户名或密码为空！");
			request.getRequestDispatcher("shouye/index.jsp").forward(request,
					response);
		} else {
			Jcpeizhi jcpeizhi = jiazaiPeizhi();
			if(jcpeizhi == null){
				request.setAttribute("error", "系统还未配置参数，联系管理员！");
				// 服务器跳转
				request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
			}else{
				// 获取Session
				HttpSession session=request.getSession();
				session.setAttribute("jcpeizhi", jcpeizhi);
				if (loginType.equals("admin")) {
					Admin admin = new Admin();
					admin.setAdminName(userName);
					admin.setAdminPassword(password);
					try {
						if (adminService.queryAdmins(admin, 0, 0).size()==1) {
							session.setAttribute("admin", admin);
							List<Jcbiaoti> jcbiaotis = jiazaiBiaoti(1);
							if(jcbiaotis.size()==0){
								request.setAttribute("error", "系统还未配置标题，联系管理员！");
								// 服务器跳转
								request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
							}else{
								List<List<Jcdaohang>> jcdaohangslist = jiazaiDaohang(jcbiaotis);
								session.setAttribute("jcdaohangslist", jcdaohangslist);
								session.setAttribute("jcbiaotis", jcbiaotis);
								session.setAttribute("admin", admin);
								// 客户端跳转
								response.sendRedirect("houtai/adminMain.jsp");
							}
						}else{
							request.setAttribute("error", "用户名或密码错误！");
							// 服务器跳转
							request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
						}
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("error", "服务器错误！");
						// 服务器跳转
						request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
					}
				} else if (loginType.equals("yonghu")){
					Yonghu yonghu = new Yonghu();
					yonghu.setYonghuName(userName);
					yonghu.setYonghuPassword(password);
					try {
						if (yonghuService.queryYonghus(yonghu, userName, 0, 0, null, null, null, null).size() == 1) {
							Yonghu yonghuLogin = (Yonghu)(yonghuService.queryYonghus(yonghu, userName, 0, 0, null, null, null, null)).get(0);
							List<Jcbiaoti> jcbiaotis = jiazaiBiaoti(3);
							if(jcbiaotis.size()==0){
								request.setAttribute("error", "系统还未配置标题，联系管理员！");
								// 服务器跳转
								request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
							}else{
								List<List<Jcdaohang>> jcdaohangslist = jiazaiDaohang(jcbiaotis);
								session.setAttribute("jcdaohangslist", jcdaohangslist);
								session.setAttribute("jcbiaotis", jcbiaotis);
								session.setAttribute("yonghu", yonghuLogin);
								// 客户端跳转
								response.sendRedirect("houtai/yonghuMain.jsp");
							}
						}else{
							request.setAttribute("error", "用户名或密码错误！");
							// 服务器跳转
							request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
						}
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("error", "服务器错误！");
						// 服务器跳转
						request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
					}
				}else if (loginType.equals("user")){
					User user = new User();
					user.setUserName(userName);
					user.setUserPassword(password);
					try {
						if (userService.queryUsers(user, userName, 0, 0, null, null, null, null).size() == 1) {
							User userLogin = (User)(userService.queryUsers(user, userName, 0, 0, null, null, null, null)).get(0);
							List<Jcbiaoti> jcbiaotis = jiazaiBiaoti(2);
							if(jcbiaotis.size()==0){
								request.setAttribute("error", "系统还未配置标题，联系管理员！");
								// 服务器跳转
								request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
							}else{
								List<List<Jcdaohang>> jcdaohangslist = jiazaiDaohang(jcbiaotis);
								session.setAttribute("jcdaohangslist", jcdaohangslist);
								session.setAttribute("jcbiaotis", jcbiaotis);
								session.setAttribute("user", userLogin);
								// 客户端跳转
								response.sendRedirect("wangzhan/index.jsp");
							}
						}else{
							request.setAttribute("error", "用户名或密码错误！");
							// 服务器跳转
							request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
						}
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("error", "服务器错误！");
						// 服务器跳转
						request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("error", "登录权限错误！");
					// 服务器跳转
					request.getRequestDispatcher("shouye/index.jsp").forward(request, response);
				}
			}
		}
	}
}
