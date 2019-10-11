package com.blue.ape.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;


public class DigestUtil {
	
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final String SHA_256 = "SHA-256";
	
	public static String getSalt() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	public static String sha256Digest(String saltPassword) {
		try {
			byte[] data = saltPassword.getBytes(DEFAULT_ENCODING);
			MessageDigest md = MessageDigest.getInstance(SHA_256);
			return Hex.encodeHexString(md.digest(data)); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return saltPassword;
	}
	
}
