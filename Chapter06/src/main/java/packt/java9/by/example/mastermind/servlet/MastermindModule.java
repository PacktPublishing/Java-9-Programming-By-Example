package packt.java9.by.example.mastermind.servlet;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import packt.java9.by.example.mastermind.ColorFactory;
import packt.java9.by.example.mastermind.Guess;
import packt.java9.by.example.mastermind.Guesser;
import packt.java9.by.example.mastermind.UniqueGuesser;
import packt.java9.by.example.mastermind.lettered.LetteredColor;
import packt.java9.by.example.mastermind.lettered.LetteredColorFactory;

public class MastermindModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(int.class).annotatedWith(Names.named("nrColors")).toInstance(6);
        bind(int.class).annotatedWith(Names.named("nrColumns")).toInstance(4);
        bind(ColorFactory.class).to(LetteredColorFactory.class);
        bind(Guesser.class).to(UniqueGuesser.class);
    }
}
