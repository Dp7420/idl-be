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
@Table(name = "esi_table")
public class ESI {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "esi_id")
	private Long id;
	@Column(name = "employer_codeNo")
	private Long employerCodeNo;
	@Column(name = "employer_name")
	private String employerName;
	@Column(name = "regional_office")
	private String ro;
	@Column(name = "lin")
	private Long lin;
	@Column(name = "user_id")
	private String userid;
	@Column(name = "signatory")
	private String signatory;
	@Column(name = "email_id")
	@Email
	private String emailId;

	@Column(name = "mobile_no")
	private String mobileNo;
	@Column(name = "password")
	private String password;
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
