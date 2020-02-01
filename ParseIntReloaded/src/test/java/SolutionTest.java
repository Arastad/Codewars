import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

    @Test
    public void fixedTests() {
        assertEquals(1 , Parser.parseInt("one"));
        assertEquals(20 , Parser.parseInt("twenty"));
        assertEquals(246 , Parser.parseInt("two hundred forty-six"));
        assertEquals(13, Parser.parseInt("thirteen"));
        assertEquals(15, Parser.parseInt("fifteen"));
        assertEquals(14, Parser.parseInt("fourteen"));
        assertEquals(16, Parser.parseInt("sixteen"));
        assertEquals(17, Parser.parseInt("seventeen"));
        assertEquals(18, Parser.parseInt("eighteen"));
        assertEquals(19, Parser.parseInt("nineteen"));
        assertEquals(119, Parser.parseInt("one hundred nineteen"));
        assertEquals(119000, Parser.parseInt("one hundred nineteen thousand"));
    }
}
