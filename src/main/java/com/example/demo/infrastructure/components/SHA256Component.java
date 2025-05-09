package com.example.demo.infrastructure.components;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class SHA256Component {

	public String encrypt(String value) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();

			for (byte b : hash) {
				hexString.append(String.format("%02x", b));
			}

			return hexString.toString();
		} 
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Erro ao criptografar com SHA-256", e);
		}
	}
}
