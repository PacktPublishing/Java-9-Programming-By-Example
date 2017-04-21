package packt.java9.by.example.ch03;

import java.util.Comparator;

/**
 * Sort a collection that contains elements. The primitive operations 'swap' and 'compare' have to be
 * implemented by the caller and provided by means of class implementations or lambda to the actual sorter
 * via the setters before calling the method {@code sort()}.
 *
 */
public interface Sort<E> {
    void sort(SortableCollection<E> collection);
    void setSwapper(Swapper swap);
    void setComparator(Comparator<E> compare);
}
