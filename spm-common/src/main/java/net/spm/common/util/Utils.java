package net.spm.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

public class Utils {

	static String source = "1029384756";
	static String target = "6058493721";
	static String phone_regexp = "^(0|84)(9\\d|16[2-9]|3[2-9]|12\\d|8[1-5]|7[06789]|5[2]|86|88|89|186|188|5[689]|199)(\\d{7})$";
	static int NUMBER_PLATE_LENGTH_MIN = 6;
	// Bien dan su, Nha nuoc
	static String regex1 = "^\\d{2}([A-Z]|[a-z])\\d{4}\\d?";
	// Bien cong ty, ngoai giao
	static String regex2 = "^\\d{2}([A-Z]|[a-z]){2}\\d{5}";
	static String regex3 = "^\\d{5}([A-Z]|[a-z]){2}\\d{2}";
	// Bien quan doi
	static String regex4 = "^([A-Z]|[a-z]){2}\\d{4}";
	static String regex5 = "^\\d{4}([A-Z]|[a-z]){2}";

	public static final String phone_prefix = "0";
	public static final String phone_prefix_84 = "84";

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static String getCurrentDate(String... format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		if (format != null && format.length > 0) {
			dateFormat = new SimpleDateFormat(format[0]);
		}
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public static String getForeverDate(String... format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		if (format != null && format.length > 0) {
			dateFormat = new SimpleDateFormat(format[0]);
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2100);

		return dateFormat.format(cal.getTime());
	}

	public static String getDatetimeFormatVN(Date date, String... format) {
		String finalFormat = "dd/MM/yyyy HH:mm";
		if (format != null && format.length > 0) {
			finalFormat = format[0];
		}
		if (date == null) {
			date = new Date();
			return (new SimpleDateFormat(finalFormat).format(date));
		} else {
			return (new SimpleDateFormat(finalFormat).format(date));
		}
	}

	public static String getDatetimeFormatVN(Long time) {
		return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(time * 1000));
	}

