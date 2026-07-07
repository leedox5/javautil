package kr.leedox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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

    @Test
    public void repeatRejectsInvalidArguments() {
        assertThrows(NullPointerException.class, () -> Util.repeat(null, 1));
        assertThrows(IllegalArgumentException.class, () -> Util.repeat("a", -1));
    }

    @Test
    public void fillRightRejectsInvalidArguments() {
        assertThrows(NullPointerException.class, () -> Util.fillRight(null, 2));
        assertThrows(IllegalArgumentException.class, () -> Util.fillRight("abc", -1));
    }

    @Test
    public void getFilledStrLenRejectsNull() {
        assertThrows(NullPointerException.class, () -> Util.getFilledStrLen(null));
    }

    @Test
    public void getMaxLenRejectsInvalidInputs() {
        assertThrows(NullPointerException.class, () -> Util.getMaxLen(null, 0));
        assertThrows(IllegalArgumentException.class, () -> Util.getMaxLen(Arrays.<List<String>>asList(), 0));
        assertThrows(IllegalArgumentException.class, () -> Util.getMaxLen(Arrays.asList(Arrays.asList("a")), -1));
        assertThrows(IllegalArgumentException.class, () -> Util.getMaxLen(Arrays.asList(Arrays.asList("a")), 1));
        assertThrows(NullPointerException.class, () -> Util.getMaxLen(Arrays.asList((List<String>) null), 0));
        assertThrows(NullPointerException.class, () -> Util.getMaxLen(Arrays.asList(Arrays.asList((String) null)), 0));
    }
}
