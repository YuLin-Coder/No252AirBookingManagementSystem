package com.action;

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
public class BuyuanAction {
	@Autowired
	private BuyuanService buyuanService;

	@RequestMapping("/addBuyuan")
	public void addBuyuan(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String buyuanName = (String) request.getParameter("buyuanName");
			String buyuanMark = (String) request.getParameter("buyuanMark");
			String buyuanMark1 = (String) request.getParameter("buyuanMark1");
			String buyuanMark2 = (String) request.getParameter("buyuanMark2");
			String buyuanType = (String) request.getParameter("buyuanType");
			String buyuanType1 = (String) request.getParameter("buyuanType1");
			String buyuanType2 = (String) request.getParameter("buyuanType2");
			String buyuanDouble = (String) request.getParameter("buyuanDouble");
			String buyuanDouble1 = (String) request.getParameter("buyuanDouble1");
			String buyuanDouble2 = (String) request.getParameter("buyuanDouble2");
			String buyuanId = (String) request.getParameter("buyuanId");
			Buyuan buyuan = new Buyuan();
			
			if (StringUtil.isNotEmpty(buyuanId)) {
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
			}
			if (StringUtil.isNotEmpty(buyuanName)) {
				buyuan.setBuyuanName(buyuanName);
			}
			if (StringUtil.isNotEmpty(buyuanMark)) {
				buyuan.setBuyuanMark(buyuanMark);
			}
			if (StringUtil.isNotEmpty(buyuanMark1)) {
				buyuan.setBuyuanMark1(buyuanMark1);
			}
			if (StringUtil.isNotEmpty(buyuanMark2)) {
				buyuan.setBuyuanMark2(buyuanMark2);
			}
			if (StringUtil.isNotEmpty(buyuanType)) {
				buyuan.setBuyuanType(Integer.parseInt(buyuanType));
			}
			if (StringUtil.isNotEmpty(buyuanType1)) {
				buyuan.setBuyuanType1(Integer.parseInt(buyuanType1));
			}
			if (StringUtil.isNotEmpty(buyuanType2)) {
				buyuan.setBuyuanType2(Integer.parseInt(buyuanType2));
			}
			if (StringUtil.isNotEmpty(buyuanDouble)) {
				buyuan.setBuyuanDouble(Double.parseDouble(buyuanDouble));
			}
			if (StringUtil.isNotEmpty(buyuanDouble1)) {
				buyuan.setBuyuanDouble1(Double.parseDouble(buyuanDouble1));
			}
			if (StringUtil.isNotEmpty(buyuanDouble2)) {
				buyuan.setBuyuanDouble2(Double.parseDouble(buyuanDouble2));
			}

			if (StringUtil.isNotEmpty(buyuanId)) {
				buyuanService.modifyBuyuan(buyuan);
			} else {
				buyuanService.save(buyuan);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getBuyuans")
	public void getBuyuans(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String buyuanName = (String) request.getParameter("buyuanName");
		String buyuanType = (String) request.getParameter("buyuanType");
		Buyuan buyuan = new Buyuan();
		if (StringUtil.isNotEmpty(buyuanName)) {
			buyuan.setBuyuanName(buyuanName);
		}
		if (StringUtil.isNotEmpty(buyuanType)) {
			buyuan.setBuyuanType(Integer.parseInt(buyuanType));
		}
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			JSONArray jsonArray = JSONArray.fromObject(buyuanService.queryBuyuans(buyuan, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = buyuanService.queryBuyuans(buyuan, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteBuyuan")
	public void deleteBuyuan(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				buyuanService.deleteBuyuan(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/buyuanComboList")
	public void buyuanComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String buyuanType = (String) request.getParameter("buyuanType");
		Buyuan buyuan = new Buyuan();
		if (StringUtil.isNotEmpty(buyuanType)) {
			buyuan.setBuyuanType(Integer.parseInt(buyuanType));
		}
		try {
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("buyuanName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(buyuanService.queryBuyuans(buyuan,0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
