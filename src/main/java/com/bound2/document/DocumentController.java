package com.bound2.document;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;

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
    public ResponseEntity<byte[]> getDocumentPdf(@PathVariable String template) {
        var pdf = documentService.createPdf(template);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        var disposition = ContentDisposition.attachment()
                .filename("%s.pdf".formatted(template), StandardCharsets.UTF_8)
                .build();
        headers.setContentDisposition(disposition);

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}
