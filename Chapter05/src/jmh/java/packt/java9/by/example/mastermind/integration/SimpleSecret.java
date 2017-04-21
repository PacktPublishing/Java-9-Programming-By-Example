package packt.java9.by.example.mastermind.integration;

import packt.java9.by.example.mastermind.Color;
import packt.java9.by.example.mastermind.ColorManager;
import packt.java9.by.example.mastermind.Guess;

public class SimpleSecret implements Secret {
    private final ColorManager manager;

    public SimpleSecret(ColorManager manager) {
        this.manager = manager;
    }

    @Override
    public Guess createSecret( int nrColumns) {
        int nrColors = manager.getNrColors();
        Color[] colors = new Color[nrColumns];
        int count = 0;
        Color color = manager.firstColor();
        while (count < nrColors - nrColumns) {
            color = manager.nextColor(color);
            count++;
        }
        for (int i = 0; i < nrColumns; i++) {
            colors[i] = color;
            color = manager.nextColor(color);
        }
        return new Guess(colors);
    }
}
