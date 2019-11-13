package net.spm.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Vincent
 */
public class AESUtil {

	public static String encrypt(String key, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static String decrypt(String key, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/*Just for test
	public static void main(String args[]) {

		String key = RandomStringUtils.random(16, true, true);
		System.out.println("key:" + key);
		String sourceString = "{\"username\":\"Vũ Cao\",\"password\":\"Cao Hồng Vũ\"}";
		System.out.println("Original String:" + sourceString);
		String encryptedString = encrypt(key, sourceString);
		System.out.println("Encrypted String:" + encryptedString);
		String decryptedString = decrypt(key, encryptedString);
		System.out.println("Decrypted String:" + decryptedString);
	}*/
}
