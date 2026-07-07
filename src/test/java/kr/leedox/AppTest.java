package kr.leedox;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class AppTest {
    @Test
    public void mainPrintsUsageSample() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try {
            System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8.name()));
            App.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        String[] lines = out.toString(StandardCharsets.UTF_8.name()).trim().split("\\R");
        assertEquals("JavaUtil sample", lines[0]);
        assertEquals("repeat: =====", lines[1]);
    }
}
