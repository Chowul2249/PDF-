package com.home.maincontroller.homecontroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class excel extends AbstractExcelView  {

	@Override
	protected void buildExcelDocument(Map<String, Object> m,
			HSSFWorkbook workbook, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {
		// TODO Auto-generated method stub
		HSSFSheet excelSheet = workbook.createSheet("data");
		setExcelHeader(excelSheet);	
		
		List<model> list = (List<model>) m.get("data");
		setExcelRows(excelSheet,list);
		
	}
	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("S.No");
		excelHeader.createCell(1).setCellValue("Name");
		excelHeader.createCell(2).setCellValue("FatherName");
		excelHeader.createCell(3).setCellValue("Email");
		excelHeader.createCell(4).setCellValue("Date");
		excelHeader.createCell(5).setCellValue("Sex");
		excelHeader.createCell(6).setCellValue("Phone");
	}
	public void setExcelRows(HSSFSheet excelSheet, List<model> model){
		int record = 1;
		int count=1;
		for (model m : model) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(count++);
			excelRow.createCell(1).setCellValue(m.getName());
			excelRow.createCell(2).setCellValue(m.getFname());
			excelRow.createCell(3).setCellValue(m.getEmail());
			excelRow.createCell(4).setCellValue(m.getDate());
			excelRow.createCell(5).setCellValue(m.getSex());
			excelRow.createCell(6).setCellValue(m.getPhone());
		//	excelRow.createCell(7).setCellType(m.getPhoto());
			
			
		}

	}
}
