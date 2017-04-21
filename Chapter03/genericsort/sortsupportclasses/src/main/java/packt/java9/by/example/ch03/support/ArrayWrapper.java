package packt.java9.by.example.ch03.support;

import packt.java9.by.example.ch03.SortableCollection;

public class ArrayWrapper<E> implements SortableCollection<E> {
    private final E[] array;

    public ArrayWrapper(E[] array) {
        this.array = array;
    }

    public E[] getArray() {
        return array;
    }

    @Override
    public E get(int i) {
        return array[i];
    }

    @Override
    public int size() {
        return array.length;
    }
}

