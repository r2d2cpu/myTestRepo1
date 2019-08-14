package com.hpb;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.fontbox.util.autodetect.FontFileFinder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class TestPdfWrite {

    public static void main(String[] args) throws IOException {

        FontFileFinder fontFinder = new FontFileFinder();
        List<URI> fontURIs = fontFinder.find();

        File fontFile = null;

        for (URI uri : fontURIs) {
            File font = new File(uri);
            if (font.getName().equals("CHILLER.TTF")) {
                fontFile = font;
            }
        }

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();

        if (fontFile != null) {
            contentStream.setFont(PDType0Font.load(document, fontFile), 12);
        } else {
            contentStream.setFont(PDType1Font.HELVETICA, 12);
        }
        

        contentStream.newLineAtOffset(10, 10);
        contentStream.showText("Hello World");
        contentStream.endText();
        contentStream.close();
        document.save("generatedFiles/Hello World.pdf");
        document.close();
    }}