package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.*;
import com.service.*;

@Controller
public class AdminAction {
	@Autowired
	private AdminService adminService;

	@RequestMapping("/mimaAdmin")
	public void mimaAdmin(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			JSONObject result = new JSONObject();

			String adminPassword = (String) request.getParameter("adminPassword");
			String adminPassword1 = (String) request.getParameter("adminPassword1");
			Admin admin = new Admin();
			admin.setAdminName("admin");
			admin.setAdminPassword(adminPassword);
			if (adminService.queryAdmins(admin, 0, 0).size()==1) {
				admin = (Admin)(adminService.queryAdmins(admin, 0, 0)).get(0);
				admin.setAdminPassword(adminPassword1);
				adminService.modifyAdmin(admin);
				request.setAttribute("error", "密码修改成功！");
				request.getRequestDispatcher("admin/adminmima.jsp").forward(request,
						response);
			}else{
				result.put("success", "true");
				request.setAttribute("error", "原密码错误，请重新输入！");
				request.getRequestDispatcher("admin/adminmima.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
