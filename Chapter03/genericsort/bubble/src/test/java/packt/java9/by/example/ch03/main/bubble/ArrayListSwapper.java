package packt.java9.by.example.ch03.main.bubble;

import packt.java9.by.example.ch03.Swapper;

import java.util.ArrayList;

public class ArrayListSwapper<E> implements Swapper {
    final private ArrayList<E> actualNames;

    ArrayListSwapper(ArrayList<E> actualNames) {
        this.actualNames = actualNames;
    }

    @Override
    public void swap(int i, int j) {
        E tmp = actualNames.get(i);
        actualNames.set(i, actualNames.get(j));
        actualNames.set(j, tmp);
    }
}
