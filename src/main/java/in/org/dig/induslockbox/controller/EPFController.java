package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.entity.EPF;
import in.org.dig.induslockbox.service.EPFService;
import in.org.dig.platform.LoggerUtil.util.EventLogDetails;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/epf")
public class EPFController {

    private final EPFService epfService;


    public EPFController(EPFService epfService) {
        this.epfService = epfService;
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchallepf", eventName = "fetch_all_epf", eventDescription = "fetch all EPF records", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchall")
    public List<EPF> getAllEPFs() {
        return epfService.findAll();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchepfbyid", eventName = "fetch_epf_by_id", eventDescription = "fetch EPF by id", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchbyid/{id}")
    public ResponseEntity<EPF> getEPFById(@PathVariable Long id) {
        Optional<EPF> epf = epfService.findById(id);
        return epf.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ProfileExecution
    @EventLogDetails(eventId = "saveepf", eventName = "save_epf", eventDescription = "create new EPF record", serviceName = "DIGI_LOCKER")
    @PostMapping("/save")
    public EPF createEPF(@RequestBody EPF epf) throws MessagingException {
        return epfService.save(epf);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "updateepf", eventName = "update_epf", eventDescription = "update EPF details", serviceName = "DIGI_LOCKER")
    @PutMapping("/update/{id}")
    public ResponseEntity<EPF> updateEPF(@PathVariable Long id, @RequestBody EPF epfDetails) {
        try {
            EPF updatedEPF = epfService.updateEPF(id, epfDetails);
            return ResponseEntity.ok(updatedEPF);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ProfileExecution
    @EventLogDetails(eventId = "deleteepf", eventName = "delete_epf", eventDescription = "delete EPF record", serviceName = "DIGI_LOCKER")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEPF(@PathVariable Long id) {
        epfService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "activateepf", eventName = "activate_epf", eventDescription = "activate EPF record", serviceName = "DIGI_LOCKER")
    @PatchMapping("/activate/{id}")
    public void activateEPF(@PathVariable Long id) {
        epfService.activateById(id);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchepfbycompany", eventName = "fetch_epf_by_company", eventDescription = "fetch EPF records by company id", serviceName = "DIGI_LOCKER")
    @GetMapping("/company/{company_id}")
    public ResponseEntity<List<EPF>> getEPFByCompanyId(@PathVariable Long company_id) {
        List<EPF> epfDetails = epfService.findByCompany_id(company_id);
        return ResponseEntity.ok(epfDetails);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "decryptepfpassword", eventName = "decrypt_epf_password", eventDescription = "decrypt EPF password", serviceName = "DIGI_LOCKER")
    @GetMapping("/decrypt/{id}")
    public String getDecryptPassword(@PathVariable Long id) {
        return epfService.GetDecryptPassword(id);
    }
}