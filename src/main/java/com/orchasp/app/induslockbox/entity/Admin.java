package com.orchasp.app.induslockbox.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
@Entity
@Table(name = "admin", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "userName"),
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "mobile")})
public class Admin{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String userName;
	
	@NotBlank
	@Size(max = 12)
	private String mobile;

	@NotBlank
	@Size(min = 8)
	@Email
	private String email;

	@NotBlank
	@Size(min = 8)
	private String password; 
	
//	@NotBlank
//	@Size(min = 25)
//	private String firstName;
//	
//	@NotBlank
//	@Size(min = 25)
//	private String lastName;
//	
//	@NotBlank
//	private String displayName;
//	
//	@NotBlank
//	@Lob
//	private  byte[] profile_pic;
//	
//	@NotBlank
//	private Timestamp dob;
//	
//	@NotBlank
//	private String address;
//	
//	@NotBlank
//	@Column(name = "Status")
//	private int status;
//	
//	@NotBlank
//	@Column(name = "createdBy")
//	private Long createdBy;
//	
//	@NotBlank
//	@Column(name = "updatedBy")
//	private Long updatedBy;

	@Enumerated(EnumType.STRING)
	private Role role;

}
