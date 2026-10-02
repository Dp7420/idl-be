package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.entity.EmailFormats;
import in.org.dig.induslockbox.service.EmailFormatService;
import in.org.dig.platform.LoggerUtil.util.EventLogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/template")
public class EmailFormatController {

    @Autowired
    private EmailFormatService service;

    @ProfileExecution
    @EventLogDetails(eventId = "createemailtemplate", eventName = "create_email_template", eventDescription = "create email template", serviceName = "DIGI_LOCKER")
    @PostMapping("/create")
    public EmailFormats create(@RequestBody EmailFormats emailFormats) {
        return service.createTemplate(emailFormats);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchallemailtemplates", eventName = "fetch_all_email_templates", eventDescription = "fetch all email templates", serviceName = "DIGI_LOCKER")
    @GetMapping("/all")
    public List<EmailFormats> getAll() {
        return service.getAllTemplates();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "updateemailtemplate", eventName = "update_email_template", eventDescription = "update email template", serviceName = "DIGI_LOCKER")
    @PutMapping("/update/{id}")
    public EmailFormats update(@PathVariable Long id,
                               @RequestBody EmailFormats emailFormats) {
        return service.updateTemplate(id, emailFormats);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "deleteemailtemplate", eventName = "delete_email_template", eventDescription = "delete email template", serviceName = "DIGI_LOCKER")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteTemplate(id);
        return "Template Deleted Successfully";
    }
}