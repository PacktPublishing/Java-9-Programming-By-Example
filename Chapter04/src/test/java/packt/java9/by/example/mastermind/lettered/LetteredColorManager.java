package packt.java9.by.example.mastermind.lettered;

import packt.java9.by.example.mastermind.Color;
import packt.java9.by.example.mastermind.ColorManager;

public class LetteredColorManager extends ColorManager {
    public LetteredColorManager(int nrColors) {
        super(nrColors);
    }

    protected Color newColor() {
        return new LetteredColor();
    }
}
