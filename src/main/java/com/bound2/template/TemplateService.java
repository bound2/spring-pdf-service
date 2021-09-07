package com.bound2.template;

import com.samskivert.mustache.Mustache;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Service
public class TemplateService {

    private final Mustache.Compiler compiler;

    public TemplateService(Mustache.Compiler compiler) {
        this.compiler = compiler;
    }

    public String getBase64Template(String template, Map<String, Object> params) {
        try {
            var resource = getClass().getClassLoader().getResource("templates/%s.html".formatted(template));
            var path = Path.of(resource.toURI());
            var html = Files.readString(path, StandardCharsets.UTF_8);

            var outputStream = new ByteArrayOutputStream();
            var writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            compiler.compile(html).execute(params, writer);

            return Base64.encodeBase64String(outputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
