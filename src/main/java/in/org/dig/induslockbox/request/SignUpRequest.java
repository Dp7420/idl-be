package in.org.dig.induslockbox.request;

import in.org.dig.induslockbox.entity.Role;

import lombok.Data;

@Data
public class SignUpRequest {
	private String userName;
	private String email;
	private String password;
	private String mobile;
//	private String firstName;
//	private String lastName;
//	private String displayName;
//	private  byte[] profile_pic;
//	private Timestamp dob;
//	private String address;
//	private int status;
//	private Long createdBy;
//	private Long updatedBy;
	private Role role;
	
	
}
