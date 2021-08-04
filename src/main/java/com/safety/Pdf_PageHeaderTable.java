package com.safety;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class Pdf_PageHeaderTable extends Table {

	public Pdf_PageHeaderTable(int numColumns) {
		super(numColumns);
		useAllAvailableWidth();
		setBorder(Border.NO_BORDER);
		setPadding(0);
		Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
		cell_1.setPaddingLeft(10);
		cell_1.setBorder(Border.NO_BORDER);
		cell_1.add(new Paragraph("SCHNEIDER ELECTRIC NORTH AMERICA Services\n"
				+ "Safety, Environmental, and Energy Management System\n"
				+ "Name: Point of Work Risk Assessment (POWRA/FLRA)")
				.setFontColor(Document_Main.blueColor)
				.setFontSize(7));
		//cell_2.setHeight(18);
		cell_2.setPaddingRight(10);
		cell_2.setBorder(Border.NO_BORDER);
		cell_2.add(new Paragraph("Procedure Number: SE001 Attachment B\n"
				+ "Page 1 of 1\n"
				+ "Date Effective: 4/18/2018")
				.setTextAlignment(TextAlignment.RIGHT)
				.setFontColor(Document_Main.blueColor)
				.setFontSize(7));
		
		addCell(cell_1);
		addCell(cell_2);

	}

}
