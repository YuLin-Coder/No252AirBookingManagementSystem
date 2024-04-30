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
public class SjqitaAction {
	@Autowired
	private SjqitaService sjqitaService;

	@RequestMapping("/getSjqitas")
	public void getSjqitas(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String sjqitaName = (String) request.getParameter("sjqitaName");
		String sjqitaPhone = (String) request.getParameter("sjqitaPhone");
		String sjqitaId = (String) request.getParameter("sjqitaId");
		String sjqitaType1 = (String) request.getParameter("sjqitaType1");
		String sjqitaType = (String) request.getParameter("sjqitaType");
		Sjqita sjqita = new Sjqita();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(sjqitaName)) {
				sjqita.setSjqitaName(sjqitaName);
			}
			if (StringUtil.isNotEmpty(sjqitaPhone)) {
				sjqita.setSjqitaPhone(sjqitaPhone);
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjqita.setSjqitaId(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(sjqitaType)) {
				sjqita.setSjqitaType(Integer.parseInt(sjqitaType));
			}
			if (StringUtil.isNotEmpty(sjqitaType1)) {
				sjqita.setSjqitaType1(Integer.parseInt(sjqitaType1));
			}
			JSONArray jsonArray = JSONArray.fromObject(sjqitaService.querySjqitas(
					sjqita, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = sjqitaService.querySjqitas(sjqita, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addSjqita")
	public void addSjqita(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String sjqitaName = (String) request.getParameter("sjqitaName");
			String sjqitaPhone = (String) request.getParameter("sjqitaPhone");
			String sjqitaMark = (String) request.getParameter("sjqitaMark");
			String sjqitaMark1 = (String) request.getParameter("sjqitaMark1");
			String sjqitaMark2 = (String) request.getParameter("sjqitaMark2");
			String sjqitaDizhi = (String) request.getParameter("sjqitaDizhi");
			String sjqitaDate = (String) request.getParameter("sjqitaDate");
			String sjqitaDate1 = (String) request.getParameter("sjqitaDate1");
			String sjqitaType = (String) request.getParameter("sjqitaType");
			String sjqitaType1 = (String) request.getParameter("sjqitaType1");
			String sjqitaDouble = (String) request.getParameter("sjqitaDouble");
			String sjqitaDouble1 = (String) request.getParameter("sjqitaDouble1");
			String sjqitaId = (String) request.getParameter("sjqitaId");
			Sjqita sjqita = new Sjqita();

			if (StringUtil.isNotEmpty(sjqitaId)) {
				sjqita = sjqitaService.getSjqita(Integer.parseInt(sjqitaId));
			}
			if (StringUtil.isNotEmpty(sjqitaName)) {
				sjqita.setSjqitaName(sjqitaName);
			}
			if (StringUtil.isNotEmpty(sjqitaPhone)) {
				sjqita.setSjqitaPhone(sjqitaPhone);
			}
			if (StringUtil.isNotEmpty(sjqitaMark)) {
				sjqita.setSjqitaMark(sjqitaMark);
			}
			if (StringUtil.isNotEmpty(sjqitaMark1)) {
				sjqita.setSjqitaMark1(sjqitaMark1);
			}
			if (StringUtil.isNotEmpty(sjqitaMark2)) {
				sjqita.setSjqitaMark2(sjqitaMark2);
			}
			if (StringUtil.isNotEmpty(sjqitaDizhi)) {
				sjqita.setSjqitaDizhi(sjqitaDizhi);
			}
			if (StringUtil.isNotEmpty(sjqitaDate)) {
				sjqita.setSjqitaDate(DateUtil.formatString(sjqitaDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjqitaDate1)) {
				sjqita.setSjqitaDate1(DateUtil.formatString(sjqitaDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjqitaType)) {
				sjqita.setSjqitaType(Integer.parseInt(sjqitaType));
			}
			if (StringUtil.isNotEmpty(sjqitaType1)) {
				sjqita.setSjqitaType1(Integer.parseInt(sjqitaType1));
			}
			if (StringUtil.isNotEmpty(sjqitaDouble)) {
				sjqita.setSjqitaDouble(Double.parseDouble(sjqitaDouble));
			}
			if (StringUtil.isNotEmpty(sjqitaDouble1)) {
				sjqita.setSjqitaDouble1(Double.parseDouble(sjqitaDouble1));
			}
			if (StringUtil.isNotEmpty(sjqitaId)) {
				Date date = new Date();
				sjqita.setSjqitaDate(date);
				sjqitaService.modifySjqita(sjqita);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				sjqitaService.save(sjqita);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteSjqita")
	public void deleteSjqita(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjqitaService.deleteSjqita(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjqitaComboList")
	public void sjqitaComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sjqitaType1 = (String) request.getParameter("sjqitaType1");
		String sjqitaType = (String) request.getParameter("sjqitaType");
		Sjqita sjqita = new Sjqita();
		try {
			if (StringUtil.isNotEmpty(sjqitaType)) {
				sjqita.setSjqitaType(Integer.parseInt(sjqitaType));
			}
			if (StringUtil.isNotEmpty(sjqitaType1)) {
				sjqita.setSjqitaType1(Integer.parseInt(sjqitaType1));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("sjqitaName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(sjqitaService.querySjqitas(sjqita,
					0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
