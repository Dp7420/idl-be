package com.orchasp.app.induslockbox.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company_table")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Long companyid;
	@Column(name = "company_code")
	private String companyCode;
	@Column(name = "cin")
	private String cin;
	@Column(name = "company_name")
	private String companyname;

	@Column(name = "date_of_incorporation")
	private String dateOfIncorporation;

	@Column(name = "register_no")
	private String registerNo;

	@Column(name = "company_phone")
	private String phoneNo;

	@Column(name = "company_email")
	@Email
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "company_website")
	private String website;

	@Column(name = "telephone_no")
	private String telephoneNo;

	@Column(name = "fax_no")
	private String faxNo;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "pincode")
	private String pincode;
	private String image;

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

}
