package com.orchasp.app.induslockbox.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kmp_table")
public class KMP {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kmp_id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Email
	@Column(name = "email")
	private String email;
	@Column(name = "aadhar_no")
	private Long aadharNo;
	@Column(name = "passport_no")
	private String passportNo;
	@Column(name = "pan_no")
	private String panNo;
	@Column(name = "designation")
	private String designation;
	@Column(name = "address")
	private String address;
	@Column(name = "state")
	private String state;
	@Column(name = "mobile_no")
	private String mobileNo;
	private String image;
	private String resume;
	
	
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;
	@Column(name = "active_status")
	private boolean active = true;
	
	
	

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

}
