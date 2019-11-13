package net.spm.common.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Vincent
 */
public class Base64Util {

    public static String encode(String in) {
        byte[] encodedBytes = Base64.encodeBase64(in.getBytes());
        return new String(encodedBytes);
    }

    public static String decode(String in) {
        byte[] decodedBytes = Base64.decodeBase64(in.getBytes());
        return new String(decodedBytes);
    }
}
