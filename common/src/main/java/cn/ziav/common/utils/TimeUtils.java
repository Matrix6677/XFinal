package cn.ziav.common.utils;

import java.util.Calendar;
import java.util.Date;

/** @author Ziav */
public class TimeUtils {

  private TimeUtils() {}

  public static boolean isInSameDay(Date d1, Date d2) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(d1);
    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(d2);
    boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    return isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
  }
}
