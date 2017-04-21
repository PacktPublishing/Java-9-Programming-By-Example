package packt.java9.by.example.ch03.main.bubble;

import packt.java9.by.example.ch03.SortableCollection;

import java.util.ArrayList;

public class ArrayListSortableCollection<E> implements SortableCollection<E> {
    final private ArrayList<E> actualNames;

    ArrayListSortableCollection(ArrayList<E> actualNames) {
        this.actualNames = actualNames;
    }

    @Override
    public E get(int i) {
        return actualNames.get(i);
    }

    @Override
    public int size() {
        return actualNames.size();
    }
}
