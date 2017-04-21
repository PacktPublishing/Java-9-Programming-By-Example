package packt.java9.by.example.mastermind.integration;

import packt.java9.by.example.mastermind.Color;
import packt.java9.by.example.mastermind.ColorManager;
import packt.java9.by.example.mastermind.Guess;

import java.util.ArrayList;
import java.util.List;

public class RandomSecret implements Secret {
    private final ColorManager manager;

    public RandomSecret(ColorManager manager) {
        this.manager = manager;
    }

    @Override
    public Guess createSecret(int nrColumns) {
        int nrColors = manager.getNrColors();
        Color[] colors = new Color[nrColumns];
        List<Color> allColors = new ArrayList<>(nrColors);
        Color color = manager.firstColor();
        for (int count = 0; count < nrColors; count++) {
            allColors.add(color);
            color = manager.nextColor(color);
        }
        for (int i = 0; i < nrColumns; i++) {
            int index = (int) Math.floor(Math.random() * (allColors.size() + 1));
            if( index >= allColors.size() ){
                index --;
            }
            colors[i] = allColors.remove(index);
        }
        return new Guess(colors);
    }
}
