package com.bound2.document;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/api/v1/document/{template}/html")
    public ModelAndView getDocumentHtml(@PathVariable String template) {
        return new ModelAndView(template);
    }

    @GetMapping("/api/v1/document/{template}/pdf")
    public void getDocumentPdf(@PathVariable String template) {
        documentService.createPdf(template);
    }
}
