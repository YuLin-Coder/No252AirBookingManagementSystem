package com.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.model.*;
import com.service.*;
import com.util.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
//导入导出

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@Controller
public class GonggaoAction {
	@Autowired
	private GonggaoService gonggaoService;
	@Autowired
	private GgtypeService ggtypeService;

	/***上传导入开始***/
	private InputStream excelFile;

	public InputStream getExcelFile() {
		return excelFile;
	}
	/***上传导入结束***/

	@RequestMapping("/getGonggaos")
	public void getGonggaos(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = (String) request.getParameter("page");
		String rows = (String) request.getParameter("rows");
		String gonggaoName = (String) request.getParameter("gonggaoName");
		String gonggaoId = (String) request.getParameter("gonggaoId");
		String ggtypeId = (String) request.getParameter("ggtypeId");
		String sdate = (String) request.getParameter("sdate");
		String edate = (String) request.getParameter("edate");
		PageBean pageBean = null;
		if ((StringUtil.isNotEmpty(page))&&(!page.equals("null"))) {
			pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		}else{
			pageBean = new PageBean(0,0);
		}
		Gonggao gonggao = new Gonggao();
		try {
			if (StringUtil.isNotEmpty(gonggaoName)) {
				gonggao.setGonggaoName(gonggaoName);
			}
			if (StringUtil.isNotEmpty(gonggaoId)) {
				gonggao.setGonggaoId(Integer.parseInt(gonggaoId));
			}
			if (StringUtil.isNotEmpty(ggtypeId)) {
				gonggao.setGgtypeId(Integer.parseInt(ggtypeId));
			}
			JSONArray jsonArray = JSONArray.fromObject(gonggaoService.queryGonggaos(
					gonggao, pageBean.getStart(), pageBean.getRows(), sdate, edate));
			JSONObject result = new JSONObject();
			int total = gonggaoService.queryGonggaos(gonggao, 0, 0, sdate, edate).size();
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/addGonggao")
	public void addGonggao(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String gonggaoName = (String) request.getParameter("gonggaoName");
		String gonggaoMark = (String) request.getParameter("gonggaoMark");
		String gonggaoDate = (String) request.getParameter("gonggaoDate");
		String ggtypeId = (String) request.getParameter("ggtypeId");
		String gonggaoId = (String) request.getParameter("gonggaoId");
		Gonggao gonggao = new Gonggao();

		if (StringUtil.isNotEmpty(gonggaoId)) {
			gonggao = gonggaoService.getGonggao(Integer.parseInt(gonggaoId));
		}
		if (StringUtil.isNotEmpty(gonggaoName)) {
			gonggao.setGonggaoName(gonggaoName);
		}
		if (StringUtil.isNotEmpty(gonggaoMark)) {
			gonggao.setGonggaoMark(gonggaoMark);
		}
		if (StringUtil.isNotEmpty(gonggaoDate)) {
			gonggao.setGonggaoDate(DateUtil.formatString(gonggaoDate,
					"yyyy-MM-dd HH:mm:ss"));
		}
		if (StringUtil.isNotEmpty(ggtypeId)) {
			gonggao.setGgtypeId(Integer.parseInt(ggtypeId));
		}
		try {
			if (StringUtil.isNotEmpty(gonggaoId)) {
				gonggaoService.modifyGonggao(gonggao);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			} else {
				Date date = new Date();
				gonggao.setGonggaoDate(date);
				gonggaoService.save(gonggao);
				result.put("success", "true");
				ResponseUtil.write(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/deleteGonggao")
	public void deleteGonggao(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		String delIds = (String) request.getParameter("delIds");
		try {
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				gonggaoService.deleteGonggao(Integer.parseInt(str[i]));
			}
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/gonggaoComboList")
	public void gonggaoComboList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String ggtypeId = (String) request.getParameter("ggtypeId");
		Gonggao gonggao = new Gonggao();
		if (StringUtil.isNotEmpty(ggtypeId)) {
			gonggao.setGgtypeId(Integer.parseInt(ggtypeId));
		}
		try {
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "");
			jsonObject.put("gonggaoName", "请选择...");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JSONArray.fromObject(gonggaoService.queryGonggaos(gonggao, 0, 0, null, null)));
			ResponseUtil.write(response, jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/gonggaoTongji")
	public void gonggaoTongji(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sdate=request.getParameter("sdate");
		String edate=request.getParameter("edate");
		List<Integer> ggtypeIds = new ArrayList<Integer>();
		List<String> ggtypeNames = new ArrayList<String>();
		List<Integer> gonggaoZongshus = new ArrayList<Integer>();
		List<Ggtype> ggtypes = new ArrayList<Ggtype>();
		List<Gonggao> gonggaos = new ArrayList<Gonggao>();
		Gonggao gonggao = new Gonggao();
		Integer zongshu = 0;
		try {
			ggtypes = ggtypeService.queryGgtypes(null, 0,0);
			for(int i=0;i<ggtypes.size();i++){
				ggtypeIds.add(ggtypes.get(i).getGgtypeId());
				ggtypeNames.add(ggtypes.get(i).getGgtypeName());
			}
			for(int i=0;i<ggtypeIds.size();i++){
				Integer gonggaoZongshu = 0;
				gonggao.setGgtypeId(ggtypeIds.get(i));
				gonggaos = gonggaoService.queryGonggaos(gonggao, 0, 0, sdate, edate);
				for(int j=0;j<gonggaos.size();j++){
					gonggaoZongshu = gonggaoZongshu + gonggaos.size();
				}
				zongshu = zongshu + gonggaoZongshu;
				gonggaoZongshus.add(gonggaoZongshu);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("ggtypeNames", ggtypeNames);
			session.setAttribute("gonggaoZongshus", gonggaoZongshus);
			session.setAttribute("zongshu", zongshu);
			response.sendRedirect("admin/gonggaotongji.jsp");	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/shangchuanGonggao")
	public void shangchuanGonggao(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String gonggaoId = (String) request.getParameter("gonggaoId");
			String directory = "/file";
			String targetDirectory = request.getSession().getServletContext().getRealPath(directory);
	        String fileName = uploadFile.getOriginalFilename();  
			File dir = new File(targetDirectory,fileName);        
	        if(!dir.exists()){
	            dir.mkdirs();
	        }
	        //MultipartFile自带的解析方法
	        uploadFile.transferTo(dir);

			String shangchuandizhi = "/file" + "/" + fileName;
			String shangchuanname = fileName;
			Gonggao gonggao = gonggaoService.getGonggao(Integer.parseInt(gonggaoId));
			gonggao.setGonggaoImg(shangchuandizhi);
			gonggao.setGonggaoImgName(shangchuanname);
			gonggaoService.modifyGonggao(gonggao);
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/xiazaiGonggao")
	public void xiazaiGonggao(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String filename = (String) request.getParameter("filename");
        //模拟文件，myfile.txt为需要下载的文件  
        String path = request.getSession().getServletContext().getRealPath("file")+"\\"+filename;  
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();
	}

	@RequestMapping("/daoruGonggao")
	public void daoruGonggao(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)
			throws Exception {
		try {
			String directory = "/file";
			String targetDirectory = request.getSession().getServletContext().getRealPath(directory);
	        String fileName = uploadFile.getOriginalFilename();  
			File dir = new File(targetDirectory,fileName);        
	        if(!dir.exists()){
	            dir.mkdirs();
	        }
	        //MultipartFile自带的解析方法
	        uploadFile.transferTo(dir);
			excelFile = new FileInputStream(dir);
			Workbook wb = new HSSFWorkbook(excelFile);
			Sheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum() + 1;
			for (int i = 1; i < rowNum; i++) {
				Gonggao gonggao = new Gonggao();
				Row row = sheet.getRow(i);
				int cellNum = row.getLastCellNum();
				for (int j = 0; j < cellNum; j++) {
					Cell cell = row.getCell(j);
					String cellValue = null;
					switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
					case 0:
						cellValue = String.valueOf((int) cell
								.getNumericCellValue());
						break;
					case 1:
						cellValue = cell.getStringCellValue();
						break;
					case 2:
						cellValue = cell.getStringCellValue();
						break;
					}

					switch (j) {// 通过列数来判断对应插如的字段
					case 1:
						gonggao.setGonggaoName(cellValue);
						break;
					case 2:
						gonggao.setGonggaoMark(cellValue);
						break;
					}
				}
				gonggaoService.save(gonggao);
			}
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/daochuGonggao")
	public void daochuGonggao(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String delIds = (String) request.getParameter("delIds");
		JSONObject result = new JSONObject();
		String str[] = delIds.split(",");

		// 创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建一个工作表
		HSSFSheet sheet = workbook.createSheet("gonggaos记录");
		// 添加表头行
		HSSFRow hssfRow = sheet.createRow(0);
		// 设置单元格格式居中
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 添加表头内容
		HSSFCell headCell = hssfRow.createCell(0);
		headCell.setCellValue("编号");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(1);
		headCell.setCellValue("用户名");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(2);
		headCell.setCellValue("密码");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(3);
		headCell.setCellValue("姓名");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(4);
		headCell.setCellValue("性别");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(5);
		headCell.setCellValue("年龄");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(6);
		headCell.setCellValue("电话");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(7);
		headCell.setCellValue("备注1");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(8);
		headCell.setCellValue("备注2");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(9);
		headCell.setCellValue("备注3");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(10);
		headCell.setCellValue("备注4");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(13);
		headCell.setCellValue("标志1");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(14);
		headCell.setCellValue("备注2");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(15);
		headCell.setCellValue("权限");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(16);
		headCell.setCellValue("部门");
		headCell.setCellStyle(cellStyle);

		// 添加数据内容
		for (int i = 0; i < str.length; i++) {
			hssfRow = sheet.createRow((int) i + 1);
			Gonggao gonggao = gonggaoService.getGonggao(Integer.parseInt(str[i]));

			// 创建单元格，并设置值
			HSSFCell cell = hssfRow.createCell(0);
			cell.setCellValue(gonggao.getGonggaoId());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(1);
			cell.setCellValue(gonggao.getGonggaoName());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(7);
			cell.setCellValue(gonggao.getGonggaoMark());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(16);
			cell.setCellValue(gonggao.getGgtypeName());
			cell.setCellStyle(cellStyle);
		}

		// 保存Excel文件
		try {
			Date date = new Date();
			String strdate = DateUtil.formatDate(date, "yyyyMMddhhmmss");
			OutputStream outputStream = new FileOutputStream("D:/gonggao"
					+ strdate + ".xls");
			workbook.write(outputStream);
			outputStream.close();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
