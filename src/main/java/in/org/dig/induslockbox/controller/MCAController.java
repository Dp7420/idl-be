package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.entity.MCA;
import in.org.dig.induslockbox.service.MCAService;
import in.org.dig.platform.LoggerUtil.util.EventLogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/mca")
public class MCAController {

    @Autowired
    private MCAService mcaService;

    @ProfileExecution
    @EventLogDetails(eventId = "fetchallmca", eventName = "fetch_all_mca", eventDescription = "fetch all MCA records", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchall")
    public List<MCA> getAllMCAs() {
        return mcaService.findAll();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchmcabyid", eventName = "fetch_mca_by_id", eventDescription = "fetch MCA by id", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchbyid/{id}")
    public ResponseEntity<MCA> getMCAById(@PathVariable Long id) {
        Optional<MCA> mcaOpt = mcaService.findById(id);
        if (mcaOpt.isPresent()) {
            return ResponseEntity.ok(mcaOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchmcabycompany", eventName = "fetch_mca_by_company", eventDescription = "fetch MCA records by company id", serviceName = "DIGI_LOCKER")
    @GetMapping("/company/{company_id}")
    public ResponseEntity<List<MCA>> getMCAByCompanyId(@PathVariable Long company_id) {
        List<MCA> mcaDetails = mcaService.findByCompany_id(company_id);
        return ResponseEntity.ok(mcaDetails);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "savemca", eventName = "save_mca", eventDescription = "create new MCA record", serviceName = "DIGI_LOCKER")
    @PostMapping("/save")
    public MCA createMCA(@RequestBody MCA mca) {
        return mcaService.save(mca);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "updatemca", eventName = "update_mca", eventDescription = "update MCA details", serviceName = "DIGI_LOCKER")
    @PutMapping("/update/{id}")
    public ResponseEntity<MCA> updateMCA(@PathVariable Long id, @RequestBody MCA updatedMCA) {
        try {
            MCA updatedMCAEntity = mcaService.updateMCA(id, updatedMCA);
            return ResponseEntity.ok(updatedMCAEntity);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ProfileExecution
    @EventLogDetails(eventId = "deletemca", eventName = "delete_mca", eventDescription = "delete MCA record", serviceName = "DIGI_LOCKER")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMCA(@PathVariable Long id) {
        try {
            mcaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ProfileExecution
    @EventLogDetails(eventId = "activatemca", eventName = "activate_mca", eventDescription = "activate MCA record", serviceName = "DIGI_LOCKER")
    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateMCA(@PathVariable Long id) {
        try {
            mcaService.activateById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ProfileExecution
    @EventLogDetails(eventId = "decryptmcav2password", eventName = "decrypt_mca_v2_password", eventDescription = "decrypt MCA V2 password", serviceName = "DIGI_LOCKER")
    @GetMapping("/decrypt/V2Password/{id}")
    public String getDecryptV2Password(@PathVariable Long id) {
        return mcaService.GetDecryptV2Password(id);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "decryptmcav3password", eventName = "decrypt_mca_v3_password", eventDescription = "decrypt MCA V3 password", serviceName = "DIGI_LOCKER")
    @GetMapping("/decrypt/V3Password/{id}")
    public String getDecryptV3Password(@PathVariable Long id) {
        return mcaService.GetDecryptV3Password(id);
    }
}