package in.org.dig.induslockbox.controller;

import in.org.dig.induslockbox.aspect.ProfileExecution;
import in.org.dig.induslockbox.request.SignInRequest;
import in.org.dig.induslockbox.request.SignUpRequest;
import in.org.dig.induslockbox.service.AuthenticationService;
import in.org.dig.induslockbox.service.OtpService;
import in.org.dig.platform.LoggerUtil.util.EventLogDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AdminController {
	@Autowired
	private AuthenticationService service;
	@Autowired
	private OtpService service2;

//	http://localhost:8082/api/auth/signup
@ProfileExecution
@EventLogDetails(eventId = "signup", eventName = "sign_up", eventDescription = "user registration", serviceName = "DIGI_LOCKER")
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest request) {
		return ResponseEntity.ok(service.signup(request));
	}

//	http://localhost:8082/api/auth/signin
	@PostMapping("/signin")
	@ProfileExecution
	@EventLogDetails(eventId = "signin", eventName = "sign_in", eventDescription = "user login", serviceName = "DIGI_LOCKER")
	public ResponseEntity<?> signin(@Valid @RequestBody SignInRequest request) {
		return ResponseEntity.ok(service.signin(request));
	}

	@ProfileExecution
	@EventLogDetails(eventId = "requestOpt", eventName = "request_otp", eventDescription = "user receive otp to authenticate", serviceName = "DIGI_LOCKER")
	@PostMapping("/requestOtp")
	public ResponseEntity<?> sendOtpToMail(@RequestParam String email) {
		return ResponseEntity.ok(service2.sendOtp(email));
	}

	@ProfileExecution
	@EventLogDetails(eventId = "verifyOtp", eventName = "verify_otp", eventDescription = "user verify otp for authenticate", serviceName = "DIGI_LOCKER")
	@PostMapping("/verifyOtp")
	public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
		return ResponseEntity.ok(service2.verifyOtp(email, otp));
	}

	@ProfileExecution
	@EventLogDetails(eventId = "changePassword", eventName = "change_password", eventDescription = "user changing the password", serviceName = "DIGI_LOCKER")
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestParam String email, @RequestParam String otp,@RequestParam String newPassword) {
		return ResponseEntity.ok(service2.changePassword(email, otp, newPassword));
	}

	@ProfileExecution
	@EventLogDetails(eventId = "home", eventName = "home calling", eventDescription = "event api calling", serviceName = "Digi_Locker")
	@GetMapping("/home")
	public String local() {
		return "Local home";
	}

}
