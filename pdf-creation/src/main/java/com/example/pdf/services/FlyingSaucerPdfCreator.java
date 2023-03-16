package com.example.pdf.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
public class FlyingSaucerPdfCreator {

    public void generatePdfFromHtml(String html) throws Exception {
        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";

        System.out.print("Saving File to " + outputFolder);
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }
}
