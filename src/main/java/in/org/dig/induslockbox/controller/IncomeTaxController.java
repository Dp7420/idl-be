package in.org.dig.induslockbox.controller;

import java.util.List;
import java.util.Optional;

import in.org.dig.platform.LoggerUtil.util.EventLogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.entity.IncomeTax;
import in.org.dig.induslockbox.service.IncomeTaxService;

@RestController
@RequestMapping("/api/admin/incometax")
public class IncomeTaxController {

    @Autowired
    private IncomeTaxService incomeTaxService;

    @ProfileExecution
    @EventLogDetails(eventId = "fetchallincometax", eventName = "fetch_all_income_tax", eventDescription = "fetch all income tax records", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchall")
    public List<IncomeTax> getAllIncomeTaxes() {
        return incomeTaxService.findAll();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchincometaxbyid", eventName = "fetch_income_tax_by_id", eventDescription = "fetch income tax by id", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchbyid/{id}")
    public ResponseEntity<IncomeTax> getIncomeTaxById(@PathVariable Long id) {
        Optional<IncomeTax> incomeTax = incomeTaxService.findById(id);
        return incomeTax.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ProfileExecution
    @EventLogDetails(eventId = "saveincometax", eventName = "save_income_tax", eventDescription = "create new income tax record", serviceName = "DIGI_LOCKER")
    @PostMapping("/save")
    public IncomeTax createIncomeTax(@RequestBody IncomeTax incomeTax) {
        return incomeTaxService.save(incomeTax);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "updateincometax", eventName = "update_income_tax", eventDescription = "update income tax details", serviceName = "DIGI_LOCKER")
    @PutMapping("/update/{id}")
    public ResponseEntity<IncomeTax> updateIncomeTax(@PathVariable Long id, @RequestBody IncomeTax incomeTaxDetails) {
        try {
            IncomeTax updatedIncomeTax = incomeTaxService.updateIncomeTax(id, incomeTaxDetails);
            return ResponseEntity.ok(updatedIncomeTax);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ProfileExecution
    @EventLogDetails(eventId = "deleteincometax", eventName = "delete_income_tax", eventDescription = "delete income tax record", serviceName = "DIGI_LOCKER")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteIncomeTax(@PathVariable Long id) {
        incomeTaxService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "activateincometax", eventName = "activate_income_tax", eventDescription = "activate income tax record", serviceName = "DIGI_LOCKER")
    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateIncomeTax(@PathVariable Long id) {
        try {
            incomeTaxService.activateById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchincometaxbycompany", eventName = "fetch_income_tax_by_company", eventDescription = "fetch income tax records by company id", serviceName = "DIGI_LOCKER")
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<IncomeTax>> getIncomeTaxByCompanyId(@PathVariable Long companyId) {
        List<IncomeTax> incomeTaxes = incomeTaxService.findByCompany_id(companyId);
        return ResponseEntity.ok(incomeTaxes);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "decryptincometaxpassword", eventName = "decrypt_income_tax_password", eventDescription = "decrypt income tax password", serviceName = "DIGI_LOCKER")
    @GetMapping("/decrypt/{id}")
    public String getDecryptPassword(@PathVariable Long id) {
        return incomeTaxService.GetDecryptPassword(id);
    }
}