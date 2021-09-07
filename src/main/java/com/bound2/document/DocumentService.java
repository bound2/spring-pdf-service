package com.bound2.document;

import com.bound2.document.pdf.PdfService;
import com.bound2.document.template.TemplateService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DocumentService {

    private final PdfService pdfService;
    private final TemplateService templateService;

    public DocumentService(PdfService pdfService, TemplateService templateService) {
        this.pdfService = pdfService;
        this.templateService = templateService;
    }

    public byte[] createPdf(String template) {
        String base64Html = templateService.getBase64Html(template, Map.of());
        return pdfService.generate(base64Html);
    }
}
