package com.safety;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class Pdf_JobStepsTable extends Table {
	int numberOfColumns;
	int numberOfLines = 7;

	static String[] jobStepsHeaders = {
			"#",
			"JOB STEPS",
			"HAZARDS",
			"RISK =",
			"CONTROL MEASURES",
		};

	public Pdf_JobStepsTable(int numColumns) {
		super(numColumns);
		this.numberOfColumns = numColumns;
		useAllAvailableWidth();

		Cell headerCell = new Cell(1,5);
		headerCell.add(new Paragraph("Identify and Prioritize the tasks and hazards below, then identify and implement control measures.")
				.setBold()
				.setFontColor(Document_Main.redColor)
				.setFontSize(8));
		addHeaderCell(headerCell);

		// Identify and Prioritize the tasks and hazards below, then identify and implement control measures.   Risk Matrix on next page.

		for(int i=0; i< numberOfColumns;i++) {
			addCell(new Cell()
					.setBackgroundColor(Document_Main.lightGreenColor)
					//.setWidth(12)
					.add(new Paragraph(jobStepsHeaders[i]).setFontSize(4)));
		}
		int j = 1;
		int k = 0;
		for(int i=0; i< (numberOfColumns * Document_Main.document.get(0).getStepSize()); i++) {
			if(((numberOfColumns * Document_Main.document.get(0).getStepSize())-i) %5 == 0) {
				addCell(new Cell().add(new Paragraph(j + "").setFontSize(4)));  // adds number cell
				j++; // numbers the rows
			} else {
				k++;	
				switch (k) {
				case 1:  addCell(new Cell().add(new Paragraph(Document_Main.document.get(0).getSteps(j-2).getJobSteps()).setFontSize(4)));
					// addCell(new Cell().add(new Paragraph(j-2 + "").setFontSize(4)));
				break;
				case 2:  addCell(new Cell().add(new Paragraph(Document_Main.document.get(0).getSteps(j-2).getHazards()).setFontSize(4)));
					// addCell(new Cell().add(new Paragraph(j-2 + "").setFontSize(4)));
				break;
				case 3:  addCell(new Cell().add(new Paragraph(Document_Main.document.get(0).getSteps(j-2).getRisk()).setFontSize(4)));
					// addCell(new Cell().add(new Paragraph(j-2 + "").setFontSize(4)));
				break;
				case 4:  addCell(new Cell().add(new Paragraph(Document_Main.document.get(0).getSteps(j-2).getControls()).setFontSize(4)));
					// addCell(new Cell().add(new Paragraph(j-2 + "").setFontSize(4)));
				break;
				}
			}
			
			if(k == 4) k = 0;
		}
	}
}
