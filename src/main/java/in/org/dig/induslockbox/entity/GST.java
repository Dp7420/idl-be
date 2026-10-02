package in.org.dig.induslockbox.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gst_table")
public class GST {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gst_id")
	private Long id;
	@Column(name = "gst_number")
	private String gstNumber;
	@Column(name = "user_id")
	private String userid;
	@Column(name = "password")
	private String password;
	@Email
	@Column(name = "email")
	private String email;
	@Column(name = "mobile_no")
	private String mobileno;
	@Column(name = "address")
	private String address;
	@Column(name = "signatory")
	private String signatory;
	@Column(name = "state")
	private String state;
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
