package com.example.pdf.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;


@Service
public class FlyingSaucerOpenPdfCreator {

    public void createPdf(String html) throws Exception {

        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleafOpenPdf.pdf";

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(html);
        renderer.layout();
        OutputStream outputStream = new FileOutputStream(outputFolder);

        renderer.createPDF(outputStream);
    }

    public ByteArrayInputStream convertHtmlToPdf(String htmlContent) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream, false);
        renderer.finishPDF();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return inputStream;
    }
}
