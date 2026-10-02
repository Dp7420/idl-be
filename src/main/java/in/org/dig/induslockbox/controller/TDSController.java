package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.entity.TDS;
import in.org.dig.induslockbox.service.TDSService;
import in.org.dig.platform.LoggerUtil.util.EventLogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/admin/tds")
public class TDSController {

	@Autowired
	private TDSService TDSService;

	@ProfileExecution
	@EventLogDetails(eventId = "fetchalltds", eventName = "fetch_all_tds", eventDescription = "fetch all TDS records", serviceName = "DIGI_LOCKER")
	@GetMapping("/fetchall")
	public List<TDS> getAllTDS() {
		return TDSService.findAll();
	}

	@ProfileExecution
	@EventLogDetails(eventId = "fetchtdsbyid", eventName = "fetch_tds_by_id", eventDescription = "fetch TDS by id", serviceName = "DIGI_LOCKER")
	@GetMapping("/fetchbyid/{id}")
	public ResponseEntity<TDS> getDirectorById(@PathVariable Long id) {
		Optional<TDS> tds = TDSService.findById(id);
		return tds.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@ProfileExecution
	@EventLogDetails(eventId = "fetchtdsbycompany", eventName = "fetch_tds_by_company", eventDescription = "fetch TDS records by company id", serviceName = "DIGI_LOCKER")
	@GetMapping("/company/{company_id}")
	public ResponseEntity<List<TDS>> getDirectorByCompanyId(@PathVariable Long company_id) {
		List<TDS> TDSDetails = TDSService.findByCompany_id(company_id);
		return ResponseEntity.ok(TDSDetails);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "savetds", eventName = "save_tds", eventDescription = "create new TDS record", serviceName = "DIGI_LOCKER")
	@PostMapping("/save")
	public TDS createBank(@RequestBody TDS tds) {
		return TDSService.save(tds);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "updatetds", eventName = "update_tds", eventDescription = "update TDS details", serviceName = "DIGI_LOCKER")
	@PutMapping("/update/{id}")
	public TDS updateDirector(@PathVariable Long id, @RequestBody TDS tdsDetails) {
		return TDSService.updateTDS(id, tdsDetails);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "deletetds", eventName = "delete_tds", eventDescription = "delete TDS record", serviceName = "DIGI_LOCKER")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
		TDSService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@ProfileExecution
	@EventLogDetails(eventId = "activatetds", eventName = "activate_tds", eventDescription = "activate TDS record", serviceName = "DIGI_LOCKER")
	@PatchMapping("/activate/{id}")
	public void activateDirector(@PathVariable Long id) {
		TDSService.activateById(id);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "decrypttdspassword", eventName = "decrypt_tds_password", eventDescription = "decrypt TDS password", serviceName = "DIGI_LOCKER")
	@GetMapping("/decrypt/{id}")
	public String getDecryptPassword(@PathVariable Long id) {
		return TDSService.getDecryptPassword(id);
	}
}