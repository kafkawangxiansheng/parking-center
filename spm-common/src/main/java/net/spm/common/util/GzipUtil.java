package net.spm.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {
	
	public static byte[] compress(String sourceString) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(sourceString.length());
		GZIPOutputStream gzip = new GZIPOutputStream(bos);
		gzip.write(sourceString.getBytes());
		gzip.close();
		byte[] compressedByte = bos.toByteArray();
		bos.close();
		return compressedByte;
	}
	
	public static String decompress(byte[] compressedByte) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(compressedByte);
		GZIPInputStream gis = new GZIPInputStream(bis);
		BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		gis.close();
		bis.close();
		return sb.toString();
	}
	
}
