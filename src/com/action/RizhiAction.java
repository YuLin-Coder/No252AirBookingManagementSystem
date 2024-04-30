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
public class RizhiAction {
	@Autowired
	private RizhiService rizhiService;

	@RequestMapping("/getRizhis")
	public void getRizhis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String rizhiName = (String) request.getParameter("rizhiName");
		String rizhiId = (String) request.getParameter("rizhiId");
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		Rizhi rizhi = new Rizhi();
		try {
			if (StringUtil.isNotEmpty(rizhiName)) {
				rizhi.setRizhiName(rizhiName);
			}
			if (StringUtil.isNotEmpty(rizhiId)) {
				rizhi.setRizhiId(Integer.parseInt(rizhiId));
			}
			JSONArray jsonArray = JSONArray.fromObject(rizhiService.queryRizhis(rizhi, pageBean.getStart(), pageBean.getRows()));
			JSONObject result = new JSONObject();
			int total = rizhiService.queryRizhis(rizhi, 0, 0).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteRizhi")
	public void deleteRizhi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String delIds = (String) request.getParameter("delIds");
			System.out.println("delIds = " + delIds);
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				rizhiService.deleteRizhi(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
