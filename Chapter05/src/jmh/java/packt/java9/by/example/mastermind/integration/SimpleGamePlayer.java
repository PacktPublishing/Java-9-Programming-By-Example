package packt.java9.by.example.mastermind.integration;

import packt.java9.by.example.mastermind.*;
import packt.java9.by.example.mastermind.lettered.LetteredColorFactory;

public class SimpleGamePlayer implements Player {
    final int nrColors = 10;
    final int nrColumns = 6;
    final private ColorManager manager = new ColorManager(nrColors, new LetteredColorFactory());

    @Override
    public void play() {
        Table table = new Table(nrColumns, manager);
        Secret secret = new RandomSecret(manager);
        Guess secretGuess = secret.createSecret(nrColumns);
        Game game = new Game(table, secretGuess);

        Guesser guesser = new UniqueGuesser(table);
        while (!game.isFinished()) {
            Guess guess = guesser.guess();
            game.addNewGuess(guess);
        }
    }
}
