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
public class UxtypeAction {
	@Autowired
	private UxtypeService uxtypeService;

	@RequestMapping("/getUxtypes")
	public void getUxtypes(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String uxtypeName = (String) request.getParameter("uxtypeName");
		String uxtypeId = (String) request.getParameter("uxtypeId");
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		Uxtype uxtype = new Uxtype();
		try {
			if (StringUtil.isNotEmpty(uxtypeName)) {
				uxtype.setUxtypeName(uxtypeName);
			}
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxtype.setUxtypeId(Integer.parseInt(uxtypeId));
			}
			JSONArray jsonArray = JSONArray.fromObject(uxtypeService.queryUxtypes(uxtype, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = uxtypeService.queryUxtypes(uxtype, 0, 0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addUxtype")
	public void addUxtype(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String uxtypeName = (String) request.getParameter("uxtypeName");
			String uxtypeMark = (String) request.getParameter("uxtypeMark");
			String uxtypeId = (String) request.getParameter("uxtypeId");
			Uxtype uxtype = new Uxtype();
			
			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxtype = uxtypeService.getUxtype(Integer.parseInt(uxtypeId));
			}
			if (StringUtil.isNotEmpty(uxtypeName)) {
				uxtype.setUxtypeName(uxtypeName);
			}
			if (StringUtil.isNotEmpty(uxtypeMark)) {
				uxtype.setUxtypeMark(uxtypeMark);
			}

			if (StringUtil.isNotEmpty(uxtypeId)) {
				uxtypeService.modifyUxtype(uxtype);
			} else {
				uxtypeService.save(uxtype);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteUxtype")
	public void deleteUxtype(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				uxtypeService.deleteUxtype(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/uxtypeComboList")
	public void uxtypeComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("uxtypeName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(uxtypeService.queryUxtypes(null, 0, 0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
