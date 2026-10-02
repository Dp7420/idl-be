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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.entity.GST;
import in.org.dig.induslockbox.service.GSTService;

@RestController
@RequestMapping("/api/admin/gst")
public class GSTController {

    @Autowired
    private GSTService GSTService;

    @ProfileExecution
    @EventLogDetails(eventId = "fetchallgst", eventName = "fetch_all_gst", eventDescription = "fetch all GST records", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchall")
    public List<GST> getAllGSTs() {
        return GSTService.findAll();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchgstbyid", eventName = "fetch_gst_by_id", eventDescription = "fetch GST by id", serviceName = "DIGI_LOCKER")
    @GetMapping("/fetchbyid/{id}")
    public ResponseEntity<GST> getGSTById(@PathVariable Long id) {
        Optional<GST> GST = GSTService.findById(id);
        return GST.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ProfileExecution
    @EventLogDetails(eventId = "savegst", eventName = "save_gst", eventDescription = "create new GST record", serviceName = "DIGI_LOCKER")
    @PostMapping("/save")
    public GST createGST(@RequestBody GST GST,@RequestParam String createdBy,@RequestParam Long companyid) {
        return GSTService.save(GST,createdBy,companyid);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "updategst", eventName = "update_gst", eventDescription = "update GST details", serviceName = "DIGI_LOCKER")
    @PutMapping("/update/{id}")
    public ResponseEntity<GST> updateGST(@PathVariable Long id, @RequestBody GST gstDetails) {
        try {
            GST updatedGST = GSTService.updateGST(id, gstDetails);
            return ResponseEntity.ok(updatedGST);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ProfileExecution
    @EventLogDetails(eventId = "deletegst", eventName = "delete_gst", eventDescription = "delete GST record", serviceName = "DIGI_LOCKER")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGST(@PathVariable Long id) {
        GSTService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ProfileExecution
    @EventLogDetails(eventId = "activategst", eventName = "activate_gst", eventDescription = "activate GST record", serviceName = "DIGI_LOCKER")
    @PatchMapping("/activate/{id}")
    public void activateGST(@PathVariable Long id) {
        GSTService.activateById(id);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "fetchgstbycompany", eventName = "fetch_gst_by_company", eventDescription = "fetch GST records by company id", serviceName = "DIGI_LOCKER")
    @GetMapping("/company/{company_id}")
    public ResponseEntity<List<GST>> getGSTByCompanyId(@PathVariable Long company_id) {
        List<GST> GSTDetails = GSTService.findByCompany_id(company_id);
        return ResponseEntity.ok(GSTDetails);
    }

    @ProfileExecution
    @EventLogDetails(eventId = "decryptgstpassword", eventName = "decrypt_gst_password", eventDescription = "decrypt GST password", serviceName = "DIGI_LOCKER")
    @GetMapping("/decrypt/{id}")
    public String getDecryptPassword(@PathVariable Long id) {
        return GSTService.GetDecryptPassword(id);
    }

}