package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.*;
import com.service.*;
import com.util.*;

@Controller
public class JcbiaotiAction {
	@Autowired
	private JcbiaotiService jcbiaotiService;
	@Autowired
	private JcdaohangService jcdaohangService;

	@RequestMapping("/addJcbiaoti")
	public void addJcbiaoti(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String jcbiaotiName = (String) request.getParameter("jcbiaotiName");
			String jcbiaotiNeirong = (String) request.getParameter("jcbiaotiNeirong");
			String jcbiaotiMark = (String) request.getParameter("jcbiaotiMark");
			String jcbiaotiMark1 = (String) request.getParameter("jcbiaotiMark1");
			String jcbiaotiMark2 = (String) request.getParameter("jcbiaotiMark2");
			String jcbiaotiPaixu = (String) request.getParameter("jcbiaotiPaixu");
			String jcbiaotiType = (String) request.getParameter("jcbiaotiType");
			String jcbiaotiType1 = (String) request.getParameter("jcbiaotiType1");
			String jcbiaotiType2 = (String) request.getParameter("jcbiaotiType2");
			String jcbiaotiId = (String) request.getParameter("jcbiaotiId");
			Jcbiaoti jcbiaoti = new Jcbiaoti();

			if (StringUtil.isNotEmpty(jcbiaotiId)) {
				jcbiaoti = jcbiaotiService.getJcbiaoti(Integer.parseInt(jcbiaotiId));
			}
			if (StringUtil.isNotEmpty(jcbiaotiName)) {
				jcbiaoti.setJcbiaotiName(jcbiaotiName);
			}
			if (StringUtil.isNotEmpty(jcbiaotiNeirong)) {
				jcbiaoti.setJcbiaotiNeirong(jcbiaotiNeirong);
			}
			if (StringUtil.isNotEmpty(jcbiaotiMark)) {
				jcbiaoti.setJcbiaotiMark(jcbiaotiMark);
			}
			if (StringUtil.isNotEmpty(jcbiaotiMark1)) {
				jcbiaoti.setJcbiaotiMark1(jcbiaotiMark1);
			}
			if (StringUtil.isNotEmpty(jcbiaotiMark2)) {
				jcbiaoti.setJcbiaotiMark2(jcbiaotiMark2);
			}
			if (StringUtil.isNotEmpty(jcbiaotiPaixu)) {
				jcbiaoti.setJcbiaotiPaixu(Integer.parseInt(jcbiaotiPaixu));
			}
			if (StringUtil.isNotEmpty(jcbiaotiType)) {
				jcbiaoti.setJcbiaotiType(Integer.parseInt(jcbiaotiType));
			}
			if (StringUtil.isNotEmpty(jcbiaotiType1)) {
				jcbiaoti.setJcbiaotiType1(Integer.parseInt(jcbiaotiType1));
			}
			if (StringUtil.isNotEmpty(jcbiaotiType2)) {
				jcbiaoti.setJcbiaotiType2(Integer.parseInt(jcbiaotiType2));
			}
			if (StringUtil.isNotEmpty(jcbiaotiId)) {
				jcbiaotiService.modifyJcbiaoti(jcbiaoti);
				Jcdaohang jcdaohang = new Jcdaohang();
				jcdaohang.setJcbiaotiId(jcbiaoti.getJcbiaotiId());
				List<Jcdaohang> jcdaohangs = jcdaohangService.queryJcdaohangs(jcdaohang, 0, 0);
				for (int j = 0; j < jcdaohangs.size(); j++) {
					jcdaohang = jcdaohangs.get(j);
					jcdaohang.setJcdaohangType1(jcbiaoti.getJcbiaotiType1());
					jcdaohangService.modifyJcdaohang(jcdaohang);
				}
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				jcbiaoti.setJcbiaotiType1(0);
				jcbiaotiService.save(jcbiaoti);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getJcbiaotis")
	public void getJcbiaotis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String jcbiaotiName = (String) request.getParameter("jcbiaotiName");
		String jcbiaotiId = (String) request.getParameter("jcbiaotiId");
		String jcbiaotiType = (String) request.getParameter("jcbiaotiType");
		String jcbiaotiType1 = (String) request.getParameter("jcbiaotiType1");
		Jcbiaoti jcbiaoti = new Jcbiaoti();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(jcbiaotiName)) {
				jcbiaoti.setJcbiaotiName(jcbiaotiName);
			}
			if (StringUtil.isNotEmpty(jcbiaotiId)) {
				jcbiaoti.setJcbiaotiId(Integer.parseInt(jcbiaotiId));
			}
			if (StringUtil.isNotEmpty(jcbiaotiType)) {
				jcbiaoti.setJcbiaotiType(Integer.parseInt(jcbiaotiType));
			}
			if (StringUtil.isNotEmpty(jcbiaotiType1)) {
				jcbiaoti.setJcbiaotiType1(Integer.parseInt(jcbiaotiType1));
			}
			List<Jcbiaoti> jcbiaotis = jcbiaotiService.queryJcbiaotis(jcbiaoti, pageBean.getStart(), pageBean.getRows());
			JSONArray jsonArray = JSONArray.fromObject(jcbiaotis);
			JSONObject result = new JSONObject();
			int total = jcbiaotiService.queryJcbiaotis(jcbiaoti, 0, 0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteJcbiaoti")
	public void deleteJcbiaoti(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				Jcdaohang jcdaohang = new Jcdaohang();
				jcdaohang.setJcbiaotiId(Integer.parseInt(str[i]));
				List<Jcdaohang> jcdaohangs = jcdaohangService.queryJcdaohangs(jcdaohang, 0, 0);
				for (int j = 0; j < jcdaohangs.size(); j++) {
					jcdaohangService.deleteJcdaohang(jcdaohangs.get(j).getJcdaohangId());
				}
				jcbiaotiService.deleteJcbiaoti(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/jcbiaotiComboList")
	public void jcbiaotiComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String jcbiaotiType = (String) request.getParameter("jcbiaotiType");
		Jcbiaoti jcbiaoti = new Jcbiaoti();
		try {
			if (StringUtil.isNotEmpty(jcbiaotiType)) {
				jcbiaoti.setJcbiaotiType(Integer.parseInt(jcbiaotiType));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("jcbiaotiName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(jcbiaotiService.queryJcbiaotis(jcbiaoti,
					0, 0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
