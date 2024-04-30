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
public class JcdaohangAction {
	@Autowired
	private JcdaohangService jcdaohangService;
	@Autowired
	private JcbiaotiService jcbiaotiService;

	@RequestMapping("/addJcdaohang")
	public void addJcdaohang(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String jcdaohangName = (String) request.getParameter("jcdaohangName");
			String jcdaohangNeirong = (String) request.getParameter("jcdaohangNeirong");
			String jcdaohangMark = (String) request.getParameter("jcdaohangMark");
			String jcdaohangMark1 = (String) request.getParameter("jcdaohangMark1");
			String jcdaohangMark2 = (String) request.getParameter("jcdaohangMark2");
			String jcdaohangPaixu = (String) request.getParameter("jcdaohangPaixu");
			String jcdaohangType = (String) request.getParameter("jcdaohangType");
			String jcdaohangType1 = (String) request.getParameter("jcdaohangType1");
			String jcdaohangType2 = (String) request.getParameter("jcdaohangType2");
			String jcbiaotiId = (String) request.getParameter("jcbiaotiId");
			String jcdaohangId = (String) request.getParameter("jcdaohangId");
			Jcdaohang jcdaohang = new Jcdaohang();

			if (StringUtil.isNotEmpty(jcdaohangId)) {
				jcdaohang = jcdaohangService.getJcdaohang(Integer.parseInt(jcdaohangId));
			}
			if (StringUtil.isNotEmpty(jcdaohangName)) {
				jcdaohang.setJcdaohangName(jcdaohangName);
			}
			if (StringUtil.isNotEmpty(jcdaohangNeirong)) {
				jcdaohang.setJcdaohangNeirong(jcdaohangNeirong);
			}
			if (StringUtil.isNotEmpty(jcdaohangMark)) {
				jcdaohang.setJcdaohangMark(jcdaohangMark);
			}
			if (StringUtil.isNotEmpty(jcdaohangMark1)) {
				jcdaohang.setJcdaohangMark1(jcdaohangMark1);
			}
			if (StringUtil.isNotEmpty(jcdaohangMark2)) {
				jcdaohang.setJcdaohangMark2(jcdaohangMark2);
			}
			if (StringUtil.isNotEmpty(jcdaohangPaixu)) {
				jcdaohang.setJcdaohangPaixu(Integer.parseInt(jcdaohangPaixu));
			}
			if (StringUtil.isNotEmpty(jcdaohangType)) {
				jcdaohang.setJcdaohangType(Integer.parseInt(jcdaohangType));
			}
			if (StringUtil.isNotEmpty(jcdaohangType1)) {
				jcdaohang.setJcdaohangType1(Integer.parseInt(jcdaohangType1));
			}
			if (StringUtil.isNotEmpty(jcdaohangType2)) {
				jcdaohang.setJcdaohangType2(Integer.parseInt(jcdaohangType2));
			}
			if (StringUtil.isNotEmpty(jcbiaotiId)) {
				jcdaohang.setJcbiaotiId(Integer.parseInt(jcbiaotiId));
				Jcbiaoti jcbiaoti = jcbiaotiService.getJcbiaoti(Integer.parseInt(jcbiaotiId));
				jcdaohang.setJcbiaotiName(jcbiaoti.getJcbiaotiName());
				jcdaohang.setJcdaohangType(jcbiaoti.getJcbiaotiType());
			}
			if (StringUtil.isNotEmpty(jcdaohangId)) {
				jcdaohangService.modifyJcdaohang(jcdaohang);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				jcdaohang.setJcdaohangType1(0);
				jcdaohangService.save(jcdaohang);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getJcdaohangs")
	public void getJcdaohangs(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String jcdaohangName = (String) request.getParameter("jcdaohangName");
		String jcdaohangId = (String) request.getParameter("jcdaohangId");
		String jcdaohangType = (String) request.getParameter("jcdaohangType");
		String jcdaohangType1 = (String) request.getParameter("jcdaohangType1");
		String jcbiaotiId = (String) request.getParameter("jcbiaotiId");
		Jcdaohang jcdaohang = new Jcdaohang();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(jcdaohangName)) {
				jcdaohang.setJcdaohangName(jcdaohangName);
			}
			if (StringUtil.isNotEmpty(jcdaohangId)) {
				jcdaohang.setJcdaohangId(Integer.parseInt(jcdaohangId));
			}
			if (StringUtil.isNotEmpty(jcdaohangType)) {
				jcdaohang.setJcdaohangType(Integer.parseInt(jcdaohangType));
			}
			if (StringUtil.isNotEmpty(jcdaohangType1)) {
				jcdaohang.setJcdaohangType1(Integer.parseInt(jcdaohangType1));
			}
			if (StringUtil.isNotEmpty(jcbiaotiId)) {
				jcdaohang.setJcbiaotiId(Integer.parseInt(jcbiaotiId));
			}
			List<Jcdaohang> jcdaohangs = jcdaohangService.queryJcdaohangs(jcdaohang, pageBean.getStart(), pageBean.getRows());
			JSONArray jsonArray = JSONArray.fromObject(jcdaohangs);
			JSONObject result = new JSONObject();
			int total = jcdaohangService.queryJcdaohangs(jcdaohang, 0, 0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteJcdaohang")
	public void deleteJcdaohang(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				jcdaohangService.deleteJcdaohang(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/jcdaohangComboList")
	public void jcdaohangComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String jcdaohangType = (String) request.getParameter("jcdaohangType");
		Jcdaohang jcdaohang = new Jcdaohang();
		try {
			if (StringUtil.isNotEmpty(jcdaohangType)) {
				jcdaohang.setJcdaohangType(Integer.parseInt(jcdaohangType));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("jcdaohangName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(jcdaohangService.queryJcdaohangs(jcdaohang,
					0, 0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
