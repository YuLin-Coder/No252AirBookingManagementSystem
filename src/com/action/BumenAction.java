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
public class BumenAction {
	@Autowired
	private BumenService bumenService;
	@Autowired
	private BuyuanService buyuanService;

	@RequestMapping("/addBumen")
	public void addBumen(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String bumenName = (String) request.getParameter("bumenName");
			String bumenMark = (String) request.getParameter("bumenMark");
			String bumenMark1 = (String) request.getParameter("bumenMark1");
			String bumenMark2 = (String) request.getParameter("bumenMark2");
			String bumenType = (String) request.getParameter("bumenType");
			String bumenType1 = (String) request.getParameter("bumenType1");
			String bumenType2 = (String) request.getParameter("bumenType2");
			String bumenDouble = (String) request.getParameter("bumenDouble");
			String bumenDouble1 = (String) request.getParameter("bumenDouble1");
			String bumenDouble2 = (String) request.getParameter("bumenDouble2");
			String buyuanId = (String) request.getParameter("buyuanId");
			String bumenId = (String) request.getParameter("bumenId");
			Bumen bumen = new Bumen();
			
			if (StringUtil.isNotEmpty(bumenId)) {
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
			}
			if (StringUtil.isNotEmpty(bumenName)) {
				bumen.setBumenName(bumenName);
			}
			if (StringUtil.isNotEmpty(bumenMark)) {
				bumen.setBumenMark(bumenMark);
			}
			if (StringUtil.isNotEmpty(bumenMark1)) {
				bumen.setBumenMark1(bumenMark1);
			}
			if (StringUtil.isNotEmpty(bumenMark2)) {
				bumen.setBumenMark2(bumenMark2);
			}
			if (StringUtil.isNotEmpty(bumenType)) {
				bumen.setBumenType(Integer.parseInt(bumenType));
			}
			if (StringUtil.isNotEmpty(bumenType1)) {
				bumen.setBumenType1(Integer.parseInt(bumenType1));
			}
			if (StringUtil.isNotEmpty(bumenType2)) {
				bumen.setBumenType2(Integer.parseInt(bumenType2));
			}
			if (StringUtil.isNotEmpty(bumenDouble)) {
				bumen.setBumenDouble(Double.parseDouble(bumenDouble));
			}
			if (StringUtil.isNotEmpty(bumenDouble1)) {
				bumen.setBumenDouble1(Double.parseDouble(bumenDouble1));
			}
			if (StringUtil.isNotEmpty(bumenDouble2)) {
				bumen.setBumenDouble2(Double.parseDouble(bumenDouble2));
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				bumen.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				bumen.setBuyuanName(buyuan.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				bumenService.modifyBumen(bumen);
			} else {
				bumenService.save(bumen);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getBumens")
	public void getBumens(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String bumenName = (String) request.getParameter("bumenName");
		String bumenType = (String) request.getParameter("bumenType");
		String buyuanId = (String) request.getParameter("buyuanId");
		Bumen bumen = new Bumen();
		if (StringUtil.isNotEmpty(bumenName)) {
			bumen.setBumenName(bumenName);
		}
		if (StringUtil.isNotEmpty(bumenType)) {
			bumen.setBumenType(Integer.parseInt(bumenType));
		}
		if (StringUtil.isNotEmpty(buyuanId)) {
			bumen.setBuyuanId(Integer.parseInt(buyuanId));
		}
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			JSONArray jsonArray = JSONArray.fromObject(bumenService.queryBumens(bumen, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = bumenService.queryBumens(bumen, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteBumen")
	public void deleteBumen(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				bumenService.deleteBumen(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/bumenComboList")
	public void bumenComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String bumenType = (String) request.getParameter("bumenType");
		String buyuanId = (String) request.getParameter("buyuanId");
		Bumen bumen = new Bumen();
		if (StringUtil.isNotEmpty(bumenType)) {
			bumen.setBumenType(Integer.parseInt(bumenType));
		}
		if (StringUtil.isNotEmpty(buyuanId)) {
			bumen.setBuyuanId(Integer.parseInt(buyuanId));
		}
		try {
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("bumenName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(bumenService.queryBumens(bumen,0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
