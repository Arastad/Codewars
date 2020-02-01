import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueenOnChessboardTest {

    @org.junit.jupiter.api.Test

    public void testSampleTestCases() {

        assertEquals(new ArrayList<String>(), QueenOnChessboard.availableMoves(null));
        assertEquals(new ArrayList<String>(), QueenOnChessboard.availableMoves("work?"));
        assertEquals(new ArrayList<String>(), QueenOnChessboard.availableMoves("A10"));
        assertEquals(new ArrayList<String>(), QueenOnChessboard.availableMoves("B0"));
        assertEquals(new ArrayList<String>(), QueenOnChessboard.availableMoves("2"));
        assertEquals(Arrays.asList("A2", "A3", "A4", "A5", "A6", "A7", "A8", "B1", "B2", "C1", "C3", "D1", "D4", "E1", "E5", "F1", "F6", "G1", "G7", "H1", "H8"), QueenOnChessboard.availableMoves("A1"));
        assertEquals(Arrays.asList("A3", "A5", "A7", "B4", "B5", "B6", "C1", "C2", "C3", "C4", "C6", "C7", "C8", "D4", "D5", "D6", "E3", "E5", "E7", "F2", "F5", "F8", "G1", "G5", "H5"), QueenOnChessboard.availableMoves("C5"));
        assertEquals(Arrays.asList("A3", "B3", "C3", "C8", "D3", "D7", "E3", "E6", "F1", "F3", "F5", "G2", "G3", "G4", "H1", "H2", "H4", "H5", "H6", "H7", "H8"), QueenOnChessboard.availableMoves("H3"));
    }
}