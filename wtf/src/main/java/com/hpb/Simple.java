package com.hpb;

import java.awt.Color;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

public class Simple {
	
	
	//Colors
		Color finaBlue = getColor(0.20f, 0.63f, 0.87f);
		Color lightGray = getColor(0.94f, 0.94f, 0.94f);
		Color black = Color.BLACK;
		Color white = Color.WHITE;
		//Alignments
		TextAlignment alignLeft = TextAlignment.LEFT;
		TextAlignment alignRight = TextAlignment.RIGHT;
		TextAlignment alignCenter = TextAlignment.CENTER;
		//Borders
		Border noBorder = Border.NO_BORDER;
		Border solid0 = new SolidBorder(0);
		Border solid1 = new SolidBorder(1);
		//Temp values
		String finaLogo = "images/FINA-logo_white.jpg";
		String docFont = "fonts/AlegreyaSans-Regular.ttf";
		String[] headerTitles = { "Valuta", "Tečaj", "Iznos VAL", "Iznos HRK" };
		Paragraph docTitle = new Paragraph("Ovlašteni mjenjač Hrvatska poštanska banka d.d. ");
		
		protected Color getColor(float r, float g, float b) {
			float[] rgb = new float[] { r, g, b };
			Color rgbColor = Color.getHSBColor(0.20f, 0.63f, 0.87f);
			return rgbColor;
		}
		
	protected Cell createCell(String content, Border border, int colspan, TextAlignment alignment, Color backColor, Color fontColor) {

		Cell cell = new Cell(1, colspan).add(new Paragraph(content));
		cell.setBackgroundColor(null, colspan, colspan, colspan, colspan);
		cell.setTextAlignment(alignment);
		//cell.setFontColor(Color.BLUE);
		cell.setBorder(border);
		return cell;
	}

    public static void main (String[] args) throws Exception {
    	
    	final Logger logger = Logger.getLogger(Log4jHelloWorld.class);
            //Configure logger
            BasicConfigurator.configure();
            logger.debug("Hello World!");
       
        
    	//org.apache.log4j.BasicConfigurator.configure();
        String outputFileName = "Simple.pdf";
        if (args.length > 0)
            outputFileName = args[0];

        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page1 = new PDPage(PDRectangle.A4);
            // PDRectangle.LETTER and others are also possible
        PDRectangle rect = page1.getMediaBox();
            // rect can be used to get the page width and height
        document.addPage(page1);

        // Create a new font object selecting one of the PDF base fonts
        PDFont fontPlain = PDType1Font.HELVETICA;
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
        PDFont fontMono = PDType1Font.COURIER;

        // Start a new content stream which will hold the content that's about to be created
        PDPageContentStream cos = new PDPageContentStream(document, page1);

        int line = 0;

        // Define a text content stream using the selected font, move the cursor and draw some text
        cos.beginText();
        cos.setFont(fontPlain, 12);
        cos.newLineAtOffset(100, rect.getHeight() - 50*(++line));
        cos.showText("Hello World");
        cos.endText();

        cos.beginText();
        cos.setFont(fontItalic, 12);
        cos.newLineAtOffset(100, rect.getHeight() - 50*(++line));
        cos.showText("Italic");
        cos.endText();

        cos.beginText();
        cos.setFont(fontBold, 12);
        cos.newLineAtOffset(100, rect.getHeight() - 50*(++line));
        cos.showText("Bold");
        cos.endText();

        cos.beginText();
        cos.setFont(fontMono, 12);
        cos.setNonStrokingColor(Color.BLUE);
        cos.newLineAtOffset(100, rect.getHeight() - 50*(++line));
        cos.showText("Monospaced blue");
        cos.endText();

        // Make sure that the content stream is closed:
        cos.close();

        PDPage page2 = new PDPage(PDRectangle.A4);
        document.addPage(page2);
        cos = new PDPageContentStream(document, page2);

        // draw a red box in the lower left hand corner
        cos.setNonStrokingColor(Color.RED);
        cos.addRect(10, 10, 100, 100);
        cos.fill();

        // add two lines of different widths
        cos.setLineWidth(1);
        cos.moveTo(200, 250);
        cos.lineTo(400, 250);
        cos.closeAndStroke();
        cos.setLineWidth(5);
        cos.moveTo(200, 300);
        cos.lineTo(400, 300);
        cos.closeAndStroke();

        // add an image
        try {
            PDImageXObject ximage = PDImageXObject.createFromFile("images/finaLogo.jpg", document);
            float scale = 0.5f; // alter this value to set the image size
            cos.drawImage(ximage, 100, 400, ximage.getWidth()*scale, ximage.getHeight()*scale);
        } catch (IOException ioex) {
            System.out.println("No image for you");
        }

        // close the content stream for page 2
        cos.close();

        // Save the results and ensure that the document is properly closed:
        document.save(outputFileName);
        document.close();
    }
}