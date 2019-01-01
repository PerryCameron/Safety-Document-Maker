package com.safety;

import java.io.IOException;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class Pdf_CheckedSteps extends Table {
	Document_Main definitions = new Document_Main();
	private SafetyObject sA;
	private int countRows = 1;
	private final int HEIGHT = 11;
	int sequenceBegin = 0;

	public Pdf_CheckedSteps(int numColumns) throws IOException {  // 4
		super(numColumns);
		this.sA = Document_Main.document.get(0);
		useAllAvailableWidth();

		setPadding(0);  // probably don't need
		setBorderBottom(Border.NO_BORDER);
		setBorderTop(Border.NO_BORDER);


		int sequenceEnd[] = { 2,4,7,9 };  // tells how many tables to put in each cell
		for(int i = 0; i < 4; i++) {  /// four cells in main table

			Cell cell = new Cell(); 
			cell.setPadding(0);
			cell.add(columnTable(sequenceEnd[i])
					.setBorder(Border.NO_BORDER)
					.setPadding(0));
			addCell(cell);
		}
	}

	public Table columnTable(int sequenceEnd) throws IOException {  // sequenceEnd tells how many tables to print
		Table column_Table = new Table(3);
		column_Table.useAllAvailableWidth();

		column_Table.setBorderBottom(Border.NO_BORDER);
		column_Table.setBorderTop(Border.NO_BORDER);
		//column_Table.setPadding(0);

		for(int i = sequenceBegin; i < sequenceEnd + 1; i++) {	// tables inside of each of 4 cells

			column_Table.addCell(new Cell(1,3)  /// header Cell
					.setFontSize(6)
					.setBorder(Border.NO_BORDER) // new
					.setHeight(HEIGHT)
					.setVerticalAlignment(VerticalAlignment.MIDDLE)
					.setTextAlignment(TextAlignment.CENTER)
					.setBackgroundColor(Document_Main.lightGreenColor)
					.add(new Paragraph(sA.getHEADERS(i))));

			for(int j = 0; j < sA.getSafetyCheckNumberOfElements(i);j++) {  // number of elements in string table


				      //map reference string       /// get reference string
					if(sA.getSafetyStepsMapValue(sA.getSafetyCheckarrays(i, j))) {
					column_Table.addCell(new Cell()
							.add(new Table(1)  /// creates small table for square around check
									.useAllAvailableWidth()
									.setHeight(13)
									.addCell(new Cell()
											.add(definitions.setCheckMark(5))))  // add check mark
							.setTextAlignment(TextAlignment.CENTER)
							.setBorderLeft(Border.NO_BORDER) 
							.setPadding(1)
							.setBorderRight(Border.NO_BORDER));

				} else {  // don't add check mark
					column_Table.addCell(new Cell()
							.add(new Table(1)  /// creates small table for square around check
									.useAllAvailableWidth()
									.setHeight(13)
									.addCell(new Cell()))
							.setBorderLeft(Border.NO_BORDER)
							.setPadding(1)
							.setBorderRight(Border.NO_BORDER));
				}
				column_Table.addCell(new Cell()  /// numbered cell
						.setFontSize(5)
						.setHeight(HEIGHT)  /// sets height for row
						.setBold()
						.setBorderLeft(Border.NO_BORDER)
						.setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE)
						.setBorderRight(Border.NO_BORDER)
						.add(new Paragraph(countRows + ")")));	
				column_Table.addCell(new Cell()  /// information cell
						.setFontSize(5)
						.setVerticalAlignment(VerticalAlignment.MIDDLE)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER) 
						.add(new Paragraph(sA.getSafetyCheckarrays(i, j))));
				countRows++;  // will number our rows
			}
			sequenceBegin++;	
		}
		return column_Table;
	}
}
