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
public class SjlaiyuanAction {
	@Autowired
	private SjlaiyuanService sjlaiyuanService;

	@RequestMapping("/getSjlaiyuans")
	public void getSjlaiyuans(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String sjlaiyuanName = (String) request.getParameter("sjlaiyuanName");
		String sjlaiyuanPhone = (String) request.getParameter("sjlaiyuanPhone");
		String sjlaiyuanId = (String) request.getParameter("sjlaiyuanId");
		String sjlaiyuanType1 = (String) request.getParameter("sjlaiyuanType1");
		String sjlaiyuanType = (String) request.getParameter("sjlaiyuanType");
		Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(sjlaiyuanName)) {
				sjlaiyuan.setSjlaiyuanName(sjlaiyuanName);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanPhone)) {
				sjlaiyuan.setSjlaiyuanPhone(sjlaiyuanPhone);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjlaiyuan.setSjlaiyuanId(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanType)) {
				sjlaiyuan.setSjlaiyuanType(Integer.parseInt(sjlaiyuanType));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanType1)) {
				sjlaiyuan.setSjlaiyuanType1(Integer.parseInt(sjlaiyuanType1));
			}
			JSONArray jsonArray = JSONArray.fromObject(sjlaiyuanService.querySjlaiyuans(
					sjlaiyuan, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = sjlaiyuanService.querySjlaiyuans(sjlaiyuan, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addSjlaiyuan")
	public void addSjlaiyuan(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String sjlaiyuanName = (String) request.getParameter("sjlaiyuanName");
			String sjlaiyuanPhone = (String) request.getParameter("sjlaiyuanPhone");
			String sjlaiyuanMark = (String) request.getParameter("sjlaiyuanMark");
			String sjlaiyuanMark1 = (String) request.getParameter("sjlaiyuanMark1");
			String sjlaiyuanMark2 = (String) request.getParameter("sjlaiyuanMark2");
			String sjlaiyuanDizhi = (String) request.getParameter("sjlaiyuanDizhi");
			String sjlaiyuanDate = (String) request.getParameter("sjlaiyuanDate");
			String sjlaiyuanDate1 = (String) request.getParameter("sjlaiyuanDate1");
			String sjlaiyuanType = (String) request.getParameter("sjlaiyuanType");
			String sjlaiyuanType1 = (String) request.getParameter("sjlaiyuanType1");
			String sjlaiyuanDouble = (String) request.getParameter("sjlaiyuanDouble");
			String sjlaiyuanDouble1 = (String) request.getParameter("sjlaiyuanDouble1");
			String sjlaiyuanId = (String) request.getParameter("sjlaiyuanId");
			Sjlaiyuan sjlaiyuan = new Sjlaiyuan();

			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				sjlaiyuan = sjlaiyuanService.getSjlaiyuan(Integer.parseInt(sjlaiyuanId));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanName)) {
				sjlaiyuan.setSjlaiyuanName(sjlaiyuanName);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanPhone)) {
				sjlaiyuan.setSjlaiyuanPhone(sjlaiyuanPhone);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanMark)) {
				sjlaiyuan.setSjlaiyuanMark(sjlaiyuanMark);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanMark1)) {
				sjlaiyuan.setSjlaiyuanMark1(sjlaiyuanMark1);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanMark2)) {
				sjlaiyuan.setSjlaiyuanMark2(sjlaiyuanMark2);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanDizhi)) {
				sjlaiyuan.setSjlaiyuanDizhi(sjlaiyuanDizhi);
			}
			if (StringUtil.isNotEmpty(sjlaiyuanDate)) {
				sjlaiyuan.setSjlaiyuanDate(DateUtil.formatString(sjlaiyuanDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanDate1)) {
				sjlaiyuan.setSjlaiyuanDate1(DateUtil.formatString(sjlaiyuanDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanType)) {
				sjlaiyuan.setSjlaiyuanType(Integer.parseInt(sjlaiyuanType));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanType1)) {
				sjlaiyuan.setSjlaiyuanType1(Integer.parseInt(sjlaiyuanType1));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanDouble)) {
				sjlaiyuan.setSjlaiyuanDouble(Double.parseDouble(sjlaiyuanDouble));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanDouble1)) {
				sjlaiyuan.setSjlaiyuanDouble1(Double.parseDouble(sjlaiyuanDouble1));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanId)) {
				Date date = new Date();
				sjlaiyuan.setSjlaiyuanDate(date);
				sjlaiyuanService.modifySjlaiyuan(sjlaiyuan);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				sjlaiyuanService.save(sjlaiyuan);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteSjlaiyuan")
	public void deleteSjlaiyuan(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjlaiyuanService.deleteSjlaiyuan(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjlaiyuanComboList")
	public void sjlaiyuanComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sjlaiyuanType1 = (String) request.getParameter("sjlaiyuanType1");
		String sjlaiyuanType = (String) request.getParameter("sjlaiyuanType");
		Sjlaiyuan sjlaiyuan = new Sjlaiyuan();
		try {
			if (StringUtil.isNotEmpty(sjlaiyuanType)) {
				sjlaiyuan.setSjlaiyuanType(Integer.parseInt(sjlaiyuanType));
			}
			if (StringUtil.isNotEmpty(sjlaiyuanType1)) {
				sjlaiyuan.setSjlaiyuanType1(Integer.parseInt(sjlaiyuanType1));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("sjlaiyuanName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(sjlaiyuanService.querySjlaiyuans(sjlaiyuan,
					0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
