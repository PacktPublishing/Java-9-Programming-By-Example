package packt.java9.by.example.mastermind;

import java.util.HashSet;
import java.util.Set;

/**
 * A unique guesser creates guesses that do not use a color twice in a row.
 */
public class UniqueGuesser extends Guesser {


    public UniqueGuesser(Table table) {
        super(table);
    }

    /**
     * Set the first possible guess. This method is used during the construction process.
     */
    @Override
    protected void setFirstGuess() {
        int i = lastGuess.length-1;
        for (Color color = table.manager.firstColor();
             i >= 0;
             color = table.manager.nextColor(color)) {
            lastGuess[i--] = color;
        }
    }

    /**
     * @param guess that we check for containing one color only once
     * @return true if the guess does not contain any color more than once
     */
    private boolean isNotUnique(Color[] guess) {
        final Set<Color> alreadyPresent = new HashSet<>();
        for (Color color : guess) {
            if (alreadyPresent.contains(color)) {
                return true;
            }
            alreadyPresent.add(color);
        }
        return false;
    }

    @Override
    protected Color[] nextGuess() {
        Color[] guess = super.nextGuess();
        while (isNotUnique(guess)) {
            guess = super.nextGuess();
        }
        return guess;
    }

}
