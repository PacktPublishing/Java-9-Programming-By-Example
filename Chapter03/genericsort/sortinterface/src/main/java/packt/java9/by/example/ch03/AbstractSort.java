package packt.java9.by.example.ch03;

import java.util.Comparator;

public abstract class AbstractSort<E> implements Sort<E> {
    protected Comparator<E> comparator = null;

    @Override
    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;

    }

    protected Swapper swapper = null;

    @Override
    public void setSwapper(Swapper swapper) {
        this.swapper = swapper;
    }
}
