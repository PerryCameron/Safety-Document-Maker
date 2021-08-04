package com.safety;

import java.io.IOException;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class Pdf_HeaderTable extends Table {
	//Document_Main definitions = new Document_Main();
	
	public Pdf_HeaderTable(int numColumns) throws IOException {
		super(numColumns);
		useAllAvailableWidth();
		setBorder(Border.NO_BORDER);
		Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
		
		cell_1.setPadding(0);
		//cell_1.setHeight(18);
		cell_1.setBorderBottom(Border.NO_BORDER);
		//cell_2.setHeight(18);
		cell_2.setPadding(0);
		cell_2.setBorderTop(Border.NO_BORDER);
		
		
		
		cell_1.add(topRow());
		cell_2.add(bottomRow());
		
		addCell(cell_1);
		addCell(cell_2);
	}
	
	public Table topRow() {
		Table topTable = new Table(3);
		topTable.useAllAvailableWidth();
		topTable.setMinHeight(18);
		topTable.setPadding(0);
		Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
		Cell cell_3 = new Cell();
		cell_1.add(formatTable("Point of Work Risk Assessment Completed by:", Document_Main.document.get(0).getName()));
		System.out.println("Added " + Document_Main.document.get(0).getName());
		cell_2.add(formatTable("Scope of Work:", Document_Main.document.get(0).getScopeOfWork())); //
		cell_3.add(formatTable("Location:", Document_Main.document.get(0).getStreetAddress() + " " 
		+ Document_Main.document.get(0).getCity() + ", " + Document_Main.document.get(0).getState() + " " + Document_Main.document.get(0).getZipcode()));
		topTable.addCell(cell_1);
		topTable.addCell(cell_2);
		topTable.addCell(cell_3);
		return topTable;
	}
	
	public Table bottomRow() throws IOException {
		Table bottomTable = new Table(5);
		bottomTable.useAllAvailableWidth();
		bottomTable.setPadding(0);
		bottomTable.setMinHeight(20);
		Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
		Cell cell_3 = new Cell();
		Cell cell_4 = new Cell();
		Cell cell_5 = new Cell();
		cell_1.add(formatTable("Customer:", Document_Main.document.get(0).getCustomer()));
		cell_2.add(formatTable("Date:", Document_Main.document.get(0).getDate())); //
		cell_3.add(formatTable("Job Number:", Document_Main.document.get(0).getWorkOrder()));
		cell_4.add(formatTable("Subcontractor:", Document_Main.document.get(0).getSubContractor())); //
		cell_5.add(energizedWork());
		bottomTable.addCell(cell_1);
		bottomTable.addCell(cell_2);
		bottomTable.addCell(cell_3);
		bottomTable.addCell(cell_4);
		bottomTable.addCell(cell_5);
		return bottomTable;
	}
	
	public Table energizedWork() throws IOException {
		Table energizedWork = new Table(5);
		setBorder(Border.NO_BORDER);
		Cell top_left = new Cell(1,4);
		Cell top_right = new Cell();
		Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
		Cell cell_3 = new Cell();
		Cell cell_4 = new Cell();
		Cell cell_5 = new Cell();
		
		top_left.setBorder(Border.NO_BORDER);
		top_left.add(new Paragraph("Energized work? ").setFontSize(5).setTextAlignment(TextAlignment.CENTER));
		top_right.setBorder(Border.NO_BORDER);
		top_right.setPaddingLeft(8);
		top_right.setBold();
		top_right.add(new Paragraph("Energized Repair, Modification, or Cleaning is PROHIBITED.")
				.setFontSize(5)
				.setFontColor(Document_Main.redColor));
		cell_1.setWidth(6);
		cell_2.setBorder(Border.NO_BORDER);
		cell_2.setVerticalAlignment(VerticalAlignment.MIDDLE);
		cell_2.add(new Paragraph("Yes").setFontSize(4));
		cell_3.setWidth(6);
		if(Document_Main.document.get(0).getEnergizedWork()) {  /// if energized work box is checked
		cell_1.add(Document_Main.setCheckMark(4).setTextAlignment(TextAlignment.CENTER));
		cell_3.add(new Paragraph("").setTextAlignment(TextAlignment.CENTER));
		} else {
		cell_3.add(Document_Main.setCheckMark(4).setTextAlignment(TextAlignment.CENTER));
		cell_1.add(new Paragraph("").setTextAlignment(TextAlignment.CENTER));
		}
		cell_4.setBorder(Border.NO_BORDER);
		cell_4.setVerticalAlignment(VerticalAlignment.MIDDLE);
		cell_4.add(new Paragraph("No").setFontSize(4));
		cell_5.setBorder(Border.NO_BORDER);
		cell_5.setPaddingLeft(8);
		cell_5.setVerticalAlignment(VerticalAlignment.MIDDLE);
		cell_5.add(new Paragraph("If yes STOP WORK contact supervisor").setFontSize(4));
		
		energizedWork.addCell(top_left);
		energizedWork.addCell(top_right);
		energizedWork.addCell(cell_1);
		energizedWork.addCell(cell_2);
		energizedWork.addCell(cell_3);
		energizedWork.addCell(cell_4);
		energizedWork.addCell(cell_5);
		
		return energizedWork;
	}
	
	public Table formatTable(String heading, String data) {
		Table formatTable = new Table(1);
		formatTable.setPadding(0);
		Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
		
		cell_1.setBorder(Border.NO_BORDER);
		cell_1.setWidth(6);
		cell_1.add(new Paragraph(heading).setFontSize(4).setTextAlignment(TextAlignment.LEFT));
		cell_2.setBorder(Border.NO_BORDER);
		cell_2.setVerticalAlignment(VerticalAlignment.MIDDLE);
		cell_2.add(new Paragraph(data).setFontSize(6).setTextAlignment(TextAlignment.CENTER));
		formatTable.addCell(cell_1);
		formatTable.addCell(cell_2);
		
		return formatTable; 
	}

}
