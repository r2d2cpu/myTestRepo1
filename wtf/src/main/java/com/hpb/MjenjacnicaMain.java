package com.hpb;

import java.io.IOException;

public class MjenjacnicaMain {

	public static void main(String[] args) {
		
		XlsGenerator xlsGenerator = new XlsGenerator();
		PdfGenerator pdfGenerator = new PdfGenerator();
		
		try {
			System.out.println(xlsGenerator.createXls("generatedFiles/izvjestaj.xls"));
			System.out.println(pdfGenerator.createPdf("generatedFiles/transakcija.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
