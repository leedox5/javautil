package kr.leedox;

import java.util.List;
import java.util.Objects;

public class Util {
    public static String repeat(String input, int count) {
        Objects.requireNonNull(input, "input must not be null");
        if (count < 0) {
            throw new IllegalArgumentException("count must be >= 0");
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<count; i++) {
            sb.append(input);
        }
        return sb.toString();
    }

    public static String fillRight(String str, int size) {
        Objects.requireNonNull(str, "str must not be null");
        if (size < 0) {
            throw new IllegalArgumentException("size must be >= 0");
        }

        if( size <= getFilledStrLen(str)) {
            return str;
        } else {
            int count = size - getFilledStrLen(str);
            str += repeat(" " , count);
            return str;
        }
    }

    public static int getMaxLen(List<List<String>> list, int idx) {
        Objects.requireNonNull(list, "list must not be null");
        if (idx < 0) {
            throw new IllegalArgumentException("idx must be >= 0");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list must not be empty");
        }

        int max = 0;
        for(List<String> row : list) {
            Objects.requireNonNull(row, "row must not be null");
            if (idx >= row.size()) {
                throw new IllegalArgumentException("idx is out of row bounds");
            }
            max = Math.max(max, getFilledStrLen(row.get(idx)));
        }
        return max;
    }

    public static int getFilledStrLen(String str) {
        Objects.requireNonNull(str, "str must not be null");

        int en = 0;
        int ko = 0;
        int etc = 0;

        char[] charArray = str.toCharArray();
        for(int i = 0 ; i < charArray.length ; i++) {
            if(charArray[i] >= 'A' && charArray[i] <= 'z') {
                en++;
            } else if (charArray[i] >= '\uAC00' && charArray[i] <= '\uD7A3') {
                ko += 2;
            } else {
                etc++;
            }
        }
        int charLen = en + ko + etc ;

        return charLen;
    }
}
