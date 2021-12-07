package kr.leedox;

public class Util {
	public static String repeat(String input, int count) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<count; i++) {
			sb.append(input);
		}
		return sb.toString();
	}
	
	public static String fillRight(String str, int size) {
		if( size <= getFilledStrLen(str)) {
			return str;
		} else {
			int count = size - getFilledStrLen(str);
			str += repeat(" " , count); 
			return str;
		}
	}
	
	public static int getFilledStrLen(String str) {
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
