import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.StreamHandler;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void testNewKnight() {
        Knight myKnight;
        myKnight = new Knight("a", "1");
        assertTrue(Knight.class.isInstance(myKnight));
    }

    @Test
    void testNewKnightRank() {
        Knight myKnight;

        myKnight = new Knight("a", "1");
        assertEquals("1", myKnight.getRank());
    }

    @Test
    void testNewKnightFile() {
        Knight myKnight;

        myKnight = new Knight("a", "1");
        assertEquals("a", myKnight.getFile());
    }

    @Test
    void testGetKnightPosition() {
        Knight myKnight;

        myKnight = new Knight("a", "1");
        assertEquals("a1", myKnight.getPosition());
    }

    @Test
    void testInvalidRank() {
        Knight myKnight = null;

        try {
            myKnight = new Knight("a", "10");
            fail("Should not have been able to set rank to 10");
        } catch (final RuntimeException rte) {
            assertEquals("Invalid Rank 10", rte.getMessage());
        }
    }

    @Test
    void testInvalidFile(){
        Knight myKnight = null;

        try {
            myKnight = new Knight("z","1");
            fail("Should not have been able to set file to z");
        } catch (final RuntimeException rte){
            assertEquals("Invalid File z", rte.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({"e, 1"})
    void testMoveKnightOffBoardRank(final String file, final String rank){
        Knight myKnight = null;

        myKnight = new Knight(file, rank);
        try {
            myKnight.move(2, -1);
        } catch (final RuntimeException rte){
            assertEquals("Invalid Rank 0", rte.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({"a, 1"})
    void testMoveKnightOffBoardFile(final String file, final String  rank){
        Knight myKnight = null;

        myKnight = new Knight(file, rank);
        try {
            myKnight.move(-2, 1);
        } catch (final RuntimeException rte){
            assertEquals("Index -2 out of bounds for length 8", rte.getMessage());
        }
    }

    @ParameterizedTest
    @MethodSource("positionAndDistanceProvider")
    void testMove(final String startFile, final String startRank,
                  final int fileDistance, final int rankDistance,
                  final String endFile, final String endRank) {
        Knight myKnight = null;

        myKnight = new Knight(startFile, startRank);
        myKnight.move(fileDistance, rankDistance);
        assertEquals(endFile+endRank, myKnight.getPosition());
    }

    private static Stream<Arguments> positionAndDistanceProvider(){
        return Stream.of(Arguments.of("c", "1", -2, 1, "a", "2"),
                         Arguments.of("a", "1", 2, 1, "c", "2"),
                         Arguments.of("c", "3", -1, -2, "b", "1"),
                         Arguments.of("c", "1", -1, 2, "b", "3"),
                         Arguments.of("c", "3", -2, -1, "a", "2"));
    }

    @Test
    void testDeterminePossibleLocationsOneLayer(){
        Knight myKnight = null;
        List<String> list = new ArrayList<String>();

        myKnight = new Knight("d", "4");
        list = myKnight.determineAllPossibleLocations();
        assertEquals(8, list.size());;
    }

    @Test
    void testDeterminePossibleLocationsTwoLayers(){
        Knight myKnight = null;
        List<String> list = new ArrayList<String>();
        List<String> newList = new ArrayList<String>();

        myKnight = new Knight("d", "4");
        list = myKnight.determineAllPossibleLocations();

        int currentIndex = 0;
        while (currentIndex < list.size()){
            String nextLocation = new String();
            myKnight = null;
            nextLocation = list.get(currentIndex);
            newList.add(nextLocation);
            myKnight = new Knight(nextLocation.substring(0, 1), nextLocation.substring(1, 2));
            for (String element : myKnight.determineAllPossibleLocations()) {
                if(!newList.contains(element)){
                    newList.add(element);
                }
            }
            currentIndex += 1;
        }
        assertEquals(35, newList.size());
    }

    @ParameterizedTest
    @MethodSource("positionAndMovesProvider")
    void testComputeNumberOfMoves(final String startFile, final String startRank,
                                  final String endFile, final String endRank,
                                  final int expectedMoves){
        Knight myKnight = null;

        myKnight = new Knight(startFile, startRank);
        int numberOfMoves = myKnight.computeNumberOfMoves(endFile, endRank);
        assertEquals(expectedMoves, numberOfMoves);
    }

    static Stream<Arguments> positionAndMovesProvider() {
        return Stream.of(Arguments.of("a", "1", "a", "1", 0),
                Arguments.of("a", "1", "h", "8", 6),
                Arguments.of("a", "1", "a", "2", 3),
                Arguments.of("a", "1", "c", "2", 1),
                Arguments.of("a", "1", "b", "4", 2));
    }
}
