package com.hpb;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.fontbox.util.autodetect.FontFileFinder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;

import be.quodlibet.boxable.*;
import be.quodlibet.boxable.line.LineStyle;
import be.quodlibet.boxable.text.WrappingFunction;


public class SimpleTable {
	
	    //Colors
		static Color finaBlue = Color.getHSBColor(0.65f, 31, 26); 
		static Color lightGray = Color.getHSBColor(0.65f, 96, 1);
		static Color black = Color.BLACK;
		static Color white = Color.WHITE;
		
		static HorizontalAlignment hCenter = HorizontalAlignment.CENTER;
		static HorizontalAlignment hLeft = HorizontalAlignment.LEFT;
		static HorizontalAlignment hRight = HorizontalAlignment.RIGHT;
		
		static VerticalAlignment vMidlle = VerticalAlignment.MIDDLE;
		static VerticalAlignment vTop = VerticalAlignment.TOP;
		static VerticalAlignment vBottom = VerticalAlignment.BOTTOM;
		
		//font sizes
		static int defaultfontSize =10;
		static int headerFontSize = 15;
		static int cellHeaderFontSize = 12;

    public static void main (String[] args) throws Exception {
    	
    	

     /*  for (URI uri : fontURIs) {
            File font = new File(uri);
            if (font.getName().equals("Arial.TTF")) {
                fontFile = font;
            }
        }
    	
        
     */
      
       
    	
    	
    	
        String outputFileName = "generatedFiles/SimpleTable.pdf";
        
        

        // Create a new font object selecting one of the PDF base fonts
       /* PDFont fontPlain = PDType0Font.load(doc, "Arial.ttf", true);
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
        PDFont fontMono = PDType1Font.COURIER;*/

        // Create a document and add a page to it
        PDDocument document = new PDDocument();
      
        PDPage page = new PDPage(PDRectangle.A4);
        // PDRectangle.LETTER and others are also possible
        PDRectangle rect = page.getMediaBox();
        // rect can be used to get the page width and height
        document.addPage(page);

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream cos = new PDPageContentStream(document, page);
        
    
        
        FontFileFinder fontFinder = new FontFileFinder();
        List<URI> fontURIs = fontFinder.find();

        File fontFile = new File( "fonts/Lacquer-Regular.ttf");
        PDFont font1 = PDType0Font.load(document, fontFile);
        cos.beginText();

      /*  if (fontFile != null) {
        	cos.setFont(font1, 12);
        } else {
        	cos.setFont(fontPlain, 12);
        }*/
        cos.setFont(font1, 12);
        cos.showText("Hello World");
        cos.endText();

        

        //Dummy Table
        float margin = 50;//50;
        // starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        // we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float yStart = yStartNewPage;
       
        float bottomMargin = 50;//70;
        // y pozicija lijevog gornjeg kuta tablice
        float yPosition = 800;

        BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, drawContent);
        //Color textColor = Color.getHSBColor(degrees/360.0F, 1, 1);
        
        // the parameter is the row height
      Row<PDPage> headerRow = table.createRow(50);
        Cell<PDPage> cell = headerRow.createCell(30, "Header");
        //cell.createCell(50, "Headedsdsdsr");
        cell.setBorderStyle(new LineStyle(Color.black, 1));
        cell.setTextColor(Color.WHITE);
        cell.setFontSize(20);
        // vertical alignment
        cell.setValign(VerticalAlignment.MIDDLE);
        // border style
        cell.setTopBorderStyle(new LineStyle(Color.BLACK, 0));
        cell.setFillColor(finaBlue);
        table.addHeaderRow(headerRow);

        
        
       // Cell<PDPage> cell;
      //  table.cre
        
        
        
        
BaseTable table2 = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, page, true, drawContent);
//Create Header row
Row<PDPage> headerRow2 = table.createRow(15f);
Cell<PDPage> cell2 = headerRow2.createCell(100, "Awesome Facts About Belgium");
cell2.setFont(PDType1Font.HELVETICA_BOLD);
cell2.setFillColor(Color.lightGray);
table.addHeaderRow(headerRow2);
List<String[]> facts = getFacts();

