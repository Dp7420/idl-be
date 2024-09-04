package com.orchasp.app.induslockbox.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignInRequest {
	@NotBlank
	private String email;
	@NotBlank
	private String password;
}
