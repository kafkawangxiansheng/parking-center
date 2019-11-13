package net.spm.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Date time utils
 *
 * @author vincent
 */
public class DateTimeUtil {
	public static String formatDate(Long dateInLong, String... pattern) {
		if (dateInLong == null) {
			return null;
		}
		if (pattern == null || pattern.length == 0) {
			pattern = new String[1];
			pattern[0] = "YYYY-MM-dd HH:mm:ss";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern[0]);
		return dateFormat.format(dateInLong);
	}

	public static Date getCurrentDateTime() {
		Calendar calendate = Calendar.getInstance();
		return calendate.getTime();
	}

	public static List<DateDto> getDayOfMonths(Date fromDate, Date toDate) {
		List<DateDto> lst = new ArrayList<>();
		Calendar beginCalendar = Calendar.getInstance();
		Calendar finishCalendar = Calendar.getInstance();
		beginCalendar.setTime(fromDate);
		finishCalendar.setTime(toDate);
		DateFormat formater = new SimpleDateFormat("MM/yyyy");
		int i = 1;
		int fish = getDiffMonths(fromDate, toDate);
		while (beginCalendar.before(finishCalendar)) {
			DateDto objDateDto = new DateDto();
			YearMonth yearMonthObject = YearMonth.of(beginCalendar.get(Calendar.YEAR),
					beginCalendar.get(Calendar.MONTH) + 1);
			int daysInMonth = yearMonthObject.lengthOfMonth(); // 28
			String date = formater.format(beginCalendar.getTime()).toUpperCase();
			if (i == 1) {
				objDateDto.setDay(getDiffRangeDate(fromDate, getLastDayOfMonth(beginCalendar.getTime())));
			} else if (fish == i) {
				objDateDto
						.setDay(getDiffRangeDate(getFirstDayOfMonth(beginCalendar.getTime()), beginCalendar.getTime()));
			} else {
				objDateDto.setDay(daysInMonth);
			}
			objDateDto.setMonth(date);
			objDateDto.setDayOfMonth(daysInMonth);
			lst.add(objDateDto);
			beginCalendar.add(Calendar.MONTH, 1);
			i++;
		}
		return lst;
	}

	public static Date getLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	public static Date getFirstDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	public static int getDiffRangeDate(Date fromDate, Date toDate) {
		long timediff = (TimeUnit.DAYS.convert(toDate.getTime() - fromDate.getTime(), TimeUnit.MILLISECONDS) + 1);
		return Long.valueOf(timediff).intValue();
	}

	public static int getDiffMonths(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int monthsBetween = 0;
		int dateDiff = end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH);
		if (dateDiff < 0) {
			int borrrow = end.getActualMaximum(Calendar.DAY_OF_MONTH);
			dateDiff = (end.get(Calendar.DAY_OF_MONTH) + borrrow) - start.get(Calendar.DAY_OF_MONTH);
			monthsBetween--;
			if (dateDiff > 0) {
				monthsBetween++;
			}
		} else {
			monthsBetween++;
		}
		monthsBetween += end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
		monthsBetween += (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
		return monthsBetween;
	}

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		List<DateDto> result = getDayOfMonths(simpleDate.parse("2018-12-12"), simpleDate.parse("2019-03-13"));

		System.out.println(result);
	}

}
