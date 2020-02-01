import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight {

    private static final int MOVE_RIGHT_OR_UP_TWO = 2;
    private static final int MOVE_RIGHT_OR_UP_ONE = 1;
    private static final int MOVE_LEFT_OR_DOWN_ONE = -1;
    private static final int MOVE_LEFT_OR_DOWN_TWO = -2;
    private static final int MAX_RANK = 8;

    private String rank;
    private String file;
    private String[] validFiles = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};

    public Knight(String newFile, String newRank) {
        setRank(newRank);
        setFile(newFile);
    }

    public String getFile() {
        return file;
    }

    private void setFile(final String newFile) {
        if (Arrays.asList(validFiles).contains(newFile)) {
            this.file = newFile;
        } else {
            throw new RuntimeException("Invalid File " + newFile);
        }
    }

    public String getRank() {
        return rank;
    }

    private void setRank(final String newRank){
        if ((Integer.parseInt(newRank) >= 1)
             && (Integer.parseInt(newRank) <= 8)) {
            this.rank = newRank;
        }else {
            throw new RuntimeException("Invalid Rank " + newRank);
        }
    }

    public String getPosition() {
        return this.getFile() + this.getRank();
    }

    public final void move(final int fileDistance, final int rankDistance) {
        this.moveFile(fileDistance);
        this.moveRank(rankDistance);
    }

    private void moveRank(final int distance){
        this.setRank(Integer.toString(Integer.parseInt(this.getRank()) + distance));
    }

    private void moveFile(final int distance){
        this.setFile(validFiles[Arrays.asList(validFiles).indexOf(this.getFile()) + distance]);
    }

    public final List<String> determineAllPossibleLocations() {
        Knight workingPiece = null;
        List<String> list = new ArrayList<String>();
        boolean validLocation = true;

        int[][] possibleMoves = {
                {MOVE_RIGHT_OR_UP_ONE, MOVE_RIGHT_OR_UP_TWO},
                {MOVE_RIGHT_OR_UP_TWO, MOVE_RIGHT_OR_UP_ONE},
                {MOVE_RIGHT_OR_UP_TWO, MOVE_LEFT_OR_DOWN_ONE},
                {MOVE_RIGHT_OR_UP_ONE, MOVE_LEFT_OR_DOWN_TWO},
                {MOVE_LEFT_OR_DOWN_ONE, MOVE_RIGHT_OR_UP_TWO},
                {MOVE_LEFT_OR_DOWN_TWO, MOVE_RIGHT_OR_UP_ONE},
                {MOVE_LEFT_OR_DOWN_ONE, MOVE_LEFT_OR_DOWN_TWO},
                {MOVE_LEFT_OR_DOWN_TWO, MOVE_LEFT_OR_DOWN_ONE}
        };

        for (int x = 0; x < MAX_RANK; x++){
            workingPiece = null;
            validLocation = true;
            workingPiece = new Knight(this.getFile(), this.getRank());
            try{
                workingPiece.move(possibleMoves[x][0],
                                  possibleMoves[x][1]);
            } catch(final RuntimeException rte) {
                validLocation = false;
            }
            if (validLocation){
                list.add(workingPiece.getPosition());
            }
        }
        return list;
    }

    public int computeNumberOfMoves(String newFile, String newRank) {
        Knight knightInMovement = null;
        Knight knightsDestination = null;

        List<String> list = new ArrayList<String>();
        List<String> newList = new ArrayList<String>();

        knightsDestination = new Knight(newFile, newRank);
        knightInMovement = new Knight(this.getFile(), this.getRank());

        list.add(this.getPosition());
        newList.add(this.getPosition());

        int numberOfMoves = 0;
        while (!newList.contains(knightsDestination.getPosition())){
            for (String newLocations : newList) {
                if (!list.contains(newLocations)) {
                    list.add(newLocations);
                }
            }
            for (String nextLocation : list){
                knightInMovement = null;
                newList.add(nextLocation);
                knightInMovement = new Knight(nextLocation.substring(0, 1),
                                              nextLocation.substring(1, 2));
                for (String element : knightInMovement.determineAllPossibleLocations()){
                    if(!newList.contains(element)){
                        newList.add(element);
                    }
                }
            }
            numberOfMoves += 1;
        }
        return numberOfMoves;
    }
}