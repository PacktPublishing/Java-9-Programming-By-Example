package packt.java9.by.example.mastermind;

import java.util.Arrays;

/**
 * One row on the table. It contains the guesses at position and also the
 * number of matching colors and position.
 */
public class Row {
    final Color[] positions;
    protected int matchedPositions;
    protected int matchedColors;
    public static final Row none = new Row(Guesser.none);

    public Row(Color[] positions) {
        this.positions = Arrays.copyOf(positions, positions.length);
    }

    protected Row(Row cloneFrom) {
        this(cloneFrom.positions);
        setMatch(cloneFrom.matchedPositions, cloneFrom.matchedColors);
    }

    public void setMatch(int matchedPositions, int matchedColors) {
        if (matchedColors + matchedPositions > positions.length) {
            throw new IllegalArgumentException(
                    "Number of matches can not be more that the position.");
        }
        this.matchedColors = matchedColors;
        this.matchedPositions = matchedPositions;
    }

    public boolean guessMatches(Color[] guess) {
        return nrMatchingColors(guess) == matchedColors &&
                nrMatchingPositions(guess) == matchedPositions;
    }

    /**
     * Count the number of colors that are present on the guess but not on the position where they are in the secret.
     * If the same color is on multiple position it is counted for each position once. For example the secret is
     * <pre>
     *     RGRB
     * </pre>
     * and the guess is
     * <pre>
     *     YRPR
     * </pre>
     * then this method will return 2.
     *
     * @param guess is the actual guess that we evaluate
     * @return the number of good colors out of position
     */
    public int nrMatchingColors(Color[] guess) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            for (int j = 0; j < positions.length; j++) {
                if (i != j && guess[i] == positions[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Count the number of colors that are correct and are in position.
     *
     * @param guess is the actual guess that we evaluate
     * @return the number of colors that match in position
     */
    public int nrMatchingPositions(Color[] guess) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == positions[i]) {
                count++;
            }
        }
        return count;
    }

    public int nrOfColumns() {
        return positions.length;
    }

}
