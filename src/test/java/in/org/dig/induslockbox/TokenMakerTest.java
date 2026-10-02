package in.org.dig.induslockbox;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;


public class TokenMakerTest {
	@Test
	public void generateSecretKey() {
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
		System.out.printf("\nKey = [%s]\n", encodedKey);

	}

}
