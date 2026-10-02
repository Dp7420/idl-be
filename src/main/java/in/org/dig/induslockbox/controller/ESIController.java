package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.entity.ESI;
import in.org.dig.induslockbox.service.ESIService;
import in.org.dig.platform.LoggerUtil.util.EventLogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/esi")
public class ESIController {

    @Autowired
    private ESIService ESIService;

    @ProfileExecution
    @EventLogDetails(eventId = "fetchallesi", eventName = "fetch_all_esi", eventDescription = "fetch all ESI records", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchall")
    public List<ESI> getAllESIs() {
        return ESIService.findAll();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchesibyid", eventName = "fetch_esi_by_id", eventDescription = "fetch ESI by id", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchbyid/{id}")
    public ResponseEntity<ESI> getESIById(@PathVariable Long id) {
        Optional<ESI> ESI = ESIService.findById(id);
        return ESI.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ProfileExecution
    @EventLogDetails(eventId = "saveesi", eventName = "save_esi", eventDescription = "create new ESI record", serviceName = "DIGI_LOCKER")
    @PostMapping("/save")//http://localhost:8080/api/ESIs
    public ESI createESI(@RequestBody ESI ESI) {
        return ESIService.save(ESI);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "updateesi", eventName = "update_esi", eventDescription = "update ESI details", serviceName = "DIGI_LOCKER")
    @PutMapping("/update/{id}")
    public ResponseEntity<ESI> updateESI(@PathVariable Long id, @RequestBody ESI ESIDetails) {
        try {
            ESI updatedESI = ESIService.updateESI(id, ESIDetails);
            return ResponseEntity.ok(updatedESI);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ProfileExecution
    @EventLogDetails(eventId = "deleteesi", eventName = "delete_esi", eventDescription = "delete ESI record", serviceName = "DIGI_LOCKER")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteESI(@PathVariable Long id) {
        ESIService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "activateesi", eventName = "activate_esi", eventDescription = "activate ESI record", serviceName = "DIGI_LOCKER")
    @PatchMapping("/activate/{id}")
    public void activateESI(@PathVariable Long id) {
        ESIService.activateById(id);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchesibycompany", eventName = "fetch_esi_by_company", eventDescription = "fetch ESI records by company id", serviceName = "DIGI_LOCKER")
    @GetMapping("/company/{company_id}")//http://localhost:8080/api/ESI/company/1
    public ResponseEntity<List<ESI>> getESIByCompanyId(@PathVariable Long company_id) {
        List<ESI> ESIDetails = ESIService.findByCompany_id(company_id);
        return ResponseEntity.ok(ESIDetails);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "decryptesipassword", eventName = "decrypt_esi_password", eventDescription = "decrypt ESI password", serviceName = "DIGI_LOCKER")
    @GetMapping("/decrypt/{id}")
    public String getDecryptPassword(@PathVariable Long id) {
        return ESIService.GetDecryptPassword(id);
    }
}