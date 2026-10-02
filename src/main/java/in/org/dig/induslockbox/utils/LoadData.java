package in.org.dig.induslockbox.utils;

import in.org.dig.induslockbox.entity.EmailFormats;
import in.org.dig.induslockbox.service.EmailFormatService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoadData {

    private final EmailFormatService emailFormatService;
    // templateId -> templateName
    public static Map<String, String> emailTemplateMap = new HashMap<>();


    @PostConstruct
    public void loadEmailTemplates() {

        log.info("Loading Email Templates...");

        emailTemplateMap = emailFormatService.getAllTemplateIdAndTemplateName();
        log.info("Email Templates Loaded Successfully : {}", emailTemplateMap);
    }
}