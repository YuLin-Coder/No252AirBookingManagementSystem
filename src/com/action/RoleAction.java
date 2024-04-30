package com.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.model.*;
import com.service.*;
import com.util.*;

@Controller
public class RoleAction {
	@Autowired
	private RoleService roleService;

	@RequestMapping("/getRoles")
	public void getRoles(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String roleName = (String) request.getParameter("roleName");
		String roleId = (String) request.getParameter("roleId");
		String roleMark2 = (String) request.getParameter("roleMark2");
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		Role role = new Role();
		try {
			if (StringUtil.isNotEmpty(roleName)) {
				role.setRoleName(roleName);
			}
			if (StringUtil.isNotEmpty(roleId)) {
				role.setRoleId(Integer.parseInt(roleId));
			}
			if (StringUtil.isNotEmpty(roleMark2)) {
				role.setRoleMark2(Integer.parseInt(roleMark2));
			}
			JSONArray jsonArray = JSONArray.fromObject(roleService.queryRoles(role, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = roleService.queryRoles(role, 0, 0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addRole")
	public void addRole(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String roleName = (String) request.getParameter("roleName");
			String roleMark = (String) request.getParameter("roleMark");
			String roleMark1 = (String) request.getParameter("roleMark1");
			String roleMark2 = (String) request.getParameter("roleMark2");
			String roleId = (String) request.getParameter("roleId");
			Role role = new Role();
			
			if (StringUtil.isNotEmpty(roleId)) {
				role = roleService.getRole(Integer.parseInt(roleId));
			}
			if (StringUtil.isNotEmpty(roleName)) {
				role.setRoleName(roleName);
			}
			if (StringUtil.isNotEmpty(roleMark)) {
				role.setRoleMark(roleMark);
			}
			if (StringUtil.isNotEmpty(roleMark1)) {
				role.setRoleMark1(roleMark1);
			}
			if (StringUtil.isNotEmpty(roleMark2)) {
				role.setRoleMark2(Integer.parseInt(roleMark2));
			}

			if (StringUtil.isNotEmpty(roleId)) {
				roleService.modifyRole(role);
			} else {
				roleService.save(role);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteRole")
	public void deleteRole(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				roleService.deleteRole(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/roleComboList")
	public void roleComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String roleMark2 = (String) request.getParameter("roleMark2");
		Role role = new Role();

		if (StringUtil.isNotEmpty(roleMark2)) {
			role.setRoleMark2(Integer.parseInt(roleMark2));
		}
		try {
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("roleName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(roleService.queryRoles(role, 0, 0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
