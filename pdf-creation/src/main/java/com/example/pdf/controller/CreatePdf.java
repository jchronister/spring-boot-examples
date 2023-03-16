package com.example.pdf.controller;

import com.example.pdf.services.FlyingSaucerPdfCreator;
import com.example.pdf.services.TemplateService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePdf {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private FlyingSaucerPdfCreator flyingSaucerPdfCreator;

    @Autowired
    private com.example.pdf.services.FlyingSaucerOpenPdfCreator flyingSaucerOpenPdfCreator;

    @GetMapping("/pdf")
    public String getPDF() throws Exception {

        String template = templateService.thymeleafTemplateResolver();

        flyingSaucerPdfCreator.generatePdfFromHtml(template);

        return template;
    }

    @GetMapping("/openpdf")
    public String getOpenPDF() throws Exception {

        String template = templateService.thymeleafTemplateResolver();

        flyingSaucerOpenPdfCreator.createPdf(template);

        return template;
    }

    // Return File
    @GetMapping("/getpdf")
    public void generatePdfFile(HttpServletResponse response, String contentToGenerate) throws Exception {
        String template = templateService.thymeleafTemplateResolver();
        ByteArrayInputStream byteArrayInputStream = flyingSaucerOpenPdfCreator.convertHtmlToPdf(template);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=file.pdf");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }
}
