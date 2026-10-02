package in.org.dig.induslockbox.service;


import in.org.dig.induslockbox.entity.EmailFormats;
import in.org.dig.induslockbox.repository.EmailFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmailFormatService {

    @Autowired
    private EmailFormatRepository repository;


    public String createEmailFormatTemplate(EmailFormats emailFormats) {
        try {
            EmailFormats formats = repository.save(emailFormats);
            return "Email format template created successfully : " + formats.getTemplateName();
        } catch (Exception e) {
            return "Failed to create email format template : " + e.getMessage();
        }
    }

    public EmailFormats createTemplate(EmailFormats emailFormats) {
        return repository.save(emailFormats);
    }

    public List<EmailFormats> getAllTemplates() {
        return repository.findAll();
    }

    public EmailFormats updateTemplate(Long id, EmailFormats emailFormats) {

        EmailFormats existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        existing.setTemplateName(emailFormats.getTemplateName());
        existing.setTemplateId(emailFormats.getTemplateId());
        existing.setTemplateHeader(emailFormats.getTemplateHeader());
        existing.setTemplateBody(emailFormats.getTemplateBody());
        existing.setTemplateFooter(emailFormats.getTemplateFooter());

        return repository.save(existing);
    }

    public void deleteTemplate(Long id) {
        repository.deleteById(id);
    }

    public EmailFormats getByTemplateId(String templateId) {
        return repository.findByTemplateId(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }

    public Map<String, String> getAllTemplateIdAndTemplateName() {
        return repository.getAllTemplateIdAndTemplateName();
    }
}
