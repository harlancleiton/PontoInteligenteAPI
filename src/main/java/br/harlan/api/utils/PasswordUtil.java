package br.harlan.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordUtil.class);
	
	public PasswordUtil() {
	}
	
	/**
	 * Generate hash using BCrypt
	 * 
	 * @param password
	 * @return String
	 */
	public static String generateBCrypt(String password) {
		if(password == null)
			return password;
		LOGGER.info("Gerando hash com BCrypt.");
		BCryptPasswordEncoder bPasswordEncoder = new BCryptPasswordEncoder();
		return bPasswordEncoder.encode(password);
	}
}