	public static String amountToCurrencyString(float amountTotal) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(amountTotal) + "đ";
	}

	public static boolean createFolder(String path) {
		File theDir = new File(path);
		if (!theDir.exists()) {
			boolean result = false;

			try {
				System.out.println("Begin make dir: " + path);
				theDir.setWritable(true, false);
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				result = false;
				se.printStackTrace();
			}
			return result;
		} else {
			return true;
		}
	}

	public static void zip(List<String> fileList, String output) {
		try {

			// create byte buffer
			byte[] buffer = new byte[1024];

			FileOutputStream fos = new FileOutputStream(output);

			ZipOutputStream zos = new ZipOutputStream(fos);

			for (String file : fileList) {

				File srcFile = new File(file);

				FileInputStream fis = new FileInputStream(srcFile);

				// begin writing a new ZIP entry, positions the stream to the
				// start of the entry
				// data
				zos.putNextEntry(new ZipEntry(srcFile.getName()));

				int length;

				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();

				// close the InputStream
				fis.close();

			}

			// close the ZipOutputStream
			zos.close();

		} catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
		}
	}

	public static String readHtmlFromFile(String filePath) {
		try {
			InputStream is = new FileInputStream(filePath);
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));

			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();

			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}
			buf.close();
			return sb.toString();
		} catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
			return "";
		}
	}

	public static String stringVietnameseMoneyFormatWithFloat(float amount) {
		if (amount <= 1000) {
			return String.format("%s vnđ", amount);
		}
		try {
			NumberFormat formatter = new DecimalFormat("###,###");
			String resp = formatter.format(amount);
			resp = resp.replaceAll(",", ".");
			String str_price = String.format("%s vnđ", resp);
			return str_price;
		} catch (Exception e) {
			return "0vnđ";
		}

	}

	public static String createRandomString(int length) {
		String stringRanger = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return new SecureRandom().ints(length, 0, stringRanger.length()).mapToObj(stringRanger::charAt)
				.map(Object::toString).collect(Collectors.joining());
	}

	public static String createRandomNumber(int length) {
		String stringRanger = "0123456789";
		return new SecureRandom().ints(length, 0, stringRanger.length()).mapToObj(stringRanger::charAt)
				.map(Object::toString).collect(Collectors.joining());
	}

	public static String encodeShaPassword(String password) {
		ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
		String hashedPassword = passwordEncoder.encodePassword(password, null);
		return hashedPassword;
	}

	public static String decodeShaPassword(String hashPassword) {
		return "";
	}

	public static String encodeSHA256(List<String> values) {
		String sha256hex = DigestUtils.sha256Hex(values.stream().collect(Collectors.joining(", ")));
		return sha256hex;
	}

	public static String convertStringToSlug(String str) {
		String slug = "";
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			slug = pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
		} catch (Exception e) {
			e.printStackTrace();
			slug = "";
		}
		return slug;
	}

	public static String removeVietnameseFromString(String str) {
		String slug = "";
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			slug = pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll("đ", "d");
		} catch (Exception e) {
			e.printStackTrace();
			slug = "";
		}
		return slug;
	}

	public static String decodeJWTToken(String token) {
		String[] split_string = token.split("\\.");
		String base64EncodedBody = split_string[1];
		Base64 base64Url = new Base64(true);
		String body = new String(base64Url.decode(base64EncodedBody));
		return body;
	}

	public static String removeAllNoneNumberic(String s) {
		if (s == null) {
			return "";
		} else {
			return s.replaceAll("[^\\d.]", "");
		}

	}

	public static String saveUploadedFile(MultipartFile image, String resourcePath, String filePath) {
		String imageUrl = "";
		if (!image.isEmpty()) {
			try {
				createFolder(resourcePath + filePath);
				byte[] bytes = image.getBytes();
				filePath = filePath + Calendar.getInstance().getTimeInMillis() + image.getOriginalFilename();
				String fPath = resourcePath + filePath;
				Path path = Paths.get(fPath);
				Files.write(path, bytes);
				imageUrl = filePath;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return imageUrl;
	}

	public static String encryptOrderId(long orderId) {
		String s = String.format("%012d", orderId);
		char[] result = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = source.indexOf(c);
			result[i] = target.charAt(index);
		}

		return new String(result);
	}

	public static long decryptOrderId(String s) {
		char[] result = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = target.indexOf(c);
			result[i] = source.charAt(index);
		}

		return Long.parseLong((new String(result)));
	}

	public static long getDateTimeTicks() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		return date.getTime();
	}

	public static String generateOrderCode(String prefix) {
		return String.format(prefix + "%s", String.valueOf(getDateTimeTicks()));
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static List<Integer> stringToIntegerList(String string, String... separateChar) {

		if (string == null) {
			return null;
		}
		String separatedBy = ",";
		if (separateChar != null && separateChar.length > 0) {
			separatedBy = separateChar[0];
		}
		List<Integer> integerList = new LinkedList<>();

		String[] stringList = string.split(separatedBy);
		for (String s : stringList) {
			if (s != null && !s.isEmpty()) {
				integerList.add(Integer.valueOf(s));
			}

		}
		return integerList;
	}

	public static List<Long> stringToLongList(String string, String... separateChar) {
		if (string == null || string.isEmpty()) {
			return new ArrayList<>();
		}
		String separatedBy = ",";
		if (separateChar != null && separateChar.length > 0) {
			separatedBy = separateChar[0];
		}
		List<Long> integerList = new LinkedList<>();

		String[] stringList = string.split(separatedBy);
		for (String s : stringList) {
			if (s != null && !s.isEmpty()) {
				integerList.add(Long.valueOf(s));
			}

		}
		return integerList;
	}

	public static Set<Long> stringToLongSet(String string, String... separateChar) {
		String separatedBy = ",";
		if (separateChar != null && separateChar.length > 0) {
			separatedBy = separateChar[0];
		}
		Set<Long> integerList = new HashSet<>();

		String[] stringList = string.split(separatedBy);
		for (String s : stringList) {
			if (s != null && !s.isEmpty()) {
				integerList.add(Long.valueOf(s));
			}

		}
		return integerList;
	}

	public static List<String> stringSplitToList(String string, String regex) {
		if (string == null)
			return new ArrayList<>();
		List<String> tmp = new LinkedList<>(Arrays.asList(string.split(regex)));
		tmp.removeIf(p -> p.isEmpty());
		return tmp;
	}

	public static float correctWeightForShipping(float weight) {
		float decimalPart = (weight - (int) weight);
		if (decimalPart > 0.5) {
			decimalPart = 1;
		} else if (decimalPart < 0.5) {
			decimalPart = (float) 0.5;
		}
		return (int) weight + decimalPart;
	}

	public static Map<String, String> getQueryStringMap(String query) {
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}

	public static String getClientIp(HttpServletRequest request) {

		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}

	public static Set<Long> listRemoveNull(Set<Long> set) {
		List<Long> list = new ArrayList<>(set);
		List<Long> listWithoutNulls = list.parallelStream().filter(Objects::nonNull).collect(Collectors.toList());

		return new HashSet<>(listWithoutNulls);
	}

	public static float getCashBackForCustomer(float categoryCashBackPercent, float cashBackPercentForNeedii) {
		if (categoryCashBackPercent > 0) {
			if (cashBackPercentForNeedii > 0) {
				return ((categoryCashBackPercent / 100) * ((100 - cashBackPercentForNeedii) / 100));
			}
			return categoryCashBackPercent / 100;
		}
		return 0;
	}

	public static int getBannesOnNewsfeedPage(int totalNewsfeed, int start, int frequency) {
		return (int) Math.ceil((float) (totalNewsfeed - (start - 1)) / (frequency + 1));
	}

	public static int startPosition(int start, int frequency, int page, int limit) {
		frequency = frequency + 1;
		for (int i = 1; i <= page - 1; i++) {
			int last = 0;
			for (int j = 1; j <= limit; j++) {
				if (j == start) {
					last = j;
				} else {
					if (j % frequency == 0 || j % frequency == start) {
						last = j;
					}
				}
			}
			start = last + frequency - limit;
		}
		return start;
	}

	public static String createRandomId() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public static String buildPhoneNumber(String phoneNumber) {
		StringBuilder strPhone = new StringBuilder("84");
		strPhone.append(phoneNumber);
		return strPhone.toString();
	}

	public static void main(String[] args) {

		System.out.println(validateEmail("liemnh267@gmail.com"));
	}

	public static boolean validateVNPhoneNumber(String phoneNumber) {
		if (StringUtils.isEmpty(phoneNumber)) {
			return Boolean.FALSE;
		}
		if (phoneNumber.matches(phone_regexp)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static boolean validateNumberPlate(String numberPlate) {
		if (StringUtils.isEmpty(numberPlate))
			return false;
		numberPlate = numberPlate.replaceAll(" ", "");
		if (StringUtils.isEmpty(numberPlate) || numberPlate.length() < NUMBER_PLATE_LENGTH_MIN)
			return false;
		numberPlate = numberPlate.toUpperCase();

		if (numberPlate.matches(regex1) || numberPlate.matches(regex2) || numberPlate.matches(regex3)
				|| numberPlate.matches(regex4) || numberPlate.matches(regex5))
			return true;

		return false;

	}

	public static String buildPhoneNumberWithPrefix(String phoneNumber) {
		int phoneNotZero = Integer.parseInt(phoneNumber);
		String phone = String.valueOf(phoneNotZero);
		StringBuilder strPhone = new StringBuilder("84");
		strPhone.append(phone);
		return strPhone.toString();
	}

	public static boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
}
