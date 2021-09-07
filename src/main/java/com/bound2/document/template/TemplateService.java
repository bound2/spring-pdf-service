package com.bound2.document.template;

import com.samskivert.mustache.Mustache;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Map;

@Service
public class TemplateService {

    private final Mustache.Compiler compiler;

    public TemplateService(Mustache.Compiler compiler) {
        this.compiler = compiler;
    }

    public String getBase64Html(String template, Map<String, Object> params) {
        try {
            var resource = getClass().getClassLoader().getResource("templates/%s.html".formatted(template));
            var path = Path.of(resource.toURI());
            var html = Files.readString(path, StandardCharsets.UTF_8);

            var writer = new StringWriter();
            compiler.compile(html).execute(params, writer);

            return Base64.getEncoder().encodeToString(writer.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
