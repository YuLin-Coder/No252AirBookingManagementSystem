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
public class GgtypeAction {
	@Autowired
	private GgtypeService ggtypeService;

	@RequestMapping("/getGgtypes")
	public void getGgtypes(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String ggtypeName = (String) request.getParameter("ggtypeName");
		String ggtypeId = (String) request.getParameter("ggtypeId");
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		Ggtype ggtype = new Ggtype();
		try {
			if (StringUtil.isNotEmpty(ggtypeName)) {
				ggtype.setGgtypeName(ggtypeName);
			}
			if (StringUtil.isNotEmpty(ggtypeId)) {
				ggtype.setGgtypeId(Integer.parseInt(ggtypeId));
			}
			JSONArray jsonArray = JSONArray.fromObject(ggtypeService.queryGgtypes(ggtype, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = ggtypeService.queryGgtypes(ggtype, 0, 0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addGgtype")
	public void addGgtype(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String ggtypeName = (String) request.getParameter("ggtypeName");
			String ggtypeMark = (String) request.getParameter("ggtypeMark");
			String ggtypeId = (String) request.getParameter("ggtypeId");
			Ggtype ggtype = new Ggtype();
			
			if (StringUtil.isNotEmpty(ggtypeId)) {
				ggtype = ggtypeService.getGgtype(Integer.parseInt(ggtypeId));
			}
			if (StringUtil.isNotEmpty(ggtypeName)) {
				ggtype.setGgtypeName(ggtypeName);
			}
			if (StringUtil.isNotEmpty(ggtypeMark)) {
				ggtype.setGgtypeMark(ggtypeMark);
			}

			if (StringUtil.isNotEmpty(ggtypeId)) {
				ggtypeService.modifyGgtype(ggtype);
			} else {
				ggtypeService.save(ggtype);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteGgtype")
	public void deleteGgtype(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				ggtypeService.deleteGgtype(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/ggtypeComboList")
	public void ggtypeComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("ggtypeName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(ggtypeService.queryGgtypes(null, 0, 0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
