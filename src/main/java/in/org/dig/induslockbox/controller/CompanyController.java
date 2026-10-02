package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.dto.CompanyDTO;
import in.org.dig.induslockbox.entity.Company;
import in.org.dig.induslockbox.service.CompanyService;
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
@RequestMapping("/api/admin/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@ProfileExecution
	@EventLogDetails(eventId = "fetchallcompanies", eventName = "fetch_all_companies", eventDescription = "fetch all companies", serviceName = "DIGI_LOCKER")
	@GetMapping("/fetchall")
	public List<Company> findAll() {
		return companyService.findAll();
	}

	@ProfileExecution
	@EventLogDetails(eventId = "fetchcompanybyid", eventName = "fetch_company_by_id", eventDescription = "fetch company by id", serviceName = "DIGI_LOCKER")
	@GetMapping("/fetchbyid/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
		Company company = companyService.findById(id);
		return ResponseEntity.ok(company);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "savecompany", eventName = "save_company", eventDescription = "register new company", serviceName = "DIGI_LOCKER")
	@PostMapping("/save")
	public ResponseEntity<?> registerCompany(@RequestParam("companyCode") String companyCode,
	                                         @RequestParam("companyname") String companyname, @RequestParam("cin") String cin,
	                                         @RequestParam("registerNo") String registerNo,
	                                         @RequestParam("dateOfIncorporation") String dateOfIncorporation,
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfIncorporation,
                                             @RequestParam("address") String address, @RequestParam("city") String city,
                                             @RequestParam("state") String state, @RequestParam("pincode") String pincode,
                                             @RequestParam("email") String email, @RequestParam("phoneNo") String phoneNo,
                                             @RequestParam("telephoneNo") String telephoneNo, @RequestParam("faxNo") String faxNo,
                                             @RequestParam("website") String website, @RequestParam("logoName") MultipartFile logoName,
                                             @RequestParam("active") Boolean active) throws IOException {

		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setCompanyCode(companyCode);
		companyDTO.setCompanyname(companyname);
		companyDTO.setCin(cin);
		companyDTO.setRegisterNo(registerNo);
		companyDTO.setDateOfIncorporation(dateOfIncorporation);
		companyDTO.setAddress(address);
		companyDTO.setCity(city);
		companyDTO.setState(state);
		companyDTO.setPincode(pincode);
		companyDTO.setEmail(email);
		companyDTO.setPhoneNo(phoneNo);
		companyDTO.setTelephoneNo(telephoneNo);
		companyDTO.setFaxNo(faxNo);
		companyDTO.setWebsite(website);
		companyDTO.setLogoName(logoName);
		companyDTO.setActive(active);

		Company savedCompany = companyService.save(companyDTO);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Company registered successfully");
		return ResponseEntity.ok(response);
	}

	@ProfileExecution
	@EventLogDetails(eventId = "updatecompany", eventName = "update_company", eventDescription = "update company details", serviceName = "DIGI_LOCKER")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCompany(
			@PathVariable Long id,
			@RequestParam(value = "companyCode", required = false) String companyCode,
			@RequestParam(value = "companyname", required = false) String companyname,
			@RequestParam(value = "cin", required = false) String cin,
			@RequestParam(value = "registerNo", required = false) String registerNo,
			@RequestParam(value = "dateOfIncorporation", required = false) String dateOfIncorporation,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "pincode", required = false) String pincode,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "phoneNo", required = false) String phoneNo,
			@RequestParam(value = "telephoneNo", required = false) String telephoneNo,
			@RequestParam(value = "faxNo", required = false) String faxNo,
			@RequestParam(value = "website", required = false) String website,
			@RequestParam(value = "logoName", required = false) MultipartFile logoName,
			@RequestParam(value = "active", required = false) Boolean active) throws IOException {

		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setCompanyCode(companyCode);
		companyDTO.setCompanyname(companyname);
		companyDTO.setCin(cin);
		companyDTO.setRegisterNo(registerNo);
		companyDTO.setDateOfIncorporation(dateOfIncorporation);
		companyDTO.setAddress(address);
		companyDTO.setCity(city);
		companyDTO.setState(state);
		companyDTO.setPincode(pincode);
		companyDTO.setEmail(email);
		companyDTO.setPhoneNo(phoneNo);
		companyDTO.setTelephoneNo(telephoneNo);
		companyDTO.setFaxNo(faxNo);
		companyDTO.setWebsite(website);
		companyDTO.setLogoName(logoName);
		companyDTO.setActive(active);

		Company updatedCompany = companyService.update(id, companyDTO);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Company updated successfully");
		return ResponseEntity.ok(updatedCompany);
	}


	@ProfileExecution
	@EventLogDetails(eventId = "deletecompany", eventName = "delete_company", eventDescription = "delete company", serviceName = "DIGI_LOCKER")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteOrganisation(@PathVariable Long id) {
		companyService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@ProfileExecution
	@EventLogDetails(eventId = "activatecompany", eventName = "activate_company", eventDescription = "activate company", serviceName = "DIGI_LOCKER")
	@PatchMapping("/activate/{id}")
	public void activateCompany(@PathVariable Long id) {
		companyService.activateById(id);
	}

}