package com.safety;

import java.io.IOException;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class Pdf_JobCompletionTable extends Table {
	Document_Main definitions = new Document_Main();
	SafetyObject sA;
	
	public Pdf_JobCompletionTable(int numColumns) throws IOException {
		super(numColumns);
		this.sA = Document_Main.document.get(0);
		useAllAvailableWidth();
		setBorder(Border.NO_BORDER);

		Cell headerCell = new Cell();
		Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
		Cell cell_3 = new Cell();
		
		headerCell.setBackgroundColor(Document_Main.lightGreenColor);
		headerCell.add(new Paragraph("Job Completion").setFontSize(4).setTextAlignment(TextAlignment.CENTER));
		cell_1.add(questionLineTable("Were there any incident/injuries?",sA.getIncidentInjuries(),sA.getIncidentString(), false));
		cell_2.add(questionLineTable("Are there Hazards remaining?",sA.getHazardsRemain(),sA.getHazardsString(),false));
		cell_3.add(questionLineTable("Was the area cleaned up at the end of job/shift?",sA.getAreaCleanedUP(),sA.getAreaCleanedString(),true).setFontSize(4));
		
		addHeaderCell(headerCell);
		addCell(cell_1);
		addCell(cell_2);
		addCell(cell_3);
	}
	
	public Table questionLineTable(String question, int answer, String explination, boolean threeOptions) throws IOException {
		Table firstLine = new Table(9);
		firstLine.useAllAvailableWidth();
		firstLine.setBorder(Border.NO_BORDER);
		Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
		Cell cell_3 = new Cell();
		Cell cell_4 = new Cell();
		Cell cell_5 = new Cell();
		Cell cell_6 = new Cell();
		Cell cell_7 = new Cell();
		Cell cell_8 = new Cell();
		Cell cell_9 = new Cell();
		
		cell_1.setWidth(80);
		cell_1.add(new Paragraph(question).setFontSize(4));
		cell_1.setBorder(Border.NO_BORDER);
		if(answer == 1)
		cell_2.add(Document_Main.setCheckMark(4).setTextAlignment(TextAlignment.CENTER));
		cell_2.setWidth(6);
		cell_3.setBorder(Border.NO_BORDER);
		cell_3.setWidth(6);
		cell_3.add(new Paragraph("Yes").setFontSize(4));
		
		if(answer == 0)
		cell_4.add(Document_Main.setCheckMark(4).setTextAlignment(TextAlignment.CENTER));
		cell_4.setWidth(6);
		cell_5.setBorder(Border.NO_BORDER);
		cell_5.setWidth(6);
		cell_5.add(new Paragraph("No").setFontSize(4));
		
		cell_7.setBorder(Border.NO_BORDER);
		cell_7.setWidth(8);
		if(threeOptions) {  /// is the question boolean or three options
			if(answer ==2)
				cell_6.add(Document_Main.setCheckMark(4).setTextAlignment(TextAlignment.CENTER));	
			cell_6.setWidth(5);
			cell_7.add(new Paragraph("N/A").setFontSize(4));
		} else {
			cell_6.setWidth(6);
			cell_6.setBorder(Border.NO_BORDER);
		}
		cell_8.setBorder(Border.NO_BORDER);
		cell_8.setWidth(20);
		cell_8.add(new Paragraph("If Yes, explain: ").setFontSize(4));
		cell_9.add(new Paragraph(explination).setFontSize(4));
		cell_9.setBorder(Border.NO_BORDER);

		firstLine.addCell(cell_1);
		firstLine.addCell(cell_2);
		firstLine.addCell(cell_3);
		firstLine.addCell(cell_4);
		firstLine.addCell(cell_5);
		firstLine.addCell(cell_6);
		firstLine.addCell(cell_7);
		firstLine.addCell(cell_8);
		firstLine.addCell(cell_9);
		return firstLine;
	}

}
