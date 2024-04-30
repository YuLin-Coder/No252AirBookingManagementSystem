package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
 

public class ExcelUtil {
 
	public static Workbook getWb(String path) {
		try {
			return WorkbookFactory.create(new File(path));
		} catch (Exception e) {
			throw new RuntimeException("读取EXCEL文件出错", e);
		}
	}
 
	public static Sheet getSheet(Workbook wb, int sheetIndex) {
		if (wb == null) {
			throw new RuntimeException("工作簿对象为空");
		}
		int sheetSize = wb.getNumberOfSheets();
		if (sheetIndex < 0 || sheetIndex > sheetSize - 1) {
			throw new RuntimeException("工作表获取错误");
		}
		return wb.getSheetAt(sheetIndex);
	}
 
	@SuppressWarnings("deprecation")
	public static List<List<String>> getExcelRows(Sheet sheet, int startLine, int endLine) {
		List<List<String>> list = new ArrayList<List<String>>();
		// 如果开始行号和结束行号都是-1的话，则全表读取
		if (startLine == -1)
			startLine = 0;
		if (endLine == -1) {
			endLine = sheet.getLastRowNum() + 1;
		} else {
			endLine += 1;
		}
		for (int i = startLine; i < endLine; i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				System.out.println("该行为空，直接跳过");
				continue;
			}
			int rowSize = row.getLastCellNum();
			List<String> rowList = new ArrayList<String>();
			for (int j = 0; j < rowSize; j++) {
				Cell cell = row.getCell(j);
				String temp = "";
				if (cell == null) {
					System.out.println("该列为空，赋值双引号");
					temp = "NULL";
				} else {
					int cellType = cell.getCellType();
					switch (cellType) {
					case Cell.CELL_TYPE_STRING:
						temp = cell.getStringCellValue().trim();
						temp = StringUtils.isEmpty(temp) ? "NULL" : temp;
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						temp = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						temp = String.valueOf(cell.getCellFormula().trim());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:hh:ss");
							temp = sdf.format(cell.getDateCellValue());
						} else {
							temp = new DecimalFormat("#.######").format(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BLANK:
						temp = "NULL";
						break;
					case Cell.CELL_TYPE_ERROR:
						temp = "ERROR";
						break;
					default:
						temp = cell.toString().trim();
						break;
					}
				}
				rowList.add(temp);
			}
			list.add(rowList);
		}
		return list;
	}
	
	public static List<List<String>> jiexiExcel(InputStream excelFile) throws Exception{
		Workbook wb = new HSSFWorkbook(excelFile);
		List<List<String>> list = getExcelRows(getSheet(wb, 0), -1, -1);
		return list;
	}
	
	public static boolean daochuExcle(List<List<String>> list,String importFilePath,String exportFilePath) throws Exception{
		/***
		 * 
		for (int i = 0; i < list.size(); i++) {
			List<String> row = list.get(i);
			for (int j = 0; j < row.size(); j++) {
				System.out.print(row.get(j) + "\t");
			}
			System.out.println();
		}
		 */
		try {
			//excel模板路径
		    File fi = new File(importFilePath);
		    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
		    //读取excel模板
		    HSSFWorkbook wb = new HSSFWorkbook(fs);
		    //读取了模板内所有sheet内容
		    HSSFSheet sheet = wb.getSheetAt(0);
		    //如果这行没有了，整个公式都不会有自动计算的效果的
		    sheet.setForceFormulaRecalculation(true);
		    //在相应的单元格进行赋值
		    for (int i = 0; i < list.size(); i++) {
		    	try{
			    	List<String> row = list.get(i);
			    	if((row!=null)&&(row.size()==11)){
				    	HSSFCell xuhao = sheet.getRow(i+1).getCell(0);
				    	xuhao.setCellValue(i+1);
						for (int j = 0; j < row.size(); j++) {
							HSSFCell cell = sheet.getRow(i+1).getCell(j+1);
							cell.setCellValue(list.get(i).get(j));
						}
			    	}
		    	}catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		    //修改模板内容导出新模板
		    FileOutputStream out = new FileOutputStream(exportFilePath);
		    wb.write(out);//输出流
		    out.close();//关闭流update
		    return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		    return false;
		}
	}
}