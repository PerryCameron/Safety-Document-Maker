package com.safety;

 
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
 
/**
 * SE001
 */

public class Pdf_Main {
    
    public static int counter = 1;
    SafetyObject sA;
    public void createPdf(String dest, SafetyObject safeA) throws IOException {
    	this.sA = safeA;

        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);
 
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
 
        // Initialize document
        Document document = new Document(pdf);
        //PageSize ps = PageSize.A5;
        
        document.add(mainPdfTable());
        document.setTopMargin(0);
        //Close document
        document.close();
        Desktop desktop = Desktop.getDesktop();
        File file = new File(Document_Main.DEST);
        if(file.exists()) desktop.open(file); 
    }
   
    public Table mainPdfTable() throws IOException {
    	Table mainTable = new Table(1);
    	mainTable.setBorder(Border.NO_BORDER);
    	Cell cell_1 = new Cell();
		Cell cell_2 = new Cell();
    	Cell cell_3 = new Cell();
		Cell cell_4 = new Cell();
    	Cell cell_5 = new Cell();
		Cell cell_6 = new Cell();
		Cell cell_7 = new Cell();
    	
    	cell_1.setBorder(Border.NO_BORDER);
    	cell_2.setBorder(Border.NO_BORDER);
    	cell_3.setBorder(Border.NO_BORDER);
    	cell_4.setBorder(Border.NO_BORDER);
    	cell_5.setBorder(Border.NO_BORDER);
    	cell_6.setBorder(Border.NO_BORDER);
    	cell_7.setBorder(Border.NO_BORDER);
		cell_1.setPadding(0);
		cell_2.setPadding(0);
		cell_3.setPadding(0);
		cell_4.setPadding(0);
		cell_5.setPadding(0);
		cell_6.setPadding(0);
		cell_7.setPadding(0);
		
        cell_1.add(new Pdf_PageHeaderTable(2)); 
        cell_2.add(new Pdf_HeaderTable(1)); 
        cell_3.add(new Pdf_CheckedSteps(4));
        cell_4.add(new Pdf_AssesmentTable(4));
        cell_5.add(new Pdf_JobStepsTable(5));
        cell_6.add(new Pdf_JobCompletionTable(1));
        cell_7.add(new Pdf_SignatureTable(6));

		mainTable.addCell(cell_1);
		mainTable.addCell(cell_2);
		mainTable.addCell(cell_3);
		mainTable.addCell(cell_4);
		mainTable.addCell(cell_5);
		mainTable.addCell(cell_6);
		mainTable.addCell(cell_7);
		
		return mainTable;
    }
}
