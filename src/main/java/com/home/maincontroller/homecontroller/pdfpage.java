package com.home.maincontroller.homecontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;

public class pdfpage extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	//	model mo=new model();
		List<model> list=(List<model>) model.get("pdf");
		  Paragraph p=new Paragraph("Cantidate Details..!");
		  p.setAlignment(Element.ALIGN_CENTER);
		  document.add(p);
		  
		  document.addTitle("Personal Details..");
		  /*Image img = Image.getInstance(m.getPhoto())*/;
		  PdfPTable table = new PdfPTable(4);
		       	table.setWidthPercentage(100.0f);
		        table.setWidths(new float[] {3.0f,3.0f,3.0f,3.0f/*,3.0f,3.0f,3.0f,3.0f*/});
		        table.setSpacingBefore(20);
		        
		        
		        Font font = FontFactory.getFont(FontFactory.HELVETICA);
		        font.setColor(BaseColor.WHITE);
		        
		        PdfPCell cell = new PdfPCell();
		        cell.setBackgroundColor(BaseColor.DARK_GRAY);
		        cell.setPadding(5);
		         
		      
		        int count=1;
		        for (model m : list) {
		        	
		        	Blob blob=m.getPhoto();
		            int blobLength = (int) blob.length();  
		            byte[] blobAsBytes = blob.getBytes(1, blobLength);
		            Image img = Image.getInstance(blobAsBytes);
		            img.scaleAbsolute(75f, 75f);
		            document.add(img);
		            //table.addCell(img);
		            
			         
			        cell.setPhrase(new Phrase("Name", font));
			        table.addCell(cell);
			        table.addCell(m.getName());
			 
			        cell.setPhrase(new Phrase("Email ID", font));
			        table.addCell(cell);
			        table.addCell(m.getEmail());
			        
			        cell.setPhrase(new Phrase("Father Name", font));
			        table.addCell(cell);
			        table.addCell(m.getFname());
			        //table.addCell(String.valueOf(count++));
			        
			        cell.setPhrase(new Phrase("Sex", font));
			        table.addCell(cell);
			        if(m.getSex().equals("M"))
			        {
			        table.addCell("MALE");
			        }
			        else
			        {
			        table.addCell("FEMALE");
			        }
			        
			        cell.setPhrase(new Phrase("Date Of Birth", font));
			        table.addCell(cell);
			        table.addCell(m.getDate());
			        
			        cell.setPhrase(new Phrase("Phone Number", font));
			        table.addCell(cell);
			        table.addCell(m.getPhone());
			        
			        /*cell.setPhrase(new Phrase("Photo", font));
			        table.addCell(cell);*/
		            
		        }
		        document.add(table);
		       /*
		        FileInputStream fis = new FileInputStream(file);
		        byte[] pdf;*/
		        
	}

}
