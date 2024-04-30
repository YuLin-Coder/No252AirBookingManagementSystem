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
public class SjxingtaiAction {
	@Autowired
	private SjxingtaiService sjxingtaiService;

	@RequestMapping("/getSjxingtais")
	public void getSjxingtais(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String sjxingtaiName = (String) request.getParameter("sjxingtaiName");
		String sjxingtaiPhone = (String) request.getParameter("sjxingtaiPhone");
		String sjxingtaiId = (String) request.getParameter("sjxingtaiId");
		String sjxingtaiType1 = (String) request.getParameter("sjxingtaiType1");
		String sjxingtaiType = (String) request.getParameter("sjxingtaiType");
		String sdate = (String) request.getParameter("sdate");
		String edate = (String) request.getParameter("edate");
		Sjxingtai sjxingtai = new Sjxingtai();
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			if (StringUtil.isNotEmpty(sjxingtaiName)) {
				sjxingtai.setSjxingtaiName(sjxingtaiName);
			}
			if (StringUtil.isNotEmpty(sjxingtaiPhone)) {
				sjxingtai.setSjxingtaiPhone(sjxingtaiPhone);
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjxingtai.setSjxingtaiId(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiType)) {
				sjxingtai.setSjxingtaiType(Integer.parseInt(sjxingtaiType));
			}
			if (StringUtil.isNotEmpty(sjxingtaiType1)) {
				sjxingtai.setSjxingtaiType1(Integer.parseInt(sjxingtaiType1));
			}
			JSONArray jsonArray = JSONArray.fromObject(sjxingtaiService.querySjxingtais(
					sjxingtai, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = sjxingtaiService.querySjxingtais(sjxingtai, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addSjxingtai")
	public void addSjxingtai(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();

			String sjxingtaiName = (String) request.getParameter("sjxingtaiName");
			String sjxingtaiPhone = (String) request.getParameter("sjxingtaiPhone");
			String sjxingtaiMark = (String) request.getParameter("sjxingtaiMark");
			String sjxingtaiMark1 = (String) request.getParameter("sjxingtaiMark1");
			String sjxingtaiMark2 = (String) request.getParameter("sjxingtaiMark2");
			String sjxingtaiDizhi = (String) request.getParameter("sjxingtaiDizhi");
			String sjxingtaiDate = (String) request.getParameter("sjxingtaiDate");
			String sjxingtaiDate1 = (String) request.getParameter("sjxingtaiDate1");
			String sjxingtaiType = (String) request.getParameter("sjxingtaiType");
			String sjxingtaiType1 = (String) request.getParameter("sjxingtaiType1");
			String sjxingtaiDouble = (String) request.getParameter("sjxingtaiDouble");
			String sjxingtaiDouble1 = (String) request.getParameter("sjxingtaiDouble1");
			String sjxingtaiId = (String) request.getParameter("sjxingtaiId");
			Sjxingtai sjxingtai = new Sjxingtai();

			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				sjxingtai = sjxingtaiService.getSjxingtai(Integer.parseInt(sjxingtaiId));
			}
			if (StringUtil.isNotEmpty(sjxingtaiName)) {
				sjxingtai.setSjxingtaiName(sjxingtaiName);
			}
			if (StringUtil.isNotEmpty(sjxingtaiPhone)) {
				sjxingtai.setSjxingtaiPhone(sjxingtaiPhone);
			}
			if (StringUtil.isNotEmpty(sjxingtaiMark)) {
				sjxingtai.setSjxingtaiMark(sjxingtaiMark);
			}
			if (StringUtil.isNotEmpty(sjxingtaiMark1)) {
				sjxingtai.setSjxingtaiMark1(sjxingtaiMark1);
			}
			if (StringUtil.isNotEmpty(sjxingtaiMark2)) {
				sjxingtai.setSjxingtaiMark2(sjxingtaiMark2);
			}
			if (StringUtil.isNotEmpty(sjxingtaiDizhi)) {
				sjxingtai.setSjxingtaiDizhi(sjxingtaiDizhi);
			}
			if (StringUtil.isNotEmpty(sjxingtaiDate)) {
				sjxingtai.setSjxingtaiDate(DateUtil.formatString(sjxingtaiDate,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjxingtaiDate1)) {
				sjxingtai.setSjxingtaiDate1(DateUtil.formatString(sjxingtaiDate1,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtil.isNotEmpty(sjxingtaiType)) {
				sjxingtai.setSjxingtaiType(Integer.parseInt(sjxingtaiType));
			}
			if (StringUtil.isNotEmpty(sjxingtaiType1)) {
				sjxingtai.setSjxingtaiType1(Integer.parseInt(sjxingtaiType1));
			}
			if (StringUtil.isNotEmpty(sjxingtaiDouble)) {
				sjxingtai.setSjxingtaiDouble(Double.parseDouble(sjxingtaiDouble));
			}
			if (StringUtil.isNotEmpty(sjxingtaiDouble1)) {
				sjxingtai.setSjxingtaiDouble1(Double.parseDouble(sjxingtaiDouble1));
			}
			if (StringUtil.isNotEmpty(sjxingtaiId)) {
				Date date = new Date();
				sjxingtai.setSjxingtaiDate(date);
				sjxingtaiService.modifySjxingtai(sjxingtai);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				sjxingtaiService.save(sjxingtai);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteSjxingtai")
	public void deleteSjxingtai(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				sjxingtaiService.deleteSjxingtai(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sjxingtaiComboList")
	public void sjxingtaiComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sjxingtaiType1 = (String) request.getParameter("sjxingtaiType1");
		String sjxingtaiType = (String) request.getParameter("sjxingtaiType");
		Sjxingtai sjxingtai = new Sjxingtai();
		try {
			if (StringUtil.isNotEmpty(sjxingtaiType)) {
				sjxingtai.setSjxingtaiType(Integer.parseInt(sjxingtaiType));
			}
			if (StringUtil.isNotEmpty(sjxingtaiType1)) {
				sjxingtai.setSjxingtaiType1(Integer.parseInt(sjxingtaiType1));
			}
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("sjxingtaiName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(sjxingtaiService.querySjxingtais(sjxingtai,
					0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
