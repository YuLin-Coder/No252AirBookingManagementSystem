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
public class BuzhiAction {
	@Autowired
	private BuzhiService buzhiService;
	@Autowired
	private BuyuanService buyuanService;
	@Autowired
	private BumenService bumenService;

	@RequestMapping("/addBuzhi")
	public void addBuzhi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject result = new JSONObject();
			
			String buzhiName = (String) request.getParameter("buzhiName");
			String buzhiMark = (String) request.getParameter("buzhiMark");
			String buzhiMark1 = (String) request.getParameter("buzhiMark1");
			String buzhiMark2 = (String) request.getParameter("buzhiMark2");
			String buzhiType = (String) request.getParameter("buzhiType");
			String buzhiType1 = (String) request.getParameter("buzhiType1");
			String buzhiType2 = (String) request.getParameter("buzhiType2");
			String buzhiDouble = (String) request.getParameter("buzhiDouble");
			String buzhiDouble1 = (String) request.getParameter("buzhiDouble1");
			String buzhiDouble2 = (String) request.getParameter("buzhiDouble2");
			String buyuanId = (String) request.getParameter("buyuanId");
			String bumenId = (String) request.getParameter("bumenId");
			String buzhiId = (String) request.getParameter("buzhiId");
			Buzhi buzhi = new Buzhi();
			
			if (StringUtil.isNotEmpty(buzhiId)) {
				buzhi = buzhiService.getBuzhi(Integer.parseInt(buzhiId));
			}
			if (StringUtil.isNotEmpty(buzhiName)) {
				buzhi.setBuzhiName(buzhiName);
			}
			if (StringUtil.isNotEmpty(buzhiMark)) {
				buzhi.setBuzhiMark(buzhiMark);
			}
			if (StringUtil.isNotEmpty(buzhiMark1)) {
				buzhi.setBuzhiMark1(buzhiMark1);
			}
			if (StringUtil.isNotEmpty(buzhiMark2)) {
				buzhi.setBuzhiMark2(buzhiMark2);
			}
			if (StringUtil.isNotEmpty(buzhiType)) {
				buzhi.setBuzhiType(Integer.parseInt(buzhiType));
			}
			if (StringUtil.isNotEmpty(buzhiType1)) {
				buzhi.setBuzhiType1(Integer.parseInt(buzhiType1));
			}
			if (StringUtil.isNotEmpty(buzhiType2)) {
				buzhi.setBuzhiType2(Integer.parseInt(buzhiType2));
			}
			if (StringUtil.isNotEmpty(buzhiDouble)) {
				buzhi.setBuzhiDouble(Double.parseDouble(buzhiDouble));
			}
			if (StringUtil.isNotEmpty(buzhiDouble1)) {
				buzhi.setBuzhiDouble1(Double.parseDouble(buzhiDouble1));
			}
			if (StringUtil.isNotEmpty(buzhiDouble2)) {
				buzhi.setBuzhiDouble2(Double.parseDouble(buzhiDouble2));
			}
			if (StringUtil.isNotEmpty(bumenId)) {
				buzhi.setBumenId(Integer.parseInt(bumenId));
				Bumen bumen = new Bumen();
				bumen = bumenService.getBumen(Integer.parseInt(bumenId));
				buzhi.setBumenName(bumen.getBumenName());
				buzhi.setBuyuanId(bumen.getBuyuanId());
				buzhi.setBuyuanName(bumen.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(buyuanId)) {
				buzhi.setBuyuanId(Integer.parseInt(buyuanId));
				Buyuan buyuan = new Buyuan();
				buyuan = buyuanService.getBuyuan(Integer.parseInt(buyuanId));
				buzhi.setBuyuanName(buyuan.getBuyuanName());
			}
			if (StringUtil.isNotEmpty(buzhiId)) {
				buzhiService.modifyBuzhi(buzhi);
			} else {
				buzhiService.save(buzhi);
			}
			result.put("success", "true");

			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getBuzhis")
	public void getBuzhis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String buzhiName = (String) request.getParameter("buzhiName");
		String buzhiType = (String) request.getParameter("buzhiType");
		String buyuanId = (String) request.getParameter("buyuanId");
		String bumenId = (String) request.getParameter("bumenId");
		Buzhi buzhi = new Buzhi();
		if (StringUtil.isNotEmpty(buzhiName)) {
			buzhi.setBuzhiName(buzhiName);
		}
		if (StringUtil.isNotEmpty(buzhiType)) {
			buzhi.setBuzhiType(Integer.parseInt(buzhiType));
		}
		if (StringUtil.isNotEmpty(buyuanId)) {
			buzhi.setBuyuanId(Integer.parseInt(buyuanId));
		}
		if (StringUtil.isNotEmpty(bumenId)) {
			buzhi.setBumenId(Integer.parseInt(bumenId));
		}
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		try {
			JSONArray jsonArray = JSONArray.fromObject(buzhiService.queryBuzhis(buzhi, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = buzhiService.queryBuzhis(buzhi, 0,0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteBuzhi")
	public void deleteBuzhi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				buzhiService.deleteBuzhi(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/buzhiComboList")
	public void buzhiComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String buzhiName = (String) request.getParameter("buzhiName");
		String buzhiType = (String) request.getParameter("buzhiType");
		String buyuanId = (String) request.getParameter("buyuanId");
		String bumenId = (String) request.getParameter("bumenId");
		Buzhi buzhi = new Buzhi();
		if (StringUtil.isNotEmpty(buzhiName)) {
			buzhi.setBuzhiName(buzhiName);
		}
		if (StringUtil.isNotEmpty(buzhiType)) {
			buzhi.setBuzhiType(Integer.parseInt(buzhiType));
		}
		if (StringUtil.isNotEmpty(buyuanId)) {
			buzhi.setBuyuanId(Integer.parseInt(buyuanId));
		}
		if (StringUtil.isNotEmpty(bumenId)) {
			buzhi.setBumenId(Integer.parseInt(bumenId));
		}
		try {
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("buzhiName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(buzhiService.queryBuzhis(buzhi,0,0)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
