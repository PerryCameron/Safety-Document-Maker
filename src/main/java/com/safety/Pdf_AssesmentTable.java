package com.safety;

import java.io.IOException;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class Pdf_AssesmentTable extends Table {
	
	public static final int HEIGHT = 100;
	
	
	public Pdf_AssesmentTable(int numColumns) throws IOException {
		super(numColumns);
		Image safetyImage = new Image(ImageDataFactory.create(Document_Main.SAFETY_IMG));
		
		Cell cell_1 = new Cell();
		cell_1.setPadding(0);
		cell_1.setWidth(250);
		cell_1.add(addSeverityTable());
		addCell(cell_1);
		
		Cell cell_2 = new Cell();
		cell_2.setPadding(0);

		safetyImage.scaleAbsolute(80, 67);
		cell_2.setVerticalAlignment(VerticalAlignment.MIDDLE);
		cell_2.add(safetyImage);
		addCell(cell_2);
		
		Cell cell_3 = new Cell();
		cell_3.setWidth(120);
		cell_3.setPadding(0);
		cell_3.add(addRiskAssesmentTable());
		addCell(cell_3);
		
		Cell cell_4 = new Cell();
		cell_4.setWidth(125);
		cell_4.setPadding(0);
		cell_4.add(addRiskFactorTable());
		addCell(cell_4);
	}
	
	public Table addSeverityTable() {
		Table severity = new Table(4);
		severity.useAllAvailableWidth();
		severity.setHeight(HEIGHT);
		
		Cell headerCell1 = new Cell(1,2);
		Cell headerCell2 = new Cell(1,2);
		Cell columnHeadLeft1 = new Cell();
		Cell columnHeadRight1 = new Cell();
		Cell columnHeadLeft2 = new Cell();
		Cell columnHeadRight2 = new Cell();
		
		Cell cell_1 = new Cell();
		Cell cell_1a = new Cell();
		Cell cell_1b = new Cell();
		Cell cell_1c = new Cell();

		
		Cell cell_2 = new Cell();
		Cell cell_2a = new Cell();
		Cell cell_2b = new Cell();
		Cell cell_2c = new Cell();
		
		
		Cell cell_3 = new Cell();
		Cell cell_3a = new Cell();
		Cell cell_3b = new Cell();
		Cell cell_3c = new Cell();

		///////////////////////  sub-tables //////////////////////////////
		
		headerCell1.add(new Paragraph("Severity").setFontSize(4));
		headerCell1.setBackgroundColor(Document_Main.lightGreenColor);
		headerCell1.setTextAlignment(TextAlignment.CENTER);
		headerCell1.setWidth(125);
		headerCell2.setVerticalAlignment(VerticalAlignment.MIDDLE);
		headerCell2.add(new Paragraph("Likelihood").setFontSize(4));
		headerCell2.setBackgroundColor(Document_Main.lightGreenColor);
		headerCell2.setTextAlignment(TextAlignment.CENTER);
		headerCell2.setWidth(125);
		columnHeadLeft1.add(new Paragraph("Severity").setFontSize(4));
		columnHeadLeft1.setTextAlignment(TextAlignment.CENTER);
		columnHeadRight1.add(new Paragraph("Definition").setFontSize(4));
		columnHeadRight1.setTextAlignment(TextAlignment.CENTER);
		columnHeadLeft2.add(new Paragraph("Likelihood").setFontSize(4));
		columnHeadLeft2.setTextAlignment(TextAlignment.CENTER);
		columnHeadRight2.add(new Paragraph("Definition").setFontSize(4));
		columnHeadRight2.setTextAlignment(TextAlignment.CENTER);
		//////////// greeen ////////////////
		cell_1.add(new Paragraph("Minor").setFontSize(4));
		cell_1.setBackgroundColor(Document_Main.lightGreenColor);
        List list = new List();
        	list.setSymbolIndent(12);
            list.setListSymbol("\u2022");
            list.add(new ListItem("First Aid Injury"));
            list.add(new ListItem("Property Damage < $1000"));
		
		cell_1a.add(list).setFontSize(4);
		cell_1a.setBackgroundColor(Document_Main.lightGreenColor);
		cell_1b.add(new Paragraph("Rare/Unlikely").setFontSize(4));
		cell_1b.setBackgroundColor(Document_Main.lightGreenColor);
		cell_1c.add(new Paragraph("It's not expected to happen but it may happen").setFontSize(4));
		cell_1c.setBackgroundColor(Document_Main.lightGreenColor);
		//////////// yellow ////////////////
		cell_2.add(new Paragraph("Moderate").setFontSize(4));
		cell_2.setBackgroundColor(Document_Main.yellowColor);
        List list1 = new List();
    	list1.setSymbolIndent(12);
        list1.setListSymbol("\u2022");
        list1.add(new ListItem("Medical Incident"));
        list1.add(new ListItem("Property Damage > $1,000"));
		cell_2a.add(list1).setFontSize(4);
		cell_2a.setBackgroundColor(Document_Main.yellowColor);
		cell_2b.add(new Paragraph("Possible/Likely").setFontSize(4));
		cell_2b.setBackgroundColor(Document_Main.yellowColor);
		cell_2c.add(new Paragraph("It will probably happen").setFontSize(4));
		cell_2c.setBackgroundColor(Document_Main.yellowColor);
        /////////// red //////////////////////
		cell_3.add(new Paragraph("Major").setFontSize(4));
		cell_3.setBackgroundColor(Document_Main.redColor);
        List list2 = new List();
    	list2.setSymbolIndent(12);
        list2.setListSymbol("\u2022");
        list2.add(new ListItem("Lost Time Incident"));
        list2.add(new ListItem("Fatality"));
        list2.add(new ListItem("Property Damage >$10,000"));
		cell_3a.add(list2).setFontSize(4);
		cell_3a.setBackgroundColor(Document_Main.redColor);
		cell_3b.add(new Paragraph("Almost Certain/Frequent").setFontSize(4));
		cell_3b.setBackgroundColor(Document_Main.redColor);
		cell_3c.add(new Paragraph("It will happen possibly frequently").setFontSize(4));
		cell_3c.setBackgroundColor(Document_Main.redColor);
		
		severity.addHeaderCell(headerCell1);
		severity.addHeaderCell(headerCell2);
		severity.addCell(columnHeadLeft1);
		severity.addCell(columnHeadRight1);
		severity.addCell(columnHeadLeft2);
		severity.addCell(columnHeadRight2);
		
		severity.addCell(cell_1);
		severity.addCell(cell_1a);
		severity.addCell(cell_1b);
		severity.addCell(cell_1c);

		
		severity.addCell(cell_2);
		severity.addCell(cell_2a);
		severity.addCell(cell_2b);
		severity.addCell(cell_2c);

		
		severity.addCell(cell_3);
		severity.addCell(cell_3a);
		severity.addCell(cell_3b);
		severity.addCell(cell_3c);

		return severity;
	}
		
	////////////////////////////////////////////////////////////////////////////
	
	public Table addRiskAssesmentTable() {
		Table riskAssesment = new Table(5);
		riskAssesment.useAllAvailableWidth();
		riskAssesment.setHeight(HEIGHT);
		Cell headerCell = new Cell(1,5);
		Cell leftHeaderCell = new Cell(4,1);
		Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
		Cell cell_3 = new Cell();
		Cell cell_4 = new Cell();
		Cell cell_5 = new Cell();
		Cell cell_6 = new Cell();
		Cell cell_7 = new Cell();
		Cell cell_8 = new Cell();
		Cell cell_9 = new Cell();
		Cell cell_10 = new Cell();
		Cell cell_11 = new Cell();
		Cell cell_12 = new Cell();
		Cell cell_13 = new Cell();
		Cell cell_14 = new Cell();
		Cell cell_15 = new Cell();
		Cell cell_16 = new Cell();

		headerCell.setBorderBottom(Border.NO_BORDER);
		headerCell.setTextAlignment(TextAlignment.CENTER);
		headerCell.add(new Paragraph("Severity").setFontSize(5).setHorizontalAlignment(HorizontalAlignment.CENTER));
		leftHeaderCell.setBorderTop(Border.NO_BORDER);
		leftHeaderCell.setBorderRight(Border.NO_BORDER);
		leftHeaderCell.setVerticalAlignment(VerticalAlignment.MIDDLE);
		leftHeaderCell.add(new Paragraph("Likelihood").setFontSize(5).setRotationAngle((Math.PI / 2)));
		cell_1.setBorderTop(Border.NO_BORDER);
		cell_1.setBorderLeft(Border.NO_BORDER);
		cell_1.add(new Paragraph("").setFontSize(4));
		cell_2.add(new Paragraph("Minor").setFontSize(4));
		cell_3.add(new Paragraph("Moderate").setFontSize(4));
		cell_4.add(new Paragraph("Major").setFontSize(4));
		cell_5.add(new Paragraph("Rare/Unlikely").setFontSize(4));
		cell_6.setBackgroundColor(Document_Main.lightGreenColor);
		cell_6.add(new Paragraph("Low").setFontSize(4));
		cell_7.setBackgroundColor(Document_Main.lightGreenColor);
		cell_7.add(new Paragraph("Low").setFontSize(4));
		cell_8.setBackgroundColor(Document_Main.yellowColor);
		cell_8.add(new Paragraph("Moderate").setFontSize(4));
		cell_9.add(new Paragraph("Possible/Likely").setFontSize(4));
		cell_10.setBackgroundColor(Document_Main.lightGreenColor);
		cell_10.add(new Paragraph("Low").setFontSize(4));
		cell_11.setBackgroundColor(Document_Main.yellowColor);
		cell_11.add(new Paragraph("Moderate").setFontSize(4));
		cell_12.setBackgroundColor(Document_Main.redColor);
		cell_12.add(new Paragraph("High").setFontSize(4));
		cell_13.add(new Paragraph("Certain/Frequent").setFontSize(4));
		cell_14.setBackgroundColor(Document_Main.yellowColor);
		cell_14.add(new Paragraph("Moderate").setFontSize(4));
		cell_15.setBackgroundColor(Document_Main.redColor);
		cell_15.add(new Paragraph("High").setFontSize(4));
		cell_16.setBackgroundColor(Document_Main.redColor);
		cell_16.add(new Paragraph("High").setFontSize(4));
		
		riskAssesment.addHeaderCell(headerCell);
		riskAssesment.addCell(leftHeaderCell);
		riskAssesment.addCell(cell_1);
		riskAssesment.addCell(cell_2);
		riskAssesment.addCell(cell_3);
		riskAssesment.addCell(cell_4);
		riskAssesment.addCell(cell_5);
		riskAssesment.addCell(cell_6);
		riskAssesment.addCell(cell_7);
		riskAssesment.addCell(cell_8);
		riskAssesment.addCell(cell_9);
		riskAssesment.addCell(cell_10);
		riskAssesment.addCell(cell_11);
		riskAssesment.addCell(cell_12);
		riskAssesment.addCell(cell_13);
		riskAssesment.addCell(cell_14);
		riskAssesment.addCell(cell_15);
		riskAssesment.addCell(cell_16);
		return riskAssesment;
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	public Table addRiskFactorTable() {
		Table riskFactor = new Table(2);
		riskFactor.useAllAvailableWidth();
		riskFactor.setHeight(HEIGHT);
		
		Cell headerCell = new Cell(1,2);
		Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
		Cell cell_3 = new Cell();
		Cell cell_4 = new Cell();
		Cell cell_5 = new Cell();
		Cell cell_6 = new Cell();
		Cell cell_7 = new Cell();
		Cell cell_8 = new Cell();
		
		headerCell.setBackgroundColor(Document_Main.lightGreenColor);
		headerCell.setHeight(8);
		headerCell.add(new Paragraph("Risk Factor Interpretation").setFontSize(4));
		//cell_1.setHeight(12);
		cell_1.add(new Paragraph("Risk Factor").setFontSize(4));
		cell_2.add(new Paragraph("Action").setFontSize(4));
		cell_3.setBackgroundColor(Document_Main.redColor);
		cell_3.add(new Paragraph("High").setFontSize(4));
		cell_4.setBackgroundColor(Document_Main.redColor);
		cell_4.add(new Paragraph("Work should not be started until the risk has been reduced by implementing appropriate control measures").setFontSize(4));
		cell_5.setBackgroundColor(Document_Main.yellowColor);
		cell_5.add(new Paragraph("Moderate").setFontSize(4));
		cell_6.setBackgroundColor(Document_Main.yellowColor);
		cell_6.add(new Paragraph("Risk control measures should be implemented withing a defined time period to reduce the risk.").setFontSize(4));
		cell_7.setBackgroundColor(Document_Main.lightGreenColor);
		cell_7.add(new Paragraph("Low").setFontSize(4));
		cell_8.setBackgroundColor(Document_Main.lightGreenColor);
		cell_8.add(new Paragraph("Monitoring is required to ensure that the controls are in place and maintained.  No additional controls are required.").setFontSize(4));
		
		riskFactor.addCell(headerCell);
		riskFactor.addCell(cell_1);
		riskFactor.addCell(cell_2);
		riskFactor.addCell(cell_3);
		riskFactor.addCell(cell_4);
		riskFactor.addCell(cell_5);
		riskFactor.addCell(cell_6);
		riskFactor.addCell(cell_7);
		riskFactor.addCell(cell_8);
		
		return riskFactor;
		
	}

}
