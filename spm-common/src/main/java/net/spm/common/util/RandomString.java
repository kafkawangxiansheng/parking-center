package net.spm.common.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Vincent
 */
public class RandomString {

    public static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String createUUID() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return UUID.randomUUID().toString();
    }

    public String createString() throws NoSuchAlgorithmException {
        int length = 20;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
