package packt.java9.by.example.mastermind.integration;

import packt.java9.by.example.mastermind.Guess;

public interface Secret {

    Guess createSecret(int nrColumns);
}
