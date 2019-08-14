package com.hpb;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;



public class PdfGenerator {
	
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

	/**
	 * Create Cell with String value
	 * @param content
	 * @param border
	 * @param colspan
	 * @param alignment
	 * @param backColor
	 * @param fontColor
	 * @return Cell
	 */
	protected Cell createCell(String content, Border border, int colspan, TextAlignment alignment, Color backColor, Color fontColor) {

		Cell cell = new Cell(1, colspan).add(new Paragraph(content));
		cell.setBackgroundColor(backColor);
		cell.setTextAlignment(alignment);
		cell.setFontColor(fontColor);
		cell.setBorder(border);
		return cell;
	}
	/**
	 * Create Cell with Numeric value
	 * @param content
	 * @param border
	 * @param colspan
	 * @param alignment
	 * @param backColor
	 * @param fontColor
	 * @return Cell
	 */
	protected Cell createCell(BigDecimal content, Border border, int colspan, TextAlignment alignment, Color backColor, Color fontColor) {

		Cell cell = new Cell(1, colspan).add(content.toString());
		cell.setBackgroundColor(backColor);
		cell.setTextAlignment(alignment);
		cell.setFontColor(fontColor);
		cell.setBorder(border);
		return cell;
	}
	/**
	 * Create cell with height and colspan
	 * @param height
	 * @param colspan
	 * @param border
	 * @return Cell
	 */
	protected Cell createEmptyRow(float height, int colspan, Border border) {
		Cell cell = new Cell(1, colspan);
		cell.setHeight(height);
		cell.setBorder(border);
		return cell;
	}

	/**
	 * Method that returns Color from RGB values
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @return Color
	 */
	protected Color getColor(float r, float g, float b) {
		float[] rgb = new float[] { r, g, b };
		Color rgbColor = Color.makeColor(Color.WHITE.getColorSpace(), rgb);
		return rgbColor;
	}

	protected Image createImage(String path) throws MalformedURLException {
		String imageFile = path;
		ImageData imgData = ImageDataFactory.create(imageFile);
		Image img = new Image(imgData);
		img.setFixedPosition(480, 800);
		img.setHeight(20);
		return img;
	}

	protected PdfFont setFontStyle(String fontPath) throws java.io.IOException {
		FontProgram fontProgram = FontProgramFactory.createFont(fontPath);
		PdfFont font = PdfFontFactory.createFont(fontProgram, PdfEncodings.CP1250, true);
		return font;
	}

	protected String createPdf(String dest) throws java.io.IOException {
				
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Table table = new Table(new float[] { 50f, 150f, 150f, 150f });
		table.setWidthPercent(100);
		
		table.addCell(createCell("Tvrtka:", noBorder, 1, alignLeft, lightGray, black));
		table.addCell(createCell("Pero Perić d.o.o.", noBorder, 3, alignLeft, lightGray, black));
		
		table.addCell(createCell("OIB:", noBorder, 1, alignLeft, lightGray, black));
		table.addCell(createCell("42181302865", noBorder, 2, alignLeft, lightGray, black));
		table.addCell(createCell("Datum: 06.08.2019 - 15:29", noBorder, 1, alignLeft, lightGray, black));
		
		table.addCell(createEmptyRow(20, 4, noBorder));
		
		//Connect to DataBase
		DbConnection db = new DbConnection("jdbc:postgresql://localhost/hpb","postgres","r2d2cpu");
		String SQL_query = "Select valuta as \"Valuta\", tecaj as \"Tečaj\", iznos_val as \"Iznos VAL\", iznos_hrk as \"Iznos HRK\" from dummy_data";
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
			    //set column names
		        for (String s : columnNames) {
		           table.addCell(createCell(s, noBorder, 1, alignCenter, finaBlue, white));
		        }
		         
				//rows
				while (rs.next()) {						
					table.addCell(createCell(rs.getString("Valuta"), new SolidBorder(0), 1,alignCenter, white, black));
					table.addCell(createCell(rs.getString("Tečaj"), new SolidBorder(0), 1,alignRight, white, black));
					table.addCell(createCell(rs.getBigDecimal("Iznos VAL"), new SolidBorder(0), 1,alignRight, white, black));
					table.addCell(createCell(rs.getBigDecimal("Iznos HRK"), new SolidBorder(0), 1,alignRight, white, black));
					ukupnoHrk = ukupnoHrk.add(rs.getBigDecimal("Iznos HRK"));
					ukupnoVal = ukupnoVal.add(rs.getBigDecimal("Iznos VAL"));
				}
				
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		//Ukupno
		table.addCell(createEmptyRow(20, 4, noBorder));
		table.addCell(createCell("Ukupno", noBorder, 2, alignLeft, white, black).setBorderBottom(new SolidBorder(1)));
		table.addCell(createCell(ukupnoVal, noBorder, 1, alignRight, finaBlue, white).setBorderBottom(new SolidBorder(1)));
		table.addCell(createCell(ukupnoHrk, noBorder, 1, alignRight, finaBlue, white).setBorderBottom(new SolidBorder(1)));
		//Datum
		table.addCell(createEmptyRow(60, 4, noBorder));
		/*table.addCell(createCell("Datum:", noBorder, 1, alignLeft, white, black));//new SolidBorder(1)
		table.addCell(createCell("06.08.2019 - 15:29", noBorder, 3, alignLeft, white, black));*/
		//Potpis
		Table signTable = new Table(new float[] { 100f, 150f, 100f, 150f });
		signTable.setWidthPercent(100);
		
		signTable.addCell(createEmptyRow(20, 5, noBorder));
		signTable.addCell(createCell("", noBorder, 1, alignRight, white, black));
		signTable.addCell(createCell("Za ovlaštenog mjenjača", noBorder, 1, alignRight, white, black));
		signTable.addCell(createCell("", noBorder, 1, alignRight, white, black));
		signTable.addCell(createCell("Za FINA-u", noBorder, 1, alignRight, white, black));
		
		signTable.addCell(createEmptyRow(20, 5, noBorder));
		signTable.addCell(createCell("", noBorder, 1, alignRight, white, black));
		signTable.addCell(createCell("", noBorder, 1, alignRight, white, black).setBorderBottom(solid1));
		signTable.addCell(createCell("", noBorder, 1, alignRight, white, black));
		signTable.addCell(createCell("", noBorder, 1, alignRight, white, black).setBorderBottom(solid1));
		
		//table.addCell(signTable);
		
		docTitle.setMarginBottom(30).setFontSize(12);
		doc.add(createImage(finaLogo));
		doc.setFont(setFontStyle(docFont));
		doc.add(docTitle);
		doc.add(table);
		doc.add(signTable);
		doc.close();
		return "Pdf " + dest + " kreiran!";
	}

}