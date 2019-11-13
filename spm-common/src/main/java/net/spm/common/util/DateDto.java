package net.spm.common.util;

public class DateDto {
	private int day;
	private String month;
	private int dayOfMonth;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "DateDto [day=" + day + ", month=" + month + ", dayOfMonth=" + dayOfMonth + "]";
	}

}
