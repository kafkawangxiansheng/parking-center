package net.spm.common.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5Util {

	public static synchronized String encrypt(Object in) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(String.valueOf(in).getBytes(Charset.forName("UTF8")));
		return new String(Hex.encodeHex(messageDigest.digest()));
	}

	public static BigInteger encryptLong(Object in) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(String.valueOf(in).getBytes(Charset.forName("UTF8")));
		return new BigInteger(1, messageDigest.digest());
	}
}
