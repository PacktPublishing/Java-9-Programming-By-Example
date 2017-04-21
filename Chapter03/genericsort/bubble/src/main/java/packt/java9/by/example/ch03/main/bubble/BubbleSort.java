package packt.java9.by.example.ch03.main.bubble;

import packt.java9.by.example.ch03.Sort;
import packt.java9.by.example.ch03.SortableCollection;
import packt.java9.by.example.ch03.Swapper;

import java.util.Comparator;

public class BubbleSort<E> implements Sort<E> {
    @Override
    public void sort(SortableCollection collection) {
        int n = collection.size();
        while (n > 1) {
            for (int j = 0; j < n - 1; j++) {
                if (comparator.compare(collection.get(j),
                        collection.get(j + 1)) > 0) {
                    swapper.swap(j, j + 1);
                }
            }
            n--;
        }
    }

    private Comparator comparator = null;

    @Override
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    private Swapper swapper = null;

    @Override
    public void setSwapper(Swapper swapper) {
        this.swapper = swapper;
    }
}
