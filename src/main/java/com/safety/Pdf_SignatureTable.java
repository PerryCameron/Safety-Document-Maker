package com.safety;

import java.io.IOException;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Image;

public class Pdf_SignatureTable extends Table {
	int numberOfRows = 1;
	
	public Pdf_SignatureTable(int numColumns) throws IOException {
		super(numColumns);
		useAllAvailableWidth();
		Image image = new Image(ImageDataFactory.create(Document_Main.SIGNATURE));
		image.scaleAbsolute(80, 20);
		addCell(new Cell(1,6)
				.add(new Paragraph("All members of the crew must sign below prior to commencing work")
						.setFontSize(4)));
    	
    	for(int i = 0; i < 3; i++ ) {
		addCell(new Cell()
				.setBackgroundColor(Document_Main.lightGreenColor)
				.add(new Paragraph("Workers Name (print)").setFontSize(4)));
		addCell(new Cell()
				.setBackgroundColor(Document_Main.lightGreenColor)
				.add(new Paragraph("Signature").setFontSize(4)));
    	}
    	
    	//for(int i = 0; i < numberOfRows * 3; i++ ) {
		addCell(new Cell().add(new Paragraph("Parrish Cameron").setFontSize(4)));
		addCell(new Cell().add(image));
		addCell(new Cell().add(new Paragraph("").setFontSize(4)));
		addCell(new Cell().add(new Paragraph("").setFontSize(4)));
		addCell(new Cell().add(new Paragraph("").setFontSize(4)));
		addCell(new Cell().add(new Paragraph("").setFontSize(4)));
		
    	//}
    	
		addCell(new Cell(1,6)
				.setBold()
				.add(new Paragraph("Supervisor Name and Signature (sign upon reviewing completed POWRA):")
						.setFontSize(4)));
		
	}

	
	
}
