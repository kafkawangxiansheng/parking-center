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

import com.spm.dto.OrderDto;

public class InOutExport extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\""+model.get("fileName")+"\"");

		@SuppressWarnings("unchecked")
		List<OrderDto> orderDtos = (List<OrderDto>) model.get("orders");

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
		header.createCell(0).setCellValue("STT");
		header.getCell(0).setCellStyle(style);
		header.createCell(1).setCellValue("Số thẻ");
		header.getCell(1).setCellStyle(style);
		header.createCell(2).setCellValue("Thời gian vào");
		header.getCell(2).setCellStyle(style);
		header.createCell(3).setCellValue("Thời gian ra");
		header.getCell(3).setCellStyle(style);
		header.createCell(4).setCellValue("Biển số ĐK");
		header.getCell(4).setCellStyle(style);
		header.createCell(5).setCellValue("Biển số vào");
		header.getCell(5).setCellStyle(style);
		header.createCell(6).setCellValue("Biển số ra");
		header.getCell(6).setCellStyle(style);
		header.createCell(7).setCellValue("Người vào");
		header.getCell(7).setCellStyle(style);
		header.createCell(8).setCellValue("Người ra");
		header.getCell(8).setCellStyle(style);
		header.createCell(9).setCellValue("Tên loại xe");
		header.getCell(9).setCellStyle(style);
		header.createCell(10).setCellValue("Mất thẻ");
		header.getCell(10).setCellStyle(style);
		header.createCell(11).setCellValue("Giá tiền");
		header.getCell(11).setCellStyle(style);

		int rowCount = 1;

		for (OrderDto orderDto : orderDtos) {
			Row orderRow = sheet.createRow(rowCount++);
			orderRow.createCell(0).setCellValue(orderDto.getOrderId());
			orderRow.createCell(1).setCellValue(orderDto.getCardStt());
			orderRow.createCell(2).setCellValue(orderDto.getCheckinTimeInFormat());
			orderRow.createCell(3).setCellValue(orderDto.getCheckoutTimeInFormat());
			orderRow.createCell(4).setCellValue(orderDto.getCarNumber());
			orderRow.createCell(5).setCellValue(orderDto.getCarNumberIn());
			orderRow.createCell(6).setCellValue(orderDto.getCarNumberOut());
			orderRow.createCell(7).setCellValue(orderDto.getAdminCheckinId());
			orderRow.createCell(8).setCellValue(orderDto.getAdminCheckoutId());
			orderRow.createCell(9).setCellValue(orderDto.getVehicleName());
			orderRow.createCell(10).setCellValue(orderDto.getIsCardLost());
			orderRow.createCell(11).setCellValue(orderDto.getTotalPrice());

		}

	}

}