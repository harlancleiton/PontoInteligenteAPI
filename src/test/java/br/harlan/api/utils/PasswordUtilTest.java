package br.harlan.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilTest {

	private static final String PASSWORD = "123456";
	private final BCryptPasswordEncoder bEncoder = new BCryptPasswordEncoder();
	
	@Test
	public void testNullPassword() throws Exception {
		assertNull(PasswordUtil.generateBCrypt(null));
	}
	
	@Test
	public void testGeneratePasswordHash() {
		String hash = PasswordUtil.generateBCrypt(PASSWORD);
		assertTrue(bEncoder.matches(PASSWORD, hash));
	}
}