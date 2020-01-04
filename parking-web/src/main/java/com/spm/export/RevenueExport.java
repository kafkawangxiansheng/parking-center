package com.spm.export;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.spm.dto.RevenueDto;

public class RevenueExport extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\""+model.get("fileName")+"\"");

		@SuppressWarnings("unchecked")
		List<RevenueDto> revenueDtos = (List<RevenueDto>) model.get("revenueDtos");

		// create excel xls sheet
		Sheet sheet = workbook.createSheet("In-Out-Logs");
		sheet.setDefaultColumnWidth(30);

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColorPredefined.BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		font.setBold(true);
		font.setColor(HSSFColorPredefined.WHITE.getIndex());
		style.setFont(font);

		// create header row
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Loại xe");
		header.getCell(0).setCellStyle(style);
		header.createCell(1).setCellValue("Lượt vào");
		header.getCell(1).setCellStyle(style);
		header.createCell(2).setCellValue("Lượt ra");
		header.getCell(2).setCellStyle(style);
		header.createCell(3).setCellValue("Tiền thu");
		header.getCell(3).setCellStyle(style);
		
		int rowCount = 1;

		for (RevenueDto revenueDto : revenueDtos) {
			Row orderRow = sheet.createRow(rowCount++);
			orderRow.createCell(0).setCellValue(revenueDto.getLabel());
			orderRow.createCell(1).setCellValue(revenueDto.getTotalCheckin());
			orderRow.createCell(2).setCellValue(revenueDto.getTotalCheckout());
			orderRow.createCell(3).setCellValue(revenueDto.getTotalPrice());
		}

	}

}