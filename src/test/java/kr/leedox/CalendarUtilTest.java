package kr.leedox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class CalendarUtilTest {
    @Test
    public void extractsDateAndTimeFields() {
        Date date = CalendarUtil.getDate(2024, 6, 17, 13, 42, 9, 123);

        assertEquals(2024, CalendarUtil.getYear(date));
        assertEquals(6, CalendarUtil.getMonth(date));
        assertEquals(17, CalendarUtil.getDate(date));
        assertEquals("월", CalendarUtil.getDay(date));
        assertEquals(13, CalendarUtil.getHour(date));
        assertEquals(42, CalendarUtil.getMinute(date));
        assertEquals(9, CalendarUtil.getSecond(date));
    }

    @Test
    public void getNowMinuteUsesMinuteField() {
        Calendar before = Calendar.getInstance();
        int actual = CalendarUtil.getNowMinute();
        Calendar after = Calendar.getInstance();

        int beforeMinute = before.get(Calendar.MINUTE);
        int afterMinute = after.get(Calendar.MINUTE);

        assertTrue(actual >= 0 && actual <= 59);
        assertTrue(actual == beforeMinute || actual == afterMinute);
    }

    @Test
    public void parsesAndFormatsDates() {
        Date date = CalendarUtil.getDate("2024-6-17 13:42:09");

        assertEquals("2024-06-17 13:42:09", CalendarUtil.format(date, "yyyy-MM-dd HH:mm:ss"));
        assertEquals("24/06/17", CalendarUtil.reformat("2024-06-17", "yyyy-MM-dd", "yy/MM/dd"));
    }

    @Test
    public void betweenReturnsDayDifference() {
        Date from = CalendarUtil.getDate(2024, 6, 17);
        Date to = CalendarUtil.getDate(2024, 6, 20);

        assertEquals(3, CalendarUtil.between(from, to));
    }
}
