package packt.java9.by.example.mastermind;

import org.junit.Assert;
import org.junit.Test;

public class UniqueGuesserTest {

    static final int NR_COLORS = 6;
    static final int NR_COLUMNS = 4;

    @Test
    public void generateAllGuesses() {
        int numberOfGuesses = 0;
        ColorManager manager = new ColorManager(NR_COLORS);
        Table table = new Table(NR_COLUMNS, manager);
        Guesser guesser = new UniqueGuesser(table);
        while (guesser.nextGuess() != Guesser.none) {
            numberOfGuesses++;
        }
        Assert.assertEquals(6 * 5 * 4 * 3, numberOfGuesses);
    }

    @Test
    public void keepsGettingTillItBleedsOut(){
        int numberOfGuesses = 0;
        ColorManager manager = new ColorManager(NR_COLORS);
        Table table = new Table(NR_COLUMNS, manager);
        Guesser guesser = new UniqueGuesser(table);
        while (guesser.guess() != Row.none) {
            numberOfGuesses++;
        }
    }
}
