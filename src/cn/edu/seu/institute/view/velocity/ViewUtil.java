package cn.edu.seu.institute.view.velocity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ViewUtil {
	public static String CONTEXT_PATH = "";

	public static String getContextPath() {
		return CONTEXT_PATH;
	}

	public static String fActivityTime(Timestamp time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(new Date(time.getTime()));
	}

	public static String fArticlePostTime(String time) {
		if (time == null || time.isEmpty()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		try {
			Date date = sdf.parse(time);
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String fTime(String time) {
		if (time == null || time.isEmpty()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		try {
			Date date = sdf.parse(time);
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 时间
	public static String today() {
		Calendar today = Calendar.getInstance(Locale.CHINA);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(today.getTime());
	}
}