for (String[] fact : facts) {
			Row<PDPage> row = table.createRow(10f);
			cell2 = row.createCell((100 / 3.0f) * 2, fact[0] );
			for (int i = 1; i < fact.length; i++) {
				cell2 = row.createCell((100 / 9f), fact[i]);
			}
}
table2.draw();
        
        
        

        Row<PDPage> row = table.createRow(20);
        cell = row.createCell(30, "black left plain");
        cell.setFontSize(15);
        cell = row.createCell(70, "black left bold");
        cell.setFontSize(15);
       // cell.setFont(fontBold);
        
        row = table.createRow(10);
       // text = text.replace("\n", "").replace("\r", "");
        String[] cellHeaders = {"Valuta", "Tecaj", "Iznos VAL", "Iznos HRK" };
        
        for(String cellContent : cellHeaders) {
        	cell = row.createCell(25, cellContent, hCenter, vMidlle);
        	cell.setFillColor(finaBlue);
        	cell.setFontSize(cellHeaderFontSize); 
            cell.setTextColor(white);
            cell.setFont(font1);
            cell.setBorderStyle(new LineStyle(white,0));
        }
       
        
        
        
        
        
        
        
        
      //Connect to DataBase
      		DbConnection db = new DbConnection("jdbc:postgresql://localhost/hpb","postgres","r2d2cpu");
      		String SQL_query = "Select valuta as \"Valuta\", tecaj as \"Tecaj\", iznos_val as \"Iznos VAL\", iznos_hrk as \"Iznos HRK\" from dummy_data";
      		//column names array
      		List<String> columnNames = new ArrayList<>();
      		//Ukupno 
      		BigDecimal ukupnoHrk = new BigDecimal("0");
            BigDecimal ukupnoVal = new BigDecimal("0");
              
      		//get results
      		try (
      				Connection conn = db.connect(); 
      				
      				PreparedStatement pstmt = conn.prepareStatement(SQL_query)) {
      				ResultSet rs = pstmt.executeQuery();
      				//get columnNames
      				ResultSetMetaData tableMetadata = rs.getMetaData();
      			        for(int i = 1; i <= 4; i++) {
      			        	columnNames.add(tableMetadata.getColumnName(i));
      			        }
      			        System.out.println(columnNames);
      			    //set column names
      		        for (String cellHeader : columnNames) {
      		        	System.out.println(cellHeader);
      		        	row = table.createRow(10);
      		        	cell = row.createCell(25, cellHeader, hCenter, vMidlle);
      		        	cell.setFillColor(finaBlue);
      		        	cell.setFontSize(cellHeaderFontSize); 
      		            cell.setTextColor(white);
      		            cell.setFont(font1);
      		            cell.setBorderStyle(new LineStyle(finaBlue,0));
      		           //table.addCell(createCell(s, noBorder, 1, alignCenter, finaBlue, white));
      		        }
      		         
      				//rows
      				while (rs.next()) {						
      					row = table.createRow(10);
      					
      					cell = row.createCell(25, rs.getString("Valuta"), hCenter, vMidlle);
      		        	cell.setFillColor(white);
      		        	cell.setFontSize(defaultfontSize); 
      		            cell.setTextColor(black);
      		            cell.setFont(font1);
      		            cell.setBorderStyle(new LineStyle(black,0));
      		            
      		            cell = row.createCell(25, rs.getString("Tecaj"), hCenter, vMidlle);
    		        	cell.setFillColor(white);
    		        	cell.setFontSize(defaultfontSize); 
    		            cell.setTextColor(black);
    		            cell.setFont(font1);
    		            cell.setBorderStyle(new LineStyle(black,1));
    		            
    		            cell = row.createCell(25, rs.getString("Iznos VAL"), hCenter, vMidlle);
      		        	cell.setFillColor(white);
      		        	cell.setFontSize(defaultfontSize); 
      		            cell.setTextColor(black);
      		            cell.setFont(font1);
      		            cell.setBorderStyle(new LineStyle(black,0));
      		            
      		            cell = row.createCell(25, rs.getString("Iznos HRK"), hCenter, vMidlle);
    		        	cell.setFillColor(white);
    		        	cell.setFontSize(defaultfontSize); 
    		            cell.setTextColor(black);
    		            cell.setFont(font1);
    		            cell.setBorderStyle(new LineStyle(black,0));
      		            
      					/*table.addCell(createCell(rs.getString("Valuta"), new SolidBorder(0), 1,alignCenter, white, black));
      					table.addCell(createCell(rs.getString("Tečaj"), new SolidBorder(0), 1,alignRight, white, black));
      					table.addCell(createCell(rs.getBigDecimal("Iznos VAL"), new SolidBorder(0), 1,alignRight, white, black));
      					table.addCell(createCell(rs.getBigDecimal("Iznos HRK"), new SolidBorder(0), 1,alignRight, white, black));
      					ukupnoHrk = ukupnoHrk.add(rs.getBigDecimal("Iznos HRK"));
      					ukupnoVal = ukupnoVal.add(rs.getBigDecimal("Iznos VAL"));*/
      				}
      				
      			} catch (SQLException ex) {
      				System.out.println(ex.getMessage());
      			}

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
for(int i = 0; i <10; i++) {
	row = table.createRow(10);
	cell = row.createCell(30, "i = " + i);
	
	 cell.setFillColor(finaBlue);
    cell.setFontSize(15);
    
    cell = row.createCell(30, "i  i " +i);
    cell.setFillColor(Color.getHSBColor(0.65f, 96, 1));
    cell.setFontSize(25);

    
    cell = row.createCell(40, "i  i " +i);
    cell.setFillColor(Color.getHSBColor(0.25f, 1, i));
    cell.setFontSize(15);
    //cell.setFont(fontBold);
    
}
        row = table.createRow(20);
        cell = row.createCell(50, "red right mono");
        cell.setTextColor(Color.RED);
        cell.setFontSize(15);
       // cell.setFont(fontMono);
        // horizontal alignment
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setBottomBorderStyle(new LineStyle(Color.RED, 5));
        cell = row.createCell(50, "green centered italic");
        cell.setTextColor(Color.GREEN);
        cell.setFontSize(15);
     //  cell.setFont(fontItalic);
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setBottomBorderStyle(new LineStyle(Color.GREEN, 5));

        row = table.createRow(20);
        cell = row.createCell(40, "rotated");
        cell.setTextRotated(true); // rotate the text
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setValign(VerticalAlignment.MIDDLE);
        // long text that wraps
        cell = row.createCell(30, "long text long text long text long text long text long text long text");
        // long text that wraps, with more line spacing
        cell = row.createCell(30, "long text long text long text long text long text long text long text");
        cell.setLineSpacing(2);
        

        table.draw();

        float tableHeight = table.getHeaderAndDataHeight();
        System.out.println(outputFileName + " tableHeight = "+tableHeight);

        // close the content stream 
        cos.close();

        // Save the results and ensure that the document is properly closed:
        document.save(outputFileName);
        document.close();
    }

	private static List<String[]> getFacts() {
		List<String[]> facts = new ArrayList<String[]>();
		facts.add(new String[] { "Oil Painting was invented by the Belgian van Eyck brothers", "art", "inventions","science" });
		facts.add(new String[] { "The Belgian Adolphe Sax invented the Saxophone", "inventions", "music", "" });
		facts.add(new String[] { "11 sites in Belgium are on the UNESCO World Heritage List", "art", "history", "" });
		facts.add(new String[] { "Belgium was the second country in the world to legalize same-sex marriage","politics", "image:150dpi.png", "" });
		facts.add(new String[] { "In the seventies, schools served light beer during lunch", "health", "school","beer" });
		facts.add(new String[] { "Belgium has the sixth fastest domestic internet connection in the world", "science","technology", "" });
		facts.add(new String[] { "Belgium hosts the World's Largest Sand Sculpture Festival", "art", "festivals","world championship" });
		facts.add(new String[] { "Rock Werchter is the Best Festival in the World", "festivals", "music","world champions" });

		// Make the table a bit bigger
		facts.addAll(facts);
		facts.addAll(facts);

		return facts;
	}
}