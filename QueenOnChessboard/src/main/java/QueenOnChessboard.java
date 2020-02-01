import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueenOnChessboard {

    private static final char MIN_RANK = "1".charAt(0);
    private static final char MAX_RANK = "8".charAt(0);
    private static final char MIN_FILE = "A".charAt(0);
    private static final char MAX_FILE = "H".charAt(0);

    public static List<String> availableMoves(String position) {
        List<String> allMoves = new ArrayList<>();

        if (!isValidPosition(position)) {
            return new ArrayList<>();
        } else {
            allMoves.addAll(moveVertically(position));
            allMoves.addAll(moveHorizontally(position));
            allMoves.addAll(moveDiagonally(position));
            Collections.sort(allMoves);
            return allMoves;
        }
    }

    private static List<String> moveDiagonally(String position) {
        List<String> diagonalMoves = new ArrayList<>();

        char startingFile = position.charAt(0);
        char startingRank = position.charAt(1);
        char nextRankUp = startingRank;
        char nextRankDown = startingRank;
        for (char nextFile = ++startingFile; nextFile <= MAX_FILE; nextFile++) {
            nextRankUp++;
            addNewPosition(nextFile, nextRankUp, diagonalMoves);
            nextRankDown--;
            addNewPosition(nextFile, nextRankDown, diagonalMoves);
        }

        startingFile = position.charAt(0);
        startingRank = position.charAt(1);
        nextRankUp = startingRank;
        nextRankDown = startingRank;
        for (char nextFile = --startingFile; nextFile >= MIN_FILE; nextFile--) {
            nextRankUp++;
            addNewPosition(nextFile, nextRankUp, diagonalMoves);
            nextRankDown--;
            addNewPosition(nextFile, nextRankDown, diagonalMoves);
        }

        return diagonalMoves;
    }

    private static List<String> moveHorizontally(String position) {
        List<String> horizontalMoves = new ArrayList<>();
        char startingRank = position.charAt(1);

        char startingFile = position.charAt(0);
        for (char newFile = --startingFile; newFile >= MIN_FILE; newFile--) {
            addNewPosition(newFile, startingRank, horizontalMoves);
        }

        startingFile = position.charAt(0);
        for (char newFile = ++startingFile; newFile <= MAX_FILE; newFile++) {
            addNewPosition(newFile, startingRank, horizontalMoves);
        }

        return horizontalMoves;
    }

    private static List<String> moveVertically(String position) {
        List<String> verticalMoves = new ArrayList<>();
        char startingFile = position.charAt(0);

        char startingRank = position.charAt(1);
        for (char newRank = --startingRank; newRank >= MIN_RANK; newRank--) {
            addNewPosition(startingFile, newRank, verticalMoves);
        }
        startingRank = position.charAt(1);
        for (char newRank = ++startingRank; newRank <= MAX_RANK; newRank++) {
            addNewPosition(startingFile, newRank, verticalMoves);
        }
        return verticalMoves;
    }

    private static Boolean isValidPosition(String position) {
        boolean returnValue = false;
        String validFiles = "ABCDEFGH";
        String validRanks = "12345678";

        if (position == null || position.isEmpty()) {
            returnValue = false;
        } else if (position.length() != 2) {
            returnValue = false;
        } else if (validFiles.contains(position.substring(0, 1)) && validRanks.contains(position.substring(1, 2))) {
            returnValue = true;
        }
        return returnValue;
    }

    private static void addNewPosition(char newFile, char newRank, List<String> possiblePositions) {
        String newLocation = newFile + Character.toString(newRank);
        if (isValidPosition(newLocation)) {
            if (!possiblePositions.contains(newLocation)) {
                possiblePositions.add(newLocation);
            }
        }
    }

}
