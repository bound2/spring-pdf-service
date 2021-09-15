package com.bound2.unit.template;

import com.bound2.document.template.TemplateService;
import com.samskivert.mustache.Mustache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TemplateServiceTest {

    @Spy
    private Mustache.Compiler compiler = Mustache.compiler();

    @InjectMocks
    private TemplateService templateService;

    @Test
    public void testExampleTemplate() {
        // given
        var templateName = "example";

        // when
        var result = templateService.getBase64Html(templateName, Map.of());

        // then
        verify(compiler).compile(anyString());
        assertThat(result).startsWith("PCFkb2N0eXBlIGh0bWw+DQo8aHRtbCBsYW5nPSJlbiI+DQo8aGVhZD4NCiAgICA8bWV0YSBjaGFyc2V0PSJVVEYtOCI");
    }
}
