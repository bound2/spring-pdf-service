package com.bound2.unit.document;

import com.bound2.document.DocumentService;
import com.bound2.document.pdf.PdfService;
import com.bound2.document.template.TemplateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {

    @Mock
    private PdfService pdfService;

    @Mock
    private TemplateService templateService;

    @InjectMocks
    private DocumentService documentService;

    @Test
    public void testCreatePdf() {
        // given
        var template = "example";
        var base64html = "dGVzdA==";
        var printBase64 = "cHJpbnRtZQ==";
        Map<String, Object> params = Map.of();
        when(templateService.getBase64Html(eq(template), eq(params))).thenReturn(base64html);
        when(pdfService.generate(eq(base64html))).thenReturn(printBase64.getBytes(StandardCharsets.UTF_8));

        // when
        var result = documentService.createPdf(template);

        // then
        verify(templateService).getBase64Html(eq(template), eq(params));
        verify(pdfService).generate(eq(base64html));
        assertThat(result).isEqualTo(printBase64.getBytes(StandardCharsets.UTF_8));
    }
}
