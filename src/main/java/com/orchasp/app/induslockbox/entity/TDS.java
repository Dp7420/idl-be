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
@Table(name = "tds_table")
public class TDS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tan_id")
	private Long id;
	@Column(name = "tan_No")
	private String tanNo;
	@Column(name = "email")
	@Email
	private String email;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "user_id")
	private String userid;
	@Column(name = "password")
	private String password;
	@Column(name = "surrendered")
	private String surrendered;
	@Column(name = "signatory")
	private String signatory;
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
