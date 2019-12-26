package com.spm.view;

import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.spm.dto.ProfileDto;

public class ProfileExport extends AbstractPdfView {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\""+model.get("fileName")+"\"");

		List<ProfileDto> profiles = (List<ProfileDto>) model.get("users");
		
		document.add(new Paragraph("Generated Users " + LocalDate.now()));
		if (profiles != null) {
			PdfPTable table = new PdfPTable(profiles.stream().findAny().get().getColumnCount());
			table.setWidthPercentage(100.0f);
			table.setSpacingBefore(10);

			// define font for table header row
			Font font = FontFactory.getFont(FontFactory.TIMES);
			font.setColor(Color.WHITE);

			// define table header cell
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(Color.DARK_GRAY);
			cell.setPadding(5);

			// write table header
			cell.setPhrase(new Phrase("Id", font));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Name", font));
			table.addCell(cell);

			for (ProfileDto profile : profiles) {
				table.addCell(String.valueOf(profile.getId()));
				table.addCell(profile.getName());
			}

			document.add(table);
		}
	}
}