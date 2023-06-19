package com.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Createpdf {

	public static byte[] generaPDFfattura(String Title, String Quantity, String Price)
	{
		
			PDDocument doc= new PDDocument();
			PDPage page= new PDPage(PDRectangle.A4);
			doc.addPage(page);
			
			try 
			{
				
				PDPageContentStream contentStream = new PDPageContentStream(doc, page);
				PDImageXObject image= PDImageXObject.createFromFile("../images/1/logo.png", doc);
				float x=0;
				float y=page.getMediaBox().getHeight()-image.getHeight();
				contentStream.drawImage(image, x, y);
				
				
				float margin=50;
				float yStart = page.getMediaBox().getHeight() - margin - image.getHeight();
				float tableWidth = page.getMediaBox().getWidth() - 2*margin;
				float yPosition = yStart;
				float tableHeight = 100f;
				float cellMargin= 10f;
				
				int rows =3;
				int cols =3;
				
				float[] columnsWidth = {100f, 100f, 100f};
				float tableRowHeight = 20f;
				
				//disegno linee orizzontali
				float nextY = yPosition;
				
				for(int i=0; i <= rows; i++)
				{
					contentStream.moveTo(margin, nextY);
					contentStream.lineTo(margin+tableWidth, nextY);
					contentStream.stroke();
					nextY -= tableRowHeight;
				}
				
				//disegno linee verticali;
				
				float yVert = yPosition;
				
				for( int i=0; i<=cols; i++)
				{
					contentStream.moveTo(margin+ i*columnsWidth[i], yVert);
					contentStream.lineTo(margin+ i*columnsWidth[i], yVert-tableHeight);
					contentStream.stroke();
				}
				
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				float textX = margin + cellMargin;
				float textY = y-15;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(textX, textY);
                    contentStream.showText(Title);
                    contentStream.endText();
                    textX += columnsWidth[1];
                    contentStream.beginText();
                    contentStream.newLineAtOffset(textX, textY);
                    contentStream.showText(Quantity);
                    contentStream.endText();
                    textX += columnsWidth[2];
                    contentStream.beginText();
                    contentStream.newLineAtOffset(textX, textY);
                    contentStream.showText(Price);
                    contentStream.endText();
                    
                 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
