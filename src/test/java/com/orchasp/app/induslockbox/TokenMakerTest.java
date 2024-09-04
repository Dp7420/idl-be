package com.orchasp.app.induslockbox;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


public class TokenMakerTest {
	@Test
	public void generateSecretKey() {
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
		System.out.printf("\nKey = [%s]\n", encodedKey);

	}

}
