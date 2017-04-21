package packt.java9.by.example.ch03.qsort;

import packt.java9.by.example.ch03.SortableCollection;
import packt.java9.by.example.ch03.Swapper;

import java.util.Comparator;

public class Qsort<E> {
    final private Comparator<E> comparator;
    final private Swapper swapper;

    public Qsort(Comparator<E> comparator, Swapper swapper) {
        this.comparator = comparator;
        this.swapper = swapper;
    }

    public void qsort(SortableCollection<E> sortable, int start, int end) {
        if (start < end) {
            final E pivot = sortable.get(start);
            final Partitioner<E> partitioner = new Partitioner<>(comparator, swapper);
            int cutIndex = partitioner.partition(sortable, start, end, pivot);
            if (cutIndex == start) {
                cutIndex++;
            }
            qsort(sortable, start, cutIndex - 1);
            qsort(sortable, cutIndex, end);
        }
    }
}
