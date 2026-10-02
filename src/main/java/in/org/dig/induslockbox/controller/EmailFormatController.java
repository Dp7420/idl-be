package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.entity.EmailFormats;
import in.org.dig.induslockbox.service.EmailFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/template")
public class EmailFormatController {

    @Autowired
    private EmailFormatService service;

    @PostMapping("/create")
    public EmailFormats create(@RequestBody EmailFormats emailFormats) {
        return service.createTemplate(emailFormats);
    }

    @GetMapping("/all")
    public List<EmailFormats> getAll() {
        return service.getAllTemplates();
    }

    @PutMapping("/update/{id}")
    public EmailFormats update(@PathVariable Long id,
                               @RequestBody EmailFormats emailFormats) {
        return service.updateTemplate(id, emailFormats);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteTemplate(id);
        return "Template Deleted Successfully";
    }
}
