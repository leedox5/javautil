package kr.leedox;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("JavaUtil sample");
        System.out.println("repeat: " + Util.repeat("=", 5));
        System.out.println("today: " + CalendarUtil.formatNow("yyyy-MM-dd"));
    }
}
