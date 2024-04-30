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
public class SjleixingAction {
	@Autowired
	private SjleixingService sjleixingService;

	@RequestMapping("/getSjleixings")
	public void getSjleixings(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String sjleixingName = (String) request.getParameter("sjleixingName");
		String sjleixingPhone = (String) request.getParameter("sjleixingPhone");
		String sjleixingId = (String) request.getParameter("sjleixingId");
		String sjleixingType1 = (String) request.getParameter("sjleixingType1");
		String sjleixingType = (String) request.getParameter("sjleixingType");
		Sjleixing sjleixing = new Sjleixing();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(sjleixingName)) {
				sjleixing.setSjleixingName(sjleixingName);
			}
			if (StringUtil.isNotEmpty(sjleixingPhone)) {
				sjleixing.setSjleixingPhone(sjleixingPhone);
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjleixing.setSjleixingId(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjleixingType)) {
				sjleixing.setSjleixingType(Integer.parseInt(sjleixingType));
			}
			if (StringUtil.isNotEmpty(sjleixingType1)) {
				sjleixing.setSjleixingType1(Integer.parseInt(sjleixingType1));
			}
			JSONArray jsonArray = JSONArray.fromObject(sjleixingService.querySjleixings(
					sjleixing, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = sjleixingService.querySjleixings(sjleixing, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addSjleixing")
	public void addSjleixing(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String sjleixingName = (String) request.getParameter("sjleixingName");
			String sjleixingPhone = (String) request.getParameter("sjleixingPhone");
			String sjleixingMark = (String) request.getParameter("sjleixingMark");
			String sjleixingMark1 = (String) request.getParameter("sjleixingMark1");
			String sjleixingMark2 = (String) request.getParameter("sjleixingMark2");
			String sjleixingDizhi = (String) request.getParameter("sjleixingDizhi");
			String sjleixingDate = (String) request.getParameter("sjleixingDate");
			String sjleixingDate1 = (String) request.getParameter("sjleixingDate1");
			String sjleixingType = (String) request.getParameter("sjleixingType");
			String sjleixingType1 = (String) request.getParameter("sjleixingType1");
			String sjleixingDouble = (String) request.getParameter("sjleixingDouble");
			String sjleixingDouble1 = (String) request.getParameter("sjleixingDouble1");
			String sjleixingId = (String) request.getParameter("sjleixingId");
			Sjleixing sjleixing = new Sjleixing();

			if (StringUtil.isNotEmpty(sjleixingId)) {
				sjleixing = sjleixingService.getSjleixing(Integer.parseInt(sjleixingId));
			}
			if (StringUtil.isNotEmpty(sjleixingName)) {
				sjleixing.setSjleixingName(sjleixingName);
			}
			if (StringUtil.isNotEmpty(sjleixingPhone)) {
				sjleixing.setSjleixingPhone(sjleixingPhone);
			}
			if (StringUtil.isNotEmpty(sjleixingMark)) {
				sjleixing.setSjleixingMark(sjleixingMark);
			}
			if (StringUtil.isNotEmpty(sjleixingMark1)) {
				sjleixing.setSjleixingMark1(sjleixingMark1);
			}
			if (StringUtil.isNotEmpty(sjleixingMark2)) {
				sjleixing.setSjleixingMark2(sjleixingMark2);
			}
			if (StringUtil.isNotEmpty(sjleixingDizhi)) {
				sjleixing.setSjleixingDizhi(sjleixingDizhi);
			}
			if (StringUtil.isNotEmpty(sjleixingDate)) {
				sjleixing.setSjleixingDate(DateUtil.formatString(sjleixingDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjleixingDate1)) {
				sjleixing.setSjleixingDate1(DateUtil.formatString(sjleixingDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjleixingType)) {
				sjleixing.setSjleixingType(Integer.parseInt(sjleixingType));
			}
			if (StringUtil.isNotEmpty(sjleixingType1)) {
				sjleixing.setSjleixingType1(Integer.parseInt(sjleixingType1));
			}
			if (StringUtil.isNotEmpty(sjleixingDouble)) {
				sjleixing.setSjleixingDouble(Double.parseDouble(sjleixingDouble));
			}
			if (StringUtil.isNotEmpty(sjleixingDouble1)) {
				sjleixing.setSjleixingDouble1(Double.parseDouble(sjleixingDouble1));
			}
			if (StringUtil.isNotEmpty(sjleixingId)) {
				Date date = new Date();
				sjleixing.setSjleixingDate(date);
				sjleixingService.modifySjleixing(sjleixing);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				sjleixingService.save(sjleixing);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteSjleixing")
	public void deleteSjleixing(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjleixingService.deleteSjleixing(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjleixingComboList")
	public void sjleixingComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sjleixingType1 = (String) request.getParameter("sjleixingType1");
		String sjleixingType = (String) request.getParameter("sjleixingType");
		Sjleixing sjleixing = new Sjleixing();
		try {
			if (StringUtil.isNotEmpty(sjleixingType)) {
				sjleixing.setSjleixingType(Integer.parseInt(sjleixingType));
			}
			if (StringUtil.isNotEmpty(sjleixingType1)) {
				sjleixing.setSjleixingType1(Integer.parseInt(sjleixingType1));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("sjleixingName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(sjleixingService.querySjleixings(sjleixing,
					0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
