package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.entity.ProfessionalTax;
import in.org.dig.induslockbox.service.ProfessionalTaxService;
import in.org.dig.platform.LoggerUtil.util.EventLogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/pt")
public class ProfessionalTaxController {
	@Autowired
	private ProfessionalTaxService ProfessionalTaxService;

	@ProfileExecution
	@EventLogDetails(eventId = "fetchallprofessionaltax", eventName = "fetch_all_professional_tax", eventDescription = "fetch all professional tax records", serviceName = "DIGI_LOCKER")
	@GetMapping("/fetchall")
	public List<ProfessionalTax> getAllProfessionalTaxs() {
		return ProfessionalTaxService.findAll();
	}

	@ProfileExecution
	@EventLogDetails(eventId = "fetchprofessionaltaxbyid", eventName = "fetch_professional_tax_by_id", eventDescription = "fetch professional tax by id", serviceName = "DIGI_LOCKER")
	@GetMapping("/fetchbyid/{id}")
	public ResponseEntity<ProfessionalTax> getProfessionalTaxById(@PathVariable Long id) {
		Optional<ProfessionalTax> ProfessionalTax = ProfessionalTaxService.findById(id);
		return ProfessionalTax.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@ProfileExecution
	@EventLogDetails(eventId = "fetchprofessionaltaxbycompany", eventName = "fetch_professional_tax_by_company", eventDescription = "fetch professional tax records by company id", serviceName = "DIGI_LOCKER")
	@GetMapping("/company/{company_id}")
	public ResponseEntity<List<ProfessionalTax>> getProfessionalTaxByCompanyId(@PathVariable Long company_id) {
		List<ProfessionalTax> ProfessionalTaxDetails = ProfessionalTaxService.findByCompany_id(company_id);
		return ResponseEntity.ok(ProfessionalTaxDetails);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "saveprofessionaltax", eventName = "save_professional_tax", eventDescription = "create new professional tax record", serviceName = "DIGI_LOCKER")
	@PostMapping("/save")
	public ProfessionalTax createProfessionalTax(@RequestBody ProfessionalTax ProfessionalTax) {
		return ProfessionalTaxService.save(ProfessionalTax);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "updateprofessionaltax", eventName = "update_professional_tax", eventDescription = "update professional tax details", serviceName = "DIGI_LOCKER")
	@PutMapping("/update/{id}")
	public ProfessionalTax updateProfessionalTax(@PathVariable Long id, @RequestBody ProfessionalTax updatedProfessionalTax) {
		return ProfessionalTaxService.updateProfessionalTax(id, updatedProfessionalTax);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "deleteprofessionaltax", eventName = "delete_professional_tax", eventDescription = "delete professional tax record", serviceName = "DIGI_LOCKER")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProfessionalTax(@PathVariable Long id) {
		ProfessionalTaxService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@ProfileExecution
	@EventLogDetails(eventId = "activateprofessionaltax", eventName = "activate_professional_tax", eventDescription = "activate professional tax record", serviceName = "DIGI_LOCKER")
	@PatchMapping("/activate/{id}")
	public void activateProfessionalTax(@PathVariable Long id) {
		ProfessionalTaxService.activateById(id);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "decryptprofessionaltaxpassword", eventName = "decrypt_professional_tax_password", eventDescription = "decrypt professional tax password", serviceName = "DIGI_LOCKER")
	@GetMapping("/decrypt/{id}")
	public String getDecryptPassword(@PathVariable Long id) {
		return ProfessionalTaxService.GetDecryptPassword(id);
	}
}