package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.dto.DirectorForm;
import in.org.dig.induslockbox.entity.Company;
import in.org.dig.induslockbox.entity.Director;
import in.org.dig.induslockbox.service.CompanyService;
import in.org.dig.induslockbox.service.DirectorService;
import in.org.dig.platform.LoggerUtil.util.EventLogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/directors")
public class DirectorController {

	@Autowired
	private DirectorService directorService;
	@Autowired
	private CompanyService companyService;

	@ProfileExecution
	@EventLogDetails(eventId = "fetchalldirectors", eventName = "fetch_all_directors", eventDescription = "fetch all directors", serviceName = "DIGI_LOCKER")
	@GetMapping("/fetchall")
	public List<Director> getAllBanks() {
		return directorService.findAll();
	}

	@ProfileExecution
	@EventLogDetails(eventId = "fetchdirectorbyid", eventName = "fetch_director_by_id", eventDescription = "fetch director by id", serviceName = "DIGI_LOCKER")
	@GetMapping("/fetchbyid/{id}")
	public ResponseEntity<Director> getDirectorById(@PathVariable Long id) {
		Director director = directorService.findById(id);
		return ResponseEntity.ok(director);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "fetchdirectorbycompany", eventName = "fetch_director_by_company", eventDescription = "fetch directors by company id", serviceName = "DIGI_LOCKER")
	@GetMapping("/company/{company_id}")
	public ResponseEntity<List<Director>> getDirectorByCompanyId(@PathVariable Long company_id) {
		List<Director> DirectorDetails = directorService.findByCompany_id(company_id);
		return ResponseEntity.ok(DirectorDetails);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "savedirector", eventName = "save_director", eventDescription = "register new director", serviceName = "DIGI_LOCKER")
	@PostMapping("/save")
	public ResponseEntity<?> saveDirector(@RequestParam("name") String name, @RequestParam("email") String email,
	                                      @RequestParam("dinNo") Long dinNo, @RequestParam("aadharNo") Long aadharNo,
	                                      @RequestParam("panNo") String panNo, @RequestParam("passportNo") String passportNo,
	                                      @RequestParam("designation") String designation,
	                                      @RequestParam("dateOfAppointment") String dateOfAppointment,
	                                      @RequestParam("mobileNo") Long mobileNo, @RequestParam("address") String address,
	                                      @RequestParam("active") Boolean active, @RequestParam("image") MultipartFile image,
	                                      @RequestParam("companyid") Long companyid) throws IOException {


		Company company = companyService.findById(companyid);
		if (company == null) {
			return ResponseEntity.badRequest().body("Invalid company ID");
		}

		DirectorForm directorForm = new DirectorForm();
		directorForm.setName(name);
		directorForm.setEmail(email);
		directorForm.setDinNo(dinNo);
		directorForm.setAadharNo(aadharNo);
		directorForm.setPanNo(panNo);
		directorForm.setPassportNo(passportNo);
		directorForm.setDesignation(designation);
		directorForm.setDateOfAppointment(dateOfAppointment);
		directorForm.setMobileNo(mobileNo);
		directorForm.setAddress(address);
		directorForm.setActive(active);
		directorForm.setImage(image);
		directorForm.setCompany(company);

		Director savedDirector = directorService.save(directorForm);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Director registered successfully");
		return ResponseEntity.ok(savedDirector);
	}


	@ProfileExecution
	@EventLogDetails(eventId = "updatedirector", eventName = "update_director", eventDescription = "update director details", serviceName = "DIGI_LOCKER")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateDirector(@PathVariable Long id,
	                                        @RequestParam(value = "name", required = false) String name,
	                                        @RequestParam(value = "email", required = false) String email,
	                                        @RequestParam(value = "dinNo", required = false) Long dinNo,
	                                        @RequestParam(value = "aadharNo", required = false) Long aadharNo,
	                                        @RequestParam(value = "panNo", required = false) String panNo,
	                                        @RequestParam(value = "passportNo", required = false) String passportNo,
	                                        @RequestParam(value = "designation", required = false) String designation,
	                                        @RequestParam(value = "dateOfAppointment", required = false) String dateOfAppointment,
	                                        @RequestParam(value = "dateOfExit", required = false) String dateOfExit,
	                                        @RequestParam(value = "mobileNo", required = false) Long mobileNo,
	                                        @RequestParam(value = "address", required = false) String address,
	                                        @RequestParam(value = "active", required = false) Boolean active,
	                                        @RequestParam(value = "image", required = false) MultipartFile image,
	                                        @RequestParam(value = "companyid", required = false) Long companyid) throws IOException {

		DirectorForm directorForm = new DirectorForm();
		directorForm.setName(name);
		directorForm.setEmail(email);
		directorForm.setDinNo(dinNo);
		directorForm.setAadharNo(aadharNo);
		directorForm.setPanNo(panNo);
		directorForm.setPassportNo(passportNo);
		directorForm.setDesignation(designation);
		directorForm.setDateOfAppointment(dateOfAppointment);
		directorForm.setDateOfExit(dateOfExit);
		directorForm.setMobileNo(mobileNo);
		directorForm.setAddress(address);
		directorForm.setActive(active);
		directorForm.setImage(image);
		directorForm.setCompanyid(companyid);

		Director updatedDirector = directorService.updateDirector(id, directorForm);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Director updated successfully");
		return ResponseEntity.ok(updatedDirector);
	}


	@ProfileExecution
	@EventLogDetails(eventId = "deletedirector", eventName = "delete_director", eventDescription = "delete director", serviceName = "DIGI_LOCKER")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
		directorService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@ProfileExecution
	@EventLogDetails(eventId = "uploaddirectorimage", eventName = "upload_director_image", eventDescription = "upload director image", serviceName = "DIGI_LOCKER")
	@PostMapping("/uploadImage/{id}")
	public ResponseEntity<Void> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
		try {
			byte[] imageBytes = image.getBytes();
			directorService.saveImage(id, imageBytes);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@ProfileExecution
	@EventLogDetails(eventId = "activatedirector", eventName = "activate_director", eventDescription = "activate director", serviceName = "DIGI_LOCKER")
	@PatchMapping("/activate/{id}")
	public void activateDirector(@PathVariable Long id) {
		directorService.activateById(id);
	}

}