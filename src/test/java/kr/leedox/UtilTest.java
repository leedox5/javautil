package kr.leedox;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilTest {
    @Test
    public void repeatConcatenatesInputByCount() {
        assertEquals("ababab", Util.repeat("ab", 3));
        assertEquals("", Util.repeat("ab", 0));
    }

    @Test
    public void fillRightPadsToDisplayLength() {
        assertEquals("abc  ", Util.fillRight("abc", 5));
        assertEquals("한글  ", Util.fillRight("한글", 6));
        assertEquals("abcdef", Util.fillRight("abcdef", 3));
    }

    @Test
    public void getFilledStrLenCountsKoreanAsDoubleWidth() {
        assertEquals(3, Util.getFilledStrLen("abc"));
        assertEquals(4, Util.getFilledStrLen("한글"));
        assertEquals(4, Util.getFilledStrLen("a한b"));
    }

    @Test
    public void getMaxLenReturnsLargestDisplayLengthAtIndex() {
        List<List<String>> rows = Arrays.asList(
                Arrays.asList("id", "값"),
                Arrays.asList("long-id", "abc"));

        assertEquals(7, Util.getMaxLen(rows, 0));
        assertEquals(3, Util.getMaxLen(rows, 1));
    }
}
