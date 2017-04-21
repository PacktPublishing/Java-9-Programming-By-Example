package packt.java9.by.example.ch03.quick;

import packt.java9.by.example.ch03.AbstractSort;
import packt.java9.by.example.ch03.SortableCollection;
import packt.java9.by.example.ch03.qsort.FJQuickSort;

public class QuickSort<E> extends AbstractSort<E> {
    public void sort(SortableCollection<E> sortable) {
        int n = sortable.size();
        FJQuickSort<E> qsort = new FJQuickSort<>(comparator,swapper);
        qsort.qsort(sortable, 0, n-1);
    }
}

